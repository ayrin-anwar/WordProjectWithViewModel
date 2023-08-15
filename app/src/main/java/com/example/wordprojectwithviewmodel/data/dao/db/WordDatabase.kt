package com.example.wordprojectwithviewmodel.data.dao.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.wordprojectwithviewmodel.data.dao.Word
import com.example.wordprojectwithviewmodel.data.dao.WordDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Word::class), version = 1, exportSchema = false)
public abstract class WordDatabase: RoomDatabase() {
    abstract fun wordDao():WordDao

    private class WordDatabaseCallback(private val scope: CoroutineScope): RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let {
                wordDatabase -> {
                    scope.launch {
                        var wordDao = wordDatabase.wordDao()
                        wordDao.deleteAll()
                        var word = Word("Hello")
                        wordDao.insertWord(word)
                        var word1 = Word("Ayrin")
                        wordDao.insertWord(word1)

                    }
            }
            }
        }
    }
    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE:WordDatabase ?= null

        fun getDatabase(context: Context,scope: CoroutineScope): WordDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordDatabase::class.java,
                    "word_database"
                ).addCallback(WordDatabaseCallback(scope)).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}