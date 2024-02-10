import java.util.Random;

import org.junit.Test;
import vector2d.Coord;
import vector2d.Polar;
import vector2d.Vector2d;
import vector2d.View;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Vector {
	Random rand = new Random();

	@Test
	public void testCoordSimple(){
		double x = rand.nextDouble();
		double y = rand.nextDouble();
		Coord p = new Coord(x,y);

		//Constructor
		assertEquals(p.x,x,0.0);
		assertEquals(p.y,y,0.0);

		//Getters
		assertEquals(p.x,(double)p.getX(),0.0);
		assertEquals(p.y,(double)p.getY(),0.0);

		//Setters
		x = rand.nextDouble();
		y = rand.nextDouble();
		p.setX(x);
		p.setY(y);
		assertEquals(p.x,x,0.0);
		assertEquals(p.y,y,0.0);
	}

	@Test
	public void testPolarSimple(){
		double v = rand.nextDouble();
		double r = rand.nextDouble();
		Polar p = new Polar(r,v);

		//Constructor
		assertEquals(p.angle,v,0.0);
		assertEquals(p.magnitude,r,0.0);

		//Getters
		assertEquals(p.angle,(double)p.getAngle(),0.0);
		assertEquals(p.magnitude,(double)p.getMagnitude(),0.0);

		//normalize
		p.normalize();
		assertEquals(p.angle,v,0.001);
		assertEquals(p.magnitude,r,0.001);

		//Setters
		v = rand.nextDouble();
		r = rand.nextDouble();
		p.setAngle(v);
		p.setMagnitude(r);
		assertEquals(p.angle,(double)v,0.0);
		assertEquals(p.magnitude,(double)r,0.0);
	}

	@Test
	public void testCoordPolarCorrespondence(){
		Polar p1 = new Polar(5.0,0.92729522);
		Coord p2 = new Coord(3.0,4.0);

		//Getters
		assertEquals(p1.getX(),p2.getX(),0.001);
		assertEquals(p1.getY(),p2.getY(),0.001);
		assertEquals(p1.getMagnitude(),p2.getMagnitude(),0.001);
		assertEquals(p1.getAngle(),p2.getAngle(),0.001);

		//distance
		assertTrue(p1.distance(p2) <= 0.001);
		assertTrue(p2.distance(p1) <= 0.001);

		//scale
		p1.scale(2.3);
		p2.scale(2.3);
		assertEquals(p1.getX(),p2.getX(),0.001);
		assertEquals(p1.getY(),p2.getY(),0.001);
		assertEquals(p1.getMagnitude(),p2.getMagnitude(),0.001);
		assertEquals(p1.getAngle(),p2.getAngle(),0.001);

		//oppose
		p1.oppose();
		p2.oppose();
		p1.normalize();
		assertEquals(p1.getX(),p2.getX(),0.001);
		assertEquals(p1.getY(),p2.getY(),0.001);
		assertEquals(p1.getMagnitude(),p2.getMagnitude(),0.001);
		assertEquals(p1.getAngle(),Polar.normalizeAngle(p2.getAngle()),0.001);

		//turn
		p1.turn(0.23);
		p2.turn(0.23);
		p1.normalize();
		assertEquals(p1.getX(),p2.getX(),0.001);
		assertEquals(p1.getY(),p2.getY(),0.001);
		assertEquals(p1.getMagnitude(),p2.getMagnitude(),0.001);
		assertEquals(p1.getAngle(),Polar.normalizeAngle(p2.getAngle()),0.001);

		{//add 1
			Polar p = new Polar(2.7,1.234);
			p1.add(p);
			p2.add(p);
			p1.normalize();
			assertEquals(p1.getX(),p2.getX(),0.001);
			assertEquals(p1.getY(),p2.getY(),0.001);
			assertEquals(p1.getMagnitude(),p2.getMagnitude(),0.001);
			assertEquals(p1.getAngle(),Polar.normalizeAngle(p2.getAngle()),0.001);
		}

		//setAngle
		p1.setAngle(0.1);
		p2.setAngle(0.1);
		assertEquals(p1.getX(),p2.getX(),0.001);
		assertEquals(p1.getY(),p2.getY(),0.001);
		assertEquals(p1.getMagnitude(),p2.getMagnitude(),0.001);
		assertEquals(p1.getAngle(),Polar.normalizeAngle(p2.getAngle()),0.001);

		//setMagnitude
		p1.setMagnitude(5.3);
		p2.setMagnitude(5.3);
		assertEquals(p1.getX(),p2.getX(),0.001);
		assertEquals(p1.getY(),p2.getY(),0.001);
		assertEquals(p1.getMagnitude(),p2.getMagnitude(),0.001);
		assertEquals(p1.getAngle(),Polar.normalizeAngle(p2.getAngle()),0.001);


		//setAngle
		p1.setAngle(0.1);
		p2.setAngle(0.1);
		assertEquals(p1.getX(),p2.getX(),0.001);
		assertEquals(p1.getY(),p2.getY(),0.001);
		assertEquals(p1.getMagnitude(),p2.getMagnitude(),0.001);
		assertEquals(p1.getAngle(),Polar.normalizeAngle(p2.getAngle()),0.001);

		//setX
		p1.setX(80.9);
		p2.setX(80.9);
		p1.normalize();
		assertEquals(p1.getX(),p2.getX(),0.001);
		assertEquals(p1.getY(),p2.getY(),0.001);
		assertEquals(p1.getMagnitude(),p2.getMagnitude(),0.001);
		assertEquals(p1.getAngle(),Polar.normalizeAngle(p2.getAngle()),0.001);

		//setY
		p1.setY(80.9);
		p2.setY(80.9);
		p1.normalize();
		assertEquals(p1.getX(),p2.getX(),0.001);
		assertEquals(p1.getY(),p2.getY(),0.001);
		assertEquals(p1.getMagnitude(),p2.getMagnitude(),0.001);
		assertEquals(p1.getAngle(),Polar.normalizeAngle(p2.getAngle()),0.001);
	}

	public void eq(Vector2d<Double> v1, Vector2d<Double> v2){
		assertEquals(v1.getX(),v2.getX(),0.0);
		assertEquals(v1.getY(),v2.getY(),0.0);
		assertEquals(v1.getAngle(),v2.getAngle(),0.0);
		assertEquals(v1.getMagnitude(),v2.getMagnitude(),0.0);
		assertEquals(v1.distance(v2),0.0,0.0001);
		assertEquals(v2.distance(v1),0.0,0.0001);
	}

	@Test
	public void testView(){
		var v1 = new Coord(25.2,85.5);
		var v2 = new View<>(v1);
		eq(v1,v2);
		v2.setX(500.0);
		eq(v1,v2);
		v2.setY(500.0);
		eq(v1,v2);
		v2.setAngle(0.8);
		eq(v1,v2);
		v2.setMagnitude(500.0);
		eq(v1,v2);
		v2.assign(new Coord(0,0));
		eq(v1,v2);
		v2.add(v1);
		eq(v1,v2);
		v2.scale(4.0);
		eq(v1,v2);
		v2.turn(0.8);
		eq(v1,v2);
		v2.oppose();
		eq(v1,v2);
	}
}
