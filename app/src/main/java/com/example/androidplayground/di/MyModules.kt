package com.example.androidplayground.di

import com.example.di.ApiModule
import com.example.di.RepositoryModule
import com.example.di.ServiceModule
import com.example.di.UseCaseModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module (
    includes = [
        ApiModule::class,
        RepositoryModule::class,
        ServiceModule::class,
        UseCaseModule::class
    ]
)
@InstallIn(SingletonComponent::class)
class MyModules {
}