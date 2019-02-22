package learn_lucene;

import java.nio.file.Paths;

import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

public class DemoDetailField {
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
		FieldType type = new FieldType();//字段类型
		type.setStored(true);//在数据库是否存储
		type.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);//该字段是否创建索引
		type.setTokenized(true);//创建索引是否分词
		Field field = new Field("content", doc1.getBytes(), type);
		//优秀实践：不会直接使用Field，要用它的子类
		//StringField：不分词字符串，地名等
		//TextField：分词，标题，内容
		document1.add(field);//添加列
		//3.通过IndexWriter把document进行写入
		indexWriter.addDocument(document1);//添加到缓冲区
		indexWriter.commit();
		indexWriter.close();
	}
}
