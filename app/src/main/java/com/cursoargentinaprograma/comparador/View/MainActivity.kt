package com.cursoargentinaprograma.comparador.View

import android.content.Context
import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.curso.android.app.practica.counter.view.MainViewModel
import com.cursoargentinaprograma.comparador.R
import com.cursoargentinaprograma.comparador.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val view = binding.root
        val startColor = ContextCompat.getColor(this, R.color.gradient_start_color)
        val endColor = ContextCompat.getColor(this, R.color.gradient_end_color)
        setGradientBackground(view, startColor, endColor)

        binding.actionInfo.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setTitle(getString(R.string.about_title))
            builder.setItems(
                arrayOf(
                    getString(R.string.app_name),
                    getString(R.string.app_version),
                    getString(R.string.app_context),
                    getString(R.string.app_author)
                )
            ) { _, _ ->
            }
            builder.setNegativeButton(getString(R.string.dialog_cancel), null)
            builder.setPositiveButton(getString(R.string.app_author_visit_website)) { dialogInterface, i ->
                val webIntent = Intent(Intent.ACTION_VIEW)
                webIntent.data = Uri.parse(getString(R.string.app_author_website))
                startActivity(webIntent)
            }

            val dialog: AlertDialog = builder.create()
            dialog.show()
            Toast.makeText(this, getString(R.string.about_opening), Toast.LENGTH_SHORT).show()
        }

        mainViewModel.comparador.observe(this) { comparador ->
            if (validFields()) {
                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder.setTitle(getString(R.string.dialog_title))
                builder.setMessage("${comparador.textFirst} ${comparador.textSecond}")
                builder.setNegativeButton(getString(R.string.dialog_cancel)) { dialogInterface, i ->
                    Toast.makeText(
                        this,
                        getString(R.string.operation_cancel),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                builder.setPositiveButton(getString(R.string.dialog_ok)) { dialogInterface, i ->
                    Toast.makeText(
                        this,
                        getString(R.string.operation_compare),
                        Toast.LENGTH_SHORT
                    ).show()

                    val textViewCompare = binding.tvCompareResult
                    if (comparador.textFirst == comparador.textSecond) {
                        textViewCompare.text = getString(R.string.match_result)
                        binding.tieFirstText.setTextColor(ContextCompat.getColor(this, R.color.green))
                        binding.tieSecondText.setTextColor(ContextCompat.getColor(this, R.color.green))
                        textViewCompare.setTextColor(
                            ContextCompat.getColor(
                                this,
                                R.color.green
                            )
                        )
                    } else {
                        textViewCompare.text = getString(R.string.no_match_result)
                        binding.tieFirstText.setTextColor(ContextCompat.getColor(this, R.color.red))
                        binding.tieSecondText.setTextColor(ContextCompat.getColor(this, R.color.red))
                        textViewCompare.setTextColor(ContextCompat.getColor(this, R.color.red))
                    }
                }
                val dialog: AlertDialog = builder.create()
                dialog.show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.actionCompare -> {
                hideKeyboard()
                if (validFields()) {
                    val isFirstLetterChecked = binding.checkboxLetter.isChecked
                    val textFirst = binding.tieFirstText.text.toString().run {
                        if (isFirstLetterChecked) toLowerCase().trim() else trim()
                    }
                    val textSecond = binding.tieSecondText.text.toString().run {
                        if (isFirstLetterChecked) toLowerCase().trim() else trim()
                    }
                    mainViewModel.compareTexts(textFirst, textSecond)
                }
            }
            R.id.actionClean -> {
                with(binding) {
                    hideKeyboard()
                    tieFirstText.text?.clear()
                    tilFirstText.error = null
                    tilFirstText.clearFocus()
                    tieFirstText.setTextColor(ContextCompat.getColor(this@MainActivity, R.color.blue))
                    tieSecondText.text?.clear()
                    tilSecondText.error = null
                    tilSecondText.clearFocus()
                    tieSecondText.setTextColor(ContextCompat.getColor(this@MainActivity, R.color.blue))
                    tvCompareResult.text = getString(R.string.waiting_result)
                    checkboxLetter.isChecked = false
                    tvCompareResult.setTextColor(ContextCompat.getColor(this@MainActivity, android.R.color.black))

                }
                Toast.makeText(this, getString(R.string.clear_form), Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun validFields(): Boolean {
        var isValid = true

        if (binding.tieFirstText.text.isNullOrEmpty()) {
            binding.tilFirstText.run {
                error = getString(R.string.helper_text)
                //requestFocus()
            }
            isValid = false
        } else {
            binding.tilFirstText.error = null
        }
        if (binding.tieSecondText.text.isNullOrEmpty()) {
            binding.tilSecondText.run {
                error = getString(R.string.helper_text)
                //requestFocus()
            }
            isValid = false
        } else {
            binding.tilSecondText.error = null
        }

        return isValid
    }

    private fun hideKeyboard() {
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val currentFocusedView = currentFocus
        if (currentFocusedView != null) {
            inputMethodManager.hideSoftInputFromWindow(
                currentFocusedView.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }
    fun setGradientBackground(view: View, startColor: Int, endColor: Int) {
        val gradientDrawable = GradientDrawable(
            GradientDrawable.Orientation.TOP_BOTTOM,
            intArrayOf(startColor, endColor)
        )

        view.background = gradientDrawable
    }
}

