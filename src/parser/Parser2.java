package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Parser2 {
	//training file paths
	private static String num_tpath = "./data/digitdata/";
	private static String num_lpath = "./data/digitdata/";
	
	private static String face_tpath = "./data/facedata/";
	private static String face_lpath = "./data/facedata/";
	
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
		List<String> img_temp = new ArrayList<String>();
		int num = 0;
		
		while(dsc.hasNext()) {
			if(num == 73) {
				img_temp.add(dsc.nextLine());
				data.add(new Data_obj(img_temp.toArray(new String[img_temp.size()])));
				img_temp = new ArrayList<>();
				num = 0;
				continue;
			}
			img_temp.add(dsc.nextLine());
			num++;
		}
		img_temp.add(dsc.nextLine());
		for(int k = 0; k<60-img_temp.size(); k++) {
			img_temp.add(String.format("%1$"+60+"s", " "));
		}
		data.add(new Data_obj(img_temp.toArray(new String[img_temp.size()])));
		dsc.close();
		
		Data_obj[] temp_arr = data.toArray(new Data_obj[data.size()]);
		
		dsc = new Scanner(new File(training_lfil));
		try {
			for(int i = 0; i < data.size(); i++) {
				temp_arr[i].addLab(Integer.parseInt(dsc.nextLine()));
			}
		}
		catch(Exception e) {
			
			e.printStackTrace();
			System.exit(1);
		}
		
		dsc.close();
		
		data =  new ArrayList<Data_obj>(Arrays.asList(temp_arr));
		
		return data;
	}
}
