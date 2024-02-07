package dit954lab.world.vehicles.addons;

import util.Unit;

public class BooleanFlak implements Flak<Unit>{
	public boolean isOpen = false;

	public boolean isFlakClosed(){return !isOpen;}
	public boolean isFlakOpen(){return isOpen;}
	public boolean closeFlak(Unit u){
		if(!isOpen) return false;
		isOpen = false;
		return true;
	}
	public boolean openFlak(Unit u){
		if(isOpen) return false;
		isOpen = true;
		return true;
	}
}
