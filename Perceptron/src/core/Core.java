package core;

import java.util.ArrayList;
import java.util.Scanner;

import parser.DObj;
import parser.Parser;
import perceptron.Perceptron;

public class Core {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int c_type = -1, arrsize = -1;//which algorithm to be used and the images to be classified
		double usage_percent = -1, lRate = -1; //amount of training data to be used and the learning rate
		while(true) {
			try {
				System.out.print("Types of images to be classified (0 - Digits, 1 - Faces): ");
				c_type = sc.nextInt();
				switch(c_type) {
				case 0:
					arrsize = 10;
					break;
				case 1:
					arrsize = 2;
					break;
				}
				System.out.print("Percentage of training data to be used (___%): ");
				usage_percent = sc.nextDouble()/100.0;
				System.out.print("Learning rate to be used (MAX: 1, MIN: 0): ");
				lRate = sc.nextDouble();
				break;
			}
			catch(Exception e) {
				System.out.println("There was an error with an input!");
				e.printStackTrace();
				System.exit(1);
			}
		}
		ArrayList<DObj> trainingData = Parser.parse(1, c_type);
		int [] dims = Parser.getSize(c_type);
		Perceptron [] train = new Perceptron[arrsize];
		for(int i = 0; i < arrsize; i++) {
			train[i] = new Perceptron(dims, lRate);
		}
		Perceptron.dpUsed(trainingData, usage_percent);
		train = Perceptron.train(train);
		//determine if retraining is required based on accuracy of model when used on validation data
		double val_acc = Perceptron.classify(train, Parser.parse(2, c_type));
		System.out.printf("Validation Accuracy: %2.2f%s\n",val_acc, "%");
//		while(val_acc < 85.0) {
//			train = Perceptron.train(train);
//			val_acc = Perceptron.classify(train, Parser.parse(2, c_type));
//			System.out.printf("Validation Accuracy: %2.2f%s\n",val_acc, "%");
//		}
		//model used on test data
		ArrayList<DObj> test = Parser.parse(3, c_type);
		System.out.printf("Test Accuracy: %2.2f%s\n", Perceptron.classify(train, test), "%");
		
		sc.close();
	}
}
