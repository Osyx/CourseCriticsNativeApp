package io.osyx.coursecritics;

import android.app.ActionBar;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, LoginDialog.DialogListener {
    String logoutId = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        changeToolbarTitle();

        // Add icon to toolbar, only works if you don't use the custom text above.
        //getSupportActionBar().setLogo(R.drawable.ic_launcher_round);
        //getSupportActionBar().setDisplayUseLogoEnabled(true);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.nav_home:
                onBackPressed();
                break;
            case R.id.nav_courses:
                Intent intentForMain = new Intent(this, CoursesActivity.class);
                startActivity(intentForMain);
                break;
            case R.id.nav_review:
                Intent intentForReview = new Intent(this, ReviewActivity.class);
                startActivity(intentForReview);
                break;
            case R.id.nav_login:
                DialogFragment dialogFragment = new LoginDialog();
                dialogFragment.show(getFragmentManager(), "loginDialog");
                break;
            case R.id.nav_logout:
                logout();
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    public void search(View view) {
        EditText search = findViewById(R.id.main_search_bar);
        String searchText = search.getText().toString();
        Intent intentForCourses = new Intent(this, CoursesActivity.class);
        intentForCourses.putExtra("SEARCH", searchText);
        startActivity(intentForCourses);
    }

    private void changeToolbarTitle() {
        int color = getResources().getColor(R.color.colorPrimaryDark);
        TextView tv = new TextView(getApplicationContext());
        Typeface tf = ResourcesCompat.getFont(getApplicationContext(), R.font.pacifico);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        tv.setLayoutParams(lp);
        tv.setText(R.string.app_name);
        tv.setTextSize(20);
        tv.setTextColor(color);
        tv.setTypeface(tf);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(tv);
    }

    private void login(String username) {
        NavigationView navigationView = findViewById(R.id.nav_view);
        Menu menu = navigationView.getMenu();
        MenuItem nav_login = menu.findItem(R.id.nav_login);
        nav_login.setTitle(username);
        MenuItem menuLogout = menu.findItem(R.id.nav_logout);
        menuLogout.setTitle("Logout");
        menuLogout.setIcon(R.drawable.ic_logout);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void logout() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        Menu menu = navigationView.getMenu();
        MenuItem nav_login = menu.findItem(R.id.nav_login);
        nav_login.setTitle("Login");
        MenuItem menuLogout = menu.findItem(R.id.nav_logout);
        menuLogout.setTitle("");
        menuLogout.setIcon(R.drawable.trans);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onDialogPositiveClick(String username, String password) {
        login(username);
    }

    @Override
    public void onDialogNegativeClick() {

    }
}
