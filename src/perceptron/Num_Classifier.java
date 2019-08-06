package perceptron;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import parser.Data_obj;

public class Num_Classifier {
	private static List<Perceptron_node> num_arr = new ArrayList<Perceptron_node>(10);
	
	public static void num_train(List<Data_obj> data_arr, double train_per, double lRate) {
		int train_size = (int) (data_arr.size() * train_per/100);
		Random rand = new Random();
		int rdm_index = -1;
		Data_obj t_node = null;
		Perceptron_node [] temp_arr = num_arr.toArray(new Perceptron_node[10]);
		for(int i = 0; i < train_size; i++) {
			//get random test sample
			rdm_index = rand.nextInt(data_arr.size());
			t_node = data_arr.get(rdm_index);
			data_arr.remove(rdm_index);
			
			//use label as array index (0-9)
			if(temp_arr[t_node.getLabel()] == null) {
				temp_arr[t_node.getLabel()] = new Perceptron_node(t_node.getData());
			}
			else {
				try {
					temp_arr[t_node.getLabel()].mod(t_node.getData(), lRate);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		//convert back to list
		num_arr = new ArrayList<Perceptron_node>(Arrays.asList(temp_arr));
	}
}
