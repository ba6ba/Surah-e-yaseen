package com.example.data.verse;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0019\b&\u0018\u00002\u00020\u0001BY\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\u000bR\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR \u0010\b\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R \u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0012\"\u0004\b\u0016\u0010\u0014R \u0010\n\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0012\"\u0004\b\u0018\u0010\u0014R \u0010\t\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0012\"\u0004\b\u001a\u0010\u0014R \u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0012\"\u0004\b\u001c\u0010\u0014R\"\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\u001d\u0010\r\"\u0004\b\u001e\u0010\u000f\u00a8\u0006\u001f"}, d2 = {"Lcom/example/data/verse/BaseVerse;", "", "id", "", "verseNumber", "verseKey", "", "madniText", "indoPakText", "simpleText", "pageNumber", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/Integer;", "setId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getIndoPakText", "()Ljava/lang/String;", "setIndoPakText", "(Ljava/lang/String;)V", "getMadniText", "setMadniText", "getPageNumber", "setPageNumber", "getSimpleText", "setSimpleText", "getVerseKey", "setVerseKey", "getVerseNumber", "setVerseNumber", "daos_devDebug"})
public abstract class BaseVerse {
    @org.jetbrains.annotations.Nullable()
    private java.lang.Integer id;
    @org.jetbrains.annotations.Nullable()
    @com.squareup.moshi.Json(name = "verse_number")
    private java.lang.Integer verseNumber;
    @org.jetbrains.annotations.Nullable()
    @com.squareup.moshi.Json(name = "verse_key")
    private java.lang.String verseKey;
    @org.jetbrains.annotations.Nullable()
    @com.squareup.moshi.Json(name = "text_madani")
    private java.lang.String madniText;
    @org.jetbrains.annotations.Nullable()
    @com.squareup.moshi.Json(name = "text_indopak")
    private java.lang.String indoPakText;
    @org.jetbrains.annotations.Nullable()
    @com.squareup.moshi.Json(name = "text_simple")
    private java.lang.String simpleText;
    @org.jetbrains.annotations.Nullable()
    @com.squareup.moshi.Json(name = "page_number")
    private java.lang.String pageNumber;
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getId() {
        return null;
    }
    
    public final void setId(@org.jetbrains.annotations.Nullable()
    java.lang.Integer p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getVerseNumber() {
        return null;
    }
    
    public final void setVerseNumber(@org.jetbrains.annotations.Nullable()
    java.lang.Integer p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getVerseKey() {
        return null;
    }
    
    public final void setVerseKey(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getMadniText() {
        return null;
    }
    
    public final void setMadniText(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getIndoPakText() {
        return null;
    }
    
    public final void setIndoPakText(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getSimpleText() {
        return null;
    }
    
    public final void setSimpleText(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getPageNumber() {
        return null;
    }
    
    public final void setPageNumber(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    public BaseVerse(@org.jetbrains.annotations.Nullable()
    java.lang.Integer id, @org.jetbrains.annotations.Nullable()
    java.lang.Integer verseNumber, @org.jetbrains.annotations.Nullable()
    java.lang.String verseKey, @org.jetbrains.annotations.Nullable()
    java.lang.String madniText, @org.jetbrains.annotations.Nullable()
    java.lang.String indoPakText, @org.jetbrains.annotations.Nullable()
    java.lang.String simpleText, @org.jetbrains.annotations.Nullable()
    java.lang.String pageNumber) {
        super();
    }
    
    public BaseVerse() {
        super();
    }
}