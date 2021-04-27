package com.medianet.miniproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.medianet.miniproject.model.Game;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {

    private List<Game> mData;
    private Context context;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    GameAdapter(Context context, List<Game> data) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_game, parent, false);

        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Game game = mData.get(position);
        holder.myTextView.setText(game.getName());
        if (game.getIsFavorie()){
            holder.favorite.setBackground(ContextCompat.getDrawable(context,R.drawable.ic_favorite));
        }else             holder.favorite.setBackground(ContextCompat.getDrawable(context,R.drawable.ic_favorite_empty));

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        View favorite;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.game_name);
            favorite = itemView.findViewById(R.id.fav);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            mData.get(getAdapterPosition()).setIsFavorie(!mData.get(getAdapterPosition()).getIsFavorie());
            notifyItemChanged(getAdapterPosition());
            if (mClickListener != null) {
                mClickListener.onItemClick(mData.get(getAdapterPosition()) );
            }

        }
    }


    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick( Game game);
    }
}