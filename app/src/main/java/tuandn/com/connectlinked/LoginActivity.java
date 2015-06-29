package tuandn.com.connectlinked;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.linkedin.platform.AccessToken;
import com.linkedin.platform.LISession;
import com.linkedin.platform.LISessionManager;
import com.linkedin.platform.errors.LIAuthError;
import com.linkedin.platform.listeners.AuthListener;
import com.linkedin.platform.utils.Scope;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends Activity {


    private SharedPreferences mSharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        Login();

    }

    // Build the list of member permissions our LinkedIn session requires
    private static Scope buildScope() {
        return Scope.build(Scope.R_BASICPROFILE, Scope.W_SHARE);
    }

    private void Login(){


        LISession liSession = LISessionManager.getInstance(LoginActivity.this).getSession();
        AccessToken accessToken= liSession.getAccessToken();

        if (accessToken != null) {
//            Start Main Activity
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
            else{

            //Add Permissions
            List<String> scope = new ArrayList<String>();
            scope.add("r_basicprofile");
            scope.add("w_share");

            LISessionManager.getInstance(getApplicationContext()).init(LoginActivity.this, buildScope(), new AuthListener() {
                @Override
                public void onAuthSuccess() {
                    // Authentication was successful.  You can now do
                    // other calls with the SDK.
                    Toast.makeText(getApplication(), "Success", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onAuthError(LIAuthError error) {
                    Toast.makeText(getApplication(), error.toString(), Toast.LENGTH_LONG).show();
                    System.out.println(error.toString());
                }
            }, true);
//                    Start Main Activity
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
    }

//        String url = "https://api.linkedin.com/v1/people/~:(id,first-name,last-name)";
//
//        APIHelper apiHelper = APIHelper.getInstance(getApplicationContext());
//        apiHelper.getRequest(this, url, new ApiListener() {
//            @Override
//            public void onApiSuccess(ApiResponse apiResponse) {
//                Toast.makeText(getApplication(),"Success",Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onApiError(LIApiError LIApiError) {
//                Toast.makeText(getApplication(),"Error",Toast.LENGTH_LONG).show();
//            }
//        });


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Add this line to your existing onActivityResult() method
        LISessionManager.getInstance(LoginActivity.this).onActivityResult(this, requestCode, resultCode, data);
    }
}
