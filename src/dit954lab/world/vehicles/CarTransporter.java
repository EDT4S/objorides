package dit954lab.world.vehicles;

import java.awt.Color;

import util.Container;
import util.Unit;
import dit954lab.Placable;
import dit954lab.world.vehicles.addons.BooleanFlak;
import dit954lab.world.vehicles.addons.Flak;
import vector2d.Coord;
import vector2d.Vector2d;
import vector2d.View;

public class CarTransporter<C extends Placable & Car> extends StandardCar implements Flak.Wrapped<Unit>,Container.Wrapped<C>{
	public static final double MINIMAL_DISTANCE = 400.0;
	
	protected Flak<Unit> flak;
    protected Container<C> container;
    
	public CarTransporter(Coord position,double angle){
		super(position,angle,2,400,Color.blue,"Biltransport");
		this.flak = new BooleanFlak();
		this.container = new Container.Stack<>(new java.util.Stack<>());
	}

	@Override
	public void gas(double amount){
		if(isFlakClosed()) super.gas(amount);
	}

	@Override
	public Flak<Unit> getFlak(){return flak;}

	@Override
	public boolean openFlak(Unit unit){
		if(isMoving()) return false;
		return Flak.Wrapped.super.openFlak(unit);
	}

	@Override
	public Container<C> getContainer(){
		return container;
	}
	
	@Override
	public boolean add(C car){
		if(!isFlakClosed()
		|| this.getPosition().distance(car.getPosition()) > MINIMAL_DISTANCE //Outside of the pickup distance
		|| contains(car) //Have already picked up the car
		|| !car.place(new View<>(this.getPosition()))) //Cannot place the car on the transporter
			return false;
		return Container.Wrapped.super.add(car);
	}

	@Override
	public C remove(){
		if(!isFlakClosed())
			return null;
		C car = Container.Wrapped.super.remove();
		if(car != null) car.place(new Coord(this.getPosition()));
		return car;
	}
	
	public boolean place(Vector2d<Double> pos){
		return false;
	}
}