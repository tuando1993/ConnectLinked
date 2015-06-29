package tuandn.com.connectlinked;

import android.app.ListActivity;
import android.os.Bundle;

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

    }

}