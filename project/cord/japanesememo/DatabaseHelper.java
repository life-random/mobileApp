package com.example.japanesememo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "JapaneseMemo.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_WORDS = "words";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_JAPANESE = "japanese";
    private static final String COLUMN_KOREAN = "korean";
    private static final String COLUMN_HIRAGANA = "hiragana";
    private static final String COLUMN_STUDY_COUNT = "study_count";
    private static final String COLUMN_IS_LEARNED = "is_learned";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_WORDS_TABLE = "CREATE TABLE " + TABLE_WORDS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_JAPANESE + " TEXT NOT NULL,"
                + COLUMN_KOREAN + " TEXT NOT NULL,"
                + COLUMN_HIRAGANA + " TEXT,"
                + COLUMN_STUDY_COUNT + " INTEGER DEFAULT 0,"
                + COLUMN_IS_LEARNED + " INTEGER DEFAULT 0" + ")";
        db.execSQL(CREATE_WORDS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORDS);
        onCreate(db);
    }

    public long insertWord(Word word) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_JAPANESE, word.getJapanese());
        values.put(COLUMN_KOREAN, word.getKorean());
        values.put(COLUMN_HIRAGANA, word.getHiragana());
        values.put(COLUMN_STUDY_COUNT, word.getStudyCount());
        values.put(COLUMN_IS_LEARNED, word.isLearned() ? 1 : 0);

        return db.insert(TABLE_WORDS, null, values);
    }

    public List<Word> getAllWords() {
        List<Word> wordList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_WORDS + " ORDER BY " + COLUMN_ID + " DESC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Word word = new Word();
                word.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                word.setJapanese(cursor.getString(cursor.getColumnIndex(COLUMN_JAPANESE)));
                word.setKorean(cursor.getString(cursor.getColumnIndex(COLUMN_KOREAN)));
                word.setHiragana(cursor.getString(cursor.getColumnIndex(COLUMN_HIRAGANA)));
                word.setStudyCount(cursor.getInt(cursor.getColumnIndex(COLUMN_STUDY_COUNT)));
                word.setLearned(cursor.getInt(cursor.getColumnIndex(COLUMN_IS_LEARNED)) == 1);

                wordList.add(word);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return wordList;
    }

    // 한글 검색 기능 추가
    public List<Word> searchWordsByKorean(String searchQuery) {
        List<Word> wordList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_WORDS +
                " WHERE " + COLUMN_KOREAN + " LIKE ? OR " +
                COLUMN_JAPANESE + " LIKE ? OR " +
                COLUMN_HIRAGANA + " LIKE ?" +
                " ORDER BY " + COLUMN_ID + " DESC";

        SQLiteDatabase db = this.getReadableDatabase();
        String searchPattern = "%" + searchQuery + "%";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{searchPattern, searchPattern, searchPattern});

        if (cursor.moveToFirst()) {
            do {
                Word word = new Word();
                word.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                word.setJapanese(cursor.getString(cursor.getColumnIndex(COLUMN_JAPANESE)));
                word.setKorean(cursor.getString(cursor.getColumnIndex(COLUMN_KOREAN)));
                word.setHiragana(cursor.getString(cursor.getColumnIndex(COLUMN_HIRAGANA)));
                word.setStudyCount(cursor.getInt(cursor.getColumnIndex(COLUMN_STUDY_COUNT)));
                word.setLearned(cursor.getInt(cursor.getColumnIndex(COLUMN_IS_LEARNED)) == 1);

                wordList.add(word);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return wordList;
    }

    public void updateWord(Word word) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_JAPANESE, word.getJapanese());
        values.put(COLUMN_KOREAN, word.getKorean());
        values.put(COLUMN_HIRAGANA, word.getHiragana());
        values.put(COLUMN_STUDY_COUNT, word.getStudyCount());
        values.put(COLUMN_IS_LEARNED, word.isLearned() ? 1 : 0);

        db.update(TABLE_WORDS, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(word.getId())});
    }

    public void deleteWord(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_WORDS, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }
}