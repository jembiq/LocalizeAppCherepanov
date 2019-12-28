package com.example.localizedappcherepanov;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.LocaleList;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Spinner mLocaleSpinner;
    Button mSwitchBtn;
    private static Locale localeRU = new Locale("ru");
    private static Locale localeEN = new Locale("en");
    private static Locale localeJP = new Locale("ja");
    private static Configuration config = new Configuration();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mSwitchBtn = findViewById(R.id.switchLangBtn);
        mLocaleSpinner = findViewById(R.id.localeSpinner);
        initLocaleSpinner();
        switchBtnAction();
    }

    private void initLocaleSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this
                , R.array.language_selector
                , android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mLocaleSpinner.setAdapter(adapter);

        mLocaleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] language = getResources().getStringArray(R.array.language_selector);
                changeLanguage(language[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void changeLanguage(String language) {
        switch (language) {
            case "Русский" :
                config.setLocale(localeRU);
                break;

            case "English" :
                config.setLocale(localeEN);
                break;

            case "日本語" :
                config.setLocale(localeJP);

            default:break;
        }
    }

    private void switchBtnAction() {
        mSwitchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                recreate();
            }
        });
    }
}