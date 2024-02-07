package dit954lab.world;

import dit954lab.Physical;
import dit954lab.world.vehicles.Car;
import util.Container;
import vector2d.Coord;
import vector2d.Vector2d;

public class CarRepairShop<C extends Car> implements Container.Wrapped<C>,Physical{
	protected Container<C> container;
	protected Vector2d<Double> position;

	public CarRepairShop(int size,Vector2d<Double> position){
		this.container = new Container.Array<>(size);
		this.position = position;
	}

	@Override
	public Container<C> getContainer(){return container;}

	@Override
	public Vector2d<Double> getPosition(){
		return position;
	}

	@Override
	public Vector2d<Double> getVelocity(){
		return new Coord(0,0);
	}
}
