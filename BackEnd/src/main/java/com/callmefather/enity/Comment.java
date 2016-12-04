package com.callmefather.enity;

/**
 * Created by xinliu on 12/2/16.
 */
public class Comment {
    private String content;
    private String commenter;
    private int postid;
    private long time;

    public Comment(String content, String commenter, int postid, long time) {
        this.content = content;
        this.commenter = commenter;
        this.postid = postid;
        this.time = time;
    }

    public String getContent() {
        return content;

    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommenter() {
        return commenter;
    }

    public void setCommenter(String commenter) {
        this.commenter = commenter;
    }

    @Override
    public String toString() {
        return content + "\n" + commenter + "\n" + postid + "\n" + time;
    }

    public int getTopicid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
