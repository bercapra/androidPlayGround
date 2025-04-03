package com.example.androidplayground.di

import com.example.di.DataSourceModule
import com.example.di.RepositoryModule
import com.example.di.UseCaseModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module (
    includes = [
        UseCaseModule::class,
        RepositoryModule::class,
        DataSourceModule::class
    ]
)
@InstallIn(SingletonComponent::class)
class MyModules {
}