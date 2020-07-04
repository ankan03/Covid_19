package com.example.covid_19;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;

public class TipsFragment extends Fragment {

    private ImageView animationView_clean_your_hands, animationView_safe_distance, animationView_do_not_touch_your_eyes, animationView_cover_your_nose, animationView_stay_home, animationView_medical_attention, animationView_follow_the_directions, animationView_mask;
    private Button details;
    private ImageView tipsImage;
    private View t1, t2, t3, t4, t5;

    public TipsFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_tips_fragment, container, false);


        tipsImage = rootView.findViewById(R.id.tips_image);
        animationView_clean_your_hands = rootView.findViewById(R.id.clean_your_hands);
        animationView_safe_distance = rootView.findViewById(R.id.safe_distance);
        animationView_do_not_touch_your_eyes = rootView.findViewById(R.id.do_not_touch_your_eyes);
        animationView_cover_your_nose = rootView.findViewById(R.id.cover_your_nose);
        animationView_stay_home = rootView.findViewById(R.id.stay_home);
        animationView_medical_attention = rootView.findViewById(R.id.medical_attention);
        animationView_follow_the_directions = rootView.findViewById(R.id.follow_the_directions);
        animationView_mask = rootView.findViewById(R.id.use_and_throw_mask);
        details = (Button) rootView.findViewById(R.id.details_tips_button);
        t1 = (LinearLayout) rootView.findViewById(R.id.tips1);
        t2 = (LinearLayout) rootView.findViewById(R.id.tips2);
        t3 = (LinearLayout) rootView.findViewById(R.id.tips3);
        t4 = (LinearLayout) rootView.findViewById(R.id.tips4);
        t5 = (LinearLayout) rootView.findViewById(R.id.tips5);


        Glide.with(this)
                .load("https://media2.giphy.com/media/SsNnE066ZyWmeNLtyJ/giphy.gif")
                .into(tipsImage);


        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.redcross.org/get-help/how-to-prepare-for-emergencies/types-of-emergencies/coronavirus-safety.html"));
                startActivity(websiteIntent);

            }
        });

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=r4i4avKYOrw"));
                startActivity(websiteIntent);

            }
        });

        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=nJpG6oOC4wA"));
                startActivity(websiteIntent);

            }
        });

        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=vF0bpEACcrg"));
                startActivity(websiteIntent);

            }
        });

        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=1APwq1df6Mw"));
                startActivity(websiteIntent);

            }
        });

        t5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=cuZFr7VYyCY"));
                startActivity(websiteIntent);

            }
        });

        Glide.with(this)
                .load("https://www.who.int/images/default-source/nepal/coronavirus_nepal/gifs_nepali/wash-hands.gif")
                .into(animationView_clean_your_hands);
        Glide.with(this)
                .load("https://www.who.int/images/default-source/nepal/coronavirus_nepal/gifs_nepali/social-distancing.gif")
                .into(animationView_safe_distance);
        Glide.with(this)
                .load("https://cdn.clipart.email/d230a4b97894024921562d2a2d3bdcd3_coronavirus-four-new-uk-cases-among-ship-evacuees-bbc-news_3240-3900.png")
                .into(animationView_do_not_touch_your_eyes);
        Glide.with(this)
                .load("https://www.who.int/images/default-source/nepal/coronavirus_nepal/gifs_nepali/sneeze-in-elbow-tissue.gif")
                .into(animationView_cover_your_nose);
        Glide.with(this)
                .load("https://www.who.int/images/default-source/searo---images/emergencies/covid19/how-to-protect/stay-healthy-at-home/1-stay-positive.gif")
                .into(animationView_stay_home);
        Glide.with(this)
                .load("https://www.who.int/universal_health_coverage/UHC-half-essential-services.gif")
                .into(animationView_medical_attention);
        Glide.with(this)
                .load("https://www.who.int/images/default-source/searo---images/emergencies/covid19/how-to-protect/stay-healthy-at-home/stay-healthy-at-home-gif06.gif")
                .into(animationView_follow_the_directions);
        Glide.with(this)
                .load("https://www.who.int/images/default-source/nepal/coronavirus_nepal/gifs_nepali/if-you-are-sick-use-a-mask-and-seek-medical-advise.gif")
                .into(animationView_mask);


//        animationView_clean_your_hands.setAnimationFromUrl("https://assets1.lottiefiles.com/packages/lf20_CYBIbn.json");
//        animationView_clean_your_hands.playAnimation();
//
//        animationView_safe_distance.setAnimationFromUrl("https://assets1.lottiefiles.com/private_files/lf30_9FzSEE.json");
//        animationView_safe_distance.playAnimation();
//
//        animationView_do_not_touch_your_eyes.setAnimationFromUrl("https://assets2.lottiefiles.com/private_files/lf30_NVMl0A.json");
//        animationView_do_not_touch_your_eyes.playAnimation();
//
//        animationView_cover_your_nose.setAnimationFromUrl("https://assets2.lottiefiles.com/private_files/lf30_NVMl0A.json");
//        animationView_cover_your_nose.playAnimation();
//
//        animationView_stay_home.setAnimationFromUrl("https://assets5.lottiefiles.com/private_files/lf30_hHODoj.json");
//        animationView_stay_home.playAnimation();
//
//        animationView_medical_attention.setAnimationFromUrl("https://assets2.lottiefiles.com/private_files/lf30_NVMl0A.json");
//        animationView_medical_attention.playAnimation();
//
//        animationView_follow_the_directions.setAnimationFromUrl("https://assets2.lottiefiles.com/private_files/lf30_NVMl0A.json");
//        animationView_follow_the_directions.playAnimation();


        return rootView;
    }
}