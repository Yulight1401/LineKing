package com.callmefather.enity;

/**
 * Created by xinliu on 12/2/16.
 */
public class Topic {
    private int id;
    private String title;
    private String content;
    private String poster;
    private long time;

    @Override
    public String toString() {
        return id + "\n" + title +
                "\n" + content +
                "\n" + poster +
                "\n" + time;
    }


    public Topic(int id, String title, String content, String poster, long time) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.poster = poster;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
