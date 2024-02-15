package dit954lab.gui.view;

import dit954lab.gui.CarData;

import java.util.stream.Stream;

public interface View{
	void repaint();
	void onGasButton(int gasAmount);
	void onBreakButton(int gasAmount);
	void onTurboOnButton();
	void onTurboOffButton();
	void onLiftBedButton();
	void onLowerBedButton();
	void onStartButton();
	void onStopButton();
	Stream<CarData> getCars();
}
