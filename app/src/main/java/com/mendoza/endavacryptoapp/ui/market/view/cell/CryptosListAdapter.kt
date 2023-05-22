package com.mendoza.endavacryptoapp.ui.market.view.cell

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mendoza.endavacryptoapp.BuildConfig
import com.mendoza.endavacryptoapp.R
import com.mendoza.endavacryptoapp.databinding.CryptoCurrencyAdapterLayoutBinding
import com.mendoza.endavacryptoapp.ui.market.usecase.CryptoCurrencyModel
import com.mendoza.endavacryptoapp.utils.StringUtils
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.abs

class CryptosListAdapter(private val onClickListener: (CryptoCurrencyModel)->Unit):RecyclerView.Adapter<CryptosListViewHolder>() {
    private lateinit var items:ArrayList<CryptoCurrencyModel>

    private lateinit var currentCurrency: String

    @SuppressLint("NotifyDataSetChanged")
    fun setCurrencies(list: List<CryptoCurrencyModel>, currentCurrency:String) {
        this.currentCurrency = currentCurrency
        if(::items.isInitialized) {
            items.clear()
            items.addAll(list)
        } else
            items = ArrayList(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptosListViewHolder {
        return CryptosListViewHolder(
            CryptoCurrencyAdapterLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false),
            onClickListener
        )
    }

    override fun getItemCount(): Int = if(::items.isInitialized) items.size else 0

    override fun onBindViewHolder(holder: CryptosListViewHolder, position: Int) {
        holder.bind(items[position], currentCurrency)
    }
}

class CryptosListViewHolder(
    private val bindingView: CryptoCurrencyAdapterLayoutBinding,
    private val onClickListener: (CryptoCurrencyModel)->Unit
): RecyclerView.ViewHolder(bindingView.root) {
    fun bind(currencyModel: CryptoCurrencyModel, currentCurrency:String) {
        with(currencyModel) {
            bindingView.tvSymbol.text = symbol
            bindingView.tvName.text = name

            bindingView.tvQuote.text = String.format("%s %s",currentCurrency, StringUtils.formatCurrency(quote))
            if(volumeChange24H > 0) {
                bindingView.volumeLayout.setBackgroundResource(R.drawable.green_rounded_rectangle)
                bindingView.ivVolumeChange.setImageResource(R.drawable.ic_arrow_up_right)
            } else {
                bindingView.volumeLayout.setBackgroundResource(R.drawable.red_rounded_rectangle)
                bindingView.ivVolumeChange.setImageResource(R.drawable.ic_arrow_down_left)
            }
            bindingView.tvVolumeChange.text = String.format("%.1f%%", abs(volumeChange24H))
            loadImage(imageView = bindingView.icon, symbol.lowercase())

            bindingView.root.setOnClickListener {
                onClickListener.invoke(currencyModel)
            }
        }
    }

    private fun loadImage(imageView:ImageView, cryptoName:String) {
        Glide.with(imageView.context).load("${BuildConfig.CRYPTO_IMAGES_URL}$cryptoName")
            .error(R.drawable.ic_error)
            .into(imageView)
    }
}