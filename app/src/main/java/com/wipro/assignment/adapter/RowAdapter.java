package com.wipro.assignment.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wipro.assignment.R;
import com.wipro.assignment.response.Rows;

import java.util.List;

public class RowAdapter extends RecyclerView.Adapter<RowHolder> {

    private List<Rows> rowsList;

    public void setRows(List<Rows> rowsList) {
        this.rowsList = rowsList;
    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item, viewGroup, false);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder rowHolder, int i) {
        Rows rows = rowsList.get(i);
        rowHolder.setRow(rows);
    }

    @Override
    public int getItemCount() {
        return rowsList.size();
    }
}
