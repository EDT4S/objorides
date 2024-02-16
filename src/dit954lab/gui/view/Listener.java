package dit954lab.gui.view;

/**
 * Events in the view.
 */
public interface Listener {
    void onGas(int gasAmount);
    void onBrake(int gasAmount);
    void onTurboOn();
    void onTurboOff();
    void onLiftBed();
    void onLowerBed();
    void onStart();
    void onStop();
}
