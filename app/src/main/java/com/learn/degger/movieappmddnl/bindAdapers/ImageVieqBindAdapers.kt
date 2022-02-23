package com.learn.degger.movieappmddnl.bindAdapers

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.learn.degger.movieappmddnl.R
import com.learn.degger.movieappmddnl.utilis.Constants
import java.io.IOException
import java.text.NumberFormat
import java.util.*


@BindingAdapter("imageName")
fun setImageFromAssets(view: ImageView, fileName: String) {

    try {
        val bitmap = BitmapFactory.decodeStream(view.context?.assets?.open(fileName))
        view.setImageBitmap(bitmap)
    } catch (e: IOException) {
        e.printStackTrace()
    }

}

@SuppressLint("CheckResult")
@BindingAdapter(value = ["imageUrl", "placeholderImage", "errorImage"], requireAll = false)
fun     loadImageFromInternet(view: ImageView, imageUrl: String, placeholderImage: Boolean, errorImage: Boolean) {

    val requestOptions = RequestOptions()

    if (placeholderImage)
        //shoing image place holder abhi k leya wasa he rakhi ha image
        requestOptions.placeholder(R.drawable.bloodshot)

    if (errorImage)
        //shoing error image abhi k leya wwasa he koe image rakhi ha
        requestOptions.placeholder(R.drawable.bloodshot)
if (imageUrl.isEmpty()){
    requestOptions.placeholder(R.drawable.bloodshot)
}else{

    Glide.with(view.context)
        .setDefaultRequestOptions(requestOptions)
        .load(Constants.ImageBaseURLw500+imageUrl)
        .into(view)
}

}

@BindingAdapter(value = ["image"], requireAll = false)
fun     loadImageFromInternetDetail(view: ImageView, imageUrl: String) {
    Glide.with(view.context)
            .load(Constants.ImageBaseURL+imageUrl)
            .into(view)
    }



@BindingAdapter("priceText")
fun setPriceText(view: TextView, price: Double) {
    val formatter = NumberFormat.getCurrencyInstance(Locale.US)

    view.text = "Rs. $price / each"
}

@BindingAdapter("ratingText")
fun setTotalRatingText(view: TextView, rating: Int) {
    view.text = "($rating)"
}

@BindingAdapter("android:text")
fun setTextViewText(view: TextView, text: Double) {
    view.text = "$text"
}