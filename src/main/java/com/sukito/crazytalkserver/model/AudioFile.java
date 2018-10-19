package com.sukito.crazytalkserver.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.util.StringUtils;

public class AudioFile {
	
	private int id;
	private String name;
	
	
	public AudioFile() {
	
	}
	
	public AudioFile(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public static void main(String[] args) {
		
		List<Integer> decimalList = Arrays.asList(new Integer[]{1,15,5,7,3});
		printList(decimalList);
		sortByBinary(decimalList);
		printList(decimalList);
	}

	private static void printList(List<Integer> decimalList) {
		StringBuilder sb = new StringBuilder();
		for (Integer integer : decimalList) {
			sb.append(integer).append(", ");
		}
		System.out.println(sb);
	}
	
	public static void sortByBinary(List<Integer> decimalsList) {
		
		Collections.sort(decimalsList, new Comparator<Integer>() {

			@Override
			public int compare(Integer intOne, Integer intTwo) {
				int intOneOccurrences = getNumberOfOneOccurrences(intOne);
				int intTwoOccurrences = getNumberOfOneOccurrences(intTwo);
				
				if(intOneOccurrences < intTwoOccurrences){
					return 1;
				} else if (intOneOccurrences > intTwoOccurrences){
					return -1;
				} else {
					return Integer.compare(intOne, intTwo);
				}
			}

			private int getNumberOfOneOccurrences(Integer intNumber) {
				String	binaryRepresentation = Integer.toBinaryString(intNumber);
				return StringUtils.countOccurrencesOf(binaryRepresentation, "1");
			}
		});
	}
	
	

}
