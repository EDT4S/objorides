package dit954lab;

import vector2d.Vector2d;

public interface Physical {
    /**
     * Gets the current position.
     */
    Vector2d<Double> getPosition();

    /**
     * Gets the current velocity.
     */
    Vector2d<Double> getVelocity();
}
