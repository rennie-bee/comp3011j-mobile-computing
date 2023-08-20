package com.example.eldercare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class NoteAddActivity extends AppCompatActivity {
    private EditText note_title, note_detail;
    private Button back, add;
    public static String username = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_add);

        note_title = (EditText) findViewById(R.id.add_note_title);
        note_detail = (EditText) findViewById(R.id.add_note_content);
        back = (Button) findViewById(R.id.back_to_note);
        add = (Button) findViewById(R.id.add_to_note);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NoteAddActivity.this, NoteActivity.class);
                startActivity(intent);
                NoteAddActivity.this.finish();
            }
        });

        // After add, store details in database
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = note_title.getText().toString().trim();
                String detail = note_detail.getText().toString().trim();
                long timeCurrentTimeMillis = System.currentTimeMillis();
                SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                String timestamp = sdf.format(timeCurrentTimeMillis);
                Note n = new Note();
                n.setNoteTitle(title);
                n.setNoteDetail(detail);
                n.setTimestamp(timestamp);
                n.setNoteAuthor(username);
                n.save();
                Intent intent = new Intent(NoteAddActivity.this, NoteActivity.class);
                startActivity(intent);
                NoteAddActivity.this.finish();
            }
        });
    }
}