package com.lucene;

import java.io.*;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
/*
 * lucene三大要素： 1.创建索引    2.搜索内容     3.分词
 * 搜索指定文件夹之下的文件中存在某个字符的文件
 */
public class Lucene01 {
	
	private static Directory directory = null;
	
	static{
		try {
			directory = FSDirectory.open(new File("d:/lucene/index01"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//创建索引
	public void createIndex() throws Exception{
		//创建Directory，索引所在的位置(索引所在的目录)
		Directory directory = FSDirectory.open(new File("d:/lucene/index01"));
		
		//创建IndexWriter  IndexWriter的作用是将索引写入到指定的目录之中   StandardAnalyzer为标准的分词分析器
		IndexWriter writer = new IndexWriter(directory,new IndexWriterConfig(Version.LUCENE_35, new StandardAnalyzer(Version.LUCENE_35)));
		
		//创建Document
		Document document = null;
		File dirFile = new File("D:/java web project/SSCF_P2P_V02/WebRoot/WEB-INF/jsp");//需要进行索引的文件夹，文件夹之下有txt文本文档
		for(File file:dirFile.listFiles()){
			document = new Document();
			//为Document添加Field    Document和Field的关系如同row和col的关系
			document.add(new Field("content", new FileReader(file)));//保存内容
			//Store.YES和Store.NO的含义：前者表示将内容存储在索引文件中，可以还原，如id，name等；
			//后者表示不把内容存储在索引文件中，不能还原，但是可以被索引
			document.add(new Field("fileName", file.getName(), Field.Store.YES,Field.Index.NOT_ANALYZED));//保存文件名称
			document.add(new Field("filePath", file.getAbsolutePath(), Field.Store.YES, Field.Index.NOT_ANALYZED));//保存文件路径
			//通过IndexWriter添加索引
			writer.addDocument(document);
		}
		//关闭indexWriter
		writer.close();
		System.out.println("完成写入!");
	}
	
	//开始搜索
	public void startSearch() throws Exception{
		//创建Document 打开索引所在的位置（前提是索引已经存在,前面的方法已经创建了索引）
		Directory directory = FSDirectory.open(new File("d:/lucene/index01"));
		//创建IndexReader 在指定的索引中读出需要的内容
		IndexReader reader = IndexReader.open(directory);
		//根据IndexReader创建IndexSearcher
		IndexSearcher searcher = new IndexSearcher(reader);
		//创建搜索Query
		  //创建parser来确定搜索的内容，第二个参数代表搜索域（创建索引的字段列）
		QueryParser parser = new QueryParser(Version.LUCENE_35, "content", new StandardAnalyzer(Version.LUCENE_35));
		Query query = parser.parse("serialize()");//要搜索的内容为java
		//根据searcher搜索返回TopDocs
		TopDocs tds = searcher.search(query, 10);//10代表条数(意思是说如果结果超过了10条，那么它最多只会显示10条)
		//总结果数
		System.out.println("tds.totalHits-->"+tds.totalHits);
		//根据TopDocs返回ScoreDoc
		ScoreDoc[] sds =  tds.scoreDocs;//此处ScoreDoc相当于ResultSet
		for (ScoreDoc sd : sds) {
			//根据ScoreDoc和searcher获取具体Document对象
			Document document = searcher.doc(sd.doc);//根据document的id来查询document对象
			//根据Document获取需要的值
			System.out.println("name:["+document.get("fileName")+"],path:["+document.get("filePath")+"]");
		}
		//关闭IndexSearcher
		searcher.close();
		//关闭IndexReader
		reader.close();
	}
	
	public static void main(String[] args) throws Exception{
		new Lucene01().createIndex();
		new Lucene01().startSearch();
	}
}
