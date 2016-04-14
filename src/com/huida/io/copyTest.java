package com.huida.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * ¸´ÖÆÎÄ¼þ
 * d:
 * @author pengyunlong
 *
 */
public class copyTest {

	public static void main(String[] args) throws Exception{
		//method_1();
		method_2();
	}

	private static void method_2() throws Exception{
		// TODO Auto-generated method stub
		FileReader fr = new FileReader("d:\\abc.txt");
		FileWriter fw = new FileWriter("e:\\haha.txt");
		char[] buffer = new char[4];
		int len;
		while((len = fr.read(buffer))!=-1)
		{
			fw.write(buffer,0,len);
		}
		fw.flush();
		fw.close();
		fr.close();
		
	}

	private static void method_1() throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		/*FileWriter fw = new FileWriter("d:\\abc.txt");
		fw.write("good good study");
		fw.flush();
		fw.close();*/
		
		FileReader fr = new FileReader("d:\\abc.txt");
		FileWriter fw = new FileWriter("e:\\haha.txt");
		int ch;
		while((ch=fr.read())!=-1)
		{
			fw.write(ch);
		}
		fw.flush();
		fr.close();
		fw.close();
	}

}
