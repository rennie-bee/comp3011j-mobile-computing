package com.example.eldercare.ui.map;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.eldercare.MainActivity;
import com.example.eldercare.MapActivity;
import com.example.eldercare.R;
import com.example.eldercare.WeatherActivity;
import com.example.eldercare.databinding.FragmentMapBinding;

// This class manages the logics at map fragment
public class MapFragment extends Fragment{

    private View view;
    private FragmentMapBinding binding;
    private Button bt_to_map, bt_to_weather;
    // Declare and initialize an owner activity in case of data transferred between the activity and
    // fragment.
    MainActivity ma = (MainActivity) getActivity();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // put contents on fragment_map layout
        view = inflater.inflate(R.layout.fragment_map, container, false);
        // a button, when it gets clicked, sending user to the Baidu Map Page
        bt_to_map = view.findViewById(R.id.bt_to_map);
        bt_to_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_to_map = new Intent(getActivity(), MapActivity.class);
                startActivity(intent_to_map);
            }
        });
        // a button, when it gets clicked, sending user to the Weather Page
        bt_to_weather = view.findViewById(R.id.bt_to_weather);
        bt_to_weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_to_weather = new Intent(getActivity(), WeatherActivity.class);
                startActivity(intent_to_weather);
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}