package com.example.autofine.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.net.MailTo;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.autofine.R;


public class SupportFragment extends Fragment {

private TextView txtSupportInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View fragmentUI = inflater.inflate(R.layout.fragment_support,container,false);

        txtSupportInfo = fragmentUI.findViewById(R.id.txtSupportInfo);

        txtSupportInfo.setText(Html.fromHtml("<h1>Техническая поддержка</h1><br>" +
                "<p>Ждем Ваших обращений по следующим контактам : <br><br>" +
                "E-mail адресс : </p>"));



        return fragmentUI;
    }
}