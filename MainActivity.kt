package com.ecom.myfinances

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ecom.myfinances.model.CD
import com.ecom.myfinances.model.Loan
import com.ecom.myfinances.model.CheckingAccount
import com.ecom.myfinances.viewmodel.FinancialViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: FinancialViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val accountTypeRadioGroup = findViewById<RadioGroup>(R.id.accountTypeRadioGroup)
        val accountNumber = findViewById<EditText>(R.id.accountNumber)
        val initialBalance = findViewById<EditText>(R.id.initialBalance)
        val currentBalance = findViewById<EditText>(R.id.currentBalance)
        val interestRate = findViewById<EditText>(R.id.interestRate)
        val paymentAmount = findViewById<EditText>(R.id.paymentAmount)
        val saveButton = findViewById<Button>(R.id.saveButton)
        val cancelButton = findViewById<Button>(R.id.cancelButton)
        val viewRecordsButton = findViewById<Button>(R.id.viewRecordsButton)

        accountTypeRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioCD -> {
                    initialBalance.visibility = View.VISIBLE
                    currentBalance.visibility = View.VISIBLE
                    interestRate.visibility = View.VISIBLE
                    paymentAmount.visibility = View.GONE
                }
                R.id.radioLoan -> {
                    initialBalance.visibility = View.VISIBLE
                    currentBalance.visibility = View.VISIBLE
                    interestRate.visibility = View.VISIBLE
                    paymentAmount.visibility = View.VISIBLE
                }
                R.id.radioChecking -> {
                    initialBalance.visibility = View.GONE
                    currentBalance.visibility = View.VISIBLE
                    interestRate.visibility = View.GONE
                    paymentAmount.visibility = View.GONE
                }
            }
        }

        saveButton.setOnClickListener {
            val accountNumberText = accountNumber.text.toString()
            val initialBalanceText = initialBalance.text.toString().toDoubleOrNull()
            val currentBalanceText = currentBalance.text.toString().toDoubleOrNull()
            val interestRateText = interestRate.text.toString().toDoubleOrNull()
            val paymentAmountText = paymentAmount.text.toString().toDoubleOrNull()

            when (accountTypeRadioGroup.checkedRadioButtonId) {
                R.id.radioCD -> {
                    if (accountNumberText.isNotEmpty() && initialBalanceText != null && currentBalanceText != null && interestRateText != null) {
                        val cd = CD(accountNumber = accountNumberText, initialBalance = initialBalanceText, currentBalance = currentBalanceText, interestRate = interestRateText)
                        viewModel.insertCD(cd)
                        Toast.makeText(this, "CD Saved", Toast.LENGTH_SHORT).show()
                    }
                }
                R.id.radioLoan -> {
                    if (accountNumberText.isNotEmpty() && initialBalanceText != null && currentBalanceText != null && interestRateText != null && paymentAmountText != null) {
                        val loan = Loan(accountNumber = accountNumberText, initialBalance = initialBalanceText, currentBalance = currentBalanceText, paymentAmount = paymentAmountText, interestRate = interestRateText)
                        viewModel.insertLoan(loan)
                        Toast.makeText(this, "Loan Saved", Toast.LENGTH_SHORT).show()
                    }
                }
                R.id.radioChecking -> {
                    if (accountNumberText.isNotEmpty() && currentBalanceText != null) {
                        val checkingAccount = CheckingAccount(accountNumber = accountNumberText, currentBalance = currentBalanceText)
                        viewModel.insertCheckingAccount(checkingAccount)
                        Toast.makeText(this, "Checking Account Saved", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            clearFields(accountNumber, initialBalance, currentBalance, interestRate, paymentAmount)
        }

        cancelButton.setOnClickListener {
            clearFields(accountNumber, initialBalance, currentBalance, interestRate, paymentAmount)
        }

        viewRecordsButton.setOnClickListener {
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }
    }

    private fun clearFields(
        accountNumber: EditText,
        initialBalance: EditText,
        currentBalance: EditText,
        interestRate: EditText,
        paymentAmount: EditText
    ) {
        accountNumber.text.clear()
        initialBalance.text.clear()
        currentBalance.text.clear()
        interestRate.text.clear()
        paymentAmount.text.clear()
    }
}