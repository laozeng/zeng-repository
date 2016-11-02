package com.shiro.controller;

import java.text.MessageFormat;

import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;

/**
 * �����û���̬������Ҫ���ص���Դ
 * �ο�·����http://www.360doc.com/content/14/0207/13/834950_350425926.shtml
 * @author user
 */
public class LoadPermission implements FactoryBean<Ini.Section>{
	
	private String filterChainDefinitions;
	//Ĭ��premission�ַ���
    public static final String PREMISSION_STRING="perms[\"{0}\"]";
    
    
    public Section getObject() throws BeansException {
        Ini ini = new Ini();
        //����Ĭ�ϵ�url
        ini.load(filterChainDefinitions);
        Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
        //ѭ��Resource��url,�����ӵ�section�С�section����filterChainDefinitionMap,
        //����ļ���������URL,ֵ���Ǵ���ʲô�������ܷ��ʸ�����
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
