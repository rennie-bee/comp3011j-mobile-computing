package com.example.eldercare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.getbase.floatingactionbutton.FloatingActionButton;

import org.litepal.LitePal;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class NoteActivity extends AppCompatActivity {
    private NoteAdapter adapter;
    private List<Note> noteList = new ArrayList<>();
    private Note n;
    public static String username = null;
    private com.getbase.floatingactionbutton.FloatingActionButton fab_add, fab_back;
    private TextView noteDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        initNotes();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_note);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);

        fab_add = (FloatingActionButton) findViewById(R.id.fab_add);
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(NoteActivity.this, "FAB", Toast.LENGTH_SHORT).show();
                Intent intent_to_add_note = new Intent(NoteActivity.this, NoteAddActivity.class);
                startActivity(intent_to_add_note);
            }
        });

        fab_back = (FloatingActionButton) findViewById(R.id.fab_back);
        fab_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_back = new Intent(NoteActivity.this, MainActivity.class);
                startActivity(intent_back);
            }
        });

        adapter = new NoteAdapter(noteList);
        recyclerView.setAdapter(adapter);


    }

    void initNotes() {
        List<Note> note_list = LitePal.where("author_name = ?", username).find(Note.class);
        if (note_list.size() > 0) {
            for (int i = 0; i < note_list.size(); i++) {
                n = new Note(note_list.get(i).getNoteTitle(), note_list.get(i).getNoteDetail(),
                        note_list.get(i).getNoteAuthor(), note_list.get(i).getTimestamp());
                noteList.add(n);
            }
        }

        //Log.d("a", String.valueOf(note_list.size()));
    }

}