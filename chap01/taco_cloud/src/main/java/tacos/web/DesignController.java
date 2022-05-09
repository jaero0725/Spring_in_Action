package tacos.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Taco;

@Slf4j	// 컴파일 시에 Lombok에 제공, Logger 제공 
@Controller	//컨트롤러로 식별, 컨텍스트의 빈으로 인스턴스를 자동생성
@RequestMapping("/design") 
public class DesignController {
	
	@GetMapping	//HTTP GET 요청이 수신 될떄 요청을 처리하기 위해 메서드가 호출됨. 스프링4.3에서 소개됨. - 이전에는 @RequestMapping 사용
	public String showDesignForm(Model model) {
		
		//Ingredient 객체를 직접 주입함 -> 추후 DB사용
		List <Ingredient> ingredients = Arrays.asList(	
				  //Arrays.asList 사용법? 
				  new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
				  new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
				  new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
				  new Ingredient("CARN", "Carnitas", Type.PROTEIN),
				  new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
				  new Ingredient("LETC", "Lettuce", Type.VEGGIES),
				  new Ingredient("CHED", "Cheddar", Type.CHEESE),
				  new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
				  new Ingredient("SLSA", "Salsa", Type.SAUCE),
				  new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
				);
		
		Type [] types = Ingredient.Type.values();
		for(Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}
		
		model.addAttribute("taco", new Taco());
		
		return "design";
	}
	
	
	//form 처리 
	@PostMapping
	public String processDesign(Taco design) {
		log.info("Processing design : " + design);
		return "redirect:/orders/current";
	}
	
	//### Method ###
	//Type이 맞느지 필터링 해주는 함수 
	private List<Ingredient> filterByType(
			List<Ingredient> ingredients, Type type
			){
		
		//Stream 사용법 익히기 
		return ingredients
				.stream()
	            .filter(x -> x.getType().equals(type))
	            .collect(Collectors.toList());
	}
}
