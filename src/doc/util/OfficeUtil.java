package doc.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;


public class OfficeUtil {

	/**
	 * 打开libreOffice服务的方法
	 * 
	 * @return
	 */
	public String getLibreOfficeHome() {
		String osName = System.getProperty("os.name");

		if (Pattern.matches("Linux.*", osName)) {
			// 获取linux系统下libreoffice主程序的位置
			System.out.println("获取Linux系统LibreOffice路径");
			return "/opt/libreoffice 5/program/soffice";
		} else if (Pattern.matches("Windows.*", osName)) {
			// 获取windows系统下libreoffice主程序的位置
			System.out.println("获取windows系统LibreOffice路径");
			return "C:\\Program Files\\LibreOffice 5";
		}
		return null;
	}

	/**
	 * 转换libreoffice支持的文件为pdf
	 * 
	 * @param inputfile
	 * @param outputfile
	 */
	public void libreOffice2PDF(File inputfile, File outputfile) {
		String LibreOffice_HOME = getLibreOfficeHome();
		String fileName = inputfile.getName();
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()) + "文件" + inputfile.getName());
		System.out.println(fileName.substring(fileName.lastIndexOf(".")));

		DefaultOfficeManagerConfiguration configuration = new DefaultOfficeManagerConfiguration();
		// libreOffice的安装目录
		configuration.setOfficeHome(new File(LibreOffice_HOME));
		// 端口号
		configuration.setPortNumber(8100);
		configuration.setTaskExecutionTimeout(1000 * 60 * 25L);
		// 设置任务执行超时为10分钟
		configuration.setTaskQueueTimeout(1000 * 60 * 60 * 24L);
		// 设置任务队列超时为24小时
		OfficeManager officeManager = configuration.buildOfficeManager();
		officeManager.start();
		System.out.println(new Date().toString() + "开始转换......");
		OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
		converter.getFormatRegistry();
		try {
			converter.convert(inputfile, outputfile);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("转换失败");
		} finally {
			officeManager.stop();
		}

		System.out.println(new Date().toString() + "转换结束....");
	}
}
