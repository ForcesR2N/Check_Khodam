package com.example.cekkhodam;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText inputName;
    private Button generateButton;
    private View mainLayout;

    private String[] animals;
    private Random random;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputName = findViewById(R.id.inputName);
        generateButton = findViewById(R.id.generateButton);
        mainLayout = findViewById(R.id.main);

        animals = new String[]{"Boomer Menkominfo", "Gen Z Icikiwir", "Alok", "Pedo Blue Archive", "Skibidi Toilet", "Ngabers Sigma"};

        random = new Random();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String name = inputName.getText().toString().trim();
                        String result = generateResult();
                        progressDialog.dismiss();
                        showPopupCheck(result);
                    }
                }, 1500);
            }
        });
    }

    private void showPopupCheck(String result) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.pop_check, null);

        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.MATCH_PARENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        TextView popupResultText = popupView.findViewById(R.id.resultText);
        if("no_khodam".equals(result)){
            popupResultText.setText("Skill issue, you dont even have a khodam");
        }else{
            popupResultText.setText(result);
        }

        TextView Back;
        Back=popupView.findViewById(R.id.back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        mainLayout.post(new Runnable() {
            @Override
            public void run() {
                popupWindow.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);
            }

        });
    }

    private String generateResult() {
        int randomIndex = random.nextInt(100);
        if(randomIndex < 50){
            randomIndex = random.nextInt(animals.length);
            return animals[randomIndex];
        }else {
        return "no_khodam";
        }
    }
}
