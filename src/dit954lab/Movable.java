package dit954lab;

public interface Movable extends Physical{
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
	 * Whether it is moving.
	 * @return True if moving.
	 */
	boolean isMoving();

	interface Wrapped extends Physical.Wrapped,Movable {
		Movable getMovable();
		@Override default Physical getPhysical(){return getMovable();}
		@Override default void move(){getMovable().move();}
		@Override default void turnLeft(){getMovable().turnLeft();}
		@Override default void turnRight(){getMovable().turnRight();}
		@Override default boolean isMoving(){return getMovable().isMoving();}
	}
}
