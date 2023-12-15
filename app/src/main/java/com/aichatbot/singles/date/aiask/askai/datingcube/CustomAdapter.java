package com.aichatbot.singles.date.aiask.askai.datingcube;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private List<User> userList;
    private Context context;
    private List<Integer> distances;

    public CustomAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
        generateRandomDistances();
    }
    private void generateRandomDistances() {
        distances = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < userList.size(); i++) {
            int randomDistance = random.nextInt(5) + 1;
            distances.add(randomDistance);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = userList.get(position);
        // Generate a random distance between 1 and 10
        int randomDistance = new Random().nextInt(5) + 1;
        String distance = String.valueOf(randomDistance);
        holder.bind(user, distances.get(position).toString());
        holder.bind(user, distance);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageViewBackground;
        private TextView textViewName;
        private TextView textViewAge;
        private TextView textViewDistance;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.imageViewBackground);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewAge = itemView.findViewById(R.id.textViewAge);
            textViewDistance = itemView.findViewById(R.id.textViewDistance);
        }

        public void bind(User user, String distance) {
            textViewName.setText(user.getName());
            textViewAge.setText(String.valueOf(user.getAge()));
            textViewDistance.setText(distance);
            // Load the image using Glide
            String imageUrl = user.getImageUri();

            // Use Glide to load the image into the imageViewBackground
            Glide.with(context)
                    .load(imageUrl)
                    .transform(new RoundedCorners(16)) // Set the corner radius as needed
                    .into(imageViewBackground);

            // Set OnClickListener for detailed view or any specific action
            itemView.setOnClickListener(v -> {
                // Implement what happens when an item is clicked
                // For example: Open a detailed activity for this user
//                Intent intent = new Intent(context, DetailedActivity.class);
//                intent.putExtra("userId", user.getUserId());
//                context.startActivity(intent);
            });
        }
    }
}

