package co.com.gmrtrd.andmil.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText nombreUsuario = (EditText) findViewById(R.id.idNombre);
        final EditText apellidoUsuario = (EditText) findViewById(R.id.idApellido);
        final EditText usuario = (EditText) findViewById(R.id.idUsuario);
        final EditText emailUsuario = (EditText) findViewById(R.id.idEmail);
        final EditText claveUsuario = (EditText) findViewById(R.id.idClave);
        final EditText confirmaClaveUsuario = (EditText) findViewById(R.id.idConfirmeClave);
        final Button botonRegistro = (Button) findViewById(R.id.botonRegistar);



    }
}
