package dit954lab.world.vehicles;

import java.awt.Color;

import util.Container;
import util.Unit;
import dit954lab.Placable;
import dit954lab.world.StandardFlak;
import dit954lab.world.vehicles.addons.BooleanFlak;
import dit954lab.world.vehicles.addons.Flak;
import vector2d.Coord;
import vector2d.Vector2d;

public class CarTransporter<C extends Placable & Car>
	extends StandardCar
	implements StandardFlak.WithContainer<C,Unit>,
	           StandardFlak.WithMovable<Unit>
{
	protected Flak<Unit> flak;
    protected Container<C> container;
    
	public CarTransporter(Coord position,double angle){
		super(position,angle,2,400,Color.blue,"Biltransport");
		this.flak = new BooleanFlak();
		this.container = new Container.Array<>(2);
	}

	@Override
	public void gas(double amount){
		if(isFlakClosed()) super.gas(amount);
	}

	@Override public Flak<Unit> getFlak(){return flak;}
	@Override public Container<C> getContainer(){return container;}

	@Override
	public boolean place(Vector2d<Double> pos){
		return false;
	}
}
