package com.shiro.controller;

import java.text.MessageFormat;

import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;

/**
 * 该类用户动态加载需要拦截的资源
 * 参考路径：http://www.360doc.com/content/14/0207/13/834950_350425926.shtml
 * @author user
 */
public class LoadPermission implements FactoryBean<Ini.Section>{
	
	private String filterChainDefinitions;
	//默认premission字符串
    public static final String PREMISSION_STRING="perms[\"{0}\"]";
    
    
    public Section getObject() throws BeansException {
        Ini ini = new Ini();
        //加载默认的url
        ini.load(filterChainDefinitions);
        Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
        //循环Resource的url,逐个添加到section中。section就是filterChainDefinitionMap,
        //里面的键就是链接URL,值就是存在什么条件才能访问该链接
        section.put("/shiro/deleteStudent.mvc",  MessageFormat.format(PREMISSION_STRING,"/shiro/deleteStudent.mvc"));
        section.put("/shiro/adminUI.mvc",  MessageFormat.format(PREMISSION_STRING,"/shiro/adminUI.mvc"));
        return section;
    }

	@Override
	public Class<?> getObjectType() {
		return this.getClass();
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	public String getFilterChainDefinitions() {
		return filterChainDefinitions;
	}

	public void setFilterChainDefinitions(String filterChainDefinitions) {
		this.filterChainDefinitions = filterChainDefinitions;
	}
}
