package jesus.rosas.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import jesus.rosas.newsapp.R
import jesus.rosas.newsapp.adapter.ViewPagerAdapter
import jesus.rosas.newsapp.fragment.LocalFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpTabs()
    }

    private fun setUpTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(LocalFragment(), "Country")
        adapter.addFragment(LocalFragment(), "Local")
        adapter.addFragment(LocalFragment(), "International")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }

}