package com.davidtunnell.deliverypalfree;

import java.util.Calendar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class FreeTrial extends SharedPref {
	// package name for app, used to open store pages
	final String appName = "com.davidtunnell.deliverypal";

	Button ok;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.free_trial);
		connectVariables();
		//
	}

	private void connectVariables() {
		TextView daysLeft = (TextView) findViewById(R.id.TrialDaysLeft);
		// set text to the amount of days left in the trial
		daysLeft.setText(getAndSaveDaysLeft() + " Days");

		Button upgrade = (Button) findViewById(R.id.UpgradeButton);
		upgrade.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// try to open full version app page in market, if not installed
				// the play page in the default web browser will open instead
				try {
					startActivity(new Intent(Intent.ACTION_VIEW, Uri
							.parse("market://details?id=" + appName)));
				} catch (android.content.ActivityNotFoundException anfe) {
					startActivity(new Intent(
							Intent.ACTION_VIEW,
							Uri.parse("http://play.google.com/store/apps/details?id="
									+ appName)));
				}
			}

		});
		ok = (Button) findViewById(R.id.OkTry);
		// if there are no more days left in the trial, allow button to be seen,
		// and used to continue to program
		if (Long.valueOf(getSavedData(daysLeftTrial, FreeTrial.this)) >= 0.0) {

			ok.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (getSavedData(firstCheck, FreeTrial.this) == null) {
						// if this is the first time the program opened, open
						// tutorial instead of main-tabs
						Intent firstPref = new Intent(
								"com.davidtunnell.deliverypalfree.TUTORIAL");
						startActivity(firstPref);
					} else {
						// else go to the main activity
						Intent firstPref = new Intent(
								"com.davidtunnell.deliverypalfree.MAINTABS");
						startActivity(firstPref);
					}

				}

			});
			// else hide the button that allows use of the program
		} else {
			ok.setVisibility(View.GONE);
		}
	}

	private String getAndSaveDaysLeft() {
		// set long variable to the time in milliseconds the program was opened
		// for the first time
		long startMilis = Long.valueOf(getSavedData(calenderTrial,
				FreeTrial.this));
		// get current time
		Calendar currentTime = Calendar.getInstance();
		// set variable to current time in milliseconds
		long currentMilis = currentTime.getTimeInMillis();
		// get the difference in miliseconds
		long difference = currentMilis - startMilis;
		// divide to find difference in terms of days
		long differenceDays = difference / (1000 * 60 * 60 * 24);
		// 30 minus difference in days
		long timeLeft = 30 - differenceDays;
		// Save days left in trial to shared preferences
		saveToSP(daysLeftTrial, String.valueOf(timeLeft), FreeTrial.this);
		// return string of the timeLeft in days
		return String.valueOf(timeLeft);
	}
}
