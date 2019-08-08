package perceptron;

import java.util.ArrayList;
import java.util.List;

public class Perceptron_node {
	private Double[][] arr;
	private Double total = 0.0;
	
	public static Double[][] conv(String [] sarr, double lRate){
		List<Double[]> outer = new ArrayList<Double[]>();
		List<Double> inner = new ArrayList<Double>();
		double rate =  0.5;
		if(lRate == 1) {
			rate = 1;
		}
		for(int i = 0; i < sarr.length; i++) {
			for(int j = 0; j < sarr[i].length(); j++) {
				if(sarr[i].charAt(j) == ' ') {
					inner.add(0.0);
				}
				else if(sarr[i].charAt(j) == '+') {
					inner.add(rate*lRate);
				}
				else if(sarr[i].charAt(j) == '#') {
					inner.add(1.0*lRate);
				}
//				else {
//					inner.add(1.0*lRate);
//				}
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
					this.arr[i][j] += 0.0;
				}
				else if(a[i].charAt(j) == '+') {
					this.arr[i][j] += 0.5*lRate;
				}
				else if(a[i].charAt(j) == '#') {
					this.arr[i][j] += 1.0*lRate;
				}
//				else {
//					this.arr[i][j]+=1.0*lRate;
//				}
			}
		}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.print(this.arr.length);
			System.exit(1);
		}
	}
	
	public Perceptron_node(String[] sarr, double lRate) {
		this.arr = conv(sarr, lRate);
	}
	public static Double setTot(Double [] [] a) {
		Double total = 0.0;
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[i].length; j++) {
				total += a[i][j];
			}
		}
		return total;
	}
	public void adj(Double [][] a, Double lRate) {
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j<a[i].length; j++) {
				this.arr[i][j]-=a[i][j]*lRate;
			}
		}
	}
	public Double[][] getDat(){
		return this.arr;
	}
}
