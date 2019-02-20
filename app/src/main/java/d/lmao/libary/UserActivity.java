package d.lmao.libary;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import d.lmao.libary.DAO.UserDAO;
import d.lmao.libary.adapter.UserAdapter;
import d.lmao.libary.database.DatabaseHelper;
import d.lmao.libary.model.User;

public class UserActivity extends AppCompatActivity {

    private ListView lvListUsers;
    private UserAdapter userAdapter;
    private List<User> userList;
        private UserDAO userDAO;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        initView();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        lvListUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position_, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);
                builder.setTitle(" Bạn muốn sửa hay xóa");
                builder.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(UserActivity.this, ChangePasswordActivity.class);
                        startActivity(intent);


                    }
                });

                builder.setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {

                        userDAO.deleteUser(userList.get(position_).getUsername());
                        userList.remove(position_);
                        userAdapter.notifyDataSetChanged();


                    }
                });
                builder.show();
            }
        });



    }

    private void initView() {

        lvListUsers = findViewById(R.id.lvUser);
        userDAO = new UserDAO(UserActivity.this);

        userList = userDAO.getAllUser();

        userAdapter = new UserAdapter(this, userList);
        lvListUsers.setAdapter(userAdapter);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        userList.clear();
        userList=userDAO.getAllUser();
        userAdapter.changeDataset(userList);
    }
}
