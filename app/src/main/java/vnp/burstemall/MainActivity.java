package vnp.burstemall;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.transition.AutoTransition;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import vnp.burstemall.utilities.MyAlertDialog;
import vnp.burstemall.utilities.StoreHighScore;
import vnp.burstemall.utilities.SoundHelper;

/**
 * Created by pkapo8 on 9/28/2016.
 */

public class MainActivity extends AppCompatActivity implements FlyingObject.FlyingObjectListener {

    private static final int BALLOONS_PER_LEVEL = 18;
    private static final int NUMBER_OF_PINS = 5;

    private static final int MIN_ANIMATION_DELAY = 500;
    private static final int MAX_ANIMATION_DELAY = 1500;
    private static final int MIN_ANIMATION_DURATION = 1000;
    private static final int MAX_ANIMATION_DURATION = 8000;
    private static final String ACTION_NEXT_LEVEL = "action_next_level";
    private static final String ACTION_RESTART_GAME = "action_restart_game";

    private ViewGroup mContentView;
    private SoundHelper mSoundHelper;
    private List<ImageView> mPinImages = new ArrayList<>();
    private List<Integer> mBackgroundImages = new ArrayList<>();

    private List<FlyingObject> mBalloons = new ArrayList<>();
    private TextView score_display_text, music, mLevelDisplay, levelUp,
            max_level_value, high_score_value, high_score, Max_Level,
            level_label,score_label, choose_Objects;
    ImageView mGoButton;
    private String mNextAction = ACTION_RESTART_GAME;
    private boolean mPlaying;
    private RelativeLayout rlTopScore;
    private LinearLayout livesRemainingLayout;
    private int[] mBalloonColors = new int[18];
    private int mNextColor, mBalloonsPopped,
            mScreenWidth, mScreenHeight,
            mPinsUsed = 0,
            mScore = 0, mLevel = 1;
    private SwitchCompat music_on_off;
    private Animation tickmarkZoomIn, tickmarkzoomOutWithBounce;
    private boolean isMusicTurned;
    private int defaultObject = R.drawable.aa;
    private ArrayList<Integer> listObjects = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setBackgroundDrawableResource(R.drawable.back_one);

//      Load the activity layout, which is an empty canvas
        setContentView(R.layout.activity_main);

//      Get background reference.
        mContentView = (ViewGroup) findViewById(R.id.content_view);
        if (mContentView == null) throw new AssertionError();
        mContentView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    setToFullScreen();
                }
                return false;
            }
        });
        setToFullScreen();

        mGoButton = (ImageView) findViewById(R.id.go_button);
        max_level_value = (TextView) findViewById(R.id.max_level_score);
        choose_Objects = (TextView) findViewById(R.id.choose_Objects);
        high_score_value = (TextView) findViewById(R.id.high_score_value);
        score_display_text = (TextView) findViewById(R.id.score_display_text);
        music = (TextView) findViewById(R.id.music);
        mLevelDisplay = (TextView) findViewById(R.id.level_display);
        levelUp = (TextView) findViewById(R.id.levelUp);
        level_label = (TextView) findViewById(R.id.level_label);
        score_label = (TextView) findViewById(R.id.score_label);
        livesRemainingLayout = (LinearLayout) findViewById(R.id.livesRemainingLayout);
        high_score = (TextView) findViewById(R.id.high_score);
        Max_Level = (TextView) findViewById(R.id.Max_Level);
        rlTopScore = (RelativeLayout) findViewById(R.id.rlTopScore);
        music_on_off = (SwitchCompat) findViewById(R.id.music_on_off);

        Typeface typeHeading1 = Typeface.createFromAsset(getAssets(), "fonts/Montague.ttf");
        setTypefaceColor(typeHeading1,R.color.text_red);
//50
        mBackgroundImages.add(R.drawable.one);
        mBackgroundImages.add(R.drawable.two);
        mBackgroundImages.add(R.drawable.three);
        mBackgroundImages.add(R.drawable.four);
        mBackgroundImages.add(R.drawable.five);
        mBackgroundImages.add(R.drawable.six);
        mBackgroundImages.add(R.drawable.eight);
        mBackgroundImages.add(R.drawable.nine);
        mBackgroundImages.add(R.drawable.ten);
        mBackgroundImages.add(R.drawable.one);
        mBackgroundImages.add(R.drawable.two);
        mBackgroundImages.add(R.drawable.three);
        mBackgroundImages.add(R.drawable.four);
        mBackgroundImages.add(R.drawable.five);
        mBackgroundImages.add(R.drawable.six);
        mBackgroundImages.add(R.drawable.eight);
        mBackgroundImages.add(R.drawable.nine);
        mBackgroundImages.add(R.drawable.ten);
        mBackgroundImages.add(R.drawable.one);
        mBackgroundImages.add(R.drawable.two);
        mBackgroundImages.add(R.drawable.three);
        mBackgroundImages.add(R.drawable.four);
        mBackgroundImages.add(R.drawable.five);
        mBackgroundImages.add(R.drawable.six);
        mBackgroundImages.add(R.drawable.eight);
        mBackgroundImages.add(R.drawable.nine);
        mBackgroundImages.add(R.drawable.ten);
        mBackgroundImages.add(R.drawable.one);
        mBackgroundImages.add(R.drawable.two);
        mBackgroundImages.add(R.drawable.three);
        mBackgroundImages.add(R.drawable.four);
        mBackgroundImages.add(R.drawable.five);
        mBackgroundImages.add(R.drawable.six);
        mBackgroundImages.add(R.drawable.eight);
        mBackgroundImages.add(R.drawable.nine);
        mBackgroundImages.add(R.drawable.ten);
        mBackgroundImages.add(R.drawable.one);
        mBackgroundImages.add(R.drawable.two);
        mBackgroundImages.add(R.drawable.three);
        mBackgroundImages.add(R.drawable.four);
        mBackgroundImages.add(R.drawable.five);
        mBackgroundImages.add(R.drawable.six);
        mBackgroundImages.add(R.drawable.eight);
        mBackgroundImages.add(R.drawable.nine);
        mBackgroundImages.add(R.drawable.ten);
        mBackgroundImages.add(R.drawable.one);
        mBackgroundImages.add(R.drawable.two);
        mBackgroundImages.add(R.drawable.three);
        mBackgroundImages.add(R.drawable.four);
        mBackgroundImages.add(R.drawable.five);






        listObjects.add(R.drawable.balloon);
        listObjects.add(R.drawable.aa);
        listObjects.add(R.drawable.alien);
        listObjects.add(R.drawable.football);
        listObjects.add(R.drawable.tennis);
        listObjects.add(R.drawable.balloon);
        listObjects.add(R.drawable.aa);
        listObjects.add(R.drawable.alien);
        listObjects.add(R.drawable.football);
        listObjects.add(R.drawable.tennis);
        listObjects.add(R.drawable.balloon);
        listObjects.add(R.drawable.aa);
        listObjects.add(R.drawable.alien);
        listObjects.add(R.drawable.football);
        listObjects.add(R.drawable.tennis);
        listObjects.add(R.drawable.balloon);
        listObjects.add(R.drawable.aa);
        listObjects.add(R.drawable.alien);
        listObjects.add(R.drawable.football);
        listObjects.add(R.drawable.tennis);
        listObjects.add(R.drawable.balloon);
        listObjects.add(R.drawable.aa);
        listObjects.add(R.drawable.alien);
        listObjects.add(R.drawable.football);
        listObjects.add(R.drawable.tennis);
        listObjects.add(R.drawable.balloon);
        listObjects.add(R.drawable.aa);
        listObjects.add(R.drawable.alien);
        listObjects.add(R.drawable.football);
        listObjects.add(R.drawable.tennis);
        listObjects.add(R.drawable.balloon);
        listObjects.add(R.drawable.aa);
        listObjects.add(R.drawable.alien);
        listObjects.add(R.drawable.football);
        listObjects.add(R.drawable.tennis);
        listObjects.add(R.drawable.balloon);
        listObjects.add(R.drawable.aa);
        listObjects.add(R.drawable.alien);
        listObjects.add(R.drawable.football);
        listObjects.add(R.drawable.tennis);
        listObjects.add(R.drawable.balloon);
        listObjects.add(R.drawable.aa);
        listObjects.add(R.drawable.alien);
        listObjects.add(R.drawable.football);
        listObjects.add(R.drawable.tennis);
        listObjects.add(R.drawable.football);
        listObjects.add(R.drawable.tennis);
        listObjects.add(R.drawable.balloon);
        listObjects.add(R.drawable.aa);
        listObjects.add(R.drawable.alien);
        listObjects.add(R.drawable.football);


        ViewTreeObserver viewTreeObserver = mContentView.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        mContentView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                    mScreenWidth = mContentView.getWidth();
                    mScreenHeight = mContentView.getHeight();
                }
            });
        }

//      Initialize sound helper class that wraps SoundPool for audio effects
        mSoundHelper = new SoundHelper(this);
        mSoundHelper.prepareMusicPlayer(this);

//      Initialize display elements
        mPinImages.add((ImageView) findViewById(R.id.pushpin1));
        mPinImages.add((ImageView) findViewById(R.id.pushpin2));
        mPinImages.add((ImageView) findViewById(R.id.pushpin3));
        mPinImages.add((ImageView) findViewById(R.id.pushpin4));
        mPinImages.add((ImageView) findViewById(R.id.pushpin5));

        livesRemainingLayout.setVisibility(View.GONE);
        rlTopScore.setVisibility(View.GONE);
        max_level_value.setText(String.valueOf(StoreHighScore.getMostLevels(this)));
        high_score_value.setText(String.valueOf(StoreHighScore.getTopScore(this)));
        music_on_off.setChecked(StoreHighScore.getMusicValue(this));
        //setAnimation();
//      Display current level and score
        updateDisplay();

//      Initialize balloon colors: red, white and blue
        mBalloonColors[0] = Color.parseColor("#C10805");
        mBalloonColors[1] = Color.parseColor("#FF5733");
        mBalloonColors[2] = Color.parseColor("#FFC300");

        mBalloonColors[3] = Color.parseColor("#FF7646");
        mBalloonColors[4] = Color.parseColor("#830AF4");
        mBalloonColors[5] = Color.parseColor("#C7FF08");
        mBalloonColors[6] = Color.parseColor("#53EF00");
        mBalloonColors[7] = Color.parseColor("#00F7BB");
        mBalloonColors[8] = Color.parseColor("#1877F9");
        mBalloonColors[9] = Color.parseColor("#0AE2F4");
        mBalloonColors[10] = Color.parseColor("#FFE908");
        mBalloonColors[11] = Color.parseColor("#CF3AF0");
        mBalloonColors[12] = Color.parseColor("#FB57B3");
        mBalloonColors[13] = Color.parseColor("#FB5757");
        mBalloonColors[14] = Color.parseColor("#105698");

        mBalloonColors[15] = Color.parseColor("#13A990");
        mBalloonColors[16] = Color.parseColor("#FF9326");
        mBalloonColors[17] = Color.parseColor("#FF8FE2");


//      Handle button click
        if (mGoButton == null) throw new AssertionError();
        mGoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPlaying) {
                    stopGame();
                } else {
                    switch (mNextAction) {
                        case ACTION_RESTART_GAME:
                            startGame();
                            break;
                        case ACTION_NEXT_LEVEL:
                            startLevel();
                            break;
                    }
                }
            }
        });
        isMusicTurned = StoreHighScore.getMusicValue(this);
        music_on_off.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                StoreHighScore.setMusicValue(getApplicationContext(),isChecked);
                isMusicTurned = isChecked;
            }
        });
        choose_Objects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ObjectOptions.class);
                startActivity(intent);

            }
        });

        if(getIntent()!=null){
            defaultObject = getIntent().getIntExtra("object",R.drawable.balloon);
        }
    }

    private void setTypefaceColor(Typeface typeHeading1, int colorPrimaryDark) {

        Max_Level.setTypeface(typeHeading1);
        max_level_value.setTypeface(typeHeading1);
        choose_Objects.setTypeface(typeHeading1);
        high_score.setTypeface(typeHeading1);
        high_score_value.setTypeface(typeHeading1);

        score_display_text.setTypeface(typeHeading1);
        music.setTypeface(typeHeading1);
        mLevelDisplay.setTypeface(typeHeading1);
        level_label.setTypeface(typeHeading1);
        score_label.setTypeface(typeHeading1);

        levelUp.setTypeface(typeHeading1);

        Max_Level.setTextColor(ContextCompat.getColor(this,colorPrimaryDark));
        max_level_value.setTextColor(ContextCompat.getColor(this,colorPrimaryDark));
        choose_Objects.setTextColor(ContextCompat.getColor(this,colorPrimaryDark));
        high_score_value.setTextColor(ContextCompat.getColor(this,colorPrimaryDark));
        mLevelDisplay.setTextColor(ContextCompat.getColor(this,colorPrimaryDark));
        level_label.setTextColor(ContextCompat.getColor(this,colorPrimaryDark));
        score_label.setTextColor(ContextCompat.getColor(this,colorPrimaryDark));
        high_score.setTextColor(ContextCompat.getColor(this,colorPrimaryDark));
        score_display_text.setTextColor(ContextCompat.getColor(this,colorPrimaryDark));
        music.setTextColor(ContextCompat.getColor(this,colorPrimaryDark));
    }

    @Override
    protected void onResume() {
        super.onResume();
        setToFullScreen();
    }

    @Override
    public void onBackPressed() {
        if(mPlaying) {
            setExitDialog();
        }else {
            super.onBackPressed();
        }
    }

    private void setExitDialog() {
        final Dialog dialog = new Dialog(this);

        setFinishOnTouchOutside(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.exit_confirmation);
        setToFullScreen();

        Button btn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);
        Button btn_confirm = (Button) dialog.findViewById(R.id.btn_confirm);

        TextView tvNotesViewText = (TextView) dialog.findViewById(R.id.tvNotesViewText);
        Typeface typeHeading1 = Typeface.createFromAsset(getAssets(), "fonts/Montague.ttf");

        tvNotesViewText.setTypeface(typeHeading1);
        btn_cancel.setTypeface(typeHeading1);
        btn_confirm.setTypeface(typeHeading1);

        tvNotesViewText.setTextColor(ContextCompat.getColor(this,R.color.text_red));
        btn_cancel.setTextColor(ContextCompat.getColor(this,R.color.text_red));
        btn_confirm.setTextColor(ContextCompat.getColor(this,R.color.text_red));

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setToFullScreen();
                dialog.dismiss();
                if(mPlaying) {
                    stopGame();
                }else {
                    finish();
                }
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //stopGame();
                setToFullScreen();
                dialog.dismiss();
                //finish();
            }
        });
        dialog.show();
    }

    private void setToFullScreen() {

        //      Set full screen mode
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    private void startGame() {

        setToFullScreen();
        livesRemainingLayout.setVisibility(View.VISIBLE);
        rlTopScore.setVisibility(View.VISIBLE);
        mGoButton.setVisibility(View.GONE);
        max_level_value.setVisibility(View.GONE);
        choose_Objects.setVisibility(View.GONE);
        music_on_off.setVisibility(View.GONE);
        music.setVisibility(View.GONE);
        high_score_value.setVisibility(View.GONE);
        high_score.setVisibility(View.GONE);
        Max_Level.setVisibility(View.GONE);

        getWindow().setBackgroundDrawableResource(R.drawable.back_one);

//      Reset score and level
        mScore = 0;
        mLevel = 1;

//      Update display
        updateDisplay();

//      Reset pins
        mPinsUsed = 0;
        for (ImageView pin : mPinImages) {
            pin.setImageResource(R.drawable.ic_favorite_black_24dp);
        }

//      Start the first level
       /* levelUp.setVisibility(View.VISIBLE);
        levelUp.setText("LEVEL 1 !!");
        levelUp.animate()
                .alpha(0.0f)
                .setDuration(1800).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                levelUp.setAlpha(1.0f);
                levelUp.setVisibility(View.GONE);
                startLevel();
            }
        });*/
        startLevel();
        if (isMusicTurned) {
            mSoundHelper.playMusic();
        }else{
            mSoundHelper.stopMusic();
        }
    }

    private void stopGame() {
        mPlaying = false;
        gameOver(false);
    }

    private void startLevel() {

//      Display the current level and score
        updateDisplay();

//      Reset flags for new level
        mPlaying = true;
        mBalloonsPopped = 0;

//      integer arg for BalloonLauncher indicates the level
        BalloonLauncher mLauncher = new BalloonLauncher();
        mLauncher.execute(mLevel);

    }

    private void finishLevel() {
        StoreHighScore.setCurrentScore(this, mScore);
        StoreHighScore.setCurrentLevel(this, mLevel);
        levelUp.setVisibility(View.VISIBLE);
        int levelToShow = mLevel+1;
        levelUp.setText("LEVEL " + levelToShow + " !!");
        mPlaying = false;
        mLevel++;

        mNextAction = ACTION_NEXT_LEVEL;
        levelUp.animate()
                .alpha(0.0f)
                .setDuration(1800).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                levelUp.setAlpha(1.0f);
                levelUp.setVisibility(View.GONE);
                getWindow().setBackgroundDrawableResource(mBackgroundImages.get(mLevel));
                startLevel();
            }
        });

    }

    private void updateDisplay() {
        score_display_text.setText(String.valueOf(mScore));
        mLevelDisplay.setText(String.valueOf(mLevel));
    }

    private void launchBalloon(int x) {

//      Balloon is launched from activity upon progress update from the AsyncTask
//      Create new imageview and set its tint color
        FlyingObject balloon = new FlyingObject(this, mBalloonColors[mNextColor], 150, mLevel, listObjects.get(mLevel-1));
        mBalloons.add(balloon);

//      Reset color for next balloon
        if (mNextColor + 1 == mBalloonColors.length) {
            mNextColor = 0;
        } else {
            mNextColor++;
        }

//      Set balloon vertical position and dimensions, add to container
        balloon.setX(x);
        balloon.setY(mScreenHeight + balloon.getHeight());
        mContentView.addView(balloon);

        int duration = Math.max(MIN_ANIMATION_DURATION, MAX_ANIMATION_DURATION - (mLevel * 1000));
        balloon.releaseBalloon(mScreenHeight, duration);

    }

    @Override
    public void popBalloon(FlyingObject balloon, boolean userTouch) {

//      Play sound, make balloon go away
        if (isMusicTurned) {
            mSoundHelper.playSound(balloon);
        }
        mContentView.removeView(balloon);
        mBalloons.remove(balloon);
        mBalloonsPopped++;

        if (userTouch) {
            mScore++;
        } else {
            mPinsUsed++;
            if (mPinsUsed <= mPinImages.size()) {
                mPinImages.get(mPinsUsed - 1)
                        .setImageResource(R.drawable.ic_favorite_border_black_24dp);
            }
            if (mPinsUsed == NUMBER_OF_PINS) {
                gameOver(true);

                return;
            }
        }
        updateDisplay();
        if (mBalloonsPopped == BALLOONS_PER_LEVEL) {
            finishLevel();
        }
    }

    private void gameOver(boolean allPinsUsed) {
        if(isMusicTurned) {
            mSoundHelper.stopMusic();
        }
//      Clean up balloons
        for (FlyingObject balloon : mBalloons) {
            balloon.setPopped(true);
        }

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (FlyingObject balloon : mBalloons) {
                    mContentView.removeView(balloon);

                }
                mBalloons.clear();
            }
        }, 500);

//      Reset for a new game
        mPlaying = false;
        mPinsUsed = 0;
        mNextAction = ACTION_RESTART_GAME;
        livesRemainingLayout.setVisibility(View.GONE);
        rlTopScore.setVisibility(View.GONE);
        mGoButton.setVisibility(View.VISIBLE);

        if (allPinsUsed) {
//          Manage high score locally
            if (StoreHighScore.isTopScore(this, mScore)) {
                String message = String.format(getString(R.string.your_top_score_is), mScore);
                StoreHighScore.setTopScore(this, mScore);
             /*   MyAlertDialog dialog = MyAlertDialog.newInstance(
                        getString(R.string.new_top_score),
                        message);
                dialog.show(getSupportFragmentManager(), null);*/
            }

            int completedLevel = mLevel - 1;
            if (StoreHighScore.isMostLevels(this, completedLevel)) {
                StoreHighScore.setPrefMostLevels(this, completedLevel);
              /*  MyAlertDialog dialog = MyAlertDialog.newInstance(
                        getString(R.string.more_levels_than_ever),
                        String.format(getString(R.string.you_completed_n_levels), completedLevel));
                dialog.show(getSupportFragmentManager(), null);*/
            }
        }
        max_level_value.setText(String.valueOf(StoreHighScore.getMostLevels(this)));
        high_score_value.setText(String.valueOf(StoreHighScore.getTopScore(this)));
        setDialog(mScore, mLevel - 1);

        //setAnimation();

    }

    private void setDialog(int mScore, int level) {
        final Dialog dialog = new Dialog(this);

        setFinishOnTouchOutside(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.custom_confirmation_dialog);
        setToFullScreen();

        Button btn_confirm = (Button) dialog.findViewById(R.id.btn_confirm);
        TextView tvScore_value = (TextView) dialog.findViewById(R.id.tvScore_value);
        TextView tvLevel_value = (TextView) dialog.findViewById(R.id.tvLevel_value);

        TextView tvScore = (TextView) dialog.findViewById(R.id.tvScore);
        TextView tvLevel = (TextView) dialog.findViewById(R.id.tvLevel);

        Typeface typeHeading1 = Typeface.createFromAsset(getAssets(), "fonts/Montague.ttf");

        tvScore_value.setTypeface(typeHeading1);
        tvLevel_value.setTypeface(typeHeading1);
        tvScore.setTypeface(typeHeading1);
        tvLevel.setTypeface(typeHeading1);
        btn_confirm.setTypeface(typeHeading1);

        tvScore_value.setTextColor(ContextCompat.getColor(this,R.color.text_red));
        tvLevel_value.setTextColor(ContextCompat.getColor(this,R.color.text_red));
        tvScore.setTextColor(ContextCompat.getColor(this,R.color.text_red));
        tvLevel.setTextColor(ContextCompat.getColor(this,R.color.text_red));
        btn_confirm.setTextColor(ContextCompat.getColor(this,R.color.text_red));


        tvScore_value.setText(String.valueOf(mScore));
        tvLevel_value.setText(String.valueOf(level));

       btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                max_level_value.setVisibility(View.VISIBLE);
                choose_Objects.setVisibility(View.VISIBLE);
                music_on_off.setVisibility(View.VISIBLE);
                music.setVisibility(View.VISIBLE);
                high_score_value.setVisibility(View.VISIBLE);
                high_score.setVisibility(View.VISIBLE);
                Max_Level.setVisibility(View.VISIBLE);
                dialog.dismiss();
                setToFullScreen();
            }
        });
        dialog.show();
    }

    private class BalloonLauncher extends AsyncTask<Integer, Integer, Void> {

        @Override
        protected Void doInBackground(Integer... params) {

            if (params.length != 1) {
                throw new AssertionError(
                        "Expected 1 param for current level");
            }

            int level = params[0];

//          level 1 = max delay; each ensuing level reduces delay by 500 ms
//            min delay is 250 ms
            int maxDelay,minDelay;
            if(level == 1) {
                 maxDelay = 1150;
                 minDelay = maxDelay / 2;
            }else{
                 maxDelay = Math.max(MIN_ANIMATION_DELAY, (MAX_ANIMATION_DELAY - ((level - 1) * 200)));
                 minDelay = maxDelay / 2;
            }
//          Keep on launching balloons until either
//              1) we run out or 2) the mPlaying flag is set to false
            int balloonsLaunched = 0;
            while (mPlaying && balloonsLaunched < BALLOONS_PER_LEVEL) {

//              Get a random horizontal position for the next balloon
                Random random = new Random(new Date().getTime());
                int xPosition = random.nextInt(mScreenWidth - 200);
                publishProgress(xPosition);
                balloonsLaunched++;

//              Wait a random number of milliseconds before looping
                int delay = random.nextInt(minDelay) + minDelay;
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return null;

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
//          This runs on the UI thread, so we can launch a balloon
//            at the randomized horizontal position
            int xPosition = values[0];
            launchBalloon(xPosition);
        }
    }
}
