package com.gs.four;

public class ActorActivity implements Activity, Driver {

	@Override
	public void sing() {
		System.out.println("actor sing...");
	}

	@Override
	public void dance() {
		System.out.println("actor dance...");
	}

	@Override
	public void drive() {
		System.out.println("ÑÝÔ±Ëæ±ã¿ª...");
	}

}
