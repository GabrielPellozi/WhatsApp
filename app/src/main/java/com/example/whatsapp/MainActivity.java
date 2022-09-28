package com.example.whatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity
{
    private TextInputEditText inputTextNumber;
    private TextInputEditText inputTextMessage;

    private Button buttonSend;

    private String GetURL(String number, String message)
    {
        message = message.replace(" ", "%20");

        return "https://wa.me/" + number + "?text=" + message;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputTextNumber = findViewById(R.id.input_text_fone);
        inputTextMessage = findViewById(R.id.input_text_mensagem);
        buttonSend = findViewById(R.id.button_add_number);

        View.OnClickListener listener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                requestSendMessage();
            }
        };

        buttonSend.setOnClickListener(listener);
    }

    private void requestSendMessage()
    {
        String message = inputTextMessage.getText().toString();

        if(message.isEmpty())
        {
            inputTextMessage.setError("Please Insert a Message");
            return;
        }

        String number = inputTextNumber.getText().toString();

        if(number.isEmpty())
        {
            inputTextNumber.setError("Please Insert a Phone Number");
            return;
        }

        sendMessage(number, message);
    }

    private void sendMessage(String number, String message)
    {
        String url = GetURL(number, message);

        Uri webpage = Uri.parse(url);
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(webIntent);
    }
}