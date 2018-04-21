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
 * 机构业务类
 * 
 * @author jerry
 *
 */
@Service
public class LogService extends BaseService {
	/**
	 * 分页查询
	 * 
	 * @param page
	 *            页码，从1开始
	 * @param v
	 *            查询 条件
	 * @return
	 */
	public  PageData<Log> getPage(Integer page, LogV v) {
		// 查询前准备
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
		// 开始查询数据库
		PageData<Log> pageData = new PageData<Log>();
		pageData.setPageSize(this.getPageSize());
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			LogMapper mapper = session.getMapper(LogMapper.class);
			pageData.setTotal(mapper.selectCount(params));
			pageData.setData(mapper.selectPage(params));
		} catch (Exception ex) {
			this.setMessage("操作失败");
		} finally {
			database.closeSession();
		}
		// 返回处理
		return pageData;
	}
	/**
	 * 添加
	 * 
	 * @param log
	 * @return
	 */
	public boolean add(Log log) {
		if (log == null) {
			this.setMessage("数据为空");
			return false;
		}
		boolean result = false;
		// 开始执行数据库
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			LogMapper mapper = session.getMapper(LogMapper.class);
			result = mapper.insert(log);
			if (result) {
				// 真正入库
				session.commit();
			}
		} catch (Exception ex) {
			session.rollback();
			this.setMessage("操作失败");
			result = false;
		} finally {
			database.closeSession();
		}
		// 返回处理
		return result;
	}
	public boolean add(Log log,SqlSession session) {
		if (log == null) {
			this.setMessage("数据为空");
			return false;
		}
		boolean result=false;
		try {
			LogMapper mapper = session.getMapper(LogMapper.class);
			result = mapper.insert(log);
		} catch (Exception ex) {
			this.setMessage("操作失败");
			result = false;
		} finally {
		}
		// 返回处理
		return result;
	}
	/**
	 * 已审核全部查询
	 * 
	 * 
	 * 
	 *
	 */
	public PageData<Log> getall( Log v) {
		Map<String, Object> params = new HashMap<String, Object>();
		// 开始查询数据库
		PageData<Log> pageData = new PageData<Log>();
		pageData.setPageSize(this.getPageSize());
		MyBatis database = getDatabase();
		SqlSession session = database.openSession();
		try {
			LogMapper mapper = session.getMapper(LogMapper.class);
			pageData.setData(mapper.selectAll(params));
		} catch (Exception ex) {
			this.setMessage("操作失败");
		} finally {
			database.closeSession();
		}
		// 返回处理
		return pageData;
	}
	/**
	 * 带审核导出excel
	 * 
	 * @return
	 */
	public boolean toWriteXls(Log v) {
		// 创建工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 创建工作表
		HSSFSheet sheet = workbook.createSheet("查询日志");
		//选择第一个工作簿：
		HSSFSheet sheetOne = workbook.getSheetAt(0);
		//设置工作簿的名称：
		workbook.setSheetName(0, "日志查询");
		//设置表头
		String [] headers = {"标题","时间","用户名"};
		PageData<Log> data = getall(v);
		List<Log> list = data.getData();
		HSSFRow rows = sheet.createRow(0);
		for (int col = 0; col < headers.length; col++) {
			// 向工作表中添表头
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