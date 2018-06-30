package xyz.nxlexiaoyao.sales.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import xyz.nxlexiaoyao.sales.bean.Member;
import xyz.nxlexiaoyao.sales.bean.Notice;
import xyz.nxlexiaoyao.sales.bean.Result;
import xyz.nxlexiaoyao.sales.bean.SaleForm;
import xyz.nxlexiaoyao.sales.bean.SaleRule;
import xyz.nxlexiaoyao.sales.dao.MemberDao;
import xyz.nxlexiaoyao.sales.dao.NoticeDao;
import xyz.nxlexiaoyao.sales.dao.SaleFormDao;
import xyz.nxlexiaoyao.sales.dao.SaleRuleDao;
import xyz.nxlexiaoyao.sales.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
    private final int pageShow = 15;
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private SaleRuleDao saleRuleDao;

    @Autowired
    private MemberService memberService;

    @Autowired
    private SaleFormDao saleFormDao;
    
    @Autowired
    private NoticeDao noticeDao;
    /**
     * 页面
     * @return
     */
    @RequestMapping(value = "/toAddMember",method = RequestMethod.GET)
    public String index(Model model){

        List<SaleRule> saleRules = saleRuleDao.selectAll();

        model.addAttribute("saleRules",saleRules);
        return "member/addMember";
    }

    /**
     * 添加报单 请求
     * @param saleForm
     * @return
     */
    @RequestMapping(value = "/createMember",method = RequestMethod.POST)
    public String addMember(SaleForm saleForm, Model model){
        saleForm.setFormType("sys");
        saleFormDao.save(saleForm);

        //通知
        Result result = new Result();
        result.setHref("member/toAddMember");
        result.setMsg("添加成功");
        model.addAttribute("result",result);
        return "common/result";
    }

    /**
     * 分页显示
     * @param pageIndex
     * @return
     */
    @RequestMapping(value = "/memberList",method = RequestMethod.GET)
    public String memberList(@RequestParam(value = "pageIndex",required = false) Integer pageIndex,Model model){
        if(pageIndex == null){
            pageIndex = 1;
        }
        Integer counter = memberDao.counter();
        int pageMax = counter/pageShow;
        pageMax++;
        int offset = (pageIndex-1)*pageShow;
        int last = counter - offset;
        if(last >0){
            int limit = 0;
            if(last >= pageShow){
                limit = pageShow;
            }else {
                limit = last;
            }
            List<Member> memberList = memberDao.selectByIndex(limit,offset);
            model.addAttribute("pageMax",pageMax);
            model.addAttribute("pageIndex",pageIndex);
            model.addAttribute("counter",counter.intValue());
            model.addAttribute("pageShow",pageShow);
            model.addAttribute("memberList",memberList);
        }
        model.addAttribute("href","member/memberDetail");
        return "member/memberList";
    }



    @RequestMapping(value = "/memberDetail",method = RequestMethod.GET)
    public String memberDetail(@RequestParam("memberId") String memberId,Model model){
        Member member = memberService.getMember(memberId);
        model.addAttribute("member",member);
//        Member referee = memberService.getMember(member.getRefereeId());
//        if(referee != null){
//            model.addAttribute("referee",referee);
//        }
        model.addAttribute("href","member/memberDetail");
        return "member/memberDetail";
    }
    
    
    /**
     * 通知列表 支持分页
     * @param pageIndex
     * @param model
     * @return
     */
    @RequestMapping(value="/notice",method=RequestMethod.GET)
    public String toNotice(@RequestParam(value = "pageIndex",required = false) Integer pageIndex,Model model) {
    	int pageShowSp =5;
    	if(pageIndex == null){
            pageIndex = 1;
        }
    	List<Notice> notices = noticeDao.selectAll();
        Integer counter = notices.size();
        int pageMax = counter/pageShowSp;
        pageMax++;
        int offset = (pageIndex-1)*pageShowSp;
        int last = counter - offset;
        if(last >0){
            int limit = 0;
            if(last >= pageShowSp){
                limit = pageShowSp;
            }else {
                limit = last;
            }
            notices = noticeDao.selectByPage(limit, offset);
            model.addAttribute("pageMax",pageMax);
            model.addAttribute("pageIndex",pageIndex);
            model.addAttribute("counter",counter.intValue());
            model.addAttribute("pageShow",pageShowSp);
            model.addAttribute("notices",notices);
        }
    	return "sys/notice";
    }
    
    
    
    
    @RequestMapping(value="/addNotice",method=RequestMethod.POST)
    public String addNotice(Notice notice) {
    	noticeDao.save(notice);
    	return "redirect:notice";
    }
    
    
    @RequestMapping(value="/noticeDetail",method=RequestMethod.GET)
    public String noticeDetail(@RequestParam("noticeId") String noticeId,Model model) {
    	Notice notice = noticeDao.selectById(noticeId);
    	model.addAttribute("notice", notice);
    	return "sys/noticeDetail";
    }
    
    
    
    @RequestMapping(value="/delNotice",method=RequestMethod.GET)
    public String delNotice(@RequestParam("noticeId") String noticeId) {
    	noticeDao.delById(noticeId);
    	return "redirect:notice";
    }
    
    
    
}
