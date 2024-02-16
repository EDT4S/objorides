package dit954lab.gui;

import dit954lab.gui.view.*;
import dit954lab.world.vehicles.Car;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.Stream;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class Controller {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer;

    private Controller(Model model,View view){
        this.timer = new Timer(delay, new TimerListener(model,view));

        // Start the timer
        this.timer.start();
    }

    public static void main(String[] args) {
        Model model = new Model();
        ViewModel vm = new ViewModel(){
            @Override
            public Stream<ViewModel.Car> getCars(){
                return model.cars.stream().map(car -> new ViewModel.Car(
                        car.getModelName(),
                        (int) Math.round(car.getPosition().getX()),
                        (int) Math.round(car.getPosition().getY())
                ));
            }
        };
        Listener l = new Listener(){
            @Override
            public void onGas(int amount){
                double gas = ((double) amount) / 100;
                for (Car car : model.cars) {
                    car.gas(gas);
                }
            }

            @Override
            public void onBrake(int amount){
                double gas = ((double) amount) / 100;
                for (Car car : model.cars) {
                    car.brake(gas);
                }
            }

            @Override
            public void onTurboOn(){
                model.getTheSaab().setTurboOn();
                System.out.println("Saab95 turbo set to on");
            }

            @Override
            public void onTurboOff(){
                model.getTheSaab().setTurboOff();
                System.out.println("Saab95 turbo set to off");
            }

            @Override
            public void onLiftBed(){
                if(model.getTheScania().getFlak().openFlak()){
                    System.out.println("Scania flak lifted");
                }
            }

            @Override
            public void onLowerBed(){
                if(model.getTheScania().getFlak().closeFlak()){
                    System.out.println("Scania flak lowered");
                }
            }

            @Override
            public void onStart(){
                for (Car car : model.cars) {
                    car.stopEngine();
                }
            }

            @Override
            public void onStop(){
                for (Car car : model.cars) {
                    car.stopEngine();
                }
            }
        };
        new Controller(model,new JFrameView("CarSim 1.0",l,vm));
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private record TimerListener(Model model,View view) implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Car car : model.cars) {
                car.move();
                view.repaint();
            }
        }
    }
}
