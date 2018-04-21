package doc.util;

/**
 * 序号
 * 
 * @author jerry
 *
 */
public class SeqUtil {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public synchronized String getId() {
		pushunsoft.identity.IdUtil util = new pushunsoft.identity.IdUtil();
		String id = util.getId();
		if (id == null) {
			setMessage(util.getMessage());
			return null;
		}
		return id;
	}

	/**
	 * 生成序号
	 * 
	 * @param type
	 *            类型
	 * @return 序号
	 */
	public synchronized String getSeqId(String type) {
		pushunsoft.identity.SeqUtil util = new pushunsoft.identity.SeqUtil();
		String seq = util.getSeqId(type);
		if (seq == null) {
			setMessage(util.getMessage());
			return null;
		}
		return seq;
	}

	public static void main(String[] args) {
		SeqUtil util = new SeqUtil();
		System.out.println(util.getSeqId("ncz"));
	}
}
