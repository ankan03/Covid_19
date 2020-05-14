package com.example.covid_19;

public class NewsData {

//    {
//        "source": {
//        "id": null,
//                "name": "Indianexpress.com"
//    },
//        "author": "Express News Service",
//            "title": "New Research: Now, bowel abnormalities seen in Covid-19 patients - The Indian Express",
//            "description": "The retrospective study included 412 Covid-19 patients (241 men and 171 women) admitted to a single health facility from March 27 to April 10.",
//            "url": "https://indianexpress.com/article/explained/now-bowel-abnormalities-seen-in-covid-19-patients-6408654/",
//            "urlToImage": "https://images.indianexpress.com/2020/05/explaind.jpg?w=759",
//            "publishedAt": "2020-05-14T02:36:55Z",
//            "content": "Records showed that 17% of patients had cross-sectional abdominal imaging ultrasounds, CT scans etc. Bowel abnormalities were seen on 31% of CT scans (3.2% of all patients) and were more frequent in intensive care unit (ICU) patients than other inpatients. (F… [+2304 chars]"
//    }

    private String name;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;

    public NewsData(String name, String author, String title, String description, String url, String urlToImage, String publishedAt, String content) {
        this.name = name;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getContent() {
        return content;
    }
}
