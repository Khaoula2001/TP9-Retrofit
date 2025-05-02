package com.emsi.retrofit_mobile.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.emsi.retrofit_mobile.R;
import com.emsi.retrofit_mobile.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> userList;
    private List<User> fullUserList;

    public void setUserList(List<User> users) {
        this.userList = users;
        this.fullUserList = new ArrayList<>(users);
        notifyDataSetChanged();
    }

    public void filterByIdOrName(String query) {
        if (query == null || query.isEmpty()) {
            userList = new ArrayList<>(fullUserList);
        } else {
            userList = new ArrayList<>();
            for (User user : fullUserList) {
                if (user.getName().toLowerCase().contains(query.toLowerCase()) ||
                        String.valueOf(user.getId()).contains(query)) {
                    userList.add(user);
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView name, email, phone, city;

        public UserViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.userName);
            email = itemView.findViewById(R.id.userEmail);
            phone = itemView.findViewById(R.id.userPhone);
            city = itemView.findViewById(R.id.userCity);
        }
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.name.setText(user.getName());
        holder.email.setText(user.getEmail());
        holder.phone.setText(user.getPhone());
        holder.city.setText(user.getCity());
    }

    @Override
    public int getItemCount() {
        return (userList == null) ? 0 : userList.size();
    }
}