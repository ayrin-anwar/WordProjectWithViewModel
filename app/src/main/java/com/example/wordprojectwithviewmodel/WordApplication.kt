package com.example.wordprojectwithviewmodel

import android.app.Application
import com.example.wordprojectwithviewmodel.data.dao.db.WordDatabase
import com.example.wordprojectwithviewmodel.data.dao.repository.WordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob


class WordApplication: Application() {
    val application = CoroutineScope(SupervisorJob())
    val database by lazy {WordDatabase.getDatabase(this,application)}
    val repository by lazy{WordRepository(database.wordDao())}
}