package doc.system.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Service;
import doc.common.AppData;
import doc.common.BaseService;
import doc.system.entity.Config;

/**
 * ����ҵ����
 * 
 * @author jerry
 *
 */
@Service
public class ConfigService extends BaseService {
	/**
	 * ��ȡ����
	 * 
	 * @return
	 */
	public Config get() {
		Config config = new Config();
		try {
			// 1.����SAXReader
			SAXReader reader = new SAXReader();
			// 2.ʹ��SAXReader��ȡҪ������XML�ĵ�
			String xmlpath = this.getClass().getClassLoader().getResource("/").getPath().toString();
			Document doc = reader.read(new FileInputStream(xmlpath + "config.xml"));
			// 3.Document�����ʾ�ľ��Ǹ�XML�ĵ���Document�����Ȼ�ȡ��Ԫ��
			Element root = doc.getRootElement();
			// 4.�Ӹ�Ԫ���а���XML�Ĳ㼶�ṹ�𼶻�ȡ��Ԫ��

			// ��ȡuploadPath(�ϴ��ļ������ַ)
			String uploadPath = root.element("UploadPath").getText();
			config.setUploadPath(uploadPath);
		} catch (Exception ex) {
			this.setMessage("读取异常");
		}
		return config;
	}

	/**
	 * ���ò���
	 * 
	 * @param config
	 * @throws IOException
	 */
	public boolean set(Config config) {
		if (config == null) {
			this.setMessage("参数为空");
			return false;
		}
		XMLWriter writer = null;
		try {
			// 1.����SAXReader
			SAXReader reader = new SAXReader();
			// 2.ʹ��SAXReader��ȡҪ������XML�ĵ�
			String xmlpath = this.getClass().getClassLoader().getResource("/").getPath().toString();
			Document doc = reader.read(new FileInputStream(xmlpath + "config.xml"));
			// 3.Document
			Element root = doc.getRootElement();
			// дuploadPath(�ϴ��ļ������ַ)
			root.element("UploadPath").setText(config.getUploadPath());
			AppData.App_UploadPath = config.getUploadPath();
			writer = new XMLWriter(new FileOutputStream(xmlpath + "config.xml"), OutputFormat.createPrettyPrint());
			writer.write(doc);
		} catch (Exception ex) {
			this.setMessage("保存异常");
			return false;
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	/**
	 * ��ʼ������
	 * 
	 * @param config
	 * @return
	 */
	public boolean init() {
		Config config = get();
		if (config == null) {
			this.setMessage("配置文件为空");
			return false;
		}
		AppData.App_UploadPath = config.getUploadPath();
		return true;
	}
}