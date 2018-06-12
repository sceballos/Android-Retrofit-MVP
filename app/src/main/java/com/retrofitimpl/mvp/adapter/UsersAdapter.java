package com.retrofitimpl.mvp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.retrofitimpl.mvp.R;
import com.retrofitimpl.mvp.activity.details.UserDetailsActivity;
import com.retrofitimpl.mvp.data.user.RandomUser;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {

    private Context context;
    private List<RandomUser> usersList;
    private int lastPosition = -1;


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView picture;
        public TextView name;

        public MyViewHolder(View view) {
            super(view);
            picture = view.findViewById(R.id.user_picture);
            name = view.findViewById(R.id.user_name);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //open details
            CardView parentCard = (CardView) view;

            try {
                RecyclerView parentRow = (RecyclerView) parentCard.getParent();

                final int position = parentRow.getChildAdapterPosition(parentCard);

                RandomUser currentUser = usersList.get(position);

                Intent intent = new Intent(context, UserDetailsActivity.class);
                intent.putExtra("clicked_user", currentUser);

                context.startActivity(intent);
                Activity activity = (Activity) context;
                activity.overridePendingTransition(R.anim.activity_enter, R.anim.activity_exit);


            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    public UsersAdapter(Context context, List<RandomUser> usersList) {
        this.context = context;
        this.usersList = usersList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_user, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        RandomUser user = usersList.get(position);

        Glide.with(holder.picture.getContext())
                .load(user.getUserPictures().getLarge())
                .centerCrop()
                .crossFade()
                .into(holder.picture);

        String capitalizedTitle = user.getUserName().getTitle().substring(0, 1).toUpperCase() +
                user.getUserName().getTitle().substring(1);

        String capitalizedFirstName = user.getUserName().getFirst().substring(0, 1).toUpperCase() +
                user.getUserName().getFirst().substring(1);

        String capitalizedLastName = user.getUserName().getLast().substring(0, 1).toUpperCase() +
                user.getUserName().getLast().substring(1);

        String fullUsername = capitalizedTitle + " " +
                capitalizedFirstName + " " + capitalizedLastName;
        holder.name.setText(fullUsername);
        setAnimation(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    /**
     * Here is the key method to apply the animation
     */
    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.item_list_animation);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

}
