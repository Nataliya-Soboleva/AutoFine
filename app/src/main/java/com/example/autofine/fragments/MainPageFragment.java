package com.example.autofine.fragments;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.autofine.R;
import com.example.autofine.activity.CheckFinesActivity;
import com.example.autofine.dal.DBManager;


public class MainPageFragment extends Fragment implements View.OnClickListener {

private TextView txtAgreement;
private TextView txtAgreementInfo;
private EditText etxtCarPlate;
private EditText etxtSeriesAndDocumentNumber;
private Button btnCheckFines;
private   Cursor carReadCursor;

    private SQLiteDatabase db;

    private SharedPreferences appPrefs;
    private final String PREF_FILE_NAME ="app_prefs";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();

        DBManager dbManager = new DBManager(getContext(),DBManager.appDBName,DBManager.appDBVer);

        db = dbManager.getWritableDatabase();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        db.close();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentUI = inflater.inflate(R.layout.fragment_main_page, container,false);

        appPrefs =getContext().getSharedPreferences(PREF_FILE_NAME,getContext().MODE_PRIVATE);//работа с внешним файлом

        initViews(fragmentUI);

        return fragmentUI;
    }



    private void initViews(View fragmentUI) {

        txtAgreement = fragmentUI.findViewById(R.id.txtAgreement);
        txtAgreementInfo = fragmentUI.findViewById(R.id.txtAgreementInfo);
        etxtCarPlate = fragmentUI.findViewById(R.id.etxtCarPlate);
        etxtSeriesAndDocumentNumber = fragmentUI.findViewById(R.id.etxtSeriesAndDocumentNumber);
        btnCheckFines = fragmentUI.findViewById(R.id.btnCheckFines);

        txtAgreement.setOnClickListener(this);
        btnCheckFines.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.txtAgreement:
            {
                if(txtAgreementInfo.getText().equals(""))
                {
                    txtAgreementInfo.setText(Html.fromHtml("<h3>Условия пользования\n" +
                            "Программным обеспечением Штрафы ПДД</h3>\n" +
                            "\n" +
                            "<p> <br>1. Эти Условия пользования Программным обеспечением «Штрафы ПДД» (далее - Программное обеспечение) является договором между любым лицом (далее - «Пользователь»), который устанавливает Программное обеспечение, получает к нему доступ или использует его любым другим образом и государственных учреждений «ЦЕНТР ИНФРАСТРУКТУРЫ и ТЕХНОЛОГИЙ МИНИСТЕРСТВА ВНУТРЕННИХ ДЕЛ УКРАИНЫ» (далее - ГУ ЦИТ МВД УКРАИНЫ), которому принадлежат все имущественные права интеллектуальной собственности на Программное обеспечение и оно имеет исключительное право разрешать или запрещать использование программного обеспечения (далее - Договор).\n" +
                            "\n" +
                            "<br><br>2. Программное обеспечение предназначено для уплаты Пользователем средств за административные штрафы в сфере обеспечения безопасности дорожного движения в соответствии с нормами действующего в Украине законодательства.\n" +
                            "\n" +
                            "<br><br>3. Под программным обеспечением в настоящем Договоре понимается набор инструкций в виде слов, цифр, кодов, схем, символов или в любом другом виде, выраженных в форме, пригодной для считывания компьютером (смартфоном или другим устройством, предназначен для таких целей) , которые приводят его в действие для достижения определенной цели или результата. Программное обеспечение для целей настоящего Договора представлено в виде мобильного приложения и Telegram-бота Штрафы ПДД.\n" +
                            "\n" +
                            "<br><br>4. Соответствии с настоящим Договором, ГУ ЦИТ МВД УКРАИНЫ предоставляет Пользователю неисключительное право на использование программного обеспечения (далее - Права), а именно: право использования одного экземпляра программного обеспечения по его функциональному назначению, без права изготовления копий, внесение изменений (модификаций) и исправления ошибок в Программном обеспечении, без права декомпилировать (за исключением случаев, предусмотренных действующим законодательством Украины), или дизассемблировать Программное обеспечение.\n" +
                            "\n" +
                            "<br><br>5. Любое нарушение Пользователем условий настоящего Договора означает автоматическое и немедленное прекращение действия настоящего Договора и может повлечь за собой уголовную, административную и / или гражданскую ответственность.\n" +
                            "\n" +
                            "<br><br>6. По настоящему Договору Пользователю предоставляются Права для собственного некоммерческого использования.\n" +
                            "\n" +
                            "<br><br>7. Территория использования программного обеспечения ограничено.\n" +
                            "\n" +
                            "<br><br>8. Срок действия Прав начинается с момента скачивания (копирование, установки) программного обеспечения на компьютер, мобильный телефон или другое устройство подходит для его считывания, и является бессрочным.\n" +
                            "\n" +
                            "<br><br>9. В соответствии с условиями настоящего Договора, ГУ ЦИТ МВД УКРАИНЫ передает Пользователю лишь право на использование программного обеспечения, при этом такая передача не является передачей ни во владение, ни в распоряжение, ни в собственность.\n" +
                            "\n" +
                            "<br><br>10. Пользователь является владельцем данных, создаваемых (накапливаются) с помощью программного обеспечения.\n" +
                            "\n" +
                            "<br><br>11. Пользователь, на момент согласования настоящего Договора, как субъект персональных данных, во исполнение требований Закона Украины «О защите персональных данных» дает свое согласие на включение его персональных данных в базу персональных данных ГУ ЦИТ МВД УКРАИНЫ. Целью обработки добровольно предоставленных Пользователем персональных данных является обеспечение реализации уплаты им средств за административные штрафы в сфере обеспечения безопасности дорожного движения. Для достижения цели обработки в базу персональных данных Заказчика, могут быть включены следующие персонала\n" +
                            "ни данные пользователя Номер и серия постановления Регион совершения нарушения, Фамилия Имя Отчество, идентификационный код (регистрационный номер учетной карточки налогоплательщика), серия и номер паспорта, номер ID карты водителя, серия и номер свидетельства о регистрации ТС, государственный номер ТС, адрес электронной почты.\n" +
                            "\n" +
                            "<br><br>12. Любые персональные данные, передаваемые могут передаваться по настоящему Договору, составят конфиденциальную информацию, не подлежащую разглашению / передачи в любом виде, кроме случаев, прямо предусмотренных законодательством Украины и условиями настоящего Договора. Обо всех случаях разглашения / передачи персональных данных по настоящему Договору, Стороны незамедлительно информируют друг друга в письменном виде.\n" +
                            "\n" +
                            "<br><br>13. Программное обеспечение может использовать компоненты, разработанные третьими лицами и / или защищенные авторскими правами третьих лиц." +
                            "\n" +
                            "<br>1<br>4. Программное обеспечение передается Пользователю в состоянии «как есть» и может содержать в своем составе ошибки.\n" +
                            "В случае не согласия с условиями настоящего Договора, считается, что он не был заключен, и Пользователь не имеет права использовать Программное обеспечение, имеет деинсталлировать и уничтожить все загруженные (скопированы, установлены) его экземпляры.\n" +
                            "\n" +
                            "<br><br>15. В случае нарушения условий настоящего Договора, сторона, допустившая такое нарушение, несет ответственность, предусмотренную действующим законодательством Украины.\n" +
                            "\n" +
                            "<br><h2>ГОСУДАРСТВЕННОЕ УЧРЕЖДЕНИЕ «ЦЕНТР ИНФРАСТРУКТУРЫ И ТЕХНОЛОГИЙ МИНИСТЕРСТВА ВНУТРЕННИХ ДЕЛ УКРАИНЫ»\n</h2>" +
                            "\n" +
                            "(03151, г.. Киев, ул. Голосеевская, 47; код ЕГРПОУ 24577777)</p>" ));
                }
                else
                {
                    txtAgreementInfo.setText("");
                }
            }
            case R.id.btnCheckFines:
            {
                 saveCarToDb();

                break;
            }
        }

    }

    private void saveCarToDb()
    {

        //check entered car before insert to db
      if(!readCarFromDb() )
      {
          if(!etxtCarPlate.getText().toString().equals("") && !etxtSeriesAndDocumentNumber.getText().toString().equals(""))
          {
              savePreferences();
              //подготовим объект записи для вставки в таблицу БД
              ContentValues carValues = new ContentValues();//название поля- значение

              carValues.put("plate_number", etxtCarPlate.getText().toString());
              carValues.put("document_number", etxtSeriesAndDocumentNumber.getText().toString());


              long recordID = db.insert("Cars", null, carValues);

              if(recordID>0)
              {
                  Toast.makeText(getContext(),"Автомобиль с ID #" + recordID + "успешно добавлен" ,Toast.LENGTH_LONG).show();

              }
              startFinesActivity();
          }
          else{
              Toast.makeText(getContext(),"Заполните все поля !" ,Toast.LENGTH_LONG).show();
          }

      }
      else{
          Toast.makeText(getContext(),"Данный автомобиль уже добавлен " ,Toast.LENGTH_LONG).show();
          startFinesActivity();
      }

    }

    private boolean readCarFromDb() {

        carReadCursor =  db.rawQuery("SELECT * FROM Cars WHERE plate_number ="+"'"+etxtCarPlate.getText().toString()+"'", null);

        //Этот метод позволяет проверить есть ли в результатах выборки записи с данным номером
        if(carReadCursor.moveToFirst())
        {
           return true;
        }
        return false;
    }

    private void savePreferences() {
        //возвращает редактор , внутренний класс сохраняется в файле data->data->exe->shared_prefs->app_prefs.xml
        SharedPreferences.Editor prefsEditor  =  appPrefs.edit();

        prefsEditor.putString("plate",etxtCarPlate.getText().toString());
        prefsEditor.putString("docnum",etxtSeriesAndDocumentNumber.getText().toString());

        prefsEditor.apply();

        Toast.makeText(getContext(), "Preferences saved", Toast.LENGTH_LONG).show();

    }

    private void startFinesActivity() {

        Intent checkFinesActivityIntent = new Intent(getContext(), CheckFinesActivity.class);

        checkFinesActivityIntent.putExtra("carPlate",etxtCarPlate.getText());
        checkFinesActivityIntent.putExtra("documentNumber",etxtSeriesAndDocumentNumber.getText());

        startActivity(checkFinesActivityIntent);
    }
}