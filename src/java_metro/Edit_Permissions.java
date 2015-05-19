package java_metro;

import static java_metro.FirstPage.menuBar;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
 
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Edit_Permissions extends javax.swing.JFrame {
    static int i = 1;
    private static JFrame frame = new JFrame("Редактирование прав доступа");
    
    public Edit_Permissions() {
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        
        frame.setJMenuBar(menuBar(false, true));

        final JTabbedPane tabbedPane = new JTabbedPane();

        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
 
        JPanel searchbox = new JPanel();
        searchbox.setBackground(Color.WHITE);
        //searchbox.setLayout(null);
        searchbox.add(addALabel("Пользователь:", 10, 10, 50, 20));
        searchbox.add(addAField());
        searchbox.add(addAButton("Найти"));
        
        content.add(searchbox, BorderLayout.NORTH);
        
        /*JButton add1 = new JButton("Добавить");
        add1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tabbedPane.addTab("Вкладка " + i++, new JPanel());
            }
        });
        searchbox.add(add1);
 
        JButton remove = new JButton("Удалить");
        remove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int select = tabbedPane.getSelectedIndex();
                if (select >= 0) {
                    tabbedPane.removeTabAt(select);
                }
            }
        });
        searchbox.add(remove);*/
        JPanel test1 = new JPanel();
        test1.setLayout(null);
        JLabel label2 = new JLabel("vjz drkflrf 2");
        test1.add(label2);
        test1.add(new JSeparator(JSeparator.HORIZONTAL));
        test1.add(addALabel("Пользователь1:", 10, 20, 120, 25));
        JCheckBox check1 = new JCheckBox("Администратор");
        check1.setBounds(145, 10, 120, 25);
        check1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if(check1.isSelected()){
                    
                } else {
                    
                }
            }
        });
        test1.add(check1);
        JCheckBox check2 = new JCheckBox("Модератор");
        check2.setBounds(145, 30, 120, 25);
        test1.add(check2);
        JCheckBox check3 = new JCheckBox("Пользователь");
        check3.setBounds(265, 10, 120, 25);
        test1.add(check3);
        JCheckBox check4 = new JCheckBox("Блокирован");
        check4.setBounds(265, 30, 120, 25);
        test1.add(check4);
        
        tabbedPane.addTab("" + i++, test1);
        tabbedPane.addTab("" + i++, new JPanel());
        tabbedPane.addTab("" + i++, new JPanel());
        tabbedPane.addTab("" + i++, new JPanel());
        tabbedPane.addTab("" + i++, new JPanel());
        
        label2.setText("" + tabbedPane.getSelectedIndex());
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPane.setTabPlacement(JTabbedPane.BOTTOM);
        
        content.add(tabbedPane, BorderLayout.CENTER);
        frame.add(content);
        pack();
        setLocationRelativeTo(null);
    }
    public static void show_edit_permissions(){
        frame.setVisible(true);
    }
    public static void hide_edit_permissions(){
        frame.setVisible(false);
    }
    
    private static JLabel addALabel(String text, int x, int y, int w, int h) { 
        JLabel label = new JLabel(text);
        label.setBounds(x, y, w, h);
        label.setPreferredSize(new Dimension(120,25));
        //label.setBorder(BorderFactory.createEtchedBorder());
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }
    private static JTextField addAField() { 
        JTextField curuser = new JTextField();
        curuser.setBorder(BorderFactory.createEtchedBorder());
        curuser.setPreferredSize(new Dimension(140,25));
        return curuser;
    }
    private static JButton addAButton(String text) { 
        JButton buttonCalc = new JButton(text); 
        buttonCalc.addActionListener (new Action1());
        return buttonCalc;
    }
    private static class Action1 implements ActionListener {        
        public void actionPerformed (ActionEvent e) {
            frame.setTitle("click");
        }
    }
}
