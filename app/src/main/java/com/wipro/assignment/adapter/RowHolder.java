package com.wipro.assignment.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestFutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.wipro.assignment.R;
import com.wipro.assignment.response.Rows;

public class RowHolder extends RecyclerView.ViewHolder {

    TextView tvTitle, tvDescription;
    ImageView ivImage;
    Context context;

    public RowHolder(@NonNull View itemView) {
        super(itemView);
        context = itemView.getContext();

        tvTitle = itemView.findViewById(R.id.tvTitle);
        tvDescription = itemView.findViewById(R.id.tvDescription);
        ivImage = itemView.findViewById(R.id.ivImage);
    }

    public void setRow(Rows row) {
        if (row.getTitle() != null)
            tvTitle.setText(row.getTitle());
        else
            tvTitle.setText("");

        if (row.getDescription() != null)
            tvDescription.setText(row.getDescription());
        else
            tvDescription.setText("");

        if (row.getImage() != null) {
            ivImage.setVisibility(View.VISIBLE);

            String url = row.getImage();
            url = url.replace("http:", "https:");

            Glide.with(context)
                    .load(url)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            ivImage.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            ivImage.setVisibility(View.VISIBLE);
                            return false;
                        }
                    })
                    .centerInside()
                    .into(ivImage);
        } else {
            ivImage.setVisibility(View.GONE);
        }
    }
}
