import java.awt.*;
import java.io.Serializable;
import java.util.Random;

public class HexTile extends Tile implements Serializable {
    public HexTile(Point location) {
        super(location);

        Random ran = new Random();
        int r = 255 - ran.nextInt(30);
        int g = 255 - ran.nextInt(30);
        int b = 255 - ran.nextInt(30);

        color = new Color(r, g, b);
    }

    @Override
    public Point getCenterOffset(float scale) {
        float r = scale / 2;
        int s = (int) (r * Math.sqrt(3));
        return new Point(r, s);
    }

    @Override
    public Point getPixelLocation(float scale) {
        int x = (int) (location.x * scale);
        int y = (int) (location.y * scale);

        if ((location.y % 2) == 1)
            x += (int) (scale / 2);

        y *= Math.sqrt(3) / 2;

        return new Point(x, y);
    }

    @Override
    public void draw(Graphics2D g, float scale) {
        Point center = getPixelCenterLocation(scale);
        Point offset = getCenterOffset(scale);
        var c = g.getColor();

        int r = (int) (scale / 2);
        int x = center.x;
        int y = center.y;

        int t = (int) (r / Math.sqrt(3));
        int s = offset.y - t;

        int[] pX = {x, x - r, x - r, x, x + r, x + r};
        int[] pY = {y + s, y + t, y - t, y - s, y - t, y + t};

        // Creates a new polygon object to be drawn
        Polygon p = new Polygon(pX, pY, 6);

        // Draw fill color
        g.setColor(color);
        g.fillPolygon(p);

        // Draw outline
        g.setColor(Color.BLACK);
        g.drawPolygon(p);

        g.setColor(c);
    }
}
