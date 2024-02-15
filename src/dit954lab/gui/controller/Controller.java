package dit954lab.gui.controller;

public interface Controller{
	void gas(int gasAmount);
	void brake(int gasAmount);
	void turboOn();
	void turboOff();
	void liftBed();
	void lowerBed();
	void start();
	void stop();
}
