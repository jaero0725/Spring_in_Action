# 2.2 폼 제출 처리하기

## 2.2.1 @PostMapping을 사용하여 POST 요청 처리하기 


```java

@Slf4j	// 컴파일 시에 Lombok에 제공, Logger 제공 
@Controller	//컨트롤러로 식별, 컨텍스트의 빈으로 인스턴스를 자동생성
@RequestMapping("/design") 
public class DesignController {
    ...
    //form 처리 
    @PostMapping
    public String processDesign(Taco design) {
      log.info("Processing design : " + design);
      return "redirect:/orders/current"; 
    }
    ...
}
```

### redirect 
- String 값을 반환하고 종료하는 것과 달리 리디렉션 뷰를 나타내는 redirect: 는 /orders/current이 상대경로로 재접속되어야 한다. 

## 2.2.2 타코 주문 폼을 나타내는 컨트롤러 

```java
@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {

	@GetMapping("/current")
	public String orderForm(Model model) {
		model.addAttribute("order", new Order());
		return "orderForm";
	}

	@PostMapping
	public String processOrder(Order order) {	
		log.info("Order submitted: " + order);
		return "redirect:/";
	}
}
```

Lombok의 @Slf4j 에너테이션을 사용하면 컴파일시에 SLF4J Logger 객체를 생성할 수 있다. <br>
이 Logger는 제출한 주문의 상세 내역을 로그로 기록하기 위해 사용된다. <br>

- model.addAttribute ("order", new Order())을 통해 model에 저장된 Order객체를 "orderForm"의 view로 보내준다. 
- 위치 : /src/main/resources/template/orderForm.html 

#### Q. 어떻게 String만 넘겨주면 저 위치로 알아서 보내줄까? 
- 스프링의 뷰리졸버에서(SpringResourceTemplateResolver를 사용) prefix, suffix를 통해 view위치를 알아서 지정해줌. 
- 타임리프를 템플릿 엔진으로 스프링 빈에 등록하려면, 타임리프용 뷰 리졸버를 스프링 빈으로 등록해야 한다. (이부분 의존성 추가하면 알아서 해줌) *** 
- 
#### 설정을 명시적으로 해주려면 아래와 같이 application.properties 파일에 해줘도 된다.
> 사실 명시적으로 설정을 안해줘도 알아서 기본적으로 되어 있어 작동이 됨. 

참조 : https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#common-application-properties-templating
```java
# 참조경로
spring.thymeleaf.prefix=classpath:templates/	-- prefix
spring.thymeleaf.suffix=.html  	                -- suffix

# templates 디렉토리에 파일이 있는지 없는지 체크, 없으면 에러를 발생시킨다.
spring.thymeleaf.check-template-location=true   -- 위치에 template이 존재하느니 check를 할지 안할지 

spring.thymeleaf.mode=HTML5			-- 형식모드

# thymeleaf에 대한 캐시를 남기지 않는다. cache=false 설정(운영시는 true)
spring.thymeleaf.cache=false                    -- cache 사용할지 안할지
```

#### 아래와 같이 appplication.properties 설정 없이 java Config파일에 설정을 할 수도 있다.
```java
@Configuration
public class ThymeleafViewResolverConfig { 
    
    @Value("${thymeleaf.cache}") 
    private boolean isCache;
    
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver  templateResolver = new SpringResourceTemplateResolver ();
        templateResolver.setPrefix("classpath:templates/");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("LEGACYHTML5");
        templateResolver.setCacheable(isCache);
        return templateResolver;
    }
    
    @Bean
    public SpringTemplateEngine templateEngine(MessageSource messageSource) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setTemplateEngineMessageSource(messageSource);
        templateEngine.addDialect(layoutDialect());
        
        return templateEngine;
    }
    
    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }
 
    @Bean
    @Autowired
    public ViewResolver viewResolver(MessageSource messageSource) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine(messageSource));
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setOrder(0);
        return viewResolver;
    }
}
```

### 타임리프 특징
#### 1. 서버사이드 HTML 렌더링(SSR)
- 타임리프는 백엔드 서버에서 HTML을 동적으로 렌더링하는 용도로 사용됨.
- 사용법은 SSR이 다 비슷해 학습하기 어렵지 않고, 페이지가 정적이고 빠른 생산성이 필요한 경우 백엔드 개발자가 페이지개발할 일이 생길때 좋은 선택
#### 2. 네츄럴 템플릿
- 순수 HTML을 최대한 유지하려는 특징, JSP와 큰 차이점. 웹브라우저에서 직접 열어도 내용확인이 가능
#### 3. 스프링 통합 지원
- 스프링과 자연스럽게 통합되어 스프링의 다양한 기능을 쉽게 사용할 수 있다.
- SpringEL 문법 통합, 스프링 Valicdation, 오류 처리 등등 

https://www.thymeleaf.org/doc/tutorials/3.0/thymeleafspring.html#the-springstandard-dialect

