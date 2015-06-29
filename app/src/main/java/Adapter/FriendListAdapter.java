package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.imageloader.ImageLoader;

import java.util.ArrayList;

import Model.Friend;
import tuandn.com.connectlinked.R;

/**
 * Created by Anh Trung on 6/24/2015.
 */
public class FriendListAdapter extends ArrayAdapter<Friend> {

    private Context context;
    private final ArrayList<Friend> mList;
    private ImageLoader imageLoader;

    public FriendListAdapter(Context context, ArrayList<Friend> mListFriend){
        super(context, R.layout.friend_list, mListFriend);
        this.context = context;
        this.mList = mListFriend;
    }

    static class ViewHolder {
        TextView name;
        ImageView userImage;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final ViewHolder holder;
        if(convertView == null){
            convertView = inflater.from(context).inflate(tuandn.com.connectlinked.R.layout.friend_list,null);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.friend_name);
            holder.userImage = (ImageView) convertView.findViewById(R.id.imageID);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        //Set Friend's name
        holder.name.setText(mList.get(position).getName());

        //Set Friend Image
        ImageLoader.Callback callback = new ImageLoader.Callback() {
            @Override
            public void onImageLoaded(ImageView imageView, String s) {
                holder.userImage = imageView;
            }

            @Override
            public void onImageError(ImageView imageView, String s, Throwable throwable) {

            }
        };

        imageLoader = new ImageLoader();
        imageLoader.bind(holder.userImage,mList.get(position).getImage(),callback);
        return convertView;
    }

}
