import java.awt.*;

public class Game {
    private int scale = 50;
    public User[] users;
    public Map map;

    public Game() {
        // Default pool of 4 users
        this.users = new User[]{
                new User("user1"),
                new User("user2"),
                new User("user3"),
                new User("host")
        };

        // Default map of a 10x10 square grid
        this.map = new Map(new SquareTileGrid(10, 10, scale));

        // Default map of a 10x10 square grid
        //this.map = new Map(new HexTileGrid(10, 10, scale));

        var t1 = new Token(1, Color.BLUE);
        map.tokens.add(t1);
        t1.setLocation(map.grid, new Point(100, 100));

        var t2 = new Token(2, Color.orange);
        map.tokens.add(t2);
        t2.setLocation(map.grid, new Point(200, 300));

        var t3 = new Token(1, Color.BLACK);
        map.tokens.add(t3);
        t3.setLocation(map.grid, map.grid.getTile(7, 7));

        map.grid.getTile(4, 2).setColor(Color.CYAN);
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

    public void draw(Graphics2D g) {
        map.draw(g);
    }
}
