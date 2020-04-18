package com.example.app.di

import com.example.home.di.homeModule
import com.example.splash.di.splashModule

val appModule = splashModule + homeModule