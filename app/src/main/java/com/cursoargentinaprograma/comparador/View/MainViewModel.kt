package com.curso.android.app.practica.counter.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cursoargentinaprograma.comparador.Model.Comparador

class MainViewModel : ViewModel() {

    private val _comparador = MutableLiveData<Comparador>()
    val comparador: LiveData<Comparador> get() = _comparador

    fun compareTexts(textFirst: String, textSecond: String) {
        _comparador.value = Comparador(textFirst, textSecond)
    }
}