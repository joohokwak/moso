package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemReviewDTO {
	private int cnt;
	private int no;
	private String cate;
	private String title;
	private String writer;
	private String pass;
	private String regdate;
	private String question;
	private String answre;
	private String content;
	private int secrite;
	private int rating;
	private int itemno;
}
