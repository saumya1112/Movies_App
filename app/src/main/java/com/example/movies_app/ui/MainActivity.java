package com.example.movies_app.ui;
import android.os.Bundle;
        import android.view.MenuItem;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.databinding.DataBindingUtil;
        import androidx.fragment.app.Fragment;
        import androidx.fragment.app.FragmentManager;
import com.example.movies_app.R;
import com.example.movies_app.databinding.ActivityMainBinding;
import com.example.movies_app.ui.movies.MovieFragment;
import com.example.movies_app.ui.settings.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import timber.log.Timber;


public class MainActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragmentManager;
    private Fragment fragment = null;
    public static final String FRAG_TAG_POPULAR = "frag-popular";
    public static final String FRAG_TAG_TOP_RATED = "frag-top-rated";
    public static final String FRAG_TAG_SETTINGS = "frag-settings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Timber.plant(new Timber.DebugTree());
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.bottomNavigation.setOnNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();
        loadPopularMoviesFragment();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_popular:
                loadPopularMoviesFragment();
                return true;
            case R.id.action_top_rated:
                loadTopRatedMoviesFragment();
                return true;
            case R.id.action_settings:
                loadSettingsFragment();
                return true;
        }
        return false;
    }

    private void loadPopularMoviesFragment() {
        fragment = fragmentManager.findFragmentByTag(FRAG_TAG_POPULAR);
        if (fragment != null) {
            // If fragment already exists, show the Fragment
            fragmentManager.beginTransaction().show(fragment).commit();
        } else {
            // If it doesn't exists, create and add the fragment
            fragmentManager.beginTransaction().add(R.id.fragment_container, new MovieFragment(), FRAG_TAG_POPULAR).commit();
        }

        // Hide rest of the fragments if they exist
        hideFragments(FRAG_TAG_TOP_RATED, FRAG_TAG_SETTINGS);
    }

    private void loadTopRatedMoviesFragment() {
        fragment = fragmentManager.findFragmentByTag(FRAG_TAG_TOP_RATED);
        if (fragment != null) {
            // If fragment already exists, show the Fragment
            fragmentManager.beginTransaction().show(fragment).commit();
        } else {
            // If it doesn't exists, create and add the fragment
            fragmentManager.beginTransaction().add(R.id.fragment_container, new MovieFragment(), FRAG_TAG_TOP_RATED).commit();
        }

        // Hide rest of the fragments if they exist
        hideFragments(FRAG_TAG_POPULAR, FRAG_TAG_SETTINGS);
    }

    private void loadSettingsFragment() {
        fragment = fragmentManager.findFragmentByTag(FRAG_TAG_SETTINGS);
        if (fragment != null) {
            // If fragment already exists, show the Fragment
            fragmentManager.beginTransaction().show(fragment).commit();
        } else {
            // If it doesn't exists, create and add the fragment
            fragmentManager.beginTransaction().add(R.id.fragment_container, new SettingsFragment(), FRAG_TAG_SETTINGS).commit();
        }

        // Hide rest of the fragments if they exist
        hideFragments(FRAG_TAG_POPULAR, FRAG_TAG_TOP_RATED);
    }

    private void hideFragments(String... tags) {
        for (String tag : tags) {
            fragment = fragmentManager.findFragmentByTag(tag);
            if (fragment != null) {
                fragmentManager.beginTransaction().hide(fragment).commit();
            }
        }
    }
}