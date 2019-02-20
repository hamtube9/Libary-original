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

import d.lmao.libary.DAO.BookDAO;

public class SuaSachActivity extends AppCompatActivity {
    private EditText edtSuaTenSach,edtSuaMaSach,edtSuaGiaSach,edtSuaSoLuong;
    private Button btnCancelSuaSach,btnSuaSach;
    private ImageView imgSuaAnhSach;
    private BookDAO bookDAO;
    public static final int PICK_IMAGE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_sach);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        initView();
        imgSuaAnhSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, PICK_IMAGE);
            }
        });

        btnCancelSuaSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtSuaTenSach.setText("");
                edtSuaMaSach.setText("");
                edtSuaGiaSach.setText("");
                edtSuaSoLuong.setText("");
            }
        });

        btnSuaSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tensach=edtSuaTenSach.getText().toString();
                String masach=edtSuaMaSach.getText().toString();
                String giasach=edtSuaGiaSach.getText().toString();
                String soluong=edtSuaSoLuong.getText().toString();
                byte[] anh=ImageViewChange(imgSuaAnhSach);

                if(validate()>0){
                    if (bookDAO.updateBook(masach,tensach,giasach,soluong,anh)>0){
                        Intent intent=new Intent(SuaSachActivity.this,ListSachActivity.class);
                        startActivity(intent);
                        Toast.makeText(SuaSachActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                }
            }
        });
    }


    private void initView() {
        edtSuaTenSach=findViewById(R.id.edtSuaTenSach);
        edtSuaMaSach=findViewById(R.id.edtSuaMaSach);
        edtSuaGiaSach=findViewById(R.id.edtSuaGiaSach);
        edtSuaSoLuong=findViewById(R.id.edtSuaSoLuong);
        btnCancelSuaSach=findViewById(R.id.btnCancelSuaSach);
        btnSuaSach=findViewById(R.id.btnSuaSach);
        imgSuaAnhSach=findViewById(R.id.imgSuaAnhSach);
        bookDAO=new BookDAO(this);
    }
    private int validate() {
        int check = -1;
        if (edtSuaTenSach.getText().toString().equals("")) {
            edtSuaTenSach.setError(getString(R.string.empty));
            return check;
        } else if (edtSuaMaSach.getText().toString().equals("")) {
            edtSuaMaSach.setError(getString(R.string.empty));
            return check;
        }  else if (Integer.parseInt(edtSuaSoLuong.getText().toString()) < 0) {
            edtSuaSoLuong.setError("So luong sach phai lon hon 0");
            return check;
        }
        else if (Integer.parseInt(edtSuaGiaSach.getText().toString()) < 0) {
            edtSuaGiaSach.setError(getString(R.string.price));
            return check;
        }
        return 1;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case PICK_IMAGE:
                if(resultCode == RESULT_OK){
                    try {
                        final Uri imageUri = data.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        imgSuaAnhSach.setImageBitmap(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
        }
    }
    private byte[] ImageViewChange(ImageView imageView) {
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
}
