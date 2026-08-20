package CreateUserPOJOLombokUsingBuilerPattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


//POJO Lombok
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLombok {
	
	//Private Var
	String name;
	String email;
	String gender;
	String status;
}
