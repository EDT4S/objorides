package dit954lab;

import vector2d.Vector2d;

public interface Placable extends Physical{
    boolean place(Vector2d<Double> pos);
}
