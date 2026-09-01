package SerializationDeserialization;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import CreateUserWithPOJOAndLombok.UserLombok;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//POJO Lombok
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class User {

	// Private Var
	@JsonInclude(Include.NON_NULL)
	private Integer id;
	private String name;
	private String email;
	private String gender;
	private String status;

}
