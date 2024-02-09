package dit954lab.world.vehicles;

import dit954lab.Placable;
import dit954lab.world.StandardFlak;
import dit954lab.world.vehicles.addons.BooleanFlak;
import dit954lab.world.vehicles.addons.Flak;
import util.Container;
import util.Unit;
import vector2d.Coord;

public class CarFerry<C extends Placable & Car>
	extends StandardVehicle
	implements StandardFlak.WithContainer<C,Unit>,
	           StandardFlak.WithMovable<Unit>
{
	protected Flak<Unit> flak;
    protected Container<C> container;
    
	public CarFerry(Coord position,double angle){
		super(position,angle,400,"Bilfärja");
		this.flak = new BooleanFlak();
		this.container = new Container.Queue<>(new java.util.ArrayDeque<>());
	}

	@Override
	public void gas(double amount){
		if(isFlakClosed()) super.gas(amount);
	}

	@Override public Flak<Unit> getFlak(){return flak;}
	@Override public Container<C> getContainer(){return container;}
}