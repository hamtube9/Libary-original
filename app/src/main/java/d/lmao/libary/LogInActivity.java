package d.lmao.libary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import d.lmao.libary.DAO.UserDAO;
import d.lmao.libary.model.User;

public class LogInActivity extends AppCompatActivity {
    private EditText edtIDlogin,edtPassLogin;
    private Button btnLogin, btnSignin;
    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        initView();

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LogInActivity.this,SignInActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ID=edtIDlogin.getText().toString().trim();
                User username=userDAO.getUser(ID);
                String Pass=edtPassLogin.getText().toString().trim();
                User user = userDAO.getUser(Pass);



                if (ID.equals("")){
                    edtIDlogin.setError("ID cant not Empty");
                }
                else if (Pass.equals("")){
                    edtPassLogin.setError("Pass cant not Empty");
                }
                else{
                    String originalPass = username.getPass();
                    String original =username.getUsername();
                    if (original.equals(username.getUsername()) && originalPass.equals(username.getPass()) ){
                        Intent intent=new Intent(LogInActivity.this,MainActivity.class);
                        startActivity(intent);
                    }else{
                        return;
                    }
                }




            }
        });
    }

    private void initView() {
        userDAO=new UserDAO(LogInActivity.this);

        edtIDlogin=findViewById(R.id.edtIDLogin);
        edtPassLogin=findViewById(R.id.edtPasswordLogin);
        btnLogin=findViewById(R.id.btnLogin);
        btnSignin=findViewById(R.id.btnSingin);
    }
}

