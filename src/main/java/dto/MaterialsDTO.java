package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaterialsDTO {
	private int no;
	private String title;
	private String txt;
	private String msize;
	private String poster;
	private String ofile;
}
