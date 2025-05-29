![image](https://github.com/user-attachments/assets/3dc30290-aa10-44ec-9502-2274d4239a58)
![image](https://github.com/user-attachments/assets/3fa9dbbd-4831-4ce6-ae5f-f1695f88f7a7)
# 📝 TodoList with Notification

Android Studio에서 Java로 구현한 시간 알림 기능이 포함된 TodoList 앱입니다.

## ✨ 주요 기능

### 📱 기본 기능
- **할일 추가/삭제**: 할일을 추가하고, 길게 눌러서 삭제할 수 있습니다
- **DateTime Picker**: 직관적인 날짜와 시간 선택 인터페이스
- **실시간 알림**: 설정한 시간에 정확한 푸시 알림 전송

### 🔔 알림 시스템
- **Android 13+ 호환**: 최신 알림 권한 자동 요청
- **정확한 시간 알림**: 설정한 시간에 정확히 알림 발송
- **사운드 & 진동**: 기본 알림음과 진동 지원
- **앱 연동**: 알림 터치 시 앱으로 바로 이동

### 🛡️ 권한 관리
- **Android 12+ 정확한 알람 권한** 자동 처리
- **알림 권한** 런타임 요청
- **권한 거부 시 대체 방안** 제공

## 🚀 시작하기

### 필요한 환경
- Android Studio 4.0 이상
- Android SDK 21 (Android 5.0) 이상
- Java 8 이상

### 설치 방법

1. **프로젝트 클론**
   ```bash
   git clone [repository-url]
   cd todolist-notification
   ```

2. **Android Studio에서 프로젝트 열기**
   - Android Studio 실행
   - "Open an existing Android Studio project" 선택
   - 프로젝트 폴더 선택

3. **필요한 파일 구조**
   ```
   app/src/main/
   ├── java/com/example/todolist/
   │   ├── MainActivity.java
   │   └── NotificationReceiver.java
   ├── res/
   │   ├── layout/
   │   │   └── activity_main.xml
   │   └── drawable/
   │       ├── edittext_background.xml
   │       ├── button_background.xml
   │       └── button_background_green.xml
   └── AndroidManifest.xml
   ```

4. **빌드 & 실행**
   - "Build" → "Make Project" (Ctrl+F9)
   - "Run" → "Run 'app'" (Shift+F10)

## 📋 파일 설명

### Java 파일

#### `MainActivity.java`
- 앱의 메인 액티비티
- TodoList UI 관리
- 날짜/시간 선택 처리
- 알림 설정 및 권한 관리

#### `NotificationReceiver.java`
- BroadcastReceiver 클래스
- 설정된 시간에 알림 표시
- 알림 클릭 시 앱 실행 처리

### Layout 파일

#### `activity_main.xml`
- 메인 화면 레이아웃
- 할일 입력, 시간 설정, 목록 표시
- Material Design 스타일 적용

#### Drawable 리소스
- `edittext_background.xml`: 입력창 스타일
- `button_background.xml`: 파란색 버튼 스타일
- `button_background_green.xml`: 초록색 버튼 스타일

### 설정 파일

#### `AndroidManifest.xml`
- 앱 권한 설정
- 컴포넌트 등록
- NotificationReceiver 등록

## 🎯 사용법

### 1. 할일 추가하기
1. 상단 입력창에 할일 내용 입력
2. **"⏰ 시간 설정"** 버튼 클릭
3. 날짜 선택 → 시간 선택
4. **"➕ 추가"** 버튼 클릭

### 2. 할일 삭제하기
- 목록에서 삭제할 항목을 **길게 누르기**

### 3. 알림 받기
- 설정한 시간이 되면 자동으로 알림 표시
- 알림 터치 시 앱으로 이동

## ⚠️ 권한 안내

### Android 12 이상 (API 31+)
앱 최초 실행 시 다음 권한들이 필요합니다:

1. **정확한 알람 권한 (`USE_EXACT_ALARM`)**
   - 설정한 시간에 정확히 알림을 받기 위해 필요
   - 권한이 없으면 설정 페이지로 자동 이동

2. **알림 권한 (`POST_NOTIFICATIONS`)** - Android 13+
   - 푸시 알림을 받기 위해 필요
   - 자동으로 권한 요청 다이얼로그 표시

### 권한 허용 방법
1. 앱에서 권한 요청 시 **"허용"** 선택
2. 또는 **설정 > 앱 > TodoList > 권한**에서 수동 허용

## 🔧 기술 스택

- **언어**: Java
- **플랫폼**: Android (API 21+)
- **주요 컴포넌트**:
  - `AlarmManager`: 정확한 시간 알림 설정
  - `NotificationManager`: 푸시 알림 관리
  - `BroadcastReceiver`: 알림 이벤트 처리
  - `DatePickerDialog` / `TimePickerDialog`: 날짜/시간 선택
  - `ListView`: 할일 목록 표시

## 🐛 문제 해결

### Q1. 알림이 오지 않아요
**A1.** 다음을 확인해보세요:
- 앱 알림 권한이 허용되어 있는지 확인
- 정확한 알람 권한이 허용되어 있는지 확인
- 배터리 최적화에서 해당 앱이 제외되어 있는지 확인

### Q2. "정확한 알람 권한이 필요합니다" 메시지가 나와요
**A2.** Android 12 이상에서 나타나는 정상적인 메시지입니다:
1. 설정 페이지로 이동
2. "알람 및 리마인더" 또는 "정확한 알람" 옵션 찾기
3. 해당 앱에 대해 권한 허용

### Q3. 앱이 크래시됩니다
**A3.** 다음을 확인해보세요:
- Android SDK 버전이 21 이상인지 확인
- 모든 파일이 올바른 위치에 있는지 확인
- 패키지명이 `com.example.todolist`와 일치하는지 확인
