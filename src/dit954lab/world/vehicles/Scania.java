package dit954lab.world.vehicles;

import dit954lab.Movable;
import dit954lab.world.StandardFlak;
import dit954lab.world.StandardFlak.FlakStandardCar;
import dit954lab.world.vehicles.addons.Flak;
import dit954lab.world.vehicles.addons.GradualFlak;
import vector2d.Coord;

import java.awt.*;

public class Scania
	extends FlakStandardCar<Double>
	implements Flak.Has<Double>
{
    protected Addon addon = new Addon(this,new GradualFlak(0,70));
	public Scania(Coord position,double angle){super(position,angle,2,200,Color.gray,"Scania");}
	@Override public Flak<Double> getFlak(){return addon;}

	public record Addon(Movable getMovable,Flak<Double> getFlak)
		implements StandardFlak.WithMovable<Double>,
		           Movable.Wrapped
	{}
}
