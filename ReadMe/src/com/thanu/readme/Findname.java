package com.thanu.readme;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.Random;
import java.util.Set;



import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.LayerDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Findname extends Activity implements View.OnClickListener,
		TextToSpeech.OnInitListener {

	// private TextToSpeech tts;
	private ImageView picture = null;
	private TextView answerText;
	private ImageView showanswer;
	// private RatingBar rating;
	//private TextView word;
	private ImageButton forwardButton;
	private LayerDrawable stars;
	private ImageView star1;
	private ImageView star2;
	private ImageView star3;
	private ImageView star4;
	private ImageView star5;
	private CheckBox option1;
	private CheckBox option2;
	private CheckBox option3;
	private TextToSpeech tts;

	private static int qCounter = 1;
	int check = 111;
	private ArrayList<String> imageName;
	// private ArrayList<String> desc;
	private ArrayList<String> animal;
	private ArrayList<String> first;
	private ArrayList<String> fruit;
	private ArrayList<String> kitchen;
	private ArrayList<String> sea;
	private ArrayList<String> vegetable;
	private ArrayList<Integer> score;
	private Random randomGenerator;
	int randomImageIndex = 0;
	private Set<Integer> shownImageset;
	private Set<String> shownOptionset;
	String ImageNameSplit[];

	MediaPlayer ourSong;
	MediaPlayer wrong;
	private Intent scoreIntent;
	private static String correctedImageName = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_findname);
		initialize();
	}

	private void initialize() {

		// tts = new TextToSpeech(this, this);
		picture = (ImageView) findViewById(R.id.imageView1);
		// rating = (RatingBar) findViewById(R.id.ratingBar1);
		// word = (TextView) findViewById(R.id.textView1);
		forwardButton = (ImageButton) findViewById(R.id.forwardButton);
		forwardButton.setOnClickListener(this);
		// rating.setIsIndicator(true);
		star1 = (ImageView) findViewById(R.id.star1);
		star2 = (ImageView) findViewById(R.id.star2);
		star3 = (ImageView) findViewById(R.id.star3);
		star4 = (ImageView) findViewById(R.id.star4);
		star5 = (ImageView) findViewById(R.id.star5);

		option1 = (CheckBox) findViewById(R.id.checkBox1);
		option2 = (CheckBox) findViewById(R.id.checkBox2);
		option3 = (CheckBox) findViewById(R.id.checkBox3);

		showanswer = (ImageView) findViewById(R.id.showanswer);
		answerText = (TextView) findViewById(R.id.answer);

		option1.setOnClickListener(this);
		option2.setOnClickListener(this);
		option3.setOnClickListener(this);

		randomGenerator = new Random();
		shownImageset = new HashSet<Integer>();
		shownOptionset = new HashSet<String>();

		imageName = new ArrayList<String>();
		// desc = new ArrayList<String>();
		score = new ArrayList<Integer>();

		animal = new ArrayList<String>();
		first = new ArrayList<String>();
		fruit = new ArrayList<String>();
		kitchen = new ArrayList<String>();
		sea = new ArrayList<String>();
		vegetable = new ArrayList<String>();
		tts = new TextToSpeech(this, this);
		addingResource();
		ourSong = MediaPlayer.create(Findname.this, R.raw.clapping_children);
		wrong = MediaPlayer.create(Findname.this, R.raw.wrong);

		// stars = (LayerDrawable) rating.getProgressDrawable();
		// imageView6 = (ImageView) findViewById(R.id.imageView6);

	}

	private void addingResource() {
		// TODO Auto-generated method stub
		imageName.add("animal_farm_chicken");
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

		animal.add("chicken");
		animal.add("cow");
		animal.add("goat");
		animal.add("goose");
		animal.add("hen");
		animal.add("horse");
		animal.add("pig");
		animal.add("rabbit");
		animal.add("rooster");
		animal.add("sheep");
		animal.add("turkey");

		first.add("airplane");
		first.add("bee");
		first.add("butterfly");
		first.add("cat");
		first.add("dog");
		first.add("egg");
		first.add("eye");
		first.add("icecream");
		first.add("ship");

		fruit.add("apple");
		fruit.add("coconut");
		fruit.add("grapes");
		fruit.add("kiwi");
		fruit.add("orange");
		fruit.add("peach");
		fruit.add("pear");
		fruit.add("pineapple");
		fruit.add("plum");
		fruit.add("raspberry");
		fruit.add("strawberry");
		fruit.add("watermelon");

		kitchen.add("kettle");
		kitchen.add("microwave");
		kitchen.add("mug");

		sea.add("crab");
		sea.add("dolphin");
		sea.add("fish");
		sea.add("jellyfish");
		sea.add("octopus");
		sea.add("seahorse");
		sea.add("shark");
		sea.add("turtle");

		vegetable.add("cabbage");
		vegetable.add("carrot");
		vegetable.add("corn");
		vegetable.add("cucumber");
		vegetable.add("onion");
		vegetable.add("peas");
		vegetable.add("pepper");
		vegetable.add("tomato");
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {

		case R.id.checkBox1:

			if (option1.isChecked()) {
				option2.setChecked(false);
				option3.setChecked(false);
			}

			break;
		case R.id.checkBox2:

			if (option2.isChecked()) {
				option1.setChecked(false);
				option3.setChecked(false);
			}

			break;
		case R.id.checkBox3:

			if (option3.isChecked()) {
				option1.setChecked(false);
				option2.setChecked(false);
			}

			break;

		case R.id.forwardButton:

			if (answerText.getVisibility() != TextView.VISIBLE
					|| showanswer.getVisibility() != ImageView.VISIBLE) {

				if (option3.isChecked() || option1.isChecked()
						|| option2.isChecked()) {

					if (option1.isChecked()) {

						if (correctedImageName.equals(option1.getText())) {
							successRating();
							loadResource();
						} else {
							FailureRating();
							if (qCounter == 6)
								speakOut("Please check the answer and move to score page");
							else
								speakOut("Please check the answer and move to next question");

							answerText.setVisibility(TextView.VISIBLE);
							showanswer.setVisibility(ImageView.VISIBLE);
							answerText
									.setText(correctedImageName.toUpperCase());
						}
						option1.setChecked(false);
					} else if (option2.isChecked()) {

						if (correctedImageName.equals(option2.getText())) {
							successRating();
							loadResource();
						} else {
							FailureRating();
							if (qCounter == 6)
								speakOut("Please check the answer and move to score page");
							else
								speakOut("Plese check the answer and move to next question");

							answerText.setVisibility(TextView.VISIBLE);
							showanswer.setVisibility(ImageView.VISIBLE);
							answerText
									.setText(correctedImageName.toUpperCase());
						}
						option2.setChecked(false);
					} else if (option3.isChecked()) {

						if (correctedImageName.equals(option3.getText())) {
							successRating();
							loadResource();
						} else {
							FailureRating();
							if (qCounter == 6)
								speakOut("Please check the answer and move to score page");
							else
								speakOut("Please check the answer and move to next question");

							answerText.setVisibility(TextView.VISIBLE);
							showanswer.setVisibility(ImageView.VISIBLE);
							answerText
									.setText(correctedImageName.toUpperCase());
						}
						option3.setChecked(false);
					}

				} else {

					speakOut("Please choose anyone");
				}
			}

			else {

				if (qCounter == 6) {

					scoreIntent = new Intent("android.intent.action.SCORE");
					scoreIntent.putIntegerArrayListExtra("score", score);
					scoreIntent.putExtra("ActivityName", "Findname");
					startActivity(scoreIntent);
				} else
					loadResource();

			}
			break;
		}

	}

	@Override
	public void onDestroy() {
		// Don't forget to shutdown tts!
		/*
		 * if (tts != null) { tts.stop(); tts.shutdown(); }
		 */
		// Don't forget to shutdown tts!
		if (tts != null) {
			tts.stop();
			tts.shutdown();
		}
		ourSong.release();
		wrong.release();
		super.onDestroy();
		// Toast.makeText(this, "onDestroy", Toast.LENGTH_LONG).show();
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
				scoreIntent.putExtra("ActivityName", "Findname");
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

			}

		}

		qCounter++;
	}

	protected void loadResource() {

		if (answerText.getVisibility() == TextView.VISIBLE
				|| showanswer.getVisibility() == ImageView.VISIBLE) {
			answerText.setVisibility(TextView.INVISIBLE);
			showanswer.setVisibility(ImageView.INVISIBLE);

		}

		System.out.println("Entering LoadResource");
		if (qCounter <= 5) {

			randomImageIndex = randomGenerator.nextInt(imageName.size());

			/* if (qCounter == 1) { */
			if (shownImageset.add(randomImageIndex)) {
				String picName = imageName.get(randomImageIndex);
				int resID = getResources().getIdentifier(picName, "drawable",
						"com.thanu.readme");
				System.out.println("Setting image " + picName);
				picture.setImageResource(resID);
				ImageNameSplit = picName.split("_");

				correctedImageName = ImageNameSplit[(ImageNameSplit.length) - 1];
				loadOptionValue(ImageNameSplit[0], correctedImageName);
				// word.setText(desc.get(randomImageIndex));

			} else
				loadResource();

		}

	}

	protected void optionValue(int index, String imageName) {

		if (index == 0) {
			option1.setText(imageName);
		}

		if (index == 1) {
			option2.setText(imageName);
		}
		if (index == 2) {
			option3.setText(imageName);
		}

	}

	protected String findotherImage(String category) {

		String ImageName = category;
		if (category.equals("animal")) {
			randomImageIndex = randomGenerator.nextInt(animal.size());
			ImageName = animal.get(randomImageIndex);
		} else if (category.equals("first")) {
			randomImageIndex = randomGenerator.nextInt(first.size());
			ImageName = first.get(randomImageIndex);
		} else if (category.equals("fruit")) {
			randomImageIndex = randomGenerator.nextInt(fruit.size());
			ImageName = fruit.get(randomImageIndex);
		} else if (category.equals("kitchen")) {
			randomImageIndex = randomGenerator.nextInt(kitchen.size());
			ImageName = kitchen.get(randomImageIndex);
		} else if (category.equals("sea")) {
			randomImageIndex = randomGenerator.nextInt(sea.size());
			ImageName = sea.get(randomImageIndex);
		} else if (category.equals("vegetable")) {
			randomImageIndex = randomGenerator.nextInt(vegetable.size());
			ImageName = vegetable.get(randomImageIndex);

		}

		if (shownOptionset.add(ImageName)) {
			// return ImageName;
		} else {
			ImageName = findotherImage(category);
		}
		return ImageName;

	}

	protected void loadOptionValue(String category, String imageName) {

		randomImageIndex = randomGenerator.nextInt(3);
		int option2 = 0;
		int option3 = 0;

		if (randomImageIndex == 0) {
			option2 = 1;
			option3 = 2;
		} else if (randomImageIndex == 1) {
			option2 = 0;
			option3 = 2;
		} else if (randomImageIndex == 2) {

			option2 = 0;
			option3 = 1;
		}

		optionValue(randomImageIndex, imageName);
		shownOptionset.add(imageName);
		System.out.println("Image1 is " + imageName + " " + randomImageIndex);

		String Image2 = findotherImage(category);
		System.out.println("Image2 value " + Image2);
		optionValue(option2, Image2);

		String Image3 = findotherImage(category);
		System.out.println("Image3 value " + Image3);
		optionValue(option3, Image3);

		shownOptionset.clear();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		speakOut("What is This?");

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

	@Override
	public void onInit(int status) {
		// TODO Auto-generated method stub
		if (status == TextToSpeech.SUCCESS) {

			int result = tts.setLanguage(Locale.US);

			if (result == TextToSpeech.LANG_MISSING_DATA
					|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
				Toast.makeText(this, "There is a probleem on Speaking",
						Toast.LENGTH_SHORT).show();
			} else {
				speakOut("What is This?");
			}

		} else {
			Toast.makeText(this,
					"There is a probleem on Speaking - Initialization",
					Toast.LENGTH_SHORT).show();
		}
	}

	private void speakOut(String text) {
		// TODO Auto-generated method stub
		tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
	}

}
