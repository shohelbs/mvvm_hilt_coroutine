package com.mvvm_hilt_coroutine.di

import com.mvvm_hilt_coroutine.repository.ProductsRepository
import com.mvvm_hilt_coroutine.repository.ProductsRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindPhotoPickerRepository(repository: ProductsRepositoryImp): ProductsRepository
}