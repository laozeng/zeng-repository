package com.test;

import java.io.*;

/**
 * �ο�·����http://www.cnblogs.com/hoojo/archive/2011/09/30/2196767.html
 * @author user
 *
 */
public class DownThread {
	// �����ֽ����飨ȡˮ����Ͳ���ĳ���  
    private final int BUFF_LEN = 32;  
      
    // �������ص���ʼ��  
    private long start;  
      
    // �������صĽ�����  
    private long end;  
      
    // ������Դ��Ӧ��������  
    private InputStream is;  
      
    // �����ص����ֽ������raf��  
    private RandomAccessFile raf;  
  
      
    // ���������������������������������ʼ�㡢������  
    public DownThread(long start, long end, InputStream is, RandomAccessFile raf) {  
        // ������̸߳������ص��ֽ�λ��  
        System.out.println(start + "---->" + end);  
        this.start = start;  
        this.end = end;  
        this.is = is;  
        this.raf = raf;  
    }  
  
    public void run() {  
        try {  
            is.skip(start);  
            raf.seek(start);  
            // �����ȡ���������ݵĵĻ������飨��Ͳ��  
            byte[] buff = new byte[BUFF_LEN];  
            // ���̸߳���������Դ�Ĵ�С  
            long contentLen = end - start;  
            // ���������Ҫ��ȡ���ξͿ�����ɱ��̵߳�����  
            long times = contentLen / BUFF_LEN + 4;  
            // ʵ�ʶ�ȡ���ֽ���  
            int hasRead = 0;  
            for (int i = 0; i < times; i++) {  
                hasRead = is.read(buff);  
                // �����ȡ���ֽ���С��0�����˳�ѭ����  
                if (hasRead < 0) {  
                    break;  
                }  
                raf.write(buff, 0, hasRead);  
            }  
        } catch (Exception ex) {  
            ex.printStackTrace();  
        }  
        // ʹ��finally�����رյ�ǰ�̵߳��������������  
        finally {  
            try {  
                if (is != null) {  
                    is.close();  
                }  
                if (raf != null) {  
                    raf.close();  
                }  
            } catch (Exception ex) {  
                ex.printStackTrace();  
            }  
        }  
    }  
}
