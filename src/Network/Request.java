package Network;

import java.io.Serializable;

public class Request implements Serializable {
    private static final long serialVersionUID = 4L;
    private final int x;
    private final int y;
    private final String player;

    public Request(int x, int y, String player) {
        this.x = x;
        this.y = y;
        this.player = player;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getPlayer() {
        return player;
    }
}

