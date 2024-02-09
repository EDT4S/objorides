package dit954lab.world.vehicles;

import vector2d.Coord;
import vector2d.Polar;
import vector2d.Vector2d;

public class StandardVehicle implements Vehicle{
	public final static double TURN_ANGLE_STEP = 0.1;

	/**
	 * Engine power of the car.
	 */
	private final double enginePower;

	/**
	 * The car model name.
	 */
	private final String modelName;

	/**
	 * The current position of the car.
	 */
	protected Vector2d<Double> position;

	/**
	 * The current speed and direction of the car.
	 */
	protected Vector2d<Double> velocity;

	protected StandardVehicle(Coord position,double angle,double enginePower,String modelName){
		this.position = position;
		this.velocity = new Polar(0.0,angle);
		this.enginePower = enginePower;
		this.modelName = modelName;
	}

	public void startEngine(){
		this.velocity.setMagnitude(0.1);
	}

	public void stopEngine(){
		this.velocity.setMagnitude(0.0);
	}

	public void gas(double amount){
		if(amount > 0.0) incrementSpeed(Math.min(amount,1.0));
	}

	public void brake(double amount){
		if(amount > 0.0) decrementSpeed(Math.min(amount,1.0));
	}

	/**
	 * Increases the speed by a certain amount.
	 */
	protected void incrementSpeed(double amount){
		this.velocity.setMagnitude(Math.min(this.getSpeedFromDelta(amount),this.getEnginePower()));
	}

	/**
	 * Decreases the speed by a certain amount.
	 */
	protected void decrementSpeed(double amount){
		this.velocity.setMagnitude(Math.max(this.getSpeedFromDelta(-amount),0.0));
	}

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
	public Vector2d<Double> getPosition(){
		return this.position;
	}

	/**
	 * Gets the current speed and direction of the car.
	 */
	public Vector2d<Double> getVelocity(){
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

	public double getEnginePower(){
		return enginePower;
	}

	public String getModelName(){
		return modelName;
	}
}
