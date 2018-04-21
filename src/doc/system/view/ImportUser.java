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

import doc.system.entity.User;
public class ImportUser {
	/**
	 * дExcel,xls��ʽ
	 */
	public boolean WriteXls(String path) {
		// ����������
		@SuppressWarnings("resource")
		HSSFWorkbook workbook = new HSSFWorkbook();
		// ����������
		HSSFSheet sheet = workbook.createSheet("sheet1");
		for (int row = 0; row < 10; row++) {
			HSSFRow rows = sheet.createRow(row);
			for (int col = 0; col < 10; col++) {
				// ����������������
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
		System.out.println("���ɳɹ���" + path);
		return true;
	}
	/**
	 * ��ȡExcel��xls��ʽ
	 */
	@SuppressWarnings("deprecation")
	public List<User> ReadXls(String path) {
		List<User> list = new ArrayList<User>();
		File xlsFile = new File(path);
		HSSFWorkbook workbook = null;
		try {
			POIFSFileSystem fs = new POIFSFileSystem(xlsFile);
			workbook = new HSSFWorkbook(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// ��ù���������
		int sheetCount = workbook.getNumberOfSheets();
		// ����������
		for (int i = 0; i < sheetCount; i++) {
			HSSFSheet sheet = workbook.getSheetAt(i);
			// �������,
			int rows = sheet.getLastRowNum() + 1;
			// ����������Ȼ��һ�У��ڵõ���������
			HSSFRow tmp = sheet.getRow(0);
			if (tmp == null) {
				continue;
			}
			int cols = tmp.getPhysicalNumberOfCells();
			// ��ȡ����,ȥ����ͷ
			for (int row = 1; row < rows; row++) {
				HSSFRow r = sheet.getRow(row);
				for (int col = 0; col < cols; col++) {
					r.getCell(col).setCellType(Cell.CELL_TYPE_STRING);;
				}
				User user = new User();
				user.setId(r.getCell(0).getStringCellValue());
				user.setXingMing(r.getCell(1).getStringCellValue());
				list.add(user);
			}
		}
		return list;
	}
	public static void main(String[] args) {
		String path = "d:\\poi.xls";
		ImportUser util = new ImportUser();
		List<User> list = util.ReadXls(path);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getId() + " " +  list.get(i).getXingMing());
		}
	}
}