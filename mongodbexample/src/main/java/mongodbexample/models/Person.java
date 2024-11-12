package mongodbexample.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.annotation.Generated;

@Document(collection="person")
public class Person {
	
	@Id
	private int id;
	private String name;
	private String city;
	private String college;
}
