package perceptron;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

import parser.DObj;

public class Perceptron {
	private double [][] weights;
	private double tot_weight;
	private int height;
	private int width;
	private double lRate;
	
	public Perceptron(int[] size, double lRate) {
		//initialize initial weights to 0
		this.weights = new double[size[1]][size[0]];
		this.height = size[1];
		this.width = size[0];
		this.lRate = lRate;
	}
	//model training functions
	public void addW(double[][] img) {
		for(int i = 0; i < this.height; i++) {
			for(int j = 0; j < this.width; j++) {
				this.weights[i][j] += img[i][j]*this.lRate;
				this.tot_weight += img[i][j]*this.lRate;
			}
		}
	}
	public void decW(double[][] img) {
		for(int i = 0; i < this.height; i++) {
			for(int j = 0; j < this.width; j++) {
				this.weights[i][j] -= img[i][j]*this.lRate;
				this.tot_weight -= img[i][j]*this.lRate;
			}
		}
	}
	//get functions
	public double[][] getdata() {
		return this.weights;
	}
	public double getHits(double[][] img){
		//dot product of image and model for a category
		double hits = 0;
		for(int i = 0; i < this.height; i++) {
			for(int j = 0; j < this.width; j++) {
				hits += this.weights[i][j]*img[i][j];
			}
		}
		return hits;
	}
	public double getTotW() {
		return this.tot_weight;
	}
	
	//guess function
	public static int guess(DObj obj, Perceptron[] train) {
		PriorityQueue<Chance_node> q = new PriorityQueue<Chance_node>();
		for(int i = 0; i < train.length; i++) {
			q.add(new Chance_node(train[i].getHits(obj.getData())/obj.getTOT(), i));
		}
		return q.peek().getLabel();
	}
	//phase 1-3 functions (phase 2,3 use the same function)
	private static ArrayList<DObj> data_used = new ArrayList<DObj> (); //data used for model
	public static void dpUsed(ArrayList<DObj> training, double usage_percent){
		/* @param training - training data
		 * @param usage_percent - percent of training data to be used
		 */
		//randomize the training data selected from the list of data
		Random rand = new Random();
		int train_amount = (int)(usage_percent * training.size());
		int train_index;
		for(int i = 0; i < train_amount; i++) {
			train_index = rand.nextInt(training.size());
			data_used.add(training.get(train_index));
			training.remove(train_index);
		}
	}
	public static Perceptron[] train(Perceptron[] train) {
		/* @param train - array of possible categories
		 * @return trained model for classifying digit or face images
		 */
		DObj[] train_mat = data_used.toArray(new DObj[data_used.size()]);
		//training the model
		for(DObj i: train_mat) {
			for(int j = 0; j < train.length; j++) {
				if(train[j].getTotW() == 0 && j == i.getLabel()) {
					//weights have not been touched yet
					train[j].addW(i.getData());
				}
				else if(train[j].getTotW() == 0) {
					//case when the weight has not been touched and this is not the category data label is referring to
					continue;
				}
				else {
					//check if index is the same as label
					if(i.getLabel() == j) {
						//check if percent match of image with correct category is greater than or equal to 90%
						if((train[j].getHits(i.getData())/i.getTOT()) >= 90.0) {
							continue;
						}
						else {
							train[j].addW(i.getData());
						}
					}
					else {
						//check if percent match of image with incorrect category is greater than or equal to 50%
						if((train[j].getHits(i.getData())/i.getTOT()) >= 70.0) {
							train[j].decW(i.getData());
						}
						else {
							continue;
						}
					}
				}
			}
		}
		return train;
	}
	public static double classify(Perceptron[] train, ArrayList<DObj> unknowns) {
		double accuracy = 0;
		double tot_possible = unknowns.size();
		DObj current_obj = null;
		int guess_check = 0;
		while(!(unknowns.isEmpty())) {
			current_obj = unknowns.remove(0);
			guess_check = guess(current_obj,train);
			if(guess_check == current_obj.getLabel()) {
				accuracy++;
			}
		}
		return (accuracy/tot_possible)*100;
	}
}
