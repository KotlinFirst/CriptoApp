package com.example.criptoapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import api.ApiFactory
import com.google.gson.Gson
import database.AppDatabase
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.InternalCoroutinesApi
import pojo.CoinPriceInfo
import pojo.CoinPriceInfoRawData
import java.util.concurrent.TimeUnit

@InternalCoroutinesApi
class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()

    val priceList = db.coinPriceInfoDao().getPriceList()

    init {
        loadData()
    }

    fun getDetailInfo(fSym:String): LiveData<CoinPriceInfo> {
        return db.coinPriceInfoDao().getPriceInfoAboutCoin(fSym)
    }

    //загружает данные из сети
    private fun loadData() {
        val disposable = ApiFactory.apiService.getTopCoinsInfo(limit = 50) //получаем самые популярные валюты
            .map { it.data?.map { it.CoinInfo?.name }?.joinToString(",") }  //превращаем в 1 строку списка имён через запятую
            .flatMap { ApiFactory.apiService.getFullPriceList(fSyms = it.toString()) } // загружаем ВСЮ информацию о валютах, передавая параметры полученые выше как it
            .map { getPriceListFromRawData(it) } //преобразовываем в лист CoinPriceInfo
            .delaySubscription(10,TimeUnit.SECONDS)
            .repeat()
            .retry()
            .subscribeOn(Schedulers.io()) //делаем всё это в другом потоке
            .subscribe(
                {
                    db.coinPriceInfoDao().insertPriceList(it) //cохраняет полученые данные из интернета в базу через интерфейс CoinPriceInfoDao
                    Log.d("TEST_OF_LOADING_DATA", it.toString())
                }, {
                    Log.d("TEST_OF_LOADING_DATA", it.message.toString())
                }
            )
        compositeDisposable.add(disposable)
    }

    private fun getPriceListFromRawData(coinPriceInfoRawData: CoinPriceInfoRawData): List<CoinPriceInfo> {
        val result = ArrayList<CoinPriceInfo>()
        val jsonObjects =
            coinPriceInfoRawData.coinPriceInfoJsonObject //получаем json обьект - переменную coinPriceJsonObject:JsonObject в классе CoinPriceInfoRawData
        if (jsonObjects == null) return result
        val coinKeySet = jsonObjects.keySet() // получаем набор ключей без объектов BTC,ETH и т.д.
        for (coinKey in coinKeySet) {
            val currencyJson =
                jsonObjects.getAsJsonObject(coinKey) //получение дечернего объекта т.е. json от валюты и дальше без bitcoina
            val currencyKeySet =
                currencyJson.keySet() // получаем набор ключей без объектов USD,EUR и т.д.
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinPriceInfo::class.java
                )  // выше конвертировали из json в coinPriceInfo
                result.add(priceInfo)
            }
        }
        return result

    }

    //lifecycle.viewModel
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}