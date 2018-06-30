package xyz.nxlexiaoyao.sales.controller.mobileApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.nxlexiaoyao.sales.bean.BaseBean;
import xyz.nxlexiaoyao.sales.bean.Member;
import xyz.nxlexiaoyao.sales.bean.MobileApp;
import xyz.nxlexiaoyao.sales.bean.Notice;
import xyz.nxlexiaoyao.sales.dao.MobileAppDao;
import xyz.nxlexiaoyao.sales.dao.NoticeDao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/sys")
public class SysRest extends BaseRest {
	private final int pageShow = 15;
    @Autowired
    private MobileAppDao mobileAppDao;
    @Autowired
    private NoticeDao noticeDao;
    
    
    
    @GetMapping("/appVer.do")
    public BaseBean<MobileApp> appVer(){
        return success(mobileAppDao.getLastMobileApp());
    }


    @GetMapping("/news.do")
    public BaseBean<List<String>> getNews(){
        List<String> news = Arrays.asList("欢迎牛懂事成为我司会员","恭喜杨洋从A网奖励出局,同时进入B网公排","郭帅帅A网报单2079元","李鬼头B网报单4879元","王仝仝C网报单7225元");
        return success(news);
    }
    
    
    
    
    @GetMapping("/notices.do")
    public BaseBean<List<Notice>> fiveNotice(@RequestParam(value="pageIndex",required=false) Integer pageIndex){
    	if(pageIndex == null){
            pageIndex = 1;
        }
    	List<Notice> notices = noticeDao.selectAll();
        Integer counter = notices.size();
        int pageMax = counter/pageShow;
        pageMax++;
        int offset = (pageIndex-1)*pageShow;
        int last = counter - offset;
        List<Notice> pageNotices = new ArrayList<>();
        if(last >0){
            int limit = 0;
            if(last >= pageShow){
                limit = pageShow;
            }else {
                limit = last;
            }
            pageNotices = noticeDao.selectByPage(limit, offset);
        }
        return success(pageNotices);
    }
}
