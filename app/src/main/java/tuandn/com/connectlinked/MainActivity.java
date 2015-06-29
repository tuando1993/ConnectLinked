package tuandn.com.connectlinked;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Utils.Utils;

/**
 * Created by Anh Trung on 6/22/2015.
 */
public class MainActivity extends Activity {
    private Button post;
    private SharedPreferences mSharedPreferences;
    private EditText username;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        mContext = MainActivity.this;

        post = (Button) findViewById(R.id.post);

        displayUsername();
    }

    private void displayUsername() {
    }

    public void postOnWall(View v){

        EditText status = (EditText) findViewById(R.id.et_status);
        String status_content = status.getText().toString();

    }

    public void ShowFriendList(View v){
        if(Utils.isConnectingToInternet(MainActivity.this)){
            Intent i = new Intent(MainActivity.this,DisplayFriendListActivity.class);
            startActivity(i);
        }
        else {
            Toast.makeText(getApplicationContext(),"You are not connecting to the Internet",Toast.LENGTH_LONG).show();
        }
    }
}

