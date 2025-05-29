![image](https://github.com/user-attachments/assets/8f02607a-26bb-4a98-966a-e47107797249)
# Video List App 📱

안드로이드 기기에 저장된 모든 동영상을 검색하여 리스트뷰에 표시하는 앱입니다.

## 🚀 주요 기능

- 기기 내 저장된 모든 동영상 파일 검색
- 동영상 제목을 리스트뷰에 표시
- 런타임 권한 요청 및 처리
- Android 13+ 호환성 지원

## 📋 요구사항

- **최소 SDK**: API 23 (Android 6.0)
- **타겟 SDK**: API 33 이상 권장
- **권한**: 저장소 읽기 권한 필요

## 🛠️ 기술 스택

- **언어**: Java
- **플랫폼**: Android
- **IDE**: Android Studio
- **주요 컴포넌트**:
  - ContentResolver
  - MediaStore API
  - ListView
  - Runtime Permissions

## 📱 스크린샷

```
┌─────────────────────────┐
│    저장된 동영상 목록     │
├─────────────────────────┤
│ ○ 동영상제목1.mp4       │
│ ○ 동영상제목2.mp4       │
│ ○ 동영상제목3.mp4       │
│ ○ ...                  │
└─────────────────────────┘
```

## 🔧 설치 및 실행

### 1. 프로젝트 클론
```bash
git clone https://github.com/yourusername/video-list-app.git
cd video-list-app
```

### 2. Android Studio에서 프로젝트 열기
- Android Studio 실행
- "Open an existing project" 선택
- 프로젝트 폴더 선택

### 3. 필요한 권한 설정
앱 실행 시 저장소 권한을 허용해주세요.

#### 권한 수동 설정 (애뮬레이터)
1. 설정 → 앱 → VideoListApp
2. 권한 → 저장소 → 허용

## 📁 프로젝트 구조

```
app/
├── src/main/
│   ├── java/com/example/videolistapp/
│   │   └── MainActivity.java          # 메인 액티비티
│   ├── res/
│   │   └── layout/
│   │       └── activity_main.xml      # 레이아웃 파일
│   └── AndroidManifest.xml            # 매니페스트 파일
└── build.gradle                       # 빌드 설정
```

## 🔑 핵심 코드

### ContentResolver를 이용한 동영상 검색
```java
Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
Cursor cursor = getContentResolver().query(uri, null, null, null, null);

if (cursor != null && cursor.moveToFirst()) {
    int titleIndex = cursor.getColumnIndex(MediaStore.Video.Media.TITLE);
    do {
        String title = cursor.getString(titleIndex);
        videoList.add(title);
    } while (cursor.moveToNext());
    cursor.close();
}
```

### 런타임 권한 요청
```java
private void checkPermission() {
    String permission = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) 
        ? Manifest.permission.READ_MEDIA_VIDEO 
        : Manifest.permission.READ_EXTERNAL_STORAGE;
        
    if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(this, new String[]{permission}, PERMISSION_REQUEST_CODE);
    } else {
        loadVideos();
    }
}
```

## 🛡️ 권한 설정

### AndroidManifest.xml
```xml
<!-- Android 12 이하 -->
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" 
    android:maxSdkVersion="32" />

<!-- Android 13 이상 -->
<uses-permission android:name="android.permission.READ_MEDIA_VIDEO" />
```

## 🐛 문제 해결

### 권한 대화상자가 나타나지 않는 경우
1. **애뮬레이터 설정에서 수동 권한 허용**
   - 설정 → 앱 → VideoListApp → 권한 → 저장소 ON

2. **타겟 SDK 버전 확인**
   - `build.gradle`에서 `targetSdkVersion 23` 이상 확인

3. **앱 재설치**
   - 앱을 완전히 삭제 후 다시 설치

### 동영상이 표시되지 않는 경우
1. **애뮬레이터에 테스트 동영상 추가**
   - PC에서 동영상 파일을 드래그 앤 드롭
   - 카메라 앱으로 짧은 동영상 촬영

2. **권한 상태 확인**
   - 설정에서 앱 권한이 올바르게 허용되었는지 확인

## 🔄 버전 호환성

| Android 버전 | API Level | 필요 권한 | 지원 상태 |
|-------------|-----------|-----------|-----------|
| 6.0-12 | 23-32 | READ_EXTERNAL_STORAGE | ✅ 지원 |
| 13+ | 33+ | READ_MEDIA_VIDEO | ✅ 지원 |

## 📝 주요 학습 포인트

- **ContentResolver 사용법**: 시스템 콘텐츠 제공자 접근
- **MediaStore API**: 미디어 파일 정보 조회
- **런타임 권한**: Android 6.0+ 권한 모델
- **Cursor 처리**: 데이터베이스 결과 처리
- **ListView 구현**: 동적 리스트 표시
