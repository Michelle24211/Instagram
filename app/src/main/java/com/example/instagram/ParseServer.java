package com.example.instagram;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;

import android.app.Application;

public class ParseServer extends Application {

    // Initializes Parse SDK as soon as the application is created
    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(Post.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("pMk1ErXTtX9u4QuOh07FR5Za4rzKHTem2Pp0EYNq")
                .clientKey("LCJEdRmpapHJVDoAlv5efBdck4VHtSfYI2eIIOtJ")
                .server("https://parseapi.back4app.com")
                .build()
        );

    }
}