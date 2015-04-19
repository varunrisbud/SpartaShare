package com.example.android.spartashare;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.*;
import com.facebook.android.DialogError;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;

import org.brickred.socialauth.Profile;
import org.brickred.socialauth.android.DialogListener;
import org.brickred.socialauth.android.SocialAuthAdapter;
import org.brickred.socialauth.android.SocialAuthError;
import org.brickred.socialauth.android.SocialAuthListener;

import java.util.Arrays;


public class MainActivity extends Activity {
    private SocialAuthAdapter adapter;

    //Android Component
    private Button fb_button, tw_button, g_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        //Social Adapter
        adapter = new SocialAuthAdapter(new DialogListener() {
            @Override
            public void onComplete(Bundle bundle) {
                adapter.getUserProfileAsync(new SocialAuthListener<Profile>() {
                    @Override
                    public void onExecute(String s, Profile profile) {
                        Log.e("Custom UI", "Login Receiving Data");
                        Profile profileMap = profile;
                        Log.d("Custom-UI",  "Validate ID         = " + profileMap.getValidatedId());
                        Log.d("Custom-UI",  "First Name          = " + profileMap.getFirstName());
                        Log.d("Custom-UI",  "Last Name           = " + profileMap.getLastName());
                        Log.d("Custom-UI",  "Email               = " + profileMap.getEmail());
                        Log.d("Custom-UI",  "Gender              = " + profileMap.getGender());
                        Log.d("Custom-UI",  "Country             = " + profileMap.getCountry());
                        Log.d("Custom-UI",  "Language            = " + profileMap.getLanguage());
                        Log.d("Custom-UI",  "Location            = " + profileMap.getLocation());
                        Log.d("Custom-UI",  "Profile Image URL   = " + profileMap.getProfileImageURL());
                    }

                    @Override
                    public void onError(SocialAuthError socialAuthError) {
                        Log.e("Custom UI", "Profile Data Error");
                    }
                });
            }

            @Override
            public void onError(SocialAuthError socialAuthError) {
                Log.e("Login activity", socialAuthError.getMessage());
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onBack() {

            }
        });


        //Wire up the Login Buttons
        fb_button = (Button) findViewById(R.id.fbLoginButton);
      //  tw_button = (Button) findViewById(R.id.buttonTwitter);
       // g_button = (Button) findViewById(R.id.buttonGoogle);

        //Event Listener for Click

        //Facebook
        fb_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.authorize(MainActivity.this, SocialAuthAdapter.Provider.FACEBOOK);
                Toast.makeText(MainActivity.this, "I am Facebook", Toast.LENGTH_SHORT).show();
            }
        });

   /*     //Twitter
        tw_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addCallBack(SocialAuthAdapter.Provider.TWITTER,"http://domain.com/auth/twitter");
                adapter.authorize(LoginActivity.this, SocialAuthAdapter.Provider.TWITTER);
                Toast.makeText(LoginActivity.this, "I am Twitter", Toast.LENGTH_SHORT).show();
            }
        });

        //Google
        g_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addCallBack(SocialAuthAdapter.Provider.GOOGLE,"http://domain.com/google");
                adapter.authorize(LoginActivity.this, SocialAuthAdapter.Provider.GOOGLE);
                Toast.makeText(LoginActivity.this, "I am Google", Toast.LENGTH_SHORT).show();
            }
        });
 */
    }

}