package com.thanu.readme;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;



import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.LayerDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements 
		View.OnClickListener {

	//private TextToSpeech tts;
	private ImageView picture = null;
	// private RatingBar rating;
	private TextView word;
	private ImageButton recButton;
	private LayerDrawable stars;
	private ImageView star1;
	private ImageView star2;
	private ImageView star3;
	private ImageView star4;
	private ImageView star5;
	private static int qCounter = 1;
	int check = 111;
	private ArrayList<String> imageName;
	private ArrayList<String> desc;
	private ArrayList<Integer> score;
	private Random randomGenerator;
	int randomImageIndex = 0;
	private Set<Integer> shownImageset;
	
	MediaPlayer ourSong;
	MediaPlayer wrong;
	private Intent scoreIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initialize();

	}

	private void initialize() {

	//	tts = new TextToSpeech(this, this);
		picture = (ImageView) findViewById(R.id.imageView1);
		// rating = (RatingBar) findViewById(R.id.ratingBar1);
		word = (TextView) findViewById(R.id.textView1);
		recButton = (ImageButton) findViewById(R.id.imageButton1);
		recButton.setOnClickListener(this);
		// rating.setIsIndicator(true);
		star1 = (ImageView) findViewById(R.id.star1);
		star2 = (ImageView) findViewById(R.id.star2);
		star3 = (ImageView) findViewById(R.id.star3);
		star4 = (ImageView) findViewById(R.id.star4);
		star5 = (ImageView) findViewById(R.id.star5);
		randomGenerator = new Random();
		shownImageset = new HashSet<Integer>();
		
		
		imageName = new ArrayList<String>();
		desc = new ArrayList<String>();
		score = new ArrayList<Integer>();
		addingResource();
		ourSong = MediaPlayer
				.create(MainActivity.this, R.raw.clapping_children);
		wrong = MediaPlayer.create(MainActivity.this, R.raw.wrong);

		
		// stars = (LayerDrawable) rating.getProgressDrawable();
		// imageView6 = (ImageView) findViewById(R.id.imageView6);

	}

	private void addingResource() {

		imageName.add("animal_farm_chick");
		imageName.add("animal_farm_cow");
		imageName.add("animal_farm_goat");
		imageName.add("animal_farm_goose");
		imageName.add("animal_farm_hen");
		imageName.add("animal_farm_horse");
		imageName.add("animal_farm_pig");
		imageName.add("animal_farm_rabbit");
		imageName.add("animal_farm_rooster");
		imageName.add("animal_farm_sheep");
		imageName.add("animal_farm_turkey");
		
		imageName.add("first_word_airplane");
		imageName.add("first_word_bee");
		imageName.add("first_word_butterfly");
		imageName.add("first_word_cat");
		imageName.add("first_word_dog");
		imageName.add("first_word_egg");
		imageName.add("first_word_eye");
		imageName.add("first_word_icecream");
		imageName.add("first_word_ship");
		imageName.add("fruit_apple");
		imageName.add("fruit_coconut");
		imageName.add("fruit_grapes");
		imageName.add("fruit_kiwi");
		imageName.add("fruit_orange");
		imageName.add("fruit_peach");
		imageName.add("fruit_pear");
		imageName.add("fruit_pineapple");
		imageName.add("fruit_plum");
		imageName.add("fruit_raspberry");
		imageName.add("fruit_strawberry");
		imageName.add("fruit_watermelon");
		
		imageName.add("kitchen_kettle");
		imageName.add("kitchen_microwave");
		imageName.add("kitchen_mug");
		
		
		
		imageName.add("sea_crab");
		imageName.add("sea_dolphin");
		imageName.add("sea_fish");
		imageName.add("sea_jellyfish");
		imageName.add("sea_octopus");
		imageName.add("sea_seahorse");
		imageName.add("sea_shark");
		imageName.add("sea_turtle");
		
		imageName.add("vegetable_cabbage");
		imageName.add("vegetable_carrot");
		imageName.add("vegetable_corn");
		imageName.add("vegetable_cucumber");
		imageName.add("vegetable_onion");
		imageName.add("vegetable_peas");
		imageName.add("vegetable_pepper");
		imageName.add("vegetable_potato");
		imageName.add("vegetable_tomato");

		desc.add("chicken");
		desc.add("cow");
		desc.add("goat");
		desc.add("goose");
		desc.add("hen");
		desc.add("horse");
		desc.add("pig");
		desc.add("rabbit");
		desc.add("rooster");
		desc.add("sheep");
		desc.add("turkey");
		desc.add("airplane");
		desc.add("bee");
		desc.add("butterfly");
		desc.add("cat");
		desc.add("dog");
		desc.add("egg");
		desc.add("eye");
		desc.add("icecream");
		desc.add("ship");
		desc.add("apple");
		desc.add("coconut");
		desc.add("grapes");
		desc.add("kiwi");
		desc.add("orange");
		desc.add("peach");
		desc.add("pear");
		desc.add("pineapple");
		desc.add("plum");
		desc.add("raspberry");
		desc.add("strawberry");
		desc.add("watermelon");
		desc.add("kettle");
		desc.add("microwave");
		desc.add("mug");
		desc.add("crab");
		desc.add("dolphin");
		desc.add("fish");
		desc.add("jellyfish");
		desc.add("octopus");
		desc.add("seahorse");
		desc.add("shark");
		desc.add("turtle");
		desc.add("cabbage");
		desc.add("carrot");
		desc.add("corn");
		desc.add("cucumber");
		desc.add("onion");
		desc.add("peas");
		desc.add("pepper");
		desc.add("potato");
		desc.add("tomato");

	}

/*	@Override
	public void onInit(int status) {
		// TODO Auto-generated method stub
		if (status == TextToSpeech.SUCCESS) {
			System.out.println("Entering condition check");
			int result = tts.setLanguage(Locale.US);

			if (result == TextToSpeech.LANG_MISSING_DATA
					|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
				System.out.println("Text to sppeech not support  quit the app");
			}

		}

	}*/

	@Override
	public void onDestroy() {
		// Don't forget to shutdown tts!
		/*if (tts != null) {
			tts.stop();
			tts.shutdown();
		}*/
		ourSong.release();
		wrong.release();
		super.onDestroy();
		// Toast.makeText(this, "onDestroy", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent mic;
		switch (v.getId()) {

		case R.id.imageButton1:
			mic = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
			mic.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
					RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
			mic.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak up Son!");
			startActivityForResult(mic, check);

			break;

		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub

		if (requestCode == check && resultCode == RESULT_OK) {

			ArrayList<String> results = data
					.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

			int i;
			String listenMessage = (String) word.getText();
			System.out.println("Listen message " + listenMessage);
			if (listenMessage.equalsIgnoreCase((results.get(0).toString()))) {
				successRating();
				loadResource();
			} else {

				FailureRating();
				loadResource();

			}
		}

	}

	protected void successRating() {

		System.out.println("Change Rating ");
		ourSong.start();
		if (qCounter <= 5) {
			score.add(1);

			if (qCounter == 1)
				star1.setImageResource(R.drawable.star_green);
			else if (qCounter == 2)
				star2.setImageResource(R.drawable.star_green);
			else if (qCounter == 3)
				star3.setImageResource(R.drawable.star_green);
			else if (qCounter == 4)
				star4.setImageResource(R.drawable.star_green);
			else if (qCounter == 5) {				
				star5.setImageResource(R.drawable.star_green);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				scoreIntent = new Intent("android.intent.action.SCORE");
				scoreIntent.putIntegerArrayListExtra("score", score);
				scoreIntent.putExtra("ActivityName", "MainActivity");
				startActivity(scoreIntent);

			}

		}

		qCounter++;

	}

	protected void FailureRating() {
		if (qCounter <= 5) {
			score.add(0);
			wrong.start();
			if (qCounter == 1)
				star1.setImageResource(R.drawable.star_red);
			else if (qCounter == 2)
				star2.setImageResource(R.drawable.star_red);
			else if (qCounter == 3)
				star3.setImageResource(R.drawable.star_red);
			else if (qCounter == 4)
				star4.setImageResource(R.drawable.star_red);
			else if (qCounter == 5) {
				star5.setImageResource(R.drawable.star_red);				
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
				scoreIntent = new Intent("android.intent.action.SCORE");
				scoreIntent.putIntegerArrayListExtra("score", score);
				scoreIntent.putExtra("ActivityName", "MainActivity");
				startActivity(scoreIntent);

			}

		}

		qCounter++;
	}

	protected void loadResource() {

		if (qCounter <= 5) {

			randomImageIndex = randomGenerator.nextInt(imageName.size());

			/* if (qCounter == 1) { */
			if (shownImageset.add(randomImageIndex)) {
				String picName = imageName.get(randomImageIndex);
				int resID = getResources().getIdentifier(picName, "drawable",
						"com.thanu.readme");
				System.out.println("Setting image " + picName);
				picture.setImageResource(resID);
				word.setText(desc.get(randomImageIndex));

			} else
				loadResource();

		}

		// }
		/*
		 * else { String picName = "lion"; int resID =
		 * getResources().getIdentifier(picName, "drawable",
		 * "com.example.readme"); picture.setImageResource(resID);
		 * word.setText("apple"); }
		 */
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
	    System.out.println("passing the activity");
	   // if ( qCounter > 5 ) {
	    	
	    	System.out.println("calling Destroy method");
	         //onDestroy(); 	
	   // }   
		super.onPause();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		System.out.println("onRestarting activity");
		super.onRestart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		System.out.println("onResuming the activity");
		super.onResume();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
        System.out.println("onStarting activity");
		super.onStart();
		qCounter = 1;
		shownImageset.clear();
		loadResource();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		System.out.println("onStoping the activity");
		
		
		
		super.onStop();
		finish();
	}

}
