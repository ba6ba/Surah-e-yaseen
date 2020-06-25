package com.example.data;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\b\u0018\u0000 \u00182\u00020\u0001:\u0002\u0018\u0019B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0005H\u00d6\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e\u00a8\u0006\u001a"}, d2 = {"Lcom/example/data/GenericError;", "", "severity", "Lcom/example/data/GenericError$Severity;", "message", "", "(Lcom/example/data/GenericError$Severity;Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "setMessage", "(Ljava/lang/String;)V", "getSeverity", "()Lcom/example/data/GenericError$Severity;", "setSeverity", "(Lcom/example/data/GenericError$Severity;)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "Severity", "daos_devDebug"})
public final class GenericError {
    @org.jetbrains.annotations.NotNull()
    private com.example.data.GenericError.Severity severity;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String message;
    public static final com.example.data.GenericError.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.data.GenericError.Severity getSeverity() {
        return null;
    }
    
    public final void setSeverity(@org.jetbrains.annotations.NotNull()
    com.example.data.GenericError.Severity p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMessage() {
        return null;
    }
    
    public final void setMessage(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public GenericError(@org.jetbrains.annotations.NotNull()
    com.example.data.GenericError.Severity severity, @org.jetbrains.annotations.NotNull()
    java.lang.String message) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.data.GenericError.Severity component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.data.GenericError copy(@org.jetbrains.annotations.NotNull()
    com.example.data.GenericError.Severity severity, @org.jetbrains.annotations.NotNull()
    java.lang.String message) {
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
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/example/data/GenericError$Severity;", "", "(Ljava/lang/String;I)V", "LOW", "HIGH", "daos_devDebug"})
    public static enum Severity {
        /*public static final*/ LOW /* = new LOW() */,
        /*public static final*/ HIGH /* = new HIGH() */;
        
        Severity() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\b"}, d2 = {"Lcom/example/data/GenericError$Companion;", "", "()V", "highSeverityError", "Lcom/example/data/GenericError;", "message", "", "lowSeverityError", "daos_devDebug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.data.GenericError lowSeverityError(@org.jetbrains.annotations.NotNull()
        java.lang.String message) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.data.GenericError highSeverityError(@org.jetbrains.annotations.NotNull()
        java.lang.String message) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}