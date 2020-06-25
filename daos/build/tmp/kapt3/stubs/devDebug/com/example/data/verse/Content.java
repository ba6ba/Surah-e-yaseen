package com.example.data.verse;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0005H\u00c6\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J5\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007H\u00c6\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u00d6\u0003J\t\u0010!\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\"\u001a\u00020#H\u00d6\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0013\"\u0004\b\u0017\u0010\u0015\u00a8\u0006$"}, d2 = {"Lcom/example/data/verse/Content;", "Lcom/example/data/verse/BaseVerse;", "position", "", "audio", "Lcom/example/data/verse/Audio;", "translation", "Lcom/example/data/verse/Translation;", "transliteration", "(ILcom/example/data/verse/Audio;Lcom/example/data/verse/Translation;Lcom/example/data/verse/Translation;)V", "getAudio", "()Lcom/example/data/verse/Audio;", "setAudio", "(Lcom/example/data/verse/Audio;)V", "getPosition", "()I", "setPosition", "(I)V", "getTranslation", "()Lcom/example/data/verse/Translation;", "setTranslation", "(Lcom/example/data/verse/Translation;)V", "getTransliteration", "setTransliteration", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "toString", "", "daos_devDebug"})
public final class Content extends com.example.data.verse.BaseVerse {
    private int position;
    @org.jetbrains.annotations.NotNull()
    private com.example.data.verse.Audio audio;
    @org.jetbrains.annotations.Nullable()
    private com.example.data.verse.Translation translation;
    @org.jetbrains.annotations.Nullable()
    private com.example.data.verse.Translation transliteration;
    
    public final int getPosition() {
        return 0;
    }
    
    public final void setPosition(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.data.verse.Audio getAudio() {
        return null;
    }
    
    public final void setAudio(@org.jetbrains.annotations.NotNull()
    com.example.data.verse.Audio p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.example.data.verse.Translation getTranslation() {
        return null;
    }
    
    public final void setTranslation(@org.jetbrains.annotations.Nullable()
    com.example.data.verse.Translation p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.example.data.verse.Translation getTransliteration() {
        return null;
    }
    
    public final void setTransliteration(@org.jetbrains.annotations.Nullable()
    com.example.data.verse.Translation p0) {
    }
    
    public Content(int position, @org.jetbrains.annotations.NotNull()
    com.example.data.verse.Audio audio, @org.jetbrains.annotations.Nullable()
    com.example.data.verse.Translation translation, @org.jetbrains.annotations.Nullable()
    com.example.data.verse.Translation transliteration) {
        super(null, null, null, null, null, null, null);
    }
    
    public final int component1() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.data.verse.Audio component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.example.data.verse.Translation component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.example.data.verse.Translation component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.data.verse.Content copy(int position, @org.jetbrains.annotations.NotNull()
    com.example.data.verse.Audio audio, @org.jetbrains.annotations.Nullable()
    com.example.data.verse.Translation translation, @org.jetbrains.annotations.Nullable()
    com.example.data.verse.Translation transliteration) {
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