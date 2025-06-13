package com.example.japanesememo;

public class Word {
    private int id;
    private String japanese;
    private String korean;
    private String hiragana;
    private int studyCount;
    private boolean isLearned;

    public Word() {}

    public Word(String japanese, String korean, String hiragana) {
        this.japanese = japanese;
        this.korean = korean;
        this.hiragana = hiragana;
        this.studyCount = 0;
        this.isLearned = false;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getJapanese() { return japanese; }
    public void setJapanese(String japanese) { this.japanese = japanese; }

    public String getKorean() { return korean; }
    public void setKorean(String korean) { this.korean = korean; }

    public String getHiragana() { return hiragana; }
    public void setHiragana(String hiragana) { this.hiragana = hiragana; }

    public int getStudyCount() { return studyCount; }
    public void setStudyCount(int studyCount) { this.studyCount = studyCount; }

    public boolean isLearned() { return isLearned; }
    public void setLearned(boolean learned) { isLearned = learned; }
}
