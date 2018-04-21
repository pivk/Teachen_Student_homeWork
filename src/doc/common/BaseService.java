package doc.common;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import pushunsoft.database.MyBatis;
public class BaseService {
	protected Logger logger = LogManager.getLogger(this.getClass());
	private String message=null;
	private int pageSize=20;//Ã¿Ò³ÌõÊý
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public void setDatabase(MyBatis database) {
		this.database = database;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	private MyBatis database=null;
	public MyBatis getDatabase() {
		if(database==null){
			database=new MyBatis();
		}
		return database;
	}
}
