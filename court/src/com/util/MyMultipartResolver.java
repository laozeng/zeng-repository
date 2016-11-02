package com.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;
/**
 * 也就是因为这个配置,会让Spring MVC处理request对象,所以在使用KindEditor上传图片的时候,拿到的request都是空的.
	只需要自己写一个类继承CommonsMultipartResolver,重写isMultipart方法放过KindEditor上传图片的URL即可.
 * @author user
 */
public class MyMultipartResolver extends CommonsMultipartResolver{
	@Override
	public boolean isMultipart(HttpServletRequest request) {
		//放过该请求
		if(request.getRequestURL().toString().contains("/doUpload")){
			return false;
		}
		return super.isMultipart(request);
	}
}
