import java.awt.Color;

import dit954lab.world.vehicles.Car;
import dit954lab.world.vehicles.Saab95;
import dit954lab.world.vehicles.Volvo240;
import org.junit.Test;
import vector2d.Coord;

import static org.junit.Assert.*;

public class Cars {
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

		assertEquals(spd1,spd2,0.0);
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
		assertEquals(spd3,spd2,0.0);
		assertEquals(spd4,spd3,0.0);
		assertTrue(spd5 <= spd4);
		assertEquals(0.0,spd6,0.0);
		assertEquals(0.0,spd7,0.0);
	}

	@Test
	public void saabTurbo() {
		Saab95 car = new Saab95(new Coord(5.0,3.0),0);

		car.startEngine();
		car.setTurboOn();
		assertTrue(car.isTurboOn());
		car.gas(1.0);
		car.move();
		double spd1 = car.getCurrentSpeed();
		car.stopEngine();

		car.startEngine();
		car.setTurboOff();
		assertFalse(car.isTurboOn());
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
		assertEquals(car.getCurrentSpeed(),car.getEnginePower(),0.0);
	}


	@Test
	public void stopMoving() {
		Volvo240 car = new Volvo240(new Coord(5.0,3.0),0);

		car.startEngine();
		car.gas(1.0);
		car.move();
		assertTrue(car.isMoving());
		car.stopEngine();
		assertFalse(car.isMoving());
	}
}
