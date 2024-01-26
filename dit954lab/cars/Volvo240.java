package dit954lab.cars;

import java.awt.*;

import vector2d.Coord;

public class Volvo240 extends StandardCar{
    public final static double trimFactor = 1.25;

    public Volvo240(Coord position,double angle){
        super(position,angle,4,100,Color.black,"Volvo240");
    }

    @Override
    public double speedFactor(){
        return this.getEnginePower() * 0.01 * trimFactor;
    }

    @Override
    protected void incrementSpeed(double amount){
        this.velocity.setMagnitude(Math.min(this.getSpeedFromDelta(amount),getEnginePower()));
    }

    @Override
    protected void decrementSpeed(double amount){
        this.velocity.setMagnitude(Math.max(this.getSpeedFromDelta(-amount),0));
    }
}
