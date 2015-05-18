package java_metro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import static java_metro.FirstPage.menuBar;
import static java_metro.FirstPage.showmain;

public class LoginWindow extends JFrame {
 
/* Для того, чтобы впоследствии обращаться к содержимому текстовых полей, 
    рекомендуется сделать их членами класса окна */
    final private JTextField loginField;
    final private JPasswordField passwordField;
    final private static JFrame frame = new JFrame("Вход в систему");;
    LoginWindow(){
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Настраиваем первую горизонтальную панель (для ввода логина)
        Box box1 = Box.createHorizontalBox();
        JLabel loginLabel = new JLabel("Логин:");
        loginField = new JTextField(15);
        box1.add(loginLabel);
        box1.add(Box.createHorizontalStrut(6));
        box1.add(loginField);
        // Настраиваем вторую горизонтальную панель (для ввода пароля)
        Box box2 = Box.createHorizontalBox();
        JLabel passwordLabel = new JLabel("Пароль:");
        passwordField = new JPasswordField(15);
        box2.add(passwordLabel);
        box2.add(Box.createHorizontalStrut(6));
        box2.add(passwordField);
        // Настраиваем третью горизонтальную панель (с кнопками)
        Box box3 = Box.createHorizontalBox();
        JButton ok = new JButton("OK");
        JButton cancel = new JButton("Отмена");
        cancel.addActionListener (new Action_cancel());
        box3.add(Box.createHorizontalGlue());
        box3.add(ok);
        box3.add(Box.createHorizontalStrut(12));
        box3.add(cancel);
        // Уточняем размеры компонентов
        loginLabel.setPreferredSize(passwordLabel.getPreferredSize());
        // Размещаем три горизонтальные панели на одной вертикальной
        Box mainBox = Box.createVerticalBox();
        mainBox.setBorder(new EmptyBorder(12,12,12,12));
        mainBox.add(box1);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(box2);
        mainBox.add(Box.createVerticalStrut(17));
        mainBox.add(box3);
        frame.setJMenuBar(menuBar(false, true));
        frame.setContentPane(mainBox);
        frame.pack();
        frame.setResizable(false);
    }
    public static void show_loginwindow(){
        frame.setVisible(true);
    }
    public static void hide_loginwindow(){
        frame.setVisible(false);
    }
    private static class Action_cancel implements ActionListener {        
        public void actionPerformed (ActionEvent e) {
            hide_loginwindow();
            showmain();
        }
    }
}
