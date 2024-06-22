package com.example.cekkhodam;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText inputName;
    private Button generateButton;
    private TextView resultText;

    private String[] animals;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        inputName = findViewById(R.id.inputName);
        generateButton = findViewById(R.id.generateButton);
        resultText = findViewById(R.id.resultText);

        animals = new String[]{"Cat", "Dog", "Mouse", "Tiger", "Elephant", "Lion", "Monkey", "Giraffe", "Zebra", "Rabbit"};

        random = new Random();

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = inputName.getText().toString().trim();
                String result = generateResult();
                resultText.setText(result);

            }

        });
    }

    private String generateResult() {
        int randomIndex = random.nextInt(10);
        return animals[randomIndex];
    }
}