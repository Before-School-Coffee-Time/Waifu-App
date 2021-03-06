package bsct.waifu_app;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void testButton(View v) {
        TextView text = (TextView) findViewById(R.id.textbox1);

        if(text.getText().equals("As you can see there is a background, textview, and character currently implemented"))
            text.setText(R.string.followup2);
        else if(text.getText().equals("Welcome to the early Waifu App. Please click to continue."))
            text.setText(R.string.followup1);
    }

    public void poke(View V) {
        TextView text = (TextView) findViewById(R.id.textbox1);

        SharedPreferences prefs = getSharedPreferences("WaifuApp", MODE_PRIVATE);

        long currentTime = System.currentTimeMillis();
        long previousPoke = prefs.getLong("PokeTime", -1);

        System.out.println(previousPoke + " and " + currentTime);

        if(previousPoke != -1){
            long timeDifference = currentTime - previousPoke;
            timeDifference = timeDifference /1000;
            int seconds = (int)timeDifference % 60;
            int minutes = (int)timeDifference / 60;
            text.setText("It's been " + minutes + " minutes and " + seconds + " seconds since you last poked me");
        }
        else {
            text.setText(R.string.poked);
        }
        prefs.edit().putLong("PokeTime", currentTime).apply();
    }
}
