package utilities;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;

import base.BaseTest;

public class DataUtil extends BaseTest{
	
	@DataProvider(name="data")
	public Object[][] getData(Method m){
		
		String sheetName = m.getName();
		
		int rowNum = excel.getRowCount(sheetName);
		int colNum = excel.getColumnCount(sheetName);
		
		excel.getCellData(sheetName, colNum, rowNum);
		
		Object[][] data = new Object[rowNum-1][colNum];
		
		
		for(int rows=2; rows <= rowNum; rows++) {
			
			
			
			for(int cols=0; cols < colNum; cols++) {
				
				
				
				data[rows-2][cols] = excel.getCellData(sheetName, cols, rows);
				
			}
		}
		
		return data;
		
	}

}
