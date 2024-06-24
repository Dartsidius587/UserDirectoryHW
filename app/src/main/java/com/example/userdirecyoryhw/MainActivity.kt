package com.example.userdirecyoryhw

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    var listUsers: MutableList<User> = mutableListOf()

    private lateinit var toolBarMainTB: Toolbar
    private lateinit var nameET: EditText
    private lateinit var ageET: EditText
    private lateinit var saveBTN: Button
    private lateinit var listUsersLV: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listUsers)
        listUsersLV.adapter = adapter
        listUsersLV.onItemClickListener =
            AdapterView.OnItemClickListener { parent, v, position, id ->
                val user = adapter.getItem(position)
                adapter.remove(user)
                Toast.makeText(this, "Запись о пользователе: $user удалена.", Toast.LENGTH_LONG)
                    .show()
            }

        saveBTN.setOnClickListener {
            listUsers.add(User(nameET.text.toString(), ageET.text.toString().toInt()))
            adapter.notifyDataSetChanged()
            nameET.text.clear()
            ageET.text.clear()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_exit -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun init() {
        toolBarMainTB = findViewById(R.id.toolBarMainTB)
        setSupportActionBar(toolBarMainTB)
        title = "Каталог пользователей"
        toolBarMainTB.setLogo(R.drawable.users_ic)

        nameET = findViewById(R.id.nameET)
        ageET = findViewById(R.id.ageET)
        saveBTN = findViewById(R.id.saveBTN)
        listUsersLV = findViewById(R.id.listUsersLV)
    }
}