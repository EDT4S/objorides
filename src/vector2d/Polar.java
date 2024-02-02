package vector2d;
/**
 * A mutable 2-dimensional vector represented using polar coordinates.
 */
public final class Polar implements Vector2d<Double>{
	public double magnitude;
	public double angle;

	public Polar(double magnitude, double angle){
		this.magnitude = magnitude;
		this.angle = angle;
	}

	public Polar(Vector2d<Double> pos){
		this.assign(pos);
	}

	public static final double normalizeAngle(double v){
		return ((v% (2.0*Math.PI)) + (2.0*Math.PI)) % (2.0*Math.PI);
	}

	public void normalize(){
		if(this.magnitude < 0.0){
			this.magnitude = -this.magnitude;
			this.angle+= Math.PI;
		}
		this.angle = normalizeAngle(this.angle);
	}

	@Override
	public Double getX(){
		return this.magnitude * Math.cos(this.angle);
	}

	@Override
	public Double getY(){
		return this.magnitude * Math.sin(this.angle);
	}

	@Override
	public Double getAngle(){
		return this.angle;
	}

	@Override
	public Double getMagnitude(){
		return this.magnitude;
	}

	@Override
	public void add(Vector2d<Double> other){
		Coord v = new Coord(this);
		v.add(other);
		this.assign(v);
	}

	@Override
	public void scale(Double factor){
		this.magnitude*= factor;
	}

	@Override
	public void oppose(){
		this.magnitude = -this.magnitude;
	}

	@Override
	public Double distance(Vector2d<Double> other){
		return Math.hypot(this.getX() - other.getX(),this.getY() - other.getY());
	}

	@Override
	public void turn(Double angle){
		this.angle+= angle;
	}

	@Override
	public void setX(Double x) {
		this.assign(new Coord(x,this.getY()));
	}

	@Override
	public void setY(Double y) {
		this.assign(new Coord(this.getX(),y));
	}

	@Override
	public void setAngle(Double angle) {
		this.angle = angle;
	}

	@Override
	public void setMagnitude(Double magnitude) {
		this.magnitude = magnitude;
	}

	@Override
	public void assign(Vector2d<Double> v) {
		this.angle     = v.getAngle();
		this.magnitude = v.getMagnitude();
	}
}
