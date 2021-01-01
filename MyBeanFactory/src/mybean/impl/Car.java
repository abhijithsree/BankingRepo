package mybean.impl;

public class Car {

	public String carName;
	
	public String carBrand;
	
	public String engine;
	
	public String carType;

	public Car(String carName, String carBrand, String engine, String carType) {
		super();
		this.carName = carName;
		this.carBrand = carBrand;
		this.engine = engine;
		this.carType = carType;
	}

	@Override
	public String toString() {
		return "Car [carName=" + carName + ", carBrand=" + carBrand + ", engine=" + engine + ", carType=" + carType
				+ "]";
	}
}
