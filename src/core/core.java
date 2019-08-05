package core;

import parser.Data_obj;
import parser.Parser;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class core {
	public static void main(String[] args) {
		int train_per = -1, algo = -1, classifier_type= -1, lRate = -1;
		String prev_sess = "", filename = "";
		//get inputs from user for the current session
		Scanner sc = new Scanner(System.in);
		boolean new_sess = true;
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
						
						System.out.print("Please input percentage of training data to be used: ");
						train_per = sc.nextInt();
						if(train_per > 100 || train_per < 0) {
							throw new IOException();
						}
						
						System.out.print("Please input percentage of training data to be used: ");
						lRate = sc.nextInt();
						if(lRate > 100 || lRate < 0) {
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
				return;
			}
		}
		
		sc.close();
		
		switch(algo) {
			case 0:
				//Naive Bayes
			case 1:
				//Perceptron
				try {
					Parser.trainParse(classifier_type);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				Data_obj[] training_nodes = Parser.getData();
				
		}
		
	}
}
