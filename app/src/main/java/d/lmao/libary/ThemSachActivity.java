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
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import d.lmao.libary.DAO.BookDAO;
import d.lmao.libary.model.Book;

public class ThemSachActivity extends AppCompatActivity {
    private ImageView imgThemAnhSach;
    private EditText edtThemTenSach, edtThemMaSach, edtThemGiaSach, edtThemSoLuongSach;
    private Button btnThemSach;
    private BookDAO bookDAO;
    public static final int PICK_IMAGE = 1;
//    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_sach);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initView();



        imgThemAnhSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, PICK_IMAGE);
            }
        });

        btnThemSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tensach = edtThemTenSach.getText().toString();
                String masach = edtThemMaSach.getText().toString();
                String soluong = edtThemSoLuongSach.getText().toString();
                String gia = btnThemSach.getText().toString();


                if(validate()>0){

                    Book book = new Book(tensach,masach,gia,soluong,ImageViewChange(imgThemAnhSach));

                    if(bookDAO.insertBook(book)>0){
                        Toast.makeText(getApplicationContext(), "Add Successfull", Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        return;
                    }
                }
            }
        });


    }

    private void initView() {
        bookDAO = new BookDAO(ThemSachActivity.this);
        imgThemAnhSach = findViewById(R.id.imgThemAnhSach);


        edtThemTenSach = findViewById(R.id.edtThemTenSach);
        edtThemMaSach = findViewById(R.id.edtThemMaSach);
        edtThemGiaSach = findViewById(R.id.edtThemGiaSach);
        edtThemSoLuongSach = findViewById(R.id.edtThemSoLuongSach);
        btnThemSach = findViewById(R.id.btnThemSach);

    }

    private int validate() {
        int check = -1;
        if (edtThemTenSach.getText().toString().equals("")) {
            edtThemTenSach.setError(getString(R.string.empty));
            return check;
        } else if (edtThemMaSach.getText().toString().equals("")) {
            edtThemMaSach.setError(getString(R.string.empty));
            return check;

        } else if (Integer.parseInt(edtThemGiaSach.getText().toString()) < 0) {
            edtThemGiaSach.setError(getString(R.string.price));
            return check;
        } else if (Integer.parseInt(edtThemSoLuongSach.getText().toString()) < 0) {
            edtThemSoLuongSach.setError(getString(R.string.price));
            return check;
        }
        return 1;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PICK_IMAGE:
                if (resultCode == RESULT_OK) {
                    try {
                        final Uri imageUri = data.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        imgThemAnhSach.setImageBitmap(selectedImage);
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
