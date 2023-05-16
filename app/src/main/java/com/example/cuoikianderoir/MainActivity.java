package com.example.cuoikianderoir;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cuoikianderoir.database.DataBase;
import com.example.cuoikianderoir.database.DatabaseHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText edtSearch;

    private ListView listView;

    private FloatingActionButton floatingActionButton;

    private HoaDonAdapter adapter = new HoaDonAdapter();

    private ArrayList<HoaDonNgaySinh> hoaDonNgaySinhArrayList = new ArrayList<>();


    private DatabaseHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new DatabaseHandler(this.getApplicationContext());


        anhXaView();

        setUpListView();

        setData();

        setEventView();

    }

    private void setUpListView() {
        listView.setAdapter(adapter);
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

    private void setEventView() {
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.themHoaDon(new HoaDonNgaySinh(1,"ten", 100, 250000));
                List<HoaDonNgaySinh> list = handler.getDanhSachHoaDon();
                adapter.addData(list);
            }
        });

        edtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<HoaDonNgaySinh> list = handler.getDanhSachHoaDon();
                adapter.addData(list);
            }
        });
    }
}
