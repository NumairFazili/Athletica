package com.example.athletica.View.facility;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.athletica.R;
import com.example.athletica.Model.Comments;
import com.example.athletica.Model.Ratings;

import java.util.List;

public class CommentAdapter extends ArrayAdapter<Comments> {


    private Activity context;
    private List<Comments> commentsList;
    private List<Ratings> ratingsList;
    private float userRating;

    public CommentAdapter(Activity context, List<Comments> commentsList,List<Ratings> ratingsList) {
        super(context, R.layout.activity_comment_adapter, commentsList);
        this.context = context;
        this.commentsList = commentsList;
        this.ratingsList=ratingsList;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listView = inflater.inflate(R.layout.activity_comment_adapter, null, true);

        TextView comment_text = (TextView) listView.findViewById(R.id.comment_text_view);
        RatingBar ratingBar=(RatingBar) listView.findViewById((R.id.ratingBar));
        TextView userName=(TextView)listView.findViewById(R.id.comment_user_name);

        Comments com = commentsList.get(position);
        comment_text.setText(com.getCommentContent());
        for (Ratings r :this.ratingsList){
            if((r.getUserID()).equals(com.getUserID()))
                this.userRating=r.getRatingContent();
        }

        ratingBar.setRating(this.userRating);
        userName.setText(com.getUserName());

        return listView;

    }
}
