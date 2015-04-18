package java_metro;
/**
 * @author Денис
 */

import java.util.Calendar;
import static java_metro.methods1.*;
import static java_metro.DBConnect.*;
import java.awt.Desktop;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Java_Metro {

    public static void main(String[] args) throws Exception {
        java.awt.EventQueue.invokeLater(() -> {
            try {
                FirstPage firstPage = new FirstPage();
                Page_Dnepr page_Dnepr = new Page_Dnepr();
                Page_Kyiv page_Kyiv = new Page_Kyiv();
                Page_Kharkov page_Kharkov = new Page_Kharkov();
                Edit_Weekend edit_Weekend = new Edit_Weekend();
                Edit_Permissions edit_Permissions = new Edit_Permissions();
                Edit_Timetable edit_Timetable = new Edit_Timetable();
                LoginWindow Loginwindow = new LoginWindow();
            } catch (Exception ex) {
                Logger.getLogger(Java_Metro.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        int choose = 0;
        int dir1 = 0, dir2 = 0;
        int departTime[];
        int deliverTime;
        Calendar startDate;
        show_text("Программа МЕТРО\nВыберите город");
        show_text("1 - Днепропетровск;\n2 - Киев;\n3 - Харьков;");
        //choose = ask(3);
        if (choose == 1)
        {
            while (dir1 == dir2){
            show_text("Выберите станцию отправления:");
            dir1 = ask(old_show_stations());
            show_text("Выберите станцию прибытия:");
            dir2 = ask(old_show_stations());
            if (dir1 == dir2) show_text("Станция прибытия должна отличаться от станции отправления!");
            }
            startDate = askdate();
            departTime = depart(dir1, dir2, startDate);
            show_text("Ближайшее время отправления:");
            show_text(formattime(departTime[0]) + ", " + formattime(departTime[1]) + ", " + formattime(departTime[2]));
            deliverTime = deliver(dir1, dir2, startDate);
            show_text("Время прибытия первого поезда:\n" + formattime(deliverTime));
            show_text("Время в пути:\n" + (deliverTime - departTime[0])/60 + " минут");
            disconnectFromDB();
            /*Thread.sleep(1000);
            String reportstring = "http://idea-pc/ReportServer/Pages/ReportViewer.aspx?/Metro/reportbytime&rs:Command=Render&FromThe1=1&ToThe1=2&FromStartTime=55555";
            if(Desktop.isDesktopSupported())
            {
                Desktop.getDesktop().browse(new URI(reportstring));
            }*/
            //репорт сервер
            //  http://idea-pc/ReportServer/Pages/ReportViewer.aspx?/Metro
            //  /reportbytime&rs:Command=Render&FromThe1=1&ToThe1=2&FromStartTime=55555
        }
        if (choose == 2)
        {
            show_text("Киев");
        }
        if (choose == 3)
        {
            show_text("Харьков");
        }
    }
    
}
