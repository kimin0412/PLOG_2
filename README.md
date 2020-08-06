﻿# Sub pjt2

## day 01

(2020-07-20)



Initial Commit

- 브랜치 생성을 위해 일단 README 작성 후 commit



branch : master > develop > wireframe

- 와이어프레임 ver1 제작
  - 매터모스트로 자료 공유(wireframe폴더 내 자료)
  - 구조
    - 00 서비스/프로젝트 소개
    - 01 메인페이지
    - 02 주요 기능 관련 페이지
    - 03 회원 관련 페이지



## day 02

(2020-07-21)

branch : master > develop > feature-main

- 브랜치 연습



오프라인 수업일

- 팀 회의
  - 주제 확정, 개발 환경 설정
  - 와이어프레임 수정
  - 역할 분담
  - git 관련 rule 설정
- git 연습
  - 브랜치 만들어서 merge하는 연습



## day 03

(2020-07-22)

- JIRA 생성
  - 우리 프로젝트를 위한 커스텀 명세 초안 제작
  - 해당 초안에 따라 JIRA에 에픽, 스토리, sub-tast 생성

- 각자 맡은 파트 개발
  - 민지 : 뷰 화면에 editor 구현(화면 + DB저장)
  - 혜민 : 뷰 구조 잡고 화면 구성(라우터, 뷰티파이)
  - 지식 : 회원가입(Token 활용) 구현 중 
  - 민섭 : DB와 스케줄기능(캘린더) 연동 중
  - 민재 : POST CRUD 기능 구현 중

- 팀회의(컨설턴트님 + 코치님)
  - '핵심'이 부족하다
    - 아이덴티티 등 핵심이라고 내세울 수 있는 게 필요할 듯
  - 유저들이 사용할 만한 기능에 집중할 필요
    - 안 쓸 기능에 목메지 말고, 쓸만한거 구현
    - 특히, 내보내기 기능
  - 아이디어
    - JIRA처럼 시각화 해주기
    - 워드클라우드처럼 필기 분석해주기
  - 정리
    - 내가 못하는 걸 대신 해줄 수 있는 서비스!




## day 04

(2020-07-23)

- 오늘의 과제 : 최소한 프론트까지는 업데이트 하기

  - 일단 프론트 자체를 배포하는 것 까지만 완료
  - 젠킨스와 연동하다가 master 로 push event 발생하면 바로 build 되도록 설정하는 중에, 젠킨스 폴더가 우분투 내에 생성되지 않는 문제로 에러

  - 백엔드, DB는 아직

- 오늘의 개발 로그

  - (오늘 오후시간엔 웹엑스로 팀 전체 배포에 참여)
  - 민지 : 배포(진짜 고생 많이했어요 :) )
  - 혜민 : 프론트 명세 구체화, 프론트 화면 구현
  - 지식 : 회원 토큰 활용, 뷰와 연동시키는 부분 개발 중
  - 민섭 : DB, 스케쥴 관련 개발
  - 민재 : post CRUD(상황에 따라 DB활용하는 부분)



## day 05

(2020-07-24)

- 오늘의 개발로그

  - 오전 : 프론트 배포 관련해서, 다시 git pull 하는 과정을 통해 화면 업데이트

    - 에러 뜬 것(sudo 입력 안해서였음)

    - 처음에 에러 왜 뜨는지 모르고 master브랜치를 develop내용을 pull 한뒤 push 했더니, 조금 꼬였음(현재 HEAD > develop 가리키고 있는 상황)

  - 오후

    - 민지 : 에디터 화면에 구현 한 뒤, 프론트 코드와 합침
    - 혜민 : 회원 관련 화면 구현, 글쓰기 화면의 chips 관련 구현, 백엔드 코드와 일부 합침
    - 지식 : 회원 기능 구현할 때 토큰사용 부분 공부하며 구현 중(뷰와의 데이터 교환 포함)
    - 민섭 : DB관련 ERD수정, 프론트와 백으로 스케쥴 CRUD 중 C 구현 성공
    - 민재 : POST CRUD 계속 진행중, DB관련 ERD 수정 계속 진행 중...



- 다음 주에 해야 할 것
  - 필수
    - 백엔드, DB 배포
    - 프론트 화면 구성 완료
    - 백엔드 파트 완료
    - 프론트 + 백엔드 연동시키기
      - 뷰 라우터, axios 활용법 공부 필요
    - 반응형 페이지 구현
      - 일단 시간 날때 조금씩 구현 중
  - 추가
    - 글 내보내기 기능
    - 우리 서비스에 추가할 만한 핵심 기능(정체성을 결정해줄..?!)



## day 06-07

(2020-07-25 ~ 2020-07-26)



- 주말
  - 반응형(작은 화면) 일부 구현
  - 로그인 시큐리티 관련 작업



## day 08

(2020-07-27)

- 오늘의 개발로그
  - 오전
    - 백엔드 배포 전 DB정리 작업
    - 프론트와 연계 일부 시도(스케쥴)
  - 오후
    - 민지 : 백엔드 빌드, DB정리, POST 불러오기 관련 작업
    - 혜민 : 프론트 화면 구조 계속 잡아나감(+반응형), 네비게이션바 수정
    - 지식 : 로그인 시큐리티 계속 진행
    - 민섭 : 스케쥴 파트 CRUD 구현 후 프론트와 연계 작업(크.. 수고하셨어요)
    - 민재 : POST CRUD관련 작업
- 오늘 & 내일 할 것
  - 수요일 전까지 일단 백엔드, DB 배포
  - 수요일부터 본격적으로 프론트+백엔드 코드 합친 뒤 요청 처리 시작
  - 프론트 : 화면 구조나 필요한 페이지 작업 해놓기
  - 백엔드 : CRUD & account 계속 작업
  - 지라! 스토리 포인트 업데이트 하기



## day 09

(2020-07-28)

- 오늘의 개발로그
  - 민지 : POST CRUD, 마크다운 에디터 형식으로 보이게
  - 혜민 : 필요한 페이지 구성 대부분 완료, 라우터 활용 공부
  - 지식 : 로그인(구글api활용 O, grdle > maven 하는 중)
  - 민섭 : 스케쥴 CRUD 프론트와 연동 확인(로컬에서)
  - 민재 : 면접( 화이팅 :) 
- 오늘 & 내일 할 것
  - 준비
    - 라우터 활용
    - 상세보기
    - 로그인 관련 처리
  - 내일
    - DB 하나로 정리
    - 백엔드 배포
    - 프론트와 연결
    - 등등 할게 많음...



## day 10

(2020-07-29)

- 오늘의 개발 로그
  - 민지 : 배포 (잘했어요 :) 최고 )
  - 혜민 : 프론트 화면 만들고, 앞단에서 처리하는 로직 구현
  - 지식 : 회원 관련 구현
  - 민섭 : 스케쥴 CRUD, 해쉬태그 기능 시작
  - 민재 : POST CRUD, 해쉬태그 기능 함께 시작
- 내일 할 것
  - PT 발표 관련 정리
  - 로그인, 로그아웃 구현 후 세션 유지 하도록 코드 작성
  - 최소한의 기능 정리해 배포까지



## day 11

(2020-07-30)

- 오늘의 개발 로그
  - 민지
    - 스케쥴, POST, 해시태그 CRUD 등 백단 최종 점검
    - 최종 배포(merge)
  - 혜민
    - 최종 와이어프레임
    - PT자료 제작
    - 프론트 최종 점검 
  - 지식
    - 기능테스트 수행, 제출
    - 회원 관련 기능(로그인, 로그아웃)
      - 로그인은 성공(200)
      - 로그아운 CORS 에러 수정 중
  - 민섭
    - 스케쥴, POST, 해시태그 CRUD등 백단 최종 점검
    - PT 준비, 발표
  - 민재
    - 스케쥴, POST, 해시태그 CRUD등 백단 최종 점검
    - 검색 기능 백단과 연결해서 구현



## day 12
 
