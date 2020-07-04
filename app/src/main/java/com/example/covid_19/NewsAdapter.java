package com.example.covid_19;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
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

import androidx.recyclerview.widget.RecyclerView;


//public class NewsAdapter extends ArrayAdapter {
//
//    String DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";
//    private Context context;
//
//
//    public NewsAdapter(Context context, ArrayList<NewsData> newsHolders) {
//        super(context, 0, newsHolders);
//        this.context = context;
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//
//        View listItemView = convertView;
//        if (listItemView == null) {
//            listItemView = LayoutInflater.from(getContext()).inflate(
//                    R.layout.news_list_item, parent, false);
//        }
//
//        NewsData currentItem = (NewsData) getItem(position);
//
//
//        ImageView image = (ImageView) listItemView.findViewById(R.id.image_news);
//        Glide.with(context)
//                .load(currentItem.getUrlToImage())
//                .centerCrop()
//                .placeholder(R.drawable.ic_launcher_background)
//                .into(image);
//
//        TextView title = (TextView) listItemView.findViewById(R.id.title);
//        title.setText(currentItem.getTitle());
//
//        TextView description = (TextView) listItemView.findViewById(R.id.description);
//        description.setText(currentItem.getDescription());
//
//
//        TextView source_news = (TextView) listItemView.findViewById(R.id.sourse_news);
//        source_news.setText(currentItem.getName());
//
//        TextView author = (TextView) listItemView.findViewById(R.id.author_name);
//        if (currentItem.getAuthor() != "null") {
//            author.setText(currentItem.getAuthor());
//        } else {
//            author.setText("");
//        }
//
//
//        //String DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";
//        //Date dateObject = new Date(currentItem.getPublishedAt());
//        String stringToParse = currentItem.getPublishedAt();
//
//        //String string = "004-034556";
//        String[] parts = stringToParse.split("T");
//        String dateOfNews = parts[0]; // 004
//        String part2 = parts[1]; // 034556
//        String part3[] = part2.split("Z");
//        String timeOfNews = part3[0];
////        Date dateObject = new Date(stringToParse);
////
////        String formattedDate = formatDate(dateObject);
////        String formattTime = formatTime(dateObject);
//
//        TextView date = (TextView) listItemView.findViewById(R.id.publish_date);
//        date.setText(dateOfNews);
//
//        TextView time = (TextView) listItemView.findViewById(R.id.publish_time);
//        time.setText(timeOfNews);
//
//        return listItemView;
//
//    }
//
//    private String formatDate(Date dateObject) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
//        return dateFormat.format(dateObject);
//    }
//
//    private String formatTime(Date dateObject) {
//        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm:ss a");
//        return timeFormat.format(dateObject);
//    }
//
//    public Date parseDate(String stringToParse) {
//        Date dateAndTime = null;
//        try {
//            dateAndTime = new SimpleDateFormat(DATE_FORMAT_PATTERN).parse(stringToParse);
//        } catch (ParseException e) {
//            Log.e("News Adapter", "Problem retrieving the news publish date JSON results.", e);
//        }
//        return null;
//    }
//}

//public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {
//
//
//    String DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";
//    private Context context;
//
//    //View view;
//    ArrayList<NewsData> newsDataArrayList;
////    Context context;
//
////    public MyViewHolder(View v) {
////        super(v);
////        view = v;
////    }
//
//
//    public NewsAdapter(Context context, ArrayList<NewsData> newsHolders) {
////        super(context, 0, newsHolders);
//        this.context = context;
//        this.newsDataArrayList = newsHolders;
//    }
//
//    @NonNull
//    @Override
//    public NewsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_item,parent,false);
//        return new  MyViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//
//
//
////            NewsData currentItem = (NewsData) getItem(position);
//
//            Glide.with(context)
//                    .load(newsDataArrayList.get(position).getUrlToImage())
//                    .centerCrop()
//                    .placeholder(R.drawable.ic_launcher_background)
//                    .into(holder.image);
//
//            holder.title.setText(newsDataArrayList.get(position).getTitle());
//            holder.description.setText(newsDataArrayList.get(position).getDescription());
//            holder.source_news.setText(newsDataArrayList.get(position).getName());
//
//            if (newsDataArrayList.get(position).getAuthor() != "null") {
//                holder.author.setText(newsDataArrayList.get(position).getAuthor());
//            } else {
//                holder.author.setText("");
//            }
//
//
//            String stringToParse = newsDataArrayList.get(position).getPublishedAt();
//
//            String[] parts = stringToParse.split("T");
//            String dateOfNews = parts[0]; // 004
//            String part2 = parts[1]; // 034556
//            String part3[] = part2.split("Z");
//            String timeOfNews = part3[0];
//
//        Date dateObject = new Date(stringToParse);
//
//        String formattedDate = formatDate(dateObject);
//        String formattTime = formatTime(dateObject);
//
//            holder.date.setText(dateOfNews);
//
//            holder.time.setText(timeOfNews);
//
////            return listItemView;
//
//
//
//
//
//        }
////        holder.name.setText(users.get(position).getName());
////        holder.userName.setText(users.get(position).getUsername());
////        holder.email.setText(users.get(position).getEmail());
//
//
//
//    private String formatDate(Date dateObject) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
//        return dateFormat.format(dateObject);
//    }
//
//    private String formatTime(Date dateObject) {
//        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm:ss a");
//        return timeFormat.format(dateObject);
//    }
//
//    public Date parseDate(String stringToParse) {
//        Date dateAndTime = null;
//        try {
//            dateAndTime = new SimpleDateFormat(DATE_FORMAT_PATTERN).parse(stringToParse);
//        } catch (ParseException e) {
//            Log.e("News Adapter", "Problem retrieving the news publish date JSON results.", e);
//        }
//        return null;
//    }
//
//
//
//    @Override
//    public int getItemCount() {
//        return newsDataArrayList.size();
//    }
//
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//        private TextView title,description,source_news,author,date,time;
//        private ImageView image;
//
//        public MyViewHolder(@NonNull View listItemView) {
//            super(listItemView);
//
//            image = (ImageView) listItemView.findViewById(R.id.image_news);
//            title = (TextView) listItemView.findViewById(R.id.title);
//            description = (TextView) listItemView.findViewById(R.id.description);
//            source_news = (TextView) listItemView.findViewById(R.id.sourse_news);
//            author = (TextView) listItemView.findViewById(R.id.author_name);
//            date = (TextView) listItemView.findViewById(R.id.publish_date);
//            time = (TextView) listItemView.findViewById(R.id.publish_time);
//
//
//
//
////            name = (TextView)listItemView.findViewById(R.id.name);
////            userName = (TextView)listItemView.findViewById(R.id.user_name);
////            email = (TextView)listItemView.findViewById(R.id.email);
//        }
//
//
//
//
//    }
//
//
//    }
//
//
//
//
//


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

        Date d = StringToDate(stringToParse);
        String dateOfNews = formatDate(d);
//        String timeOfNews = formatTime(d);

//        //String string = "004-034556";
        String[] parts = stringToParse.split("T");
//        String dateOfNews = parts[0]; // 004
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

    public Date StringToDate(String s) {

        Date result = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            result = dateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();

        }
        return result;
    }


}