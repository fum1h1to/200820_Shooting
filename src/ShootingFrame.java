import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class ShootingFrame extends JFrame {//自分好みのJFrameを作る
    public ShootingPanel panel;

    public ShootingFrame(){
        panel = new ShootingPanel();

        add(panel);//JFrameに追加

        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosed(WindowEvent e){
                super.windowClosed(e);
                Shooting.loop = true;
            }
        });

        addKeyListener(new KeyBoard());//キーボード入力を可能にする。

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Shooting");
        setSize(500, 500);
        setLocationRelativeTo(null);//画面の真ん中に配置することができる。
        setResizable(false);//ウィンドウの拡大縮小をできなくする
        setVisible(true);
    }
}