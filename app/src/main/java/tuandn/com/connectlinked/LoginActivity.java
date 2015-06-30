package tuandn.com.connectlinked;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.linkedin.platform.AccessToken;
import com.linkedin.platform.LISession;
import com.linkedin.platform.LISessionManager;
import com.linkedin.platform.errors.LIAuthError;
import com.linkedin.platform.listeners.AuthListener;
import com.linkedin.platform.utils.Scope;

public class LoginActivity extends Activity {


    private SharedPreferences mSharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
    }

    // Build the list of member permissions our LinkedIn session requires
    private static Scope buildScope() {
        return Scope.build(Scope.R_BASICPROFILE, Scope.W_SHARE);
    }

    public void Login(View v){

        LISession liSession = LISessionManager.getInstance(LoginActivity.this).getSession();
        AccessToken accessToken= liSession.getAccessToken();

        if (accessToken != null) {
//            Start Main Activity
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
            else{
            LISessionManager.getInstance(getApplicationContext()).init(LoginActivity.this, buildScope(), new AuthListener() {
                @Override
                public void onAuthSuccess() {
                    // Authentication was successful.  You can now do
                    // other calls with the SDK.
                    Toast.makeText(getApplication(), "Success", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }

                @Override
                public void onAuthError(LIAuthError error) {
                    Toast.makeText(getApplication(), error.toString(), Toast.LENGTH_LONG).show();
                    System.out.println(error.toString());
                }
            }, true);
//                    Start Main Activity
                }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Add this line to your existing onActivityResult() method
        LISessionManager.getInstance(LoginActivity.this).onActivityResult(this, requestCode, resultCode, data);
    }
}
