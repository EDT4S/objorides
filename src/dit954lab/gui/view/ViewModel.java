package dit954lab.gui.view;

import java.util.stream.Stream;

/**
 * Usually, the model updates the view, but here, the view fetches the data it needs from the model when necessary.
 * This is the interface for what the view needs from the model.
 */
public interface ViewModel {
    Stream<Car> getCars();
    Car getRepairShop();

    record Car(String name,int x,int y){}
}
