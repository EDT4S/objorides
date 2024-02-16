package dit954lab.gui;

import dit954lab.world.CarRepairShop;
import dit954lab.world.vehicles.Car;
import dit954lab.world.vehicles.Saab95;
import dit954lab.world.vehicles.Scania;
import dit954lab.world.vehicles.Volvo240;
import vector2d.Coord;

import java.util.ArrayList;
import java.util.List;

public final class Model {
    // A list of cars, modify if needed
    public List<Car> cars = new ArrayList<>(3);
    private Volvo240 car1 = new Volvo240(new Coord(0,0),0);
    private Saab95 car2 = new Saab95(new Coord(0,100),0);
    private Scania car3 = new Scania(new Coord(0,200),0);
    private CarRepairShop<Volvo240> volvoRepairShop = new CarRepairShop<>(2,new Coord(400,0));

    public Model(){
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
        volvoRepairShop.getFlak().openFlak();
    }

    public Volvo240 getTheVolvo(){return car1;}
    public Saab95 getTheSaab(){return car2;}
    public Scania getTheScania(){return car3;}
    public CarRepairShop<Volvo240> getTheVolvoRepairShop(){return volvoRepairShop;}
}
