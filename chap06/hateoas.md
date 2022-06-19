> # 📋 Hypermedia Driven RESTful Web Services - Spring HATEOAS

## 💡 Introduction 
### ❓ Question 
- REST API도 LEVEL 이 존재 한다.
- HATEOAS란?  : 가장 높은 등급 
- HATEOAS를 JAVA에서 어떻게 구현할 것인가?

### 🏁 목표
- 1) HATEOAS 의 Concept
- 2) HATEOAS 의 구현

## 💡 REST maturity model : REST 성숙도 모델
![image](https://user-images.githubusercontent.com/55049159/120597342-e235cf00-c47f-11eb-96e5-54a1d9b34c32.png)
- level 0 ~ 3 | 이전까지 구현 한 것은 level 2 
- level 2 : Resouces +  연산을 하기 위해 HTTP method를 지정  (대부분의 프로젝트가 level2에 기반)
- level 1 : url 뒷 부분에 연산에 관해 넣어준다. 액션에 대해 별도의 URL 을 넣어준다. 
- level 3 : HATEOAS (level 2 + extra links to navigate through API) request는 차이가 없지만, 링크가 들어간다. 
- 완벽한 RESTful API를 구현하기 위해 HATEOAS를 고려해야 한다.

## 💡 What is HATEOAS?
- Hypermedia As The Engine Of Application State
- One of the constraints of the REST architecture
- Server response in the form of JSON+HAL
- Such a resource consists of two parts: 
1) data
2) links to actions that are possible to be performed 
   on a given resource
  ![image](https://user-images.githubusercontent.com/55049159/120597953-a818fd00-c480-11eb-9254-8d1dedce5e7d.png)
- Data content, Links 로 구분  
- level 2 에서는 Data content 까지만..
1) self : 이자체
2) direct_movies : 링크를 쫓아가면, 이 감독이 만든 링크로 이동
3) directors : 모든 감독에 대한 List 링크로 이동 

<hr>

### ✅  HATEOAS 란? 
- REST API 에서, HATEOAS는 응답으로서 Hypermedia를 보내주는데 HATEOAS는 데이터 정보 뿐 아니라, 연관된 정보도 함께 링크에 넣어서 보내준다. 
- HATEOAS principle client에게 the next potential steps에 대한 관련된 정보를 줘야 한다는 점. 

### ✅  HATEOAS 목적? 
- 높은 수준에서 클라이언트, 서버를 분리시키기 위함.
- 분리시킴으로써 각각 독립적으로 진화할 수 있도록 함.
- 서버를 고치면, 클라이언트가 변경되는 경우를 막음. 
- url이 변경되어도, client입장에서 이름을 통해 접근하기 때문에 클라이언트에서 영향이 없다.  

### HAL(Hypertext Application Language) Model

![image](https://user-images.githubusercontent.com/55049159/120600176-6b9ad080-c483-11eb-86fc-5e1a3d67a26e.png)
-  Links : relation / href 
