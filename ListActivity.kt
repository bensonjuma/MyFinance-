package com.ecom.myfinances

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ecom.myfinances.viewmodel.FinancialViewModel

class ListActivity : AppCompatActivity() {

    private val viewModel: FinancialViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val listView = findViewById<ListView>(R.id.financialListView)

        viewModel.allFinancialRecords.observe(this, { records ->
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, records)
            listView.adapter = adapter
        })
    }
}