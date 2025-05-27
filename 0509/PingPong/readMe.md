![image](https://github.com/user-attachments/assets/da900309-7801-4fd9-9d60-c807692271d8)

# 🏓 PingPong - Snow Animation App

[![Android](https://img.shields.io/badge/Platform-Android-green.svg)](https://developer.android.com/)
[![Java](https://img.shields.io/badge/Language-Java-orange.svg)](https://www.java.com/)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg)](https://android-arsenal.com/api?level=21)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

> 🌨️ 아름다운 눈 내리는 애니메이션을 즐길 수 있는 안드로이드 앱

## 📱 스크린샷

```
┌─────────────────────────┐
│    PingPong - 눈 내림    │
├─────────────────────────┤
│                         │
│     🌨️ 눈 애니메이션     │
│        겨울 풍경         │
│          🌲             │
│     ❄️  ❄️  ❄️         │
│   ❄️      ❄️     ❄️     │
│                         │
├─────────────────────────┤
│ 눈 크기: ■■■□□ (10)     │
│ 눈 개수: ■■■■□ (50)     │
│   [  일시정지  ]         │
└─────────────────────────┘
```

## ✨ 주요 기능

- 🎨 **실시간 눈 애니메이션**: 부드러운 20 FPS 눈 내리는 효과
- 🎚️ **조절 가능한 설정**:
  - 눈 크기 조절 (5~35)
  - 눈 개수 조절 (10~200개)
- ⏯️ **애니메이션 제어**: 시작/일시정지 기능
- 🏔️ **아름다운 배경**: 겨울 풍경 (언덕, 나무)
- 📱 **최적화된 UI**: 직관적이고 반응형 인터페이스

## 🚀 빠른 시작

### 필수 요구사항

- **Android Studio** 4.0 이상
- **Android SDK** API 21 (Android 5.0) 이상
- **Java** 8 이상

### 설치 방법

1. **저장소 클론**
   ```bash
   git clone https://github.com/yourusername/pingpong-snow-app.git
   cd pingpong-snow-app
   ```

2. **Android Studio에서 열기**
   - Android Studio 실행
   - `Open an existing Android Studio project` 선택
   - 클론한 폴더 선택

3. **빌드 및 실행**
   ```bash
   # Gradle 빌드
   ./gradlew assembleDebug
   
   # 또는 Android Studio에서 Run 버튼 클릭
   ```

## 📋 사용법

### 기본 조작

1. **앱 실행**: 자동으로 눈 애니메이션이 시작됩니다
2. **눈 크기 조절**: 하단의 첫 번째 슬라이더를 움직여 조절
3. **눈 개수 조절**: 두 번째 슬라이더로 눈송이 개수 변경
4. **일시정지/재생**: 하단 버튼으로 애니메이션 제어

### 컨트롤 가이드

| 컨트롤 | 기능 | 범위 |
|--------|------|------|
| 눈 크기 슬라이더 | 눈송이 크기 조절 | 5 ~ 35 |
| 눈 개수 슬라이더 | 눈송이 개수 조절 | 10 ~ 200 |
| 일시정지 버튼 | 애니메이션 제어 | 시작/정지 |

## 🏗️ 프로젝트 구조

```
app/
├── src/main/java/com/example/pingpong/
│   ├── MainActivity.java          # 메인 액티비티
│   ├── SnowView.java             # 커스텀 뷰 (눈 애니메이션)
│   └── Snowflake.java            # 눈송이 데이터 클래스
├── src/main/res/
│   ├── layout/                   # 레이아웃 파일들
│   ├── values/                   # 색상, 문자열 리소스
│   └── drawable/                 # 이미지 리소스
└── AndroidManifest.xml           # 앱 매니페스트
```

## 🛠️ 기술 스택

- **언어**: Java
- **플랫폼**: Android (API 21+)
- **UI**: Custom Canvas Drawing
- **애니메이션**: Thread-based Animation (20 FPS)
- **아키텍처**: MVC Pattern

## 📊 성능 최적화

### 메모리 관리
- ✅ 액티비티 생명주기에 따른 스레드 관리
- ✅ 효율적인 Paint 객체 재사용
- ✅ 불필요한 객체 생성 최소화

### 렌더링 최적화
- 🎯 20 FPS 타겟 프레임 레이트
- 🎨 AntiAliasing 적용으로 부드러운 그래픽
- ⚡ Canvas의 효율적인 그리기 순서

## 🤝 기여하기

프로젝트에 기여해주시는 모든 분들을 환영합니다!

### 기여 방법

1. **Fork** 이 저장소
2. **Feature branch** 생성 (`git checkout -b feature/AmazingFeature`)
3. **Commit** 변경사항 (`git commit -m 'Add some AmazingFeature'`)
4. **Push** 브랜치에 (`git push origin feature/AmazingFeature`)
5. **Pull Request** 생성

### 개발 가이드라인

- 📝 명확한 커밋 메시지 작성
- 🧪 새로운 기능은 테스트 코드 포함
- 📖 README 업데이트 (필요시)
- 🎨 기존 코드 스타일 유지

## 🐛 이슈 및 버그 리포트

버그를 발견했거나 새로운 기능을 제안하고 싶으시다면:

1. [Issues](https://github.com/yourusername/pingpong-snow-app/issues) 페이지에서 기존 이슈 확인
2. 새로운 이슈 생성 시 다음 정보 포함:
   - 🔍 **버그 설명**: 무엇이 잘못되었는지
   - 📱 **디바이스 정보**: 기종, Android 버전
   - 🔄 **재현 방법**: 단계별 설명
   - 📊 **예상 결과**: 어떻게 동작해야 하는지

## 📈 로드맵

### v1.1 (계획중)

- [ ] 🌧️ 비 내리는 효과 추가
- [ ] 🎵 배경음악 및 효과음
- [ ] 🎨 다양한 배경 테마
- [ ] 💾 설정 저장 기능

### v1.2 (향후)

- [ ] 🖼️ 커스텀 배경 이미지
- [ ] 🌈 다양한 날씨 효과
- [ ] 📊 성능 모니터링
- [ ] 🌍 다국어 지원

## 📄 라이선스

이 프로젝트는 MIT 라이선스 하에 배포됩니다. 자세한 내용은 [LICENSE](LICENSE) 파일을 참조하세요.

```
MIT License

Copyright (c) 2024 PingPong Snow App

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
```

## 📞 연락처

**개발자**: Your Name  
**이메일**: your.email@example.com  
**프로젝트 링크**: [https://github.com/yourusername/pingpong-snow-app](https://github.com/yourusername/pingpong-snow-app)

## 🙏 감사의 말

- 안드로이드 개발 커뮤니티의 모든 분들께
- 오픈소스 라이브러리 기여자들께
- 베타 테스터 및 피드백을 주신 모든 분들께

---

<div align="center">

**⭐ 이 프로젝트가 도움이 되셨다면 Star를 눌러주세요! ⭐**

[🏠 홈](README.md) | [📖 문서](docs/) | [🐛 이슈](https://github.com/yourusername/pingpong-snow-app/issues) | [💡 기능 요청](https://github.com/yourusername/pingpong-snow-app/issues/new)

</div>
