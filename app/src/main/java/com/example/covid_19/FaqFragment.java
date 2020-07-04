package com.example.covid_19;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

//public class FaqFragment extends Fragment {
//
//
//
//
//
//
//    public FaqFragment() {
//
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//
//        View rootView = inflater.inflate(R.layout.fragment_faq, container, false);
//
//
//        final ArrayList<FaqObject> faq = new ArrayList<FaqObject>();
//        faq.add(new FaqObject("What is a coronaviruses","Coronaviruses are a large family of viruses which may cause illness in animals or humans.  In humans, several coronaviruses are known to cause respiratory infections ranging from the common cold to more severe diseases such as Middle East Respiratory Syndrome (MERS) and Severe Acute Respiratory Syndrome (SARS). The most recently discovered coronavirus causes coronavirus disease COVID-19."));
//        faq.add(new FaqObject("What is a COVID-19","COVID-19 is the infectious disease caused by the most recently discovered coronavirus. This new virus and disease were unknown before the outbreak began in Wuhan, China, in December 2019. COVID-19 is now a pandemic affecting many countries globally."));
//        faq.add(new FaqObject("What are the symptoms of COVID-19","The most common symptoms of COVID-19 are fever, dry cough, and tiredness. Other symptoms that are less common and may affect some patients include aches and pains, nasal congestion, headache, conjunctivitis, sore throat, diarrhea, loss of taste or smell or a rash on skin or discoloration of fingers or toes. These symptoms are usually mild and begin gradually. Some people become infected but only have very mild symptoms \n Most people (about 80%) recover from the disease without needing hospital treatment. Around 1 out of every 5 people who gets COVID-19 becomes seriously ill and develops difficulty breathing. Older people, and those with underlying medical problems like high blood pressure, heart and lung problems, diabetes, or cancer, are at higher risk of developing serious illness.  However, anyone can catch COVID-19 and become seriously ill.  People of all ages who experience fever and/or  cough associated withdifficulty breathing/shortness of breath, chest pain/pressure, or loss of speech or movement should seek medical attention immediately. If possible, it is recommended to call the health care provider or facility first, so the patient can be directed to the right clinic."));
//        faq.add(new FaqObject("What should I do if I have COVID-19 symptoms and when should I seek medical care ?","If you have minor symptoms, such as a slight cough or a mild fever, there is generally no need to seek medical care. Stay at home, self-isolate and monitor your symptoms. Follow national guidance on self-isolation.\n" +
//                "\n" +
//                "However, if you live in an area with malaria or dengue fever it is important that you do not ignore symptoms of fever.  Seek medical help.  When you attend the health facility wear a mask if possible, keep at least 1 metre distance from other people and do not touch surfaces with your hands. If it is a child who is sick help the child stick to this advice.\n" +
//                "\n" +
//                "Seek immediate medical care if you have difficulty breathing or pain/pressure in the chest. If possible, call your health care provider in advance, so he/she can direct you to the right health facility."));
//        faq.add(new FaqObject("How dose COVID-19 speed","People can catch COVID-19 from others who have the virus. The disease spreads primarily from person to person through small droplets from the nose or mouth, which are expelled when a person with COVID-19 coughs, sneezes, or speaks. These droplets are relatively heavy, do not travel far and quickly sink to the ground. People can catch COVID-19 if they breathe in these droplets from a person infected with the virus.  This is why it is important to stay at least 1 meter) away from others. These droplets can land on objects and surfaces around the person such as tables, doorknobs and handrails.  People can become infected by touching these objects or surfaces, then touching their eyes, nose or mouth.  This is why it is important to wash your hands regularly with soap and water or clean with alcohol-based hand rub.\n" +
//                "\n" +
//                "WHO is assessing ongoing research on the ways that COVID-19 is spread and will continue to share updated findings.    "));
//        faq.add(new FaqObject("Can COVID-19 be caught from a person who has no symptoms ?","COVID-19 is mainly spread through respiratory droplets expelled by someone who is coughing or has other symptoms such as fever or tiredness. Many people with COVID-19 experience only mild symptoms. This is particularly true in the early stages of the disease. It is possible to catch COVID-19 from someone who has just a mild cough and does not feel ill.\n" +
//                "\n" +
//                "Some reports have indicated that people with no symptoms can transmit the virus. It is not yet known how often it happens. WHO is assessing ongoing research on the topic and will continue to share updated findings."));
//        faq.add(new FaqObject("How can we protect others and ourselves if we don't know who is infected ?","Practicing hand and respiratory hygiene is important at ALL times and is the best way to protect others and yourself.\n" +
//                "\n" +
//                "When possible maintain at least a 1 meter distance between yourself and others. This is especially important if you are standing by someone who is coughing or sneezing.  Since some infected persons may not yet be exhibiting symptoms or their symptoms may be mild, maintaining a physical distance with everyone is a good idea if you are in an area where COVID-19 is circulating. "));
//        faq.add(new FaqObject("What should I do if I have come in close contact with someone who has COVID-19","If you have been in close contact with someone with COVID-19, you may be infected.\n" +
//                "\n" +
//                "Close contact means that you live with or have been in settings of less than 1 metre from those who have the disease. In these cases, it is best to stay at home.\n" +
//                "\n" +
//                "However, if you live in an area with malaria or dengue fever it is important that you do not ignore symptoms of fever. Seek medical help. When you attend the health facility wear a mask if possible, keep at least 1 metre distant from other people and do not touch surfaces with your hands. If it is a child who is sick help the child stick to this advice.\n" +
//                "\n" +
//                "If you do not live in an area with malaria or dengue fever please do the following:\n" +
//                "\n" +
//                "If you become ill, even with very mild symptoms you must self-isolate\n" +
//                "Even if you don’t think you have been exposed to COVID-19 but develop symptoms, then self-isolate and monitor yourself\n" +
//                "You are more likely to infect others in the early stages of the disease when you just have mild symptoms, therefore early self-isolation is very important.\n" +
//                "If you do not have symptoms, but have been exposed to an infected person, self-quarantine for 14 days.\n" +
//                "If you have definitely had COVID-19 (confirmed by a test) self-isolate for 14 days even after symptoms have disappeared as a precautionary measure – it is not yet known exactly how long people remain infectious after they have recovered. Follow national advice on self-isolation."));
//        faq.add(new FaqObject("What should I do if I have come in close contact with someone who has COVID-19","Self-isolation is an important measure taken by those who have COVID-19 symptoms to avoid infecting others in the community, including family members.\n" +
//                "\n" +
//                "Self-isolation is when a person who is experiencing fever, cough or other COVID-19 symptoms stays at home and does not go to work, school or public places. This can be voluntarily or based on his/her health care provider’s recommendation. However, if you live in an area with malaria or dengue fever it is important that you do not ignore symptoms of fever. Seek medical help. When you attend the health facility wear a mask if possible, keep at least 1 metre distant from other people and do not touch surfaces with your hands. If it is a child who is sick help the child stick to this advice.\n" +
//                "\n" +
//                "If you do not live in an area with malaria or dengue fever please do the following:\n" +
//                "\n" +
//                "-  If a person is in self-isolation, it is because he/she is ill but not severely ill (requiring medical attention)\n" +
//                "\n" +
//                "have a large, well-ventilated with hand-hygiene and toilet facilities\n" +
//                "If this is not possible, place beds at least 1 metre apart\n" +
//                "Keep at least 1 metre from others, even from your family members\n" +
//                "Monitor your symptoms daily\n" +
//                "Isolate for 14 days, even if you feel healthy\n" +
//                "If you develop difficulty breathing, contact your healthcare provider immediately – call them first if possible\n" +
//                "Stay positive and energized by keeping in touch with loved ones by phone or online, and by exercising yourself at home."));
//
//
//
//
//        FaqAdapter adapter = new FaqAdapter(getActivity(), faq);
//        ListView faqlistView = (ListView) rootView.findViewById(R.id.faq_list);
//
//        faqlistView.setAdapter(adapter);
//
//
//        return rootView;
//    }
//}


public class FaqFragment extends Fragment {

    View rootView;
    ExpandableListView lv;
    private String[] groups;
    private String[][] children;
    private Button button;

    public FaqFragment() {

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.mohfw.gov.in/pdf/FAQ.pdf"));
//                startActivity(websiteIntent);
//            }
//        });

        groups = new String[]{"What is a coronaviruses ?", "What is a COVID-19 ?", "What are the symptoms of COVID-19 ?", "What should I do if I have COVID-19 symptoms and when should I seek medical care ?", "How dose COVID-19 speed ?", "Can COVID-19 be caught from a person who has no symptoms ?", "How can we protect others and ourselves if we don't know who is infected ?", "What should I do if I have come in close contact with someone who has COVID-19 ?", "What does it mean to self-isolate ?", "What should I do if I have no symtoms , but I think I have been exposed to COVID-19? What does it mean to self quarantine?", "What is the difference between self-isolation,self-quarantine and distancing ?", "Can children or adolescents catch COVID-19 ?", "What can I do to protect myself and prevent the spread of disease ?", "Is there a vaccine,drug or treatment for COVID-19 ?", "Does WHO recommend wearing medical masks to prevent the speed of COVID-19 ?", "How to properly were a medical mask ?", "How long does it take after exposure to COVID-19 to develop symptoms ?", "What is the connection between COVID-19 and animals ?", "Can I catch COVID-19 from my pet or other animals ?", "How long does the virus survive on surface ?", "How to grocery shop safely ?", "How to wash fruits and vegetables ?", "Are antibiotics effective in preventing or treating COVID-19 ?", "Can I catch COVID-19 from the faeces of someone with the disease ?"};

        children = new String[][]{
                {"Coronaviruses are a large family of viruses which may cause illness in animals or humans.  In humans, several coronaviruses are known to cause respiratory infections ranging from the common cold to more severe diseases such as Middle East Respiratory Syndrome (MERS) and Severe Acute Respiratory Syndrome (SARS). The most recently discovered coronavirus causes coronavirus disease COVID-19."},
                {"COVID-19 is the infectious disease caused by the most recently discovered coronavirus. This new virus and disease were unknown before the outbreak began in Wuhan, China, in December 2019. COVID-19 is now a pandemic affecting many countries globally."},
                {"The most common symptoms of COVID-19 are fever, dry cough, and tiredness. Other symptoms that are less common and may affect some patients include aches and pains, nasal congestion, headache, conjunctivitis, sore throat, diarrhea, loss of taste or smell or a rash on skin or discoloration of fingers or toes. These symptoms are usually mild and begin gradually. Some people become infected but only have very mild symptoms.\n" +
                        "\n" +
                        "Most people (about 80%) recover from the disease without needing hospital treatment. Around 1 out of every 5 people who gets COVID-19 becomes seriously ill and develops difficulty breathing. Older people, and those with underlying medical problems like high blood pressure, heart and lung problems, diabetes, or cancer, are at higher risk of developing serious illness.  However, anyone can catch COVID-19 and become seriously ill.  People of all ages who experience fever and/or  cough associated withdifficulty breathing/shortness of breath, chest pain/pressure, or loss of speech or movement should seek medical attention immediately. If possible, it is recommended to call the health care provider or facility first, so the patient can be directed to the right clinic."},
                {"If you have minor symptoms, such as a slight cough or a mild fever, there is generally no need to seek medical care. Stay at home, self-isolate and monitor your symptoms. Follow national guidance on self-isolation.\n" +
                        "\n" +
                        "However, if you live in an area with malaria or dengue fever it is important that you do not ignore symptoms of fever.  Seek medical help.  When you attend the health facility wear a mask if possible, keep at least 1 metre distance from other people and do not touch surfaces with your hands. If it is a child who is sick help the child stick to this advice.\n" +
                        "\n" +
                        "Seek immediate medical care if you have difficulty breathing or pain/pressure in the chest. If possible, call your health care provider in advance, so he/she can direct you to the right health facility."},

                {"People can catch COVID-19 from others who have the virus. The disease spreads primarily from person to person through small droplets from the nose or mouth, which are expelled when a person with COVID-19 coughs, sneezes, or speaks. These droplets are relatively heavy, do not travel far and quickly sink to the ground. People can catch COVID-19 if they breathe in these droplets from a person infected with the virus.  This is why it is important to stay at least 1 meter) away from others. These droplets can land on objects and surfaces around the person such as tables, doorknobs and handrails.  People can become infected by touching these objects or surfaces, then touching their eyes, nose or mouth.  This is why it is important to wash your hands regularly with soap and water or clean with alcohol-based hand rub.\n" +
                        "\n" +
                        "WHO is assessing ongoing research on the ways that COVID-19 is spread and will continue to share updated findings."},
                {"COVID-19 is mainly spread through respiratory droplets expelled by someone who is coughing or has other symptoms such as fever or tiredness. Many people with COVID-19 experience only mild symptoms. This is particularly true in the early stages of the disease. It is possible to catch COVID-19 from someone who has just a mild cough and does not feel ill.\n" +
                        "\n" +
                        "Some reports have indicated that people with no symptoms can transmit the virus. It is not yet known how often it happens. WHO is assessing ongoing research on the topic and will continue to share updated findings."},
                {"Practicing hand and respiratory hygiene is important at ALL times and is the best way to protect others and yourself.\n" +
                        "\n" +
                        "When possible maintain at least a 1 meter distance between yourself and others. This is especially important if you are standing by someone who is coughing or sneezing.  Since some infected persons may not yet be exhibiting symptoms or their symptoms may be mild, maintaining a physical distance with everyone is a good idea if you are in an area where COVID-19 is circulating."},
                {"If you have been in close contact with someone with COVID-19, you may be infected.\n" +
                        "\n" +
                        "Close contact means that you live with or have been in settings of less than 1 metre from those who have the disease. In these cases, it is best to stay at home.\n" +
                        "\n" +
                        "However, if you live in an area with malaria or dengue fever it is important that you do not ignore symptoms of fever. Seek medical help. When you attend the health facility wear a mask if possible, keep at least 1 metre distant from other people and do not touch surfaces with your hands. If it is a child who is sick help the child stick to this advice.\n" +
                        "\n" +
                        "If you do not live in an area with malaria or dengue fever please do the following:\n" +
                        "\n" +
                        "If you become ill, even with very mild symptoms you must self-isolate\n" +
                        "Even if you don’t think you have been exposed to COVID-19 but develop symptoms, then self-isolate and monitor yourself\n" +
                        "You are more likely to infect others in the early stages of the disease when you just have mild symptoms, therefore early self-isolation is very important.\n" +
                        "If you do not have symptoms, but have been exposed to an infected person, self-quarantine for 14 days.\n" +
                        "If you have definitely had COVID-19 (confirmed by a test) self-isolate for 14 days even after symptoms have disappeared as a precautionary measure – it is not yet known exactly how long people remain infectious after they have recovered. Follow national advice on self-isolation."},
                {"Self-isolation is an important measure taken by those who have COVID-19 symptoms to avoid infecting others in the community, including family members.\n" +
                        "\n" +
                        "Self-isolation is when a person who is experiencing fever, cough or other COVID-19 symptoms stays at home and does not go to work, school or public places. This can be voluntarily or based on his/her health care provider’s recommendation. However, if you live in an area with malaria or dengue fever it is important that you do not ignore symptoms of fever. Seek medical help. When you attend the health facility wear a mask if possible, keep at least 1 metre distant from other people and do not touch surfaces with your hands. If it is a child who is sick help the child stick to this advice.\n" +
                        "\n" +
                        "If you do not live in an area with malaria or dengue fever please do the following:\n" +
                        "\n" +
                        "-  If a person is in self-isolation, it is because he/she is ill but not severely ill (requiring medical attention)\n" +
                        "\n" +
                        "have a large, well-ventilated with hand-hygiene and toilet facilities\n" +
                        "If this is not possible, place beds at least 1 metre apart\n" +
                        "Keep at least 1 metre from others, even from your family members\n" +
                        "Monitor your symptoms daily\n" +
                        "Isolate for 14 days, even if you feel healthy\n" +
                        "If you develop difficulty breathing, contact your healthcare provider immediately – call them first if possible\n" +
                        "Stay positive and energized by keeping in touch with loved ones by phone or online, and by exercising yourself at home."},
                {"To self-quarantine means to separate yourself from others because you have been exposed to someone with COVID-19 even though you, yourself, do not have symptoms.During self-quarantine you monitor yourself for symptoms.  The goal of the self-quarantine is to prevent transmission.  Since people who become ill with COVID-19 can infect people immediately self-quarantine can prevent some infections from happening.  .\n" +
                        "\n" +
                        "In this case:\n" +
                        "\n" +
                        "Have a large, well-ventilated single room with hand hygiene and toilet facilities\n" +
                        "If this is not available place beds at least 1 metre apart.\n" +
                        "Keep at least 1-metre distance from others, even from your family members.\n" +
                        "Monitor your symptoms daily\n" +
                        "Self-quarantine for 14 days, even if you feel healthy\n" +
                        "If you develop difficulty breathing, contact your healthcare provider immediately – call them first if possible.\n" +
                        "Stay positive and energized by keeping in touch with loved ones by phone or online, and by exercising yourself at home.\n" +
                        "However, if you live in an area with malaria or dengue fever it is important that you do not ignore symptoms of fever.  Seek medical help.  When you attend the health facility wear a mask if possible, keep at least 1 metre distant from other people and do not touch surfaces with your hands. If it is a child who is sick help the child stick to this advice."},
                {"Quarantine means restricting activities or separating people who are not ill themselves but may have been exposed to COVID-19. The goal is to prevent spread of the disease at the time when people just develop symptoms..\n" +
                        "\n" +
                        "Isolation means separating people who are ill with symptoms of COVID-19 and may be infectious to prevent the spread of the disease.\n" +
                        "\n" +
                        "Physical distancing means being physically apart. WHO recommends keeping at least 1-metre distance from others. This is a general measure that everyone should take even if they are well with no known exposure to COVID-19."},
                {"Research indicates that children and adolescents are just as likely to become infected as any other age group and can spread the disease.\n" +
                        "\n" +
                        "Evidence to date suggests that children and young adults are less likely to get severe disease, but severe cases can still happen in these age groups.\n" +
                        "\n" +
                        "Children and adults should follow the same guidance on self-quarantine and self-isolation if there is a risk they have been exposed or are showing symptoms. It is particularly important that children avoid contact with older people and others who are at risk of more severe disease."},
                {"Stay aware of the latest information on the COVID-19 outbreak, available on the WHO website and through your national and local public health authority. Most countries around the world have seen cases of COVID-19 and many are experiencing outbreaks. Authorities in China and some other countries have succeeded in slowing their outbreaks. However, the situation is unpredictable so check regularly for the latest news.\n" +
                        "\n" +
                        "You can reduce your chances of being infected or spreading COVID-19 by taking some simple precautions:\n" +
                        "\n" +
                        "Regularly and thoroughly clean your hands with an alcohol-based hand rub or wash them with soap and water. Why? Washing your hands with soap and water or using alcohol-based hand rub kills viruses that may be on your hands.\n" +
                        "Maintain at least 1 metre distance between yourself and others. Why? When someone coughs, sneezes, or speaks they spray small liquid droplets from their nose or mouth which may contain virus. If you are too close, you can breathe in the droplets, including the COVID-19 virus if the person has the disease.\n" +
                        "Avoid going to crowded places. Why? Where people come together in crowds, you are more likely to come into close contact with someone that has COVID-19 and it is more difficult to maintain physical distance of 1 metre.\n" +
                        "Avoid touching eyes, nose and mouth. Why? Hands touch many surfaces and can pick up viruses. Once contaminated, hands can transfer the virus to your eyes, nose or mouth. From there, the virus can enter your body and infect you.\n" +
                        "Make sure you, and the people around you, follow good respiratory hygiene. This means covering your mouth and nose with your bent elbow or tissue when you cough or sneeze. Then dispose of the used tissue immediately and wash your hands. Why? Droplets spread virus. By following good respiratory hygiene, you protect the people around you from viruses such as cold, flu and COVID-19.\n" +
                        "Stay home and self-isolate even with minor symptoms such as cough, headache, mild fever, until you recover. Have someone bring you supplies. If you need to leave your house, wear a mask to avoid infecting others. Why? Avoiding contact with others will protect them from possible COVID-19 and other viruses.\n" +
                        "If you have a fever, cough and difficulty breathing, seek medical attention, but call by telephone in advance if possible and follow the directions of your local health authority. Why? National and local authorities will have the most up to date information on the situation in your area. Calling in advance will allow your health care provider to quickly direct you to the right health facility. This will also protect you and help prevent spread of viruses and other infections.\n" +
                        "Keep up to date on the latest information from trusted sources, such as WHO or your local and national health authorities. Why? Local and national authorities are best placed to advise on what people in your area should be doing to protect themselves."},
                {"While some western, traditional or home remedies may provide comfort and alleviate symptoms of mild COVID-19, there are no medicines that have been shown to prevent or cure the disease. WHO does not recommend self-medication with any medicines, including antibiotics, as a prevention or cure for COVID-19. However, there are several ongoing clinical trials of both western and traditional medicines. WHO is coordinating efforts to develop vaccines and medicines to prevent and treat COVID-19 and will continue to provide updated information as soon research results become available.\n" +
                        "\n" +
                        "The most effective ways to protect yourself and others against COVID-19 are to:\n" +
                        "\n" +
                        "Clean your hands frequently and thoroughly\n" +
                        "Avoid touching your eyes, mouth and nose\n" +
                        "Cover your cough with the bend of elbow or tissue. If a tissue is used, discard it immediately and wash your hands.\n" +
                        "Maintain a distance of at least 1 metre from others."},
                {"Currently, there is not enough evidence for or against the use of masks (medical or other) in healthy individuals in the wider community. However, WHO is actively studying the rapidly evolving science on masks and continuously updates its guidance.\n" +
                        "\n" +
                        "Medical masks are recommended primarily in health care settings, but can be considered in other circumstances (see below). Medical masks should be combined with other key infection prevention and control measures such as hand hygiene and physical distancing.\n" +
                        "\n" +
                        "Healthcare workers\n" +
                        "Why? Medical masks and respirators such as N95, FFP2 or equivalent are recommended for and should be reserved for, healthcare workers while giving care to patients. Close contact with people with suspected or confirmed COVID-19 and their surrounding environment are the main routes of transmission, which means healthcare workers are the most exposed.\n" +
                        "\n" +
                        "People who are sick and exhibiting symptoms of COVID-19\n" +
                        "Why? Anyone who is sick, with mild symptoms such as muscle aches, slight cough, sore throat or fatigue, should isolate at home and use a medical mask according to WHO’s recommendation on home care of patients with suspected COVID-19. Coughing, sneezing or talking can generate droplets that cause can spread the infection. These droplets can reach the face of others nearby and land on the surrounding environment. If an infected person coughs, sneezes, or talks while wearing a medical mask, this can help to protect those nearby from infection. If a sick person needs to go to a health facility they should wear a medical mask.\n" +
                        "\n" +
                        "Anyone taking care of a person at home who is sick with COVID-19\n" +
                        "Why? Those caring for individuals who are sick with COVID-19 should wear a medical mask for protection. Again, close, frequent and prolonged contact with someone with COVID-19 puts caretakers at high risk. National decision makers may also choose to recommend medical mask use for certain individuals using a risk-based approach. This approach takes into consideration the purpose of the mask, risk of exposure and vulnerability of the wearer, the setting, the feasibility of use and the types of masks to be considered."},
                {"If you choose to wear a mask:\n" +
                        "\n" +
                        "Before touching the mask, clean hands with an alcohol-based hand rub or soap and water\n" +
                        "Take the mask and inspect it for tears or holes.\n" +
                        "Orient which side is the top side (where the metal strip is).\n" +
                        "Ensure the proper side of the mask faces outwards (the coloured side).\n" +
                        "Place the mask to your face. Pinch the metal strip or stiff edge of the mask so it moulds to the shape of your nose.\n" +
                        "Pull down the mask’s bottom so it covers your mouth and your chin.\n" +
                        "Do not touch the mask while you are wearing it for protection.\n" +
                        "After use, take off the mask with clean hands; remove the elastic loops from behind the ears while keeping the mask away from your face and clothes, to avoid touching potentially contaminated surfaces of the mask.\n" +
                        "Discard the mask in a closed bin immediately after use. Do not reuse the mask.\n" +
                        "Perform hand hygiene after touching or discarding the mask – Use alcohol-based hand rub or, if visibly soiled, wash your hands with soap and water.\n" +
                        "Be aware that there is a global shortage of medical masks (both surgical masks and N95 masks). These should be reserved as much as possible for health care workers.\n" +
                        "\n" +
                        "Remember that  masks are not a substitute for other, more effective ways to protect yourself and others against COVID-19 such as frequently washing your hands, covering your cough with the bend of elbow or tissue and maintain a distance of at least 1 meter from others. See basic protective measures against the new coronavirus for more information.\n" +
                        "\n" +
                        "Follow the advice of your national health authority on the use of masks."},
                {"The time between exposure to COVID-19 and the moment when symptoms start is commonly around five to six days but can range from 1 – 14 days."},
                {"COVID-19 is spread through human-to-human transmission.\n" +
                        "\n" +
                        "We already know a lot about other viruses in the coronavirus family and most of these types of viruses have an origin in animals. The COVID-19 virus (also called SARS-CoV-2) is a new virus in humans. The possible animal source of COVID-19 has not yet been confirmed but research is ongoing.\n" +
                        "\n" +
                        "WHO continues to monitor the latest research on this and other COVID-19 topics and will update, as new findings are available."},
                {"Several dogs and cats (domestic cats and tigers) in contact with infected humans have tested positive for COVID-19. In addition, ferrets appear to be susceptible to the infection. In experimental conditions, both cats and ferrets were able to transmit infection to other animals of the same species. However, there is no evidence that these animals can transmit the disease to humans and spread COVID-19. COVID-19 is mainly spread through droplets produced when an infected person coughs, sneezes, or speaks.\n" +
                        "\n" +
                        "Minks raised in farms have also been detected with the virus. Most likely, they have been infected by farm workers. In a few instances, the minks that were infected by humans have transmitted the virus to other people. These are the first reported cases of animal-to-human transmission.\n" +
                        "\n" +
                        "It is still recommended that people who are sick with COVID-19 and people who are at risk limit contact with companion and other animals. When handling and caring for animals, basic hygiene measures should always be implemented. This includes hand washing after handling animals, their food or supplies, as well as avoiding kissing, licking or sharing food.\n" +
                        "\n" +
                        "More recommendations are available on the OIE website: https://www.oie.int/en/scientific-expertise/specific-information-and-recommendations/questions-and-answers-on-2019novel-coronavirus/\n" +
                        "\n" +
                        "WHO continues to monitor the latest research on this and other COVID-19 topics and will update as new findings are available."},
                {"The most important thing to know about coronavirus on surfaces is that they can easily be cleaned with common household disinfectants that will kill the virus. Studies have shown that the COVID-19 virus can survive for up to 72 hours on plastic and stainless steel, less than 4 hours on copper and less than 24 hours on cardboard.\n" +
                        "\n" +
                        "As, always clean your hands with an alcohol-based hand rub or wash them with soap and water. Avoid touching your eyes, mouth, or nose."},
                {"When grocery shopping, keep at least 1-metre distance from others and avoid touching your eyes, mouth and nose. If possible, sanitize the handles of shopping trolleys or baskets before shopping. Once home, wash your hands thoroughly and also after handling and storing your purchased products.\n" +
                        "\n" +
                        "There is currently no confirmed case of COVID-19 transmitted through food or food packaging."},
                {"Fruits and vegetables are important components of a healthy diet. Wash them the same way you should do under any circumstance: before handling them, wash your hands with soap and water. Then, wash fruits and vegetables thoroughly with clean water, especially if you eat them raw."},
                {"No. Antibiotics do not work against viruses; they only work on bacterial infections. COVID-19 is caused by a virus, so antibiotics do not work. Antibiotics should not be used as a means of prevention or treatment of COVID-19. In hospitals physicians will sometimes use antibiotics to prevent or treat secondary bacterial infections which can be a complication of COVID-19 in severely ill patients. They should only be used as directed by a physician to treat a bacterial infection."},
                {"While initial investigations suggest the virus may be present in faeces in some cases, to date, there have not been reports of faecal-oral transmission of COVID-19. Additionally, there is no evidence to date on the survival of the COVID-19 virus in water or sewage.\n" +
                        "\n" +
                        "WHO is assessing ongoing research on the ways COVID-19 is spread and will continue to share new findings on this topic."}

        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_faq, container, false);

        button = (Button) rootView.findViewById(R.id.faq_gov);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.mohfw.gov.in/pdf/FAQ.pdf"));
                startActivity(websiteIntent);
            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lv = (ExpandableListView) view.findViewById(R.id.expListView);
        lv.setAdapter(new ExpandableListAdapter(groups, children));
        lv.setGroupIndicator(null);

    }


    public class ExpandableListAdapter extends BaseExpandableListAdapter {

        private final LayoutInflater inf;
        private String[] groups;
        private String[][] children;

        public ExpandableListAdapter(String[] groups, String[][] children) {
            this.groups = groups;
            this.children = children;
            inf = LayoutInflater.from(getActivity());
        }

        @Override
        public int getGroupCount() {
            return groups.length;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return children[groupPosition].length;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groups[groupPosition];
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return children[groupPosition][childPosition];
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

            ViewHolder holder;
            if (convertView == null) {
                convertView = inf.inflate(R.layout.faq_list_item, parent, false);
                holder = new ViewHolder();

                holder.text = (TextView) convertView.findViewById(R.id.list_child);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.text.setText(getChild(groupPosition, childPosition).toString());

            return convertView;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null) {
                convertView = inf.inflate(R.layout.faq_list_group, parent, false);

                holder = new ViewHolder();
                holder.text = (TextView) convertView.findViewById(R.id.list_parent);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.text.setText(getGroup(groupPosition).toString());

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        private class ViewHolder {
            TextView text;
        }
    }
}