package com.example.autofine.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.autofine.models.CarData;
import com.example.autofine.models.Fine;
import com.example.autofine.R;
import com.example.autofine.RetrofitInstance;
import com.example.autofine.dal.DBManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckFinesActivity extends AppCompatActivity  implements View.OnClickListener{

    //объявляем БД с кот будем работать
    private SQLiteDatabase db;
    private Cursor carsSelectCursor;


    private TextView txtMyCar;
    private GridView gvMyCars;


    private CarData selectedItemCarData;
    private long carID = 0;
    private String plateNumber="";
    private String docNumber="";
    private static List<Fine> allFinesBySelectCar ;
    private static Long carDataId = 0l;
    private static long idToSqlite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_fines);

        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();

        initDB();

        readCarsFromDB();
    }

    //закрываем БД
    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    //SQLite для загрузки введенных ранее авто для просмотра штрафов
    private void initDB() {

        DBManager dbManager = new DBManager(this,DBManager.appDBName,DBManager.appDBVer);
        //В этот момент создается база
        db = dbManager.getWritableDatabase();

    }
    private void readCarsFromDB() {

        //курсор- спецобъект , открывающий доступ к результатам работы SELECT
        //он выступает в роли итератора и позволяет перебрать все записи

        carsSelectCursor = db.rawQuery("SELECT * FROM Cars ORDER BY _id DESC", null);

        if(carsSelectCursor==null)
        {
            Toast.makeText(this, "У Вас нет ни одного авторизованого автомобиля !!!", Toast.LENGTH_LONG).show();
        }

        //Массив для указания того какие колонки таблицы БД мы берем из курсора

        String[] dbColumns ={
                "plate_number",
                "document_number"
        };

        //маппинг того к каким контролам внутри car_item.xml
        //сопоставлять колонкию Маппинг происходит в порядке следования пар - колонка - id элемента UI

        int[] viewsToColumnsMapping ={
                R.id.txtPlateNumber,
                R.id.txtDocumentNumber
        };


        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(
                this,
                R.layout.car_item,
                carsSelectCursor,
                dbColumns,
                viewsToColumnsMapping,
                0
        );
        gvMyCars.setAdapter(cursorAdapter);
    }

    private void initViews() {

        txtMyCar = findViewById(R.id.txtMyCar);
        gvMyCars = findViewById(R.id.gvMyCars);


        gvMyCars.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                idToSqlite = id;

                if (carsSelectCursor.moveToPosition(position))
                {
                    plateNumber =  carsSelectCursor.getString(1);
                    docNumber =  carsSelectCursor.getString(2);

                    Log.d("AutoFineApp", "selected item Car plate number: "+ plateNumber);
                    Log.d("AutoFineApp", "selected item Car document number: "+ docNumber);

                    startActivityFinesData();
                }
            }
        });
    }



    private void startActivityFinesData() {

        Intent startFinesDataActivity = new Intent( this, FinesDataActivity.class);

        startFinesDataActivity.putExtra("carPlate",plateNumber);
        startFinesDataActivity.putExtra("documentNumber",docNumber);
        startFinesDataActivity.putExtra("idSqlite",idToSqlite);

        startActivity(startFinesDataActivity);

    }

    @Override
    public void onClick(View v) {
    }
}