package com.example.brewbuddycs380;

import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

// java code for activity_question.xml
public class QuestionActivity extends AppCompatActivity {

    //viewpager2 is used to allow swiping between fragments
    ViewPager2 viewPager2;
    // the tabs on the top bar of the activity
    TabLayout tabLayout;
    // the titles of the tabs across the top
    ArrayList<String> titles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // changes the status bar color so it looks prettier
        if (Build.VERSION.SDK_INT >= 21)
        {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.statusbar_question));
        }
        setContentView(R.layout.activity_question);

        // initialize the two objects
        viewPager2 = findViewById(R.id.viewPager2);
        tabLayout = findViewById(R.id.tabLayout);

        // find getStarted button from activity
        Button getStartedBtn = findViewById(R.id.getStarted);


        // on click it starts the viewpage2, and hides the button so
        // the user can swipe between buttons
        getStartedBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            titles = new ArrayList<String>();
            titles.add("Q1");
            titles.add("Q2");
            titles.add("Q3");
            titles.add("Q4");
            titles.add("Q5");
            setViewPagerAdapter();
                new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(titles.get(position));
                    }
                }).attach();
            getStartedBtn.setVisibility(View.GONE);
            findViewById(R.id.introText).setVisibility(View.GONE);
        }
    });
        // used for incrementing the progress bar.
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                // Update the progress bar based on the selected fragment
                ProgressBar progressBar = findViewById(R.id.progressBar);
                // Set the progress to fill proportional to the fragment
                // (question 3 is 60% full etc.)
                progressBar.setProgress((position+1)*20);
            }
        });
    }
    public void setViewPagerAdapter() {
        ViewPager2Adapter viewPager2Adapter = new ViewPager2Adapter(this);
        //creates an ArrayList of Fragments
        ArrayList<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new question1());
        fragmentList.add(new question2());
        fragmentList.add(new question3());
        fragmentList.add(new question4());
        fragmentList.add(new question5());
        //sets the data for the adapter
        viewPager2Adapter.setData(fragmentList);
        viewPager2.setAdapter(viewPager2Adapter);
    }
}