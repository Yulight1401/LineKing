package com.callmefather.controller;

import com.callmefather.dao.TopicDAO;
import com.callmefather.enity.Topic;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by xinliu on 12/2/16.
 */
@RestController
@RequestMapping("/topics")
public class TopicController {


    @RequestMapping(method = RequestMethod.POST)
    public void newTopic(HttpServletRequest request, HttpServletResponse response) {
        Topic topic = createTopic(request);
        boolean success = false;
        if (topic.getId() == 0) {
            success = TopicDAO.newTopic(topic);
        } else {
            success = TopicDAO.updateTopic(topic);
        }
        System.out.println(success ? topic : "new or update Topic failed");
        ControllerUtil.setState(success, response);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public static void deleteTopic(HttpServletRequest request, HttpServletResponse response) {
        Topic topic = createTopic(request);

    }

//    @RequestMapping(method = RequestMethod.PUT)
//    public void updateTopic(HttpServletRequest request, HttpServletResponse response) {
//        Topic topic = createTopic(request);
//        boolean success = TopicDAO.updateTopic(topic);
//        System.out.println(success ? topic : "update Topic failed");
//        ControllerUtil.setState(success, response);
//    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Topic> getTopics(HttpServletRequest request, HttpServletResponse response) {
        List<Topic> topics = TopicDAO.getTopics();
        ControllerUtil.setState(topics.size() > 0, response);
        return topics;
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public List<Topic> getUserTopic(@PathVariable(value = "name", required = true) String name, HttpServletRequest request, HttpServletResponse response) {
        List<Topic> topics = TopicDAO.getUserTopics(name);
        ControllerUtil.setState(topics.size() > 0, response);
        return topics;
    }

    private static Topic createTopic(HttpServletRequest request) {
        String id = ControllerUtil.getParam(request, "id", "0");
        String title = ControllerUtil.getParam(request, "title", null);
        String content = ControllerUtil.getParam(request, "content", null);
        String poster = ControllerUtil.getParam(request, "poster", null);
        String time = ControllerUtil.getParam(request, "time", "0");
        System.out.println(id);
        System.out.println(title);
        System.out.println(content);
        System.out.println(poster);
        System.out.println(time);
        return new Topic(Integer.parseInt(id), title, content, poster, Long.parseLong(time));
    }


}
