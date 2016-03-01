package com.thanu.readme;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class SpellActivity extends Activity implements View.OnClickListener,
		OnTouchListener, OnDragListener, TextToSpeech.OnInitListener {

	private ImageView picture = null;
	private static final String LOGCAT = null;
	private TextToSpeech tts;
	
	RelativeLayout emptyLayout;
	RelativeLayout valueLayout;
	RelativeLayout buttonLayout;
	LinearLayout starLinearLayout;
	LinearLayout imageLinearLayout;
	RelativeLayout correctanswer;
	RelativeLayout questionlayout;
	RelativeLayout dummylayout;

	TextView letterText;
	TextView emptyText;
	TextView answerquestion;
	TextView answerText;
	ImageView showanswer;

	RelativeLayout.LayoutParams params;
	String answer = "";
	String shuffleanswer = "";
	int lenghtofanswer = 0;
	static int initialX = 0;
	static int initialY = 0;
	int temp;
	// private RatingBar rating;
	// private TextView word;
	// private ImageButton recButton;
	// private LayerDrawable stars;
	private ImageView star1;
	private ImageView star2;
	private ImageView star3;
	private ImageView star4;
	private ImageView star5;
	private ImageView forward;
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
	float dpHeight= 0;
	float dpWidth = 0;
	int letterSize = 40;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spell);
		initialize();
		// addTextView();

		/*
		 * makeEmtyTextView(0); makeEmtyTextView(1); makeEmtyTextView(2);
		 * makeEmtyTextView(3); makeEmtyTextView(4);
		 */
		/*
		 * makeValueTextView("t",0); makeValueTextView("h",1);
		 * makeValueTextView("a",2); makeValueTextView("n",3);
		 * makeValueTextView("u",4);
		 */
		/*
		 * letterText = new TextView(SpellActivity.this);
		 * 
		 * letterText.setId(0); letterText.setText("textvalue");
		 * letterText.setTextColor(Color.parseColor("#b73d00"));
		 * letterText.setBackgroundResource(R.drawable.letter);
		 * letterText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);
		 * letterText.setTypeface(letterText.getTypeface(), Typeface.BOLD);
		 * params = new RelativeLayout.LayoutParams(
		 * RelativeLayout.LayoutParams.WRAP_CONTENT,
		 * RelativeLayout.LayoutParams.WRAP_CONTENT); params.setMargins(80 + (0
		 * * 60), 50, 0, 0); valueLayout.addView(letterText, params);
		 */
		
		DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();

         dpHeight = displayMetrics.heightPixels / displayMetrics.density;
         dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        //Toast.makeText(this, "Density "  +displayMetrics.widthPixels + "/" +displayMetrics.density + "=" +dpWidth + "  h "+ displayMetrics.heightPixels + " "+ dpHeight  , Toast.LENGTH_LONG).show();
        
        System.out.println("Phoe with and height in Density "+  dpWidth  + "  " + dpHeight);
        
        if ( dpWidth  > 1250 ) {
        	
        	letterSize = 60;
        }

	}

	@SuppressLint("NewApi")
	protected void makeValueTextView(String name, int pos) {
		// String TextViewName = letterText+name;

		// TextView TextViewName = new TextView(this);
		params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		letterText = new TextView(SpellActivity.this);
		letterText.setId(pos);
		letterText.setText(name);
		letterText.setTextColor(Color.parseColor("#b73d00"));
		letterText.setBackgroundResource(R.drawable.letter);
		letterText.setTextSize(TypedValue.COMPLEX_UNIT_SP, letterSize - 10);
		letterText.setTypeface(letterText.getTypeface(), Typeface.BOLD);
		letterText.setOnTouchListener(this);

		// params.addRule(RelativeLayout., RelativeLayout.TRUE);
		// params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
		params.setMargins( (pos * 70), 30, 0, 0);
		valueLayout.addView(letterText, params);

	}

	protected void makeEmtyTextView(int pos) {

		params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		letterText = new TextView(SpellActivity.this);
		letterText.setId(pos);
		//letterText.setTextColor(Color.parseColor("#b73d00"));
		//letterText.setBackgroundColor(Color.parseColor("#ffffff"));
		letterText.setTextSize(TypedValue.COMPLEX_UNIT_SP, letterSize);
		letterText.setTypeface(letterText.getTypeface(), Typeface.BOLD);
		// letterText.setOnDragListener(this);
		
		// params.addRule(RelativeLayout., RelativeLayout.TRUE);
		// params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
		params.setMargins(40 + ((pos - lenghtofanswer) * 70), 20, 0, 0);
		emptyLayout.addView(letterText, params);

	}

	@SuppressLint("NewApi")
	public boolean onTouch(View view, MotionEvent event) {
		TextView currentTextview = (TextView) view;
		initialX = 0;
		initialY = 0;

		switch (event.getActionMasked()) {

		case MotionEvent.ACTION_DOWN:
			initialX = (int) event.getX();
			initialY = (int) event.getY();
			System.out.println("i am in Action Down x y  " + initialX + " "
					+ initialY);
			System.out.println("current selected Textview is "
					+ currentTextview.getText());
			DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
			view.startDrag(null, shadowBuilder, view, 0);
			view.setVisibility(View.INVISIBLE);
			break;
		case MotionEvent.ACTION_MOVE:
			System.out.println("i am in Action Move");
			int currentX = (int) event.getX();
			int currentY = (int) event.getY();
			params = (RelativeLayout.LayoutParams) currentTextview
					.getLayoutParams();

			int left = params.leftMargin + (currentX - initialX);
			int top = params.topMargin + (currentY - initialY);
			int right = params.rightMargin - (currentX - initialX);
			int bottom = params.bottomMargin - (currentY - initialY);

			params.rightMargin = right;
			params.leftMargin = left;
			params.bottomMargin = bottom;
			params.topMargin = top;

			currentTextview.setLayoutParams(params);
			break;

		default:
			break;
		}

		return true;
	}

	/*
	 * if (event.getAction() == MotionEvent.ACTION_DOWN) {
	 * 
	 * 
	 * return true; } else { return false; }
	 * 
	 * if (event.getAction() == MotionEvent.ACTION_MOVE) {
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * }
	 */

	@SuppressLint("NewApi")
	public boolean onDrag(View layoutview, DragEvent dragevent) {
		int action = dragevent.getAction();
		switch (action) {
		case DragEvent.ACTION_DRAG_STARTED:
			Log.d(LOGCAT, "Drag event started");
			break;
		case DragEvent.ACTION_DRAG_ENTERED:
			Log.d(LOGCAT, "Drag event entered into " + layoutview.toString());
			// View view = (View) dragevent.getLocalState();

			break;
		case DragEvent.ACTION_DRAG_EXITED:
			Log.d(LOGCAT, "Drag event exited from " + layoutview.toString());
			break;
		case DragEvent.ACTION_DROP:
			Log.d(LOGCAT, "Dropped");

			if (layoutview == findViewById(R.id.emptyhole)) {
				System.out.println("i am here at empty relative layout");
				View view = (View) dragevent.getLocalState();
				ViewGroup owner = (ViewGroup) view.getParent();
				owner.removeView(view);

				RelativeLayout container = (RelativeLayout) layoutview;
				float currentX = (float) dragevent.getX();
				float currentY = (float) dragevent.getY();
				System.out.println("Final initial  x y  " + initialX + " "
						+ initialY);
				System.out.println("Final  x y  " + currentX + " " + currentY);
				/*
				 * params = (RelativeLayout.LayoutParams) view
				 * .getLayoutParams();
				 * 
				 * int left = params.leftMargin + (currentX - initialX); int top
				 * = params.topMargin + (currentY - initialY); int right =
				 * params.rightMargin - (currentX - initialX); int bottom =
				 * params.bottomMargin - (currentY - initialY);
				 * 
				 * params.rightMargin = right; params.leftMargin = left;
				 * params.bottomMargin = bottom; params.topMargin = top;
				 */

				// params = new RelativeLayout.LayoutParams(
				// RelativeLayout.LayoutParams.WRAP_CONTENT,
				// RelativeLayout.LayoutParams.WRAP_CONTENT);
				// params.setMargins(80 , 50, 0, 0);
				view.setX(currentX - (view.getWidth() / 2));
				view.setY(currentY - (view.getHeight() / 2));
				container.addView(view);
				view.setVisibility(View.VISIBLE);
			}

			else {

				if (layoutview == findViewById(R.id.valuehole)) {
					System.out.println("I am here at orginal view");
					View view = (View) dragevent.getLocalState();
					ViewGroup owner = (ViewGroup) view.getParent();
					owner.removeView(view);

					RelativeLayout container = (RelativeLayout) layoutview;
					float currentX = (float) dragevent.getX();
					float currentY = (float) dragevent.getY();
					view.setX(currentX - (view.getWidth() / 2));
					view.setY(currentY - (view.getHeight() / 2));
					container.addView(view);
					view.setVisibility(View.VISIBLE);
				} else {
					System.out.println("I am here at some other layout");
					View view = (View) dragevent.getLocalState();
					view.setVisibility(View.VISIBLE);
					Context context = getApplicationContext();
					/*Toast.makeText(context, "You can't drop the image here",
							Toast.LENGTH_LONG).show();*/
					speakOut(" Please put the letters inside the hole");
					break;
				}

			}
			break;
		case DragEvent.ACTION_DRAG_ENDED:
			Log.d(LOGCAT, "Drag ended");
			break;
		default:
			break;
		}
		return true;
	}

	private void initialize() {

		// tts = new TextToSpeech(this, this);
		picture = (ImageView) findViewById(R.id.imageView1);

		emptyLayout = (RelativeLayout) findViewById(R.id.emptyhole);
		//emptyLayout.setBackgroundColor(Color.BLUE);
		valueLayout = (RelativeLayout) findViewById(R.id.valuehole);
		buttonLayout = (RelativeLayout) findViewById(R.id.buttonLayout);
		starLinearLayout = (LinearLayout) findViewById(R.id.starlayout);
		imageLinearLayout = (LinearLayout) findViewById(R.id.layoutid);
		correctanswer = (RelativeLayout) findViewById(R.id.correctanswer);
		questionlayout = (RelativeLayout) findViewById(R.id.questionlayout);
		dummylayout   = (RelativeLayout) findViewById(R.id.dummylayout);
		tts = new TextToSpeech(this, this);

		emptyLayout.setOnDragListener(this);
		valueLayout.setOnDragListener(this);
		starLinearLayout.setOnDragListener(this);
		imageLinearLayout.setOnDragListener(this);
		buttonLayout.setOnDragListener(this);
		dummylayout.setOnDragListener(this);
		

		correctanswer.setOnDragListener(this);
		questionlayout.setOnDragListener(this);

		// rating = (RatingBar) findViewById(R.id.ratingBar1);
		// word = (TextView) findViewById(R.id.textView1);
		// recButton = (ImageButton) findViewById(R.id.imageButton1);
		// recButton.setOnClickListener(this);
		// rating.setIsIndicator(true);
		star1 = (ImageView) findViewById(R.id.star1);
		star2 = (ImageView) findViewById(R.id.star2);
		star3 = (ImageView) findViewById(R.id.star3);
		star4 = (ImageView) findViewById(R.id.star4);
		star5 = (ImageView) findViewById(R.id.star5);
		forward = (ImageView) findViewById(R.id.forwardButton);
		showanswer = (ImageView) findViewById(R.id.showanswer);

		forward.setOnClickListener(this);
		// showanswer.setOnClickListener(this);
		randomGenerator = new Random();
		shownImageset = new HashSet<Integer>();

		answerquestion = (TextView) findViewById(R.id.answerquestion);
		answerText = (TextView) findViewById(R.id.answer);

		imageName = new ArrayList<String>();
		desc = new ArrayList<String>();
		score = new ArrayList<Integer>();
		addingResource();
		ourSong = MediaPlayer.create(SpellActivity.this,
				R.raw.clapping_children);
		wrong = MediaPlayer.create(SpellActivity.this, R.raw.wrong);

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

	@SuppressLint("DefaultLocale")
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.forwardButton:

			if (answerText.getVisibility() != TextView.VISIBLE
					|| showanswer.getVisibility() != ImageButton.VISIBLE) {

				int viewCount = emptyLayout.getChildCount();
				answerquestion.setVisibility(TextView.INVISIBLE);
				answerText.setVisibility(TextView.INVISIBLE);
				int i;
				String resultValue = "";
				for (i = 0; i < viewCount; i++) {
					resultValue += ((TextView) emptyLayout.getChildAt(i))
							.getText().toString();

				}
				
				System.out.println("resulted value " + resultValue);
				System.out.println("answer value " + answer);
				if (resultValue.equalsIgnoreCase(answer)) {
					// System.out.println("final answered value is " +
					// resultValue);
					successRating();
					loadResource();
				} else {

					/*
					 * answerquestion.setVisibility(TextView.VISIBLE);
					 * answerText.setText(answer.toUpperCase());
					 * answerText.setVisibility(TextView.VISIBLE);
					 */
					/*
					 * AlertDialog alert = new
					 * AlertDialog.Builder(SpellActivity.this).create();
					 * alert.setTitle("This is ");
					 * alert.setMessage(answer.toUpperCase()); alert.show();
					 */

					FailureRating();
					
					if ( qCounter == 6)
						speakOut("Please check the spelling and move to score page");					
					else
						speakOut("Please check the spelling and move to next question");			
					
					answerText.setVisibility(TextView.VISIBLE);
					showanswer.setVisibility(ImageView.VISIBLE);
					answerText.setText(answer.toUpperCase());
				}

				// loadResource();
			} else
			{
				if ( qCounter == 6) {
					
					scoreIntent = new Intent("android.intent.action.SCORE");
					scoreIntent.putIntegerArrayListExtra("score", score);
					scoreIntent.putExtra("ActivityName", "SpellActivity");
					startActivity(scoreIntent);
					}
				else 
					loadResource();
				
				
			}
				

			break;

		case R.id.showanswer:

			/*
			 * // answerquestion.setVisibility(TextView.VISIBLE);
			 * System.out.println(" i am here at visibilty is " +
			 * answerText.getVisibility()); if (answerText.getVisibility() ==
			 * TextView.INVISIBLE) { // System.out.println( " visibilty is " +
			 * // answerText.getVisibility() );
			 * answerText.setText(answer.toUpperCase());
			 * answerText.setVisibility(TextView.VISIBLE);
			 * showanswer.setImageResource(R.drawable.hideanswer);
			 * 
			 * } else { answerText.setVisibility(TextView.INVISIBLE);
			 * showanswer.setImageResource(R.drawable.showanswer); }
			 */

			break;

		default:
			break;
		}
	}

	@Override
	public void onDestroy() {
		// Don't forget to shutdown tts!
		/*
		 * if (tts != null) { tts.stop(); tts.shutdown(); }
		 */
		ourSong.release();
		wrong.release();
		super.onDestroy();
		// Toast.makeText(this, "onDestroy", Toast.LENGTH_LONG).show();
	}

	protected void wating() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
				scoreIntent.putExtra("ActivityName", "SpellActivity");
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
			/*
			 * try { System.out.println("Thread is waiting");
			 * Thread.sleep(2000);
			 * 
			 * } catch (InterruptedException e) { // TODO Auto-generated catch
			 * block e.printStackTrace(); }
			 */

		}

		qCounter++;
	}

	@SuppressLint("DefaultLocale")
	protected void loadResource() {

		// answerquestion.setVisibility(TextView.INVISIBLE);
		// answerText.setVisibility(TextView.INVISIBLE);
		// showanswer.setImageResource(R.drawable.showanswer);

		if (answerText.getVisibility() == TextView.VISIBLE
				|| showanswer.getVisibility() == ImageButton.VISIBLE) {
			answerText.setVisibility(TextView.INVISIBLE);
			showanswer.setVisibility(ImageButton.INVISIBLE);

		}
		if (qCounter <= 5) {

			randomImageIndex = randomGenerator.nextInt(imageName.size());

			/* if (qCounter == 1) { */
			if (shownImageset.add(randomImageIndex)) {

				String picName = imageName.get(randomImageIndex);
				answer = desc.get(randomImageIndex);

				lenghtofanswer = answer.length();

				if (lenghtofanswer <= 9) {

					int resID = getResources().getIdentifier(picName,
							"drawable", "com.thanu.readme");
					System.out.println("Setting image " + picName);
					picture.setImageResource(resID);

					shuffleanswer = shuffle(answer).toUpperCase();

					temp = 0;
					System.out.println("Shuffle string is " + shuffleanswer);
					/*
					 * emptyLayout.removeAllViewsInLayout();
					 * valueLayout.removeAllViewsInLayout();
					 */
					if (emptyLayout.getChildCount() >= 1) {
						emptyLayout.removeAllViewsInLayout();
						System.out.println("i am clering the empty layout ");
					}
					if (valueLayout.getChildCount() >= 1) {
						valueLayout.removeAllViewsInLayout();
						System.out.println("i am clering the valueLayout  ");
					}

					while (temp < lenghtofanswer) {
						String temp1 = "" + shuffleanswer.charAt(temp);
						makeEmtyTextView(temp + lenghtofanswer);
						makeValueTextView(temp1, temp);
						temp++;
					}
				}

				else
					loadResource();

				// word.setText(desc.get(randomImageIndex));

			} else
				loadResource();

		}

		// }
		/*
		 * else { String picName = "lion"; int resID =
		 * getResources().getIdentifier(picName, "drawable",
		 * "com.thanu.readme"); picture.setImageResource(resID);
		 * word.setText("apple"); }
		 */
	}

	public String shuffle(String input) {
		List<Character> characters = new ArrayList<Character>();
		for (char c : input.toCharArray()) {
			characters.add(c);
		}
		StringBuilder output = new StringBuilder(input.length());
		while (characters.size() != 0) {
			int randPicker = (int) (Math.random() * characters.size());
			output.append(characters.remove(randPicker));
		}
		return output.toString();
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
			}
			else {
				//speakOut("What is This?");
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
