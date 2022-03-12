package com.diegocampos.ejerciciorevisiontecnica;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout myDrawer;
    NavigationView myNav;
    Toolbar myToolbar;

    ActionBarDrawerToggle toogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDrawer = findViewById(R.id.myNaveDrawer);
        myNav = findViewById(R.id.myNavView);
        myToolbar = findViewById(R.id.myToolbar);

        setSupportActionBar(myToolbar);

        myNav.setNavigationItemSelectedListener(this);

        toogle = setDrawerToogle();
        myDrawer.addDrawerListener(toogle);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toogle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toogle.syncState();
    }

    private ActionBarDrawerToggle setDrawerToogle() {
        return new ActionBarDrawerToggle(this, myDrawer, myToolbar, R.string.drawer_open, R.string.drawer_close);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toogle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //para mostrar los fragmentos
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        switch (item.getItemId()) {
            case R.id.itRevision:
                ft.replace(R.id.myFrame, new Fragmento1()).commit();
                Toast.makeText(MainActivity.this, "Elegiste Revisión", Toast.LENGTH_SHORT).show();
                break;
            case R.id.itBuscar:
                ft.replace(R.id.myFrame, new Fragmento2()).commit();
                Toast.makeText(MainActivity.this, "Elegiste Buscar", Toast.LENGTH_SHORT).show();
                break;
            case R.id.itModificar:
                ft.replace(R.id.myFrame, new Fragmento3()).commit();
                Toast.makeText(MainActivity.this, "Elegiste Modificar", Toast.LENGTH_SHORT).show();
                break;
            case R.id.itEliminar:
                ft.replace(R.id.myFrame, new Fragmento4()).commit();
                Toast.makeText(MainActivity.this, "Elegiste Eliminar", Toast.LENGTH_SHORT).show();
                break;
        }
        setTitle(item.getTitle()); //para mostrar el título
        myDrawer.closeDrawers(); //para cerrar drawer
        return true;
    }
}
