package com.example.cuoikianderoir;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HoaDonAdapter extends BaseAdapter {


    private ArrayList<HoaDonNgaySinh> listHoaDonNgaySinh = new ArrayList<>();

    private TextView ten;

    private TextView soPhong;

    private TextView tien;

    public IListener listener = null;

    void addData(List<HoaDonNgaySinh> list) {
        listHoaDonNgaySinh.clear();
        listHoaDonNgaySinh.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listHoaDonNgaySinh.size();
    }

    @Override
    public Object getItem(int position) {
        return listHoaDonNgaySinh.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_item, parent, false);

        anhXaView(view);

        /**
         * đổ data vào
         */
        HoaDonNgaySinh hoaDonNgaySinh = (HoaDonNgaySinh) getItem(position);

        ten.setText(hoaDonNgaySinh.getHoTen());

        soPhong.setText(String.valueOf(hoaDonNgaySinh.getSoPhong()));

        tien.setText(String.valueOf(hoaDonNgaySinh.getTien()));

        tien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onUpdate(hoaDonNgaySinh);
            }
        });

        return view;
    }


    private void anhXaView(View view) {
        ten = view.findViewById(R.id.tvItemTen);

        soPhong = view.findViewById(R.id.tvItemPhong);

        tien = view.findViewById(R.id.tvItemTien);
    }

    interface IListener{
        void onUpdate(HoaDonNgaySinh hoaDonNgaySinh);
    }
}
