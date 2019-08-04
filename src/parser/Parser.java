package parser;

public class Parser {
	public static Data_obj[] data; //object with file data
	public static String[] image; //image of given file
	public static int label; //label for the image
	
	public static String num_path = "/data/digitdata/";
	public static String face_path = "/data/facedata/";
	
	public static void Parse(String num_file, String face_file) {
		
	}
	
	public Data_obj[] getData() {
		return data;
	}
}
