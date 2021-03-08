import java.awt.*;

public interface IToken {
    ITile getTile();

    void setTile(ITile tile);

    Point getLocation();

    void setColor(Color color);

    Color getColor();

    float getScale();

    void setScale(float scale);
}
