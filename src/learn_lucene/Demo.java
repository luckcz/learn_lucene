package learn_lucene;

import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

public class Demo {
	String doc1 = "hello world";
	String doc2 = "hello java world";
	String doc3 = "hello lucene world";
	/**
	 *1.创建IndexWriter
	 *2.把要创建索引的文本数据放入Document对象的字段中
	 *3.通过IndexWriter把document进行写入
	 */
	private final String PATH = "D:/workspace/learn_lucene/index";
	@Test
	public void testCreateIndex() throws Exception {
		//1.创建IndexWriter
		Directory d = FSDirectory.open(Paths.get(PATH)); //索引目录
		IndexWriterConfig conf = new IndexWriterConfig(new SimpleAnalyzer());//对写入做配置
		IndexWriter indexWriter = new IndexWriter(d, conf);
		//2.把要创建索引的文本数据放入Document对象的字段中
		Document document1 = new Document();//行
		document1.add(new TextField("id", "1", Store.YES));//添加列
		document1.add(new TextField("title", "doc1", Store.YES));//添加列
		document1.add(new TextField("content", doc1, Store.YES));//添加列
		Document document2 = new Document();//行
		document2.add(new TextField("id", "2", Store.YES));//添加列
		document2.add(new TextField("title", "doc2", Store.YES));//添加列
		document2.add(new TextField("content", doc2, Store.YES));//添加列
		Document document3 = new Document();//行
		document3.add(new TextField("id", "3", Store.YES));//添加列
		document3.add(new TextField("title", "doc3", Store.YES));//添加列
		document3.add(new TextField("content", doc3, Store.YES));//添加列
		//3.通过IndexWriter把document进行写入
		indexWriter.addDocument(document1);//添加到缓冲区
		indexWriter.addDocument(document2);
		indexWriter.addDocument(document3);
		indexWriter.commit();
		indexWriter.close();
	}
	
	/**
	 *1.创建IndexSearcher
	 *2.创建Query对象
	 *3.使用IndexSearcher传入Query进行搜索
	 *4.从结果中获取documentId 再通过它获取document
	 *5.把document转换为我们想要的对象进行返回
	 * @throws Exception 
	 */
	@Test
	public void testSearch() throws Exception{
		//1.创建IndexSearcher
		Directory d = FSDirectory.open(Paths.get(PATH)); //索引目录
		IndexReader reader = DirectoryReader.open(d);//索引读入器
		IndexSearcher indexSearcher = new IndexSearcher(reader);//索引搜索器
		//2.创建Query对象-把特定格式字符串解析得到
		String parseStr = "content:lucene";
		Analyzer analyzer = new SimpleAnalyzer();//用它对搜索条件分词
		String defaultField = "content";//默认搜索字段，如果搜索字符串中没有指定，就使用默认的
		QueryParser parse = new QueryParser(defaultField, analyzer);
		Query query = parse.parse(parseStr);
		TopDocs topDocs = indexSearcher.search(query, 10000);//查询top n个数据
		System.out.println("总长命中数："+topDocs.totalHits);
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;//命中记录的数组
		for(ScoreDoc scoreDoc : scoreDocs){
			int docId = scoreDoc.doc;//获取文档编码
			//通过编号获取文档
			//5.把document转换为我们想要的对象进行返回
			Document document = indexSearcher.doc(docId);
			System.out.println("id--->"+document.get("id"));
			System.out.println("title--->"+document.get("title"));
			System.out.println("content--->"+document.get("content"));
		}
	} 

}
