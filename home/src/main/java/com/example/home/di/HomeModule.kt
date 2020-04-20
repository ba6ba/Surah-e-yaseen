package com.example.home.di

import com.example.recitation.di.recitationModule
import com.example.tilawat.di.tilawatModule
import org.koin.dsl.module

val homeModule = tilawatModule + recitationModule