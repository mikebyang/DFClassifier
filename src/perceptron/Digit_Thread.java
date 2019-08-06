package perceptron;

public class Digit_Thread implements Runnable{
	private int classifier;
	public Digit_Thread(int label) {
		this.classifier = label;
	}
	public static Double[] classify(String[] image) {
		Double[][] nver = Perceptron_node.conv(image);
		Double[] ans = new Double[2];
		Thread[] threader = new Thread[10];
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
