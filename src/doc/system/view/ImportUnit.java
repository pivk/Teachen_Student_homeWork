package doc.system.view;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;

import doc.system.entity.Unit;
public class ImportUnit {
	/**
	 * 写Excel,xls格式
	 */
	public boolean WriteXls(String path) {
		// 创建工作薄
		@SuppressWarnings("resource")
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 创建工作表
		HSSFSheet sheet = workbook.createSheet("sheet1");
		for (int row = 0; row < 10; row++) {
			HSSFRow rows = sheet.createRow(row);
			for (int col = 0; col < 10; col++) {
				// 向工作表中添加数据
				rows.createCell(col).setCellValue("data" + row + col);
			}
		}
		File xlsFile = new File(path);
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
		System.out.println("生成成功，" + path);
		return true;
	}
	/**
	 * 读取Excel，xls格式
	 */
	@SuppressWarnings("deprecation")
	public List<Unit> ReadXls(String path) {
		List<Unit> list = new ArrayList<Unit>();
		File xlsFile = new File(path);
		HSSFWorkbook workbook = null;
		try {
			POIFSFileSystem fs = new POIFSFileSystem(xlsFile);
			workbook = new HSSFWorkbook(fs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 获得工作表个数
		int sheetCount = workbook.getNumberOfSheets();
		// 遍历工作表
		for (int i = 0; i < sheetCount; i++) {
			HSSFSheet sheet = workbook.getSheetAt(i);
			// 获得行数,
			int rows = sheet.getLastRowNum() + 1;
			// 获得列数，先获得一行，在得到这行列数
			HSSFRow tmp = sheet.getRow(0);
			if (tmp == null) {
				continue;
			}
			int cols = tmp.getPhysicalNumberOfCells();
			// 读取数据,去除表头
			for (int row = 1; row < rows; row++) {
				HSSFRow r = sheet.getRow(row);
				for (int col = 0; col < cols; col++) {
					r.getCell(col).setCellType(Cell.CELL_TYPE_STRING);;
				}
				Unit unit = new Unit();
				unit.setId(r.getCell(0).getStringCellValue());
				unit.setMingCheng(r.getCell(1).getStringCellValue());
				list.add(unit);
			}
		}
		return list;
	}
}
