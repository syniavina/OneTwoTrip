import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Date_define{

    // создать класс, чтобы подогнать дату под входной файл

    boolean duringDay(String log_date) {

        boolean later = false; //need a value if error appears

        //define current date
        Calendar today = Calendar.getInstance();

        //define yesterday
        today.add(Calendar.DAY_OF_MONTH, -1);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss", Locale.ENGLISH);

        try {

            Date date = formatter.parse(log_date);

            // convert date from Date-type to Calendar-type
            Calendar calendar_date = Calendar.getInstance();
            calendar_date.setTime(date);

            later = calendar_date.after(today); //если date позже today, то true

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return later;
    }

    // создать класс, чтобы подогнать дату под входной файл
    boolean forceDate(String log_date) {
        String const_date = "18/May/2016:12:44:05";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss", Locale.ENGLISH);
        boolean compare_date = false;

        try {
            Date pseudo_yesterday = formatter.parse(const_date);
            Date date_date_log = formatter.parse(log_date);
            compare_date = pseudo_yesterday.after(date_date_log);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return compare_date;
    }

}

