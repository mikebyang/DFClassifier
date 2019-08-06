package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {
	//Array with all the training data objects
	private static List<Data_obj> data = new ArrayList<Data_obj>();
	
	//training file paths
	private static String num_tpath = "./data/digitdata/";
	private static String num_lpath = "./data/digitdata/";
	
	private static String face_tpath = "./data/facedata/";
	private static String face_lpath = "./data/facedata/";
	
	//helper functions
	public static List<Data_obj> getData() {
		return data;
	}
	public static boolean isBlank(String str) {//check if string contains only whitespaces
		if(str.trim().length() <= 0) {
			return true;
		}
		return false;
	}
	public static int cell_counter(String str) {//count number of non-whitespace characters
		return str.replace(" ", "").length();
	}
	
	//parse training data or labels files and input into object fields
	public static void trainParse(int classification, String dat_fil, String lab_fil) throws FileNotFoundException {
		String training_fil = "";
		String training_lfil = "";
		switch(classification) {
			case 0:
				training_fil = num_tpath + dat_fil;
				training_lfil = num_lpath + lab_fil;
				break;
			case 1:
				training_fil = face_tpath + dat_fil;
				training_lfil = face_lpath + lab_fil;
				break;
		}
		
		//data scanner
		Scanner dsc = new Scanner(new File(training_fil));
		//label scanner
		Scanner lsc = new Scanner(new File(training_lfil));
		
		String img_part = "";
//		int cells = 0;
		List<String> img_temp = new ArrayList<String>();
		
		while(dsc.hasNext() && lsc.hasNext()) {
			if(isBlank(img_part = dsc.nextLine()) && img_temp.isEmpty()) {
				continue;
			}
			else if(isBlank(img_part) && !img_temp.isEmpty()) {
				data.add(new Data_obj(Integer.parseInt(lsc.nextLine()), img_temp.toArray(new String[img_temp.size()])));
//				cells = 0;
				img_temp = new ArrayList<>();
				continue;
			}
			else {
				img_temp.add(img_part);
//				cells += cell_counter(img_part);
				continue;
			}
		}
		
		dsc.close();
		lsc.close();
	}
	
	public static void imgParse(int classification, String dat_fil, String lab_fil) throws FileNotFoundException {
		String training_fil = "";
		String training_lfil = "";
		switch(classification) {
			case 0:
				training_fil = num_tpath + dat_fil;
				training_lfil = num_lpath + lab_fil;
				break;
			case 1:
				training_fil = face_tpath + dat_fil;
				training_lfil = face_lpath + lab_fil;
				break;
		}
		
		//data scanner
		Scanner dsc = new Scanner(new File(training_fil));
		//label scanner
		Scanner lsc = new Scanner(new File(training_lfil));
		
		String img_part = "";
		int cells = 0;
		List<String> img_temp = new ArrayList<String>();
		
		while(dsc.hasNext() && lsc.hasNext()) {
			if(isBlank(img_part = dsc.nextLine()) && img_temp.isEmpty()) {
				continue;
			}
			else if(isBlank(img_part) && !img_temp.isEmpty()) {
				data.add(new Data_obj(Integer.parseInt(lsc.nextLine()), img_temp.toArray(new String[img_temp.size()]), cells));
				cells = 0;
				img_temp = new ArrayList<>();
				continue;
			}
			else {
				img_temp.add(img_part);
				cells += cell_counter(img_part);
				continue;
			}
		}
		
		dsc.close();
		lsc.close();
	}
	
	
//	//function testing
//	public static void main(String[] args) {
//		try {
//			trainParse(0);
//		}
//		catch(Exception e) {
//			System.out.println("There was a problem!");
//			e.printStackTrace();
//		}
//	}
}
