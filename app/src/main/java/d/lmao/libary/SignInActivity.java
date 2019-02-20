package d.lmao.libary;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.Nullable;
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

import d.lmao.libary.DAO.UserDAO;
import d.lmao.libary.model.User;

public class SignInActivity extends AppCompatActivity {
    private ImageView imgBackSignin, imgUserSignIn;
    private EditText edtIDSignin, edtPasswordSignin, edtPhoneSignin, edtNameSignIn;
    private Button btnComfirmSignIn;
    private UserDAO userDAO;
    public static final int PICK_IMAGE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        initView();

        imgUserSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, PICK_IMAGE);
            }
        });
        btnComfirmSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id=edtIDSignin.getText().toString();
                String pass=edtPasswordSignin.getText().toString();
                String name=edtNameSignIn.getText().toString();
                String phone= edtPhoneSignin.getText().toString();
                if (validate() > 0) {
                    User user = new User(id,pass,name,phone,ImageViewChange(imgUserSignIn));
                    if (userDAO.insertUser(user) > 0) {
                        Toast.makeText(SignInActivity.this, "Done", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SignInActivity.this, "False", Toast.LENGTH_SHORT).show();

                }

                Intent intent = new Intent(SignInActivity.this, LogInActivity.class);
                startActivity(intent);
            }
        });


    }

    private void initView() {
        imgUserSignIn = findViewById(R.id.imgUserSignIn);
        userDAO = new UserDAO(SignInActivity.this);
        edtPhoneSignin = findViewById(R.id.edtPhoneSignin);
        edtNameSignIn = findViewById(R.id.edtNameSignIn);
        edtIDSignin = findViewById(R.id.edtIDSignin);
        edtPasswordSignin = findViewById(R.id.edtPasswordSignin);
//        edtRePasswordSignin = findViewById(R.id.edtRePasswordSignin);
        btnComfirmSignIn = findViewById(R.id.btnComfirmSignIn);

    }

    private int validate() {
        String user_name = edtIDSignin.getText().toString();
        String pass = edtPasswordSignin.getText().toString();
//        String cfpass = edtRePasswordSignin.getText().toString();
        String name = edtNameSignIn.getText().toString();
        String phone = edtPhoneSignin.getText().toString();

        if (user_name.equals("")) {
            edtIDSignin.setError("Không được để trống");
            return -1;
        } else if (pass.equals("")) {
            edtPasswordSignin.setError("Không được để trống");
            return -1;
        } else if (pass.length() < 6) {
            edtPasswordSignin.setError("Độ dài lớn hơn 6 ký tự");
            return -1;
        }
//        else if (cfpass.equals("")) {
//            edtRePasswordSignin.setError("Không được để trống");
//            return -1;
//        } else if (!cfpass.equals(pass)) {
//            edtRePasswordSignin.setError("Không khớp");
//            return -1;
//        }
        else if (name.equals("")) {
            edtNameSignIn.setError("Không được để trống");
            return -1;
        } else if (phone.equals("")) {
            edtPhoneSignin.setError("Không được để trống");
        } else if (phone.length() < 9 && phone.length() > 11) {
            edtPhoneSignin.setError("Số điện thoại bắt buộc phải 10 số");
            return -1;
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
                        imgUserSignIn.setImageBitmap(selectedImage);
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
