package dit954lab.world;

import dit954lab.Physical;
import dit954lab.Placable;
import dit954lab.world.vehicles.Car;
import dit954lab.world.vehicles.addons.BooleanFlak;
import dit954lab.world.vehicles.addons.Flak;
import util.Container;
import util.Unit;
import vector2d.Coord;
import vector2d.Vector2d;

public class CarRepairShop<C extends Car & Placable>
	implements Container.Wrapped<C>,
	           Flak.Wrapped<Unit>,
	           Physical
{
	protected Addon<C> addon;
	protected Vector2d<Double> position;

	public CarRepairShop(int size,Vector2d<Double> position){
		this.addon = new Addon<>(
			this,
			new BooleanFlak(),
			new Container.Array<>(size)
		);
		this.position = position;
	}

	@Override public Container<C> getContainer(){return addon;}
	@Override public Vector2d<Double> getPosition(){return position;}
	@Override public Vector2d<Double> getVelocity(){return new Coord(0,0);}
	@Override public Flak<Unit> getFlak(){return addon;}

	public record Addon<C extends Placable & Car>(Physical getPhysical,Flak<Unit> getFlak,Container<C> getContainer)
		implements StandardFlak.WithContainer<C,Unit>,
			       Flak.Wrapped<Unit>,
			       Physical.Wrapped
	{}
}
