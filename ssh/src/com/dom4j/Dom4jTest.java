package com.dom4j;

import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

@SuppressWarnings("all")
public class Dom4jTest {
	public static void main(String[] args) throws Exception {
		Dom4jTest dom4j = new Dom4jTest();
		dom4j.readXml("src/books.xml");
//		dom4j.write("src/oneBook.xml");
	}

	// ����xml�ļ�
	public void readXml(String path) throws Exception {
		// ����������
		SAXReader reader = new SAXReader();
		// ��ȡ����dom�ĵ�
		Document document = reader.read(new File(path));
		// ��ȡxml���ڵ�
		Element root = document.getRootElement();

		// ѭ��������
		for (Iterator it = root.elementIterator(); it.hasNext();) {
			// ��ȡ��ڵ����
			Element books = (Element) it.next();
			// �ٴ�ѭ������ÿ���飬��ȡ���ӽڵ�����
			for (Iterator iterator = books.elementIterator(); iterator.hasNext();) {
				Element element = (Element) iterator.next();
				String nodeName = element.getName();
				String nodeValue = element.getText();
				if (nodeName.equals("id")) {
					System.out.print("ͼ���ţ�" + nodeValue);
				} else if (nodeName.equals("name")) {
					System.out.print("\tͼ�����ƣ�" + nodeValue);
				} else if (nodeName.equals("price")) {
					System.out.print("\tͼ��۸�" + nodeValue);
				} else if (nodeName.equals("author")) {
					System.out.print("\tͼ�����ߣ�" + nodeValue);
				} else {
					System.out.print("\tͼ������磺" + nodeValue + "\n\n");
				}
			}
		}
	}

	// ���ı�д��ָ����xml�ļ���
	public void write(String path) throws Exception{
		String text = "<?xml version='1.0' encoding='UTF-8'?><books><book><id>1</id><name>��������</name></book></books>"; 
		Document document = DocumentHelper.parseText(text);
		System.out.println("document:"+document.toString());
		//�����ı��������ģ�����ʹxml��ʽ��
		OutputFormat format =OutputFormat.createPrettyPrint();
		format.setEncoding("GBK");//�˴��ı��뷽ʽӦ������Ŀ�ı��뷽ʽһ�·��ܲ�����
		XMLWriter writer = new XMLWriter(new FileWriter(path),format);
		writer.write(document);
		writer.close();
		System.out.println("д���ļ��ɹ���");
	}
}
