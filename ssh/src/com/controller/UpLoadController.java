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
 * 使用两种方式实现多文件的上传
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
	
	@RequestMapping("/upLoad1.html")  //使用普通io流的方式实现多文件的上传
	public String upLoad1(@RequestParam("file") CommonsMultipartFile[] files,HttpServletRequest request){
		for (int i = 0; i < files.length; i++) {
			System.out.println("fileName---------->"+ files[i].getOriginalFilename());

			if (!files[i].isEmpty()) {
				int pre = (int) System.currentTimeMillis();
				try {
					// 拿到输出流，同时重命名上传的文件
					String fileName = new Date().getTime()+ files[i].getOriginalFilename();
					String realPath = request.getSession().getServletContext().getRealPath("/upload"); //获取web项目在服务器上的路径
					FileOutputStream os = new FileOutputStream(realPath +"/"+ fileName);
					// 拿到上传文件的输入流
					FileInputStream in = (FileInputStream) files[i].getInputStream();

					// 以写字节的方式写文件
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
					System.out.println("上传出错");
				}
			}
		} 
		return "redirect:/file/success.html";
	}
	
	@RequestMapping("/upLoad2.html") //使用springmvc自带的解析器进行多文件上传
	public String upLoad2(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 //创建一个通用的多部分解析器  
       CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());   
        //判断 request 是否有文件上传,即多部分请求  
        if(multipartResolver.isMultipart(request)){  
            //转换成多部分request    
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
            //取得request中的所有文件名  
            Iterator<String> iter = multiRequest.getFileNames();  
            int index = 0;
            while(iter.hasNext()){  
                //记录上传过程起始时的时间，用来计算上传时间  
                int pre = (int) System.currentTimeMillis();  
                //取得上传文件  
                MultipartFile file = multiRequest.getFile(iter.next());  
                if(file != null){  
                    //取得当前上传文件的文件名称  
                    String myFileName = file.getOriginalFilename();  
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
                    if(myFileName.trim() !=""){  
                        System.out.println(myFileName);  
                        //重命名上传后的文件名  
                        String fileName = "demoUpload" + file.getOriginalFilename();  
                        //定义上传路径  
                        String realPath = request.getSession().getServletContext().getRealPath("/upload"); //获取web项目在服务器上的路径
                        String path = realPath +"/"+ fileName;  
                        File localFile = new File(path);  
                        file.transferTo(localFile); 
                        fileNames[index] = fileName;
                        index++;
                    }  
                }  
                //记录上传该文件后的时间  
                int finaltime = (int) System.currentTimeMillis();  
                System.out.println(finaltime - pre);  
            }  
              
        } 
        return "redirect:/file/success.html";
	}
	
	@RequestMapping("/downLoad")  //该方法实现下载功能
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
