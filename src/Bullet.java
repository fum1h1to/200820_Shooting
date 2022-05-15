import java.awt.*;

public class Bullet {
    public int x;
    public int y;
    public int w = 3;
    public int h = 3;
    public boolean visual = true;

    public Bullet(int x, int y){
        this.x = x - w / 2;
        this.y = y - h / 2;
    }

    public void draw(Graphics gra){
        if(visual)
        gra.fillRect(this.x - h / 2, this.y - h / 2, w, h);
    }
}