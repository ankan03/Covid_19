package com.example.covid_19;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SymtomsFragment extends Fragment {
    private ImageView animationView_fever, animationView_dry_cough, animationView_tiredness;
    private Button details;
    private View t1, t2, t3, t4, t5;

    public SymtomsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_symtoms_fragment, container, false);


        animationView_fever = rootView.findViewById(R.id.fever);
        animationView_dry_cough = rootView.findViewById(R.id.dry_cough);
        animationView_tiredness = rootView.findViewById(R.id.tiredness);


        Glide.with(this)
                .load("https://www.gstatic.com/healthricherkp/covidsymptoms/light_fever.gif")
                .into(animationView_fever);
        Glide.with(this)
                .load("https://www.gstatic.com/healthricherkp/covidsymptoms/light_cough.gif")
                .into(animationView_dry_cough);
        Glide.with(this)
                .load("https://www.gstatic.com/healthricherkp/covidsymptoms/light_tiredness.gif")
                .into(animationView_tiredness);


        details = (Button) rootView.findViewById(R.id.details);
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cdc.gov/coronavirus/2019-ncov/symptoms-testing/symptoms.html"));
                startActivity(websiteIntent);

            }
        });

        t1 = (LinearLayout) rootView.findViewById(R.id.tips1);
        t2 = (LinearLayout) rootView.findViewById(R.id.tips2);
        t3 = (LinearLayout) rootView.findViewById(R.id.tips3);
        t4 = (LinearLayout) rootView.findViewById(R.id.tips4);
        t5 = (LinearLayout) rootView.findViewById(R.id.tips5);

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=1PLdl6NDGDE"));
                startActivity(websiteIntent);

            }
        });

        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=OOJqHPfG7pA"));
                startActivity(websiteIntent);

            }
        });

        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=YwrDP_l1AIM"));
                startActivity(websiteIntent);

            }
        });

        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=iv4mrCE6Tgg"));
                startActivity(websiteIntent);

            }
        });

        t5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=U8r3oTVMtQ0"));
                startActivity(websiteIntent);

            }
        });


//        animationView_fever = (LottieAnimationView) rootView.findViewById(R.id.fever_view);
//        animationView_fever.setAnimationFromUrl("https://assets2.lottiefiles.com/private_files/lf30_NVMl0A.json");
//        animationView_fever.playAnimation();
//
//        animationView_dry_cough = (LottieAnimationView) rootView.findViewById(R.id.dry_cough_view);
//        animationView_dry_cough.setAnimationFromUrl("https://assets5.lottiefiles.com/private_files/lf30_hHODoj.json");
//        animationView_dry_cough.playAnimation();
//
//        animationView_tiredness = (LottieAnimationView) rootView.findViewById(R.id.tiredness_animation);
//        animationView_tiredness.setAnimationFromUrl("https://assets2.lottiefiles.com/private_files/lf30_NVMl0A.json");
//        animationView_tiredness.playAnimation();


//        final ArrayList<InformationObject> symtomsItem = new ArrayList<InformationObject>();
//        symtomsItem.add(new InformationObject("fever","Fever is one of the Most common symptoms .According to a report in the Journal of the American Medical Association, as many as 98% of COVID-19 patients who were hospitalized had a fever(prolonged, for more than a couple of days) \n Gastrointestinal symptoms (stomachache, vomiting and/or diarrhea) \n Skin rashes (often reddish in color) \n If your child has a persistent fever above 101 degrees Fahrenheit (38.3 degrees Celsius), call your pediatrician","https://assets2.lottiefiles.com/private_files/lf30_NVMl0A.json"));
//        symtomsItem.add(new InformationObject("dry cough","Dry cough is one of the Most common symptoms .According to a report in the Journal of the American Medical Association, as many as 76% to 82%  of COVID-19 patients who were hospitalized had a fever \n Studies to date suggest that the virus that causes COVID-19 is mainly transmitted through contact with respiratory droplets rather than through the air","https://assets5.lottiefiles.com/private_files/lf30_hHODoj.json"));
//        symtomsItem.add(new InformationObject("tiredness","Tiredness is one of the Most common symptoms . It is shown that people are suffereng in drowsiness during a long time due to illness ","https://assets6.lottiefiles.com/private_files/lf30_agyQw8.json"));
//        symtomsItem.add(new InformationObject("aches and pains","Aches and pains are under Less common symptoms .The most common symptoms of COVID-19 are fever, tiredness, and dry cough. Some patients may have aches and pains, nasal congestion, runny nose, sore throat or diarrhea. These symptoms are usually mild and begin gradually\n","https://assets4.lottiefiles.com/packages/lf20_mRAJtD.json"));
//        symtomsItem.add(new InformationObject("sore throat","Sore throat is one of the Less common symptoms .Pain or irritation in the throat that can occur with or without swallowing, often accompanies infections, such as a cold or flu.Sore throat can have causes that aren't due to underlying disease. Examples include overuse of voice, a burn from hot food, very dry mouth or sleeping with the mouth open\n","https://assets5.lottiefiles.com/private_files/lf30_hHODoj.json"));
//        symtomsItem.add(new InformationObject("diarrhoea","Diarrhoea is one of the Less common symptoms .Some patients with COVID-19 experience gastrointestinal symptoms, particularly diarrhea, as the first sign of illness, according to a new study.\n","https://assets1.lottiefiles.com/packages/lf20_utjRCm.json"));
//        symtomsItem.add(new InformationObject("conjunctivitis","Conjunctivitis is one of the Less common symptoms .Cases of conjunctivitis or redness in the eyes are no longer being treated as mere eye infections. Such patients are now being taken to a separate triage and checked for travel history. Conjunctivitis can also be a symptom of Covid-19 infection, say doctors","https://assets2.lottiefiles.com/datafiles/pZCPuvTioKu2fiT/data.json"));
//        symtomsItem.add(new InformationObject("headache","A new review of neurological symptoms of COVID-19 patients has revealed the manifestations of the coronavirus on the entire nervous system, which may appear even before fever or cough in signals like headache and dizziness","https://assets7.lottiefiles.com/packages/lf20_5Gqhew.json"));
//        symtomsItem.add(new InformationObject("loss of taste or smell","Loss of taste or smell is one of the Less common symptoms . A loss of smell and taste may be the early symptom of Covid-19 infection, according to a latest report by a leading American professional association of medical specialists","https://assets1.lottiefiles.com/packages/lf20_utjRCm.json"));
//        symtomsItem.add(new InformationObject("a rash on skin, or discolouration of fingers or toes","A rash on skin, or discolouration of fingers or toes is one of the Less common symptoms .Although the recently expanded list of COVID-19 symptoms does not include skin changes, such as rash or discoloration of digits, we are seeing an increasing number of reports, says Sarah Young, MD, a medical dermatologist at Cleveland Clinic. ","https://assets1.lottiefiles.com/packages/lf20_utjRCm.json"));
//        symtomsItem.add(new InformationObject("difficulty breathing or shortness of breath","Difficulty breathing or shortness of breath one of the Serious symptoms .Shortness of breath can make it hard to breathe deeply. You may feel winded, or as if you can’t get enough air into your lungs","https://assets1.lottiefiles.com/packages/lf20_utjRCm.json"));
//        symtomsItem.add(new InformationObject("chest pain or pressure","Chest pain or pressure is one of the Serious symptoms .Panic disorder – whether you’ve lived with it for years or have developed it due to the pandemic – can cause chest pain, but cardiac and other physiological issues need to be ruled out before treatment can begin. And remember, any kind of chest pain requires medical attention.","https://assets1.lottiefiles.com/packages/lf20_utjRCm.json"));
//        symtomsItem.add(new InformationObject("loss of speech or movement","Loss of speech or movement is one of the Serious symptoms .The WHO has listed loss of speech or movements as one of the most serious symptoms of COVID-19. If you experience any of these symptoms, it is strongly advisable to seek medical attention immediately","https://assets1.lottiefiles.com/packages/lf20_utjRCm.json"));


        return rootView;
    }
}