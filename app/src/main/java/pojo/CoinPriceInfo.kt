package pojo

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import api.ApiFactory.BASE_IMAGE_URL
import com.google.gson.annotations.SerializedName
import utils.convertTimestempToTime

@Entity(tableName = "full_price_list")
data class CoinPriceInfo (

    @PrimaryKey
    @SerializedName("FROMSYMBOL"              ) var FromSymbol              : String,         //ВЫЛЕЗАЛА ОШИБКА, УБРАЛ NULL из за PrimaryKey
    @SerializedName("TOSYMBOL"                ) var ToSymbol                : String? = null,
    @SerializedName("MARKET"                  ) var Market                  : String? = null,
    @SerializedName("PRICE"                   ) var Price                   : String? = null,
    @SerializedName("LASTUPDATE"              ) var LastUpdate              : Long? = null,
    @SerializedName("LASTVOLUME"              ) var LastVolume              : String? = null,
    @SerializedName("LASTVOLUMETO"            ) var LastVolumeTo            : String? = null,
    @SerializedName("LASTTRADEID"             ) var LastTradeId             : String? = null,
    @SerializedName("VOLUMEDAY"               ) var VolumeDay               : String? = null,
    @SerializedName("VOLUMEDAYTO"             ) var VolumeDayTo             : String? = null,
    @SerializedName("VOLUME24HOUR"            ) var Volume24Hour            : String? = null,
    @SerializedName("VOLUME24HOURTO"          ) var Volume24HourTo          : String? = null,
    @SerializedName("OPENDAY"                 ) var OpenDay                 : String? = null,
    @SerializedName("HIGHDAY"                 ) var HighDay                 : String? = null,
    @SerializedName("LOWDAY"                  ) var LowDay                  : String? = null,
    @SerializedName("OPEN24HOUR"              ) var Open24Hour              : String? = null,
    @SerializedName("HIGH24HOUR"              ) var High24Hour              : String? = null,
    @SerializedName("LOW24HOUR"               ) var Low24Hour               : String? = null,
    @SerializedName("LASTMARKET"              ) var LastMarket              : String? = null,
    @SerializedName("VOLUMEHOUR"              ) var VolumeHour              : String? = null,
    @SerializedName("VOLUMEHOURTO"            ) var VolumeHourTo            : String? = null,
    @SerializedName("OPENHOUR"                ) var OpenHour                : String? = null,
    @SerializedName("HIGHHOUR"                ) var HighHour                : String? = null,
    @SerializedName("LOWHOUR"                 ) var LowHour                 : String? = null,
    @SerializedName("TOPTIERVOLUME24HOUR"     ) var TopTierVolume24Hour     : String? = null,
    @SerializedName("TOPTIERVOLUME24HOURTO"   ) var TopTierVolume24HourTo   : String? = null,
    @SerializedName("CHANGE24HOUR"            ) var Change24Hour            : String? = null,
    @SerializedName("CHANGEPCT24HOUR"         ) var ChangePCT24Hour         : String? = null,
    @SerializedName("CHANGEDAY"               ) var ChangeDay               : String? = null,
    @SerializedName("CHANGEPCTDAY"            ) var ChangePCTDay            : String? = null,
    @SerializedName("CHANGEHOUR"              ) var ChangeHour              : String? = null,
    @SerializedName("CHANGEPCTHOUR"           ) var ChangePCTHour           : String? = null,
    @SerializedName("CONVERSIONTYPE"          ) var ConversionType          : String? = null,
    @SerializedName("CONVERSIONSYMBOL"        ) var ConversionSymbol        : String? = null,
    @SerializedName("SUPPLY"                  ) var Supply                  : String? = null,
    @SerializedName("MKTCAP"                  ) var MKTCap                  : String? = null,
    @SerializedName("MKTCAPPENALTY"           ) var MKTCapPenalty           : String? = null,
    @SerializedName("CIRCULATINGSUPPLY"       ) var CirculatingSupply       : String? = null,
    @SerializedName("CIRCULATINGSUPPLYMKTCAP" ) var CirculatingSupplyMKTCap : String? = null,
    @SerializedName("TOTALVOLUME24H"          ) var TotalVolume24H          : String? = null,
    @SerializedName("TOTALVOLUME24HTO"        ) var TotalVolume24HTo        : String? = null,
    @SerializedName("TOTALTOPTIERVOLUME24H"   ) var TotalTopTierVolume24H   : String? = null,
    @SerializedName("TOTALTOPTIERVOLUME24HTO" ) var TotalTopTierVolume24HTo : String? = null,
    @SerializedName("IMAGEURL"                ) var ImageURL                : String? = null
){
    fun getFormattedTime(): String{
        return convertTimestempToTime(LastUpdate)
    }
    fun getFullImageUrl(): String {
        return BASE_IMAGE_URL + ImageURL
    }

}