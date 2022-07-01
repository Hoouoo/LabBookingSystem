# LabBookingSystem
실습실 온라인 예약 시스템

## 개요

 해당 서비스는 동의대학교 컴퓨터 소프트웨어공학과 학생들에게 실습실 예약 서비스를 제공하는 것을 목적으로 하며, 강의실 이용을 체계적으로 할 수 있게 하도록 제공한다. 사용자는 웹페이지를 통해서 해당 서비스를 받을 수 있으며, 서비스 내용은 다음과 같다.


 특정 시간 이후 실습실 이용 불가 및 한정된 자리로 인하여 실습실을 사용이 제한적인 경우가 있었을 것이다.
 이러한 제한적인 상황을 해결하는데 실습실 예약 시스템을 제공함으로써 사용자의 시간과 에너지 절약을 지켜줄 수 있을 것으로 예상한다.


## 개발 환경
```
* RDBMS : MariaDB
* IDE : Intellij 2021.2.3 Ultimate Edition
* Web ApplicationServer : Spring Boot 2.5.5
* FrontEnd : JSP, Thymeleaf
```
## 사용 기술
Java, JSP, Spring Boot, Spring Data JPA, MariaDB, Docker, Google Cloude Platform,Git

## 서비스의 주요 기능은 다음과 같다  
1. 사용자 회원 관리 기능
2. 실습실 조회 기능
3. 실습실 예약 및 조회 기능
4. 실습실 예약 취소 기능
5. 문의 및 신고 기능
6. 관리자 회원 관리 기능
7. 관리자, 교수 강의실 설정기능
8. 관리자, 교수 사용자 제어 기능
9. 관리자 실습실 관리 기능
10. 사용자 알림 메세지 기능

## 설계
### ERD
![객체ERD](https://user-images.githubusercontent.com/73890228/173502442-286ff018-6212-48b7-bf5a-b50ff4f87d60.png)

### 유스케이스
#### 회원가입
![image](https://user-images.githubusercontent.com/73890228/173502530-69692897-74f1-4e15-a112-422b9f2bc02f.png)

## 실행 화면
### 사용자 메인 화면
![메인](https://user-images.githubusercontent.com/73890228/173502642-99ec5051-bdb7-4a40-a33b-10ebea5a42c7.PNG)

### 관리자 메인 화면
![관리자](https://user-images.githubusercontent.com/73890228/173502754-586b5d11-a799-4f10-bd48-f4e34685f29f.PNG)


### 예약 화면
![예약](https://user-images.githubusercontent.com/73890228/173502669-34520841-76c2-4101-b2f3-56e211ae2a01.PNG)

#### 예약 승인 화면
![승인](https://user-images.githubusercontent.com/73890228/173502693-d7f689ca-bff7-4c90-8903-61c71cd7315a.PNG)


#### 시간표 등록 화면
![시간표등록2](https://user-images.githubusercontent.com/73890228/173502935-40fd0daf-1cf6-4fcd-b6ea-a02d7dac5e1e.png)


## 시연 영상 및 발표 영상
[![객체지향설계 시연영상](https://user-images.githubusercontent.com/73890228/173502642-99ec5051-bdb7-4a40-a33b-10ebea5a42c7.PNG)](https://youtu.be/jVy62ODoLEk)
