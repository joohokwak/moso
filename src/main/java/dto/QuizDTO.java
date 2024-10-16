package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDTO {
	private int no;
	private String name;
	private String type;
	private String text;
	private int price;
	private String point;
	private String regdate;
	private String sizename;
	private String poster;
}
