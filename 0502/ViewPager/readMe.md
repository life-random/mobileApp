![image](https://github.com/user-attachments/assets/bad42300-71fa-47da-ab1d-c9d4b5aa22c2)


# ViewPager 이미지 슬라이더 앱

간단한 Android ViewPager 프로젝트로, 손가락 스와이프를 통해 이미지와 해당 설명 텍스트를 슬라이드하는 앱입니다.

## 기능 설명

- 손가락으로 화면을 스와이프하면 이미지와 텍스트가 전환됩니다.
- 총 3개의 이미지와 각 이미지에 해당하는 설명 텍스트를 표시합니다.
- `ViewPager`와 `PagerAdapter`를 사용하여 부드러운 페이지 전환 효과를 제공합니다.

## 주요 파일 구성

### Java 파일

- **MainActivity.java**: 메인 액티비티 클래스로 ViewPager를 초기화하고 어댑터를 설정합니다.
- **ViewPagerAdapter.java**: PagerAdapter를 상속받은 커스텀 어댑터로, 이미지와 텍스트를 ViewPager에 공급하는 역할을 합니다.

### XML 레이아웃 파일

- **activity_main.xml**: 메인 화면 레이아웃으로 ViewPager를 포함합니다.
- **slide_layout.xml**: 각 슬라이드의 레이아웃으로 이미지와 텍스트를 표시하는 요소를 포함합니다.

### 리소스 파일

- **drawable/image1.png**: 첫 번째 슬라이드 이미지
- **drawable/image2.png**: 두 번째 슬라이드 이미지
- **drawable/image3.png**: 세 번째 슬라이드 이미지

## 설치 및 설정 방법

1. 프로젝트 파일을 Android Studio에서 열기
2. 필요한 이미지 파일(image1, image2, image3)을 `app/src/main/res/drawable` 폴더에 추가
3. 필요한 경우 `ViewPagerAdapter.java`에서 텍스트 내용을 수정
4. 프로젝트 빌드 및 실행

## ViewPagerAdapter 클래스 설명

ViewPagerAdapter는 다음과 같은 주요 메소드를 포함합니다:

- **getCount()**: 페이지 수를 반환합니다.
- **isViewFromObject()**: 주어진 View와 Object가 같은지 확인합니다.
- **instantiateItem()**: 특정 위치의 페이지 View를 생성하고 이미지와 텍스트를 설정합니다.
- **destroyItem()**: 페이지 View를 제거합니다.

## 프로젝트 구조

```
app/
├── src/
│   ├── main/
│   │   ├── java/[패키지명]/
│   │   │   ├── MainActivity.java
│   │   │   └── ViewPagerAdapter.java
│   │   ├── res/
│   │   │   ├── drawable/
│   │   │   │   ├── image1.png
│   │   │   │   ├── image2.png
│   │   │   │   └── image3.png
│   │   │   ├── layout/
│   │   │   │   ├── activity_main.xml
│   │   │   │   └── slide_layout.xml
│   │   │   └── ...
│   │   └── AndroidManifest.xml
│   └── ...
└── ...
```

## 사용 방법

1. 앱을 실행합니다.
2. 화면을 좌우로 스와이프하여 이미지와 설명 텍스트를 변경할 수 있습니다.

## 주의사항

- 프로젝트를 실행하기 전 반드시 이미지 파일을 drawable 폴더에 추가해야 합니다.
- 안드로이드 스튜디오 3.0 이상 버전에서 개발되었으며, androidx 라이브러리를 사용합니다.
