package dit954lab.cars;

import java.awt.Color;

import dit954lab.Movable;

public interface Car extends Movable{
	/**
	 * Gets the current number of doors on the car.
	 */
	int getNrDoors();

	/**
	 * Gets the current engine power of the car.
	 */
	double getEnginePower();

	/**
	 * Gets the current speed of the car.
	 */
	double getCurrentSpeed();

	/**
	 * Gets the current color of the car.
	 */
	Color getColor();

	/**
	 * Sets the current speed of the car.
	 */
	void setColor(Color clr);

	/**
	 * Starts the car by setting the current speed to a fixed amount.
	 * Note: The vehicle will start moving (the speed will be non-zero).
	 */
	void startEngine();

	/**
	 * Stops the car immediately by setting the current speed to 0.
	 */
	void stopEngine();

	/**
	 * The speed factor indicates how much the car accelerates when increasing/decreasing the speed.
	 */
	double speedFactor();

	/**
	 * Increases the speed by a certain amount.
	 * Does nothing if the amount is non-positive.
	 * @param amount A positive amount.
	 */
	void gas(double amount);

	/**
	 * Decreases the speed by a certain amount.
	 * Does nothing if the amount is non-positive.
	 * @param amount A positive amount.
	 */
	void brake(double amount);

	/**
	 * Gets the modelName of the car.
	 */
	String getModelName();
}
