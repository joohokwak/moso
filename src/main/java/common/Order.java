package common;

import lombok.Getter;

@Getter
public enum Order {
	ASC("ASC"), 							// 오름차순
    DESC("DESC"), 							// 내림차순
    DESC_NULLS_FIRST("DESC NULLS FIRST"), 	// NULL을 먼저 정렬 (내림차순)
    DESC_NULLS_LAST("DESC NULLS LAST"), 	// NULL을 마지막에 정렬
    ASC_NULLS_FIRST("ASC NULLS FIRST"), 	// NULL을 먼저 정렬 (오름차순)
    ASC_NULLS_LAST("ASC NULLS LAST"); 		// NULL을 마지막에 정렬

	private String order;
	
	Order(String order) {
		this.order = order;
	}
}
