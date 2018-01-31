package com.example.narendra.quicklift2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    private static Button login,signup;

    //private final int REQUEST_LOGIN = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser() != null) {

            //if already Login
            if (!FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber().isEmpty()) {
                startActivity(new Intent(this, MainActivity.class)
                        .putExtra("phone", FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber().isEmpty())
                );
                finish();
            }
        }else{
            startActivityForResult(AuthUI.getInstance()
                    .createSignInIntentBuilder().setAvailableProviders(Arrays.asList(
                            new AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER).build()
                    )).build(),REQUEST_LOGIN);
        }
*/

        setContentView(R.layout.activity_login);

        signup = findViewById(R.id.login2signup);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(login);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signupIntent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(signupIntent);
            }
        });



    }
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_LOGIN){
            IdpResponse response = IdpResponse.fromResultIntent(data);

            //Successfully Loged In
            if (resultCode == RESULT_OK){
                if (!FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber().isEmpty()){
                    startActivity(new Intent(this,MainActivity.class)
                            .putExtra("phone",FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber().isEmpty())
                    );
                    finish();
                    return;
                }else{
                    //Login Failed
                    if (response == null){
                        Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (response.getErrorCode() == ErrorCodes.NO_NETWORK){
                        Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (response.getErrorCode() == ErrorCodes.UNKNOWN_ERROR){
                        Toast.makeText(this, "Unknown Error", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                Toast.makeText(this, "Unknown Signin Error !!! ", Toast.LENGTH_SHORT).show();
            }
        }
    }
    */

}
