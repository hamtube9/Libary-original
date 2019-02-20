package d.lmao.libary;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import d.lmao.libary.DAO.BookDAO;
import d.lmao.libary.DAO.UserDAO;
import d.lmao.libary.adapter.BookAdapter;
import d.lmao.libary.model.Book;

public class ListSachActivity extends AppCompatActivity {

    private ListView lvSach;
    private BookAdapter bookAdapter;
    private List<Book> bookList;
    private BookDAO bookDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sach);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        initViews();


        lvSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position_, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ListSachActivity.this);
                builder.setTitle(" Bạn muốn sửa hay xóa");
                builder.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(ListSachActivity.this, SuaSachActivity.class);
                        startActivity(intent);


                    }
                });

                builder.setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {

                        bookDAO.deleteBook(bookList.get(position_).getMasach());
                        bookList.remove(position_);
                        bookAdapter.notifyDataSetChanged();


                    }
                });
                builder.show();
            }
        });
    }

    private void initViews() {
    lvSach=findViewById(R.id.lvSach);
    bookDAO=new BookDAO(ListSachActivity.this);
    bookList=bookDAO.getBook();
    bookAdapter=new BookAdapter(this,bookList);
    lvSach.setAdapter(bookAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {


            case R.id.action_ThemSach:
                Intent intent = new Intent(ListSachActivity.this, ThemSachActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onResume() {
        super.onResume();
        bookList.clear();
        bookList=bookDAO.getBook();
        bookAdapter.changeDataset(bookList);
        super.onResume();
    }
}
