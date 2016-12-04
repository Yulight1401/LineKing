package com.callmefather.dao;

import com.callmefather.enity.Comment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xinliu on 12/2/16.
 */
public class CommentDAO {
    private static final String TBNAME = "comment";
    public static final String CONTENT = "_content";
    public static final String COMMENTER = "_commenter";
    public static final String TOPICID = "_topicid";
    public static final String TIME = "_time";

    public static boolean newComment(Comment comment) {
        Statement statement = DAOUtil.getStatement();
        synchronized (DAOUtil.lock) {
            boolean success = true;
            try {
                System.out.println(comment.getContent());
                System.out.println(comment.getCommenter());
                System.out.println(comment.getTopicid());
                System.out.println(comment.getTime());
                success = !statement.execute("INSERT INTO " + TBNAME + "(" + CONTENT + "," + COMMENTER + "," + TOPICID + "," + TIME + ")" +
                        "VALUES ('" + comment.getContent() + "','" + comment.getCommenter() + "'," + comment.getTopicid() + "," + comment.getTime() + ")");
                System.out.println(success);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                System.out.println(success ? "new comment" : "new comment failed");
                return success;
            }
        }
    }

    public static List<Comment> getComments(int topicid) {
        Statement statement = DAOUtil.getStatement();
        List<Comment> list = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM " + TBNAME + " where " + TOPICID + "= " + topicid);
            while (rs.next()) {
                String content = rs.getString(CONTENT);
                String commenter = rs.getString(COMMENTER);
                int postId = rs.getInt(TOPICID);
                long time = rs.getLong(TIME);
                Comment comment = new Comment(content, commenter, postId, time);
                list.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return list;
        }
    }
}
