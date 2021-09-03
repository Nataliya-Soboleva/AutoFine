package com.example.autofine.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.autofine.R;
import com.example.autofine.models.Fine;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;

public class SelectedFineData extends AppCompatActivity {

    private Fine selectFine = new Fine();
    private TextView tvDateOfViolation;
    private TextView tvTypeViolation;
    private TextView tvDecreeNumber;
    private TextView tvAmount;
    private TextView tvPayment;
    private ImageView imgPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_fine_data);

        initView();

        Bundle intentData = getIntent().getExtras();

        if(intentData != null)
        {
            selectFine.setDateOfViolation(new Date(intentData.getString("dateOfViolation","")));
            selectFine.setTypeOfViolation(intentData.getString("typeOfViolation",""));
            selectFine.setDecreeNumber(intentData.getString("decreeNumber",""));
            selectFine.setAmountOfTheFine(new BigDecimal(intentData.getString("amountOfTheFine","")));
            selectFine.setPayment(intentData.getBoolean("payment",false));
            selectFine.setImage(intentData.getString("image",""));
        }
       // Log.d("AutoFineApp", "selected item Fine IMAGE ::::: "+ selectFine.getImage());

            byte[] imgByte =  selectFine.getImage().getBytes();
            setImageViewWithByteArray(imgPhoto, imgByte);

    }


    public static void setImageViewWithByteArray(ImageView view, byte[] data) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        view.setImageBitmap(bitmap);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(selectFine!=null)
        {
            fillDataToFine();
        }
    }

    private void fillDataToFine() {

        tvDateOfViolation.setText(selectFine.getDateOfViolation().toString());
        tvTypeViolation.setText(selectFine.getTypeOfViolation());
        tvDecreeNumber.setText(selectFine.getDecreeNumber());
        tvAmount.setText(selectFine.getAmountOfTheFine().toString());
       if(selectFine.getPayment().toString().equals("true"))tvPayment.setText("Оплачено");
       else tvPayment.setText("Не оплачено");

    }

    private void initView() {

        tvDateOfViolation = findViewById(R.id.tvDateOfViolation);
        tvTypeViolation = findViewById(R.id.tvTypeViolation);
        tvDecreeNumber = findViewById(R.id.tvDecreeNumber);
        tvAmount = findViewById(R.id.tvAmount);
        tvPayment = findViewById(R.id.tvPayment);
        imgPhoto = findViewById(R.id.imgPhoto);
    }

}