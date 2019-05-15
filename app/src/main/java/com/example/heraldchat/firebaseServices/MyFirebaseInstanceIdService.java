package com.example.heraldchat.firebaseServices;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import static android.support.constraint.Constraints.TAG;

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {



    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

       String refreshedtoken= FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedtoken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.



    }
}
