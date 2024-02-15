package dit954lab.world.vehicles;

import dit954lab.Movable;
import dit954lab.Placable;
import dit954lab.world.StandardFlak;
import dit954lab.world.StandardFlak.FlakStandardVehicle;
import dit954lab.world.vehicles.addons.BooleanFlak;
import dit954lab.world.vehicles.addons.Flak;
import util.Container;
import util.Unit;
import vector2d.Coord;

public class CarFerry<C extends Placable & Car>
	extends FlakStandardVehicle<Unit>
	implements Flak.Has<Unit>,
	           Container.Has<C>
{
	protected Addon<C> addon = new Addon<>(
		this,
		new BooleanFlak(),
		new Container.Queue<>(new java.util.concurrent.ArrayBlockingQueue<>(5))
	);
	public CarFerry(Coord position,double angle){super(position,angle,400,"Bilfärja");}
	@Override public Flak<Unit> getFlak(){return addon;}
	@Override public Container<C> getContainer(){return addon;}

	public record Addon<C extends Placable & Car>(Movable getMovable,Flak<Unit> getFlak,Container<C> getContainer)
		implements StandardFlak.WithContainer<C,Unit>,
			       StandardFlak.WithMovable<Unit>,
			       Movable.Wrapped
	{}
}