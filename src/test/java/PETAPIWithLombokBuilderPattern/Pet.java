package PETAPIWithLombokBuilderPattern;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/*{
	  "id": 101, //Seperate variable
	  "category": {   //Class and inside the class 2 variables
	    "id": 100,
	    "name": "puppy"
	  },
	  "name": "doggie",//Seperate variable
	  "photoUrls": [
	    "https://www.pet123.com",
	    "https://www.pet000.com"
	  ],
	  "tags": [ //tag is array and having key & value, so create seperate class because of having 2 variables.
	    {
	      "id": 100,
	      "name": "black"
	    },
	     {
	      "id": 101,
	      "name": "white"
	    }
	  ],
	  "status": "available"//Seperate variable
	}*/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Pet {

	private int id; //Seperate variable
	private String name; //Seperate variable
	private String status; //Seperate variable
	
	
	private List<String>photoUrls; //List of arrays
	private List<Tag>tag;
	private Category category;
	
	//1st inner class
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class Category{
		
		private int id;
		private String name;
		
	}
	
	//2nd inner class
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class Tag{
		
		private int id;
		private String name;
		
}
	
}
