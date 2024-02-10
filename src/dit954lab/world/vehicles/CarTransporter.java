package dit954lab.world.vehicles;

import java.awt.Color;

import dit954lab.Movable;
import util.Container;
import util.Unit;
import dit954lab.Placable;
import dit954lab.world.StandardFlak;
import dit954lab.world.StandardFlak.*;
import dit954lab.world.vehicles.addons.BooleanFlak;
import dit954lab.world.vehicles.addons.Flak;
import vector2d.Coord;

public class CarTransporter<C extends Placable & Car>
	extends FlakStandardCar<Unit>
	implements Flak.Has<Unit>,
	           Container.Has<C>
{
	protected Addon<C> addon = new Addon<>(
		this,
		new BooleanFlak(),
		new Container.Array<>(3)
	);
	public CarTransporter(Coord position,double angle){super(position,angle,2,400,Color.blue,"Biltransport");}
	@Override public Flak<Unit> getFlak(){return addon;}
	@Override public Container<C> getContainer(){return addon;}

	public record Addon<C extends Placable & Car>(Movable getMovable,Flak<Unit> getFlak,Container<C> getContainer)
		implements StandardFlak.WithContainer<C,Unit>,
			       StandardFlak.WithMovable<Unit>,
			       Movable.Wrapped
	{}
}
