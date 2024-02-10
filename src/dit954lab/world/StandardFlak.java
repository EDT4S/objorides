package dit954lab.world;

import dit954lab.Movable;
import dit954lab.Physical;
import dit954lab.Placable;
import dit954lab.world.vehicles.StandardCar;
import dit954lab.world.vehicles.StandardVehicle;
import dit954lab.world.vehicles.addons.Flak;
import util.Container;
import vector2d.Coord;
import vector2d.View;

import java.awt.*;

public final class StandardFlak{
	private StandardFlak(){}

	public interface WithContainer<Obj extends Placable,F>
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
			|| this.getPosition().distance(obj.getPosition()) > getMinimalPickupDistance() //Outside the pickup distance
			|| contains(obj) //Have already picked up the object
			|| !canPickup(obj)
			|| !Container.Wrapped.super.add(obj)) //Cannot add the object to the container
				return false;
			//TODO: If two objects calls place on one object, then it is in two containers. Maybe a better system would be that every physical object has a parent physical object that it it based on. The world would also be a physical object. Alternatively, not storing positions on objects at all and have a "location" class that contain all the objects in that location and their positions.
			//Place the object on the container
			obj.place(new View<>(this.getPosition()));
			return true;
		}
	
		default Obj remove(){
			if(!isFlakOpen())
				return null;
			Obj car = Container.Wrapped.super.remove();
			if(car != null) car.place(new Coord(this.getPosition()));
			return car;
		}

		default boolean canPickup(Obj obj){
			//Note: This also prevents the object from picking up itself
			//TODO: This is probably bad design. Should be a temporary solution. Alternatives would for example be objects having different weights and containers having a max weight, or simply that it is only possible to pickup objects lighter than oneself.
			return !(obj instanceof Container.Has<?>);
		}
	}

	public interface WithMovable<F>
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

	public static abstract class FlakStandardVehicle<U> extends StandardVehicle implements Flak.Has<U>{
		protected FlakStandardVehicle(Coord position, double angle, double enginePower, String modelName) {
			super(position, angle, enginePower, modelName);
		}

		@Override public void gas(double amount){if(getFlak().isFlakClosed()) super.gas(amount);}
	}

	public static abstract class FlakStandardCar<U> extends StandardCar implements Flak.Has<U>{
		protected FlakStandardCar(Coord position, double angle, int nrDoors, double enginePower, Color color, String modelName) {
			super(position, angle, nrDoors, enginePower, color, modelName);
		}

		@Override public void gas(double amount){if(getFlak().isFlakClosed()) super.gas(amount);}
	}
}
