package cjj.common;

import cjj.dao.ResortUserMapper;

import javax.annotation.Resource;


public class SortThread implements Runnable {
    private int start,end;
    private ResortUserMapper resortUserMapper;
    public SortThread(int start,int end,ResortUserMapper resortUserMapper){
        this.start=start;
        this.end=end;
        this.resortUserMapper=resortUserMapper;
    }



    @Override
    public void run() {
        for (int i=start;i<=end;i++) {
            resortUserMapper.updateSumPraise(ScheduleSort.idList.get(i));
        }
    }
}
