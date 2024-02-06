package com.hamidul.navigationdrawer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;


public class BlankFragment extends Fragment {

    public static String WEB_URL = "";
    WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

         View myView =  inflater.inflate(R.layout.fragment_blank, container, false);

         webView = myView.findViewById(R.id.webView);

         webView.getSettings().setJavaScriptEnabled(true);
         webView.loadUrl(WEB_URL);

         return myView;
    }
}