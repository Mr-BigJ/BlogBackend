package cjj.Controller;

import cjj.common.Result;
import cjj.service.DianZanService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@CrossOrigin
@RequestMapping("/pro/dianzan")
public class DianZanController {
    @Resource
    DianZanService dianZanService;

    @PostMapping("/sumStar")
    public Result sumStar(String username){
        return dianZanService.sumStar(username);
    }

    @PostMapping("/isDianZan")
    public Result isDianZan(int userId ,int contentId){
        return dianZanService.isDianZan(userId,contentId);
    }

    @PostMapping("/addDianZan")
    public Result addDianZan(int userId ,int contentId,int status){
        return dianZanService.addDianZan(userId,contentId,status);
    }
}
