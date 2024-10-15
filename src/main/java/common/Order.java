package common;

import lombok.Getter;

@Getter
public enum Order {
	ASC("ASC"),
	DESC("DESC"),
	DESC_NULLS_FIRST("DESC NULLS FIRST"),
	DESC_NULLS_LAST("DESC NULLS LAST"),
	ASC_NULLS_FIRST("ASC NULLS FIRST"),
	ASC_NULLS_LAST("ASC NULLS LAST")
	;

	private String order;
	
	Order(String order) {
		this.order = order;
	}
}
