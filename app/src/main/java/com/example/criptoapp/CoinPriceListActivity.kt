package com.example.criptoapp

import adapters.CoinInfoAdapter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_coin_price_list.*
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_price_list)
        val adapter = CoinInfoAdapter(this)
        rvCoinPriceList.adapter = adapter
        viewModel = ViewModelProviders.of(this)[CoinViewModel::class.java]

//        viewModel = ViewModelProvider(
//            this,
//            ViewModelProvider.AndroidViewModelFactory(application)
//        )[CoinViewModel::class.java]
        viewModel.priceList.observe(this, Observer { adapter.coinInfoList = it })
//        viewModel.getDetailInfo("BTC").observe(this, Observer {
//
//        })

    }


}