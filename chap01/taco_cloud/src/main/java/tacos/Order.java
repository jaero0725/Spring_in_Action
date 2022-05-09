package tacos;

import lombok.Data;

//Spring Data Validation 처리 필요함. 

@Data
public class Order {

	private String name;
	private String street;
	private String city;
	private String state;
	private String zip;
	private String ccNumber;
	private String ccExpiration;
	private String ccCVV;

}