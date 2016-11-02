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
 * lucene����Ҫ�أ� 1.��������    2.��������     3.�ִ�
 * ����ָ���ļ���֮�µ��ļ��д���ĳ���ַ����ļ�
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
	
	//��������
	public void createIndex() throws Exception{
		//����Directory���������ڵ�λ��(�������ڵ�Ŀ¼)
		Directory directory = FSDirectory.open(new File("d:/lucene/index01"));
		
		//����IndexWriter  IndexWriter�������ǽ�����д�뵽ָ����Ŀ¼֮��   StandardAnalyzerΪ��׼�ķִʷ�����
		IndexWriter writer = new IndexWriter(directory,new IndexWriterConfig(Version.LUCENE_35, new StandardAnalyzer(Version.LUCENE_35)));
		
		//����Document
		Document document = null;
		File dirFile = new File("D:/java web project/SSCF_P2P_V02/WebRoot/WEB-INF/jsp");//��Ҫ�����������ļ��У��ļ���֮����txt�ı��ĵ�
		for(File file:dirFile.listFiles()){
			document = new Document();
			//ΪDocument���Field    Document��Field�Ĺ�ϵ��ͬrow��col�Ĺ�ϵ
			document.add(new Field("content", new FileReader(file)));//��������
			//Store.YES��Store.NO�ĺ��壺ǰ�߱�ʾ�����ݴ洢�������ļ��У����Ի�ԭ����id��name�ȣ�
			//���߱�ʾ�������ݴ洢�������ļ��У����ܻ�ԭ�����ǿ��Ա�����
			document.add(new Field("fileName", file.getName(), Field.Store.YES,Field.Index.NOT_ANALYZED));//�����ļ�����
			document.add(new Field("filePath", file.getAbsolutePath(), Field.Store.YES, Field.Index.NOT_ANALYZED));//�����ļ�·��
			//ͨ��IndexWriter�������
			writer.addDocument(document);
		}
		//�ر�indexWriter
		writer.close();
		System.out.println("���д��!");
	}
	
	//��ʼ����
	public void startSearch() throws Exception{
		//����Document ���������ڵ�λ�ã�ǰ���������Ѿ�����,ǰ��ķ����Ѿ�������������
		Directory directory = FSDirectory.open(new File("d:/lucene/index01"));
		//����IndexReader ��ָ���������ж�����Ҫ������
		IndexReader reader = IndexReader.open(directory);
		//����IndexReader����IndexSearcher
		IndexSearcher searcher = new IndexSearcher(reader);
		//��������Query
		  //����parser��ȷ�����������ݣ��ڶ����������������򣨴����������ֶ��У�
		QueryParser parser = new QueryParser(Version.LUCENE_35, "content", new StandardAnalyzer(Version.LUCENE_35));
		Query query = parser.parse("serialize()");//Ҫ����������Ϊjava
		//����searcher��������TopDocs
		TopDocs tds = searcher.search(query, 10);//10��������(��˼��˵������������10������ô�����ֻ����ʾ10��)
		//�ܽ����
		System.out.println("tds.totalHits-->"+tds.totalHits);
		//����TopDocs����ScoreDoc
		ScoreDoc[] sds =  tds.scoreDocs;//�˴�ScoreDoc�൱��ResultSet
		for (ScoreDoc sd : sds) {
			//����ScoreDoc��searcher��ȡ����Document����
			Document document = searcher.doc(sd.doc);//����document��id����ѯdocument����
			//����Document��ȡ��Ҫ��ֵ
			System.out.println("name:["+document.get("fileName")+"],path:["+document.get("filePath")+"]");
		}
		//�ر�IndexSearcher
		searcher.close();
		//�ر�IndexReader
		reader.close();
	}
	
	public static void main(String[] args) throws Exception{
		new Lucene01().createIndex();
		new Lucene01().startSearch();
	}
}
