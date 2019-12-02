package ai.jobiak.lambda.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Builder.Default;

@Data
@Builder
@AllArgsConstructor
public class GenericRes<T> {

	@Default
	private boolean status = true;

	@Default
	private String code = "200";

	private String message, description;
	private T data;

}