import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player {
    public int x;
    public int y;
    public int w = 30;
    public int h = 20;

    public ArrayList<Bullet> Pbullets;
    public int bulletInterval = 4;

    public Player(int x, int y){
        this.x = x - w / 2;
        this.y = y - h / 2;
        Pbullets = new ArrayList<Bullet>();
    }

    public void update(){
        for(int i = 0; i < Pbullets.size(); i++){
            Bullet bullet = Pbullets.get(i);
            bullet.y -= 10;
            if(bullet.y < 0 || bullet.visual == false){
                Pbullets.remove(i);
                i--;//ちらつき対策。処理されないインデックスをなくす。
                //消されたインデックスの後ろ詰められて処理されないから、iを一つ下げれば詰められたやつを処理できる。
            } 
        }

        if(KeyBoard.isKeyPressed(KeyEvent.VK_W) && y > 25) y -= 5;
        if(KeyBoard.isKeyPressed(KeyEvent.VK_S) && y < 455) y += 5;
        if(KeyBoard.isKeyPressed(KeyEvent.VK_A) && x > 15) x -= 5;
        if(KeyBoard.isKeyPressed(KeyEvent.VK_D) && x < 470) x += 5;

        if(KeyBoard.isKeyPressed(KeyEvent.VK_SPACE) && bulletInterval == 0){
            Pbullets.add(new Bullet(this.x, this.y));
            bulletInterval = 4;
        }
        if(bulletInterval > 0) bulletInterval--;
    }

    public boolean colision(ArrayList<Enemy> enemy){
        for(int i = 0; i < enemy.size(); i++){
            Enemy enem = enemy.get(i);
            if(Math.abs(enem.x - this.x) < (enem.w / 2 + this.w / 2)
            && Math.abs(enem.y - this.y) < (enem.h / 2 + this.h / 2)){
                return true;
            }
            for(int j = 0; j < enem.Ebullets.size(); j++){
                Bullet eb = enem.Ebullets.get(j);
                if(Math.abs(eb.x - this.x) < (eb.w / 2 + this.w / 2)
                && Math.abs(eb.y - this.y) < (eb.h / 2 + this.h / 2)){
                    return true;
                }
            }
        }
        return false;
    }

    public void draw(Graphics gra){
        gra.setColor(Color.CYAN);
        gra.fillRect(this.x - w / 2, this.y - h / 2 + 10, w, 10);
        gra.fillRect(this.x - w / 2 + 10, this.y - h / 2, 10, 10);
        for(int i = 0; i < Pbullets.size(); i++){
            Bullet bullet = Pbullets.get(i);
            gra.setColor(Color.YELLOW);
            bullet.draw(gra);
        }
    }
}