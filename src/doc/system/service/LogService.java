package doc.system.service;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import doc.common.BaseService;
import pushunsoft.common.PageData;
import doc.system.entity.Log;
import doc.system.mapper.LogMapper;
import doc.system.view.LogV;
import pushunsoft.database.MyBatis;
/**
 * ����ҵ����
 * 
 * @author jerry
 *
 */
@Service
public class LogService extends BaseService {
	/**
	 * ��ҳ��ѯ
	 * 
	 * @param page
	 *            ҳ�룬��1��ʼ
	 * @param v
	 *            ��ѯ ����
	 * @return
	 */
	public  PageData<Log> getPage(Integer page, LogV v) {
		// ��ѯǰ׼��
		Map<String, Object> params = new HashMap<String, Object>();
		if (page == null) {
			page = 1;
		}
		int begin = (page - 1) * this.getPageSize();
		params.put("begin", begin);
		params.put("PageSize", this.getPageSize());
		if (v != null) {
			if (v.getId() != null && !"".equals(v.getId())) {
				params.put("shiJian", v.getDate());
			}
			if (v.getBiaoTi() != null && !"".equals(v.getBiaoTi())) {
				params.put("biaoTi", "%" + v.getBiaoTi().trim() + "%");
			}
			if (v.getUserId() != null && !"".equals(v.getUserId())) {
				params.put("userId", v.getUserId());
			}
		}
		// ��ʼ��ѯ���ݿ�
		PageData<Log> pageData = new PageData<Log>();
		pageData.setPageSize(this.getPageSize());
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			LogMapper mapper = session.getMapper(LogMapper.class);
			pageData.setTotal(mapper.selectCount(params));
			pageData.setData(mapper.selectPage(params));
		} catch (Exception ex) {
			this.setMessage("����ʧ��");
		} finally {
			database.closeSession();
		}
		// ���ش���
		return pageData;
	}
	/**
	 * ���
	 * 
	 * @param log
	 * @return
	 */
	public boolean add(Log log) {
		if (log == null) {
			this.setMessage("����Ϊ��");
			return false;
		}
		boolean result = false;
		// ��ʼִ�����ݿ�
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			LogMapper mapper = session.getMapper(LogMapper.class);
			result = mapper.insert(log);
			if (result) {
				// �������
				session.commit();
			}
		} catch (Exception ex) {
			session.rollback();
			this.setMessage("����ʧ��");
			result = false;
		} finally {
			database.closeSession();
		}
		// ���ش���
		return result;
	}
	public boolean add(Log log,SqlSession session) {
		if (log == null) {
			this.setMessage("����Ϊ��");
			return false;
		}
		boolean result=false;
		try {
			LogMapper mapper = session.getMapper(LogMapper.class);
			result = mapper.insert(log);
		} catch (Exception ex) {
			this.setMessage("����ʧ��");
			result = false;
		} finally {
		}
		// ���ش���
		return result;
	}
	/**
	 * �����ȫ����ѯ
	 * 
	 * 
	 * 
	 *
	 */
	public PageData<Log> getall( Log v) {
		Map<String, Object> params = new HashMap<String, Object>();
		// ��ʼ��ѯ���ݿ�
		PageData<Log> pageData = new PageData<Log>();
		pageData.setPageSize(this.getPageSize());
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			LogMapper mapper = session.getMapper(LogMapper.class);
			pageData.setData(mapper.selectAll(params));
		} catch (Exception ex) {
			this.setMessage("����ʧ��");
		} finally {
			database.closeSession();
		}
		// ���ش���
		return pageData;
	}
	/**
	 * ����˵���excel
	 * 
	 * @return
	 */
	public boolean toWriteXls(Log v) {
		// ����������
		HSSFWorkbook workbook = new HSSFWorkbook();
		// ����������
		HSSFSheet sheet = workbook.createSheet("��ѯ��־");
		//ѡ���һ����������
		HSSFSheet sheetOne = workbook.getSheetAt(0);
		//���ù����������ƣ�
		workbook.setSheetName(0, "��־��ѯ");
		//���ñ�ͷ
		String [] headers = {"����","ʱ��","�û���"};
		PageData<Log> data = getall(v);
		List<Log> list = data.getData();
		HSSFRow rows = sheet.createRow(0);
		for (int col = 0; col < headers.length; col++) {
			// �����������ͷ
			rows.createCell(col).setCellValue(headers[col]);
		}
		int row = 1;
		for(Log dv : list ){
			rows = sheet.createRow(row);
			if(dv.getBiaoTi()!=null){
				rows.createCell(0).setCellValue(dv.getBiaoTi());
			}
			if(dv.getDate()!=null){
				rows.createCell(1).setCellValue(dv.getDate());
			}
			if(dv.getUserId()!=null){
				rows.createCell(2).setCellValue(dv.getUserId());
			}
			row++;
		}
		Date date = new Date();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMddHHmmss");
		File xlsFile = new File("d:\\"+dateFormatter.format(date)+".xls");
		FileOutputStream xlsStream = null;
		try {
			xlsStream = new FileOutputStream(xlsFile);
			workbook.write(xlsStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}