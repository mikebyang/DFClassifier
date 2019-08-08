package parser;


public class Data_obj {
	//image from data file
	private String [] image;
	//for the face -> 0 - not a face, 1 - is a face
	//for numbers -> will be the numerical value of the image
	private int label;

	private int width;
	private int height;
	
	public Data_obj(String [] image) {
		this.image = image;
		this.width = image[0].length();
		this.height = image.length;
	}
	
	
	public void addLab(int label) {
		this.label = label;
	}

	public String [] getData() {
		return this.image;
	}
	public int getLabel() {
		return this.label;
	}

	public int getWidth() {
		return this.width;
	}
	public int getHeight() {
		return this.height;
	}
}
