package dit954lab.world.vehicles;

import java.awt.Color;

import dit954lab.world.vehicles.addons.Flak;
import dit954lab.world.vehicles.addons.GradualFlak;
import vector2d.Coord;

public class Scania extends StandardCar implements Flak.Wrapped<Double>{
    protected Flak<Double> flak;
    
	public Scania(Coord position,double angle){
		super(position,angle,2,200,Color.black,"Scania");
		this.flak = new GradualFlak(0,70);
	}

	@Override
	public void gas(double amount){
		if(isFlakClosed()) super.gas(amount);
	}

	@Override
	public Flak<Double> getFlak(){return flak;}

	@Override
	public boolean openFlak(Double angle){
		if(isMoving()) return false;
		return Flak.Wrapped.super.openFlak(angle);
	}
}