package com.example.fgmobileapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import com.example.fgmobileapp.databinding.ActivityMainBinding;
import com.example.fgmobileapp.viewmodel.FGViewModel;

public class MainActivity extends Activity {

    //TextView _view;
    FGViewModel viewModel;
    Button btnConnect;
    SeekBar seekBarHorizontal;
    SeekBar seekBarVertical;
    TextView txtPositions;
    float slider_Y = -1;
    float slider_X = 0;
    float dAileron = 0;
    float dElevator = 0;
    JoyStickComponent joystick;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(new FGViewModel());
        binding.executePendingBindings();
        viewModel = new FGViewModel();
    }

    public void Connect(View view) throws Exception {
        EditText _ip = findViewById(R.id.ipAddr);
        EditText _port = findViewById(R.id.port);
        String ip = _ip.getText().toString();
        int port =  Integer.parseInt(_port.getText().toString());
        //viewModel.Connect(ip.getText().toString(),Integer.parseInt(port.getText().toString()));
        viewModel.Connect(ip,port);
    }
}
