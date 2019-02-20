package d.lmao.libary;

import android.content.Intent;
import android.os.Bundle;
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
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ImageView imgbtnSach,imgbtnThemSach,imgbtnUser,imgbtnHoaDon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgbtnSach=findViewById(R.id.imgbtnSach);
        imgbtnThemSach=findViewById(R.id.imgbtnThemSach);
                imgbtnUser=findViewById(R.id.imgbtnUser);
        imgbtnHoaDon=findViewById(R.id.imgbtnHoaDon);

        imgbtnSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ListSachActivity.class);
                startActivity(intent);
            }
        });

        imgbtnThemSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ListLoaiSachActivity.class);
                startActivity(intent);
            }
        });
        imgbtnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,UserActivity.class);
                startActivity(intent);
            }
        });


                imgbtnHoaDon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(MainActivity.this,ListHoaDonActivity.class);
                        startActivity(intent);
                    }
                });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_sach) {
            Intent intent=new Intent(MainActivity.this,ListSachActivity.class);
            startActivity(intent);

        }else  if (id == R.id.nav_them_sach) {
            Intent intent=new Intent(MainActivity.this,ThemSachActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_loai_sach) {
            Intent intent=new Intent(MainActivity.this,ListLoaiSachActivity.class);
            startActivity(intent);


        }else if (id == R.id.nav_them_loai_sach) {
            Intent intent=new Intent(MainActivity.this,ThemLoaiSachActivity.class);
            startActivity(intent);


        }
        else if (id == R.id.nav_User) {
            Intent intent=new Intent(MainActivity.this,UserActivity.class);
            startActivity(intent);

        }  else if (id == R.id.nav_change_pass) {
            Intent intent=new Intent(MainActivity.this,ChangePasswordActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_hoa_don) {
            Intent intent=new Intent(MainActivity.this,ListHoaDonActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_them_hoadon) {
            Intent intent=new Intent(MainActivity.this,ThemHoaDonActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
