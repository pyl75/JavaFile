package com.huida.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderDemo {

	public static void main (String[] args) throws Exception{
		//method_1();
		method_2();
	}

	private static void method_2()throws Exception {
		// TODO Auto-generated method stub
		 FileReader fr = new FileReader("abc.txt");
		char[] buffer = new char[4];
		int count = fr.read(buffer);
		System.out.println(count);
		int count1 = fr.read(buffer);
		System.out.println(count1);
	}

	private static void method_1() {
		// TODO Auto-generated method stub
		try {
			//文件必须要有，学到后面才会判断文件是否存在。
			FileReader fr =new FileReader("abc.txt");
			int ch;
			while((ch=fr.read())!=-1)
			{
				char c = (char) ch;
				System.out.println(c);
			}

		}
		catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
