package java_metro;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static java_metro.Page_Dnepr.*;
import static java_metro.Page_Kyiv.*;
import static java_metro.Page_Kharkov.*;
import static java_metro.Edit_Weekend.*;
import static java_metro.Edit_Permissions.*;
import static java_metro.Edit_Timetable.*;
import static java_metro.LoginWindow.*;

public class FirstPage extends javax.swing.JFrame{
private final static JFrame frame = new JFrame("Метро");
    public FirstPage() {
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(addAPanel());

        frame.setJMenuBar(menuBar(false, false));
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    /*public static void main(String args[]) {
        System.out.println("code run");
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FirstPage();
                    new Page_Dnepr();
                    new Page_Kyiv();
                    new Page_Kharkov();
                } catch (Exception ex) {
                    Logger.getLogger(FirstPage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }*/
    
    public static JMenuBar menuBar(Boolean ishide, Boolean isBack) {
        JMenuBar menuBar = new JMenuBar();
        
        Font font = new Font("Verdana", Font.PLAIN, 11);
        JMenu fileMenu = new JMenu("Меню");
        fileMenu.setFont(font);
        
        if(isBack) {
            JMenuItem backMenu = new JMenuItem("Назад");
            backMenu.setFont(font);
            fileMenu.add(backMenu);
            backMenu.addActionListener(new ActionListener() {      
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(true);
                hide_dnepr();
                hide_kyiv();
                hide_kharkov();
                hide_edit_weekend();
                hide_edit_permissions();
                hide_edit_timetable();
                hide_loginwindow();
            }
            });
        }
        
        JMenuItem newMenu = new JMenuItem("Авторизация");
        newMenu.setFont(font);
        newMenu.addActionListener (new Login_window());
        fileMenu.add(newMenu);
        
        if (ishide == false){
        JMenu AdminMenu = new JMenu("Администрирование");
        AdminMenu.setFont(font);
        fileMenu.add(AdminMenu);
        
        JMenuItem AdminUnwork = new JMenuItem("Редактирование выходных дней");
        AdminUnwork.setFont(font);
        AdminUnwork.addActionListener (new Settings_weekend());
        AdminMenu.add(AdminUnwork);
        
        JMenuItem AdminAccess = new JMenuItem("Редактирование прав доступа");
        AdminAccess.setFont(font);
        AdminAccess.addActionListener (new Settings_permissions());
        AdminMenu.add(AdminAccess);
        
        JMenuItem AdminTimetable = new JMenuItem("Редактирование графика движения");
        AdminTimetable.setFont(font);
        AdminTimetable.addActionListener (new Settings_timetable());
        AdminMenu.add(AdminTimetable);
        }
        
        fileMenu.addSeparator();
        
        JMenuItem ExitMenu = new JMenuItem("Выход");
        ExitMenu.setFont(font);
        fileMenu.add(ExitMenu);
         
        ExitMenu.addActionListener(new ActionListener() {      
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        menuBar.add(fileMenu);
        return menuBar;
    }
    
    private static JPanel addAPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.add(addALabel("Выберите город:",50,0,300,50));
        JButton button1 = new JButton("Днепропетровск");
        button1.setBounds(50, 50, 200, 50);
        button1.addActionListener (new Action_dnepr());
        panel.add(button1);
        JButton button2 = new JButton("Киев");
        button2.setBounds(50, 105, 200, 50);
        button2.addActionListener (new Action_kyiv());
        panel.add(button2);
        JButton button3 = new JButton("Харьков");
        button3.setBounds(50, 160, 200, 50);
        button3.addActionListener (new Action_kharkov());
        panel.add(button3);
        return panel;
    }
    
    private static JLabel addALabel(String text, int x, int y, int w, int h) { 
        JLabel label = new JLabel(text);
        label.setBounds(x, y, w, h);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }
    private static void hidemain(){
        frame.setVisible(false);
    }
    public static void showmain(){
        frame.setVisible(true);
    }
    private static class Action_dnepr implements ActionListener {        
        public void actionPerformed (ActionEvent e) {
            hidemain();
            show_dnepr();
        }
    }
    private static class Action_kyiv implements ActionListener {        
        public void actionPerformed (ActionEvent e) {
            hidemain();
            show_kyiv();
        }
    }
    private static class Action_kharkov implements ActionListener {        
        public void actionPerformed (ActionEvent e) {
            hidemain();
            show_kharkov();
        }
    }
    private static class Settings_weekend implements ActionListener {        
        public void actionPerformed (ActionEvent e) {
            hidemain();
            show_edit_weekend();
        }
    }
    private static class Settings_permissions implements ActionListener {        
        public void actionPerformed (ActionEvent e) {
            hidemain();
            show_edit_permissions();
        }
    }
    private static class Settings_timetable implements ActionListener {        
        public void actionPerformed (ActionEvent e) {
            hidemain();
            show_edit_timetable();
        }
    }
    private static class Login_window implements ActionListener {        
        public void actionPerformed (ActionEvent e) {
            hidemain();
            show_loginwindow();
        }
    }
}
