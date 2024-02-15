package dit954lab.world.vehicles;

import vector2d.Coord;

import java.awt.*;

public class Volvo240 extends StandardCar{
	public final static double trimFactor = 1.25;

	public Volvo240(Coord position,double angle){
		super(position,angle,4,100,Color.black,"Volvo240");
	}

	@Override
	public double speedFactor(){
		return super.speedFactor() * trimFactor;
	}
}
