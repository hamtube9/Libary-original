package d.lmao.libary;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import d.lmao.libary.DAO.UserDAO;
import d.lmao.libary.model.User;

public class ChangePasswordActivity extends AppCompatActivity {
    private EditText edtCFChangepass,edtChangePassword;
    private UserDAO userDAO;
    private Button btnCFChangePass;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        initView();
        btnCFChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String originalPass=edtCFChangepass.toString().trim();
                String newPass=edtChangePassword.toString().trim();
//                if(validate()>0){
                    if (!newPass.equals(originalPass)){
                        userDAO.updateUser(edtChangePassword.getText().toString());
                        Toast.makeText(ChangePasswordActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        finish();

                    }



//                }
            }
        });
    }

    private void initView() {
        edtCFChangepass=findViewById(R.id.edtCFChangepass);
        edtChangePassword=findViewById(R.id.edtChangePassword);
        btnCFChangePass=findViewById(R.id.btnCFChangePass);
        userDAO=new UserDAO(this);
    }

//    private int validate() {
//        int check = -1;
//        if (edtCFChangepass.equals("")) {
//            edtCFChangepass.setError("khong duoc de trong");
//            return check;
//        } else if (edtCFChangepass.equals(edtCFChangepass)) {
//            edtCFChangepass.setError("khong duoc trung mat khau cu");
//            return check;
//        }
//        return 1;
//    }
}
