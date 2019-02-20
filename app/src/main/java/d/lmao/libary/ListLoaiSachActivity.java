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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import d.lmao.libary.DAO.TypeBookDAO;
import d.lmao.libary.adapter.HoaDonAdapter;
import d.lmao.libary.adapter.LoaiSachAdapter;
import d.lmao.libary.model.TypeBook;

public class ListLoaiSachActivity extends AppCompatActivity {
    private ListView lvLoaiSach;
    private LoaiSachAdapter loaiSachAdapter;
    private LinearLayoutManager linearLayoutManager;

    private List<TypeBook> typeBookList;

    private TypeBookDAO typeBookDAO;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_loai_sach);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        initView();

        lvLoaiSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position_, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ListLoaiSachActivity.this);
                builder.setTitle(" Bạn muốn sửa hay xóa");
                builder.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(ListLoaiSachActivity.this, SuaLoaiSachActivity.class);
                        startActivity(intent);


                    }
                });

                builder.setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {

                        typeBookDAO.deleteLoaiSach(typeBookList.get(position_).getName());
                        typeBookList.remove(position_);
                        loaiSachAdapter.notifyDataSetChanged();


                    }
                });
                builder.show();
            }
        });
    }

    private void initView() {
        lvLoaiSach=findViewById(R.id.lvLoaiSach);
        typeBookDAO=new TypeBookDAO(this);
        typeBookList=typeBookDAO.getTypeBook();
        loaiSachAdapter=new LoaiSachAdapter(this,  typeBookList);
        lvLoaiSach.setAdapter(loaiSachAdapter);

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


            case R.id.action_ThemLoaiSach:
                Intent intent = new Intent(ListLoaiSachActivity.this, ThemLoaiSachActivity.class);
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
        typeBookList.clear();
        typeBookList=typeBookDAO.getTypeBook();
        loaiSachAdapter.changeDataset( typeBookList);
    }
}
