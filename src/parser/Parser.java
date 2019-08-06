package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Parser {
	//training file paths
	private static String num_tpath = "./data/digitdata/";
	private static String num_lpath = "./data/digitdata/";
	
	private static String face_tpath = "./data/facedata/";
	private static String face_lpath = "./data/facedata/";
	
	private static int maxw = 0;
	private static int maxh = 0;
	
	//helper functions
	public static boolean isBlank(String str) {//check if string contains only whitespaces
		if(str.trim().length() <= 0) {
			return true;
		}
		return false;
	}
	public static int cell_counter(String str) {//count number of non-whitespace characters
		return str.replace(" ", "").length();
	}
	private static int[] isOdd(int num) {
		int [] ans = new int[2];
		
		ans[0] = num%2;
		ans[1] = (num-ans[0])/2;
		
		return ans;
		
	}
	public static ArrayList<Data_obj>  normalize(Data_obj[] data) {
		/*
		 * @param data -> object array which contains objects with image fields to be normalized using the static variable maxw as the largest width
		 * and maxh as the largest height
		 * @return Data_obj array with normalized image fields
		 */
		int dif = 0;
		int[] ans;
		for(int i = 0; i < data.length; i++) {
			if(data[i].getHeight() < maxh) {
				dif = maxh - data[i].getHeight();
				ans = isOdd(dif);
				data[i].modHeight(ans[1], ans[0], maxw, maxh);
			}
			if(data[i].getWidth() < maxw) {
				dif = maxh - data[i].getWidth();
				ans = isOdd(dif);
				data[i].modWidth(ans[1], ans[0], maxh, maxw);
			}
		}
		return new ArrayList<>(Arrays.asList(data));
	}
	
	
	//parse img or labels files and input into object fields then store created objects into an array
	public static ArrayList<Data_obj> trainParse(int classification, String dat_fil, String lab_fil, int mode) throws FileNotFoundException {
		/*
		 * @param classification -> input to tell parser what type of image classification is to occur (0 means digit and 1 means face)
		 * @param dat_fil, lab_fil -> files that are to be parsed. dat_fil is the image file and lab_fil is the corresponding label file.
		 * @param mode -> meant to indicate whether this is a training file or a test/validation file (0 means training and 1 means test/validation)
		 * @return list of Data_obj with all images from the files and corresponding label fields
		 */
		
		//Array with all the training data objects
		ArrayList<Data_obj> data = new ArrayList<Data_obj>();
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
		List<String> img_temp = new ArrayList<String>();
		
//		switch(mode) {
//			case 0:
		while(dsc.hasNext() && lsc.hasNext()) {
			if(isBlank(img_part = dsc.nextLine()) && img_temp.isEmpty()) {
				continue;
			}
			else if(isBlank(img_part) && !img_temp.isEmpty()) {
				data.add(new Data_obj(Integer.parseInt(lsc.nextLine()), img_temp.toArray(new String[img_temp.size()])));
				if(mode == 0) {
					maxh = img_temp.size();
				}
				img_temp = new ArrayList<>();
				continue;
			}
			else {
				img_temp.add(img_part);
				if(mode == 0) {
					maxw = img_part.length();
				}
				continue;
			}
		}
		data = normalize(data.toArray(new Data_obj[data.size()]));
//				break;
//			case 1:
////				int cells = 0;
//				while(dsc.hasNext() && lsc.hasNext()) {
//					if(isBlank(img_part = dsc.nextLine()) && img_temp.isEmpty()) {
//						continue;
//					}
//					else if(isBlank(img_part) && !img_temp.isEmpty()) {
//						data.add(new Data_obj(Integer.parseInt(lsc.nextLine()), img_temp.toArray(new String[img_temp.size()])));
////						cells = 0;
//						img_temp = new ArrayList<>();
//						continue;
//					}
//					else {
//						img_temp.add(img_part);
////						cells += cell_counter(img_part);
//						continue;
//					}
//				}
//				break;
//		}
		
		dsc.close();
		lsc.close();
		
		return data;
	}
	
	
	
	
	
//	public static void imgParse(int classification, String dat_fil, String lab_fil) throws FileNotFoundException {
//		String training_fil = "";
//		String training_lfil = "";
//		switch(classification) {
//			case 0:
//				training_fil = num_tpath + dat_fil;
//				training_lfil = num_lpath + lab_fil;
//				break;
//			case 1:
//				training_fil = face_tpath + dat_fil;
//				training_lfil = face_lpath + lab_fil;
//				break;
//		}
//		
//		//data scanner
//		Scanner dsc = new Scanner(new File(training_fil));
//		//label scanner
//		Scanner lsc = new Scanner(new File(training_lfil));
//		
//		String img_part = "";
//		int cells = 0;
//		List<String> img_temp = new ArrayList<String>();
//		
//		while(dsc.hasNext() && lsc.hasNext()) {
//			if(isBlank(img_part = dsc.nextLine()) && img_temp.isEmpty()) {
//				continue;
//			}
//			else if(isBlank(img_part) && !img_temp.isEmpty()) {
//				data.add(new Data_obj(Integer.parseInt(lsc.nextLine()), img_temp.toArray(new String[img_temp.size()]), cells));
//				cells = 0;
//				img_temp = new ArrayList<>();
//				continue;
//			}
//			else {
//				img_temp.add(img_part);
//				cells += cell_counter(img_part);
//				continue;
//			}
//		}
//		
//		dsc.close();
//		lsc.close();
//	}
	
	
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
