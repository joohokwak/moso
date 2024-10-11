package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FaqDTO {
	private int no;
	private String title;
	private String cate;
	private String content;
	private String regdate;
}
