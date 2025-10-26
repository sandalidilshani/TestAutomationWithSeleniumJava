package utils;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    private static Workbook workbook;
    private static Sheet sheet;
    private static FileInputStream file;

    public static void loadExcel(String filePath, String sheetName) throws IOException {
        file = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(file);
        sheet = workbook.getSheet(sheetName);

        if (sheet == null) {
            throw new IOException("Sheet '" + sheetName + "' not found in workbook");
        }
    }

    public static String getCellData(int row, int col) {
        Row rowData = sheet.getRow(row);
        if (rowData == null) {
            return "";
        }

        Cell cell = rowData.getCell(col);
        if (cell == null) {
            return "";
        }

        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue().trim();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf((int) cell.getNumericCellValue());
        } else if (cell.getCellType() == CellType.BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        }
        return "";
    }

    public static int getRowCount() {
        return sheet.getPhysicalNumberOfRows();
    }

    public static void closeExcel() throws IOException {
        try {
            if (workbook != null) {
                workbook.close();
            }
        } finally {
            if (file != null) {
                file.close();
            }
        }
    }
}