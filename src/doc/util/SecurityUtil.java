package doc.util;
/**
 * 安全
 * @author jerry
 *
 */
public class SecurityUtil {
	/**
	 * 解密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String key) {
		return key;
	}
	/**
	 * 加密
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
