package tuandn.com.connectlinked;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.linkedin.platform.APIHelper;
import com.linkedin.platform.errors.LIApiError;
import com.linkedin.platform.listeners.ApiListener;
import com.linkedin.platform.listeners.ApiResponse;

import org.json.JSONObject;

import java.util.ArrayList;

import Model.Friend;

/**
 * Created by Anh Trung on 6/24/2015.
 */
public class DisplayFriendListActivity extends ListActivity {


    private ArrayList<Friend> friendList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Display Friend List
        displayFriendList();
    }


    private void displayFriendList() {

        friendList = new ArrayList<Friend>();

        String url = "https://api.linkedin.com/v1/people/~/connections";

        APIHelper apiHelper = APIHelper.getInstance(getApplicationContext());
        apiHelper.getRequest(this, url, new ApiListener() {
            @Override
            public void onApiSuccess(ApiResponse apiResponse) {
                System.out.println(apiResponse.toString());
                try{
                    JSONObject object = apiResponse.getResponseDataAsJson();
                }
                catch (Exception e){
                }
            }

            @Override
            public void onApiError(LIApiError LIApiError) {
                Toast.makeText(getApplication(), LIApiError.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

}