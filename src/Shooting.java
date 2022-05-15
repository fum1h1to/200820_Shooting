import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class Shooting{
    public static ShootingFrame shootingFrame;
    public static boolean loop; 
    public static Random random = new Random();

    public static EnumShootingScreen screen;
    public static void main(String[] args){
        shootingFrame = new ShootingFrame();//自分で作ったJFrameの箱をインスタンス化している。
        loop = true;

        Graphics gra = shootingFrame.panel.image.getGraphics();//createGraphics()のほうが便利らしい
        //graはshootingFrame.panel.imageと同期してるとしてよい。
        long startTime;
        long fpsTime = 0;
        int fps = 30;
        int FPS = 30;
        int FPSCount = 0;

        ArrayList<Star> stars = new ArrayList<Star>();
        for(int i = 0; i < 40; i++){
            stars.add(new Star(random.nextInt(500),random.nextInt(500)));
        }

        screen = EnumShootingScreen.START;

        while(loop){
            if((System.currentTimeMillis() - fpsTime) >= 1000){
                fpsTime = System.currentTimeMillis();
                FPS = FPSCount;
                FPSCount = 0;
            }
            FPSCount++;
            startTime = System.currentTimeMillis();

            gra.setColor(Color.BLACK);
            gra.fillRect(0, 0, 500, 500);
            drawStar(gra, stars);

            switch(screen){
                case START:
                    startScreen(gra);
                    break;
                case GAME:
                    gameScreen(gra);
                    break;
                case GAMEOVER:
                    gameoverScreen(gra);
                    break;
            }

            gra.setColor(Color.WHITE);
            gra.setFont(new Font("SansSerif", Font.PLAIN, 15));
            gra.drawString(FPS + "FPS", 0, 460);

            shootingFrame.panel.draw();

            try{
                long runTime = System.currentTimeMillis() - startTime;
                if(runTime < (1000 / fps)){
                    Thread.sleep((1000 / fps) - runTime);
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    public static Player player;
    public static ArrayList<Enemy> enemy;
    public static int score = 0;
    public static int level = 0;
    public static long levelTimer = 0;

    private static void drawStar(Graphics gra, ArrayList<Star> star){
        for(int i = 0; i < 4; i++){
            if(random.nextInt(50) == 1) star.add(new Star(random.nextInt(500),-10));
        }
        for(int i = 0; i < star.size(); i++){
            Star st = star.get(i);
            st.update(screen);
            st.draw(gra);
            if(st.y > 500){
                star.remove(i);
            }
        }
    }

    private static void startScreen(Graphics gra){
        gra.setColor(Color.WHITE);
        Font font = new Font("SansSerif", Font.PLAIN, 40);
        gra.setFont(font);
        FontMetrics metrics = gra.getFontMetrics(font);
        //FontMetrics はfontの長さとかを調べるときに使える。
        gra.drawString("Simple Shooting Game", 250 - (metrics.stringWidth("Simple ShootingGame") /2 ), 100);
        font = new Font("SansSerif", Font.PLAIN, 20);
        gra.setFont(font);
        metrics = gra.getFontMetrics(font);
        gra.drawString("Press SPACE to Start", 250 - (metrics.stringWidth("Press SPACE to Start") /2 ), 400);
    
        if(KeyBoard.isKeyPressed(KeyEvent.VK_SPACE)){
            screen = EnumShootingScreen.GAME;
            player = new Player(250, 400);
            enemy = new ArrayList<Enemy>();
            level = 0;
            score = 0;
            levelTimer = System.currentTimeMillis();
        }
    }

    private static void gameScreen(Graphics gra){
        if(System.currentTimeMillis() - levelTimer > 10 * 1000){//levelTimerは最初はゼロ
            levelTimer = System.currentTimeMillis();
            level++;
            score = score + (level==0?0:1) * 100;
        }
        player.update();
        player.draw(gra);

        if(random.nextInt(Shooting.level<10?50 - Shooting.level * 2:30) == 1) enemy.add(new Enemy(random.nextInt(460), -30));
        for(int i = 0; i < enemy.size(); i++){
            Enemy enem = enemy.get(i);
            enem.update(screen);
            enem.draw(gra);
            if(enem.y > 500 || enem.colision(player.Pbullets)){
                enemy.remove(i);
                i--;
            }
        }
        if(player.colision(enemy)){
            screen = EnumShootingScreen.GAMEOVER;
        }
        gra.setColor(Color.WHITE);
        gra.setFont(new Font("SansSerif", Font.PLAIN, 20));
        gra.drawString("SCORE:" + score , 350, 400);
        gra.drawString("LEVEL :" + level, 353, 425);
    }

    private static void gameoverScreen(Graphics gra){
        gra.setColor(Color.RED);
        Font font = new Font("SansSerif", Font.PLAIN, 50);
        gra.setFont(font);
        FontMetrics metrics = gra.getFontMetrics(font);
        //FontMetrics はfontの長さとかを調べるときに使える。
        gra.drawString("GameOver", 250 - (metrics.stringWidth("GameOver") /2 ), 100);
        gra.setColor(Color.WHITE);
        font = new Font("SansSerif", Font.PLAIN, 20);
        gra.setFont(font);
        metrics = gra.getFontMetrics(font);
        gra.drawString("SCORE:" + score, 250 - (metrics.stringWidth("SCORE" + score) /2 ), 350);
        gra.drawString("Press ESC to Title", 250 - (metrics.stringWidth("Press ESC to Title") /2 ), 400);

        if(KeyBoard.isKeyPressed(KeyEvent.VK_ESCAPE)){
            screen = EnumShootingScreen.START;
        }
    }
}