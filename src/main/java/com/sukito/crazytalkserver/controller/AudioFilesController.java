package com.sukito.crazytalkserver.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sukito.crazytalkserver.service.AudioFileService;

@RestController
@RequestMapping("/audio")
public class AudioFilesController {
	
	@Autowired
	private AudioFileService audioFileService;
	
	@RequestMapping(method=RequestMethod.GET , value="/test")
	public String testHi(){
		return "Test";
	}
	
	
	@PostMapping("/")
	public String fileUpload(@RequestParam("file") MultipartFile file) {
		System.out.println("Ha entrado por POST");
		System.out.println(file.getName());
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		
		Path newFilePath = null;
				
		try {
			Path uploadedFile = Paths.get(""+"crazyTalk_"+new GregorianCalendar().getTimeInMillis()+".aac");
			Files.deleteIfExists(uploadedFile);
			Files.copy(file.getInputStream(), uploadedFile);
			newFilePath = uploadedFile;
			
			try
			{
//				newFilePath = getNewFilePath(downloadedFile);
				newFilePath = audioFileService.getNewFilePathTest(uploadedFile);
			}
			catch (Exception e)
			{
				System.err.println(e);
				e.printStackTrace();
			}
			//TODO hacer que devuelva la URL buena para descargarse el archivo tratado
			return "testFile.aac";
		} catch (IOException ioe) {
			LoggerFactory.getLogger(this.getClass()).error("file upload", ioe);
			return "Ha petado algo";
		}
	}

	
	@GetMapping(value = {"/{fileName}"})
	public void downloadFile(@PathVariable("fileName") String fileName 
			,HttpServletResponse response){
		
		System.out.println("Ha entrado por GET download");
		
		try {
			InputStream is = new FileInputStream(fileName);
			IOUtils.copy(is, response.getOutputStream());
			response.flushBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}


}
