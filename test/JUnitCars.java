package test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import org.junit.jupiter.api.Test;

import dit954lab.cars.Car;
import dit954lab.cars.Saab95;
import dit954lab.cars.Volvo240;
import vector2d.Coord;

class JUnitCars {
	@Test
	public void gasUpperLimit(){
		Car car;
		
		car = new Saab95(new Coord(5.0,3.0),0);
		car.startEngine();
		car.gas(20.0);
		car.move();
		double spd1 = car.getCurrentSpeed();

		car = new Saab95(new Coord(5.0,3.0),0);
		car.startEngine();
		car.gas(1.0);
		car.move();
		double spd2 = car.getCurrentSpeed();

		assertTrue(spd1 > 0.0);
		assertTrue(spd2 > 0.0);
		assertEquals(spd2,spd1,0.0);
	}

	@Test
	public void gasLowerLimit(){
		Car car = new Saab95(new Coord(5.0,3.0),0);

		car.startEngine();
		double spd1 = car.getCurrentSpeed();
		car.gas(-5.0);
		car.move();
		double spd2 = car.getCurrentSpeed();

		assertEquals(spd1,spd2);
	}

	@Test
	public void simpleProperties() {
		Car car = new Saab95(new Coord(5.0,3.0),0);

		assertEquals(car.getNrDoors(),2);

		assertEquals(125.0,car.getEnginePower(),0.0);

		car.setColor(Color.lightGray);
		assertEquals(car.getColor(),Color.lightGray);
		assertEquals(car.getModelName(),"Saab95");
	}

	@Test
	public void brakeUpperLimit1() {
		Car car = new Saab95(new Coord(5.0,3.0),0);

		car.startEngine();
		car.gas(20.0);
		car.move();
		car.brake(1.0);
		double spd1 = car.getCurrentSpeed();
		car.stopEngine();

		car.startEngine();
		car.gas(1.0);
		car.move();
		car.brake(20.0);
		double spd2 = car.getCurrentSpeed();
		car.stopEngine();

		assertEquals(spd1,spd2,0.0);
	}

	@Test
	public void brakeUpperLimit2() {
		Car car = new Saab95(new Coord(5.0,3.0),0);

		car.startEngine();
		car.gas(20.0);
		car.move();
		car.brake(1.0);
		double spd1 = car.getCurrentSpeed();
		car.stopEngine();

		car.startEngine();
		car.gas(1.0);
		car.move();
		car.brake(20.0);
		double spd2 = car.getCurrentSpeed();
		car.stopEngine();

		assertEquals(spd1,spd2,0.0);
	}

	@Test
	public void brakeLowerLimit() {
		Car car = new Saab95(new Coord(5.0,3.0),0);

		car.startEngine();
		car.gas(1.0);
		car.move();
		double spd1 = car.getCurrentSpeed();
		car.brake(0.0);
		double spd2 = car.getCurrentSpeed();
		car.brake(-1.0);

		assertEquals(spd1,spd2,0.0);
	}

	@Test
	public void direction() {
		Car car = new Saab95(new Coord(5.0,3.0),0);

		car.startEngine();

		assertEquals(0.0,car.getVelocity().getAngle(),0.0);
		assertEquals(0.0,car.getPosition().getX(),5.0);
		assertEquals(0.0,car.getPosition().getY(),3.0);

		car.turnRight();
		assertEquals(0.1,car.getVelocity().getAngle(),0.001);

		car.turnLeft();
		assertEquals(0.0,car.getVelocity().getAngle(),0.001);

		car.stopEngine();
	}

	@Test
	public void gasBreak() {
		Car car = new Saab95(new Coord(5.0,3.0),0);

		car.startEngine();
		double spd1 = car.getCurrentSpeed();
		car.gas(0.5);
		car.move();
		double spd2 = car.getCurrentSpeed();
		car.brake(0.0);
		double spd3 = car.getCurrentSpeed();
		car.brake(-1.0);
		double spd4 = car.getCurrentSpeed();
		car.brake(0.5);
		double spd5 = car.getCurrentSpeed();
		car.brake(1.0);
		double spd6 = car.getCurrentSpeed();
		car.brake(1.0);
		double spd7 = car.getCurrentSpeed();

		assertTrue(spd1 > 0.0);
		assertTrue(spd2 > spd1);
		assertEquals(spd3,spd2);
		assertEquals(spd4,spd3);
		assertTrue(spd5 <= spd4);
		assertEquals(0.0,spd6);
		assertEquals(0.0,spd7);
	}

	@Test
	public void saabTurbo() {
		Saab95 car = new Saab95(new Coord(5.0,3.0),0);

		car.startEngine();
		car.setTurboOn();
		car.gas(1.0);
		car.move();
		double spd1 = car.getCurrentSpeed();
		car.stopEngine();

		car.startEngine();
		car.setTurboOff();
		car.gas(1.0);
		car.move();
		double spd2 = car.getCurrentSpeed();
		car.stopEngine();

		assertTrue(spd1 > spd2);
	}

	@Test
	public void speedUpperLimit() {
		Volvo240 car = new Volvo240(new Coord(5.0,3.0),0);

		car.startEngine();
		do{
			double prevSpd = car.getCurrentSpeed();
			car.gas(1.0);
			car.move();
			assertTrue(car.getCurrentSpeed() > prevSpd);
		}while(car.getCurrentSpeed() < car.getEnginePower());
		assertEquals(car.getCurrentSpeed(),car.getEnginePower());
	}
}
