# Laragon + PHP 회원가입 및 로그인 시스템 문서
![image](https://github.com/user-attachments/assets/a7958028-39b7-4da1-ab41-e8dd1d314d7d)

이 문서는 Laragon 환경에서 PHP와 MySQL을 이용한 회원가입 및 로그인 시스템의 구조와 각 파일의 역할을 설명합니다.

## 📁 프로젝트 구조

```
/루트 디렉토리
│
├── index.php            # 초기 진입 페이지 (로그인/회원가입 링크)
├── register.php         # 회원가입 입력 폼 페이지
├── create_user.php      # 회원가입 처리 로직
├── login.php            # 로그인 입력 및 처리 페이지
├── home.php             # 로그인 후 유저 홈 페이지
├── functions.php        # 공통 함수 정의 파일
├── style.css            # 스타일 시트
├── admin_profile.png    # 관리자 프로필 이미지
└── user_profile.png     # 일반 사용자 프로필 이미지
```

---

## 📄 index.php

* 초기 접속 시 보여지는 페이지
* 로그인과 회원가입 링크 제공

## 📄 register.php

* 사용자가 이름, 이메일, 패스워드, 사용자 유형 등을 입력할 수 있는 폼 제공
* 입력 후 `create_user.php`로 POST 전송

## 📄 create\_user.php

* 사용자가 입력한 정보를 바탕으로 데이터베이스에 회원 정보 저장
* 유효성 검사 및 중복 체크 포함

## 📄 login.php

* 이메일 및 비밀번호를 입력받고 로그인 처리 수행
* 로그인 성공 시 `home.php`로 리디렉션
* 실패 시 오류 메시지 출력

## 📄 home.php

* 로그인 성공 후 보여지는 메인 페이지
* 사용자 정보, 프로필 이미지 출력
* 로그아웃 기능 포함 가능

## 📄 functions.php

* 공통 유틸 함수 정의 (예: 오류 출력, DB 연결 함수 등)
* 로그인 체크 또는 사용자 정보 가져오는 함수 포함 가능

## 🎨 style.css

* 전체 UI 스타일 정의
* 입력창, 버튼, 오류 및 성공 메시지, 사용자 프로필 스타일 포함

---

## ✅ 기능 요약

* 회원가입 기능 (`register.php`, `create_user.php`)
* 로그인 기능 (`login.php`)
* 세션을 이용한 로그인 유지 및 접근 제한 (`functions.php` 활용 가능)
* 로그인 후 사용자에 따라 다른 콘텐츠 제공 가능 (ex: 관리자 vs 사용자)

---

## 💡 향후 개선 사항 (추천)

* 비밀번호 해시화 (`password_hash()` 사용)
* SQL Injection 방지 (Prepared Statements 사용)
* 사용자 입력 검증 강화 (JS + 서버 측)
* 관리자 전용 페이지 분리 및 권한 관리
* 이메일 인증 시스템 추가

---

## 🔄 원복 방법

* 이 문서를 바탕으로 파일들을 원래 상태로 복원 가능
* 각 파일명과 역할을 확인하고 백업본과 비교하여 덮어쓰기 수행
