package com.example.covid_19;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class NewsAdapter extends ArrayAdapter {

    String DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private Context context;


    public NewsAdapter(Context context, ArrayList<NewsData> newsHolders) {
        super(context, 0, newsHolders);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);
        }

        NewsData currentItem = (NewsData) getItem(position);


        ImageView image = (ImageView) listItemView.findViewById(R.id.image_news);
        Glide.with(context)
                .load(currentItem.getUrlToImage())
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(image);

        TextView title = (TextView) listItemView.findViewById(R.id.title);
        title.setText(currentItem.getTitle());

        TextView description = (TextView) listItemView.findViewById(R.id.description);
        description.setText(currentItem.getDescription());


        TextView source_news = (TextView) listItemView.findViewById(R.id.sourse_news);
        source_news.setText(currentItem.getName());

        TextView author = (TextView) listItemView.findViewById(R.id.author_name);
        if (currentItem.getAuthor() != "null") {
            author.setText(currentItem.getAuthor());
        } else {
            author.setText("");
        }


        //String DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        //Date dateObject = new Date(currentItem.getPublishedAt());
        String stringToParse = currentItem.getPublishedAt();

        //String string = "004-034556";
        String[] parts = stringToParse.split("T");
        String dateOfNews = parts[0]; // 004
        String part2 = parts[1]; // 034556
        String part3[] = part2.split("Z");
        String timeOfNews = part3[0];
//        Date dateObject = new Date(stringToParse);
//
//        String formattedDate = formatDate(dateObject);
//        String formattTime = formatTime(dateObject);

        TextView date = (TextView) listItemView.findViewById(R.id.publish_date);
        date.setText(dateOfNews);

        TextView time = (TextView) listItemView.findViewById(R.id.publish_time);
        time.setText(timeOfNews);

        return listItemView;

    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm:ss a");
        return timeFormat.format(dateObject);
    }

    public Date parseDate(String stringToParse) {
        Date dateAndTime = null;
        try {
            dateAndTime = new SimpleDateFormat(DATE_FORMAT_PATTERN).parse(stringToParse);
        } catch (ParseException e) {
            Log.e("News Adapter", "Problem retrieving the news publish date JSON results.", e);
        }
        return null;
    }
}
