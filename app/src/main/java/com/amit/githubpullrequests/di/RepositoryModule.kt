package com.amit.githubpullrequests.di

import com.amit.githubpullrequests.data.repository.GithubPrRepositoryImpl
import com.amit.githubpullrequests.domain.repository.GithubPrRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindGithubRepository(
        githubPrRepositoryImpl: GithubPrRepositoryImpl
    ): GithubPrRepository
}