package xyz.nxlexiaoyao.sales.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import xyz.nxlexiaoyao.sales.bean.MobileApp;
import xyz.nxlexiaoyao.sales.dao.MobileAppDao;

@Controller
@RequestMapping("/sys")
public class SysController {
    @Autowired
    private MobileAppDao mobileAppDao;
    @RequestMapping(value = "/toApp")
    public String toApp(Model model){
        MobileApp mobileApp = mobileAppDao.getLastMobileApp();
        model.addAttribute("app",mobileApp);
        return "sys/appManage";
    }


    
    @RequestMapping(value = "/addApp",method = RequestMethod.POST)
    public String addApp(MobileApp mobileApp){
    	mobileAppDao.delAll();
        mobileAppDao.save(mobileApp);
        return "redirect:toApp";
    }
}
