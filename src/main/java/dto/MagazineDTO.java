package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MagazineDTO {
	private int no;
	private String title;
	private String text;
	private String poster;
	private String content;
	private String regdate;
	private String mtype;
}
