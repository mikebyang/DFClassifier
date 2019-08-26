package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Parser {
	//image sizes width x height
	private static int[][] sizes = {{28,28}, {60,74}};
	//file paths
	private static String digit_path = "./data/digitdata/";
	private static String face_path = "./data/facedata/";
	//training path
	private static String[] trainfil = {digit_path + "trainingimages", face_path + "facedatatrain"};
	private static String[] trainlabels = {digit_path + "traininglabels", face_path + "facedatatrainlabels"};
	//validation path
	private static String[] valim = {digit_path + "validationimages", face_path + "facedatavalidation"};
	private static String[] valla = {digit_path + "validationlabels", face_path + "facedatavalidationlabels"};
	//test path
	private static String[] testim = {digit_path + "testimages", face_path + "facedatatest"};
	private static String[] testla = {digit_path + "testlabels", face_path + "facedatatestlabels"};
	//phase - 1 for train, 2 - for validation, 3 for test
	public static ArrayList<DObj> parse(int phase, int classifier){
		/*
		 * @param Phases: 1 - training, 2 - validation, 3 - test
		 * @return ArrayList of DObj objects
		 */
		ArrayList<DObj> objList = new ArrayList<DObj>();
		String imgPath = "";
		String labPath = "";
		int width = 0, height = 0;
		switch(phase) {
		case 1://phase 1 - training
			imgPath = trainfil[0+classifier];
			labPath = trainlabels[0+classifier];
			width = sizes[0+classifier][0];
			height = sizes[0+classifier][1];
			break;
		case 2://phase 2 - validation
			imgPath = valim[0+classifier];
			labPath = valla[0+classifier];
			width = sizes[0+classifier][0];
			height = sizes[0+classifier][1];
			break;
		case 3://phase 3 - test
			imgPath = testim[0+classifier];
			labPath = testla[0+classifier];
			width = sizes[0+classifier][0];
			height = sizes[0+classifier][1];
			break;
		}
		
		try {
			//create FIFO queue with all labels
			Scanner lsc = new Scanner(new File(labPath));
			Queue<Integer> labels = new LinkedList<Integer>();
			while(lsc.hasNext()) {
				labels.add(Integer.parseInt(lsc.nextLine()));
			}
			lsc.close();
			//extract images and convert them into DObj and place in stack
			Scanner isc = new Scanner(new File(imgPath));
			ArrayList<String> line = new ArrayList<String>();
			int counter = 0;
			while(isc.hasNext()) {
				if(counter == height) {
					objList.add(new DObj(width, height, line.toArray(new String[line.size()]), 
							labels.remove()));
					counter = 0;
					line = new ArrayList<String>();
					continue;
				}
				line.add(isc.nextLine());
				counter++;
			}
			while(height-line.size() != 0) {
				line.add(String.format("%1$"+width+"s", " "));
			}
			objList.add(new DObj(width, height, 
					line.toArray(new String[line.size()]), 
					labels.remove()));
			isc.close();
		}
		catch(FileNotFoundException FNF) {
			System.out.println("File was not found!");
			FNF.printStackTrace();
			System.exit(1);
		}
		catch(Exception e) {
			System.out.println("Error reading file!");
			e.printStackTrace();
			System.exit(1);
		}
		
		return objList;
	}
	public static int[] getSize(int classifier) {
		return sizes[classifier];
	}
}
