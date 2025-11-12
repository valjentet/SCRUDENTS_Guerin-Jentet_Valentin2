package com.tumme.ii3510_courseapp_2526

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val imageButtonCompose: ImageButton = findViewById(R.id.imageButtonJPC)
        imageButtonCompose.setOnClickListener {
            Log.d(localClassName, "Click on imageButtonJPC")
            val intentLJPC = Intent(this, LearnJetPackCompose::class.java)
            startActivity(intentLJPC)
        }

        val buttonTaskList: Button = findViewById(R.id.buttonTaskList)
        buttonTaskList.setOnClickListener {
            Log.d(localClassName, "Click on buttonTaskList")
            val intentTaskList = Intent(this, TaskList::class.java)
            startActivity(intentTaskList)
        }

        val buttonChangeLanguage: Button = findViewById(R.id.buttonChangeLanguage)
        buttonChangeLanguage.setOnClickListener {
            showLanguageSelectionDialog()
        }
    }

    private fun showLanguageSelectionDialog() {
        val languageOptions = listOf(
            LanguageOption("en", getString(R.string.language_english)),
            LanguageOption("fr", getString(R.string.language_french))
        )

        AlertDialog.Builder(this)
            .setTitle(R.string.dialog_choose_language)
            .setItems(languageOptions.map { it.displayName }.toTypedArray()) { _, which ->
                val selectedTag = languageOptions[which].tag
                setLocale(selectedTag)
            }
            .show()
    }

    private fun setLocale(languageTag: String) {
        val localeList = LocaleListCompat.forLanguageTags(languageTag)
        AppCompatDelegate.setApplicationLocales(localeList)
    }

    private data class LanguageOption(val tag: String, val displayName: String)
}