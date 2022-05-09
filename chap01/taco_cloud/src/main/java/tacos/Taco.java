package tacos;

import java.util.List;

import lombok.Data;

@Data
public class Taco {
	private String name;
	private List<String> ingredients;	//DB 사용안하려고 List로 영양소받아옴
}
