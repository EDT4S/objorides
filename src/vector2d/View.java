package vector2d;

public record View<S,V extends Vector2d<S>>(V v) implements Vector2d<S>{
	@Override public S getX(){return v.getX();}
	@Override public S getY(){return v.getY();}
	@Override public S getAngle(){return v.getAngle();}
	@Override public S getMagnitude(){return v.getMagnitude();}
	@Override public void setX(S x){}
	@Override public void setY(S y){}
	@Override public void setAngle(S angle){}
	@Override public void setMagnitude(S magnitude){}
	@Override public void assign(Vector2d<S> v){}
	@Override public void add(Vector2d<S> other){}
	@Override public void scale(S factor){}
	@Override public void turn(S angle){}
	@Override public void oppose(){}
	@Override public S distance(Vector2d<S> other){return v.distance(other);}
}
