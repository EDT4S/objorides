package dit954lab.cars;

import java.awt.*;

import vector2d.Coord;
import vector2d.Polar;
import vector2d.Vector2d;

public abstract class StandardCar implements Car{
	public final static double TURN_ANGLE_STEP = 0.1;

	/**
	 * Number of doors on the car.
	 */
	private final int nrDoors;

	/**
	 * Engine power of the car.
	 */
	private final double enginePower;

	/**
	 * Color of the car.
	 */
	private Color color;

	/**
	 * The car model name.
	 */
	private final String modelName;

	/**
	 * The current position of the car.
	 */
	protected Coord position;

	/**
	 * The current speed and direction of the car.
	 */
	protected Polar velocity;

	protected StandardCar(Coord position,double angle,int nrDoors,double enginePower,Color color,String modelName){
		this.position = position;
		this.velocity = new Polar(0.0,angle);
		this.nrDoors = nrDoors;
		this.enginePower = enginePower;
		this.color = color;
		this.modelName = modelName;
	}

	public double getCurrentSpeed(){
		return velocity.getMagnitude();
	}

	public Color getColor(){
		return color;
	}

	public void setColor(Color clr){
		color = clr;
	}

	public void startEngine(){
		this.velocity.setMagnitude(0.1);
	}

	public void stopEngine(){
		this.velocity.setMagnitude(0.0);
	}

	public final void gas(double amount){
		if(amount > 0.0) incrementSpeed(Math.min(amount,1.0));
	}

	public final void brake(double amount){
		if(amount > 0.0) decrementSpeed(Math.min(amount,1.0));
	}

	/**
	 * Increases the speed by a certain amount.
	 */
	protected abstract void incrementSpeed(double amount);

	/**
	 * Decreases the speed by a certain amount.
	 */
	protected abstract void decrementSpeed(double amount);

	/**
	 * The new speed when it is increased by a certain amount using the speed factor of the car.
	 * A helper method intended to be used when implementing incrementSpeed and decrementSpeed.
	 */
	protected final double getSpeedFromDelta(double amount){
		return this.getCurrentSpeed() + this.speedFactor() * amount;
	}

	/**
	 * Gets the current position of the car.
	 */
	public final Vector2d<Double> getPosition(){
		return this.position;
	}

	/**
	 * Gets the current speed and direction of the car.
	 */
	public final Vector2d<Double> getVelocity(){
		return this.velocity;
	}

	public final void move(){
		this.position.add(this.velocity);
	}

	public final void turnLeft(){
		this.velocity.turn(-TURN_ANGLE_STEP);
	}

	public final void turnRight(){
		this.velocity.turn(TURN_ANGLE_STEP);
	}

	public int getNrDoors(){
		return nrDoors;
	}

	public double getEnginePower(){
		return enginePower;
	}

	public String getModelName(){
		return modelName;
	}
}
