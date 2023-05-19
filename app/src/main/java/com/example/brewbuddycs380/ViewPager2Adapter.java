package com.example.brewbuddycs380;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import java.util.ArrayList;

// for use with the QuestionActivity class
public class ViewPager2Adapter extends FragmentStateAdapter {

    // fragments that will be swiped between
    private ArrayList<Fragment> fragments;

    // constructor
    public ViewPager2Adapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);

    }

    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    public int getItemCount() {
        return fragments.size();
    }

    public void setData(ArrayList<Fragment> fragments) {
        this.fragments = fragments;
    }
}
