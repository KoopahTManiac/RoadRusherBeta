package com.koopahtmaniac.roadrusher;

import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.google.android.gms.ads.AdView;


public class RequestHandler implements IReqHandler
{

	private static AdView adV;
	private static AdView fadV;
	protected int adHeight = 10;

	private static final int SHOW_ADS = 1;
	private static final  int HIDE_ADS = 0;
	private static final  int SHOW_FULLADS = 3;
	private static final  int HIDE_FULLADS = 4;

	private final static Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			switch (msg.what)
			{
				case SHOW_ADS: {
					adV.setVisibility(View.VISIBLE);
					break;
				}
				case HIDE_ADS:
				{
					adV.setVisibility(View.GONE);
					break;
				}
				case SHOW_FULLADS:
				{
					fadV.setVisibility(View.VISIBLE);
					break;
				}
				case HIDE_FULLADS:
				{
					fadV.setVisibility(View.GONE);
					break;
				}
			}
		}
	};

	public void showFullAd(boolean show) 
	{
		handler.sendEmptyMessage(show ? SHOW_FULLADS : HIDE_FULLADS);
	}

	RequestHandler(AdView adView, AdView fad)
	{
		adV = adView;
		fadV = fad;
	}

	public void showAds(boolean show) 
	{
		handler.sendEmptyMessage(show ? SHOW_ADS : HIDE_ADS);
	}

	@Override
	public void showAd(boolean show) 
	{
		showFullAd(show);
	}
}