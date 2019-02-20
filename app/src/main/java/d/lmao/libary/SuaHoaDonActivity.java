package d.lmao.libary;

import android.app.DatePickerDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import d.lmao.libary.DAO.HoaDonDAO;
import d.lmao.libary.DAO.TypeBookDAO;
import d.lmao.libary.model.HoaDon;

public class SuaHoaDonActivity extends AppCompatActivity {
    private HoaDonDAO hoaDonDAO;
    private EditText edtIDHoaDon;
    private long datePicker = -1;
    private Button btnSuaHD,btnChangeDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_hoa_don);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        initView();
        btnChangeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(btnChangeDate);
            }
        });
        btnSuaHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ID=edtIDHoaDon.getText().toString().trim();

                if (ID.length()<2){
                    edtIDHoaDon.setError("Ma Hoa Don phai lon hon 2 ki tu");
                    return;
                }
                if (datePicker < 0){
                    Toast.makeText(SuaHoaDonActivity.this, "Hay chon ngay", Toast.LENGTH_SHORT).show();
                    return;
                }

                long result =hoaDonDAO.updateHoaDon(new HoaDon(ID,datePicker));
                if (result < 0){
                    Toast.makeText(SuaHoaDonActivity.this, "Khong thanh cong", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(SuaHoaDonActivity.this, "Done", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void initView() {
        btnChangeDate = findViewById(R.id.btnChangeDate);
        edtIDHoaDon = findViewById(R.id.edtIDHoaDOn);


        btnSuaHD =  findViewById(R.id.btnSuaHD);
        hoaDonDAO = new HoaDonDAO(this);

    }

    private int validate(){

        if (edtIDHoaDon.getText().toString().equals("")) {
            edtIDHoaDon.setError(getString(R.string.empty));
            return -1;
        }
        return 1 ;
    }

    private void showDatePickerDialog(final Button btnPicker) {


        // lay thoi gian hien tai theo ngay thang nam

        final Calendar calendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendar.set(year, month, day);
                SuaHoaDonActivity.this.datePicker = calendar.getTimeInMillis();
                btnPicker.setText(new Date(calendar.getTimeInMillis()).toString());
            }


        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }
}
