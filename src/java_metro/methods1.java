package java_metro;

import java.util.*;
//import java.util.regex.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java_metro.DBConnect.*;

/**
 *
 * @author Денис
 */

public class methods1 {
    
    static void show_text(String text)
    {
        System.out.println(text);
    }
    
    static int ask(int SIZE) {
        int[] count = new int[SIZE];
        for (int i = 0; i < SIZE; i++) count[i] = i+1;
        boolean flag = false;
        int choose = 0;
        show_text("Введите 1 - " + SIZE + ":");
        Scanner inner = new Scanner(System.in);
        while (flag != true) 
        {
            choose=inner.nextInt();
            for (int i = 0; i < SIZE; i++) if (choose==count[i]) flag = true;
        }
        return choose;
    }
    
    static boolean checkcorrectdate(String receivetext){
        Date date = null;
        boolean isdatecorrect;
        String pattern = "dd.MM.yyyy-HH:mm";
        DateFormat formatter = new SimpleDateFormat(pattern);
        formatter.setLenient(false);
        try {
            date = formatter.parse(receivetext);
            
            // Выводим результат на консоль
            //System.out.println(formatter.format(date));
            isdatecorrect = true;
        } catch (ParseException e) {
            // Обрабатываем исключение
            //e.printStackTrace();
            isdatecorrect = false;
        }
        return isdatecorrect;
    }
    static boolean checkcorrecttime(String receivetext){
        Date date = null;
        boolean istimecorrect;
        String pattern = "HH:mm";
        DateFormat formatter = new SimpleDateFormat(pattern);
        formatter.setLenient(false);
        try {
            date = formatter.parse(receivetext);
            istimecorrect = true;
        } catch (ParseException e) {
            istimecorrect = false;
        }
        return istimecorrect;
    }
    
    static Calendar askdate() {
        boolean flag = false;
        Calendar date = new GregorianCalendar();
        /*String textdate = "";
        show_text("Введите дату и время отправления в формате дд.мм.гггг-чч:мм");
        Scanner inner = new Scanner(System.in);
        while (flag != true){
            textdate = inner.nextLine();
            if (checkcorrectdate(textdate)) flag = true;
            else show_text("Некорректный формат даты. Введите дату в формате дд.мм.гггг-чч:мм");
        }
        date.setFirstDayOfWeek(Calendar.MONDAY);
        date.set(Calendar.DAY_OF_MONTH, Integer.parseInt(textdate.substring(0, 2)));
        date.set(Calendar.MONTH, Integer.parseInt(textdate.substring(3, 5))-1);
        date.set(Calendar.YEAR, Integer.parseInt(textdate.substring(6, 10)));
        date.set(Calendar.HOUR_OF_DAY, Integer.parseInt(textdate.substring(11, 13)));
        date.set(Calendar.MINUTE, Integer.parseInt(textdate.substring(14, 16)));
        date.set(Calendar.SECOND, 0);
        /*System.out.println(date.get(Calendar.DAY_OF_MONTH) + "." + 
                date.get(Calendar.MONTH) + "." + date.get(Calendar.YEAR) + 
                " " + date.get(Calendar.HOUR_OF_DAY) + ":" + date.get(Calendar.MINUTE));*/
        return date;
    }
    
    static int old_show_stations() throws SQLException {
        /*rs = connect("SELECT * FROM [005].[dbo].[users]");
        while (rs.next()) {
            System.out.println( rs.getInt("ID") + ", " + rs.getString(2) +"  "+ rs.getString(3));
            System.out.println();
        }*/
        String SQL = "SELECT count([Name]) FROM [005].[dbo].[Stations]";
        ResultSet rs = connect(SQL);
        rs.next();
        int SIZE = rs.getInt(1);
        String stations[] = new String[SIZE];
        rs = connect("SELECT [Name] FROM [005].[dbo].[Stations]");
        int i = 0;
        while (rs.next() || i < SIZE) {
            stations[i] = rs.getString("Name");
            i++;
        }
        for (int j = 0; j < SIZE; j++) show_text(j+1 + " - " + stations[j]);
        return SIZE;
    }
    static String[] show_stations() throws SQLException {
        /*rs = connect("SELECT * FROM [005].[dbo].[users]");
        while (rs.next()) {
            System.out.println( rs.getInt("ID") + ", " + rs.getString(2) +"  "+ rs.getString(3));
            System.out.println();
        }*/
        String SQL = "SELECT count([Name]) FROM [005].[dbo].[Stations]";
        ResultSet rs = connect(SQL);
        rs.next();
        int SIZE = rs.getInt(1);
        String stations[] = new String[SIZE];
        rs = connect("SELECT [Name] FROM [005].[dbo].[Stations]");
        int i = 0;
        while (rs.next() || i < SIZE) {
            stations[i] = (i+1) + " - " + rs.getString("Name");
            i++;
        }
        return stations;
    }
    
    static boolean isWeekend(Calendar date) throws SQLException{
        boolean days;
        String SQL = "SELECT COUNT (ID) FROM [005].[dbo].[UnworkDay] WHERE Date = '" + date.get(Calendar.YEAR) + "-" + (int)(date.get(Calendar.MONTH)+1) + "-" + date.get(Calendar.DAY_OF_MONTH) + "' AND Status = 1";
        ResultSet rs = connect(SQL);
        rs.next();
        if (rs.getInt(1) >= 1) days = true;
        else days = ((date.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || date.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)) ? true:false;
        return days;
    }
    
    static int TimeForRequest (Calendar date) {
        int time = (date.get(Calendar.HOUR_OF_DAY)*60 + date.get(Calendar.MINUTE))*60;
        return time;
    }
    
    static String formattime (int StartTime){
        String time;
        if ((StartTime/60 - StartTime/60/60*60) < 10){
            time = Integer.toString(StartTime/60/60) + ":0" + Integer.toString(StartTime/60 - StartTime/60/60*60);
        } else time = Integer.toString(StartTime/60/60) + ":" + Integer.toString(StartTime/60 - StartTime/60/60*60);
        return time;
    }
    
    static int[] depart (int dir1, int dir2, Calendar date) throws SQLException{
        int departTime[] = new int[3];
        int count = 0;
        dir2 = (dir2 < dir1) ? dir1 - 1 : dir1 + 1;
        int days = isWeekend(date) ? 0 : 1;
        String SQL = "SELECT TOP 3 [StartTime] From [005].[dbo].[TimeTable] Where Tothe = " + dir2 + " And Fromthe = " + dir1 + " And Workunwork = " + days + " And StartTime > " + TimeForRequest(date);
        ResultSet rs = connect(SQL);
        while (rs.next() || count < 3) {
            departTime[count] = rs.getInt("StartTime");
            count++;
        }
        return departTime;
    }
    
    static int deliver (int dir1, int dir2, Calendar date) throws SQLException{
        int tempdate = TimeForRequest(date);
        int temp1 = dir1;
        int temp2 = dir2;
        int flag = 0;
        int days = isWeekend(date) ? 0 : 1;
        if (dir1 < dir2){
            temp2 = dir1 + 1;
            flag = 0;
        }else if (dir2 < dir1){
            temp2 = dir1 - 1;
            flag = 1;
        }
        while (temp2 != dir2)
        {
            String SQL = "SELECT TOP 1 [StartTime] From [005].[dbo].[TimeTable] Where (Tothe = " + temp2 + ") And (Fromthe = " + temp1 + ") And (Workunwork = " + days + ") And (StartTime > " + tempdate + ")";
            ResultSet rs = connect(SQL);
            rs.next();
            tempdate = rs.getInt(1);
            //show_text(temp1 + " - " + temp2);
            //show_text(formattime(tempdate));
            if (flag == 0){
                temp1 ++;
                temp2 ++;
            }else if (flag == 1){
                temp1 --;
                temp2 --;
            }
        }
        String SQL = "SELECT TOP 1 [StartTime] From [005].[dbo].[TimeTable] Where (Tothe = " + temp2 + ") And (Fromthe = " + temp1 + ") And (Workunwork = " + days + ") And (StartTime > " + tempdate + ")";
        ResultSet rs = connect(SQL);
        rs.next();
        tempdate = rs.getInt(1);
        //show_text(temp1 + " - " + temp2);
        if (temp2 == 6 && temp1 ==5) {
            temp2 = 5;
            temp1 = 6;
        } else if (temp2 == 1 && temp1 == 2){
            temp2 = 2;
            temp1 = 1;
        } else {
            if (flag == 0) {
                temp1 ++;
                temp2 ++;
            } else if (flag == 1){
                temp1 --;
                temp2 --;
            }
        }
        //show_text(tempdate + "");
        SQL = "SELECT TOP 1 [StartTime] From [005].[dbo].[TimeTable] Where (Tothe = " + temp2 + ") And (Fromthe = " + temp1 + ") And (Workunwork = " + days + ") And (StartTime > " + tempdate + ")";
        rs = connect(SQL);
        rs.next();
        //show_text(temp1 + " - " + temp2);
        tempdate = rs.getInt(1);
        //show_text(tempdate + "");
        return tempdate;
    }
}
