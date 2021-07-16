package jesus.rosas.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jesus.rosas.newsapp.ui.ViewPagerAdapter
import jesus.rosas.newsapp.databinding.ActivityMainBinding
import jesus.rosas.newsapp.ui.LocalFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpTabs()
    }

    private fun setUpTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager).apply {
            addFragment(LocalFragment.newInstance(), "Country")
            addFragment(LocalFragment.newInstance(), "Local")
            addFragment(LocalFragment.newInstance(), "International")
        }


        binding.viewPager.adapter = adapter
        binding.viewPager.offscreenPageLimit = 3
        binding.tabs.setupWithViewPager(binding.viewPager)
    }

}