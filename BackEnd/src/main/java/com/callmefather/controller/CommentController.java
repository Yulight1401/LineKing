package com.callmefather.controller;

import com.callmefather.dao.CommentDAO;
import com.callmefather.enity.Comment;
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
@RequestMapping("/topics/detail")
public class CommentController {
    @RequestMapping(value = "/{topicid}", method = RequestMethod.POST)
    public void newComment(@PathVariable(value = "topicid", required = true) String topicid,
                           HttpServletRequest request, HttpServletResponse response) {
        String content = ControllerUtil.getParam(request, "content", null);
        String commenter = ControllerUtil.getParam(request, "commenter", null);
        String time = ControllerUtil.getParam(request, "time", null);
        Comment comment = new Comment(content, commenter, Integer.decode(topicid), Long.decode(time));
        boolean success = CommentDAO.newComment(comment);
        ControllerUtil.setState(success, response);
    }

    @RequestMapping(value = "/{topicid}", method = RequestMethod.GET)
    public List<Comment> getComments(@PathVariable(value = "topicid", required = true) String postid,
                                     HttpServletRequest request, HttpServletResponse response) {
        List<Comment> comments = CommentDAO.getComments(Integer.decode(postid));
        ControllerUtil.setState(comments.size() > 0, response);
        return comments;
    }
}
