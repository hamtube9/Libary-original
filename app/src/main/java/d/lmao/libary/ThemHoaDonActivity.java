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
import d.lmao.libary.model.HoaDon;

public class ThemHoaDonActivity extends AppCompatActivity {
    private EditText edtID;
    private Button btnDatePicker,btnSave;
    private long datePicker = -1;
    private HoaDonDAO hoaDonDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_hoa_don);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        initView();

        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(btnDatePicker);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ID=edtID.getText().toString().trim();

                if (ID.length()<2){
                    edtID.setError("Ma Hoa Don phai lon hon 2 ki tu");
                    return;
                }
                if (datePicker < 0){
                    edtID.setError("Hay chon ngay");
                    return;
                }

                long result =hoaDonDAO.insertHoaDon(new HoaDon(ID,datePicker));
                if (result < 0){
                    Toast.makeText(ThemHoaDonActivity.this, "Khong thanh cong", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ThemHoaDonActivity.this, "Done", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void initView() {
        edtID=findViewById(R.id.edtID);
        btnDatePicker=findViewById(R.id.btnDatePicker);
        btnSave=findViewById(R.id.btnSave);
        hoaDonDAO=new HoaDonDAO(this);



    }

    private void showDatePickerDialog(final Button btnPicker) {


        // lay thoi gian hien tai theo ngay thang nam

        final Calendar calendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendar.set(year, month, day);
                ThemHoaDonActivity.this.datePicker = calendar.getTimeInMillis();
                btnPicker.setText(new Date(calendar.getTimeInMillis()).toString());
            }


        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }
}
