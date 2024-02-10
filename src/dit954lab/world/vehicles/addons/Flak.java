package dit954lab.world.vehicles.addons;

public interface Flak<U>{
	boolean isFlakClosed();
	boolean isFlakOpen();
	boolean closeFlak();
	boolean openFlak();
	boolean closeFlak(U u);
	boolean openFlak(U u);

	interface Has<U>{
		Flak<U> getFlak();
	}

	interface Wrapped<U> extends Flak<U>,Has<U>{
		@Override default boolean isFlakClosed(){return getFlak().isFlakClosed();}
		@Override default boolean isFlakOpen(){return getFlak().isFlakOpen();}
		@Override default boolean closeFlak(){return getFlak().closeFlak();}
		@Override default boolean openFlak(){return getFlak().openFlak();}
		@Override default boolean closeFlak(U u){return getFlak().closeFlak(u);}
		@Override default boolean openFlak(U u){return getFlak().openFlak(u);}
	}
}
