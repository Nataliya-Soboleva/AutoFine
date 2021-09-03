package com.example.autofine.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.autofine.dal.DBManager;
import com.example.autofine.models.CarData;
import com.example.autofine.models.Fine;
import com.example.autofine.FineAdapter;
import com.example.autofine.R;
import com.example.autofine.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinesDataActivity extends AppCompatActivity {


    private TextView  txtCarPlateNumber;
    private TextView  txtFinesByCar;
    private TextView  txtCarDocumentNumber;
    private GridView gvCarFines;
    private Button btnDeleteCar;

    private String plateNumber;
    private static Long carDataId=0l;
    private List<Fine> allFinesByCarDataId;
    private Fine selectedFine;
    private SQLiteDatabase db;
    private static long idToSqlite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fines_data);

        Bundle intentData = getIntent().getExtras();
        if (intentData != null) {
            plateNumber = intentData.getString("carPlate", "noName");
            idToSqlite = intentData.getLong("idSqlite",0l);
        }
        readFinesByCarData(plateNumber,carDataId);

        initViews();
    }


    @Override
    protected void onResume() {
        super.onResume();

        DBManager dbManager = new DBManager(this,DBManager.appDBName,DBManager.appDBVer);

        db = dbManager.getWritableDatabase();
    }


    private synchronized void readFinesByCarData(String plateNumber, Long carDataId) {

        //получаем id из carData по номеру выбранного авто
        RetrofitInstance.getInstance()
                .getCarDataApiInterface()
                .findByPlateNumber(plateNumber)
                .enqueue(new Callback<CarData>() {
                    @Override
                    public void onResponse(Call<CarData> call, Response<CarData> response) {


                        Log.d("AutoFineApp","Response :" + response.toString());

                        if(response.isSuccessful())
                        {

                            if(response.body() != null)
                            {
                                CarData searchCarData = response.body();

                                Log.d("AutoFineApp","Get car data id :" + searchCarData.getId());
                                FinesDataActivity.carDataId = searchCarData.getId();
                                //Читаем все штрафы по данному авто
                                readFines(FinesDataActivity.carDataId);
                            }
                        }
                        else
                        {
                            Log.d("AutoFineApp","Error Getting Car data");
                        }
                    }

                    @Override
                    public void onFailure(Call<CarData> call, Throwable t) {
                        Log.d("AutoFineApp","Exception in onFailure : "+ t.getMessage());
                    }
                });
    }

    private  void readFines(Long carDataId) {

        RetrofitInstance.getInstance()
                .getFineApiInterface()
                .byId(carDataId)
                .enqueue(new Callback<List<Fine>>() {
                    @Override
                    public void onResponse(Call<List<Fine>> call, Response<List<Fine>> response) {
                        Log.d("AutoFineApp","Response :" +  response.toString());
                        if(response.isSuccessful())
                        {
                            if(response.body() != null)
                            {
                                allFinesByCarDataId = response.body();
                                Log.d("AutoFineApp","Get All fines count :" +  allFinesByCarDataId.size());
                                renderFines( allFinesByCarDataId);
                            }
                        }
                        else
                        {
                            Log.d("AutoFineApp","Error Getting All Fines by car data id ");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Fine>> call, Throwable t) {

                        Log.d("AutoFineApp","Exception in onFailure  readFines : "+ t.getMessage());
                    }
                });
    }

    private void renderFines(List<Fine> allFinesByCarDataId) {

        FineAdapter fineAdapter = new FineAdapter(this,0, allFinesByCarDataId);

        gvCarFines.setAdapter(fineAdapter);
    }

    private void initViews() {

        txtCarPlateNumber = findViewById(R.id.txtCarPlateNumber);
        txtCarDocumentNumber = findViewById(R.id.txtCarDocumentNumber);
        txtFinesByCar = findViewById(R.id.txtFinesByCar);
        btnDeleteCar = findViewById(R.id.btnDeleteCar);

        txtCarPlateNumber.setText( plateNumber);

        gvCarFines = findViewById(R.id.gvCarFines);

        gvCarFines.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Object obj = gvCarFines.getItemAtPosition(position);
                if (obj != null)
                {
                    Log.d("AutoFineApp", "selected item fine: "+ obj.toString());

                    selectedFine = (Fine) obj;
                    Log.d("AutoFineApp", "selected item Fine: "+ selectedFine.getId());

                }
               openSelectFineDataActivity();
            }
        });
        btnDeleteCar.setOnClickListener(this :: deleteCar);
    }

    private void deleteCar(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Уверены что хотите удалить  ?" );
        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                db.delete("Cars","_id =" + (int)idToSqlite,null);
                finish();
            }
        });
        builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void openSelectFineDataActivity() {

        Intent userEditorIntent = new Intent(this, SelectedFineData.class);

        userEditorIntent.putExtra("dateOfViolation",selectedFine.getDateOfViolation().toString());
        userEditorIntent.putExtra("typeOfViolation",selectedFine.getTypeOfViolation());
        userEditorIntent.putExtra("decreeNumber",selectedFine.getDecreeNumber());
        userEditorIntent.putExtra("amountOfTheFine",selectedFine.getAmountOfTheFine().toString());
        userEditorIntent.putExtra("payment",selectedFine.getPayment());
        userEditorIntent.putExtra("image",selectedFine.getImage());

        startActivity(userEditorIntent);


    }
}