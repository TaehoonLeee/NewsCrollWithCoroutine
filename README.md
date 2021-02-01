# IT News Crolling Application
> JSoup의 웹 크롤링을 이용한 네이버, 다음 IT뉴스 크롤링 어플.
- JSoup, Hilt, Dagger2, Glide를 사용하여 제작한 어플입니다.  
- MVVM Architecture로 이루어진 어플입니다.  
- JSoup을 이용하여 네이버, 다음 뉴스를 크롤링하여 어플에서 보여줍니다.  
![스크린샷 2021-02-01 오후 8 35 37](https://user-images.githubusercontent.com/48707020/106454733-8c035000-64ce-11eb-8249-89173e52b813.png)  

## 목차  
- [사용 라이브러리](#사용-라이브러리)  
- [세부 기능](#세부-기능)  
        - [영화 목록](#영화-목록)  
        - [에러 처리](#에러-처리)  
        - [영화 세부정보](#영화-세부정보)  
        - [배우 세부 정보](#배우-세부-정보)  

## 사용 라이브러리  
> JSoup(1.13.1), Hilt(2.28-alpha), Glide(4.11.0)  

## 세부 기능
### 네이버 뉴스 메인 화면 
![스크린샷 2021-02-01 오후 8 35 37](https://user-images.githubusercontent.com/48707020/106454733-8c035000-64ce-11eb-8249-89173e52b813.png)  
- 네이버의 IT 헤드라인 뉴스를 보여줍니다.  

### 네이버 카테고리 별 뉴스 화면  
![스크린샷 2021-02-01 오후 8 44 57](https://user-images.githubusercontent.com/48707020/106454738-8e65aa00-64ce-11eb-963d-0b2a506f4ad0.png)  
- 네이버 뉴스 메인 화면에서 선택한 카테고리 별 뉴스를 보여줍니다.
        
### 다음 뉴스 메인 화면  
![스크린샷 2021-02-01 오후 8 45 42](https://user-images.githubusercontent.com/48707020/106454741-8f96d700-64ce-11eb-96ce-cd685ffdb237.png)  
- 다음의 IT 헤드라인 뉴스를 보여줍니다.

### 뉴스 기사  
![스크린샷 2021-02-01 오후 8 45 14](https://user-images.githubusercontent.com/48707020/106454740-8f96d700-64ce-11eb-81b0-a8076a779c3e.png)
![스크린샷 2021-02-01 오후 8 45 58](https://user-images.githubusercontent.com/48707020/106454743-902f6d80-64ce-11eb-9b8e-44abfc03ee21.png)  
- WebView를 통해 선택한 기사의 내용을 보여줍니다.
