package dit954lab;

import vector2d.Vector2d;

public interface Movable{
	/**
	 * Moves something in its current speed and direction that it is facing.
	 * Usually by updating the position of the object.
	 */
	void move();

	/**
	 * Change direction to the left in some fixed angle.
	 */
	void turnLeft();

	/**
	 * Change direction to the right in some fixed angle.
	 */
	void turnRight();

	/**
	 * Gets the current position.
	 */
	Vector2d<Double> getPosition();

	/**
	 * Gets the current velocity.
	 */
	Vector2d<Double> getVelocity();
}