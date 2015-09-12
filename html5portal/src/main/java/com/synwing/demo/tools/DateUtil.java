package com.synwing.demo.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.LocalDateTime;

public class DateUtil {

	/**
	 * unix时间转换为目录
	 * @param dateTime:12283567
	 * @param targetId:2d59a310-de3e-481c-b94e-c3f3adee7b22
	 * @return:2015/03/21/2d59a310-de3e-481c-b94e-c3f3adee7b22/
	 * @throws ParseException
	 */
	public static String timeStr2DirStr(long longTime, String targetId)
			throws ParseException {

		LocalDateTime localDateTime = new LocalDateTime(longTime * 1000);
		NumberFormat formatter = NumberFormat.getNumberInstance();
		formatter.setMinimumIntegerDigits(4);
		formatter.setGroupingUsed(false);
		String yearStr = formatter.format(localDateTime.getYear());

		formatter.setMinimumIntegerDigits(2);
		formatter.setGroupingUsed(false);
		String monthStr = formatter.format(localDateTime.getMonthOfYear());

		formatter.setMinimumIntegerDigits(2);
		formatter.setGroupingUsed(false);
		String dayStr = formatter.format(localDateTime.getDayOfMonth());

		StringBuffer returnStr = new StringBuffer("");
		returnStr.append(yearStr);
		returnStr.append("/");
		returnStr.append(monthStr);
		returnStr.append("/");
		returnStr.append(dayStr);
		returnStr.append("/");
		returnStr.append(targetId);
		returnStr.append("/");
		return returnStr.toString();
	}

	/**
	 * unix时间转换为文件名称
	 * 
	 * @param dateTime:12283567
	 * @param targetId:2d59a310-de3e-481c-b94e-c3f3adee7b22
	 * @return:2d59a310-de3e-481c-b94e-c3f3adee7b22-12283567
	 * @throws ParseException
	 */
	public static String timeStr2FileStr(long longTime, String targetId)
			throws ParseException {

		StringBuffer returnStr = new StringBuffer("");
		returnStr.append(targetId);
		returnStr.append("-");
		returnStr.append(longTime);
		returnStr.append(".dat");
		return returnStr.toString();
	}

	/**
	 * 找出目录下面距离传入时间最近的文件
	 * 
	 * @param pathStr:d:/2015/02/10/2d59a310-de3e-481c-b94e-c3f3adee7b22
	 * @param dateTime:2012-01-01 14:55:11
	 * @param targetId:2d59a310-de3e-481c-b94e-c3f3adee7b22
	 * @return:2d59a310-de3e-481c-b94e-c3f3adee7b22-12283567.dat
	 * @throws ParseException
	 */
	public static String neartTimeStr2FileStr(String pathStr, long longTime,
			String targetId) throws ParseException {

		File file = new File(pathStr);
		if (file.exists()) {
			File fa[] = file.listFiles();
			for (int i = 0; i < fa.length; i++) {
				File fs = fa[i];
				String fileName = new String("");
				// 不能是目录文件
				if (!fs.isDirectory()) {
					fileName = fs.getName();
					Long fileLong = Long.valueOf(fileName.substring(targetId
							.length() + 1, fs.getName().lastIndexOf(".")));
					if (Math.abs(longTime - fileLong) < 300L) {
						return fileName;
					}
				}
			}
		}

		// 如果没有找到，则返回拼装的文件名
		StringBuffer returnStr = new StringBuffer("");
		returnStr.append(targetId);
		returnStr.append("-");
		returnStr.append(longTime);
		returnStr.append(".dat");
		return returnStr.toString();
	}

	/**
	 * 字符串时间转换为unixtime
	 * 
	 * @param dateTime:2012-01-01 14:55:11
	 * @return long:12283567
	 * @throws ParseException
	 */
	public static long timeStr2Unix(String dateTime) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = null;
		date = df.parse(dateTime);
		long longTime = date.getTime() / 1000;
		return longTime;
	}

	/**
	 * 字符串时间转换为时间
	 * 
	 * @param dateTime:2012-01-01 14:55:11
	 * @return:Date
	 * @throws ParseException
	 */
	public static Date timeStr2Date(String dateTime) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = null;
		date = df.parse(dateTime);
		return date;
	}

	/**
	 * 读取文件内容到List<String>
	 * 
	 * @param fileName文件全路径名
	 * @return List<String>
	 */
	public static List<String> readFileToList(String fileName) {
		int rows = 0;
		List<String> returnValue = new ArrayList<String>();
		try {
			InputStream is = new FileInputStream(fileName);
			String line; // 用来保存每行读取的内容
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));
			line = reader.readLine(); // 读取第一行
			while (line != null) { // 如果 line 为空说明读完了
				try {
					returnValue.add(line);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Raw is: " + rows + "内容：" + line);
				}
				line = reader.readLine(); // 读取下一行
				rows++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnValue;
	}

	/**
	 * 根据开始时间,结束时间,atrgetId,备份基础目录查询数据
	 * @param startTime:1970-05-23 00:05:07
	 * @param endTime
	 * @param targetId:"2d59a310-de3e-481c-b94e-c3f3adee7b22"
	 * @param baseDir:F:/test01back/
	 * @throws ParseException 
	 */
	public static List<String> getDataByDateStrTargetIdBaseDir(String startTime, String endTime, String targetId, String baseDir) throws ParseException {
		// 把时间转换为unixtime
		long startTimeL = timeStr2Unix(startTime);
		long endTimeL = timeStr2Unix(endTime);

		List<String> returnList = new ArrayList<String>();

		long tempTimeL = startTimeL;
		while (tempTimeL <= endTimeL) {
			List<String> tempList = new ArrayList<String>();
			// 找时间最接近那个文件
			String fileName = neartTimeStr2FileStr(baseDir+ timeStr2DirStr(tempTimeL, targetId), tempTimeL, targetId);
			File tempFile = new File(baseDir+ timeStr2DirStr(tempTimeL, targetId) + fileName);
			if (tempFile.exists()) {
				tempList = readFileToList(tempFile.getPath());
			}
			returnList.addAll(tempList);
			tempTimeL = tempTimeL + 300L;
		}
		
		return returnList;

	}

	public static void main(String[] args) throws ParseException {

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("运行前时间：" + df.format(new Date()));
		
		String startTime = "1970-05-23 00:05:07";
		String endTime = "1970-05-23 00:06:26";
		String targetId = "2d59a310-de3e-481c-b94e-c3f3adee7b22";
		String baseDir = "F:/test01back/";

		List<String> returnList = getDataByDateStrTargetIdBaseDir(startTime, endTime, targetId, baseDir);

		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("运行后时间：" + df2.format(new Date()));

		for (int i = 0; i < returnList.size(); i++) {
			System.out.println(returnList.get(i));

		}

	}

}
