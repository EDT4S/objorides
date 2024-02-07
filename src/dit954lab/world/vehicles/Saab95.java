package dit954lab.world.vehicles;

import java.awt.*;

import vector2d.Coord;

public class Saab95 extends StandardCar{
	protected boolean turboOn;

	public Saab95(Coord position,double angle){
		super(position,angle,2,125,Color.red,"Saab95");
		this.turboOn = false;
	}

	/**
	 * Activates the turbo feature of this car.
	 */
	public void setTurboOn(){
		this.turboOn = true;
	}

	/**
	 * Deactivates the turbo feature of this car.
	 */
	public void setTurboOff(){
		this.turboOn = false;
	}

	public boolean isTurboOn(){
		return this.turboOn;
	}
	
	@Override
	public double speedFactor(){
		double turbo = 1;
		if(this.turboOn) turbo = 1.3;
		return super.speedFactor() * turbo;
	}
}
