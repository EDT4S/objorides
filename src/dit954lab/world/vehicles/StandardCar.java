package dit954lab.world.vehicles;

import dit954lab.Placable;
import vector2d.Coord;
import vector2d.Vector2d;

import java.awt.*;

public class StandardCar extends StandardVehicle implements Car,Placable{
	/**
	 * Number of doors on the car.
	 */
	private final int nrDoors;

	/**
	 * Color of the car.
	 */
	private Color color;

	protected StandardCar(Coord position,double angle,int nrDoors,double enginePower,Color color,String modelName){
		super(position,angle,enginePower,modelName);
		this.nrDoors = nrDoors;
		this.color = color;
	}

	public Color getColor(){
		return color;
	}

	public void setColor(Color clr){
		color = clr;
	}

	public int getNrDoors(){
		return nrDoors;
	}

	public void place(Vector2d<Double> pos){
		this.position = pos;
	}
}
