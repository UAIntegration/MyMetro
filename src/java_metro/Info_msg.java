package java_metro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Info_msg extends javax.swing.JFrame {
    public static void MSG_TEXT(String Text)
    {
        final JFrame frame2 = new JFrame("info");
        frame2.setVisible(true);
        frame2.setSize(400,100);
        frame2.setLocationRelativeTo(null);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel(Text);
        JPanel panel = new JPanel();
        frame2.add(panel);
        panel.add(label);
        JButton button = new JButton("ОК");
        button.addActionListener (new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                frame2.setVisible(false);
            }
        });
        panel.add(button);
    }
}
