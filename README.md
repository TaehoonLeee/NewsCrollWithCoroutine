# IT News Crolling Application
> JSoup의 웹 크롤링을 이용한 네이버, 다음 IT뉴스 크롤링 어플.
- JSoup, Hilt, Dagger2, Glide, Coroutine, Room을 사용하여 제작한 어플입니다.  
- MVVM Architecture로 이루어진 어플입니다.  
- JSoup을 이용하여 네이버, 다음 뉴스를 크롤링하여 어플에서 보여줍니다.  
![스크린샷 2021-02-01 오후 8 35 37](https://user-images.githubusercontent.com/48707020/106454733-8c035000-64ce-11eb-8249-89173e52b813.png)  

## 목차  
- [사용 라이브러리](#사용-라이브러리)  
- [세부 기능](#세부-기능)  
        - [네이버 뉴스 메인 화면](#네이버-뉴스-메인-화면)  
        - [네이버 카테고리 별 뉴스 화면](#네이버-카테고리-별-뉴스-화면)  
        - [다음 뉴스 메인 화면](#다음-뉴스-메인-화면)  
        - [찜한 뉴스](#찜한-뉴스)
        - [뉴스 ](#뉴스-기사)  

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

### 찜한 뉴스
![스크린샷 2021-02-07 오전 12 20 56](https://user-images.githubusercontent.com/48707020/107122373-c3149f80-68da-11eb-87b2-b941db263565.png)
![스크린샷 2021-02-07 오전 12 21 33](https://user-images.githubusercontent.com/48707020/107122374-c576f980-68da-11eb-9f2a-5b55b7934290.png)
![스크린샷 2021-02-07 오전 12 21 47](https://user-images.githubusercontent.com/48707020/107122375-c6a82680-68da-11eb-849d-3efb531a9a47.png)
![스크린샷 2021-02-07 오전 12 21 59](https://user-images.githubusercontent.com/48707020/107122376-c740bd00-68da-11eb-8145-4e3f70480266.png)  
- 뉴스 기사를 왼쪽으로 밀어 나오는 하트 버튼을 클릭하여 찜할 수 있습니다.  
![스크린샷 2021-02-07 오전 12 24 07](https://user-images.githubusercontent.com/48707020/107122384-d889c980-68da-11eb-8fff-b288bcc7d6dc.png)
![스크린샷 2021-02-07 오전 12 24 21](https://user-images.githubusercontent.com/48707020/107122387-daec2380-68da-11eb-8f3e-63198543c2be.png)  
- 찜한 사를 왼쪽으로 밀어 나오는 쓰레기통 버튼을 클릭하여 목록에서 지울 수 있습니다.  

### 뉴스 기사  
![스크린샷 2021-02-01 오후 8 45 14](https://user-images.githubusercontent.com/48707020/106454740-8f96d700-64ce-11eb-81b0-a8076a779c3e.png)
![스크린샷 2021-02-01 오후 8 45 58](https://user-images.githubusercontent.com/48707020/106454743-902f6d80-64ce-11eb-9b8e-44abfc03ee21.png)  
- WebView를 통해 선택한 기사의 내용을 보여줍니다.
