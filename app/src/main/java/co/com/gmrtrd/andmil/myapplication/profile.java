package co.com.gmrtrd.andmil.myapplication;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class profile extends Fragment {

    private TextView tvUsernameProfile, tvEmailProfile;
    private ImageView iv;

    public profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
       View thisview=inflater.inflate(R.layout.fragment_profile, container, false);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        tvUsernameProfile = (TextView) thisview.findViewById(R.id.tv_username_profile);
        tvEmailProfile = (TextView) thisview.findViewById(R.id.tv_email_profile);
        iv= (ImageView) thisview.findViewById(R.id.profile_photo);
       /* if (user != null) {
            for (UserInfo profile : user.getProviderData()) {
                // Id of the provider (ex: google.com)
                String providerId = profile.getProviderId();

                // UID specific to the provider
                String uid = profile.getUid();


                // Name, email address, and profile photo Url
                String name = profile.getDisplayName();
                String email = profile.getEmail();
                Uri photoUrl = profile.getPhotoUrl();


            };
        }*/

        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();
            Picasso.with(getContext()).load(photoUrl).fit().centerCrop().into(iv);
            tvUsernameProfile.setText(name);
            tvEmailProfile.setText(email);

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            String uid = user.getUid();
        }
        getActivity().setTitle("Informacion Personal");


        return thisview;
    }

}
