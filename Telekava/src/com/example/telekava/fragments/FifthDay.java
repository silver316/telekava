package com.example.telekava.fragments;

import com.example.telekava.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FifthDay extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
		View v = inflater.inflate(R.layout.fifth_fragment, container, false);		
		return v;
	}
	
	public static FifthDay newInstance() {
		FifthDay fifthday = new FifthDay();		
		return fifthday;
	}
}
