# Chap05 구성속성 이해하기
## 📖 Chap05.1 에서 배우는 내용  
#### 🏁 [목표] Spring JDBC 개념과 사용법 익히기
#### 🏁 [익혀야 되는 개념]
> 1) Spring 에서 JDBC를 이용하여 Database를 다루는 큰 그림
> 2) Plain JDBC와 Spring JDBC의 차이, Spring JDBC를 사용하는 이유?
> 3) Datasource의 Connection Pool 개념



## 5.1 자동-구성 세부 조정하기 
## 5.1.0 두가지 형태의 구성
스프링 에는 두가지 형태의 구성이 있다.<br>
@Bean 애노테이션이 지정된 메서드는 사용하는 빈의 인스턴스를 생성하고 속성 값도 설정한다.<br>

1) 빈 연결 : 스프링 애플리케이션 컨텍스트에서 빈으로 생성되는 애플리케이션 컴포넌트 및 상호 간에 주입되는 방법을 선언하는 구성
2) 속성 연결 : 스프링 애플리케이션 컨텍스트에서 빈의 속성 값을 설정하는 구성

```java
// 스프링에 내장된 H2 DB를 DataSource로 선언하는 다음의 @Bean
@Bean
public DataSource dataSource() {
    return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .addScript("schema.sql")
            .addScript("user_data.sql","ingredient_data.sql")
            .build();
}

```

만일 스프링 부트를 사용중이 아니라면 이 메서드(dataSource)를 통해 DataSource 빈을 구성할 수 있다.
하지만 스프링 부트를 사용 중일 때는 자동-구성이 DataSource 빈을 구성해준다.
스프링 부트는 런타임 시에 H2 의존성 라이브러리를 찾고 DataSource 빈을 자동으로 찾아 스프링 애플리케이션 컨텍스트에 생성한다.

## 5.1.1 스프링 환경 추상화 이해하기

## 5.1.2 데이터 소스 구성하기

## 5.1.3 내장 서버 구성하기 

## 5.1.4 로깅 구성하기 
- 대부분의 애플리케이션은 로깅을 제공한다.
- 기본적으로 스프링 부트는 콘솔에 로그 메시지를 쓰기 위해 Logback을 통해 로깅을 구성한다.이때 기본 로깅 수준은 INFO다.
- 로깅 구성을 제어할 때는 classpath의 루트에(src/main/resources) logback.xml 파일을 생성하면 된다.
```xml

<configuration>
    <appender name="STDOUT" class="ch.qos. logback.core.ConsoleAppender">
        <encoder>
            <pattern>
                %d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} – "ómsg%n
            </pattern>
        </encoder>
    </appender>
    <logger name="root" level="INFO"/>
    <root level="INFO">
        <appender-ref ref="STDOUT"/>
    </root>
</configuration>

```
-> 로깅구성에서 로깅 수준과 로그를 수록할 파일을 설정을 하는데, 스프링 부트의 구성 속성을 사용하면 logback.xml파일을 생성하지 않고 로그 설정을 변경할 수 있다.
- 로깅 수준을 설정할 때는 logging.level을 접두어로 갖는 속성들을 생성한다. 그리고 그다음에 로깅 수준을 설정하기 원하는 로거의 이름을 붙인다.

```
logging:
  file:
    path: /var/logs/
    name: TacoCloud.log
  level:
    root: WARN
    org.springframework.security: DEBUG
    
```

### Logging with SLF4J and Logback
일반적으로 서버가 같은 경우는 로깅을 통해 자기가 수행되는 과정을 기록을 해두는 작업이 필요하다. (로깅)
로깅을 할떄 사용하는 다양한 프레임워크가 있다. 
SLF4J와 Logback을 사용한다. 

Logging이란 어디다가 message에 기록하는 것이다. (console, files, database ..)
목적 : 디버깅을 위해, 사용자의 interaction을 기록하기 위해.

#### Logging vs System.out.println()
- logging 은 message마다 등급을 주어서 어느정도 레벨의 메시지만 출력할 수 있게 한다.
- 모든 log를 출력하면 너무 많이 나올 수 있으니깐 개발자가 기준을 사용함. (priority Level)
- 리소스를 많이 먹음. 
### Logging Frameworks 
java에서 사용하는 프레임워크
- java.util.logging - 잘 사용 x
- Log4J :  표준 형식이 이였지만 
- Logback : Log4J의 후계자, 많은 프로젝트에서 사용됨. 
- SLF4J : Simple Logging Facade for Java 

#### Facade pattern  : logger framework 앞 전면에 있는 패턴 
![image](https://user-images.githubusercontent.com/55049159/172027357-5674c168-fa04-4877-b435-6ee8b0e41722.png)
- 밑단의 api 를 학습해서 사용하기엔 번거롭기 떄문에 , 앞단의 뭔가를 두어서 클라이언트는 얘가 제공하는 인터페이스를 사용하면, Facade가 변경을 통해 호출해 준다. 
=> Facade가 제공하는 공통 api만 사용하면 내부적으로 알아서 변환해준다는 점. SLF4J가 그 역할을 해준다. 
- logback을 사용하다 log4J를 사용하고 싶으면 바꾸기만 하면됨.
- 컴파일 할 당시에 slf4j-api-1.7.32.jar 이 있어야됨.  


## 5.1.5 다른 속성의 값을 가져오기

다른 구성 속성들로부터 값을 가져올 수도 있다. 예를 들어 greeting.welcome 이라는 속성을 또 다른 속성인 spring.application.name의 값으로 설정하고 싶다고 해보자.

```
greeting:
  welcome: ${spring.application.name}
  
```

또한 다른 텍스트 속에 ${}를 포함시킬 수도 있다.

```
greeting:
  welcome: You are using ${spring.application.name}
  
```

이렇게 구성 속성을 사용해서 스프링 자체의 컴포넌트를 구성하면
해당 컴포넌트의 속성 값을 쉽게 주입할 수 있고 자동-구성을 세부 조정할 수 있다.


