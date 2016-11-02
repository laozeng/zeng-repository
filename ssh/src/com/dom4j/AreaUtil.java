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
	
	//加载所有的省份
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
	
	//通过省份编号获取城市
	public List<City> getCitiesByProvinceId(Integer id){
		for(Province province:provinces){
			if(id.equals(province.getId())){
				//如果找到了就退出循环
				cities = province.list;
				break;
			}
		}
		return cities;
	}
	
	//通过城市编号获取乡镇
	public List<Town> getTownsByCityId(Integer id){
		for(City city:cities){
			if(id.equals(city.getId())){
				//如果找到就退出循环
				towns = city.list;
				return towns;
			}
		}
		return towns;
	}
	

	private static List<Province> getData(String path) throws Exception{
		AreaUtil areaUtil = new AreaUtil();
		// 创建解析器
		SAXReader reader = new SAXReader();
		// 获取整个dom文档
		Document document = reader.read(new File(path));
		// 获取xml根节点
		Element root = document.getRootElement();
		//装载所有的省份
		List<Province> list = new ArrayList<Province>();
		//此处根节点为area
		for (Iterator it = root.elementIterator() ; it.hasNext() ;) {
			//每个省份
			Element provinceEle =(Element) it.next();
			//省份名称
			String provinceName = provinceEle.attributeValue("province");
			//省份编号
			Integer provinceId = Integer.valueOf(provinceEle.attributeValue("provinceID"));
//			Province province = areaUtil.new Province(provinceId, provinceName);
			Province province = new Province(provinceId, provinceName);
			List<City> cityList = new ArrayList<City>();
			
			//循环迭代省份节点
			for(Iterator cityIt = provinceEle.elementIterator(); cityIt.hasNext();){
				//每个城市
				Element cityEle =(Element) cityIt.next();
				//城市名称
				String cityName = cityEle.attributeValue("City");
				//城市编号
				Integer cityId = Integer.valueOf(cityEle.attributeValue("CityID"));
				City city = new City(cityId, cityName);
				List<Town> townList = new ArrayList<Town>();
				
				for(Iterator townIt = cityEle.elementIterator(); townIt.hasNext();){
					//每个乡镇
					Element townEle =(Element) townIt.next();
					//乡镇名称
					String townName = townEle.attributeValue("Piecearea");
					//乡镇编号
					Integer townId = Integer.valueOf(townEle.attributeValue("PieceareaID"));
					Town town = new Town(townId, townName);
					//将乡镇添加到集合中
					if(town.getName() != "市辖区"){
						townList.add(town);
					}
				}
				
				//设置乡镇集合
				city.list = townList;
				//将乡镇加入到集合
				cityList.add(city);
			}
			
			//设置城市集合
			province.list = cityList;
			//将省份加到集合中
			list.add(province);
		}
		return list;
	}
}
