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

	// 解析xml文件
	public void readXml(String path) throws Exception {
		// 创建解析器
		SAXReader reader = new SAXReader();
		// 获取整个dom文档
		Document document = reader.read(new File(path));
		// 获取xml根节点
		Element root = document.getRootElement();

		// 循环迭代根
		for (Iterator it = root.elementIterator(); it.hasNext();) {
			// 获取书节点对象
			Element books = (Element) it.next();
			// 再次循环迭代每本书，获取其子节点属性
			for (Iterator iterator = books.elementIterator(); iterator.hasNext();) {
				Element element = (Element) iterator.next();
				String nodeName = element.getName();
				String nodeValue = element.getText();
				if (nodeName.equals("id")) {
					System.out.print("图书编号：" + nodeValue);
				} else if (nodeName.equals("name")) {
					System.out.print("\t图书名称：" + nodeValue);
				} else if (nodeName.equals("price")) {
					System.out.print("\t图书价格：" + nodeValue);
				} else if (nodeName.equals("author")) {
					System.out.print("\t图书作者：" + nodeValue);
				} else {
					System.out.print("\t图书出版社：" + nodeValue + "\n\n");
				}
			}
		}
	}

	// 将文本写入指定的xml文件中
	public void write(String path) throws Exception{
		String text = "<?xml version='1.0' encoding='UTF-8'?><books><book><id>1</id><name>三国演义</name></book></books>"; 
		Document document = DocumentHelper.parseText(text);
		System.out.println("document:"+document.toString());
		//处理文本中有中文，并且使xml格式化
		OutputFormat format =OutputFormat.createPrettyPrint();
		format.setEncoding("GBK");//此处的编码方式应该与项目的编码方式一致方能不乱码
		XMLWriter writer = new XMLWriter(new FileWriter(path),format);
		writer.write(document);
		writer.close();
		System.out.println("写入文件成功！");
	}
}
