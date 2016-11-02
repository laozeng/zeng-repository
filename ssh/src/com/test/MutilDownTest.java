package com.test;

import java.io.*;
import java.net.*;

public class MutilDownTest {
	public static void main(String[] args) {  
        //���弸���߳�ȥ����  
        final int DOWN_THREAD_NUM = 4;  
        final String OUT_FILE_NAME = "d:\\demo.mp4";  
        InputStream[] isArr = new InputStream[DOWN_THREAD_NUM];  
        RandomAccessFile[] outArr = new RandomAccessFile[DOWN_THREAD_NUM];  
        try {  
            // ����һ��URL����  
            URL url = new URL("http://gvglh.com/upload/store_model/fbb5d452-4ca2-4ddd-b16d-15f851fa3214.mp4");  
            // �Դ�URL����򿪵�һ��������  
            isArr[0] = url.openStream();  
            long fileLen = getFileLength(url);  
            System.out.println("������Դ�Ĵ�С" + fileLen);  
            // ������ļ���������һ��RandomAccessFile�����  
            //�������ж�ȡ��������д�루��ѡ���������ȡ�ļ�������һ���������ļ������ڶ��������ǣ�����ָ�����Դ��ļ��ķ���ģʽ  
            //"rw"�����ǿɶ���д��  
            outArr[0] = new RandomAccessFile(OUT_FILE_NAME, "rw");  
            // ����һ����������Դ��ͬ��С�Ŀ��ļ�  
            for (int i = 0; i < fileLen; i++) {  
                outArr[0].write(0);  
            }  
            // ÿ�߳�Ӧ�����ص��ֽ���  
            long numPerThred = fileLen / DOWN_THREAD_NUM;  
            // ����������Դ������ʣ�µ�����ȡģ  
            long left = fileLen % DOWN_THREAD_NUM;  
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < DOWN_THREAD_NUM; i++) {  
                // Ϊÿ���̴߳�һ����������һ��RandomAccessFile����  
                // ��ÿ���̷ֱ߳���������Դ�Ĳ�ͬ���֡�  
                //isArr[0]��outArr[0]�Ѿ�ʹ�ã��Ӳ�Ϊ0��ʼ  
                if (i != 0) {  
                    // ��URL�򿪶��������  
                    isArr[i] = url.openStream();  
                    // ��ָ������ļ��������RandomAccessFile����  
                    outArr[i] = new RandomAccessFile(OUT_FILE_NAME, "rw");  
                }  
                // �ֱ���������߳�������������Դ  
                if (i == DOWN_THREAD_NUM - 1) {  
                    // ���һ���߳�����ָ��numPerThred+left���ֽ�  
                    new DownThread(i * numPerThred, (i + 1) * numPerThred  
                            + left, isArr[i], outArr[i]).run();  
                } else {  
                    // ÿ���̸߳�������һ����numPerThred���ֽ�  
                    new DownThread(i * numPerThred, (i + 1) * numPerThred,  
                            isArr[i], outArr[i]).run();  
                }  
            }
            long endTime = System.currentTimeMillis();
            System.out.println("��ʱ��"+(endTime - startTime) / 1000 + "s");
        } catch (Exception ex) {  
            ex.printStackTrace();  
        }  
    }  
  
    // �����ȡָ��������Դ�ĳ��ȵķ���  
    public static long getFileLength(URL url) throws Exception {  
        long length = 0;  
        // �򿪸�URL��Ӧ��URLConnection  
        URLConnection con = url.openConnection();  
        // ��ȡ����URL��Դ�ĳ���  
        long size = con.getContentLength();  
        length = size;  
        return length;  
    }  
}
