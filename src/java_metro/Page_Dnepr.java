package java_metro;

//тест бранч


import java.awt.*;
import javax.swing.*;
import static java_metro.FirstPage.*;
import static java_metro.methods1.*;
import static java_metro.Info_msg.*;
import de.wannawork.jcalendar.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Page_Dnepr extends javax.swing.JFrame{
    private final static JFrame frame = new JFrame("Днепропетровск");
    private final static DateBox ForCalendar = new DateBox();
    private final static CurrentTime Time = new CurrentTime();
    private final static Combo cbox = new Combo();
    private final static TimeTextBox departime = new TimeTextBox();
    
    public Page_Dnepr() throws Exception {
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        
        frame.setJMenuBar(menuBar(false, true));
        frame.add(addAPanel());
    }
    public static void show_dnepr(){
        frame.setVisible(true);
    }
    public static void hide_dnepr(){
        frame.setVisible(false);
    }
    private static JPanel addAPanel() throws Exception {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.add(addALabel("Откуда:", 10, 10, 50, 20));
        panel.add(addALabel("Куда:", 10, 35, 50, 20));
        String all_stations[] = show_stations();
        panel.add(cbox.combobox1(all_stations));
        panel.add(cbox.combobox2(all_stations));
        panel.add(addALabel("Дата:", 230, 10, 50, 20));
        panel.add(addALabel("Время:", 230, 35, 50, 20));
        panel.add(ForCalendar.showDatepicker());
        panel.add(Time.TimeBox());
        panel.add(addAButton("Показать", 275, 180, 100, 50));
        panel.add(addALabel("Ближайшее отправление поезда:", 10, 60, 250, 20));
        panel.add(departime.DepartBox());
        panel.add(addALabel("Время прибытия первого поезда:", 10, 110, 250, 20));
        panel.add(departime.DeliverBox());
        panel.add(addALabel("Время в пути:", 10, 160, 250, 20));
        panel.add(departime.AllTimeBox());
        return panel;
    }
    private static JLabel addALabel(String text, int x, int y, int w, int h) { 
        JLabel label = new JLabel(text);
        label.setBounds(x, y, w, h);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        //label.setBorder(BorderFactory.createEtchedBorder());
        return label;
    }
    private static JButton addAButton(String text, int x, int y, int w, int h) { 
        JButton buttonCalc = new JButton(text); 
        buttonCalc.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonCalc.setBounds(x, y, w, h);
        buttonCalc.addActionListener (new Action_Calc());
        return buttonCalc;
    }
    private static class Action_Calc implements ActionListener {        
        public void actionPerformed (ActionEvent e) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(ForCalendar.OldDate());
            int dir1 = cbox.getSelectCombo1();
            int dir2 = cbox.getSelectCombo2();
            if (dir1 == dir2){
                MSG_TEXT("Станция прибытия должна отличаться от станции отправления!");
            }else{
                if(checkcorrecttime(Time.getUserTime())){
                    cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(Time.getUserTime().substring(0, Time.getUserTime().indexOf(':'))));
                    cal.set(Calendar.MINUTE, Integer.parseInt(Time.getUserTime().substring(Time.getUserTime().indexOf(':')+1, Time.getUserTime().length())));
                    cal.set(Calendar.SECOND, 0);
                    int departTime[] = new int [3];
                    int deliverTime = 0;
                    try {
                        departTime = depart(dir1, dir2, cal);
                        departime.setDepartTime(departTime);
                    } catch (SQLException ex) {
                        Logger.getLogger(Page_Dnepr.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        deliverTime = deliver(dir1, dir2, cal);
                        departime.setDelivTime(deliverTime);
                    } catch (SQLException ex) {
                        Logger.getLogger(Page_Dnepr.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    departime.setAllTime((deliverTime - departTime[0])/60);
                }else {
                    MSG_TEXT("Время должно быть указано в корректном формате (ЧЧ:ММ)!");
                }
            }
        }
    }
}
class DateBox {   
    final JCalendarComboBox calendarPanel = new JCalendarComboBox();
    public JCalendarComboBox showDatepicker () {
        calendarPanel.setBounds(273, 11, 103, 20);
        return calendarPanel;
    }
    public Date OldDate(){
        Date TDate = calendarPanel.getDate();
        return TDate;
    }
}
class CurrentTime {
    private static final JTextField curtime = new JTextField();
    public JTextField TimeBox(){
        String curStringDate = new SimpleDateFormat("HH:mm").format(System.currentTimeMillis());
        curtime.setText(curStringDate);
        curtime.setBounds(290, 36, 50, 20);
        curtime.setBorder(BorderFactory.createEtchedBorder());
        curtime.setHorizontalAlignment(JTextField.CENTER);
        return curtime;
    }
    public String getUserTime(){
        return curtime.getText().trim();
    }
}
class Combo {
    JComboBox combobox1;
    JComboBox combobox2;
    public JComboBox combobox1 (String[] show_stations){
        combobox1 = new JComboBox( show_stations );
        combobox1.setBounds(60, 11, 150, 20);
        return combobox1;
    }
    public JComboBox combobox2 (String[] show_stations){
        combobox2 = new JComboBox( show_stations );
        combobox2.setBounds(60, 36, 150, 20);
        return combobox2;
    }
    public int getSelectCombo1 (){
        return combobox1.getSelectedIndex()+1;
    }
    public int getSelectCombo2 (){
        return combobox2.getSelectedIndex()+1;
    }
}
class TimeTextBox {
    private static final JTextField deptime = new JTextField();
    private static final JTextField deltime = new JTextField();
    private static final JTextField alltime = new JTextField();
    public JTextField DepartBox(){
        deptime.setBounds(10, 85, 250, 20);
        deptime.setBorder(BorderFactory.createEtchedBorder());
        deptime.setHorizontalAlignment(JTextField.CENTER);
        deptime.setEditable(false);
        return deptime;
    }
    public JTextField DeliverBox(){
        deltime.setBounds(10, 135, 250, 20);
        deltime.setBorder(BorderFactory.createEtchedBorder());
        deltime.setHorizontalAlignment(JTextField.CENTER);
        deltime.setEditable(false);
        return deltime;
    }
    public JTextField AllTimeBox(){
        alltime.setBounds(10, 185, 250, 20);
        alltime.setBorder(BorderFactory.createEtchedBorder());
        alltime.setHorizontalAlignment(JTextField.CENTER);
        alltime.setEditable(false);
        return alltime;
    }
    public void setDepartTime(int[] DepartTime){
        String res = "";
        for (int i=0; i<DepartTime.length; i++) res = res + formattime(DepartTime[i]) + " ";
        deptime.setText(res);
    }
    public void setDelivTime(int DelivTime){
        String res = "" + formattime(DelivTime);
        deltime.setText(res);
    }
    public void setAllTime(int AllTime){
        String res = AllTime + " минут";
        alltime.setText(res);
    }
}
