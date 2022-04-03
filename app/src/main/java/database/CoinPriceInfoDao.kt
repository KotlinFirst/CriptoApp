package database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pojo.CoinPriceInfo

@Dao
interface CoinPriceInfoDao {
    //выдаст всю инф о всех сохран. валютах
    @Query("SELECT * FROM full_price_list ORDER BY LastUpdate")
    fun getPriceList(): LiveData<List<CoinPriceInfo>>

    //выдаст инф об одной валюте
    @Query("SELECT * FROM full_price_list WHERE FromSymbol ==:fSum LIMIT 1")
    fun getPriceInfoAboutCoin(fSum:String):LiveData<CoinPriceInfo>

    //сохраняет полученые из интернета данные в базу
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPriceList(priceList:List<CoinPriceInfo>)
}