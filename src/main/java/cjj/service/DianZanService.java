package cjj.service;

import cjj.common.Result;

public interface DianZanService {
    Result sumStar(String username);

    Result isDianZan(int userId, int contentId);

    Result addDianZan(int userId, int contentId,int status);
}
