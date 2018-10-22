package doc.common;
public class AppData {
	/**
	 * 系统
	 */
	public static final String App_Name = "高校课程管理系统";
	/**
	 * 
	 */
	public static final String App_Id = "0";

	public static final String App_Version = "0.1";

	public static String App_UploadPath = "d:/upload/";
	public static doc.system.entity.Config Config=null;
	public static String FILECOMPRESS="filecompress";

	
	public static final String Session_User = "USER";
	
	public static final String User_Password = "123";
	public static final String User_Admin = "admin";
	public static final String[] User_Nei = { User_Admin, "jerry" };
	public static final String Tree_Type_Directory = "目录";
	public static final String Tree_Type_File = "文件";
	public static final String Tree_Type_Shortcut = "快捷方式";

}
