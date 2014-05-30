package com.example.telekava.activities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.example.telekava.MainActivity;
import com.example.telekava.R;
import com.example.telekava.activities.ETVActivity;
import com.example.telekava.fragments.FifthDay;
import com.example.telekava.fragments.FirstDay;
import com.example.telekava.fragments.FourthDay;
import com.example.telekava.fragments.SecondDay;
import com.example.telekava.fragments.SeventhDay;
import com.example.telekava.fragments.SixthDay;
import com.example.telekava.fragments.ThirdDay;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.TextView;

/**
 * ETV vaate klass.
 * @author Silver Tikk
 */
@SuppressLint("SimpleDateFormat")
public class ETVActivity extends FragmentActivity {
	//Aadress, kust loetakse n‰dalakava andmed
	String url = "http://www.kavad.ee/telekavad&view=2&ch=1";
	//Kava laadimise dialoog
	ProgressDialog progressDialog;
	//Listid vastavalt p‰evade, aegade ja saadete hoidmiseks
	ArrayList<String> daylist =  new ArrayList<String>();
	ArrayList<String> timelist =  new ArrayList<String>();
	ArrayList<String> programlist =  new ArrayList<String>();
	//Andmete kandmiseks vaadete vahel
	Adapter adapter;
	//Vaadete nihutamiseks	 
	ViewPager viewPager = null;

	/**
	 * Annab ETV vaatele kujunduse ja loob suhtluse fragmentidega.
	 */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.etvtitle);
        //M‰‰rab ETV vaate kujunduseks etv_activity.xml failis kirjeldatud kujunduse
        setContentView(R.layout.etv_activity);
              
        new Content().execute();
        
        viewPager = (ViewPager) findViewById(R.id.pager);
        //Tagastab FragmentManager objekti, mis on vajalik fragmentidega suhtlemiseks
        FragmentManager fragmentManager = getSupportFragmentManager();
        adapter = new Adapter(fragmentManager);
        viewPager.setAdapter(adapter);
        //M‰‰rab vaadete arvu, mis peaks j‰‰ma kummalegi poole antud vaatest
        //N‰dalakavas on 7 p‰eva ja igale p‰evale eraldi vaade, seetıttu v‰‰rtus 7
        viewPager.setOffscreenPageLimit(7);

    }
    
    /**
     * Kuvab peavaate Delete vıi Backspace nupu (Android seadmel) vajutamisel.
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  
    {  
        if(keyCode==KeyEvent.KEYCODE_DEL || keyCode==KeyEvent.KEYCODE_BACK)  
        {  
            this.startActivity(new Intent(ETVActivity.this, MainActivity.class)); 
        }  
        return true;  
    }  
    
    /**
     * Haldab vaadete sisu kasutades selleks AsyncTask abiklassi, mis vıimaldab
     * tagataustal kasutada operatsioone, ilma et oleks vaja manipuleerida thread 
     * vıi handler objekte.
     */
    private class Content extends AsyncTask<Void, Void, Void> {
		
		private int day;        		
		private int current_index;
		        		
		private int day_one;
		private int day_two;
		private int day_three;
		private int day_four;
		private int day_five;
		private int day_six;
		private int day_seven;
		
		private String day_one_content = "";
		private String day_two_content = "";
		private String day_three_content = "";
		private String day_four_content = "";
		private String day_five_content = "";
		private String day_six_content = "";
		private String day_seven_content = "";

		/**
		 * N‰itab progressi dialoogi enne sisu kuvamist.
		 */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(ETVActivity.this);
            progressDialog.setTitle("ETV kava");
            progressDialog.setMessage("Laadimine...");
            //Keelab indeterminate kasutamise, progressi indikaator ei liigu vasakult paremale ega tagasi
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }
 
        /**
         * Sooritab vajalikud tegevused, et oleks vıimalik sisu kuvada.
         */
        @Override
        protected Void doInBackground(Void... params) {
 
            try {
            	//Vıtab ¸hendust veebilehega ja laeb sealt dokumendi
            	Document document = Jsoup.connect(url).get();
            	
            	//Selekteerib dokumendist kıik elemendid, mis sisaldavad p‰eva nimetust, ja lisab need vastavasse listi
            	Elements days = document.select("td[class=rek2]");
            	for(Element day : days) {
                	daylist.add(day.text() + "\n\n");
                } 
            	
            	//Aegade formaatimiseks, tunnid 0-23 ja minutid 0-59
            	SimpleDateFormat parser = new SimpleDateFormat("HH:mm");  
            	//Eelmine aeg ei ole salvestatud
            	Boolean previous = false;                		
        		Date previous_time = null; 
        		
        		//Selekteerib dokumendist kıik elemendid, mis sisaldavad aega, ja lisab need vastavasse listi
        		Elements times = document.select("td[class=vaade2]");    	            	            	
                for(Element time : times) {  
                	try {                		             		               		
                		Date current_time = parser.parse(time.text());   
                		//Kui eelmine aeg ei ole salvestatud, siis m‰‰ratakse eelmiseks ajaks hetke aeg
                		if(!previous) {
                			previous = true;
                			previous_time = current_time;           			
                		} else {
                			//Kui eelmine aeg on salvestatud ja hetke aeg on v‰iksem kui eelmine aeg, siis minnakse ¸le j‰rgmisele p‰evale
	                		if(current_time.before(previous_time)) {
	                			switch(day) {
	                				case 0: day_one = current_index;	                						
	                						break;
	                				case 1: day_two = current_index;
	                						break;
	                				case 2: day_three = current_index;
	                						break;
	                				case 3: day_four = current_index;
	                						break;
	                				case 4: day_five = current_index;
	                						break;
	                				case 5: day_six = current_index;
	                						break;
	                				case 6: day_seven = current_index;
	                						break;              				
	                			}  
	                			day++;
	                		}
	                		previous_time = current_time;
                		}
                		current_index++;
                		timelist.add(time.text() + " ");  
                	} catch (java.text.ParseException e) {
                		e.printStackTrace();
					}  
                }
                
                //Selekteerib dokumendist kıik elemendid, mis sisaldavad saate nimetust, ja lisab need vastavasse listi
                Elements programs = document.select("a[class=w]");               
                for(Element program : programs) {
                	programlist.add(program.text() + "\n\n");
                } 
                
                //Selekteerib kıik esimese p‰eva saated ja nende ajad ning v‰‰rtustab tulemuse vastavasse muutujasse
                for(int index = 0; index < day_one; index++){
                	day_one_content += (timelist.get(index) + programlist.get(index));
                }
                
                //Selekteerib kıik teise p‰eva saated ja nende ajad ning v‰‰rtustab tulemuse vastavasse muutujasse
                for(int index = day_one; index < day_two; index++){
                	day_two_content += (timelist.get(index) + programlist.get(index));
                }
                
                //Selekteerib kıik kolmanda p‰eva saated ja nende ajad ning v‰‰rtustab tulemuse vastavasse muutujasse
                for(int index = day_two; index < day_three; index++){
                	day_three_content += (timelist.get(index) + programlist.get(index));
                }
                
                //Selekteerib kıik neljanda p‰eva saated ja nende ajad ning v‰‰rtustab tulemuse vastavasse muutujasse
                for(int index = day_three; index < day_four; index++){
                	day_four_content += (timelist.get(index) + programlist.get(index));
                }
                
                //Selekteerib kıik viienda p‰eva saated ja nende ajad ning v‰‰rtustab tulemuse vastavasse muutujasse
                for(int index = day_four; index < day_five; index++){
                	day_five_content += (timelist.get(index) + programlist.get(index));
                }
               
                //Selekteerib kıik kuuenda p‰eva saated ja nende ajad ning v‰‰rtustab tulemuse vastavasse muutujasse
                for(int index = day_five; index < day_six; index++){
                	day_six_content += (timelist.get(index) + programlist.get(index));
                }
                
                //Selekteerib kıik seitsmenda p‰eva saated ja nende ajad ning v‰‰rtustab tulemuse vastavasse muutujasse
                for(int index = day_six; index < day_seven; index++){
                	day_seven_content += (timelist.get(index) + programlist.get(index));
                }                             
 
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        
        /**
         * Kuvab sisu vastavates vaades.
         */
        @Override
        protected void onPostExecute(Void result) {  
        	//M‰‰rab vastavate p‰evade nimetused
        	adapter.setTitle(0,(daylist.get(0).substring(0, daylist.get(0).indexOf(' '))));
        	adapter.setTitle(1,(daylist.get(1).substring(0, daylist.get(1).indexOf(' '))));
        	adapter.setTitle(2,(daylist.get(2).substring(0, daylist.get(2).indexOf(' '))));
        	adapter.setTitle(3,(daylist.get(3).substring(0, daylist.get(3).indexOf(' '))));
        	adapter.setTitle(4,(daylist.get(4).substring(0, daylist.get(4).indexOf(' '))));
        	adapter.setTitle(5,(daylist.get(5).substring(0, daylist.get(5).indexOf(' '))));
        	adapter.setTitle(6,(daylist.get(6).substring(0, daylist.get(6).indexOf(' '))));
        	//Annab adapterile teada, et andmeid muudeti
        	adapter.notifyDataSetChanged();
        	//Kuvab seitsmesse fragmenti vastava teksti
        	TextView firstText = (TextView) findViewById(R.id.firstFragment);
        	firstText.setText(day_one_content);
            TextView secondText = (TextView) findViewById(R.id.secondFragment);
            secondText.setText(day_two_content);
            TextView thirdText = (TextView) findViewById(R.id.thirdFragment);
            thirdText.setText(day_three_content);
            TextView fourthText = (TextView) findViewById(R.id.fourthFragment);
            fourthText.setText(day_four_content);
            TextView fifthText = (TextView) findViewById(R.id.fifthFragment);
            fifthText.setText(day_five_content);
            TextView sixthText = (TextView) findViewById(R.id.sixthFragment);
            sixthText.setText(day_six_content);
            TextView seventhText = (TextView) findViewById(R.id.seventhFragment);
            seventhText.setText(day_seven_content);
            //Eemaldab progressi dialoogi            
            progressDialog.dismiss();            
        }
    }    
}

/**
 * Adapter klass fragmentidega suhtlemiseks.
 */
class Adapter extends FragmentPagerAdapter {
		
	//Fragmentide pealkirjad, mis n‰itab vastava vaate n‰dalap‰eva nimetust
	public String[] titles = { "", "", "", "", "", "", "" };
	
	public Adapter(FragmentManager fragmentManager) {
		super(fragmentManager);
	}
	
	//Tagastab fragmendi vastavalt positsioonile
	@Override
	public Fragment getItem(int arg0) {
		switch(arg0){
		case 0: 
			return FirstDay.newInstance();
		case 1: 
			return SecondDay.newInstance(); 
		case 2:
			return ThirdDay.newInstance();	
		case 3:
			return FourthDay.newInstance();	
		case 4:
			return FifthDay.newInstance();	
		case 5:
			return SixthDay.newInstance();	
		case 6:
			return SeventhDay.newInstance();	
		default: 
			return FirstDay.newInstance();
		}
	}

	//Tagastab saadaval olevate vaadete arvu
	@Override
	public int getCount() {
		return 7;
	}
	
	//Tagastab vastava p‰eva vaate pealkirja
	public CharSequence getPageTitle(int position) {
		return titles[position]; 
	}
	
	//M‰‰rab vastavale vaatele pealkirja
	public void setTitle(int index, String text) {
	        this.titles[index] = text;
	}
}