import java.awt.*;
import java.io.Serializable;

public class Game implements Serializable {
    private int scale = 50;
    public User[] users;
    public static Map map;

    public Game() {
        // Default pool of 4 users
        this.users = new User[]{
                new User("user1"),
                new User("user2"),
                new User("user3"),
                new User("host")
        };

        // Default map of a 10x10 square grid
        map = new Map(new SquareTileGrid(10, 10, scale));

        // Default map of a 10x10 square grid
        //this.map = new Map(new HexTileGrid(10, 10, scale));
    }

    public Game(User[] users, Map map) {
        this.users = users;
        Game.map = map;
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
        setUsers(game.getUsers());
        map = game.getMap();
        TerraGen.window.gameRenderer.repaint();
    }

    public Map getMap() {
        return map;
    }

    public void draw(Graphics2D g) {
        map.draw(g);
    }
}
