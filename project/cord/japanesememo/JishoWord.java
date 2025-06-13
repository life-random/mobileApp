package com.example.japanesememo;

public class JishoWord {
    private String japanese;
    private String korean;
    private String reading;
    private String partOfSpeech;

    public JishoWord() {}

    public JishoWord(String japanese, String korean, String reading) {
        this.japanese = japanese;
        this.korean = korean;
        this.reading = reading;
    }

    // Getters and Setters
    public String getJapanese() { return japanese; }
    public void setJapanese(String japanese) { this.japanese = japanese; }

    public String getKorean() { return korean; }
    public void setKorean(String korean) { this.korean = korean; }

    public String getReading() { return reading; }
    public void setReading(String reading) { this.reading = reading; }

    public String getPartOfSpeech() { return partOfSpeech; }
    public void setPartOfSpeech(String partOfSpeech) { this.partOfSpeech = partOfSpeech; }
}