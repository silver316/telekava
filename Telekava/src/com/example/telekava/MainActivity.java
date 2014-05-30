package com.example.telekava;

import com.example.telekava.activities.ETVActivity;
import com.example.telekava.activities.KANAL2Activity;
import com.example.telekava.activities.TV3Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Peavaate klass.
 * @author Silver Tikk
 */
public class MainActivity extends Activity {
	
	/**
	 * M‰‰rab peavaate kujunduse, kuhu lisatakse 
	 * ETV, KANAL2 ja TV3 kanalite valimisnupud.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//M‰‰rab peavaate kujunduseks activity_main.xml failis kirjeldatud kujunduse
		setContentView(R.layout.activity_main);
		
		ETVButton();
		KANAL2Button();
		TV3Button();
	}
	
	/**
	 * Leiab ETV nupu activity_main.xml failist.
	 * Antud nupul vajutades luuakse uus sınum, mida hakatakse jagama 
	 * MainActivity ja ETVActivity klasside vahel.
	 */
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
	
	/**
	 * Leiab KANAL2 nupu activity_main.xml failist.
	 * Antud nupul vajutades luuakse uus sınum, mida hakatakse jagama 
	 * MainActivity ja KANAL2Activity klasside vahel.
	 */
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
	
	/**
	 * Leiab TV3 nupu activity_main.xml failist.
	 * Antud nupul vajutades luuakse uus sınum, mida hakatakse jagama 
	 * MainActivity ja TV3Activity klasside vahel.
	 */
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
}

