package app.com.cricapp.prashantmaurya.scoremonitor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by prashant.maurya on 1/4/2017.
 */

public class CreateAccountActivity extends BaseActivity implements View.OnClickListener {

    EditText nickname,password,emailId;

    private static final String TAG = "EmailPassword";

    private FirebaseAuth mAuth;
    // [END declare_auth]

    // [START declare_auth_listener]
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_layout);
        nickname=(EditText)findViewById(R.id.input_nickname);
        emailId=(EditText)findViewById(R.id.input_email);
        password=(EditText)findViewById(R.id.input_password);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    createOtherActivity();
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // [START_EXCLUDE]


                // [END_EXCLUDE]

            }
        };
        Button createAccount=(Button)findViewById(R.id.create_account_button);
        createAccount.setOnClickListener(this);


    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    // [END on_start_add_listener]
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    private void createOtherActivity() {
        startActivity(new Intent(CreateAccountActivity.this,MainActivity.class));
    }

    @Override
    public void onClick(View v) {
        int i=v.getId();
        if(i==R.id.create_account_button){
            SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("MyPref",0);
            SharedPreferences.Editor editor=sharedPref.edit();
            editor.putString("NickName",nickname.getText().toString());
            editor.commit();
            createAccount(emailId.getText().toString(), password.getText().toString());

        }

    }
    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccont:" + email);
        if (!validateForm()) {
            return;
        }

        showProgressDialog();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(CreateAccountActivity.this, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        }

                        // [START_EXCLUDE]
                        hideProgressDialog();
                    }
                });


    }

    private boolean validateForm() {
        boolean valid = true;

        String email = emailId.getText().toString();
        if (TextUtils.isEmpty(email)) {
            emailId.setError("Required.");
            valid = false;
        } else {
            emailId.setError(null);
        }

        String passwords = password.getText().toString();
        if (TextUtils.isEmpty(passwords)) {
            password.setError("Required.");
            valid = false;
        } else {
            password.setError(null);
        }

        return valid;
    }


}
