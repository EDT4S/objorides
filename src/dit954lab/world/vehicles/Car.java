package dit954lab.world.vehicles;

import java.awt.*;

public interface Car extends Vehicle{
	/**
	 * Gets the current number of doors on the car.
	 */
	int getNrDoors();

	/**
	 * Gets the current color of the car.
	 */
	Color getColor();

	/**
	 * Sets the current color of the car.
	 */
	void setColor(Color clr);
}
