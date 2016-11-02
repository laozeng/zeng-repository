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
		System.out.println("用时："+(endTime - startTime));
	}
	
	/**
	 * 获取指定目录之下的所有文件
	 * @param direct
	 * @param files
	 */
	public static void getFiles(File direct){
		for(File file:direct.listFiles()){
			if(file.isFile()){
				//如果是文件
				files.add(file);
			}else{
				//如果不是文件
				getFiles(file);
			}
		}
	}
}
