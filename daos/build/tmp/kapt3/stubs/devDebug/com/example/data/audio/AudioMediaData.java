package com.example.data.audio;

import java.lang.System;

@kotlinx.android.parcel.Parcelize()
@androidx.room.Entity()
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b-\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0087\b\u0018\u0000 P2\u00020\u0001:\bNOPQRSTUB\u000f\b\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004Bs\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\t\u001a\u00020\u0006\u0012\b\b\u0002\u0010\n\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0006\u0012\b\b\u0002\u0010\f\u001a\u00020\u0006\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014\u00a2\u0006\u0002\u0010\u0015J\t\u00106\u001a\u00020\u0006H\u00c6\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\u0014H\u00c6\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\bH\u00c6\u0003J\t\u00109\u001a\u00020\u0006H\u00c6\u0003J\t\u0010:\u001a\u00020\u0006H\u00c6\u0003J\t\u0010;\u001a\u00020\u0006H\u00c6\u0003J\t\u0010<\u001a\u00020\u0006H\u00c6\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u000eH\u00c6\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\u0010H\u00c6\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\u0012H\u00c6\u0003Jw\u0010@\u001a\u00020\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\u00062\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u00c6\u0001J\t\u0010A\u001a\u00020BH\u00d6\u0001J\u0013\u0010C\u001a\u00020D2\b\u0010E\u001a\u0004\u0018\u00010FH\u00d6\u0003J\t\u0010G\u001a\u00020BH\u00d6\u0001J\t\u0010H\u001a\u00020\u0006H\u00d6\u0001J\u0019\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020L2\u0006\u0010M\u001a\u00020BH\u00d6\u0001R\u001a\u0010\u000b\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001a\u0010\t\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0017\"\u0004\b#\u0010\u0019R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0017\"\u0004\b%\u0010\u0019R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\'\"\u0004\b(\u0010)R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u001a\u0010\f\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u0017\"\u0004\b3\u0010\u0019R\u001a\u0010\n\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u0017\"\u0004\b5\u0010\u0019\u00a8\u0006V"}, d2 = {"Lcom/example/data/audio/AudioMediaData;", "Landroid/os/Parcelable;", "builder", "Lcom/example/data/audio/AudioMediaData$Builder;", "(Lcom/example/data/audio/AudioMediaData$Builder;)V", "id", "", "data", "Lcom/example/data/audio/AudioMediaData$Data;", "genre", "title", "album", "subTitle", "metaData", "Lcom/example/data/audio/AudioMediaData$MetaData;", "authorData", "Lcom/example/data/audio/AudioMediaData$AuthorData;", "imageMetaData", "Lcom/example/data/audio/AudioMediaData$ImageMetaData;", "mediaMetaData", "Lcom/example/data/audio/AudioMediaData$MediaMetaData;", "(Ljava/lang/String;Lcom/example/data/audio/AudioMediaData$Data;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/example/data/audio/AudioMediaData$MetaData;Lcom/example/data/audio/AudioMediaData$AuthorData;Lcom/example/data/audio/AudioMediaData$ImageMetaData;Lcom/example/data/audio/AudioMediaData$MediaMetaData;)V", "getAlbum", "()Ljava/lang/String;", "setAlbum", "(Ljava/lang/String;)V", "getAuthorData", "()Lcom/example/data/audio/AudioMediaData$AuthorData;", "setAuthorData", "(Lcom/example/data/audio/AudioMediaData$AuthorData;)V", "getData", "()Lcom/example/data/audio/AudioMediaData$Data;", "setData", "(Lcom/example/data/audio/AudioMediaData$Data;)V", "getGenre", "setGenre", "getId", "setId", "getImageMetaData", "()Lcom/example/data/audio/AudioMediaData$ImageMetaData;", "setImageMetaData", "(Lcom/example/data/audio/AudioMediaData$ImageMetaData;)V", "getMediaMetaData", "()Lcom/example/data/audio/AudioMediaData$MediaMetaData;", "setMediaMetaData", "(Lcom/example/data/audio/AudioMediaData$MediaMetaData;)V", "getMetaData", "()Lcom/example/data/audio/AudioMediaData$MetaData;", "setMetaData", "(Lcom/example/data/audio/AudioMediaData$MetaData;)V", "getSubTitle", "setSubTitle", "getTitle", "setTitle", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "AuthorData", "Builder", "Companion", "Data", "ImageMetaData", "MediaMetaData", "MetaData", "PlaybackState", "daos_devDebug"})
public final class AudioMediaData implements android.os.Parcelable {
    @org.jetbrains.annotations.NotNull()
    @androidx.room.PrimaryKey(autoGenerate = false)
    private java.lang.String id;
    @org.jetbrains.annotations.Nullable()
    private com.example.data.audio.AudioMediaData.Data data;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String genre;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String title;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String album;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String subTitle;
    @org.jetbrains.annotations.Nullable()
    private com.example.data.audio.AudioMediaData.MetaData metaData;
    @org.jetbrains.annotations.Nullable()
    private com.example.data.audio.AudioMediaData.AuthorData authorData;
    @org.jetbrains.annotations.Nullable()
    private com.example.data.audio.AudioMediaData.ImageMetaData imageMetaData;
    @org.jetbrains.annotations.Nullable()
    private com.example.data.audio.AudioMediaData.MediaMetaData mediaMetaData;
    public static final com.example.data.audio.AudioMediaData.Companion Companion = null;
    public static final android.os.Parcelable.Creator CREATOR = null;
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getId() {
        return null;
    }
    
    public final void setId(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.example.data.audio.AudioMediaData.Data getData() {
        return null;
    }
    
    public final void setData(@org.jetbrains.annotations.Nullable()
    com.example.data.audio.AudioMediaData.Data p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getGenre() {
        return null;
    }
    
    public final void setGenre(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTitle() {
        return null;
    }
    
    public final void setTitle(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAlbum() {
        return null;
    }
    
    public final void setAlbum(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSubTitle() {
        return null;
    }
    
    public final void setSubTitle(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.example.data.audio.AudioMediaData.MetaData getMetaData() {
        return null;
    }
    
    public final void setMetaData(@org.jetbrains.annotations.Nullable()
    com.example.data.audio.AudioMediaData.MetaData p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.example.data.audio.AudioMediaData.AuthorData getAuthorData() {
        return null;
    }
    
    public final void setAuthorData(@org.jetbrains.annotations.Nullable()
    com.example.data.audio.AudioMediaData.AuthorData p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.example.data.audio.AudioMediaData.ImageMetaData getImageMetaData() {
        return null;
    }
    
    public final void setImageMetaData(@org.jetbrains.annotations.Nullable()
    com.example.data.audio.AudioMediaData.ImageMetaData p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.example.data.audio.AudioMediaData.MediaMetaData getMediaMetaData() {
        return null;
    }
    
    public final void setMediaMetaData(@org.jetbrains.annotations.Nullable()
    com.example.data.audio.AudioMediaData.MediaMetaData p0) {
    }
    
    public AudioMediaData(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.Nullable()
    com.example.data.audio.AudioMediaData.Data data, @org.jetbrains.annotations.NotNull()
    java.lang.String genre, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String album, @org.jetbrains.annotations.NotNull()
    java.lang.String subTitle, @org.jetbrains.annotations.Nullable()
    com.example.data.audio.AudioMediaData.MetaData metaData, @org.jetbrains.annotations.Nullable()
    com.example.data.audio.AudioMediaData.AuthorData authorData, @org.jetbrains.annotations.Nullable()
    com.example.data.audio.AudioMediaData.ImageMetaData imageMetaData, @org.jetbrains.annotations.Nullable()
    com.example.data.audio.AudioMediaData.MediaMetaData mediaMetaData) {
        super();
    }
    
    public AudioMediaData() {
        super();
    }
    
    private AudioMediaData(com.example.data.audio.AudioMediaData.Builder builder) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.example.data.audio.AudioMediaData.Data component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.example.data.audio.AudioMediaData.MetaData component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.example.data.audio.AudioMediaData.AuthorData component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.example.data.audio.AudioMediaData.ImageMetaData component9() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.example.data.audio.AudioMediaData.MediaMetaData component10() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.data.audio.AudioMediaData copy(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.Nullable()
    com.example.data.audio.AudioMediaData.Data data, @org.jetbrains.annotations.NotNull()
    java.lang.String genre, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String album, @org.jetbrains.annotations.NotNull()
    java.lang.String subTitle, @org.jetbrains.annotations.Nullable()
    com.example.data.audio.AudioMediaData.MetaData metaData, @org.jetbrains.annotations.Nullable()
    com.example.data.audio.AudioMediaData.AuthorData authorData, @org.jetbrains.annotations.Nullable()
    com.example.data.audio.AudioMediaData.ImageMetaData imageMetaData, @org.jetbrains.annotations.Nullable()
    com.example.data.audio.AudioMediaData.MediaMetaData mediaMetaData) {
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
    
    @java.lang.Override()
    public int describeContents() {
        return 0;
    }
    
    @java.lang.Override()
    public void writeToParcel(@org.jetbrains.annotations.NotNull()
    android.os.Parcel parcel, int flags) {
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 3)
    public static final class Creator implements android.os.Parcelable.Creator {
        
        public Creator() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.Object[] newArray(int size) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.Object createFromParcel(@org.jetbrains.annotations.NotNull()
        android.os.Parcel in) {
            return null;
        }
    }
    
    @kotlinx.android.parcel.Parcelize()
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0007J\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\'\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003H\u00c6\u0001J\t\u0010\u0016\u001a\u00020\u0003H\u00d6\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u00d6\u0003J\t\u0010\u001b\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u001c\u001a\u00020\u0005H\u00d6\u0001J\u0019\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0003H\u00d6\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000f\u00a8\u0006\""}, d2 = {"Lcom/example/data/audio/AudioMediaData$Data;", "Landroid/os/Parcelable;", "id", "", "audioId", "", "totalNumber", "(ILjava/lang/String;I)V", "getAudioId", "()Ljava/lang/String;", "setAudioId", "(Ljava/lang/String;)V", "getId", "()I", "setId", "(I)V", "getTotalNumber", "setTotalNumber", "component1", "component2", "component3", "copy", "describeContents", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "daos_devDebug"})
    public static final class Data implements android.os.Parcelable {
        private int id;
        @org.jetbrains.annotations.NotNull()
        private java.lang.String audioId;
        private int totalNumber;
        public static final android.os.Parcelable.Creator CREATOR = null;
        
        public final int getId() {
            return 0;
        }
        
        public final void setId(int p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getAudioId() {
            return null;
        }
        
        public final void setAudioId(@org.jetbrains.annotations.NotNull()
        java.lang.String p0) {
        }
        
        public final int getTotalNumber() {
            return 0;
        }
        
        public final void setTotalNumber(int p0) {
        }
        
        public Data(int id, @org.jetbrains.annotations.NotNull()
        java.lang.String audioId, int totalNumber) {
            super();
        }
        
        public Data() {
            super();
        }
        
        public final int component1() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        public final int component3() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.data.audio.AudioMediaData.Data copy(int id, @org.jetbrains.annotations.NotNull()
        java.lang.String audioId, int totalNumber) {
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
        
        @java.lang.Override()
        public int describeContents() {
            return 0;
        }
        
        @java.lang.Override()
        public void writeToParcel(@org.jetbrains.annotations.NotNull()
        android.os.Parcel parcel, int flags) {
        }
        
        @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 3)
        public static final class Creator implements android.os.Parcelable.Creator {
            
            public Creator() {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.Object[] newArray(int size) {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.Object createFromParcel(@org.jetbrains.annotations.NotNull()
            android.os.Parcel in) {
                return null;
            }
        }
    }
    
    @kotlinx.android.parcel.Parcelize()
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\bJ\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0006H\u00c6\u0003J1\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u00c6\u0001J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001J\u0013\u0010\u001a\u001a\u00020\u00062\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u00d6\u0003J\t\u0010\u001d\u001a\u00020\u0019H\u00d6\u0001J\t\u0010\u001e\u001a\u00020\u0003H\u00d6\u0001J\u0019\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0019H\u00d6\u0001R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0007\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\t\"\u0004\b\f\u0010\u000bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u000e\"\u0004\b\u0012\u0010\u0010\u00a8\u0006$"}, d2 = {"Lcom/example/data/audio/AudioMediaData$MediaMetaData;", "Landroid/os/Parcelable;", "mediaId", "", "mediaUri", "isBrowsable", "", "isPlayable", "(Ljava/lang/String;Ljava/lang/String;ZZ)V", "()Z", "setBrowsable", "(Z)V", "setPlayable", "getMediaId", "()Ljava/lang/String;", "setMediaId", "(Ljava/lang/String;)V", "getMediaUri", "setMediaUri", "component1", "component2", "component3", "component4", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "daos_devDebug"})
    public static final class MediaMetaData implements android.os.Parcelable {
        @org.jetbrains.annotations.NotNull()
        private java.lang.String mediaId;
        @org.jetbrains.annotations.NotNull()
        private java.lang.String mediaUri;
        private boolean isBrowsable;
        private boolean isPlayable;
        public static final android.os.Parcelable.Creator CREATOR = null;
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getMediaId() {
            return null;
        }
        
        public final void setMediaId(@org.jetbrains.annotations.NotNull()
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getMediaUri() {
            return null;
        }
        
        public final void setMediaUri(@org.jetbrains.annotations.NotNull()
        java.lang.String p0) {
        }
        
        public final boolean isBrowsable() {
            return false;
        }
        
        public final void setBrowsable(boolean p0) {
        }
        
        public final boolean isPlayable() {
            return false;
        }
        
        public final void setPlayable(boolean p0) {
        }
        
        public MediaMetaData(@org.jetbrains.annotations.NotNull()
        java.lang.String mediaId, @org.jetbrains.annotations.NotNull()
        java.lang.String mediaUri, boolean isBrowsable, boolean isPlayable) {
            super();
        }
        
        public MediaMetaData() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        public final boolean component3() {
            return false;
        }
        
        public final boolean component4() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.data.audio.AudioMediaData.MediaMetaData copy(@org.jetbrains.annotations.NotNull()
        java.lang.String mediaId, @org.jetbrains.annotations.NotNull()
        java.lang.String mediaUri, boolean isBrowsable, boolean isPlayable) {
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
        
        @java.lang.Override()
        public int describeContents() {
            return 0;
        }
        
        @java.lang.Override()
        public void writeToParcel(@org.jetbrains.annotations.NotNull()
        android.os.Parcel parcel, int flags) {
        }
        
        @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 3)
        public static final class Creator implements android.os.Parcelable.Creator {
            
            public Creator() {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.Object[] newArray(int size) {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.Object createFromParcel(@org.jetbrains.annotations.NotNull()
            android.os.Parcel in) {
                return null;
            }
        }
    }
    
    @kotlinx.android.parcel.Parcelize()
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\t\u0010\u0012\u001a\u00020\u0005H\u00d6\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u00d6\u0003J\t\u0010\u0017\u001a\u00020\u0005H\u00d6\u0001J\t\u0010\u0018\u001a\u00020\u0003H\u00d6\u0001J\u0019\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0005H\u00d6\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e\u00a8\u0006\u001e"}, d2 = {"Lcom/example/data/audio/AudioMediaData$ImageMetaData;", "Landroid/os/Parcelable;", "imageUri", "", "imageDrawableRes", "", "(Ljava/lang/String;I)V", "getImageDrawableRes", "()I", "setImageDrawableRes", "(I)V", "getImageUri", "()Ljava/lang/String;", "setImageUri", "(Ljava/lang/String;)V", "component1", "component2", "copy", "describeContents", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "daos_devDebug"})
    public static final class ImageMetaData implements android.os.Parcelable {
        @org.jetbrains.annotations.NotNull()
        private java.lang.String imageUri;
        private int imageDrawableRes;
        public static final android.os.Parcelable.Creator CREATOR = null;
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getImageUri() {
            return null;
        }
        
        public final void setImageUri(@org.jetbrains.annotations.NotNull()
        java.lang.String p0) {
        }
        
        public final int getImageDrawableRes() {
            return 0;
        }
        
        public final void setImageDrawableRes(int p0) {
        }
        
        public ImageMetaData(@org.jetbrains.annotations.NotNull()
        java.lang.String imageUri, int imageDrawableRes) {
            super();
        }
        
        public ImageMetaData() {
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
        public final com.example.data.audio.AudioMediaData.ImageMetaData copy(@org.jetbrains.annotations.NotNull()
        java.lang.String imageUri, int imageDrawableRes) {
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
        
        @java.lang.Override()
        public int describeContents() {
            return 0;
        }
        
        @java.lang.Override()
        public void writeToParcel(@org.jetbrains.annotations.NotNull()
        android.os.Parcel parcel, int flags) {
        }
        
        @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 3)
        public static final class Creator implements android.os.Parcelable.Creator {
            
            public Creator() {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.Object[] newArray(int size) {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.Object createFromParcel(@org.jetbrains.annotations.NotNull()
            android.os.Parcel in) {
                return null;
            }
        }
    }
    
    @kotlinx.android.parcel.Parcelize()
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BU\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u00a2\u0006\u0002\u0010\rJ\t\u0010$\u001a\u00020\u0003H\u00c6\u0003J\t\u0010%\u001a\u00020\u0003H\u00c6\u0003J\t\u0010&\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\'\u001a\u00020\u0006H\u00c6\u0003J\t\u0010(\u001a\u00020\u0006H\u00c6\u0003J\t\u0010)\u001a\u00020\u0003H\u00c6\u0003J\t\u0010*\u001a\u00020\u0003H\u00c6\u0003J\t\u0010+\u001a\u00020\fH\u00c6\u0003JY\u0010,\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\fH\u00c6\u0001J\t\u0010-\u001a\u00020.H\u00d6\u0001J\u0013\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u000102H\u00d6\u0003J\t\u00103\u001a\u00020.H\u00d6\u0001J\t\u00104\u001a\u00020\u0003H\u00d6\u0001J\u0019\u00105\u001a\u0002062\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020.H\u00d6\u0001R\u001a\u0010\u0007\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\b\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000f\"\u0004\b\u0013\u0010\u0011R\u001a\u0010\t\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\n\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0015\"\u0004\b\u0019\u0010\u0017R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0015\"\u0004\b\u001b\u0010\u0017R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u000f\"\u0004\b\u001d\u0010\u0011R\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0015\"\u0004\b#\u0010\u0017\u00a8\u0006:"}, d2 = {"Lcom/example/data/audio/AudioMediaData$MetaData;", "Landroid/os/Parcelable;", "url", "", "format", "number", "", "audioDuration", "audioProgress", "displayableDuration", "displayableProgress", "playbackState", "Lcom/example/data/audio/AudioMediaData$PlaybackState;", "(Ljava/lang/String;Ljava/lang/String;JJJLjava/lang/String;Ljava/lang/String;Lcom/example/data/audio/AudioMediaData$PlaybackState;)V", "getAudioDuration", "()J", "setAudioDuration", "(J)V", "getAudioProgress", "setAudioProgress", "getDisplayableDuration", "()Ljava/lang/String;", "setDisplayableDuration", "(Ljava/lang/String;)V", "getDisplayableProgress", "setDisplayableProgress", "getFormat", "setFormat", "getNumber", "setNumber", "getPlaybackState", "()Lcom/example/data/audio/AudioMediaData$PlaybackState;", "setPlaybackState", "(Lcom/example/data/audio/AudioMediaData$PlaybackState;)V", "getUrl", "setUrl", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "daos_devDebug"})
    public static final class MetaData implements android.os.Parcelable {
        @org.jetbrains.annotations.NotNull()
        private java.lang.String url;
        @org.jetbrains.annotations.NotNull()
        private java.lang.String format;
        private long number;
        private long audioDuration;
        private long audioProgress;
        @org.jetbrains.annotations.NotNull()
        private java.lang.String displayableDuration;
        @org.jetbrains.annotations.NotNull()
        private java.lang.String displayableProgress;
        @org.jetbrains.annotations.NotNull()
        private com.example.data.audio.AudioMediaData.PlaybackState playbackState;
        public static final android.os.Parcelable.Creator CREATOR = null;
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getUrl() {
            return null;
        }
        
        public final void setUrl(@org.jetbrains.annotations.NotNull()
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getFormat() {
            return null;
        }
        
        public final void setFormat(@org.jetbrains.annotations.NotNull()
        java.lang.String p0) {
        }
        
        public final long getNumber() {
            return 0L;
        }
        
        public final void setNumber(long p0) {
        }
        
        public final long getAudioDuration() {
            return 0L;
        }
        
        public final void setAudioDuration(long p0) {
        }
        
        public final long getAudioProgress() {
            return 0L;
        }
        
        public final void setAudioProgress(long p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getDisplayableDuration() {
            return null;
        }
        
        public final void setDisplayableDuration(@org.jetbrains.annotations.NotNull()
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getDisplayableProgress() {
            return null;
        }
        
        public final void setDisplayableProgress(@org.jetbrains.annotations.NotNull()
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.data.audio.AudioMediaData.PlaybackState getPlaybackState() {
            return null;
        }
        
        public final void setPlaybackState(@org.jetbrains.annotations.NotNull()
        com.example.data.audio.AudioMediaData.PlaybackState p0) {
        }
        
        public MetaData(@org.jetbrains.annotations.NotNull()
        java.lang.String url, @org.jetbrains.annotations.NotNull()
        java.lang.String format, long number, long audioDuration, long audioProgress, @org.jetbrains.annotations.NotNull()
        java.lang.String displayableDuration, @org.jetbrains.annotations.NotNull()
        java.lang.String displayableProgress, @org.jetbrains.annotations.NotNull()
        com.example.data.audio.AudioMediaData.PlaybackState playbackState) {
            super();
        }
        
        public MetaData() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        public final long component3() {
            return 0L;
        }
        
        public final long component4() {
            return 0L;
        }
        
        public final long component5() {
            return 0L;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component6() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component7() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.data.audio.AudioMediaData.PlaybackState component8() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.data.audio.AudioMediaData.MetaData copy(@org.jetbrains.annotations.NotNull()
        java.lang.String url, @org.jetbrains.annotations.NotNull()
        java.lang.String format, long number, long audioDuration, long audioProgress, @org.jetbrains.annotations.NotNull()
        java.lang.String displayableDuration, @org.jetbrains.annotations.NotNull()
        java.lang.String displayableProgress, @org.jetbrains.annotations.NotNull()
        com.example.data.audio.AudioMediaData.PlaybackState playbackState) {
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
        
        @java.lang.Override()
        public int describeContents() {
            return 0;
        }
        
        @java.lang.Override()
        public void writeToParcel(@org.jetbrains.annotations.NotNull()
        android.os.Parcel parcel, int flags) {
        }
        
        @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 3)
        public static final class Creator implements android.os.Parcelable.Creator {
            
            public Creator() {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.Object[] newArray(int size) {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.Object createFromParcel(@org.jetbrains.annotations.NotNull()
            android.os.Parcel in) {
                return null;
            }
        }
    }
    
    @kotlinx.android.parcel.Parcelize()
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B%\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0007J\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0005H\u00c6\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J)\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001J\t\u0010\u0016\u001a\u00020\u0003H\u00d6\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u00d6\u0003J\t\u0010\u001b\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u001c\u001a\u00020\u0005H\u00d6\u0001J\u0019\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0003H\u00d6\u0001R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000b\u00a8\u0006\""}, d2 = {"Lcom/example/data/audio/AudioMediaData$AuthorData;", "Landroid/os/Parcelable;", "id", "", "name", "", "detail", "(ILjava/lang/String;Ljava/lang/String;)V", "getDetail", "()Ljava/lang/String;", "setDetail", "(Ljava/lang/String;)V", "getId", "()I", "setId", "(I)V", "getName", "setName", "component1", "component2", "component3", "copy", "describeContents", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "daos_devDebug"})
    public static final class AuthorData implements android.os.Parcelable {
        private int id;
        @org.jetbrains.annotations.NotNull()
        private java.lang.String name;
        @org.jetbrains.annotations.Nullable()
        private java.lang.String detail;
        public static final android.os.Parcelable.Creator CREATOR = null;
        
        public final int getId() {
            return 0;
        }
        
        public final void setId(int p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getName() {
            return null;
        }
        
        public final void setName(@org.jetbrains.annotations.NotNull()
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getDetail() {
            return null;
        }
        
        public final void setDetail(@org.jetbrains.annotations.Nullable()
        java.lang.String p0) {
        }
        
        public AuthorData(int id, @org.jetbrains.annotations.NotNull()
        java.lang.String name, @org.jetbrains.annotations.Nullable()
        java.lang.String detail) {
            super();
        }
        
        public AuthorData() {
            super();
        }
        
        public final int component1() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.data.audio.AudioMediaData.AuthorData copy(int id, @org.jetbrains.annotations.NotNull()
        java.lang.String name, @org.jetbrains.annotations.Nullable()
        java.lang.String detail) {
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
        
        @java.lang.Override()
        public int describeContents() {
            return 0;
        }
        
        @java.lang.Override()
        public void writeToParcel(@org.jetbrains.annotations.NotNull()
        android.os.Parcel parcel, int flags) {
        }
        
        @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 3)
        public static final class Creator implements android.os.Parcelable.Creator {
            
            public Creator() {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.Object[] newArray(int size) {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.Object createFromParcel(@org.jetbrains.annotations.NotNull()
            android.os.Parcel in) {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\n\b\u0086\u0001\u0018\u0000 \f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\fB\u0011\b\u0002\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b\u00a8\u0006\r"}, d2 = {"Lcom/example/data/audio/AudioMediaData$PlaybackState;", "", "imageRes", "", "(Ljava/lang/String;II)V", "getImageRes", "()I", "setImageRes", "(I)V", "PLAYING", "PAUSE", "NONE", "Companion", "daos_devDebug"})
    public static enum PlaybackState {
        /*public static final*/ PLAYING /* = new PLAYING(0) */,
        /*public static final*/ PAUSE /* = new PAUSE(0) */,
        /*public static final*/ NONE /* = new NONE(0) */;
        private int imageRes;
        public static final com.example.data.audio.AudioMediaData.PlaybackState.Companion Companion = null;
        
        public final int getImageRes() {
            return 0;
        }
        
        public final void setImageRes(int p0) {
        }
        
        PlaybackState(int imageRes) {
        }
        
        @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004\u00a8\u0006\t"}, d2 = {"Lcom/example/data/audio/AudioMediaData$PlaybackState$Companion;", "", "()V", "get", "Lcom/example/data/audio/AudioMediaData$PlaybackState;", "playing", "", "playState", "pauseState", "daos_devDebug"})
        public static final class Companion {
            
            @org.jetbrains.annotations.NotNull()
            public final com.example.data.audio.AudioMediaData.PlaybackState get(boolean playing, @org.jetbrains.annotations.NotNull()
            com.example.data.audio.AudioMediaData.PlaybackState playState, @org.jetbrains.annotations.NotNull()
            com.example.data.audio.AudioMediaData.PlaybackState pauseState) {
                return null;
            }
            
            private Companion() {
                super();
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010-\u001a\u00020.R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001c\u0010!\u001a\u0004\u0018\u00010\"X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001a\u0010\'\u001a\u00020\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0012\"\u0004\b)\u0010\u0014R\u001a\u0010*\u001a\u00020\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u0012\"\u0004\b,\u0010\u0014\u00a8\u0006/"}, d2 = {"Lcom/example/data/audio/AudioMediaData$Builder;", "", "()V", "authorData", "Lcom/example/data/audio/AudioMediaData$AuthorData;", "getAuthorData", "()Lcom/example/data/audio/AudioMediaData$AuthorData;", "setAuthorData", "(Lcom/example/data/audio/AudioMediaData$AuthorData;)V", "data", "Lcom/example/data/audio/AudioMediaData$Data;", "getData", "()Lcom/example/data/audio/AudioMediaData$Data;", "setData", "(Lcom/example/data/audio/AudioMediaData$Data;)V", "id", "", "getId", "()Ljava/lang/String;", "setId", "(Ljava/lang/String;)V", "imageMetaData", "Lcom/example/data/audio/AudioMediaData$ImageMetaData;", "getImageMetaData", "()Lcom/example/data/audio/AudioMediaData$ImageMetaData;", "setImageMetaData", "(Lcom/example/data/audio/AudioMediaData$ImageMetaData;)V", "mediaMetaData", "Lcom/example/data/audio/AudioMediaData$MediaMetaData;", "getMediaMetaData", "()Lcom/example/data/audio/AudioMediaData$MediaMetaData;", "setMediaMetaData", "(Lcom/example/data/audio/AudioMediaData$MediaMetaData;)V", "metaData", "Lcom/example/data/audio/AudioMediaData$MetaData;", "getMetaData", "()Lcom/example/data/audio/AudioMediaData$MetaData;", "setMetaData", "(Lcom/example/data/audio/AudioMediaData$MetaData;)V", "subTitle", "getSubTitle", "setSubTitle", "title", "getTitle", "setTitle", "build", "Lcom/example/data/audio/AudioMediaData;", "daos_devDebug"})
    public static final class Builder {
        @org.jetbrains.annotations.Nullable()
        private com.example.data.audio.AudioMediaData.Data data;
        @org.jetbrains.annotations.NotNull()
        private java.lang.String id = "";
        @org.jetbrains.annotations.Nullable()
        private com.example.data.audio.AudioMediaData.MetaData metaData;
        @org.jetbrains.annotations.NotNull()
        private java.lang.String title = "";
        @org.jetbrains.annotations.Nullable()
        private com.example.data.audio.AudioMediaData.AuthorData authorData;
        @org.jetbrains.annotations.NotNull()
        private java.lang.String subTitle = "";
        @org.jetbrains.annotations.Nullable()
        private com.example.data.audio.AudioMediaData.MediaMetaData mediaMetaData;
        @org.jetbrains.annotations.Nullable()
        private com.example.data.audio.AudioMediaData.ImageMetaData imageMetaData;
        
        @org.jetbrains.annotations.Nullable()
        public final com.example.data.audio.AudioMediaData.Data getData() {
            return null;
        }
        
        public final void setData(@org.jetbrains.annotations.Nullable()
        com.example.data.audio.AudioMediaData.Data p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getId() {
            return null;
        }
        
        public final void setId(@org.jetbrains.annotations.NotNull()
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.Nullable()
        public final com.example.data.audio.AudioMediaData.MetaData getMetaData() {
            return null;
        }
        
        public final void setMetaData(@org.jetbrains.annotations.Nullable()
        com.example.data.audio.AudioMediaData.MetaData p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getTitle() {
            return null;
        }
        
        public final void setTitle(@org.jetbrains.annotations.NotNull()
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.Nullable()
        public final com.example.data.audio.AudioMediaData.AuthorData getAuthorData() {
            return null;
        }
        
        public final void setAuthorData(@org.jetbrains.annotations.Nullable()
        com.example.data.audio.AudioMediaData.AuthorData p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getSubTitle() {
            return null;
        }
        
        public final void setSubTitle(@org.jetbrains.annotations.NotNull()
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.Nullable()
        public final com.example.data.audio.AudioMediaData.MediaMetaData getMediaMetaData() {
            return null;
        }
        
        public final void setMediaMetaData(@org.jetbrains.annotations.Nullable()
        com.example.data.audio.AudioMediaData.MediaMetaData p0) {
        }
        
        @org.jetbrains.annotations.Nullable()
        public final com.example.data.audio.AudioMediaData.ImageMetaData getImageMetaData() {
            return null;
        }
        
        public final void setImageMetaData(@org.jetbrains.annotations.Nullable()
        com.example.data.audio.AudioMediaData.ImageMetaData p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.data.audio.AudioMediaData build() {
            return null;
        }
        
        public Builder() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J$\u0010\u0003\u001a\u00020\u00042\u0019\b\u0004\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006\u00a2\u0006\u0002\b\tH\u0086\b\u00a8\u0006\n"}, d2 = {"Lcom/example/data/audio/AudioMediaData$Companion;", "", "()V", "build", "Lcom/example/data/audio/AudioMediaData;", "block", "Lkotlin/Function1;", "Lcom/example/data/audio/AudioMediaData$Builder;", "", "Lkotlin/ExtensionFunctionType;", "daos_devDebug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.data.audio.AudioMediaData build(@org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super com.example.data.audio.AudioMediaData.Builder, kotlin.Unit> block) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}