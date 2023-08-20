package com.example.eldercare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class NoteEditActivity extends AppCompatActivity {
    public static final String NOTE_TITLE = "note_title";
    public static final String NOTE_DETAIL = "note_detail";
    private EditText note_title, note_detail;
    private Button back, edit, delete;
    private String noteTitle, noteDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);

        note_title = (EditText) findViewById(R.id.edit_note_title);
        note_detail = (EditText) findViewById(R.id.edit_note_content);
        back = (Button) findViewById(R.id.back_to_note_edit);
        edit = (Button) findViewById(R.id.edit_to_note);
        delete = (Button) findViewById(R.id.edit_to_note_delete);

        Intent intent = getIntent();
        noteTitle = intent.getStringExtra(NOTE_TITLE);
        noteDetail = intent.getStringExtra(NOTE_DETAIL);
        note_title.setText(noteTitle);
        note_detail.setText(noteDetail);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NoteEditActivity.this, NoteActivity.class);
                startActivity(intent);
                NoteEditActivity.this.finish();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = note_title.getText().toString().trim();
                String detail = note_detail.getText().toString().trim();
                List<Note> noteLst = LitePal.where("title = ? and detail = ? and author_name = ?",
                        noteTitle, noteDetail, NoteActivity.username).find(Note.class);
                Note n = noteLst.get(0);
                n.setNoteTitle(title);
                n.setNoteDetail(detail);
                long timeCurrentTimeMillis = System.currentTimeMillis();
                SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                String timestamp = sdf.format(timeCurrentTimeMillis);
                n.setTimestamp(timestamp);
                n.save();
                Intent intent = new Intent(NoteEditActivity.this, NoteActivity.class);
                startActivity(intent);
                NoteEditActivity.this.finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteDialog();
            }
        });


    }

    // In case that user accidentally clicks the logout button, set a dialog to let user confirm
    private void showDeleteDialog(){
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(this);
        //normalDialog.setIcon(R.drawable.icon_dialog);
        normalDialog.setTitle("Confirm");
        normalDialog.setMessage("Are you sure to confirm deleting?");
        normalDialog.setPositiveButton("CONFIRM",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // If user confirms, the user will be sent to the Sign in Activity to re-login.
                        Intent intent_to_note = new Intent(NoteEditActivity.this, NoteActivity.class);
                        startActivity(intent_to_note);
                        String title = note_title.getText().toString().trim();
                        String detail = note_detail.getText().toString().trim();
                        LitePal.deleteAll(Note.class, "title = ? and detail = ?", noteTitle, noteDetail);
                        // finish the activity in case "back" in Android
                        NoteEditActivity.this.finish();
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