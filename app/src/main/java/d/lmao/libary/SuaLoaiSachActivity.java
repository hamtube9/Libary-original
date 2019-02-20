package d.lmao.libary;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import d.lmao.libary.DAO.TypeBookDAO;

public class SuaLoaiSachActivity extends AppCompatActivity {
    private EditText edtSuaIDLS,edtSuaNameLoaiSach,edtSuaTacGiaLS,edtSuaPosLS;
    private Button btnSuaTypeBook;
    private TypeBookDAO typeBookDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_loai_sach);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        initView();

        btnSuaTypeBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=edtSuaIDLS.getText().toString();
                String tenLS=edtSuaNameLoaiSach.getText().toString();
                String tacgia=edtSuaTacGiaLS.getText().toString();
                String vitri=edtSuaPosLS.getText().toString();

                typeBookDAO.updateTypeBook(id,tenLS,tacgia,vitri);
                Intent intent=new Intent(SuaLoaiSachActivity.this,ListLoaiSachActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        edtSuaIDLS=findViewById(R.id.edtSuaIDLS);
        edtSuaNameLoaiSach=findViewById(R.id.edtSuaNameLoaiSach);
        edtSuaTacGiaLS=findViewById(R.id.edtSuaTacGiaLS);
        edtSuaPosLS=findViewById(R.id.edtSuaPosLS);
        btnSuaTypeBook=findViewById(R.id.btnSuaTypeBook);
        typeBookDAO=new TypeBookDAO(this);
    }
}
