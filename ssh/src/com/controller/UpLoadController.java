package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.util.*;

@Controller
@RequestMapping("/file")
/**
 * ʹ�����ַ�ʽʵ�ֶ��ļ����ϴ�
 * @author user
 */
public class UpLoadController {
	
	private String[] fileNames = new String[3];
	
	@RequestMapping("/upLoadUI.html")
	public String upLoadUI(){
		return "test/upload";
	}
	
	@RequestMapping("/success.html")
	public ModelAndView success(){
		ModelAndView mav = new ModelAndView("test/success");
		mav.addObject("fileNames", fileNames);
		return mav;
	}
	
	@RequestMapping("/upLoad1.html")  //ʹ����ͨio���ķ�ʽʵ�ֶ��ļ����ϴ�
	public String upLoad1(@RequestParam("file") CommonsMultipartFile[] files,HttpServletRequest request){
		for (int i = 0; i < files.length; i++) {
			System.out.println("fileName---------->"+ files[i].getOriginalFilename());

			if (!files[i].isEmpty()) {
				int pre = (int) System.currentTimeMillis();
				try {
					// �õ��������ͬʱ�������ϴ����ļ�
					String fileName = new Date().getTime()+ files[i].getOriginalFilename();
					String realPath = request.getSession().getServletContext().getRealPath("/upload"); //��ȡweb��Ŀ�ڷ������ϵ�·��
					FileOutputStream os = new FileOutputStream(realPath +"/"+ fileName);
					// �õ��ϴ��ļ���������
					FileInputStream in = (FileInputStream) files[i].getInputStream();

					// ��д�ֽڵķ�ʽд�ļ�
					int b = 0;
					while ((b = in.read()) != -1) {
						os.write(b);
					}
					os.flush();
					os.close();
					in.close();
					int finaltime = (int) System.currentTimeMillis();
					System.out.println(finaltime - pre);
					fileNames[i] = (String) fileName;

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("�ϴ�����");
				}
			}
		} 
		return "redirect:/file/success.html";
	}
	
	@RequestMapping("/upLoad2.html") //ʹ��springmvc�Դ��Ľ��������ж��ļ��ϴ�
	public String upLoad2(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 //����һ��ͨ�õĶಿ�ֽ�����  
       CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());   
        //�ж� request �Ƿ����ļ��ϴ�,���ಿ������  
        if(multipartResolver.isMultipart(request)){  
            //ת���ɶಿ��request    
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
            //ȡ��request�е������ļ���  
            Iterator<String> iter = multiRequest.getFileNames();  
            int index = 0;
            while(iter.hasNext()){  
                //��¼�ϴ�������ʼʱ��ʱ�䣬���������ϴ�ʱ��  
                int pre = (int) System.currentTimeMillis();  
                //ȡ���ϴ��ļ�  
                MultipartFile file = multiRequest.getFile(iter.next());  
                if(file != null){  
                    //ȡ�õ�ǰ�ϴ��ļ����ļ�����  
                    String myFileName = file.getOriginalFilename();  
                    //������Ʋ�Ϊ����,˵�����ļ����ڣ�����˵�����ļ�������  
                    if(myFileName.trim() !=""){  
                        System.out.println(myFileName);  
                        //�������ϴ�����ļ���  
                        String fileName = "demoUpload" + file.getOriginalFilename();  
                        //�����ϴ�·��  
                        String realPath = request.getSession().getServletContext().getRealPath("/upload"); //��ȡweb��Ŀ�ڷ������ϵ�·��
                        String path = realPath +"/"+ fileName;  
                        File localFile = new File(path);  
                        file.transferTo(localFile); 
                        fileNames[index] = fileName;
                        index++;
                    }  
                }  
                //��¼�ϴ����ļ����ʱ��  
                int finaltime = (int) System.currentTimeMillis();  
                System.out.println(finaltime - pre);  
            }  
              
        } 
        return "redirect:/file/success.html";
	}
	
	@RequestMapping("/downLoad")  //�÷���ʵ�����ع���
	public void downLoad(String fileName,HttpServletResponse response,HttpServletRequest request) throws Exception{
		fileName = new String(fileName.getBytes("utf-8"),"iso-8859-1");
		response.setCharacterEncoding("utf-8");  
        response.setContentType("multipart/form-data");  
        response.setHeader("Content-Disposition", "attachment;fileName="+fileName);  
        try {  
        	String realPath = request.getSession().getServletContext().getRealPath("/");
            File file=new File(realPath+fileName);  
            InputStream inputStream=new FileInputStream(file);  
            OutputStream os=response.getOutputStream();
            byte[] b=new byte[inputStream.available()];  
            int length;  
            while((length=inputStream.read(b))>0){  
                os.write(b,0,length);  
            }  
            
            inputStream.close();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }
	}
}
