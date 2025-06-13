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
import java.util.ArrayList;
import java.util.List;

public class JishoApiService {
    private static final String BASE_URL = "https://jisho.org/api/v1/search/words?keyword=";

    public interface ApiCallback {
        void onSuccess(List<JishoWord> words);
        void onError(String error);
    }

    public void searchWord(String query, ApiCallback callback) {
        new SearchWordTask(callback).execute(query);
    }

    private static class SearchWordTask extends AsyncTask<String, Void, List<JishoWord>> {
        private ApiCallback callback;
        private String errorMessage;

        public SearchWordTask(ApiCallback callback) {
            this.callback = callback;
        }

        @Override
        protected List<JishoWord> doInBackground(String... params) {
            String query = params[0];
            List<JishoWord> words = new ArrayList<>();

            try {
                String encodedQuery = URLEncoder.encode(query, "UTF-8");
                URL url = new URL(BASE_URL + encodedQuery);
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

                    words = parseResponse(response.toString());
                } else {
                    errorMessage = "HTTP Error: " + responseCode;
                }
                connection.disconnect();

            } catch (IOException e) {
                errorMessage = "네트워크 오류: " + e.getMessage();
            } catch (JSONException e) {
                errorMessage = "데이터 파싱 오류: " + e.getMessage();
            }

            return words;
        }

        @Override
        protected void onPostExecute(List<JishoWord> words) {
            if (errorMessage != null) {
                callback.onError(errorMessage);
            } else {
                callback.onSuccess(words);
            }
        }

        private List<JishoWord> parseResponse(String jsonResponse) throws JSONException {
            List<JishoWord> words = new ArrayList<>();
            JSONObject root = new JSONObject(jsonResponse);
            JSONArray dataArray = root.getJSONArray("data");

            for (int i = 0; i < Math.min(dataArray.length(), 10); i++) { // 최대 10개 결과
                JSONObject item = dataArray.getJSONObject(i);

                // 일본어 텍스트 추출
                JSONArray japanese = item.getJSONArray("japanese");
                String japaneseText = "";
                String reading = "";

                if (japanese.length() > 0) {
                    JSONObject firstJapanese = japanese.getJSONObject(0);
                    japaneseText = firstJapanese.optString("word", "");
                    reading = firstJapanese.optString("reading", "");

                    if (japaneseText.isEmpty()) {
                        japaneseText = reading;
                    }
                }

                // 영어 의미 추출 (한국어 번역을 위해)
                JSONArray senses = item.getJSONArray("senses");
                String englishMeaning = "";

                if (senses.length() > 0) {
                    JSONObject firstSense = senses.getJSONObject(0);
                    JSONArray englishDefinitions = firstSense.getJSONArray("english_definitions");
                    if (englishDefinitions.length() > 0) {
                        englishMeaning = englishDefinitions.getString(0);
                    }
                }

                if (!japaneseText.isEmpty() && !englishMeaning.isEmpty()) {
                    JishoWord word = new JishoWord();
                    word.setJapanese(japaneseText);
                    word.setReading(reading);
                    word.setKorean(englishMeaning); // 실제로는 영어지만 사용자가 한국어로 수정 가능
                    words.add(word);
                }
            }

            return words;
        }
    }
}