package parser;

public class DObj {
	private double [][] image;
	private int label;
	private int width;
	private int height;
	private double tot = 0;
	public DObj(int width, int height, String [] img, int label) {
		this.image = new double[height][width];
		this.width = width;
		this.height = height;
		this.label = label;
		String line = "";
		for(int i = 0; i < this.height; i++) {
			line = img[i];
			for(int j = 0; j < this.width; j++) {
				if(line.charAt(j) == '#') {
					this.image[i][j] += 1.0;
					this.tot += 1.0;
				}
				else if(line.charAt(j) == '+') {
					this.image[i][j] += 0.5;
					this.tot += 0.5;
				}
			}
		}
	}
	
	public double[][] getData(){
		return this.image;
	}
	public int getLabel() {
		return this.label;
	}
	public double getTOT() {
		return this.tot;
	}
}
