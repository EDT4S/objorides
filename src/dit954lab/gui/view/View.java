package dit954lab.gui.view;

import dit954lab.gui.CarData;

import java.util.stream.Stream;

public interface View{
	void repaint();

	//TODO: Probably part of the model.
	Stream<CarData> getCars();
}
