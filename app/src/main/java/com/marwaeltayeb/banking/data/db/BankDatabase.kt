package com.marwaeltayeb.banking.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.marwaeltayeb.banking.data.model.Client
import com.marwaeltayeb.banking.data.model.Transaction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Client::class, Transaction::class], version = 1)
abstract class BankDatabase : RoomDatabase(){

    abstract fun bankDao(): BankDao

    companion object {
        @Volatile
        private var INSTANCE: BankDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): BankDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, BankDatabase::class.java, "bank_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(BankDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class BankDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.bankDao())
                    }
                }
            }
        }

        suspend fun populateDatabase(bankDao: BankDao) {
            //bankDao.deleteAll()

            var client = Client("John", "john@gamil.com", "34323342", 4000.00)
            bankDao.insertClient(client)
            client = Client("Emily", "emily@gamil.com", "98347844", 2000.00)
            bankDao.insertClient(client)
            client = Client("Chris", "chris@gamil.com", "87344375", 9000.00)
            bankDao.insertClient(client)
            client = Client("Mary", "mary@gamil.com", "4554345", 16000.00)
            bankDao.insertClient(client)
            client = Client("Jennifer", "jennifer@gamil.com", "9454845", 80000.00)
            bankDao.insertClient(client)

            client = Client("Robert", "robert@gamil.com", "78343932", 1000.00)
            bankDao.insertClient(client)
            client = Client("James", "james@gamil.com", "45643545", 3000.00)
            bankDao.insertClient(client)
            client = Client("Susan", "susan@gamil.com", "99993456", 50000.00)
            bankDao.insertClient(client)
            client = Client("Jessica", "jessica@gamil.com", "4345535", 7000.00)
            bankDao.insertClient(client)
            client = Client("Steven", "steven@gamil.com", "0309348", 5000.00)
            bankDao.insertClient(client)
        }
    }
}