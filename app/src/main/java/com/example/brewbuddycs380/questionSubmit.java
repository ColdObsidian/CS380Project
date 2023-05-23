package com.example.brewbuddycs380;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import java.util.*;


public class questionSubmit extends Fragment {
    private SharedViewModel viewModel;
    private static EnumSet<Properties> preferences = EnumSet.noneOf(Properties.class);

    public static EnumSet<Properties> getPreferences(){
        return preferences;
    }

    public questionSubmit() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
    }

    // Gets all the input for the toggle buttons in every fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question_submit, container, false);
        Button submitBtn = (Button) view.findViewById(R.id.submit);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // On click, checks every toggle
                Boolean icedState = viewModel.getIcedToggle().getValue();
                if (icedState != null && icedState) {
                    preferences.add(Properties.ICED);
                }

                Boolean hotState = viewModel.getHotToggle().getValue();
                if (hotState != null && hotState) {
                    preferences.add(Properties.HOT);
                }

                Boolean lightRoastState = viewModel.getLightRoastToggle().getValue();
                if (lightRoastState != null && lightRoastState) {
                    preferences.add(Properties.WEAK);
                }

                Boolean darkRoastState = viewModel.getDarkRoastToggle().getValue();
                if (darkRoastState != null && darkRoastState) {
                    preferences.add(Properties.STRONG);
                }

                Boolean sweetState = viewModel.getSweetToggle().getValue();
                if (sweetState != null && sweetState) {
                    preferences.add(Properties.SWEET);
                }

                Boolean mildSweetState = viewModel.getMildSweetToggle().getValue();
                if (mildSweetState != null && mildSweetState) {
                }

                Boolean darkSweetState = viewModel.getDarkSweetToggle().getValue();
                if (darkSweetState != null && darkSweetState) {
                    preferences.add(Properties.BITTER);
                }

                Boolean creamState = viewModel.getCreamToggle().getValue();
                if (creamState != null && creamState) {
                    preferences.add(Properties.CREAMY);
                }

                Boolean blackState = viewModel.getBlackToggle().getValue();
                if (blackState != null && blackState) {
                    preferences.add(Properties.BLACK);
                }

                Boolean decafState = viewModel.getDecafToggle().getValue();
                if (decafState != null && decafState) {
                    preferences.add(Properties.DECAF);
                }

                Boolean foamState = viewModel.getFoamToggle().getValue();
                if (foamState != null && foamState) {
                    preferences.add(Properties.FOAM);
                }

                Boolean vanillaState = viewModel.getVanillaToggle().getValue();
                if (vanillaState != null && vanillaState) {
                    preferences.add(Properties.FLAVOR_VANILLA);
                }

                Boolean chocolateState = viewModel.getChocolateToggle().getValue();
                if (chocolateState != null && chocolateState) {
                    preferences.add(Properties.FLAVOR_CHOCOLATE);
                }

                Boolean caramelState = viewModel.getCaramelToggle().getValue();
                if (caramelState != null && caramelState) {
                    preferences.add(Properties.FLAVOR_CARAMEL);
                }

                Boolean fruityState = viewModel.getFruityToggle().getValue();
                if (fruityState != null && fruityState) {
                    preferences.add(Properties.FLAVOR_FRUIT);
                }
                // moves to a new activity
                startActivity(new Intent(getActivity(), RecommendationScreen.class));
            }
        });
        return view;
    }
}