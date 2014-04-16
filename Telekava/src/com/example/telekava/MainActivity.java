package com.example.telekava;

import com.example.telekava.activities.ETVActivity;
import com.example.telekava.activities.KANAL2Activity;
import com.example.telekava.activities.TV3Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ETVButton();
		KANAL2Button();
		TV3Button();
	}

	private void ETVButton() {
		Button etvButton = (Button) findViewById(R.id.ETVButton);
		etvButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(MainActivity.this,
                        ETVActivity.class);
                startActivity(myIntent);
			}
		});		
	}
	
	private void KANAL2Button() {
		Button kanal2Button = (Button) findViewById(R.id.KANAL2Button);
		kanal2Button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(MainActivity.this,
                        KANAL2Activity.class);
                startActivity(myIntent);
			}
		});		
	}
	
	private void TV3Button() {
		Button tv3Button = (Button) findViewById(R.id.TV3Button);
		tv3Button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(MainActivity.this,
                        TV3Activity.class);
                startActivity(myIntent);
			}
		});		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
	        return true;
	}
	
}

