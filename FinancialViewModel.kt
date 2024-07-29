package com.ecom.myfinances.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ecom.myfinances.database.DatabaseProvider
import com.ecom.myfinances.model.CD
import com.ecom.myfinances.model.Loan
import com.ecom.myfinances.model.CheckingAccount
import kotlinx.coroutines.launch
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

class FinancialViewModel(application: Application) : AndroidViewModel(application) {
    private val db = DatabaseProvider.getDatabase(application)

    private val cds = db.cdDao().getAllCDs()
    private val loans = db.loanDao().getAllLoans()
    private val checkingAccounts = db.checkingAccountDao().getAllCheckingAccounts()

    val allFinancialRecords: LiveData<List<String>> = MediatorLiveData<List<String>>().apply {
        fun updateRecords() {
            val records = mutableListOf<String>()
            cds.value?.let { cdsList ->
                cdsList.forEach { records.add("CD: $it") }
            }
            loans.value?.let { loansList ->
                loansList.forEach { records.add("Loan: $it") }
            }
            checkingAccounts.value?.let { checkingAccountsList ->
                checkingAccountsList.forEach { records.add("Checking: $it") }
            }
            value = records
        }

        addSource(cds) { updateRecords() }
        addSource(loans) { updateRecords() }
        addSource(checkingAccounts) { updateRecords() }
    }

    fun insertCD(cd: CD) = viewModelScope.launch {
        db.cdDao().insert(cd)
    }

    fun insertLoan(loan: Loan) = viewModelScope.launch {
        db.loanDao().insert(loan)
    }

    fun insertCheckingAccount(checkingAccount: CheckingAccount) = viewModelScope.launch {
        db.checkingAccountDao().insert(checkingAccount)
    }
}