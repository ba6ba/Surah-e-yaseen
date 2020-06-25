package com.example.storage.converters;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0017\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00018\u0000H&\u00a2\u0006\u0002\u0010\nJ\u0017\u0010\u000b\u001a\u0004\u0018\u00018\u00002\u0006\u0010\f\u001a\u00020\bH&\u00a2\u0006\u0002\u0010\rR\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u000e"}, d2 = {"Lcom/example/storage/converters/BaseConverter;", "T", "", "listType", "Ljava/lang/reflect/Type;", "getListType", "()Ljava/lang/reflect/Type;", "fromList", "", "list", "(Ljava/lang/Object;)Ljava/lang/String;", "fromString", "value", "(Ljava/lang/String;)Ljava/lang/Object;", "storage_devDebug"})
public abstract interface BaseConverter<T extends java.lang.Object> {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.reflect.Type getListType();
    
    @org.jetbrains.annotations.Nullable()
    public abstract T fromString(@org.jetbrains.annotations.NotNull()
    java.lang.String value);
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.lang.String fromList(@org.jetbrains.annotations.Nullable()
    T list);
}