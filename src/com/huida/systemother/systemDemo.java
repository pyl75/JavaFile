package com.huida.systemother;

import java.io.IOException;
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
		Method_3();
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
