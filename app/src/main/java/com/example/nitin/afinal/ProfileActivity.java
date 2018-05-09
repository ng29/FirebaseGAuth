package com.example.nitin.afinal;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class ProfileActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    private TextView pName,pEmail,pId;
    private ImageView dp;
    private Button b,logout;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        b=(Button)findViewById(R.id.show);
        logout = findViewById(R.id.logout);
        pName=(TextView)findViewById(R.id.pn);
        pEmail=(TextView)findViewById(R.id.pe);
        pId=(TextView)findViewById(R.id.pi);
        dp=(ImageView)findViewById(R.id.pic);
        mAuth=FirebaseAuth.getInstance();

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUI(user);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent i=new Intent(ProfileActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
    private void updateUI(FirebaseUser user) {

        logout.setVisibility(View.VISIBLE);
        pId.setVisibility(View.VISIBLE);
        pName.setVisibility(View.VISIBLE);
        pEmail.setVisibility(View.VISIBLE);
        dp.setVisibility(View.VISIBLE);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if (acct != null) {
            pName.setText( acct.getDisplayName());
            pEmail.setText(acct.getEmail());
             pId .setText(acct.getId());
             Uri uri = acct.getPhotoUrl();
            Glide.with(this).load(uri).into(dp);
        }

    }
}
