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
