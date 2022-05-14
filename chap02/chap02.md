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

- redirect : String 값을 반환하고 종료하는 것과 달리 리디렉션 뷰를 나타내는 redirect: 는 /orders/current이 상대경로로 재접송되어야 한다. 


