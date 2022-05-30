# Chap04 스프링 시큐리티
## 📖 스프링시큐리티에 대해서  
#### 🏁 [목표] Spring Security의 동작방식을 익혀보자
#### 🏁 [익혀야 되는 개념]
> 1) Authorization, Authentication의 개념
> 2) Spring Securit 등장배경 
> 3) Spring Security 의 Filter Chain, DelegatingFilterProxy란?
> 4) Spring Security 동장방식(UserDetailService, AuthnticaionProvider등의 개념)
> 5) 참조 : CSRF란?, BCryptPasswordEncoder란?

### Spring Security의 동장방식
![SpringSecurity01](https://user-images.githubusercontent.com/55049159/170259315-e5e0b8b6-029b-4777-8a2a-43944598ae41.png)
- 사용자가 request(username, password)를 보낼 때
- Authenticationfilter 가 받아서, username과 Password와 관련된 Token을 생성한다. 
- 토큰 값을 AuthenticationManager가 받아,
- AuthenticationManager의 구현체인 AuthenticationProvider에게 넘긴다.(AuthenticationProvider은 여러 개 있을 수 있음) 
- AuthenticationProvider는 사용자가 보낸 password를 바탕으로 해서 PasswordEncoder를 통해서, Hashed password를 얻어낸다.
- 또한 AuthenticationProvider가 UserDetailsService를 사용하여 DB의 User, Role에 접근한다. 
- UserDetails의 password와 사용자가 넘겨준 password(Hashed password)를 바탕으로 하여 확인한다.
- 인증이 성공적으로 이루어지면, AuthenticationFilter안에 SecurityContext에 Authentication 정보를 저장하게 된다. 
 
 ### Authorization, Authentication의 개념
 
 ### CSRF란?
 
 ### BCryptPasswordEncoder란?
  
<a href="https://zeroco.tistory.com/101?category=871881">Spring Boot에서 Spring Security 설정하기 (in-memory) </a><br>
<a href="https://zeroco.tistory.com/101?category=871881">Spring Boot에서 Spring Security 설정하기 (Database) </a> <br>
<a href="https://github.com/jaero0725/FastCampusSpring/tree/main/HANSUNG_UNIV/hs_springSecurity"> 소스코드</a><br>
