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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CoursesActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, LoginDialog.DialogListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
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

        EditText searchBar = findViewById(R.id.courses_search_bar);
        searchBar.setText(getIntent().getStringExtra("SEARCH"));

        if(getIntent().getBooleanExtra("TOAST", false))
            Toast.makeText(getApplicationContext(), "Review submitted!", Toast.LENGTH_SHORT).show();

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


    public void onClickingCourse(View view) {
        ImageView course = (ImageView) findViewById(R.id.course1exp);
        if(course.getVisibility() == View.GONE) {
            course.setVisibility(View.VISIBLE);
        }
        else {
            course.setVisibility(View.GONE);
        }
    }

    public void onClickingCourse2(View view) {
        ImageView course = (ImageView) findViewById(R.id.course2exp);
        if(course.getVisibility() == View.GONE) {
            course.setVisibility(View.VISIBLE);
        }
        else {
            course.setVisibility(View.GONE);
        }
    }

    public void onClickingCourse3(View view) {
        ImageView course = (ImageView) findViewById(R.id.course3exp);
        if(course.getVisibility() == View.GONE) {
            course.setVisibility(View.VISIBLE);
        }
        else {
            course.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.courses, menu);
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
                Intent intentForMain = new Intent(this, MainActivity.class);
                //intentForMain.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentForMain);
                break;
            case R.id.nav_courses:
                onBackPressed();
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

    private void changeToolbarTitle() {
        int color = getResources().getColor(R.color.colorPrimaryDark);
        TextView tv = new TextView(getApplicationContext());
        Typeface tf = ResourcesCompat.getFont(getApplicationContext(), R.font.pacifico);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        tv.setLayoutParams(lp);
        tv.setText(R.string.course_activity_title);
        tv.setTextSize(20);
        tv.setTextColor(color);
        tv.setTypeface(tf);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(tv);
    }
}
