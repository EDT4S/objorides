package dit954lab.cars;

import java.awt.*;

public class Saab95 extends StandardCar{
    protected boolean turboOn;

    public Saab95(){
        super(2,125,Color.red,"Saab95");
        this.turboOn = false;
    }

    /**
     * Activates the turbo feature of this car.
     */
    public void setTurboOn(){
        this.turboOn = true;
    }

    /**
     * Deactivates the turbo feature of this car.
     */
    public void setTurboOff(){
        this.turboOn = false;
    }

    @Override
    public double speedFactor(){
        double turbo = 1;
        if(this.turboOn) turbo = 1.3;
        return this.getEnginePower() * 0.01 * turbo;
    }

    @Override
    protected void incrementSpeed(double amount){
        this.currentSpeed = this.getSpeedFromDelta(amount);
    }

    @Override
    protected void decrementSpeed(double amount){
        this.currentSpeed = this.getSpeedFromDelta(-amount);
    }
}
