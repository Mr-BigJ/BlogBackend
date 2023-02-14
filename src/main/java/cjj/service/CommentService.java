package cjj.service;

import cjj.common.Result;

public interface CommentService {
    Result sumComment(String username);

    Result addComment(int userId, String comment, int contentId);

    Result getComments(int contentId);
}
