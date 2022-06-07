package com.tl.employee.helper;


import com.tl.employee.dto.EmployeeExcelDto;
import com.tl.employee.model.Employee;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String SHEET = "Users";

    public static boolean hasExcelFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }
    public static List<Employee> getUsersFromExcel(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();
            List<Employee> employees = new ArrayList<Employee>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                EmployeeExcelDto employeeExcelDto = new EmployeeExcelDto();
                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
                        case 0 -> employeeExcelDto.setFirstName(currentCell.getStringCellValue());
                        case 1 -> employeeExcelDto.setLastName(currentCell.getStringCellValue());
                        case 2 -> employeeExcelDto.setEmail(currentCell.getStringCellValue());
                        case 3 -> employeeExcelDto.setPhoneNumber(String.valueOf(currentCell.getNumericCellValue()));
                        default -> {
                        }
                    }
                    cellIdx++;
                }
                Employee employee = Employee.builder()
                        .firstName(employeeExcelDto.getFirstName())
                        .lastName(employeeExcelDto.getLastName())
                        .phoneNumber(employeeExcelDto.getPhoneNumber())
                        .email(employeeExcelDto.getEmail())
                        .build();
                employees.add(employee);
            }
            workbook.close();
            return employees;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}