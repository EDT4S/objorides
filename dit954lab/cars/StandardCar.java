package dit954lab.cars;

import java.awt.*;

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
	public final String modelName;

	/**
	 * The current speed of the car.
	 */
	protected double currentSpeed;

	protected StandardCar(int nrDoors,double enginePower,Color color,String modelName){
		this.nrDoors = nrDoors;
		this.enginePower = enginePower;
		this.color = color;
		this.modelName = modelName;
	}

	/**
	 * Gets the current speed of the car.
	 */
	public double getCurrentSpeed(){
		return currentSpeed;
	}

	/**
	 * Gets the current color of the car.
	 */
	public Color getColor(){
		return color;
	}

	/**
	 * Sets the current speed of the car.
	 */
	public void setColor(Color clr){
		color = clr;
	}

    /**
     * Starts the car by setting the current speed to a fixed amount.
     * Note: The vehicle will start moving (the speed will be non-zero).
     */
	public void startEngine(){
		currentSpeed = 0.1;
	}

    /**
     * Stops the car immediately by setting the current speed to 0.
     */
	public void stopEngine(){
		currentSpeed = 0.0;
	}

    /**
     * The speed factor indicates how much the car accelerates when increasing/decreasing the speed.
     */
	public abstract double speedFactor();

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
	 * Increases the speed by a certain amount.
	 * Does nothing if the amount is non-positive.
	 * @param amount A positive amount.
	 */
	public final void gas(double amount){
		if(amount > 0.0) incrementSpeed(amount);
	}

	/**
	 * Decreases the speed by a certain amount.
	 * Does nothing if the amount is non-positive.
	 * @param amount A positive amount.
	 */
	public final void brake(double amount){
		if(amount > 0.0) decrementSpeed(amount);
	}

	/**
	 * Gets the current number of doors on the car.
	 */
	public int getNrDoors(){
		return nrDoors;
	}

	/**
	 * Gets the current engine power of the car.
	 */
	public double getEnginePower(){
		return enginePower;
	}
}
