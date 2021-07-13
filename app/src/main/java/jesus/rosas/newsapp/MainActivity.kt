package jesus.rosas.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import jesus.rosas.newsapp.adapter.ViewPagerAdapter
import jesus.rosas.newsapp.fragment.LocalFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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