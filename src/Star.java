import java.util.Random;
import java.awt.event.KeyEvent;
import java.awt.*;

public class Star {
    public int x;
    public int y;
    public int r;
    public int speedX;
    public int speedY;

    private Random random = new Random();

    public Star(int x, int y){
        this.x = x;
        this.y = y;
        this.r = random.nextInt(2)+1;
        this.speedX = 0;
        this.speedY = this.r + 1;
    }

    public void update(EnumShootingScreen scree){
        y += speedY;
        x += speedX;
        speedX = 0;
        speedY = this.r;
        if(scree == EnumShootingScreen.GAME){
            if(KeyBoard.isKeyPressed(KeyEvent.VK_W) ) speedY = this.r + 1;
            if(KeyBoard.isKeyPressed(KeyEvent.VK_S) ) speedY = this.r - 1;
            if(KeyBoard.isKeyPressed(KeyEvent.VK_A) ) speedX = 3;
            if(KeyBoard.isKeyPressed(KeyEvent.VK_D) ) speedX = -3;
        }  
        if(this.x < 0){
            this.x = 500;
        }
        if(this.x > 500){
            this.x = 0;
        }
    }

    public void draw(Graphics gra){
        gra.setColor(new Color(230, 230, 230, 170));
        gra.fillOval(this.x, this.y, 2 * r,  2 * r);
    }
}