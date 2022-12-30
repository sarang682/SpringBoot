package chap6.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ArticleVO {
	int article_no;
	int writer;
	String name;
	String title;
	String regdate;
	String moddate;
	int read_cnt;
}
