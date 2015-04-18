package java_metro;

import java.awt.*;
import javax.swing.*;
import static java_metro.FirstPage.*;

public class Page_Kyiv extends javax.swing.JFrame{
    private static JFrame frame = new JFrame("Киев");
    public Page_Kyiv() {
        //JFrame frame = new JFrame("Киев");
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        
        frame.setJMenuBar(menuBar(false, true));
        frame.add(addAPanel());
    }
    public static void show_kyiv(){
        frame.setVisible(true);
    }
    public static void hide_kyiv(){
        frame.setVisible(false);
    }
    private static JPanel addAPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.add(addALabel("Киев", 10, 20, 100, 20));
        return panel;
    }
    private static JLabel addALabel(String text, int x, int y, int w, int h) { 
        JLabel label = new JLabel(text);
        label.setBounds(x, y, w, h);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }
}
