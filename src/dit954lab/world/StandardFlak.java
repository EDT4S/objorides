package dit954lab.world;

import dit954lab.Movable;
import dit954lab.Physical;
import dit954lab.Placable;
import dit954lab.world.vehicles.addons.Flak;
import util.Container;
import vector2d.Coord;
import vector2d.View;

public final class StandardFlak{
	private StandardFlak(){}

	public static interface WithContainer<Obj extends Placable & Physical,F>
		extends Physical,
		        Flak<F>,
		        Container.Wrapped<Obj>
	{
		double DEFAULT_MINIMAL_PICKUP_DISTANCE = 400;
		
		default double getMinimalPickupDistance(){
			return DEFAULT_MINIMAL_PICKUP_DISTANCE;
		}
	
		default boolean add(Obj obj){
			if(!isFlakOpen()
			|| this.getPosition().distance(obj.getPosition()) > getMinimalPickupDistance() //Outside of the pickup distance
			|| contains(obj) //Have already picked up the object
			|| !obj.place(new View<>(this.getPosition()))) //Cannot place the object on the container
				return false;
			return Container.Wrapped.super.add(obj);
		}
	
		default Obj remove(){
			if(!isFlakOpen())
				return null;
			Obj car = Container.Wrapped.super.remove();
			if(car != null) car.place(new Coord(this.getPosition()));
			return car;
		}
	}

	public static interface WithMovable<F>
		extends Flak.Wrapped<F>,
		        Movable
	{
		default boolean openFlak(F f){
			if(isMoving()) return false;
			return Flak.Wrapped.super.openFlak(f);
		}

		default boolean openFlak(){
			if(isMoving()) return false;
			return Flak.Wrapped.super.openFlak();
		}
	}
}
