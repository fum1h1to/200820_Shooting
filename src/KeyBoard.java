import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class KeyBoard extends KeyAdapter{
    private static ArrayList<Integer> pressedButtons = new ArrayList<Integer>();//配列のインデックス番号と実際の番号が同じ整数型だからそれと区別するためにIntegerがあるのかな

    public static boolean isKeyPressed(int keyCode){
        return pressedButtons.contains(keyCode);
    }

    @Override
    public void keyPressed(KeyEvent e){
        super.keyPressed(e);
        //arraylist.containsは引数の値が配列の中に含まれるか調べるメソッド
        if(!pressedButtons.contains(e.getKeyCode())) pressedButtons.add(e.getKeyCode());
    }
    @Override
    public void keyReleased(KeyEvent e){
        super.keyReleased(e);
        pressedButtons.remove((Integer)e.getKeyCode());
    }
}