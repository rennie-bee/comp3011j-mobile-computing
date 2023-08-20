package com.example.eldercare.ui.product;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.eldercare.MapActivity;
import com.example.eldercare.ProductActivity;
import com.example.eldercare.R;
import com.example.eldercare.databinding.FragmentProductBinding;

// This class manages the logics at product fragment
public class ProductFragment extends Fragment {
    private Button bt_to_product;
    private FragmentProductBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // put contents on fragment_product layout
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        // a button, when it gets clicked, sending user to the Baidu Map Page
        bt_to_product = view.findViewById(R.id.bt_to_product);
        bt_to_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_to_product = new Intent(getActivity(), ProductActivity.class);
                startActivity(intent_to_product);
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