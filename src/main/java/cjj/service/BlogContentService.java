package cjj.service;

import cjj.common.Result;
import cjj.entity.BlogContent;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface BlogContentService {
    Result getContents();
    Result addOne(BlogContent blogContent);
    int sum();

    Result getMyBlogs(String username);


    Result updatePraise(int contentId);

    Result updateUnPraise(int contentId);

    Result updateLook(int contentId);

    Result sumPraise(String username);


    Result uploadContentImg(MultipartFile file);

    Result getRecentBlogs(String username);

    Result search(String msg);

    Result decPraise(int contentId);
    Result decUnPraise(int contentId);
    Result decLook(int contentId);

}
