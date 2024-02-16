package dit954lab.gui.controller;

import dit954lab.gui.CarData;
import dit954lab.gui.ModelView;
import dit954lab.gui.view.JFrameView;
import dit954lab.gui.view.View;
import dit954lab.world.vehicles.Car;
import dit954lab.world.vehicles.Saab95;
import dit954lab.world.vehicles.Scania;
import dit954lab.world.vehicles.Volvo240;
import vector2d.Coord;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class DefaultController implements Controller, ModelView {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    View frame;
    // A list of cars, modify if needed
    List<Car> cars = new ArrayList<>(3);
    Volvo240 car1 = new Volvo240(new Coord(0,0),0);
    Saab95 car2 = new Saab95(new Coord(0,100),0);
    Scania car3 = new Scania(new Coord(0,200),0);

    private DefaultController(){
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
    }

    public static void main(String[] args) {
        // Instance of this class
        DefaultController cc = new DefaultController();
        cc.frame = new JFrameView("CarSim 1.0",cc,cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Car car : cars) {
                car.move();
                frame.repaint();
            }
        }
    }

    @Override
    public void gas(int amount){
        double gas = ((double) amount) / 100;
        for (Car car : this.cars) {
            car.gas(gas);
        }
    }

    @Override
    public void brake(int amount){
        double gas = ((double) amount) / 100;
        for (Car car : this.cars) {
            car.brake(gas);
        }
    }

    @Override
    public void turboOn(){
        this.car2.setTurboOn();
        System.out.println("Saab95 turbo set to on");
    }

    @Override
    public void turboOff(){
        this.car2.setTurboOff();
        System.out.println("Saab95 turbo set to off");
    }

    @Override
    public void liftBed(){
        if(this.car3.getFlak().openFlak()){
            System.out.println("Scania flak lifted");
        }
    }

    @Override
    public void lowerBed(){
        if(this.car3.getFlak().closeFlak()){
            System.out.println("Scania flak lowered");
        }
    }

    @Override
    public void start(){
        for (Car car : this.cars) {
            car.stopEngine();
        }
    }

    @Override
    public void stop(){
        for (Car car : this.cars) {
            car.stopEngine();
        }
    }

    @Override
    public Stream<CarData> getCars(){
        return this.cars.stream().map(car -> new CarData(
                car.getModelName(),
                (int) Math.round(car.getPosition().getX()),
                (int) Math.round(car.getPosition().getY())
        ));
    }
}
