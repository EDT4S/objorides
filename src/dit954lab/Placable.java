package dit954lab;

import vector2d.Vector2d;

public interface Placable extends Physical{
    void place(Vector2d<Double> pos);
}
