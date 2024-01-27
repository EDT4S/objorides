package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

import vector2d.Coord;
import vector2d.Polar;

class JUnitVector{
	Random rand = new Random();

	@Test
	void testCoordSimple(){
		double x = rand.nextDouble();
		double y = rand.nextDouble();
		Coord p = new Coord(x,y);

		//Constructor
		assertEquals(p.x,x);
		assertEquals(p.y,y);

		//Getters
		assertEquals(p.x,p.getX());
		assertEquals(p.y,p.getY());

		//Setters
		x = rand.nextDouble();
		y = rand.nextDouble();
		p.setX(x);
		p.setY(y);
		assertEquals(p.x,x);
		assertEquals(p.y,y);
	}

	@Test
	void testPolarSimple(){
		double v = rand.nextDouble();
		double r = rand.nextDouble();
		Polar p = new Polar(r,v);

		//Constructor
		assertEquals(p.angle,v);
		assertEquals(p.magnitude,r);

		//Getters
		assertEquals(p.angle,p.getAngle());
		assertEquals(p.magnitude,p.getMagnitude());

		//normalize
		p.normalize();
		assertEquals(p.angle,v,0.001);
		assertEquals(p.magnitude,r,0.001);

		//Setters
		v = rand.nextDouble();
		r = rand.nextDouble();
		p.setAngle(v);
		p.setMagnitude(r);
		assertEquals(p.angle,v);
		assertEquals(p.magnitude,r);
	}

	@Test
	void testCoordPolarCorrespondence(){
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
}
