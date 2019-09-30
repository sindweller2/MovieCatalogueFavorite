package submission4.moviecataloguefavorite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class FavoriteActivity extends AppCompatActivity {

    private PageAdapter pageAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        viewPager = findViewById(R.id.viewPager2);
        tabLayout = findViewById(R.id.tabLayout2);
        pageAdapter = new PageAdapter(getSupportFragmentManager());
        pageAdapter.addFragment(new MovieFavoriteFragment(), getResources().getString(R.string.firsttab));
        pageAdapter.addFragment(new TVFavoriteFragment(), getResources().getString(R.string.secondtab));
        viewPager.setAdapter(pageAdapter);
        tabLayout.setupWithViewPager(viewPager);

        this.setTitle(getResources().getString(R.string.favorite));

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
