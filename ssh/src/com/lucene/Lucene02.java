package com.lucene;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Lucene02 {
	private static List<File> files = new ArrayList<>();
	public static void main(String[] args) {
		File direct = new File("D:\\myeclipse");
		long startTime = System.currentTimeMillis();
		getFiles(direct);
		for (File file : files) {
			System.out.println("name:"+file.getName());
		}
		long endTime = System.currentTimeMillis();
		System.out.println("��ʱ��"+(endTime - startTime));
	}
	
	/**
	 * ��ȡָ��Ŀ¼֮�µ������ļ�
	 * @param direct
	 * @param files
	 */
	public static void getFiles(File direct){
		for(File file:direct.listFiles()){
			if(file.isFile()){
				//������ļ�
				files.add(file);
			}else{
				//��������ļ�
				getFiles(file);
			}
		}
	}
}
