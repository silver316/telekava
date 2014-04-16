package com.example.telekava.activities;

import com.example.telekava.MainActivity;
import com.example.telekava.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;

public class KANAL2Activity extends FragmentActivity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kanal2_activity);
	}
	
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  
    {  
        if(keyCode==KeyEvent.KEYCODE_DEL)  
        {  
            this.startActivity(new Intent(KANAL2Activity.this, MainActivity.class)); 
        }  
        return true;  
    }  
}
