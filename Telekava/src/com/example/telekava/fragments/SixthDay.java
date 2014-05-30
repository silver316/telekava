package com.example.telekava.fragments;

import com.example.telekava.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Kuuenda päeva fragment ehk vaade.
 * @author Silver Tikk
 */
public class SixthDay extends Fragment {
	
	/**
	 * Loob ja tagastab vaate hierarhia.
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
		View v = inflater.inflate(R.layout.sixth_fragment, container, false);		
		return v;
	}
	
	/**
	 * Loob uue nädalapäeva vaate ja tagastab selle.
	 * @return Vastava nädalapäeva vaate
	 */
	public static SixthDay newInstance() {
		SixthDay sixthday = new SixthDay();		
		return sixthday;
	}
}
