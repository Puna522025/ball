package vnp.burstemall;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

/**
 * Created by pkapo8 on 10/6/2016.
 */

public class ObjectOptions extends AppCompatActivity implements OnItemClick {
    RelativeLayout rlBackground;
    RecyclerView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.object_list);

        getWindow().setBackgroundDrawableResource(R.drawable.back_one);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rlBackground = (RelativeLayout) findViewById(R.id.rlBackground);
        setToFullScreen();
        listView = (RecyclerView) findViewById(R.id.listView);
        listView.setLayoutManager(layoutManager);

        ObjectInfo itemsData[] = {new ObjectInfo("aa", R.drawable.aa),
                new ObjectInfo("balloon", R.drawable.balloon),
                new ObjectInfo("alien", R.drawable.alien),
                new ObjectInfo("football", R.drawable.football),
                new ObjectInfo("tennis", R.drawable.tennis)};

        CustomAdapter customAdapter = new CustomAdapter(itemsData, getApplicationContext(), this);
        listView.setAdapter(customAdapter);
        listView.setItemAnimator(new DefaultItemAnimator());


        if (rlBackground == null) throw new AssertionError();
        rlBackground.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    setToFullScreen();
                }
                return false;
            }
        });

    }

    private void setToFullScreen() {
        //      Set full screen mode
        rlBackground.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setToFullScreen();
    }

    @Override
    public void onItemClickListener(int position, int imageUrl) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("object", imageUrl);
        intent.putExtra("position", position);
        startActivity(intent);
        // finish();
    }
}
