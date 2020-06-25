package com.example.data.audio;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\t\u0010!\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\"\u001a\u00020\u0005H\u00c6\u0003J\t\u0010#\u001a\u00020\u0003H\u00c6\u0003J\t\u0010$\u001a\u00020\u0005H\u00c6\u0003J\t\u0010%\u001a\u00020\tH\u00c6\u0003J\t\u0010&\u001a\u00020\u000bH\u00c6\u0003JE\u0010\'\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u00c6\u0001J\u0013\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010+\u001a\u00020\u0005H\u00d6\u0001J\t\u0010,\u001a\u00020\u0003H\u00d6\u0001R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001a\"\u0004\b\u001e\u0010\u001cR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0016\"\u0004\b \u0010\u0018\u00a8\u0006-"}, d2 = {"Lcom/example/data/audio/AudioHelperData;", "", "title", "", "numberOfAudioItems", "", "currentAudioId", "currentNumber", "authorData", "Lcom/example/data/audio/AudioMediaData$AuthorData;", "audioClipModel", "Lcom/example/data/audio/AudioClipModel;", "(Ljava/lang/String;ILjava/lang/String;ILcom/example/data/audio/AudioMediaData$AuthorData;Lcom/example/data/audio/AudioClipModel;)V", "getAudioClipModel", "()Lcom/example/data/audio/AudioClipModel;", "setAudioClipModel", "(Lcom/example/data/audio/AudioClipModel;)V", "getAuthorData", "()Lcom/example/data/audio/AudioMediaData$AuthorData;", "setAuthorData", "(Lcom/example/data/audio/AudioMediaData$AuthorData;)V", "getCurrentAudioId", "()Ljava/lang/String;", "setCurrentAudioId", "(Ljava/lang/String;)V", "getCurrentNumber", "()I", "setCurrentNumber", "(I)V", "getNumberOfAudioItems", "setNumberOfAudioItems", "getTitle", "setTitle", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "daos_devDebug"})
public final class AudioHelperData {
    @org.jetbrains.annotations.NotNull()
    private java.lang.String title;
    private int numberOfAudioItems;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String currentAudioId;
    private int currentNumber;
    @org.jetbrains.annotations.NotNull()
    private com.example.data.audio.AudioMediaData.AuthorData authorData;
    @org.jetbrains.annotations.NotNull()
    private com.example.data.audio.AudioClipModel audioClipModel;
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTitle() {
        return null;
    }
    
    public final void setTitle(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final int getNumberOfAudioItems() {
        return 0;
    }
    
    public final void setNumberOfAudioItems(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCurrentAudioId() {
        return null;
    }
    
    public final void setCurrentAudioId(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final int getCurrentNumber() {
        return 0;
    }
    
    public final void setCurrentNumber(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.data.audio.AudioMediaData.AuthorData getAuthorData() {
        return null;
    }
    
    public final void setAuthorData(@org.jetbrains.annotations.NotNull()
    com.example.data.audio.AudioMediaData.AuthorData p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.data.audio.AudioClipModel getAudioClipModel() {
        return null;
    }
    
    public final void setAudioClipModel(@org.jetbrains.annotations.NotNull()
    com.example.data.audio.AudioClipModel p0) {
    }
    
    public AudioHelperData(@org.jetbrains.annotations.NotNull()
    java.lang.String title, int numberOfAudioItems, @org.jetbrains.annotations.NotNull()
    java.lang.String currentAudioId, int currentNumber, @org.jetbrains.annotations.NotNull()
    com.example.data.audio.AudioMediaData.AuthorData authorData, @org.jetbrains.annotations.NotNull()
    com.example.data.audio.AudioClipModel audioClipModel) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    public final int component2() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    public final int component4() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.data.audio.AudioMediaData.AuthorData component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.data.audio.AudioClipModel component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.data.audio.AudioHelperData copy(@org.jetbrains.annotations.NotNull()
    java.lang.String title, int numberOfAudioItems, @org.jetbrains.annotations.NotNull()
    java.lang.String currentAudioId, int currentNumber, @org.jetbrains.annotations.NotNull()
    com.example.data.audio.AudioMediaData.AuthorData authorData, @org.jetbrains.annotations.NotNull()
    com.example.data.audio.AudioClipModel audioClipModel) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object p0) {
        return false;
    }
}