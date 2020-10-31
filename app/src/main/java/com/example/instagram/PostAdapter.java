package com.example.instagram;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private Context context;
    private List<Post> posts;

    public PostAdapter(List<Post> posts, Context context){
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post p = posts.get(position);
        holder.bind(p);

    }

    @Override
    public int getItemCount() {

            return posts.size();

    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView username;
        private TextView description;
        private ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.user);
            description = itemView.findViewById(R.id.description);
            image = itemView.findViewById(R.id.image);
        }

        public void bind(Post p) {
            description.setText(p.getDescription());
            username.setText(p.getAuthor().getUsername());
            ParseFile im = p.getImage();
            if(p.getImage() != null)
                Glide.with(context).load(im.getUrl()).into(image);
        }
    }

    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Post> list) {
        posts.addAll(list);
        notifyDataSetChanged();
    }


}
