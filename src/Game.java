import java.awt.*;

public class Game {
    public User[] users;
    public Map map;

    public Game() {
        // Default pool of 4 users
        this.users = new User[] {
                new User("user1"),
                new User("user2"),
                new User("user3"),
                new User("host")
        };

        // Default map of a 10x10 square grid
        this.map = new Map(new SquareTileGrid(10,10, 50));
    }

    public Game(User[] users, Map map) {
        this.users = users;
        this.map = map;
    }

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public void draw(Graphics2D g)
    {
        map.draw(g);
    }
}
