package com.example.brewbuddycs380;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ToggleButton;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;

public class question3 extends Fragment {
    private SharedViewModel viewModel;

    public question3() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question3, container, false);

        // Set a listener on the darkSweetToggle button
        ToggleButton darkSweetToggle = view.findViewById(R.id.darkSweetButton);
        darkSweetToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                viewModel.setDarkSweetToggle(isChecked);
            }
        });

        // Set a listener on the mildSweetToggle button
        ToggleButton mildSweetToggle = view.findViewById(R.id.mildSweetButton);
        mildSweetToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                viewModel.setMildSweetToggle(isChecked);
            }
        });

        // Set a listener on the sweetToggle button
        ToggleButton sweetToggle = view.findViewById(R.id.sweetButton);
        sweetToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                viewModel.setSweetToggle(isChecked);
            }
        });

        return view;
    }

}