package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatalogDTO {
	private int no;
	private String title;
	private String content;
	private String regdate;
	private int visitcount;
	private String ofile;
	private String nfile;
	
}
