package com.example.autofine;

import android.content.Context;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.autofine.models.CarData;
import com.example.autofine.models.Fine;

import java.util.List;

public class FineAdapter extends ArrayAdapter<Fine> {

      public FineAdapter(@NonNull Context context, int resource, @NonNull List<Fine> objects) {
        super(context, resource, objects);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //что бы получить конкретный элемент, передается position(ссылка на id  элемента который рендерится , оборачивается)
        Fine fineToRender = getItem(position);

        //convertView нужен что бы выполнить привязку
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fine_item, parent, false); //от куда скачиваем разметку и куда  присобачиваем закачиваем
        }



        TextView txtPayment = convertView.findViewById(R.id.txtPayment);
        TextView txtPlateNumberAndDate = convertView.findViewById(R.id.txtPlateNumberAndDate);


        //Payment
        if(fineToRender.getPayment()) {
            txtPayment.setText("Оплачено");
            txtPayment.setTextColor(Color.GREEN);
        }
        else{
            txtPayment.setText("Не оплачено");
            txtPayment.setTextColor(Color.RED);
        }

        //PlateNumber and date fines
        CarData carData = fineToRender.getCarDataId();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd  HH:mm:ss");

        String data = sdf.format(fineToRender.getDateOfViolation()) ;
        txtPlateNumberAndDate.setText(data);

        return convertView;
    }
}
