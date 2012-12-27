package com.davidtunnell.deliverypalfree;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.davidtunnell.deliverypalfree.R;

public class Tutorial extends SharedPref {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tutorial);

		Button done = (Button) findViewById(R.id.DoneTutorial);
		done.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (getSavedData(firstCheck, Tutorial.this) == null) {
					Intent firstPref = new Intent(
							"com.davidtunnell.deliverypalfree.PREFERENCES");
					startActivity(firstPref);
				} else {
					// else go to the main activity
					Intent firstPref = new Intent(
							"com.davidtunnell.deliverypalfree.MAINTABS");
					startActivity(firstPref);
				}

			}

		});
	}

}
