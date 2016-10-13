package co.com.gmrtrd.andmil.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText usuarioLogin = (EditText) findViewById(R.id.usuarioLogin);
        final EditText claveLogin = (EditText) findViewById(R.id.claveLogin);
        final Button botonIngresar = (Button) findViewById(R.id.botonIngresar);
        final TextView olvidoLink = (TextView) findViewById(R.id.linkRecuperacion);
        final TextView registroLink = (TextView) findViewById(R.id.linkRegistro);
        findViewById(R.id.sign_in_button).setOnClickListener(this);

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

    @Override
    public void onClick(View v) {

    }
}
