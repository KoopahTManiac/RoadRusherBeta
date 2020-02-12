package com.koopahtmaniac.roadrusher;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.ads.AdSize;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;


public class AndroidLauncher extends AndroidApplication
{
	public static AdView adView; //small ad
	public static AdView fullAdView; //big ad


	private AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
	public static String FULLADCODE = "ca-app-pub-6480714559562426/8895978825";
	public View gameView;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		adView = new AdView(this);

		AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();

		RelativeLayout layout = new RelativeLayout(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().addFlags(LayoutParams.FLAG_KEEP_SCREEN_ON);
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
		RelativeLayout.LayoutParams fadParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		fadParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		fadParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		fullAdView = new AdView(this);
		fullAdView.setAdSize(com.google.android.gms.ads.AdSize.MEDIUM_RECTANGLE);
		fullAdView.setAdUnitId(FULLADCODE);

		AdRequest.Builder adRequestBuilder = new AdRequest.Builder();
		fullAdView.loadAd(adRequestBuilder.build());


		View gameView = initializeForView(new GameCore(new RequestHandler(adView, fullAdView)), cfg);

		layout.addView(gameView);
		layout.addView(fullAdView, fadParams);


		fullAdView.setVisibility(View.GONE); //closed at the start

		//initialize(new GameCore(), cfg);
		setContentView(layout);
	}
	public void Reset()
	{

		gameView = null;
		RelativeLayout layout = new RelativeLayout(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().addFlags(LayoutParams.FLAG_KEEP_SCREEN_ON);
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
		RelativeLayout.LayoutParams fadParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		fadParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		fadParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

		fullAdView = new AdView(this);
		fullAdView.setAdSize(com.google.android.gms.ads.AdSize.MEDIUM_RECTANGLE);
		fullAdView.setAdUnitId(FULLADCODE);

		AdRequest.Builder adRequestBuilder = new AdRequest.Builder();
		fullAdView.loadAd(adRequestBuilder.build());


		gameView = initializeForView(new GameCore(new RequestHandler(adView, fullAdView)), cfg);

		layout.addView(gameView);
		layout.addView(fullAdView, fadParams);


		fullAdView.setVisibility(View.GONE); //closed at the start
	}
}