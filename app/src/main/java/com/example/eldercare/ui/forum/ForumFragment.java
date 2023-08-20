package com.example.eldercare.ui.forum;

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
import com.example.eldercare.NoteActivity;
import com.example.eldercare.R;
import com.example.eldercare.databinding.FragmentForumBinding;

// This class manages the logics at forum fragment
public class ForumFragment extends Fragment {

    private FragmentForumBinding binding;
    private Button bt_to_note;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // put contents on fragment_forum layout
        View view = inflater.inflate(R.layout.fragment_forum, container, false);

        bt_to_note = view.findViewById(R.id.bt_to_note);
        bt_to_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // when user clicks button to note, send user to the NoteActivity;
                Intent intent_to_note = new Intent(getActivity(), NoteActivity.class);
                startActivity(intent_to_note);
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