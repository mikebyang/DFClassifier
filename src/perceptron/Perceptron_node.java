package perceptron;

public class Perceptron_node implements Comparable<Perceptron_node>{
	private double chance; //chance of being a certain number
	private int label; //number it has a chance of being
	
	public Perceptron_node(double chance, int label) {
		this.chance = chance;
		this.label = label;
	}
	
	public double getChance() {
		return this.chance;
	}
	
	public int getLabel() {
		return this.label;
	}

	/**
	 * @param
	 * @return Negative, zero, or positive if this chance is 
	 * less than, equal to, or greater than supplied chance
	 */
	
	@Override
	public int compareTo(Perceptron_node o) {
		double sort = this.chance - o.chance;
		if(sort > 0) {
			return 1;
		}
		else if(sort < 0) {
			return -1;
		}
		else {
			return 0;
		}
	}
	
}
