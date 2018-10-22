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
	 * ��libreOffice����ķ���
	 * 
	 * @return
	 */
	public String getLibreOfficeHome() {
		String osName = System.getProperty("os.name");

		if (Pattern.matches("Linux.*", osName)) {
			// ��ȡlinuxϵͳ��libreoffice�������λ��
			System.out.println("��ȡLinuxϵͳLibreOffice·��");
			return "/opt/libreoffice 5/program/soffice";
		} else if (Pattern.matches("Windows.*", osName)) {
			// ��ȡwindowsϵͳ��libreoffice�������λ��
			System.out.println("��ȡwindowsϵͳLibreOffice·��");
			return "C:\\Program Files\\LibreOffice 5";
		}
		return null;
	}

	/**
	 * ת��libreoffice֧�ֵ��ļ�Ϊpdf
	 * 
	 * @param inputfile
	 * @param outputfile
	 */
	public void libreOffice2PDF(File inputfile, File outputfile) {
		String LibreOffice_HOME = getLibreOfficeHome();
		String fileName = inputfile.getName();
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()) + "�ļ�" + inputfile.getName());
		System.out.println(fileName.substring(fileName.lastIndexOf(".")));

		DefaultOfficeManagerConfiguration configuration = new DefaultOfficeManagerConfiguration();
		// libreOffice�İ�װĿ¼
		configuration.setOfficeHome(new File(LibreOffice_HOME));
		// �˿ں�
		configuration.setPortNumber(8100);
		configuration.setTaskExecutionTimeout(1000 * 60 * 25L);
		// ��������ִ�г�ʱΪ10����
		configuration.setTaskQueueTimeout(1000 * 60 * 60 * 24L);
		// ����������г�ʱΪ24Сʱ
		OfficeManager officeManager = configuration.buildOfficeManager();
		officeManager.start();
		System.out.println(new Date().toString() + "��ʼת��......");
		OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
		converter.getFormatRegistry();
		try {
			converter.convert(inputfile, outputfile);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ת��ʧ��");
		} finally {
			officeManager.stop();
		}

		System.out.println(new Date().toString() + "ת������....");
	}
}
