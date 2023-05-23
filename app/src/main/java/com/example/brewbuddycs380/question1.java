package com.example.brewbuddycs380;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ToggleButton;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;

public class question1 extends Fragment {
    private SharedViewModel viewModel;

    public question1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question1, container, false);

        // set a listener on the toggle button
        ToggleButton icedToggle = view.findViewById(R.id.icedButton);
        // checks if the toggle is on or off. If on, sets it to on in the SharedViewModel class
        icedToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                viewModel.setIcedToggle(isChecked);
            }
        });
        // set a listener on the toggle button
        ToggleButton hotToggle = view.findViewById(R.id.hotButton);
        // checks if the toggle is on or off. If on, sets it to on in the SharedViewModel class
        hotToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                viewModel.setHotToggle(isChecked);
            }
        });

        return view;
    }
}