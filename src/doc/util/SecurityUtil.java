package doc.util;
/**
 * ��ȫ
 * @author jerry
 *
 */
public class SecurityUtil {
	/**
	 * ����
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String key) {
		return key;
	}
	/**
	 * ����
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String key) {
		String mi=pushunsoft.security.Base64.encrypt(key);
		return mi;
	}
	public static void main(String[] args){
		String mi=SecurityUtil.encrypt("123");
		System.out.println(mi);
	}
}
