import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class Enemy {
    public int x;
    public int y;
    public int w = 30;
    public int h = 20;
    public int speedX = 0;
    public int speedY = 4;
    public Random random = new Random();

    public ArrayList<Bullet> Ebullets;
    public int bulletInterval = 4;

    public Enemy(int x, int y){
        this.x = x - w / 2;
        this.y = y - h / 2;
        Ebullets = new ArrayList<Bullet>();
    }

    public void update(EnumShootingScreen scree){
        for(int i = 0; i < Ebullets.size(); i++){
            Bullet bullet = Ebullets.get(i);
            int bspeedX = 0;
            int bspeedY = 8;
            if(scree == EnumShootingScreen.GAME){
                if(KeyBoard.isKeyPressed(KeyEvent.VK_W) ) bspeedY = bspeedY + 2;
                if(KeyBoard.isKeyPressed(KeyEvent.VK_S) ) bspeedY = bspeedY - 1;
                if(KeyBoard.isKeyPressed(KeyEvent.VK_A) ) bspeedX = -2;
                if(KeyBoard.isKeyPressed(KeyEvent.VK_D) ) bspeedX = 2;
            }  
            bullet.y += bspeedY;
            bullet.x -= bspeedX;
            if(bullet.x < 0){
                bullet.x = 500;
            }
            if(bullet.x > 500){
                bullet.x = 0;
            }
            if(bullet.y > 500 || bullet.visual == false){
                Ebullets.remove(i);
                i--;//ちらつき対策。処理されないインデックスをなくす。
                //消されたインデックスの後ろ詰められて処理されないから、iを一つ下げれば詰められたやつを処理できる。
            } 
        }
        this.y += speedY;
        this.x -= speedX;
        speedX = 0;
        speedY = 4;
        if(scree == EnumShootingScreen.GAME){
            if(KeyBoard.isKeyPressed(KeyEvent.VK_W) ) speedY = speedY + 2;
            if(KeyBoard.isKeyPressed(KeyEvent.VK_S) ) speedY = speedY - 1;
            if(KeyBoard.isKeyPressed(KeyEvent.VK_A) ) speedX = -2;
            if(KeyBoard.isKeyPressed(KeyEvent.VK_D) ) speedX = 2;
        }  
        if(this.x < 0){
            this.x = 500;
        }
        if(this.x > 500){
            this.x = 0;
        }
        if(bulletInterval == 0 && random.nextInt(Shooting.level<10?30 - Shooting.level * 2:10) == 1){
            //三項演算子　条件 ？ 条件がtrueの時の処理 : 条件がfalseの時の処理
             Ebullets.add(new Bullet(x, y));
             bulletInterval = 4;
        }
        if(bulletInterval > 0) bulletInterval--;

    }

    public boolean colision(ArrayList<Bullet> Pbullet){
        for(int i = 0; i < Pbullet.size(); i++){
            Bullet pb = Pbullet.get(i);
            if(Math.abs(this.x - pb.x) < (this.w / 2 + pb.w / 2)
            && Math.abs(this.y - pb.y) < (this.h / 2 + pb.h / 2)){
                pb.visual = false;
                Shooting.score += 10;
                return true;
            }
            for(int j = 0; j < this.Ebullets.size(); j++){
                Bullet eb = Ebullets.get(j);
                if(Math.abs(eb.x - pb.x) < (eb.w / 2 + pb.w / 2)
                && Math.abs(eb.y - pb.y) < (eb.h / 2 + pb.h / 2)){
                    eb.visual = false;
                }
            }
        }
        return false;
    }

    public void draw(Graphics gra){
        gra.setColor(Color.RED);
        gra.fillRect(this.x - w / 2, this.y - h / 2, w, 10);
        gra.fillRect(this.x - w / 2 + 10, this.y - h / 2 + 10, 10, 10);
        for(int i = 0; i < Ebullets.size(); i++){
            Bullet bullet = Ebullets.get(i);
            gra.setColor(Color.RED);
            bullet.draw(gra);
        }
    }
}