package com.example.telekava.activities;

import com.example.telekava.MainActivity;
import com.example.telekava.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;

/**
 * TV3 vaate klass. Vajab implementeerimist.
 * @author Silver Tikk
 */
public class TV3Activity extends FragmentActivity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tv3_activity);
	}
	
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  
    {  
        if(keyCode==KeyEvent.KEYCODE_DEL)  
        {  
            this.startActivity(new Intent(TV3Activity.this, MainActivity.class)); 
        }  
        return true;  
    }  
}
