package dit954lab.world.vehicles.addons;

import util.Unit;

public class BooleanFlak implements Flak<Unit>{
	public boolean isOpen = false;

	@Override public boolean isFlakClosed(){return !isOpen;}
	@Override public boolean isFlakOpen(){return isOpen;}
	@Override public boolean closeFlak(){
		if(!isOpen) return false;
		isOpen = false;
		return true;
	}
	@Override public boolean openFlak(){
		if(isOpen) return false;
		isOpen = true;
		return true;
	}
	@Override public boolean closeFlak(Unit u){return closeFlak();}
	@Override public boolean openFlak(Unit u){return openFlak();}
}
