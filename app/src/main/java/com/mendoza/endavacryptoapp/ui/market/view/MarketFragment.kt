package com.mendoza.endavacryptoapp.ui.market.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.mendoza.endavacryptoapp.R
import com.mendoza.endavacryptoapp.databinding.FragmentMarketBinding
import com.mendoza.endavacryptoapp.ui.market.usecase.CryptoCurrencyModel
import com.mendoza.endavacryptoapp.ui.market.view.cell.CryptosListAdapter
import com.mendoza.endavacryptoapp.ui.market.viewModel.MarketViewModel
import com.mendoza.endavacryptoapp.ui.profile.usecase.GithubProfileModel
import com.mendoza.endavacryptoapp.utils.StringUtils
import org.koin.androidx.viewmodel.ext.android.viewModel

class MarketFragment : Fragment() {

    private var _binding: FragmentMarketBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val marketViewModel:MarketViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMarketBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()

        observeViewModel()

        marketViewModel.fetchLatestCryptos(getSelectedCurrency())
        marketViewModel.getProfileData("lcmendozaf")
    }

    private fun observeViewModel() {
        marketViewModel.loadingView.observe(viewLifecycleOwner, this::onLoadingChanged)
        marketViewModel.errorView.observe(viewLifecycleOwner, this::showErrorScreen)
        marketViewModel.cryptoCurrenciesList.observe(viewLifecycleOwner, this::onCryptoListChanged)
        marketViewModel.profile.observe(viewLifecycleOwner, this::onProfileChanged)
    }

    private fun setupViews() {
        binding.swipeRefresh.setOnRefreshListener {
            marketViewModel.fetchLatestCryptos(getSelectedCurrency())
        }

        binding.chipGroup.setOnCheckedStateChangeListener { _, _ ->
            marketViewModel.fetchLatestCryptos(getSelectedCurrency())
        }
        binding.cryptosRecyclerview.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.cryptosRecyclerview.adapter = CryptosListAdapter(this::onCryptoClicked)
    }

    private fun getSelectedCurrency(): String = if(binding.chipArs.isChecked) "ARS" else "USD"

    private fun onLoadingChanged(isLoading:Boolean) {
        binding.swipeRefresh.isRefreshing = isLoading
    }

    private fun showErrorScreen(showError:Boolean) {
        if(showError) {
            binding.cryptosRecyclerview.visibility = View.GONE
            binding.errorLayout.root.visibility = View.VISIBLE
        } else {
            binding.errorLayout.root.visibility = View.GONE
        }
    }

    private fun onCryptoListChanged(list:List<CryptoCurrencyModel>) {
        binding.cryptosRecyclerview.visibility = View.VISIBLE
        (binding.cryptosRecyclerview.adapter as CryptosListAdapter).setCurrencies(list, getSelectedCurrency())
    }

    private fun onProfileChanged(profile: GithubProfileModel) {
        Glide.with(requireContext()).load(profile.avatarUrl).error(R.drawable.ic_error).into(binding.ivProfile)

        binding.tvGreetings.text = getString(R.string.greetings_user, profile.name)
    }

    private fun onCryptoClicked(crypto: CryptoCurrencyModel) {
        Toast.makeText(context, "Market Capital ${getSelectedCurrency()} ${StringUtils.formatCurrency(crypto.marketCapital)}", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}