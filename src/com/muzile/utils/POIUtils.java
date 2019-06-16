package com.muzile.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.muzile.bean.Result;

public class POIUtils {
	/**
	 * 
	 * @param titles String[] titles = {"序号","科目名称","成绩","学分","成绩所属学期"}
	 * @param list
	 */
	public static void createExcelFile(String[] titles ,List<Result> list,String date,String name){
		HSSFWorkbook wb = new HSSFWorkbook();
		FileOutputStream fos = null;
		HSSFSheet sheet = wb.createSheet("学生成绩表");
		
		//写入表头
		HSSFRow row = sheet.createRow(0);
		//定义表头宽度
		int[] columnWidth = {4000,4000,4000,4000,4000};
		HSSFCell cell = null;
	    for(int i = 0; i < titles.length; i++){
	        cell = row.createCell(i);
	        cell.setCellValue(titles[i]);
	        sheet.setColumnWidth(i, columnWidth[i]);
	    }
	    
	    // 写入内容
	    int i = 1;
	    for(Result res : list){
	        row = sheet.createRow(i);
	        //必须按照hderarNames的顺序来
	        row.createCell(0).setCellValue(res.getSid());//序号
	        row.createCell(1).setCellValue(res.getSname());//科目名称
	        row.createCell(2).setCellValue(res.getScore());//成绩
	        row.createCell(3).setCellValue(res.getGpa());//学分
	        row.createCell(4).setCellValue(res.getDate());//成绩所属学期
	        i++;
	    }
	    
	    //写入本地
	    try {
	    	String uuid = UUID.randomUUID().toString().replaceAll("-", "");
	    	File file = new File("c:/SchoolManageSys/excel/");
	    	if(!file.exists()){
	    		file.mkdirs();
	    	}
			fos = new FileOutputStream(new File("c:/SchoolManageSys/excel/成绩单_"+name+"_"+date+"_"+uuid+".xls"));
			wb.write(fos);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void createExcelFile(String[] titles ,List<Result> list){
		HSSFWorkbook wb = new HSSFWorkbook();
		FileOutputStream fos = null;
		HSSFSheet sheet = wb.createSheet("学生成绩表");
		
		//写入表头
		HSSFRow row = sheet.createRow(0);
		//定义表头宽度
		int[] columnWidth = {4000,4000,4000,4000,4000};
		HSSFCell cell = null;
	    for(int i = 0; i < titles.length; i++){
	        cell = row.createCell(i);
	        cell.setCellValue(titles[i]);
	        sheet.setColumnWidth(i, columnWidth[i]);
	    }
	    
	    // 写入内容
	    int i = 1;
	    for(Result res : list){
	        row = sheet.createRow(i);
	        //必须按照hderarNames的顺序来
	        row.createCell(0).setCellValue(res.getSid());//序号
	        row.createCell(1).setCellValue(res.getSname());//科目名称
	        row.createCell(2).setCellValue(res.getScore());//成绩
	        row.createCell(3).setCellValue(res.getGpa());//学分
	        row.createCell(4).setCellValue(res.getDate());//成绩所属学期
	        i++;
	    }
	    
	    //写入本地
	    try {
	    	String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			fos = new FileOutputStream(new File("c:/SchoolManageSys/excel/成绩单_"+uuid+".xls"));
			wb.write(fos);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}


