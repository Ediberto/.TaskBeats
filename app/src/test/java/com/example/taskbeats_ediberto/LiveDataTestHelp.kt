package com.example.taskbeats_ediberto

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
//O QUE ESSE CÓDIGO FAZ?
//val latch = CountDownLatch(1), FAZ UMA CONTAGEM POR DOIS SEGUNDOS,
// VAI ENCONTRAR O VALOR,
// data = value, QUANDO O VALOR CHEGAR,
//val latch = CountDownLatch(1) //FNALIZA ESSA CONTAGEM E
//val observer = object : Observer<T> {  //REMOVE DO OBSERVER
//AÍ ELE ESPERA
//QDO ELE CAIR AQUI E ELE ESTIVER ESPERANDO DEPOIS DOS 2 SEGUNDOS
//this.observeForever(observer)
//    if(!latch.await(time, timeUnit)) {
//É JOGADO UMA EXCEPTION INFORMANDO QUE O LIVEDATA NUNCA FOI SETADO
//throw TimeoutException("LiveData value was never set.")
// //CONVERSAO DO TIPO PARA O TIPO T
//    @Suppress("UNCHEKED_CAST")
//    return data as T

//getOrAwaitValue, é uma função do KOTLIN EXTENDIDA À CLASSE LiveData
//ESSA CLASSE É DO PRÓPRIO ANDROID
//o "T" SGINIFICA TIPO
//POR EXEMPLO.
//SE FIZERMOS
//fun LiveData<String>.getOrAwaitValue(
//SO VAI FUNCIONAR PARA LIVEDATA QUE FOREM STRING
//NESTE CASO <T>, VAI FUNCIONAR PARA QQ TIPO DE DADOS NO LIVEDATE, T É GENÉRICO
//PODE SER A, OU B, ETC
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(value: T) {
            data = value
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    this.observeForever(observer)
    if(!latch.await(time, timeUnit)) {
        throw TimeoutException("LiveData value was never set.")
    }
    //CONVERSAO DO TIPO PARA O TIPO T
    @Suppress("UNCHEKED_CAST")
    return data as T
}