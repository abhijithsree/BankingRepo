package mybean.impl;

public class Animal {

	
	public String animalName;
	
	public String category;
	
	public String weight;
	
	public String eating;
	
	public String type;

	public Animal( String animalname, String category, String weight, String eating, String type) {
		super();
		this.animalName = animalname;
		this.category = category;
		this.weight = weight;
		this.eating = eating;
		this.type = type;
	}

	

	@Override
	public String toString() {
		return "Animal [animalName=" + animalName + ", category=" + category + ", weight=" + weight + ", eating=" + eating
				+ ", type=" + type + "]";
	}
}
