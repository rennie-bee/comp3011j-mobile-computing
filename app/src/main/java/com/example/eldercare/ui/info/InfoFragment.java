package com.example.eldercare.ui.info;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.eldercare.MainActivity;
import com.example.eldercare.MapActivity;
import com.example.eldercare.NoteActivity;
import com.example.eldercare.NoteAddActivity;
import com.example.eldercare.R;
import com.example.eldercare.ResetPasswordActivity;
import com.example.eldercare.SigninActivity;
import com.example.eldercare.databinding.FragmentInfoBinding;


// This class manages the logics at Info fragment
public class InfoFragment extends Fragment{

    private FragmentInfoBinding binding;
    private Button bt_reset, bt_logout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // put contents on fragment_info layout
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        // a button, when it gets clicked, sending user to the password resetting page
        bt_reset = view.findViewById(R.id.bt_reset);
        bt_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_to_reset = new Intent(getActivity(), ResetPasswordActivity.class);
                startActivity(intent_to_reset);
            }
        });

        // a button, when it gets clicked, let user confirm whether to log out or not
        bt_logout = view.findViewById(R.id.bt_logout);
        bt_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showConfirmDialog();
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    // In case that user accidentally clicks the logout button, set a dialog to let user confirm
    private void showConfirmDialog(){
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(getActivity());
        //normalDialog.setIcon(R.drawable.icon_dialog);
        normalDialog.setTitle("Confirm");
        normalDialog.setMessage("Are you sure to confirm logging out?");
        normalDialog.setPositiveButton("CONFIRM",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // If user confirms, the user will be sent to the Sign in Activity to re-login.
                        Intent intent_to_login = new Intent(getActivity(), SigninActivity.class);
                        startActivity(intent_to_login);
                        // Set username to null, which is the default value
                        NoteActivity.username = null;
                        NoteAddActivity.username = null;
                        // finish the activity in case "back" in Android
                        getActivity().finish();
                    }
                });
        normalDialog.setNegativeButton("CLOSE",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { }
                });
        normalDialog.show();
    }


}