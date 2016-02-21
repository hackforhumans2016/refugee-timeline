package de.hackforhumans.refugeetimeline.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import de.hackforhumans.refugeetimeline.R;
import de.hackforhumans.refugeetimeline.activity.TimelineActivity;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        WebView introduction = (WebView) findViewById(R.id.introductionTxt);

        String introText = "You just arrived to Germany and do not know which steps to take next? You would like to get a residence permit, start a language course, or apply for a job?<br>\n" +
                " This app shows you a timeline with the tasks you need to accomplish in order to reach certain goals. It is meant to give you a quick overview of the necessary procedures to reach these goals and shall provide access to further resources.<br>\n" +
                " First, select your goal in the 'Select Goal' menu. Then, in the timeline, click on any task for more detailed information and mark it as finished if you fulfilled it (enter the proper date if necessary). All dates for tasks in the future are of course not accurate, but calculated based on the estimated duration of certain processes, fixed deadlines, etc.<br>\n" +
                " <br>\n" +
                " A lot of additional work is needed for this App to be truly useful. Most importantly, more detailed content needs to be provided, ideally with localized information (the App is prepared to show information tailored to your federal state or city of residence).<br>\n" +
                "\n" +
                "Check out <a href=\"http://refugee-timeline.launchrock.com\">refugee-timeline.launchrock.com</a> for details.";

        introduction.loadData(introText, "text/html", null);

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        if(sharedPref.getBoolean("started", false)){
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setClass(getApplicationContext(), TimelineActivity.class);
            startActivity(intent);
        }



        final Button btnStart = (Button)findViewById(R.id.startBtn);
        btnStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                /*SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("started", true);
                editor.commit();*/

                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setClass(StartActivity.this, TimelineActivity.class);
                startActivity(intent);

            }
        });
    }

}
