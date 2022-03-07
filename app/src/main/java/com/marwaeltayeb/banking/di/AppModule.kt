package com.marwaeltayeb.banking.di

import android.content.Context
import com.marwaeltayeb.banking.data.BankRepository
import com.marwaeltayeb.banking.data.db.BankDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideBankRepository(db: BankDatabase): BankRepository {
        return BankRepository(db.bankDao())
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context, scope: CoroutineScope): BankDatabase {
        return BankDatabase.getDatabase(context, scope)
    }

    @Provides
    @Singleton
    fun providesApplicationScope() = CoroutineScope(SupervisorJob())
}