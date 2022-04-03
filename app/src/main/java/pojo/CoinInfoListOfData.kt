package pojo

import com.google.gson.annotations.SerializedName

data class CoinInfoListOfData (
    @SerializedName("Data") var data: List<Datum>? = null
)