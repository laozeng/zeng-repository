package com.design.create_model;

import java.util.ArrayList;
import java.util.List;

/**
 * 建造者模式(builder)测试
 * @author user
 */
public class BuilderTest {
	public static void main(String[] args) throws Exception {
		/*PrinterBuilder builder = new PrinterBuilder();
		builder.createColorPrinter(10);*/
		List<Printer> printers = PrinterBuilder.createColorPrinter(10);
		System.out.println(printers);
	}
}

//定义打印机接口
interface Printer{
	void print();
}

//定义彩色打印机实现类
class ColorPrinter implements Printer{
	@Override
	public void print() {
		System.out.println("ColorPrinter  print()....");
	}
}

//定义黑白打印机实现类
class WhitePrinter implements Printer{
	@Override
	public void print() {
		System.out.println("WhitePrinter  print()....");
	}
}

//定义建造者类
class PrinterBuilder{
	private static List<Printer> printers = new ArrayList<Printer>();
	
	//构造指定个数的彩色打印机
	public static List<Printer> createColorPrinter(int count){
		for(int i=0;i<count;i++){
			printers.add(new ColorPrinter());
		}
		return printers;
	}
	//构造指定个数的黑白打印机
	public static List<Printer> createWhitePrinter(int count){
		for(int i=0;i<count;i++){
			printers.add(new WhitePrinter());
		}
		return printers;
	}
}
