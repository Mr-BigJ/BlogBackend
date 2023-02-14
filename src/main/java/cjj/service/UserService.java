package cjj.service;

import cjj.common.Result;
import cjj.entity.BlogUser;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    Result login(String username, String password);

    String getUserName();

    Result register(String username, String password,String email);

    Result uploadAvatar(MultipartFile file,String userId);

    Result sumLook(String username);

    Result getUserInfo(String username);

    Result allUser();

    Result search(String msg);

    Result getRank(String username);
}
