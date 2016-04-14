package com.huida.io;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterDemo {
	private static final String LINE_SEPARATOR=System.getProperty("line.separator");
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//1.创建出一个FileWriter对象流
		//FileWriter和abc.txt关联
		
		FileWriter fw=null;
		try {
			fw = new FileWriter("abc.txt");
			
			// 每次执行会重新创建覆盖之前的文件
			//往文件里写入字符串
			//其实是写入到该流的缓冲区里了
			fw.write("abcdef");
			//3.刷新 缓冲区里的的内容才真正的写入到文件里
			fw.flush();
			//fw.write("shit");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//4.关闭close中有flush 方法
			if(fw!=null)
			{
				try {
				fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
