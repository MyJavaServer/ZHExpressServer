package com.zhihui.zhexpress.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class ExcelExportUtil {
	public static String NO_DEFINE = "no_define";// 未定义的字段
	public static String DEFAULT_DATE_PATTERN = "yyyy年MM月dd日";// 默认日期格式
	public static int DEFAULT_COLOUMN_WIDTH = 17;

	/**
	 * 导出Excel 97(.xls)格式 ，少量数据
	 * 
	 * @param title
	 *            标题行
	 * @param headMap
	 *            属性-列名
	 * @param jsonArray
	 *            数据集
	 * @param datePattern
	 *            日期格式，null则用默认日期格式
	 * @param colWidth
	 *            列宽 默认 至少17个字节
	 * @param out
	 *            输出流
	 */
	public static void exportExcel(String title, Map<String, String> headMap, JSONArray jsonArray, String datePattern,
			int colWidth, OutputStream out) {
		if (datePattern == null)
			datePattern = DEFAULT_DATE_PATTERN;
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		workbook.createInformationProperties();
		workbook.getDocumentSummaryInformation().setCompany("*****公司");
		SummaryInformation si = workbook.getSummaryInformation();
		si.setAuthor("JACK"); // 填加xls文件作者信息
		si.setApplicationName("导出程序"); // 填加xls文件创建程序信息
		si.setLastAuthor("最后保存者信息"); // 填加xls文件最后保存者信息
		si.setComments("JACK is a programmer!"); // 填加xls文件作者信息
		si.setTitle("POI导出Excel"); // 填加xls文件标题信息
		si.setSubject("POI导出Excel");// 填加文件主题信息
		si.setCreateDateTime(new Date());
		// 表头样式
		HSSFCellStyle titleStyle = workbook.createCellStyle();
		HSSFFont titleFont = workbook.createFont();
		titleFont.setFontHeightInPoints((short) 20);
		titleStyle.setFont(titleFont);
		// 列头样式
		HSSFCellStyle headerStyle = workbook.createCellStyle();
		HSSFFont headerFont = workbook.createFont();
		headerFont.setFontHeightInPoints((short) 12);
		headerStyle.setFont(headerFont);
		// 单元格样式
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		HSSFFont cellFont = workbook.createFont();
		cellStyle.setFont(cellFont);
		// 生成一个(带标题)表格
		HSSFSheet sheet = workbook.createSheet();
		// 声明一个画图的顶级管理器
		// HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		// 定义注释的大小和位置,详见文档
		// HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,
		// 0, 0, 0, (short) 4, 2, (short) 6, 5));
		// 设置注释内容
		// comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
		// 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
		// comment.setAuthor("JACK");
		// 设置列宽
		int minBytes = colWidth < DEFAULT_COLOUMN_WIDTH ? DEFAULT_COLOUMN_WIDTH : colWidth;// 至少字节数
		int[] arrColWidth = new int[headMap.size()];
		// 产生表格标题行,以及设置列宽
		String[] properties = new String[headMap.size()];
		String[] headers = new String[headMap.size()];
		int ii = 0;
		for (Iterator<String> iter = headMap.keySet().iterator(); iter.hasNext();) {
			String fieldName = iter.next();

			properties[ii] = fieldName;
			headers[ii] = fieldName;

			int bytes = fieldName.getBytes().length;
			arrColWidth[ii] = bytes < minBytes ? minBytes : bytes;
			sheet.setColumnWidth(ii, arrColWidth[ii] * 256);
			ii++;
		}
		// 遍历集合数据，产生数据行
		int rowIndex = 0;
		for (Object obj : jsonArray) {
			if (rowIndex == 65535 || rowIndex == 0) {
				if (rowIndex != 0)
					sheet = workbook.createSheet();// 如果数据超过了，则在第二页显示

				HSSFRow titleRow = sheet.createRow(0);// 表头 rowIndex=0
				titleRow.createCell(0).setCellValue(title);
				titleRow.getCell(0).setCellStyle(titleStyle);
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headMap.size() - 1));

				HSSFRow headerRow = sheet.createRow(1); // 列头 rowIndex =1
				for (int i = 0; i < headers.length; i++) {
					headerRow.createCell(i).setCellValue(headers[i]);
					headerRow.getCell(i).setCellStyle(headerStyle);

				}
				rowIndex = 2;// 数据内容从 rowIndex=2开始
			}
			JSONObject jo = (JSONObject) JSONObject.toJSON(obj);
			HSSFRow dataRow = sheet.createRow(rowIndex);
			for (int i = 0; i < properties.length; i++) {
				HSSFCell newCell = dataRow.createCell(i);

				Object o = jo.get(properties[i]);
				String cellValue = "";
				if (o == null)
					cellValue = "";
				else if (o instanceof Date)
					cellValue = new SimpleDateFormat(datePattern).format(o);
				else
					cellValue = o.toString();

				newCell.setCellValue(cellValue);
				newCell.setCellStyle(cellStyle);
			}
			rowIndex++;
		}
		// 自动调整宽度(暂时不用)

		// for (int i = 0; i < headers.length; i++) {
		// sheet.autoSizeColumn(i);
		// }

		try {
			workbook.write(out);
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 导出Excel 2007 OOXML (.xlsx)格式
	 * 
	 * @param title
	 *            标题行
	 * @param headMap
	 *            属性-列头
	 * @param jsonArray
	 *            数据集
	 * @param datePattern
	 *            日期格式，传null值则默认 年月日
	 * @param colWidth
	 *            列宽 默认 至少17个字节
	 * @param out
	 *            输出流
	 */
	public static void exportExcelX(String title, Map<String, String> headMap, JSONArray jsonArray, String datePattern,
			int colWidth, OutputStream out) {
		if (datePattern == null)
			datePattern = DEFAULT_DATE_PATTERN;
		// 声明一个工作薄
		SXSSFWorkbook workbook = new SXSSFWorkbook(1000);// 缓存
		workbook.setCompressTempFiles(true);
		// 表头样式
		CellStyle titleStyle = workbook.createCellStyle();
		Font titleFont = workbook.createFont();
		titleFont.setFontHeightInPoints((short) 20);
		titleStyle.setFont(titleFont);
		// 列头样式
		CellStyle headerStyle = workbook.createCellStyle();
		Font headerFont = workbook.createFont();
		headerFont.setFontHeightInPoints((short) 12);
		headerStyle.setFont(headerFont);
		// 单元格样式
		CellStyle cellStyle = workbook.createCellStyle();
		Font cellFont = workbook.createFont();
		cellStyle.setFont(cellFont);
		// 生成一个(带标题)表格
		SXSSFSheet sheet = workbook.createSheet();
		// 设置列宽
		int minBytes = colWidth < DEFAULT_COLOUMN_WIDTH ? DEFAULT_COLOUMN_WIDTH : colWidth;// 至少字节数
		int[] arrColWidth = new int[headMap.size()];
		// 产生表格标题行,以及设置列宽
		String[] properties = new String[headMap.size()];
		String[] headers = new String[headMap.size()];
		int ii = 0;
		for (Iterator<String> iter = headMap.keySet().iterator(); iter.hasNext();) {
			String fieldName = iter.next();

			properties[ii] = fieldName;
			headers[ii] = headMap.get(fieldName);

			int bytes = fieldName.getBytes().length;
			arrColWidth[ii] = bytes < minBytes ? minBytes : bytes;
			sheet.setColumnWidth(ii, arrColWidth[ii] * 256);
			ii++;
		}
		// 遍历集合数据，产生数据行
		int rowIndex = 0;
		for (Object obj : jsonArray) {
			if (rowIndex == 65535 || rowIndex == 0) {
				if (rowIndex != 0)
					sheet = workbook.createSheet();// 如果数据超过了，则在第二页显示

				SXSSFRow titleRow = sheet.createRow(0);// 表头 rowIndex=0
				titleRow.createCell(0).setCellValue(title);
				titleRow.getCell(0).setCellStyle(titleStyle);
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headMap.size() - 1));

				SXSSFRow headerRow = sheet.createRow(1); // 列头 rowIndex =1
				for (int i = 0; i < headers.length; i++) {
					headerRow.createCell(i).setCellValue(headers[i]);
					headerRow.getCell(i).setCellStyle(headerStyle);

				}
				rowIndex = 2;// 数据内容从 rowIndex=2开始
			}
			JSONObject jo = (JSONObject) JSONObject.toJSON(obj);
			SXSSFRow dataRow = sheet.createRow(rowIndex);
			for (int i = 0; i < properties.length; i++) {
				SXSSFCell newCell = dataRow.createCell(i);

				Object o = jo.get(properties[i]);
				String cellValue = "";
				if (o == null)
					cellValue = "";
				else if (o instanceof Date)
					cellValue = new SimpleDateFormat(datePattern).format(o);
				else if (o instanceof Float || o instanceof Double)
					cellValue = new BigDecimal(o.toString()).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				else
					cellValue = o.toString();

				newCell.setCellValue(cellValue);
				newCell.setCellStyle(cellStyle);
			}
			rowIndex++;
		}
		// 自动调整宽度(暂时不用)

		// for (int i = 0; i < headers.length; i++) {
		// sheet.autoSizeColumn(i);
		// }

		try {
			workbook.write(out);
			workbook.close();
			workbook.dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Web 导出excel
	public static void downloadExcelFile(String title, Map<String, String> headMap, JSONArray ja,
			HttpServletResponse response) {
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ExcelExportUtil.exportExcelX(title, headMap, ja, null, 0, os);
			byte[] content = os.toByteArray();
			InputStream is = new ByteArrayInputStream(content);
			// 设置response参数，可以打开下载页面
			response.reset();

			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
			response.setHeader("Content-Disposition",
					"attachment;filename=" + new String((title + ".xlsx").getBytes(), "iso-8859-1"));
			response.setContentLength(content.length);
			ServletOutputStream outputStream = response.getOutputStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			BufferedOutputStream bos = new BufferedOutputStream(outputStream);
			// byte[] buff = new byte[8192];
			// int bytesRead;
			// while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
			// bos.write(buff, 0, bytesRead);
			//
			// }
			bis.close();
			bos.close();
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// io转base64
	public static String ioToBase64(InputStream in) throws IOException {
		String strBase64 = null;
		try {
			// in.available()//返回文件的字节长度
			byte[] bytes = new byte[in.available()];
			in.read(bytes);
			// 将文件中的内容读入到数组中
			strBase64 = new BASE64Encoder().encode(bytes); // 将字节流数组转换为字符串
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return strBase64;
	}

	public static MultipartFile base64ToMultipart(String base64) {
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] b = decoder.decodeBuffer(base64);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			return new BASE64DecodedMultipartFile(b, base64);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/** * 根据byte数组，生成文件 * @param bfile 文件数组 * @param filePath 文件存放路径 * @param fileName 文件名称 */ 
	public static void byte2File(byte[] bytes,String filePath,String fileName){
		BufferedOutputStream bos=null; 
		FileOutputStream fos=null; 
		File file=null; 
		try{ 
			File dir=new File(filePath); 
			if(!dir.exists() && !dir.isDirectory()){//判断文件目录是否存在 
				dir.mkdirs(); } 
				file=new File(filePath+fileName); 
				fos=new FileOutputStream(file); 
				bos=new BufferedOutputStream(fos); 
				bos.write(bytes); 
				} catch(Exception e){ 
				System.out.println(e.getMessage()); 
				e.printStackTrace(); 
				} 
		finally{
			try{ 
				if(bos != null){
					bos.close(); 
					} 
			if(
					fos != null){
				fos.close(); 
				} 
			} catch(Exception e){
				System.out.println(e.getMessage()); e.printStackTrace(); 
				} 
			} 
		}

}
