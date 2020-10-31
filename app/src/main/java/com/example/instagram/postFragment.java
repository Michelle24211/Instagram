package com.example.instagram;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class postFragment extends Fragment {
    private RecyclerView recycleView;
    private List <Post> list;
    private PostAdapter postAdapter;
    private SwipeRefreshLayout swipeContainer;
    public postFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        recycleView = view.findViewById(R.id.recycle);
        list = new ArrayList<>();
        postAdapter =  new PostAdapter(list, getContext());
        recycleView.setAdapter(postAdapter);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        queryPost();

        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                queryPost();

            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }




    private void queryPost() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_AUTHOR);
        query.setLimit(20);
        query.addDescendingOrder(Post.KEY_CREATED);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if(e == null){
                    for(Post post: posts){
                        Log.i("post", post.getDescription() + " " + post.getAuthor().getUsername());
                    }

                    postAdapter.clear();
                    postAdapter.addAll(posts);
                    swipeContainer.setRefreshing(false);
                }
                else {
                    Toast.makeText(getContext(), "Error with Posts", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}