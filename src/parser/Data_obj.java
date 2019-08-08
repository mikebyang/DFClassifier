package parser;

import java.util.Arrays;
import java.util.Random;
import java.util.ArrayList;

public class Data_obj {
	//image from data file
	private String [] image;
	//for the face -> 0 - not a face, 1 - is a face
	//for numbers -> will be the numerical value of the image
	private int label;
//	private int Cells;
	private int width;
	private int height;
	
	public Data_obj(String [] image) {
		this.image = image;
		this.width = image[0].length();
		this.height = image.length;
	}
	
//	public Data_obj(int label, String [] image, int Cells) {
//		this.label = label;
//		this.image = image;
//		this.Cells = Cells;
//	}
	
	
	public void addLab(int label) {
		this.label = label;
	}
	public void modHeight(int hmod, int extra, int maxw, int maxh) {
		ArrayList<String> arr =new ArrayList<>(Arrays.asList(this.image));
		String wspace = String.format("%1$"+maxw+"s", " ");
		Random rand = new Random();
		if(extra != 0) {
			if(rand.nextBoolean()) {
				arr.add(0, wspace);
			}
			else {
				arr.ensureCapacity(arr.size()+1);
				arr.add(arr.size(), wspace);
			}
		}
		
		for(int i = 0; i < hmod; i++) {
			arr.add(0, wspace);
			arr.ensureCapacity(arr.size()+1);
			arr.add(arr.size(), wspace);
		}
		this.image = arr.toArray(new String[arr.size()]);
		this.height = maxh;
	}
	public void modWidth(int wmod, int extra, int maxh, int maxw) {
		ArrayList<String> arr =new ArrayList<>(Arrays.asList(this.image));
		String lwspace = String.format("%1$"+wmod+"s", " ");
		String rwspace = String.format("%1$"+wmod+"s", " ");
		Random rand = new Random();
		if(extra != 0) {
			if(rand.nextBoolean()) {
				lwspace.concat(" ");
			}
			else {
				rwspace.concat(" ");
			}
		}
		
		for(int i = 0; i < maxh; i++) {
			arr.set(i, lwspace + arr.get(i) + rwspace);
		}
		this.image = arr.toArray(new String[arr.size()]);
		this.width = maxw;
	}
	public String [] getData() {
		return this.image;
	}
	public int getLabel() {
		return this.label;
	}
//	public int getCells() {
//		return this.Cells;
//	}
	public int getWidth() {
		return this.width;
	}
	public int getHeight() {
		return this.height;
	}
}
