package com.thanu.readme;

import java.util.ArrayList;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ScorePage extends Activity implements View.OnClickListener {

	private ImageView star1;
	private ImageView star2;
	private ImageView star3;
	private ImageView star4;
	private ImageView star5;
	private ImageView smilyImage;
	private ImageButton playagain;
	private Intent scoreIntent;
	private ArrayList<Integer> scoreList;
	private String ActivityName;
	private Bundle bundleName;
	int totalGreen = 0;
	private Intent gameScreen;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.score);
		scoreIntent = getIntent();
		scoreList = scoreIntent.getIntegerArrayListExtra("score");
		bundleName = scoreIntent.getExtras();
		ActivityName = bundleName.getString("ActivityName");
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub

		star1 = (ImageView) findViewById(R.id.star1);
		star2 = (ImageView) findViewById(R.id.star2);
		star3 = (ImageView) findViewById(R.id.star3);
		star4 = (ImageView) findViewById(R.id.star4);
		star5 = (ImageView) findViewById(R.id.star5);
		smilyImage = (ImageView) findViewById(R.id.smilyImage);
		playagain = (ImageButton) findViewById(R.id.playagain);
		playagain.setOnClickListener(this);

		if (scoreList.get(0) == 1) {
			star1.setImageResource(R.drawable.star_green);
			totalGreen++;
		} else
			star1.setImageResource(R.drawable.star_red);

		if (scoreList.get(1) == 1) {
			star2.setImageResource(R.drawable.star_green);
			totalGreen++;
		} else
			star2.setImageResource(R.drawable.star_red);

		if (scoreList.get(2) == 1) {
			star3.setImageResource(R.drawable.star_green);
			totalGreen++;
		} else
			star3.setImageResource(R.drawable.star_red);

		if (scoreList.get(3) == 1) {
			star4.setImageResource(R.drawable.star_green);
			totalGreen++;
		} else
			star4.setImageResource(R.drawable.star_red);

		if (scoreList.get(4) == 1) {
			star5.setImageResource(R.drawable.star_green);
			totalGreen++;
		} else
			star5.setImageResource(R.drawable.star_red);

		if (totalGreen >= 4)
			smilyImage.setImageResource(R.drawable.happy);
		else
			smilyImage.setImageResource(R.drawable.sad);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.playagain:

			if (ActivityName.equals("MainActivity")) {
                gameScreen = new Intent(this, MainActivity.class);
				startActivity(gameScreen);

			}
			else if (ActivityName.equals("Findname")) {
                gameScreen = new Intent(this, Findname.class);
				startActivity(gameScreen);

			}
			else if (ActivityName.equals("SpellActivity")) {
                gameScreen = new Intent(this, SpellActivity.class);
				startActivity(gameScreen);

			}
			break;

		}

	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		System.out.println("onStoping the activity");

		super.onStop();
		finish();
	}
}
