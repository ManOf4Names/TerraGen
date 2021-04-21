import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.concurrent.ArrayBlockingQueue;

public class Game implements Serializable {
    private int scale = 50;
    public User[] users;
    //private ArrayBlockingQueue<Map> map;
    private Map map;

    // The currently selected token
    private Token selected = null;

    public Game() {
        // Default pool of 4 users
        this.users = new User[]{
                new User("user1"),
                new User("user2"),
                new User("user3"),
                new User("host")
        };

        // Default map of a 10x10 square grid
        //map = new ArrayBlockingQueue<Map>();
        map = new Map(new SquareTileGrid(10, 10, scale));

        // Default map of a 10x10 square grid
        //this.map = new Map(new HexTileGrid(10, 10, scale));
    }

    public Game(User[] users, Map map) {
        this.users = users;
        this.map = map;
    }

    public int getScale() {
        return scale;
    }

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public void setGame(Game game) {
        //setUsers(game.getUsers());
        map = game.getMap();
        TerraGen.window.gameRenderer.repaint();
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Token addToken(int x, int y){
        return map.addToken(x, y);
    }

    public Token addToken(int x, int y, Token token){
        return map.addToken(x, y, token);
    }

    public Token getClosestToken(Point pos){
        return map.getClosestToken(pos);
    }

    public void MoveToken(Token token, Point pos) {
        var tile = map.grid.getClosestTile(pos, map.getScale());
        token.setLocation(tile.getPixelCenterLocation(map.getScale()));
    }

    public void OnMouseClick(MouseEvent mouseEvent) {
        var origin = map.grid.getTile(0, 0);
        var originPos = origin.getPixelLocation(map.getScale());

        var x = mouseEvent.getX() - originPos.x;
        var y = mouseEvent.getY() - originPos.y;

        var pos = new Point(x, y);

        if (selected == null){
            selected = getClosestToken(pos);

            if (selected.pos.distance(pos) < map.getScale() * 2)
                selected.selected = true;
            else
                selected = null;
        }else {
            MoveToken(selected, pos);
            selected.selected = false;
            selected = null;
        }
    }

    public void draw(Graphics2D g) {
        map.draw(g);
    }
}
