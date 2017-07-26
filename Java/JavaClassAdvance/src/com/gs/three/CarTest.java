package com.gs.three;

public class CarTest {
	
	// alt + / 快捷键表示代码提示
	public static void main(String[] args) {
		Car car = new Car();
		CarEngine carEngine = new CarEngine();
		carEngine.setTopSpeed(300);
		car.setCarEngine(carEngine);
		CarSeat carSeat = new CarSeat();
		carSeat.setSeatCount(7);
		carSeat.setSeatType("真皮座椅");
		car.setCarSeat(carSeat);
		CarWheel carWheel = new CarWheel();
		car.setCarWheel(carWheel);
		
		System.out.println("Car top speed: " + car.getCarEngine().getTopSpeed());
		System.out.println("Car seat type: " + car.getCarSeat().getSeatType());
	}

}
