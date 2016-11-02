package com.dom4j;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
@SuppressWarnings("all")
public class AreaUtil {
	public static void main(String[] args) throws Exception{
		
		URL url = ClassLoader.getSystemClassLoader().getResource("xml/ChinaArea.xml");
		System.out.println("url:"+url);
		
//		URL url = Thread.currentThread().getContextClassLoader().getResource("xml/ChinaArea.xml");
//		String path = url.toString();
//		path = path.substring(path.indexOf("/")+1);
//		System.out.println("path:"+path);
		
//		List<Province> list = getData("C:/Tomcat/webapps/socket/ChinaArea.xml");
//		for(Province province : list){
//			System.out.println(province);
//		}
//		List<City> list  = new AreaUtil().getCitiesByProvinceId();
	}
	
	public static List<Province> provinces = new ArrayList<Province>();
	public static List<City> cities = new ArrayList<City>();
	public static List<Town> towns = new ArrayList<Town>();
	
	//�������е�ʡ��
	public List<Province> getAllProvinces() throws Exception{
		if(provinces.size() == 0 ){
			URL url = Thread.currentThread().getContextClassLoader().getResource("xml/ChinaArea.xml");
			String path = url.toString();
			path = path.substring(path.indexOf("/")+1);
			System.out.println("path:"+path);
			provinces = getData(path);
		}
		List<Province> list = new ArrayList<Province>();
		for(Province province:provinces){
			Province  temp = new Province(province.getId(), province.getName());
			list.add(temp);
		}
		return list;
	}
	
	//ͨ��ʡ�ݱ�Ż�ȡ����
	public List<City> getCitiesByProvinceId(Integer id){
		for(Province province:provinces){
			if(id.equals(province.getId())){
				//����ҵ��˾��˳�ѭ��
				cities = province.list;
				break;
			}
		}
		return cities;
	}
	
	//ͨ�����б�Ż�ȡ����
	public List<Town> getTownsByCityId(Integer id){
		for(City city:cities){
			if(id.equals(city.getId())){
				//����ҵ����˳�ѭ��
				towns = city.list;
				return towns;
			}
		}
		return towns;
	}
	

	private static List<Province> getData(String path) throws Exception{
		AreaUtil areaUtil = new AreaUtil();
		// ����������
		SAXReader reader = new SAXReader();
		// ��ȡ����dom�ĵ�
		Document document = reader.read(new File(path));
		// ��ȡxml���ڵ�
		Element root = document.getRootElement();
		//װ�����е�ʡ��
		List<Province> list = new ArrayList<Province>();
		//�˴����ڵ�Ϊarea
		for (Iterator it = root.elementIterator() ; it.hasNext() ;) {
			//ÿ��ʡ��
			Element provinceEle =(Element) it.next();
			//ʡ������
			String provinceName = provinceEle.attributeValue("province");
			//ʡ�ݱ��
			Integer provinceId = Integer.valueOf(provinceEle.attributeValue("provinceID"));
//			Province province = areaUtil.new Province(provinceId, provinceName);
			Province province = new Province(provinceId, provinceName);
			List<City> cityList = new ArrayList<City>();
			
			//ѭ������ʡ�ݽڵ�
			for(Iterator cityIt = provinceEle.elementIterator(); cityIt.hasNext();){
				//ÿ������
				Element cityEle =(Element) cityIt.next();
				//��������
				String cityName = cityEle.attributeValue("City");
				//���б��
				Integer cityId = Integer.valueOf(cityEle.attributeValue("CityID"));
				City city = new City(cityId, cityName);
				List<Town> townList = new ArrayList<Town>();
				
				for(Iterator townIt = cityEle.elementIterator(); townIt.hasNext();){
					//ÿ������
					Element townEle =(Element) townIt.next();
					//��������
					String townName = townEle.attributeValue("Piecearea");
					//������
					Integer townId = Integer.valueOf(townEle.attributeValue("PieceareaID"));
					Town town = new Town(townId, townName);
					//��������ӵ�������
					if(town.getName() != "��Ͻ��"){
						townList.add(town);
					}
				}
				
				//�������򼯺�
				city.list = townList;
				//��������뵽����
				cityList.add(city);
			}
			
			//���ó��м���
			province.list = cityList;
			//��ʡ�ݼӵ�������
			list.add(province);
		}
		return list;
	}
}
