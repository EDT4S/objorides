package vector2d;

/**
 * Operations on a mutable 2-dimensional vector.
 *
 * @param <S> The class of the scalar.
 */
public interface Vector2d<S>{
	/**
	 * @return The horizontal coordinate scalar of the vector.
	 */
	S getX();
	
	/**
	 * @return The vertical coordinate scalar of the vector.
	 */
	S getY();

	/**
	 * @return The angular scalar that indicate the direction the vector point towards (unit: radians).
	 */
	S getAngle();

	/**
	 * @return The manitude scalar of the vector.
	 */
	S getMagnitude();



	/**
	 * Sets the horizontal coordinate scalar of the vector.
	 * @param x The new value
	 */
	void setX(S x);
	
	/**
	 * Sets the vertical coordinate scalar of the vector.
	 * @param y The new value
	 */
	void setY(S y);

	/**
	 * Sets the angular scalar that indicate the direction the vector point towards.
	 * @param x The new value (unit: radians)
	 */
	void setAngle(S angle);

	/**
	 * Sets the magnitude scalar of the vector.
	 * @param x The new value
	 */
	void setMagnitude(S magnitude);



	/**
	 * Copies the values of another vector to this.
	 * @param v The other vector
	 */
	void assign(Vector2d<S> v);

	/**
	 * Add an other vector to this.
	 * @param v The other vector
	 */
	void add(Vector2d<S> other);

	/**
	 * Multiplies this vector by a scalar.
	 * @param factor The scalar factor
	 */
	void scale(S factor);

	/**
	 * Turns this vector by an amount.
	 * @param angle The angle (unit: radians)
	 */
	void turn(S angle);
	
	/**
	 * Turns this vector to the opposite direction.
	 */	
	void oppose();



	/**
	 * The distance between this vector and an other vector.
	 * @param other The other vector
	 * @return The distance
	 */	
	S distance(Vector2d<S> other);
}