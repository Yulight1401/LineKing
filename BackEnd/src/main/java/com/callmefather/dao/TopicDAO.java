package com.callmefather.dao;

import com.callmefather.enity.Topic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xinliu on 12/2/16.
 */
public class TopicDAO {
    private static final String TBNAME = "topic";
    private static final String TOPICID = "_id";
    private static final String TITLE = "_title";
    private static final String CONTENT = "_content";
    private static final String POSTER = "_poster";
    private static final String TIME = "_time";

    public static boolean newTopic(Topic topic) {
        Statement statement = DAOUtil.getStatement();
        boolean success = false;
        synchronized (DAOUtil.lock) {
            try {
                statement.execute("INSERT INTO " + TBNAME + "(" + TITLE + "," + CONTENT + "," + POSTER + "," + TIME + ")" +
                        "VALUES ('" + topic.getTitle() + "','" + topic.getContent() + "','" + topic.getPoster() + "','" + topic.getTime() + "')");
                success = true;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                return success;
            }
        }
    }

    public static List<Topic> getTopics() {
        Statement statement = DAOUtil.getStatement();
        List<Topic> list = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM " + TBNAME);
            list = parseRS(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return list;
        }
    }

    public static boolean updateTopic(Topic topic) {
        Statement statement = DAOUtil.getStatement();
        boolean success = false;
        synchronized (DAOUtil.lock) {
            try {
                statement.execute("UPDATE " + TBNAME + " SET " +
                        TITLE + "='" + topic.getTitle() + "'," +
                        CONTENT + "='" + topic.getContent() +
                        "' WHERE " + TOPICID + "=" + topic.getId());
                success = true;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                return success;
            }
        }
    }

    public static List<Topic> getUserTopics(String name) {
        Statement statement = DAOUtil.getStatement();
        List<Topic> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = statement.executeQuery("SELECT * FROM " + TBNAME +
                    " WHERE " + POSTER + "='" + name + "'");
            list = parseRS(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return list;
        }
    }

    private static List<Topic> parseRS(ResultSet rs) {
        List<Topic> list = new ArrayList<>();
        try {
            while (rs.next()) {
                String title = rs.getString(TITLE);
                String content = rs.getString(CONTENT);
                String poster = rs.getString(POSTER);
                long time = rs.getLong(TIME);
                int id = rs.getInt("_id");
                list.add(new Topic(id, title, content, poster, time));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return list;
        }
    }
}
