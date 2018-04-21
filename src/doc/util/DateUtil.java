package doc.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * ���ڹ�����
 * 
 * @author jerry
 *
 */
public class DateUtil {
	/**
	 * �ַ���ת����
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
	 * ��ʾMySQL���ݿ����ڣ�ֻ��ʾ���ڲ���
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
	 * ��ʾMySQL���ݿ�����
	 * 
	 * @param str
	 *            ���ڸ�ʽyyyy-MM-dd HH:mm:ss
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
