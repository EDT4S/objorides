package vector2d;

/**
 * A mutable 2-dimensional vector in the standard basis.
 */
public final class Coord implements Vector2d<Double>{
	public double x;
	public double y;

	public Coord(double x, double y){
		this.x = x;
		this.y = y;
	}

	public Coord(Vector2d<Double> pos){
		this.assign(pos);
	}

	@Override
	public Double getX(){
		return this.x;
	}

	@Override
	public Double getY(){
		return this.y;
	}

	@Override
	public Double getAngle(){
		return Math.atan2(this.y,this.x);
	}

	@Override
	public Double getMagnitude(){
		return Math.hypot(this.x,this.y);
	}

	@Override
	public void add(Vector2d<Double> other){
		this.x+= other.getX();
		this.y+= other.getY();
	}

	@Override
	public void scale(Double factor){
		this.x*= factor;
		this.y*= factor;
	}

	@Override
	public void oppose(){
		this.x = -this.x;
		this.y = -this.y;
	}

	@Override
	public Double distance(Vector2d<Double> other){
		return Math.hypot(this.x - other.getX(),this.y - other.getY());
	}

	@Override
	public void turn(Double angle) {
		Polar v = new Polar(this);
		v.turn(angle);
		this.assign(v);
	}

	@Override
	public void setX(Double x){
		this.x = x;
	}

	@Override
	public void setY(Double y){
		this.y = y;
	}

	@Override
	public void setAngle(Double angle){
		this.assign(new Polar(this.getMagnitude(),angle));
	}

	@Override
	public void setMagnitude(Double magnitude){
		this.scale(magnitude / this.getMagnitude());
	}

	@Override
	public void assign(Vector2d<Double> v) {
		this.x = v.getX();
		this.y = v.getY();
	}
}
