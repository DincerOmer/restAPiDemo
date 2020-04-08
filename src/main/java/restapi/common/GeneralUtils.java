package restapi.common;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@Service
public class GeneralUtils {

    public static long generateTicketNo() {
        return System.currentTimeMillis();
    }

    private Date getFlightDate(String flightDateStr) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a", Locale.ENGLISH);
        formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));

        Date date = formatter.parse(flightDateStr);

        return date;
    }
}
