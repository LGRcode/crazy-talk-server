package com.sukito.crazytalkserver.service;

import java.nio.file.Path;

import org.springframework.stereotype.Service;

@Service
public class AudioFileService {
	
	public Path getNewFilePathTest(Path downloadedFile){
		return downloadedFile;
	}
	
}
