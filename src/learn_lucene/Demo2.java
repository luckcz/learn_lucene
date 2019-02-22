package learn_lucene;

/**
 * @project <learn_lucene> 
 * @company	杭州凯立通信有限公司
 * @author <cz>
 * @date <2019年2月14日下午3:15:41>
 * @description:此代码用的是luence3.0版本
 * 如果你还是Lucene3.2-3.6的用户，那么你只需要下载IK Analyzer 2012 U6版本。因为FF版本的API与3.x是不兼容的。 
 */
//如果需要分词中文结合就必须用ik分词器
public class Demo2 {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		String doc1 = "你好，迅雷世界";
		String doc2 = "你好 java 花河世界";
		String doc3 = "hello 全文检索 world";
		String PATH = "D:/workspace/learn_lucene/demo2_index";
			/*//1.创建indexWriter对象
			Directory d = FSDirectory.open(new File(PATH));
			//使用ik分词器，分词中文英文结合，参数默认是false，true代表大的范围搜索，小的不用搜索了
			IKAnalyzer analyzer = new IKAnalyzer(true);
			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_40,analyzer);
			IndexWriter indexWriter = new IndexWriter(d, config);
			//2.把要创建索引的文本数据放入Document对象的字段中
			Document document1 = new Document();//行
			FieldType type = new FieldType();//字段类型
			type.setStored(true);
			type.setTokenized(true);
			type.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
			document1.add(new Field("id", "2".getBytes(), type));//添加列
			document1.add(new Field("title", "doc1".getBytes(), type));//添加列
			document1.add(new Field("content", doc1.getBytes(), type));//添加列
			Document document2 = new Document();//行
			document2.add(new Field("id", "2".getBytes(), type));//添加列
			document2.add(new Field("title", "doc2".getBytes(),type));//添加列
			document2.add(new Field("content", doc2.getBytes(), type));//添加列
			Document document3 = new Document();//行
			document3.add(new Field("id", "3".getBytes(), type));//添加列
			document3.add(new Field("title", "doc3".getBytes(),type));//添加列
			document3.add(new Field("content", doc3.getBytes(), type));//添加列
			//将索引添加到indexWriter缓存中
			indexWriter.addDocument(document1);
			indexWriter.addDocument(document2);
			indexWriter.addDocument(document3);
			//提交
			indexWriter.commit();
			indexWriter.close();*/
	}
}
