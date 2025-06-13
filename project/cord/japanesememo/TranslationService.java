package com.example.japanesememo;

import android.os.AsyncTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.regex.Pattern;

public class TranslationService {
    // Google Translate API 대신 MyMemory API 사용 (무료)
    private static final String TRANSLATE_URL = "https://api.mymemory.translated.net/get?q=%s&langpair=ko|en";

    public interface TranslationCallback {
        void onTranslationSuccess(String translatedText);
        void onTranslationError(String error);
    }

    public static boolean isKorean(String text) {
        // 한글 유니코드 범위: AC00-D7AF
        Pattern koreanPattern = Pattern.compile("[ㄱ-ㅎㅏ-ㅣ가-힣]");
        return koreanPattern.matcher(text).find();
    }

    public static boolean isJapanese(String text) {
        // 일본어 유니코드 범위 (히라가나, 카타카나, 한자)
        Pattern japanesePattern = Pattern.compile("[ひらがなカタカナ一-龯ゝゞ]");
        return japanesePattern.matcher(text).find();
    }

    public void translateKoreanToEnglish(String koreanText, TranslationCallback callback) {
        new TranslateTask(callback).execute(koreanText);
    }

    private static class TranslateTask extends AsyncTask<String, Void, String> {
        private TranslationCallback callback;
        private String errorMessage;

        public TranslateTask(TranslationCallback callback) {
            this.callback = callback;
        }

        @Override
        protected String doInBackground(String... params) {
            String koreanText = params[0];
            try {
                String encodedText = URLEncoder.encode(koreanText, "UTF-8");
                String urlString = String.format(TRANSLATE_URL, encodedText);

                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(10000);
                connection.setReadTimeout(10000);

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();

                    return parseTranslationResponse(response.toString());
                } else {
                    errorMessage = "번역 서비스 오류: " + responseCode;
                }
                connection.disconnect();

            } catch (IOException e) {
                errorMessage = "네트워크 오류: " + e.getMessage();
            } catch (JSONException e) {
                errorMessage = "번역 데이터 파싱 오류: " + e.getMessage();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String translatedText) {
            if (errorMessage != null) {
                callback.onTranslationError(errorMessage);
            } else if (translatedText != null && !translatedText.isEmpty()) {
                callback.onTranslationSuccess(translatedText);
            } else {
                callback.onTranslationError("번역 결과가 없습니다.");
            }
        }

        private String parseTranslationResponse(String jsonResponse) throws JSONException {
            JSONObject root = new JSONObject(jsonResponse);
            JSONObject responseData = root.getJSONObject("responseData");
            String translatedText = responseData.getString("translatedText");

            // 번역 품질 확인
            String match = responseData.optString("match", "0");
            double matchScore = Double.parseDouble(match);

            // 매치 점수가 너무 낮으면 번역 실패로 간주
            if (matchScore < 0.3) {
                return null;
            }

            return translatedText;
        }
    }
}