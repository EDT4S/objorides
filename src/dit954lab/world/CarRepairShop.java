package dit954lab.world;

import dit954lab.Placable;
import dit954lab.world.vehicles.Car;
import dit954lab.world.vehicles.addons.BooleanFlak;
import dit954lab.world.vehicles.addons.Flak;
import util.Container;
import util.Unit;
import vector2d.Coord;
import vector2d.Vector2d;

public class CarRepairShop<C extends Car & Placable>
	implements StandardFlak.WithContainer<C,Unit>,
	           Flak.Wrapped<Unit>
{
	protected Container<C> container;
	protected Flak<Unit> flak;
	protected Vector2d<Double> position;

	public CarRepairShop(int size,Vector2d<Double> position){
		this.container = new Container.Array<>(size);
		this.flak= new BooleanFlak();
		this.position = position;
	}

	@Override public Container<C> getContainer(){return container;}
	@Override public Vector2d<Double> getPosition(){return position;}
	@Override public Vector2d<Double> getVelocity(){return new Coord(0,0);}
	@Override public Flak<Unit> getFlak(){return flak;}
}
