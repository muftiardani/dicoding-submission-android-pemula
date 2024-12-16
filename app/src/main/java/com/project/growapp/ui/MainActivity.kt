package com.project.growapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.growapp.R
import com.project.growapp.adapter.ListOrganizationAdapter
import com.project.growapp.data.Organization

class MainActivity : AppCompatActivity() {
    private lateinit var rvOrganizations: RecyclerView
    private val list = ArrayList<Organization>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBar()
        setupRecyclerView()
        populateData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about_page -> {
                navigateToAbout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupActionBar() {
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun setupRecyclerView() {
        rvOrganizations = findViewById(R.id.rv_organizations)
        rvOrganizations.setHasFixedSize(true)
        rvOrganizations.layoutManager = LinearLayoutManager(this)
    }

    private fun populateData() {
        list.addAll(getListOrganizations())
        showRecycleList()
    }

    private fun navigateToAbout() {
        val intent = Intent(this@MainActivity, AboutActivity::class.java)
        startActivity(intent)
    }

    private fun getListOrganizations(): ArrayList<Organization> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataTitle = resources.getStringArray(R.array.data_title)

        return ArrayList<Organization>().apply {
            for (i in dataName.indices) {
                add(Organization(
                    dataName[i],
                    dataDescription[i],
                    dataPhoto.getResourceId(i, -1),
                    dataTitle[i]
                ))
            }
            dataPhoto.recycle()
        }
    }

    private fun showRecycleList() {
        rvOrganizations.adapter = ListOrganizationAdapter(list)
    }
}