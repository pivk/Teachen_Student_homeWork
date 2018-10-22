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
 * 参数业务类
 * 
 * @author jerry
 *
 */
@Service
public class ConfigService extends BaseService {
	/**
	 * 获取参数
	 * 
	 * @return
	 */
	public Config get() {
		Config config = new Config();
		try {
			// 1.创建SAXReader
			SAXReader reader = new SAXReader();
			// 2.使用SAXReader读取要解析的XML文档
			String xmlpath = this.getClass().getClassLoader().getResource("/").getPath().toString();
			Document doc = reader.read(new FileInputStream(xmlpath + "config.xml"));
			// 3.Document对象表示的就是该XML文档从Document中首先获取根元素
			Element root = doc.getRootElement();
			// 4.从根元素中按照XML的层级结构逐级获取子元素
			config.setUploadPath(root.element("UploadPath").getText());
			config.setFilePath(root.element("FilePath").getText());
		
		} catch (Exception ex) {
			this.setMessage("读取失败");
		}
		return config;
	}

	/**
	 * 设置参数
	 * 
	 * @param config
	 * @throws IOException
	 */
	public boolean set(Config config) {
		if (config == null) {
			this.setMessage("写入失败");
			return false;
		}
		XMLWriter writer = null;
		try {
			// 1.创建SAXReader
			SAXReader reader = new SAXReader();
			// 2.使用SAXReader读取要解析的XML文档
			String xmlpath = this.getClass().getClassLoader().getResource("/").getPath().toString();
			Document doc = reader.read(new FileInputStream(xmlpath + "config.xml"));
			// 3.Document对象表示的就是该XML文档从Document中首先获取根元素
			Element root = doc.getRootElement();
			// 写uploadPath(上传文件保存地址)
			root.element("UploadPath").setText(config.getUploadPath());
			root.element("FilePath").setText(config.getFilePath());
	
			writer = new XMLWriter(new FileOutputStream(xmlpath + "config.xml"), OutputFormat.createPrettyPrint());
			writer.write(doc);
			AppData.Config.setUploadPath(config.getUploadPath());
			AppData.Config.setFilePath(config.getFilePath());
		
		} catch (Exception ex) {
			this.setMessage("写入失败");
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
}