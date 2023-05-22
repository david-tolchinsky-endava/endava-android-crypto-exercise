package com.mendoza.endavacryptoapp.ui.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mendoza.endavacryptoapp.databinding.FragmentHomeBinding
import com.mendoza.endavacryptoapp.ui.home.viewmodel.HomeViewModel
import com.mendoza.endavacryptoapp.utils.ThemePreferences
import com.mendoza.endavacryptoapp.utils.Themes

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val homeViewModel : HomeViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var themePreferences: ThemePreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        themePreferences = ThemePreferences(requireActivity())
        if(themePreferences.readCurrentTheme() == Themes.DAY)
            binding.chipLight.isChecked = true
        else
            binding.chipDark.isChecked = true

        binding.chipGroup.setOnCheckedStateChangeListener { _, checkedIds ->
            if(checkedIds.contains(binding.chipLight.id)) {
                themePreferences.setTheme(Themes.DAY)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                themePreferences.setTheme(Themes.NIGHT)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}