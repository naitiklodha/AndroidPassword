package com.example.passwordgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView passviewer;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        passviewer=findViewById(R.id.textView);
        final Button generate=findViewById(R.id.button);
        final Button copy=findViewById(R.id.button2);

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password=generatePassword();
                passviewer.setText(password);
                generate.setText("Generate new password");
                copy.setVisibility(View.VISIBLE);
            }
        });
        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager)
                        getSystemService(Context.CLIPBOARD_SERVICE);

                // Creates a new text clip to put on the clipboard
                String password=passviewer.getText().toString();
                ClipData clip = ClipData.newPlainText("simple text", password);

                clipboard.setPrimaryClip(clip);

                Toast.makeText(MainActivity.this,"Password copied to clipboard",Toast.LENGTH_LONG).show();
            }
        });







    }
    private static String generatePassword() {
        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "!@#$";
        String numbers = "1234567890";
        String pass="";
        String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
        Random random = new Random();
        char[] password = new char[8];

        password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
        password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
        password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
        password[3] = numbers.charAt(random.nextInt(numbers.length()));

        for(int i = 4; i< 8; i++) {
            password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
        }
        int i;
        for( i=0;i<password.length;i++)
        {
             pass=pass+password[i];
        }

        return pass;
    }
}