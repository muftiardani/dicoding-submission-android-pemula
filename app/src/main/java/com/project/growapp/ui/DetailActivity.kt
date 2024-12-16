package com.project.growapp.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.project.growapp.R
import com.project.growapp.data.Organization

class DetailActivity : AppCompatActivity() {
    companion object {
        const val KEY_ORGANIZATION = "key_organization"
    }

    private lateinit var imgPhoto: ImageView
    private lateinit var tvName: TextView
    private lateinit var tvDescription: TextView
    private lateinit var tvTitle: TextView
    private lateinit var btnShare: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setupActionBar()
        initViews()
        loadOrganizationData()
        setupShareButton()
    }

    private fun setupActionBar() {
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun initViews() {
        imgPhoto = findViewById(R.id.logo)
        tvName = findViewById(R.id.tv_name)
        tvDescription = findViewById(R.id.tv_detail_description)
        tvTitle = findViewById(R.id.tv_title)
        btnShare = findViewById(R.id.action_share)
    }

    private fun loadOrganizationData() {
        val organization = getOrganizationFromIntent()
        organization?.let { displayOrganizationData(it) }
    }

    private fun getOrganizationFromIntent(): Organization? {
        return if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(KEY_ORGANIZATION, Organization::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(KEY_ORGANIZATION)
        }
    }

    private fun displayOrganizationData(data: Organization) {
        imgPhoto.setImageResource(data.photo)
        tvName.text = data.name
        tvDescription.text = data.description
        tvTitle.text = data.title
    }

    private fun setupShareButton() {
        btnShare.setOnClickListener {
            shareToWhatsApp()
        }
    }

    private fun shareToWhatsApp() {
        val message = buildShareMessage()
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, message)
            setPackage("com.whatsapp")
        }

        try {
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "Tidak ada koneksi internet", Toast.LENGTH_SHORT).show()
        }
    }

    private fun buildShareMessage(): String {
        return "Nama Organization : ${tvName.text}\nArtikel :\n\t${tvDescription.text}"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}