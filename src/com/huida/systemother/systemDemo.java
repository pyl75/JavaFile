package com.huida.systemother;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

/**
 * 
 * @author pengyunlong
 *此类是学习Sysem类里的一些东西
 */
public class systemDemo {


	public static void main(String[] args) throws IOException {
		//method_1();
		//method_2();
		//Method_3();
		method_5();
		method_4();
		method_6();
		
	}


	private static void method_6() {
		// TODO Auto-generated method stub
		Calendar c = Calendar.getInstance();
		Date d=c.getTime();
		System.out.println(d);
		long date = System.currentTimeMillis();
		Date d1 =new Date(date);
		System.out.println(d1);
		c.setTime(d1);
		System.out.println(c.getTime());
	}


	private static void method_5() {
		// TODO Auto-generated method stub
		
	}


	private static void method_4() {
		// TODO Auto-generated method stub
		
		Date d = new Date();
		DateFormat df = DateFormat.getDateInstance();
		//df = DateFormat.
	}


	private static void Method_3() throws IOException {
		getProperties();
		Runtime rt = Runtime.getRuntime();
		Process p=rt.exec("notepad.exe");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p.destroy();
	}
	
	
private static void getProperties() {
		// TODO Auto-generated method stub
		Properties prq= System.getProperties();
		System.out.println(prq);
		//String osName = System.getProperties("os.name");
	}


/**
 * 方法是计算程序的运行时间
 */
	private static void method_2() {
		// TODO Auto-generated method stub
		long startTime = System.currentTimeMillis();
		//程序运行
		for(int i =0;i<10000;i++)
		{
			System.out.println("HelloWorld!");
		}
		//获取结束时间
		long endTime = System.currentTimeMillis();
		System.out.println(endTime-startTime);
	}

	private static void method_1() {
		// TODO 学System
		//alt+/提示代码
		/**
		 * System首先没有构造方法，不能创建对象；
		 * 1，方法字段都是用Static标识的
		 */
		
		long time = System.currentTimeMillis();
		int day = (int)(1970+time/1000/60/60/24/365);
		System.out.println("time= "+time);
		System.out.println("time= "+day);
	}

}
