package perceptron;

import java.util.ArrayList;
import java.util.List;

public class Perceptron_node {
	private Double[][] arr;
	private Double total = 0.0;
	
	public static Double[][] conv(String [] sarr){
		List<Double[]> outer = new ArrayList<Double[]>();
		List<Double> inner = new ArrayList<Double>();
		for(int i = 0; i < sarr.length; i++) {
			for(int j = 0; j < sarr[0].length(); j++) {
				if(sarr[i].charAt(j) == ' ') {
					inner.add(0.0);
				}
				else if(sarr[i].charAt(j) == '+') {
					inner.add(0.5);
				}
				else if(sarr[i].charAt(j) == '#') {
					inner.add(1.0);
				}
			}
			outer.add(inner.toArray(new Double[inner.size()]));
			inner = new ArrayList<Double>();
		}
		
		return outer.toArray(new Double[outer.size()][sarr[0].length()]);
	}
	
	public void mod(String [] a, double lRate) {
		try {
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[i].length(); j++) {
				if(a[i].charAt(j) == ' ') {
//					System.out.println("strh" + a.length);
//					System.out.println("strw" + a[i].length());
//					System.out.println("doubleh" + this.arr.length);
//					System.out.println("doublew" + this.arr[i].length);
					this.arr[i][j] += 0.0;
				}
				else if(a[i].charAt(j) == '+') {
					this.arr[i][j] += 0.5*lRate;
				}
				else if(a[i].charAt(j) == '#') {
					this.arr[i][j] += 1.0*lRate;
				}
			}
		}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.print(this.arr.length);
			System.exit(1);
		}
	}
	
	public Perceptron_node(String[] sarr) {
		this.arr = conv(sarr);
	}
	public void setTot() {
		for(int i = 0; i < this.arr.length; i++) {
			for(int j = 0; j < this.arr[i].length; j++) {
				this.total += this.arr[i][j];
			}
		}
	}
	public Double getTot() {
		return this.total;
	}
}
