package com.synwing.demo.websockets;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author wangyong
 *
 */
public class FileOperater {

	public static List<JQplotChartData> readFileToList(String fileName,int startLine,int numLine){
		int rows = 0;
		ArrayList<JQplotChartData> returnValue = new ArrayList();

		try {
			InputStream is = new FileInputStream(fileName);
			String line; // 用来保存每行读取的内容
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			line = reader.readLine(); // 读取第一行
			JQplotChartData jqplotChartData = null;
			while (line != null) { // 如果 line 为空说明读完了
				try {
					if(rows >= startLine){
						jqplotChartData = new JQplotChartData();
						jqplotChartData.setNumber(rows);
						jqplotChartData.setCgmValue(Double.valueOf(line));
						returnValue.add(jqplotChartData);
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Raw is: "+rows+"内容：" + line);
				}
				line = reader.readLine(); // 读取下一行
				rows++;
				if(rows>=startLine+numLine){
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return returnValue;
	}

}
