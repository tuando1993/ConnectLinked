package tuandn.com.connectlinked;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.linkedin.platform.APIHelper;
import com.linkedin.platform.LISession;
import com.linkedin.platform.LISessionManager;
import com.linkedin.platform.errors.LIApiError;
import com.linkedin.platform.listeners.ApiListener;
import com.linkedin.platform.listeners.ApiResponse;

import org.json.JSONObject;

import Utils.Utils;

/**
 * Created by Anh Trung on 6/22/2015.
 */
public class MainActivity extends Activity {
    private Button post;
    private SharedPreferences mSharedPreferences;
    private TextView username;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        mContext = MainActivity.this;
        username = (TextView) findViewById(R.id.user_name);

        post = (Button) findViewById(R.id.post);

        displayUsername();
    }

    private void displayUsername() {
        LISession liSession = LISessionManager.getInstance(MainActivity.this).getSession();

        String url = "https://api.linkedin.com/v1/people/~:(id,first-name,last-name)";

        APIHelper apiHelper = APIHelper.getInstance(getApplicationContext());
        apiHelper.getRequest(this, url, new ApiListener() {
            @Override
            public void onApiSuccess(ApiResponse apiResponse) {
                System.out.println(apiResponse.toString());
                try{
                    JSONObject object = apiResponse.getResponseDataAsJson();
                    username.setText("Hello, "+object.getString("firstName") +" "+ object.getString("lastName"));
                }
                catch (Exception e){

                }
            }

            @Override
            public void onApiError(LIApiError LIApiError) {
                Toast.makeText(getApplication(),"Error",Toast.LENGTH_LONG).show();
            }
        });
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

