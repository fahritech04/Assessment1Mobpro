package com.raihanfahrifi3009.assessment1mobpro.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.raihanfahrifi3009.assessment1mobpro.model.BankData


@Database(entities = [BankData::class], version = 1, exportSchema = false)
abstract class BankDataDb : RoomDatabase(){

    abstract val dao: BankDataDAO

    companion object {

        @Volatile
        private var INSTANCE: BankDataDb? = null

        fun getInstance(context: Context): BankDataDb {
            synchronized(this) {
                var  instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BankDataDb::class.java,
                        "bankdata.db"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }

    }

}