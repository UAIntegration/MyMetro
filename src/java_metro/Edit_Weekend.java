package java_metro;

import java.awt.*;
import static java_metro.FirstPage.menuBar;
import javax.swing.*;

public class Edit_Weekend extends javax.swing.JFrame {
    private static JFrame frame = new JFrame("Редактирование выходных дней");
    public Edit_Weekend() {
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        
        frame.setJMenuBar(menuBar(false, true));
        frame.add(addAPanel());
    }
    public static void show_edit_weekend(){
        frame.setVisible(true);
    }
    public static void hide_edit_weekend(){
        frame.setVisible(false);
    }
    private static JPanel addAPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.add(addALabel("Редактирование выходных дней", 10, 20, 150, 20));
        return panel;
    }
    private static JLabel addALabel(String text, int x, int y, int w, int h) { 
        JLabel label = new JLabel(text);
        label.setBounds(x, y, w, h);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }
}
