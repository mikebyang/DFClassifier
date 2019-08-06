package core;

import parser.Data_obj;
import parser.Parser;
import perceptron.Num_Classifier;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class core {
	public static void main(String[] args) {
		int algo = -1, classifier_type= -1;
		double train_per = -1,  lRate = -1;
		String prev_sess = "";
//				filename = "";
		
		//get inputs from user for the current session
		Scanner sc = new Scanner(System.in);
		
//		boolean new_sess = true;
		while(true) {
			try {
				System.out.print("Proceed with new session? ");
				String ans = sc.nextLine();
				switch(ans.toLowerCase()) {
					case "no": //using information from an old session
						System.out.print("Please input the file name for the previous session:  ");
						prev_sess = sc.nextLine();
						
						break;
					case "yes": //new session
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
						break;
				}
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
				
				List<Data_obj> training_nodes = null;
				
				try {
					training_nodes = Parser.trainParse(classifier_type, imgFile, labFile, 0);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					return;
				}
				
				switch(classifier_type) {
					case 0://digits
						Num_Classifier.num_train(training_nodes, train_per, lRate);
						break;
					case 1://faces
						break;
				}
				
		}
		
	}
}
