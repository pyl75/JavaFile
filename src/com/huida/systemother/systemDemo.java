package com.huida.systemother;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

/**
 * 
 * @author pengyunlong
 *������ѧϰSysem�����һЩ����
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
 * �����Ǽ�����������ʱ��
 */
	private static void method_2() {
		// TODO Auto-generated method stub
		long startTime = System.currentTimeMillis();
		//��������
		for(int i =0;i<10000;i++)
		{
			System.out.println("HelloWorld!");
		}
		//��ȡ����ʱ��
		long endTime = System.currentTimeMillis();
		System.out.println(endTime-startTime);
	}

	private static void method_1() {
		// TODO ѧSystem
		//alt+/��ʾ����
		/**
		 * System����û�й��췽�������ܴ�������
		 * 1�������ֶζ�����Static��ʶ��
		 */
		
		long time = System.currentTimeMillis();
		int day = (int)(1970+time/1000/60/60/24/365);
		System.out.println("time= "+time);
		System.out.println("time= "+day);
	}

}
