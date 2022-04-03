package pojo

import android.media.Rating
import com.google.gson.annotations.SerializedName

data class CoinInfo(
//@expose в обучении
    @SerializedName("Name") var name: String? = null
)