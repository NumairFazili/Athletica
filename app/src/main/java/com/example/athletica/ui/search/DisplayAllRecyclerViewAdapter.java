package com.example.athletica.ui.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.athletica.R;
import com.example.athletica.data.facility.Facility;

import java.util.List;

public class DisplayAllRecyclerViewAdapter extends RecyclerView.Adapter<DisplayAllRecyclerViewAdapter.MyViewHolder> {
    private List<Facility> facilities;

    public DisplayAllRecyclerViewAdapter(List<Facility> facilities) {
        this.facilities = facilities;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_mainpage, parent, false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Facility facility = facilities.get(position);
        holder.tvName.setText(facility.getName());
    }

    @Override
    public int getItemCount() {
        return facilities.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;

        public MyViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.textView_1_main_page);
        }
    }
}

