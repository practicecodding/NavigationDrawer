package com.hamidul.navigationdrawer;

import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.app.Notification;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import io.github.muddz.styleabletoast.StyleableToast;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    MaterialToolbar toolbar;
    NavigationView navigationView;
    Toast toast;
    StyleableToast styleableToast;
    ImageView headerImage;
    View headerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolBar);
        navigationView = findViewById(R.id.navigationView);
        headerView = navigationView.getHeaderView(0);
        headerImage = headerView.findViewById(R.id.headerImage);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                MainActivity.this,drawerLayout,toolbar,R.string.Close_Drawer,R.string.Open_Drawer);

        drawerLayout.addDrawerListener(toggle);

        BlankFragment.WEB_URL = "https://dsebd.org/";

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frameLayout,new BlankFragment());
        fragmentTransaction.commit();

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (item.getItemId()==R.id.btnShare){

                    BlankFragment.WEB_URL = "https://dsebd.org/";

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.frameLayout,new BlankFragment());
                    fragmentTransaction.commit();

                    if (styleableToast!=null) styleableToast.cancel();
                    styleableToast =StyleableToast.makeText(MainActivity.this, "Dhaka Stock Exchange", Toast.LENGTH_LONG, R.style.toastShare);
                    styleableToast.show();

                }

                return false;
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId()==R.id.navPerson){
                    drawerLayout.closeDrawer(GravityCompat.START);

                    BlankFragment.WEB_URL = "https://www.cse.com.bd/";

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.frameLayout,new BlankFragment());
                    fragmentTransaction.commit();

                    if (styleableToast!=null) styleableToast.cancel();
                    styleableToast =StyleableToast.makeText(MainActivity.this, "Chittagong Stock Exchange", Toast.LENGTH_LONG, R.style.toastPerson);
                    styleableToast.show();
                } else if (item.getItemId()==R.id.navShare) {
                    toastMessage("Share");
                    headerImage.setImageResource(R.drawable.share_icon);
                    return true;
                } else if (item.getItemId()==R.id.navMenu) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    toastMessage("Menu");
                } else if (item.getItemId()==R.id.navSetting) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    toastMessage("Setting");
                }
                return false;
            }
        });


    }

    private void toastMessage(String text){

        if (toast!=null) toast.cancel();

        toast = Toast.makeText(MainActivity.this,text,Toast.LENGTH_LONG);
        toast.show();

    }



}