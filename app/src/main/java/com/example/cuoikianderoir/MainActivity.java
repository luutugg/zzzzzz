package com.example.cuoikianderoir;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cuoikianderoir.database.DataBase;
import com.example.cuoikianderoir.database.DatabaseHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /**
     * ánh xạ view
     */
    private EditText edtSearch;

    private ListView listView;

    private FloatingActionButton floatingActionButton;

    /**
     * adapter
     */

    private HoaDonAdapter adapter = new HoaDonAdapter();

    private ArrayList<HoaDonNgaySinh> hoaDonNgaySinhArrayList = new ArrayList<>();

    /**
     * data base
     */
    private DatabaseHandler dataBaseHelper;

    private DataBase dataBase;

    private int dem= 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* thứ 6 lên hỏi */
        setUpDatabase();

        anhXaView();

        setUpListView();

        setData();

        setEventView();

    }

    private void setUpDatabase(){
        dataBaseHelper = new DatabaseHandler(this.getApplicationContext());

        dataBase = new DataBase(dataBaseHelper);
    }

    private void setUpListView() {
        listView.setAdapter(adapter);

        adapter.listener = new HoaDonAdapter.IListener() {
            @Override
            public void onUpdate(HoaDonNgaySinh hoaDonNgaySinh) {
                HoaDonNgaySinh newHoaDon = new HoaDonNgaySinh(hoaDonNgaySinh.getHoTen(),10000,1000000);
                dataBase.updateHoaDon(newHoaDon);
            }
        };
    }

    private void anhXaView() {
        edtSearch = findViewById(R.id.edtMain);

        listView = findViewById(R.id.lvMain);

        floatingActionButton = findViewById(R.id.fabMain);
    }

    private void setData() {
        for (int i = 0; i < 20; i++) {
            hoaDonNgaySinhArrayList.add(new HoaDonNgaySinh("ten", i, 250000));
        }
    }

    private void themHoaDon(int i){
        switch (i){
            case 0:{
                dataBase.themHoaDon(new HoaDonNgaySinh("ten1",201,22));
                break;
            }
            case 1:{
                dataBase.themHoaDon(new HoaDonNgaySinh("ten2",202,100));
                break;
            }
            case 2:{
                dataBase.themHoaDon(new HoaDonNgaySinh("ten3",203,23));
                break;
            }
            case 3:{
                dataBase.themHoaDon(new HoaDonNgaySinh("ten4",204,19));
                break;
            }
            case 4:{
                dataBase.themHoaDon(new HoaDonNgaySinh("ten5",205,40));
                break;
            }
            case 5:{
                dataBase.themHoaDon(new HoaDonNgaySinh("ten6",206,30));
                break;
            }
        }
    }

    private void setEventView() {
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dem++;
//               themHoaDon(dem);
                getList();
            //    dataBase.getDanhSachHoaDon();
            }
        });

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    Double tien = Double.parseDouble(s.toString().trim());
                    getListTienLonHon(tien);
                }catch (Exception e){
                    Toast.makeText(MainActivity.this,"loi convert",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void getList(){
        List<HoaDonNgaySinh> hoaDonNgaySinhList= dataBase.getDanhSachHoaDon();
        adapter.addData(hoaDonNgaySinhList);
    }

    private void getListTienLonHon(Double tien){
        List<HoaDonNgaySinh> hoaDonNgaySinhList= dataBase.getDanhSachHoaDonSoLienLonHon(tien);
        adapter.addData(hoaDonNgaySinhList);
    }
}
