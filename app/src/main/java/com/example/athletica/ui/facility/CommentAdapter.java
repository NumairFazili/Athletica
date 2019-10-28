package com.example.athletica.ui.facility;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.athletica.R;
import com.example.athletica.data.facility.Comment;

import java.util.List;

public class CommentAdapter extends ArrayAdapter<Comment> {


    private Activity context;
    private List<Comment> commentsList;

    public CommentAdapter(Activity context, List<Comment> commentsList) {
        super(context, R.layout.activity_comment_adapter, commentsList);
        this.context = context;
        this.commentsList = commentsList;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listView = inflater.inflate(R.layout.activity_comment_adapter, null, true);

        TextView comment_text = (TextView) listView.findViewById(R.id.comment_text_view);

        Comment com = commentsList.get(position);
        comment_text.setText(com.getCommentContent());

        return listView;

    }
}
