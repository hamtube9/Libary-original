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

import d.lmao.libary.DAO.HoaDonDAO;
import d.lmao.libary.adapter.HoaDonAdapter;
import d.lmao.libary.model.HoaDon;

public class ListHoaDonActivity extends AppCompatActivity {
    private ListView lvHoaDon;
    private HoaDonAdapter hoaDonAdapter;
    private List<HoaDon> listHoaDOn;
    private HoaDonDAO hoaDonDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hoa_don);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        initView();
        lvHoaDon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ListHoaDonActivity.this);
                builder.setTitle(" Bạn muốn sửa hay xóa");
                builder.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(ListHoaDonActivity.this, SuaHoaDonActivity.class);
                        startActivity(intent);


                    }
                });

                builder.setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {

                        hoaDonDAO.deleteHoaDOn(listHoaDOn.get(position).getId());
                        listHoaDOn.remove(position);
                        hoaDonAdapter.notifyDataSetChanged();


                    }
                });
                builder.show();
            }
        });

    }

    private void initView() {
        lvHoaDon=findViewById(R.id.lvHoaDon);
        hoaDonDAO=new HoaDonDAO(this);
        listHoaDOn=hoaDonDAO.getHoaDon();
        hoaDonAdapter=new HoaDonAdapter(this, listHoaDOn);
        lvHoaDon.setAdapter(hoaDonAdapter);



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


            case R.id.action_ThemHoaDon:
                Intent intent = new Intent(ListHoaDonActivity.this, ThemHoaDonActivity.class);
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
        listHoaDOn.clear();
        listHoaDOn=hoaDonDAO.getHoaDon();
        hoaDonAdapter.changeDataset((ArrayList<HoaDon>) listHoaDOn);
        super.onResume();
    }
}
