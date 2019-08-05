package parser;

public class Data_obj {
	//image from data file
	private String [] image;
	//for the face -> 0 - not a face, 1 - is a face
	//for numbers -> will be the numerical value of the image
	private int label;
	private int Cells;
	
	public Data_obj(int label, String [] image, int Cells) {
		this.label = label;
		this.image = image;
		this.Cells = Cells;
	}
	
	public String [] getData() {
		return this.image;
	}
	public int getLabel() {
		return this.label;
	}
	public int getCells() {
		return this.Cells;
	}
}
