package co.com.gmrtrd.andmil.myapplication;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public boolean isFirstStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hiloSplash();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_buscar) {
            // Handle the camera action
        } else if (id == R.id.nav_notificacion) {

        } else if (id == R.id.nav_favorito) {

        } else if (id == R.id.nav_cambiar) {

        } else if (id == R.id.nav_publicado) {

        } else if (id == R.id.nav_ingresoRegistro) {
            Intent i = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_datosPersonales) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
                    Intent i = new Intent(MainActivity.this, MyIntro.class);
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
