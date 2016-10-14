package co.com.gmrtrd.andmil.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener, View.OnClickListener{

    public static String TAG="LoginActivity.java";
    public static final int RC_SIGN_OUT = 50;
    private static final int RC_SIGN_IN =20;
    private static boolean persistedEnabled = false;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
   GoogleApiClient mGoogleApiClient;
    public boolean isFirstStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        hiloSplash();
        final EditText usuarioLogin = (EditText) findViewById(R.id.usuarioLogin);
        final EditText claveLogin = (EditText) findViewById(R.id.claveLogin);
        final Button botonIngresar = (Button) findViewById(R.id.botonIngresar);
        final TextView olvidoLink = (TextView) findViewById(R.id.linkRecuperacion);
        final TextView registroLink = (TextView) findViewById(R.id.linkRegistro);
        findViewById(R.id.sign_in_button).setOnClickListener(this);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        Log.d(TAG,getString(R.string.default_web_client_id));
// ...
        mAuth = FirebaseAuth.getInstance();
        if(!LoginActivity.persistedEnabled){
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            persistedEnabled = true;
        }
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            Intent ingresoIntent = new Intent(LoginActivity.this, MainActivity.class);
            LoginActivity.this.startActivity(ingresoIntent);
        }



        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }


    public void regristroClick(View v) {
        Intent registroIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        LoginActivity.this.startActivity(registroIntent);
    }

    public void olvidoClick(View v) {
        Intent olvidoIntent = new Intent(LoginActivity.this, RestauraContrasena.class);
        LoginActivity.this.startActivity(olvidoIntent);
    }

    public void ingresoClick(View v) {
        Intent ingresoIntent = new Intent(LoginActivity.this, MainActivity.class);
        LoginActivity.this.startActivity(ingresoIntent);
    }
    private void signIn() {
        /*Intent ingresoIntent = new Intent(LoginActivity.this, MainActivity.class);
       // LoginActivity.this.startActivity(ingresoIntent);
        //Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        //startActivityForResult(signInIntent, RC_SIGN_IN);*/
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setProviders(
                                AuthUI.EMAIL_PROVIDER,
                                AuthUI.GOOGLE_PROVIDER)
                        .build(),
                RC_SIGN_IN);
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                // user is signed in!
                startActivity(new Intent(this, MainActivity.class));

            } else {
                Toast.makeText(LoginActivity.this, "Try again later",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // ...
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.sign_in_button) {
            signIn();
        } /*else if (i == R.id.sign_out_button) {
            signOut();
        } else if (i == R.id.disconnect_button) {
            revokeAccess();
        }
    }*/
}

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }
    private void hiloSplash(){
        //  Declaración del hilo para hacer un preference check
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //  Inicialización del SharedPreferences
                SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                //  Cración del booleano y la preferencia como true
                isFirstStart = getPrefs.getBoolean("firstStart", true);
                //  Si la aplicación nunca ha sido ejecutada...
                if (isFirstStart) {
                    //  Lanzamiento de la introducción de la app.
                    Intent i = new Intent(LoginActivity.this, MyIntro.class);
                    startActivity(i);
                    // Realización de un nuevo preferences editor
                    SharedPreferences.Editor e = getPrefs.edit();
                    //  Se hace el editor "false" porque no queremos que vuelva a aparecer la intro.
                    e.putBoolean("firstStart", false);
                    //  Se aplican los cambios
                    e.apply();
                }
            }
        });
        t.start();
    }
}
