package com.example.ocadochallenge.di

import com.example.ocadochallenge.domain.imageloader.GlideImplementation
import com.example.ocadochallenge.domain.imageloader.ImagesLoader
import dagger.Binds
import dagger.Module

@Module
interface ImageLoaderModule {
    @Binds
    fun bindImagesLoader(imagesLoader: GlideImplementation): ImagesLoader
}