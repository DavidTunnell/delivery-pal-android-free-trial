package com.davidtunnell.deliverypalfree;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import com.davidtunnell.deliverypalfree.R;

public class SplashActivity extends SharedPref {
	MediaPlayer chaChingSound;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		// create media player variable for sound effect when starting app
		chaChingSound = MediaPlayer.create(SplashActivity.this,
				R.raw.splashsound);
		// play sound
		chaChingSound.start();

		Thread timer = new Thread() {
			public void run() {
				try {
					// 2 second splash on start of program
					sleep(2000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				} finally {
					// populate shared preferences with necessary starting
					// values, or if values become empty repopulate
					populatedSharedPref();
					// if its the 1st time the program has started, open the
					// preferences menu

					Intent firstPref = new Intent(
							"com.davidtunnell.deliverypalfree.FREETRIAL");
					startActivity(firstPref);
				}
			}
		};
		// start thread
		timer.start();//
	}

	@Override
	protected void onPause() {
		super.onPause();
		// when activity is paused, end sound if its still on
		chaChingSound.release();
		// close activity
		finish();
	}
}
