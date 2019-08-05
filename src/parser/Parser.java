package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {
	//Array with all the training data objects
	public static List<Data_obj> data = new ArrayList<>();
	
	public static String num_tpath = "/data/digitdata/trainingimages";
	public static String num_lpath = "/data/digitdata/traininglabels";
	
	public static String face_tpath = "/data/facedata/facedatatrain";
	public static String face_lpath = "/data/facedata/facedatatrainlabels";
	
	public static Data_obj[] getData() {
		return (Data_obj[]) data.toArray();
	}
	
	public static boolean isBlank(String str) {
		if(str.trim().length() <= 0) {
			return true;
		}
		return false;
	}
	
	public static void trainParse(int classification) throws FileNotFoundException {
		String training_fil = "";
		String training_lfil = "";
		switch(classification) {
			case 0:
				training_fil = num_tpath;
				training_lfil = num_lpath;
				break;
			case 1:
				training_fil = face_tpath;
				training_lfil = face_lpath;
				break;
		}
		
		//data scanner
		Scanner dsc = new Scanner(new File(training_fil));
		//label scanner
		Scanner lsc = new Scanner(new File(training_lfil));
		
		String img_part = "";
		int label = -1;
		List<String> img_temp = new ArrayList<>();
		
		while(dsc.hasNext() && lsc.hasNext()) {
			if(isBlank(img_part = dsc.nextLine()) && img_temp.isEmpty()) {
				continue;
			}
			else if(isBlank(img_part = dsc.nextLine()) && !img_temp.isEmpty()) {
				data.add(new Data_obj());
			}
			else {
				
			}
		}
	}
	
	
	//function testing
	public static void main(String[] args) {
		try {
			trainParse(0);
		}
		catch(Exception e) {
			System.out.println("There was a problem!");
			e.printStackTrace();
		}
	}
}
