package com.mendoza.endavacryptoapp.ui.profile.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.mendoza.endavacryptoapp.R
import com.mendoza.endavacryptoapp.databinding.FragmentProfileBinding
import com.mendoza.endavacryptoapp.ui.market.view.cell.CryptosListAdapter
import com.mendoza.endavacryptoapp.ui.profile.usecase.GithubProfileModel
import com.mendoza.endavacryptoapp.ui.profile.viewmodel.ProfileViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val profileViewModel: ProfileViewModel by viewModel()

    private lateinit var profile: GithubProfileModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()

        observeViewModel()

        profileViewModel.getProfileData("lcmendozaf")
    }

    private fun observeViewModel() {
        profileViewModel.loadingView.observe(viewLifecycleOwner, this::onLoadingChanged)
        profileViewModel.errorView.observe(viewLifecycleOwner, this::showErrorScreen)
        profileViewModel.profile.observe(viewLifecycleOwner, this::onProfileChanged)
    }

    private fun setupViews() {
        binding.swipeRefresh.setOnRefreshListener {
            profileViewModel.getProfileData("lcmendozaf")
        }
        binding.btnGithub.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(profile.profileUrl)
                startActivity(intent)
            } catch (_:Exception) {

            }
        }

        profileViewModel.getProfileData("lcmendozaf")
    }

    private fun onLoadingChanged(isLoading:Boolean) {
        binding.swipeRefresh.isRefreshing = isLoading
    }

    private fun showErrorScreen(showError:Boolean) {
        if(showError) {
            binding.profileGroup.visibility = View.GONE
            binding.errorLayout.root.visibility = View.VISIBLE
        } else {
            binding.errorLayout.root.visibility = View.GONE
        }
    }

    private fun onProfileChanged(profile: GithubProfileModel) {
        this.profile = profile
        binding.profileGroup.visibility = View.VISIBLE
        loadProfileImage(profile.avatarUrl)
        binding.tvName.text = profile.name
        binding.tvAge.text = profile.bio
        binding.tvLocation.text = profile.location
    }

    private fun loadProfileImage(avatarUrl: String) {
        Glide.with(requireContext()).load(avatarUrl).error(R.drawable.ic_error).into(binding.ivProfile)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}