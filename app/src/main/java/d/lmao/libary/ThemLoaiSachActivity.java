package d.lmao.libary;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import d.lmao.libary.DAO.TypeBookDAO;
import d.lmao.libary.model.Book;
import d.lmao.libary.model.TypeBook;

public class ThemLoaiSachActivity extends AppCompatActivity {
        private EditText edtID,edtName,edtTacGia,edtPos;
        private Button btnAddTypeBook,btnCancel;

        private TypeBookDAO typeBookDAO;
    public static final int PICK_IMAGE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_loai_sach);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        initView();

        btnAddTypeBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tensach = edtID.getText().toString().trim();
                String masach = edtName.getText().toString().trim();
                String tacgia = edtTacGia.getText().toString().trim();
                String vitri = edtPos.getText().toString().trim();

                if(validate()>0){

                    TypeBook typeBook = new TypeBook(masach,tensach,tacgia,vitri);

                    if(typeBookDAO.insertTypeBook(typeBook)>0){
                        Toast.makeText(getApplicationContext(), "Add Successfull", Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        return;
                    }
                }



            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtID.setText("");
                edtName.setText("");
                edtTacGia.setText("");
                edtPos.setText("");
            }
        });
    }

    private void initView() {
        edtID=findViewById(R.id.edtID);
        edtName=findViewById(R.id.edtNameLoaiSach);
        edtTacGia=findViewById(R.id.edtTacGia);
        edtPos=findViewById(R.id.edtPos);

        btnAddTypeBook=findViewById(R.id.btnAddTypeBook);
        btnCancel=findViewById(R.id.btnCancel);
        typeBookDAO=new TypeBookDAO(this);


    }

    private int validate() {
        int check = -1;
        if (edtID.getText().toString().equals("")) {
            edtID.setError(getString(R.string.empty));
            return check;
        } else if (edtName.getText().toString().equals("")) {
            edtName.setError(getString(R.string.empty));
            return check;

        } else if (edtTacGia.getText().toString().equals("")) {
            edtTacGia.setError(getString(R.string.empty));
            return check;
        } else if (edtPos.getText().toString().equals("") ) {
            edtPos.setError(getString(R.string.empty));
            return check;
        }
        return 1;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        }



}
