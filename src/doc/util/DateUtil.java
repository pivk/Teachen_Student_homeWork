package doc.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 日期工具类
 * 
 * @author jerry
 *
 */
public class DateUtil {
	/**
	 * 字符串转日期
	 * 
	 * @param str
	 * @return
	 */
	public static Date str2Date(String str) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = dateFormatter.parse(str);
		} catch (ParseException e) {
			try {
				date = dateFormatter.parse("1900-01-01 00:00:00");
			} catch (ParseException e1) {
			}
		}
		return date;
	}
	/**
	 * 显示MySQL数据库日期，只显示日期部分
	 * 
	 * @param str
	 * @return
	 */
	public static String showDate(String dbDate) {
		if (dbDate == null || dbDate.length() < 10 || dbDate.startsWith("0000"))
			return "";
		else
			return dbDate.substring(0, 10);
	}
	/**
	 * 显示MySQL数据库日期
	 * 
	 * @param str
	 *            日期格式yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String showDateTime(String dbDate) {
		if (dbDate == null || dbDate.startsWith("0000"))
			return "";
		else
			return dbDate;
	}
	public static void main(String[] args) {
		String str = "1979-01-01 00:00:00";
		System.out.println(DateUtil.showDate(str));
	}
}
