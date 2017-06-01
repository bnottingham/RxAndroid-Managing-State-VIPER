package com.oneshot.rxjavaapp.feature.photolist.presentation.view.adapter;

import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.oneshot.rxjavaapp.R;
import com.oneshot.rxjavaapp.feature.photolist.presentation.model.PhotoModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brettn on 4/27/17.
 */
public class PhotoListAdapter extends RecyclerView.Adapter<PhotoListAdapter.ImageItemViewHolder> {

    private final List<PhotoModel> mPhotos = new ArrayList<>();

    public PhotoListAdapter() {
    }

    @UiThread
    public void setData(@Nullable final List<PhotoModel> photos) {
        mPhotos.clear();

        if (photos == null || photos.size() == 0) {
            return;
        }

        mPhotos.addAll(photos);
        notifyDataSetChanged();
    }

    @UiThread
    public void appendData(@Nullable final List<PhotoModel> photos) {
        if (photos == null || photos.size() == 0) {
            return;
        }

        int count = mPhotos.size();
        mPhotos.addAll(photos);

        notifyItemRangeInserted(count, photos.size());
    }

    @Override
    public ImageItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_photo, parent, false);
        ImageItemViewHolder viewHolder = new ImageItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ImageItemViewHolder holder, int position) {
        final String imageUrl = mPhotos.get(position).getImageUrl();

        Glide.with(holder.mImageView.getContext())
                .load(imageUrl)
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mPhotos.size();
    }


    public class ImageItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;

        public ImageItemViewHolder(View view) {
            super(view);
            mImageView = (ImageView) view.findViewById(R.id.list_item_image_view);
        }
    }
}
