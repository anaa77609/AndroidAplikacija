package com.example.catchat;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); //linija alatki kao linija aplikacije za aktivnost



        //kreiranje instance drawer-a
        DrawerLayout drawerLayout = findViewById(R.id.drawer);
        //kreiranje instance klase action
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, toolbar,R.string.open, R.string.close);
        //Dodavanje listener-a na sam driver
        drawerLayout.addDrawerListener(drawerToggle);

        //vrsi se sinhronizacija stanja
        drawerToggle.syncState();

        //kreiranje instance NavigationView-a
        NavigationView navigationView = findViewById(R.id.navView);

        //postavljanje dogadjaja
        navigationView.setNavigationItemSelectedListener(this);

        //kreiranje instance Inbox Fragmenta
        Fragment fragment = new InboxFragment();
        //Transakcija fragmenta se koristi za prikazivanje instance fragmenta InboxFragment
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.content_frame, fragment);

        //commit signalizira da su sve potrebne operacije dodate u transakciju
        ft.commit();


    }
    @Override
    public boolean onNavigationItemSelected (MenuItem item)
    {
        int id = item.getItemId();
        Fragment fragment = null;
        Intent intent = null;
        switch (id)
        {
            case R.id.nav_drafts:
                fragment = new DraftsFragment();
                break;
            case R.id.nav_sent:
                fragment = new SentItemsFragment();
                break;
            case R.id.nav_trash:
                fragment = new TrashFragment();
            case R.id.nav_help:
                intent = new Intent(this, HelpActivity.class);
                break;
            case R.id.nav_feedback:
                intent = new Intent(this, FeedbackActivity.class);
                break;
            default:
                fragment = new InboxFragment();
        }

        if(fragment!=null)
        {
            FragmentTransaction fg = getSupportFragmentManager().beginTransaction();
            fg.replace(R.id.content_frame, fragment);
            fg.commit();

        }

        //Ako je fragment null pokrecemo aktivnost koja se nalazi u instanci klase intent
        else
        {
            startActivity(intent);
        }
        DrawerLayout drawer = findViewById(R.id.drawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //kada korisnik pritisne na back dugme
    @Override
    public void onBackPressed()
    {
        //inicijalizacija drawera
        DrawerLayout drawerLayout = findViewById(R.id.drawer);

        //provera da li je drawer otvoren, i ako jeste pritiskom na taster back, on se zatvara
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);

        }

        //ako je drawer zatvoren back vraca korisnika jedan korak unazad
        else
        {
            super.onBackPressed();
        }
    }

//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//        super.onPointerCaptureChanged(hasCapture);
//    }
}