package dit954lab.gui;

import java.util.stream.Stream;

/**
 * Usually, the model updates the view, but here, the view fetches the data it needs from the model when necessary.
 * This is the interface for what the view needs from the model.
 */
public interface ModelView {
    Stream<CarData> getCars();
}
