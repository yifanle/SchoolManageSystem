package com.muzile.utils;

import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.muzile.bean.User;

public class AnalysisUtils
{
	private static String path = "c:/SchoolManageSys/charts/";
   private static  File file = new File(path);
   public static String creatLineChartImage(String chartTitle,String yTitle,String xTitle,List<Double> list) throws Exception{
	   delete();
	   String path = "c:/SchoolManageSys/charts/LineChart"+UUID.randomUUID()+".jpeg";
	   DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();
	   for(int i = 1;i<=list.size();i++){
		   line_chart_dataset.addValue( list.get(i-1) , "Average Score" ,"第"+i+"学期" );
	   }

	      JFreeChart lineChartObject = ChartFactory.createLineChart(
	    		  chartTitle,yTitle,
	    		  xTitle,
	         line_chart_dataset,PlotOrientation.VERTICAL,
	         true,true,false);
	      
	      charset(chartTitle,lineChartObject);
	      int width = 640; /* Width of the image */
	      int height = 480; /* Height of the image */ 
	     
	      if(!file.exists()){
	    		file.mkdirs();
	      }
	      File lineChart = new File(path); 
	      ChartUtilities.writeChartAsJPEG(new FileOutputStream(lineChart), lineChartObject, width, height);
	      return path;
   }
   
   public static String creatPieChartImage(String chartTitle,double value) throws IOException{
	   delete();
	   String path = "c:/SchoolManageSys/charts/PieChart"+UUID.randomUUID()+".jpeg";
	   DefaultPieDataset dataset = new DefaultPieDataset( );
	   dataset.setValue("fail", value );
	   dataset.setValue("pass",new Double(1-value));

	      JFreeChart chart = ChartFactory.createPieChart(
	    	 chartTitle, // chart title
	         dataset, // data
	         true, // include legend
	         true,
	         false);
	      int width = 640; /* Width of the image */
	      int height = 480; /* Height of the image */ 
	      if(!file.exists()){
	    		file.mkdirs();
	      }
	      File pieChart = new File(path); 
	      ChartUtilities.writeChartAsJPEG(new FileOutputStream(pieChart), chart, width, height);
	      return path;
   }
   
   public static String creatBarChartImage(String title , String xtitle ,String ytitle,List<User> list) throws IOException{
	   delete();
	   String path = "c:/SchoolManageSys/charts/BarChart"+UUID.randomUUID()+".jpeg";
	   DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	   for(User user : list){
		   dataset.addValue( user.getAscore() , "average" , user.getName());
	   }

	   JFreeChart barChart = ChartFactory.createBarChart(
			   title, 
			   xtitle, ytitle, 
	         dataset,PlotOrientation.VERTICAL, 
	         true, true, false);
	   charset(title,barChart);
	   int width = 640; /* Width of the image */
	   int height = 480; /* Height of the image */ 
	      if(!file.exists()){
	    		file.mkdirs();
	      }
	   File BarChart = new File(path); 
	   ChartUtilities.saveChartAsJPEG( BarChart , barChart , width , height );

	   return path;
   }
   
   private static void charset(String title,JFreeChart chart){
	   chart.setTitle(new TextTitle(title, new Font("隶书", Font.BOLD, 25)));
	   CategoryPlot plot = chart.getCategoryPlot();
	   CategoryAxis domainAxis = plot.getDomainAxis();
	   domainAxis.setVisible(true);
	   plot.setDomainAxis(domainAxis);
	   ValueAxis rAxis = plot.getRangeAxis();
	      
	   domainAxis.setTickLabelFont(new Font("宋体",Font.PLAIN,15)); //设置X轴坐标上的文字       
	   domainAxis.setLabelFont(new Font("宋体",Font.PLAIN,15)); //设置X轴的标题文字        
	              
	   rAxis.setTickLabelFont(new Font("宋体",Font.PLAIN,15)); //设置Y轴坐标上的文字
	   rAxis.setLabelFont(new Font("宋体",Font.PLAIN,15)); 
   }

   private static void delete(){
	   String[] names = file.list();
	   for(int i = 0;i<names.length;i++){
		   File delFile = new File(path+names[i]);
		   if(delFile.exists())
			   delFile.delete();
	   }
   }
   
}

