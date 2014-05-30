package com.example.telekava.fragments;

import com.example.telekava.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Teise p�eva fragment ehk vaade.
 * @author Silver Tikk
 */
public class SecondDay extends Fragment {
	
	/**
	 * Loob ja tagastab vaate hierarhia.
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
		View v = inflater.inflate(R.layout.second_fragment, container, false);		
		return v;
	}
	
	/**
	 * Loob uue n�dalap�eva vaate ja tagastab selle.
	 * @return Vastava n�dalap�eva vaate
	 */
	public static SecondDay newInstance() {
		SecondDay secondday = new SecondDay();		
		return secondday;
	}
}