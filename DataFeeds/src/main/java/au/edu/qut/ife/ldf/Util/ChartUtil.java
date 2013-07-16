package au.edu.qut.ife.ldf.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

public class ChartUtil {

	public Long dateTimeToUTC(String text, String dateFormat){
		//Sample Format: dd/MM/yy hh:mm:ss a
		SimpleDateFormat format = new SimpleDateFormat(dateFormat, Locale.US);
		
		format.setTimeZone(TimeZone.getTimeZone("UTC"));

		Long millis = null;
		try {
			java.util.Date date;
			//System.out.println(text);
			date = format.parse(text);
			millis = date.getTime();
			return millis;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println(text);
			e.printStackTrace();
			return millis;
		}
		
	}
}
