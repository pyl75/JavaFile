package com.huida.io;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterDemo {
	private static final String LINE_SEPARATOR=System.getProperty("line.separator");
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//1.������һ��FileWriter������
		//FileWriter��abc.txt����
		
		FileWriter fw=null;
		try {
			fw = new FileWriter("abc.txt");
			
			// ÿ��ִ�л����´�������֮ǰ���ļ�
			//���ļ���д���ַ���
			//��ʵ��д�뵽�����Ļ���������
			fw.write("abcdef");
			//3.ˢ�� ��������ĵ����ݲ�������д�뵽�ļ���
			fw.flush();
			//fw.write("shit");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//4.�ر�close����flush ����
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
