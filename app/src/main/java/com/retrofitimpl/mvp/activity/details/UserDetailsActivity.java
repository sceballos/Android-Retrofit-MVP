package com.retrofitimpl.mvp.activity.details;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.retrofitimpl.mvp.R;
import com.retrofitimpl.mvp.data.user.RandomUser;
import com.retrofitimpl.mvp.util.DataSetUtils;
import com.retrofitimpl.mvp.util.UserMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserDetailsActivity extends AppCompatActivity implements OnMapReadyCallback {

    RandomUser selectedUser;
    ScrollView mainScrollView;
    ImageView wallImage;
    CircleImageView userProfileImage;
    TextView userName;
    TextView userEmail;
    TextView userNationality;
    TextView userMemberSince;
    CircleImageView actionCallUser;
    CircleImageView actionEmailUser;
    TextView locationHint;
    UserMap mMapFragment;
    GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        initViews();
        selectedUser = getIntent().getParcelableExtra("clicked_user");
        setData();
        initMap();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Thread test = new Thread(new Runnable() {
            @Override
            public void run() {
                final LatLng location = DataSetUtils.getUserLtLng(UserDetailsActivity.this
                        , selectedUser);

                if (location == null) {
                    return;
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Add a marker in user location and move the camera.
                        mMap.addMarker(new MarkerOptions().position(location));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
                        mMap.getUiSettings().setAllGesturesEnabled(false);
                    }
                });

            }
        });
        test.start();
    }

    /**
     *
     * init all views for this activity
     *
     * */

    private void initViews() {
        mainScrollView = findViewById(R.id.details_main_sv);
        wallImage = findViewById(R.id.details_wall_picture);
        userProfileImage = findViewById(R.id.details_user_picture);
        userName = findViewById(R.id.user_details_name);
        userEmail = findViewById(R.id.user_details_email);
        userNationality = findViewById(R.id.user_details_nationality);
        userMemberSince = findViewById(R.id.user_details_member_since);
        actionCallUser = findViewById(R.id.user_details_action_call);
        actionEmailUser = findViewById(R.id.user_details_action_send_email);
        locationHint = findViewById(R.id.user_location_hint);
    }

    /**
     *
     * init all views for this activity
     *
     * */

    private void setData() {
        setBackgroundToWall();
        DataSetUtils.setActionBarTitle(this,
                DataSetUtils.stringToUpperCase(selectedUser.getUserName().getFirst()) + " " +
                        DataSetUtils.stringToUpperCase(selectedUser.getUserName().getLast()));
        DataSetUtils.setNameAndLocationHintDataToView(this, selectedUser, userName, locationHint);
        userEmail.setText(selectedUser.getEmail());
        DataSetUtils.loadImageIntoContainer(userProfileImage, selectedUser.getUserPictures().getLarge());
        userNationality.setText(selectedUser.getNationality());
        DataSetUtils.convertDateFormatAndSetData(this, selectedUser, userMemberSince);
        setListenersToActions();
    }

    /**
     *
     * init map features
     *
     * */

    private void initMap() {
        mMapFragment = mMapFragment.newInstance();
        try {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.map_container, mMapFragment)
                    .commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
        mMapFragment.getMapAsync(this);
    }

    /**
     *
     * set OnClickListener to action buttons
     *
     * */

    private void setListenersToActions() {
        actionCallUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel",
                        selectedUser.getCellPhone(), null));
                startActivity(intent);
            }
        });

        actionEmailUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + selectedUser.getEmail()));
                startActivity(Intent.createChooser(emailIntent,
                        "Send email to " + selectedUser.getUserName().getFirst()));
            }
        });
    }

    /**
     *
     * set backgroud to wall image from resource image to prevent OutOfMemory error
     *
     * */

    private void setBackgroundToWall() {
        BitmapDrawable backgroundPhotos = new BitmapDrawable(getResources(), DataSetUtils.decodeSampledBitmapFromResource(getResources(),
                R.drawable.details_blue_bg, 800, 600)) ;

        //homeSocialMenu.setBackground(backgroundSocial);
        wallImage.setImageDrawable(backgroundPhotos);
    }

    /**
     *
     * define animation when this activity is closed
     *
     **/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.activity_enter, R.anim.activity_exit);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.activity_enter, R.anim.activity_exit);
    }

}
