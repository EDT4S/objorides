package dit954lab.world.vehicles.addons;

public class GradualFlak implements Flak<Double>{
    public final double angleMin;
    public final double angleMax;
	public double angle;

	public GradualFlak(double angleMin,double angleMax){
		this.angleMin = angleMin;
		this.angleMax = angleMax;
		this.angle = angleMin;
	}

	@Override
	public boolean isFlakClosed(){
		return this.angle == angleMin;
	}

	@Override
	public boolean isFlakOpen(){
		return this.angle == angleMax;
	}

	@Override
	public boolean closeFlak(Double angle){
		if(angle <= 0.0 || isFlakClosed()) return false;
		this.angle = Math.max(this.angle - angle , angleMin);
		return true;
	}

	@Override
	public boolean openFlak(Double angle){
		if(angle <= 0.0 || isFlakOpen()) return false;
		this.angle = Math.min(this.angle + angle , angleMax);
		return true;
	}

	@Override
	public boolean closeFlak(){
		angle = angleMin;
		return true;
	}

	@Override
	public boolean openFlak(){
		angle = angleMax;
		return true;
	}
}
