import javax.swing.*;
import java.awt.*;

public class Button extends JButton {
    Color darkRed = new Color(237,58,61,255);
    Button(String text){
        super(text);
        setOpaque(true);
        setContentAreaFilled(true);
        setBorderPainted(false);
        setFocusPainted(false);
        setBackground(darkRed);
        setForeground(Color.white);
    }
    Button(){}
}
