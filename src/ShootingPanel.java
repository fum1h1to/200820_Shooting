import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ShootingPanel extends JPanel {
    public BufferedImage image;

    public ShootingPanel(){
        super();
        image = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
    }
    @Override//アノテーションというやつ。ミスを防げるやつ。
    public void paint(Graphics g){//JPanelの親の親とかで定義されているメソッド
        super.paint(g);//suparはなくてもいい
        g.drawImage(image, 0, 0, this);//imageは常に書き換えられている。
        //mainではimageを書き換えてるだけ。
    }//paintがjPanelに書き込んでいる。
    public void draw(){
        repaint();
    }
    /*
        ここら辺のpaint(),repaint()が今回の処理のきもとなるとこかな。
        graphicsクラスを使いたければとりあえずこの処理が必要なのかも。
        BufferImageとかはおそらく描画をまとめるやつ本来ここに記述していいものを他のとこに書くためにやっている事かな
        ↑これに至るまでにいろいろとクラスを変換してる気がする。
        bufferedImageを使う目的はまとめたいから。graphicsの描画もできるから便利だ。
        
        imageにgraphicsを使って書いて、そのimageをまたgraphicsに直している。
    */
}