package core;

import parser.Data_obj;
import parser.Parser;
import parser.Parser2;
import perceptron.Face_Classifier;
import perceptron.Num_Classifier;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.List;
import java.util.Scanner;

public class core {
	public static void main(String[] args) {
		int algo = -1, classifier_type= -1;
		double train_per = -1,  lRate = -1;
//		String prev_sess = "";
//				filename = "";
		
		//get inputs from user for the current session
		Scanner sc = new Scanner(System.in);
		
//		boolean new_sess = true;
		while(true) {
			try {
//				System.out.print("Proceed with new session? ");
//				String ans = sc.nextLine();
//				switch(ans.toLowerCase()) {
//					case "no": //using information from an old session
//						System.out.print("Please input the file name for the previous session:  ");
//						prev_sess = sc.nextLine();
//						
//						break;
//					case "yes": //new session
//						System.out.print("Please input name of training file: ");
//						filename = sc.nextLine();
						
						System.out.println("Please input percentage of training data to be used (XXX%)");
						System.out.print("(MAX: 100%, MIN: 0%) : ");
						train_per = sc.nextInt();
						if(train_per > 100 || train_per < 0) {
							throw new IOException();
						}
						
						System.out.print("Please input learning rate (MAX: 1, MIN: Greater Than 0): ");
						lRate = sc.nextDouble();
						if(lRate > 1 || lRate <= 0) {
							throw new IOException();
						}

						
						System.out.println("Please input method of Machine Learning:");
						System.out.println("0 - Naive Bayes, 1 - Perceptron");
						algo = sc.nextInt();
						if(algo > 1 || algo < 0) {
							throw new IOException();
						}
						
						System.out.println("Please input type of classification: ");
						System.out.println("0 - Digits, 1 - Faces");
						classifier_type = sc.nextInt();
						if(classifier_type > 1 || classifier_type < 0) {
							throw new IOException();
						}
//						break;
//				}
				break;
			}
			catch(IOException ioe){
				System.out.println("Incorrect Input Detected!");
				sc.nextLine();
				continue;
			}
			catch(Exception e) {
				System.out.println("An Unknown Error Occurred.");
				e.printStackTrace();
				sc.close();
				return;
			}
		}
		
		sc.close();
		
		switch(algo) {
			case 0:
				//Naive Bayes
			case 1:
				//Perceptron
				String imgFile = "", labFile = "";
				switch(classifier_type) {
					case 0:
						imgFile = "trainingimages";
						labFile = "traininglabels";
						break;
					case 1:
						imgFile = "facedatatrain";
						labFile = "facedatatrainlabels";
				}
				
				ArrayList<Data_obj> training_nodes = null;
				ArrayList<Data_obj> val_nodes = null;
				
				double corr = 0.0;
				double tots = 0.0;
				int guess = -1;
				
				switch(classifier_type) {
					case 0://digits
						try {
							training_nodes = Parser.trainParse(classifier_type, imgFile, labFile, 0);
						} catch (FileNotFoundException e) {
							e.printStackTrace();
							return;
						}
						
						Num_Classifier.num_train(training_nodes, train_per, lRate);
						
						try {
							val_nodes = Parser.trainParse(classifier_type, "validationimages", "validationlabels", 1);
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
							return;
						}
						
						Num_Classifier.val_check(val_nodes, lRate);
						
						ArrayList<Data_obj> class_nodes = null;
						
						try {
							 class_nodes = Parser.trainParse(classifier_type, "testimages", "testlabels", 1);
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
						
						for(int i = 0; i<class_nodes.size(); i++) {
							guess = Num_Classifier.classify(class_nodes.get(i).getData(), lRate);
							if(guess == class_nodes.get(i).getLabel()) {
								corr++;
								tots++;
							}
							tots++;
						}
						
						System.out.printf("Amount Predicted Correctly: %10.1f%%\n", ((corr/tots)*100));
						
						break;
					case 1://faces
						try {
							training_nodes = Parser2.trainParse(classifier_type, imgFile, labFile, 0);
						} catch (FileNotFoundException e) {
							e.printStackTrace();
							return;
						}
						Face_Classifier.face_train(training_nodes, train_per, lRate);
						
						try {
							val_nodes = Parser2.trainParse(classifier_type, "facedatavalidation", "facedatavalidationlabels", 1);
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
							return;
						}
						
						Face_Classifier.val_check(val_nodes, lRate);
						
						ArrayList<Data_obj> face_nodes = null;
						try {
							 face_nodes = Parser2.trainParse(classifier_type, "facedatatest", "facedatatestlabels", 0);
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
						
						for(int i = 0; i<face_nodes.size(); i++) {
							guess = Face_Classifier.classify(face_nodes.get(i).getData(), lRate);
							if(guess == face_nodes.get(i).getLabel()) {
								corr++;
								tots++;
							}
							tots++;
						}
						
						System.out.printf("Amount Predicted Correctly: %10.1f%%\n", ((corr/tots)*100));
						break;
				}
				
		}
		
	}
}
