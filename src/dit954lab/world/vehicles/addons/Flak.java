package dit954lab.world.vehicles.addons;

public interface Flak<U>{
	boolean isFlakClosed();
	boolean isFlakOpen();
	boolean closeFlak(U u);
	boolean openFlak(U u);

	public static interface Wrapped<U> extends Flak<U>{
		Flak<U> getFlak();
		@Override default boolean isFlakClosed(){return getFlak().isFlakClosed();}
		@Override default boolean isFlakOpen(){return getFlak().isFlakOpen();}
		@Override default boolean closeFlak(U u){return getFlak().closeFlak(u);}
		@Override default boolean openFlak(U u){return getFlak().openFlak(u);}
	}
}
