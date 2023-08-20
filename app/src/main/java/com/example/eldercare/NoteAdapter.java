package com.example.eldercare;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    private Context mContext;
    private List<Note> mNoteList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView noteTitle;
        TextView noteDetail;
        TextView noteTimestamp;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            noteTitle = (TextView) view.findViewById(R.id.note_title);
            noteDetail = (TextView) view.findViewById(R.id.note_detail);
            noteTimestamp = (TextView) view.findViewById(R.id.note_timestamp);
        }
    }

    public NoteAdapter(List<Note> note_list) {
        mNoteList = note_list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.note_item, parent, false);

        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Note note = mNoteList.get(position);
                Intent intent = new Intent(mContext, NoteEditActivity.class);
                intent.putExtra(NoteEditActivity.NOTE_TITLE, note.getNoteTitle());
                intent.putExtra(NoteEditActivity.NOTE_DETAIL, note.getNoteDetail());
                mContext.startActivity(intent);
            }
        });

        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note = mNoteList.get(position);
        holder.noteTitle.setText(note.getNoteTitle());
        holder.noteDetail.setText(note.getNoteDetail());
        String timestampText = "Last Edited: " + note.getTimestamp();
        holder.noteTimestamp.setText(timestampText);
    }

    @Override
    public int getItemCount() {
        return mNoteList.size();
    }
}
