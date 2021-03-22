import java.awt.*;
import java.util.ArrayList;

public class Map {
    public ITileGrid grid;

    public ArrayList<Token> tokens = new ArrayList<Token>();

    public Map(ITileGrid grid) {
        this.grid = grid;
    }

    public int getWidth() {
        return grid.getWidth();
    }

    public int getHeight() {
        return grid.getHeight();
    }

    public float getScale() {
        return grid.getTileSize();
    }

    public void draw(Graphics2D g)
    {
        float scale = grid.getTileSize();
        grid.draw(g);

        for (var t : tokens) {
            t.draw(g, scale);
        }
    }
}
