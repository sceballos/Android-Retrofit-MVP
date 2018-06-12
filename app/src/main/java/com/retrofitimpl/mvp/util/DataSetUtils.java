package com.retrofitimpl.mvp.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.model.LatLng;
import com.retrofitimpl.mvp.R;
import com.retrofitimpl.mvp.data.user.RandomUser;
import com.retrofitimpl.mvp.data.user.UserLocation;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DataSetUtils {

    /**
     *
     * set data from RandomUser object to View and set upper case names
     *
     * */

    public static void convertDateFormatAndSetData(Context context, RandomUser user, TextView target) {
        SimpleDateFormat serverDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        SimpleDateFormat desiredFormat = new SimpleDateFormat("MMMM dd, yyyy", Locale.US);
        Date serverDate = null;

        try {
            serverDate = serverDateFormat.parse(user.getRegisteredDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String dateToSetStr = desiredFormat.format(serverDate);
        String memberSinceStr = context.getResources().getString(R.string.member_since_string)
                + " : " + dateToSetStr;

        target.setText(memberSinceStr);
    }

    /**
     *
     * set data from RandomUser object to View and set upper case names
     *
     * */

    public static void setNameAndLocationHintDataToView(Context context, RandomUser user,
                                                        TextView targetUserName, TextView targetLocationHint) {
        String capitalizedFirstName = stringToUpperCase(user.getUserName().getFirst());

        String capitalizedLastName = stringToUpperCase(user.getUserName().getLast());

        String fullUsername = capitalizedFirstName + " " + capitalizedLastName;
        targetUserName.setText(fullUsername);

        Resources res = context.getResources();
        String findOutStr = res.getString(R.string.find_out_where_string) + " ";
        String userLivesStr = " " + res.getString(R.string.user_lives_string);

        targetLocationHint.setText(findOutStr + capitalizedFirstName + userLivesStr);
    }

    /**
     *
     * load image url into target image view
     *
     * */

    public static void loadImageIntoContainer(ImageView target, String url) {
        Glide.with(target.getContext())
                .load(url)
                .centerCrop()
                .crossFade()
                .into(target);
    }

    /**
     *
     * convert string to string with first char uppercase
     *
     * */

    public static String stringToUpperCase(String name) {
        String capitalizedStr = name.substring(0, 1).toUpperCase() +
                name.substring(1);
        return capitalizedStr;
    }

    /**
     *
     * get user lat and long based on direction provided by API
     *
     * */

    public static LatLng getUserLtLng(Context context,RandomUser user) {
        LatLng userLtLng = null;
        Geocoder coder = new Geocoder(context);
        List<Address> address;

        UserLocation userLocation = user.getUserLocation();
        String strAddress = userLocation.getStreet() + " " + userLocation.getCity() + " "
                + userLocation.getState() + " " + userLocation.getPostalCode();

        try {
            address = coder.getFromLocationName(strAddress,1);
            if (address==null) {
                return null;
            }
            Address location=address.get(0);
            userLtLng = new LatLng(location.getLatitude(), location.getLongitude());
        } catch (IOException e){
            e.printStackTrace();
        }

        return userLtLng;
    }

    /**
     *
     * set title to action bar in AppCompatActivity
     *
     * */

    public static void setActionBarTitle(AppCompatActivity context, String title) {
        context.getSupportActionBar().setTitle(title);
    }

    /**
     *
     * get resource image for background preventing OutOfMemory
     *
     * */

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    /**
     *
     * calculate size based on sample
     *
     * */

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }


}
