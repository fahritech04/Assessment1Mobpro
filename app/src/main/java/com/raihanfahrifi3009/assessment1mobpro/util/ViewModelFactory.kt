package com.raihanfahrifi3009.assessment1mobpro.util

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.raihanfahrifi3009.assessment1mobpro.database.BankDataDb
import com.raihanfahrifi3009.assessment1mobpro.ui.screen.BankViewModel

class ViewModelFactory(private val context: Context): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val dao = BankDataDb.getInstance(context).dao
        if(modelClass.isAssignableFrom(BankViewModel::class.java)) {
            return BankViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel class")
    }
}