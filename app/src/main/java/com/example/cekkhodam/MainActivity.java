package com.example.cekkhodam;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText inputName;
    private View mainLayout;
    private TextView titleTextView, subtitleTextView;
    private ImageView imageView;
    private Button generateButton;

    private String[] khodam;
    private Random random;
    private ProgressDialog progressDialog;
    private HashMap<String, String> resultMap;
    private Animation fadeInAnimation, slideInAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleTextView = findViewById(R.id.titleTextView);
        subtitleTextView = findViewById(R.id.subtitleTextView);
        imageView = findViewById(R.id.imageView);
        generateButton = findViewById(R.id.generateButton);
        inputName = findViewById(R.id.inputName);
        mainLayout = findViewById(R.id.main);

        fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        slideInAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_in_bottom);

        titleTextView.startAnimation(fadeInAnimation);
        subtitleTextView.startAnimation(fadeInAnimation);
        imageView.startAnimation(slideInAnimation);
        generateButton.startAnimation(slideInAnimation);

        khodam = new String[]{"Boomer Menkominfo", "Gen Z Icikiwir", "Alok", "Pedo Blue Archive", "Skibidi Toilet", "Ngabers Sigma"};

        random = new Random();
        resultMap = new HashMap<>();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = inputName.getText().toString().trim();
                if (name.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter your name", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressDialog.show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String result;
                        if(resultMap.containsKey(name)) {
                            result = resultMap.get(name);
                        } else {
                            result = generateResult();
                            resultMap.put(name, result);
                        }
                        progressDialog.dismiss();
                        showPopupCheck(name, result);
                    }
                }, 1500);
            }
        });
    }

    private void showPopupCheck(String name, String result) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.pop_check, null);

        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.MATCH_PARENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        TextView nameText = popupView.findViewById(R.id.nameText);
        nameText.setText(name + ", your khodam is...");

        TextView popupResultText = popupView.findViewById(R.id.resultText);
        if ("no_khodam".equals(result)) {
            popupResultText.setText("Skill issue, you don't even have a khodam");
        } else {
            popupResultText.setText(result);
        }

        TextView Back = popupView.findViewById(R.id.back);
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
        if (randomIndex < 50) {
            randomIndex = random.nextInt(khodam.length);
            return khodam[randomIndex];
        } else {
            return "no_khodam";
        }
    }
}
