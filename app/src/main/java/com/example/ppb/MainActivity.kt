package com.example.ppb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.lifecycle.MutableLiveData
import com.example.ppb.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val firestore = FirebaseFirestore.getInstance()
    private val budgetCollectionRef = firestore.collection("budgets")
//    private val firestore by lazy {
//        FirebaseFirestore.getInstance()
//    }
//    private val budgetCollectionRef by lazy {
//        firestore.collection("budgets")
//    }
    private var updateId = ""
    private val budgetListLiveData: MutableLiveData<List<Budget>> by lazy {
        MutableLiveData<List<Budget>>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
//        FirebaseApp.initializeApp(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        observeBudgets()
        getAllBudgets()
        with(binding) {
            btnAdd.setOnClickListener {
                val nominal = editNominal.text.toString()
                val desc = editDesc.text.toString()
                val date = editDate.text.toString()
                val newBudget = Budget(nominal = nominal, description = desc, date = date)
                addBudget(newBudget)
            }
            btnUpdate.setOnClickListener {
                val nominal = editNominal.text.toString()
                val desc = editDesc.text.toString()
                val date = editDate.text.toString()
                val updatedBudget = Budget(nominal = nominal, description = desc, date = date)
                updateBudget(updatedBudget)
                updateId = ""
                setEmptyFields()
            }
            listView.setOnItemClickListener { adapterView, view, i, l ->
                val item = adapterView.adapter.getItem(i) as Budget
                updateId = item.id
                editNominal.setText(item.nominal)
                editDesc.setText(item.description)
                editDate.setText(item.date)
            }
        }
    }

    private fun getAllBudgets() {
        observeBudgetChanges();
    }

    private fun observeBudgetChanges() {
        budgetCollectionRef.addSnapshotListener { value, error ->
            if (error != null) {
                Log.d("MainActivityLOG", "Error listening for budget changes: $error")
            }
            val budgets = value?.toObjects(Budget::class.java)
            if (budgets != null) {
                budgetListLiveData.postValue(budgets)
            }
        }
    }
    private fun observeBudgets() {
        budgetListLiveData.observe(this) {
            budgets ->
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, budgets.toMutableList())
            binding.listView.adapter = adapter
        }
    }
    private fun addBudget(budget: Budget) {
        budgetCollectionRef.add(budget).addOnSuccessListener {
            documentReference ->
            val createdBudgetId = documentReference.id
            budget.id = createdBudgetId
            documentReference.set(budget).addOnFailureListener {
                Log.d("MainActivityLOG", "Error updating budget id: $it")
            }
        }.addOnFailureListener {
            Log.d("MainActivityLOG", "Error adding budget id: $it")
        }
    }
    private fun updateBudget(budget: Budget) {
        budget.id = updateId
        budgetCollectionRef.document(updateId).set(budget).addOnFailureListener {
            Log.d("MainActivityLOG", "Error updating budget: $it")
        }
    }
    private fun deleteBudget(budget: Budget) {
        if (budget.id.isEmpty()) {
            Log.d("MainActivityLOG", "Error deleting budget: empty id")
        }
        budgetCollectionRef.document(budget.id).delete().addOnFailureListener {
            Log.d("MainActivityLOG", "Error deleting budget: $it")
        }
    }
    private fun setEmptyFields() {
        with(binding) {
            editNominal.setText("")
            editDesc.setText("")
            editDate.setText("")
        }
    }
}