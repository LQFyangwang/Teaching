package com.gs.three;

public class Car {

	private String name;
	// 聚合关系：由多个类的对象组合成一个完整的类的对象
	private CarSeat carSeat;
	private CarEngine carEngine;
	private CarWheel carWheel;
	
	public Car() {
		
	}
	
	

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public CarSeat getCarSeat() {
		return carSeat;
	}

	public void setCarSeat(CarSeat carSeat) {
		this.carSeat = carSeat;
	}

	public CarEngine getCarEngine() {
		return carEngine;
	}

	public void setCarEngine(CarEngine carEngine) {
		this.carEngine = carEngine;
	}

	public CarWheel getCarWheel() {
		return carWheel;
	}

	public void setCarWheel(CarWheel carWheel) {
		this.carWheel = carWheel;
	}
	
}
