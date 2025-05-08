package com.raihanfahrifi3009.assessment1mobpro.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raihanfahrifi3009.assessment1mobpro.database.BankDataDAO
import com.raihanfahrifi3009.assessment1mobpro.model.BankData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class BankViewModel(private val dao: BankDataDAO) : ViewModel() {

    private val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)

    val data: StateFlow<List<BankData>> = dao.getDataBank().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun insert(namabank: String, isi: String){
        val bankData = BankData(
            tanggal = formatter.format(Date()),
            namabank = namabank,
            catatan = isi
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(bankData)
        }

    }

    suspend fun getDataBank(id: Long): BankData? {
        return dao.getBankDataById(id)
    }

    fun update(id: Long, namabank: String, isi: String){
        val catatan = BankData(
            id = id,
            tanggal = formatter.format(Date()),
            namabank = namabank,
            catatan = isi
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.update(catatan)
        }
    }

    fun delete(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteBankDataById(id)
        }
    }
}