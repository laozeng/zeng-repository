package com.design.create_model;

import java.util.ArrayList;
import java.util.List;

/**
 * ������ģʽ(builder)����
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

//�����ӡ���ӿ�
interface Printer{
	void print();
}

//�����ɫ��ӡ��ʵ����
class ColorPrinter implements Printer{
	@Override
	public void print() {
		System.out.println("ColorPrinter  print()....");
	}
}

//����ڰ״�ӡ��ʵ����
class WhitePrinter implements Printer{
	@Override
	public void print() {
		System.out.println("WhitePrinter  print()....");
	}
}

//���彨������
class PrinterBuilder{
	private static List<Printer> printers = new ArrayList<Printer>();
	
	//����ָ�������Ĳ�ɫ��ӡ��
	public static List<Printer> createColorPrinter(int count){
		for(int i=0;i<count;i++){
			printers.add(new ColorPrinter());
		}
		return printers;
	}
	//����ָ�������ĺڰ״�ӡ��
	public static List<Printer> createWhitePrinter(int count){
		for(int i=0;i<count;i++){
			printers.add(new WhitePrinter());
		}
		return printers;
	}
}
