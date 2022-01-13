package com.example.tictactoeclient;

import static com.example.tictactoeclient.model.DefaultConstants.*;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnConnect, btnStart;
    EditText txtIP, txtPort;
    TextView txtResult;

    MainActivity instance;
    Button[][] arrayButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConnect = findViewById(R.id.btnConnect);
        btnStart = findViewById(R.id.btnStart);
        txtResult = findViewById(R.id.txtResult);
        txtIP = findViewById(R.id.txtIP);
        txtPort = findViewById(R.id.txtPort);

        btnStart.setEnabled(false);
        arrayButtons = new Button[3][3];

        arrayButtons[0][0] = findViewById(R.id.btn00);
        arrayButtons[0][1] = findViewById(R.id.btn01);
        arrayButtons[0][2] = findViewById(R.id.btn02);
        arrayButtons[1][0] = findViewById(R.id.btn10);
        arrayButtons[1][1] = findViewById(R.id.btn11);
        arrayButtons[1][2] = findViewById(R.id.btn12);
        arrayButtons[2][0] = findViewById(R.id.btn20);
        arrayButtons[2][1] = findViewById(R.id.btn21);
        arrayButtons[2][2] = findViewById(R.id.btn22);

        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                arrayButtons[i][j].setEnabled(false);
            }
        }

        instance = this;

        btnConnect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String ip = txtIP.getText().toString();
                int port = Integer.valueOf(txtPort.getText().toString());

                if(!ip.equals("") && port!=0){
                    ThreadConnection conn = new ThreadConnection(ip, port, instance);
                    conn.execute();
                }else{
                    Toast.makeText(getApplicationContext(), "Ip o port", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void updateUI(byte header){
        switch (header){
            case CONNECTION_OK:
                txtResult.setText("CONNETED OK");
                btnStart.setEnabled(true);
                break;
            case CONNECTION_KO:
                txtResult.setText("CONNECTED KO");
                break;
        }
    }
}