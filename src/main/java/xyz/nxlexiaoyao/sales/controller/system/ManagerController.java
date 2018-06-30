package xyz.nxlexiaoyao.sales.controller.system;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import xyz.nxlexiaoyao.sales.bean.Manager;
import xyz.nxlexiaoyao.sales.bean.Menu;
import xyz.nxlexiaoyao.sales.bean.Result;
import xyz.nxlexiaoyao.sales.dao.ManagerDao;
import xyz.nxlexiaoyao.sales.dao.MenuDao;
import xyz.nxlexiaoyao.sales.util.PasswordUtil;
import xyz.nxlexiaoyao.sales.util.RandomStrUtil;

@Controller
@RequestMapping("/")
public class ManagerController {
    @Autowired
    private MenuDao menuDao;
    @Autowired
    private ManagerDao managerDao;


    /**
     * 首页-登录页面
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String index(){
        return "index";
    }


    /**
     * 登录请求
     * @param manager
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login (Manager manager, HttpSession session,Model model){
    	Result result = new Result();
        if(manager != null){
            String account = manager.getAccount().trim();
            String password = manager.getPassword().trim();
            
            if(!StringUtils.isEmpty(account) && !StringUtils.isEmpty(password)){
            	Manager user = managerDao.selectByAccount(account);
            	if(user != null) {
            		
            		String salt = user.getSalt();
            		
            		String pwdMd5 = PasswordUtil.getMD5Password(password, salt);
            		String pwdData = user.getPassword();
            		
            		if(pwdMd5.equals(pwdData)){
                        List<Menu> menuList = menuDao.selectAll();
                        List<Menu> parents = new ArrayList<Menu>();
                        Set<String> rights = new HashSet<>(Arrays.asList(user.getRights().split(";")));
                        menuList.forEach(menu -> {
                        	
                            String parentId = menu.getId();
                            List<Menu> children = new ArrayList<Menu>();
                            if(menu.getLevel().intValue() == 1){
                            	if(rights.contains(menu.getId())) {
                            		menuList.forEach(child ->{
                                        if(child.getLevel().intValue() == 2 && parentId.equals(child.getParent())){
                                            children.add(child);
                                        }
                                    });
                                    menu.setChildren(children);
                                    parents.add(menu);
                            	}
                            }
                        });

                        session.setAttribute("menuList",parents);
                        
                        session.setAttribute("manager",user);
                        
                        result.setMsg("登录成功");
                        result.setHref("member/memberList");
                        model.addAttribute("result",result);
                        return "common/result";
                    }
            	}
            }
        }
        result.setMsg("登录失败");
        result.setHref("");
        model.addAttribute("result",result);
        return "common/result";
    }



    @RequestMapping(value = "/signOut",method = RequestMethod.GET)
    public String signOut(Model model,HttpSession session){
        session.removeAttribute("manager");
        session.removeAttribute("menuList");
        Result result = new Result();
        result.setMsg("已安全退出");
        result.setHref("");
        model.addAttribute("result",result);
        return "common/result";
    }
    
    
    @RequestMapping(value = "/toAddManger",method = RequestMethod.GET)
    public String toAddManager(Model model) {
    	
    	List<Menu> menus = menuDao.selectAll();
    	List<Menu> rights = new ArrayList<>();
    	menus.forEach(menu->{
    		if(menu.getLevel() == 1) {
    			rights.add(menu);
    		}
    	});
    	model.addAttribute("rights", rights);
    	return "sys/addManage";
    }
    
    @RequestMapping(value="/managerList",method=RequestMethod.GET)
    public String managerList(Model model) {
    	List<Manager> managers = managerDao.selectAll();
    	
    	model.addAttribute("managers", managers);
    	return "sys/managerList";
    }
    
    
    
    @RequestMapping(value="/addManager",method=RequestMethod.POST)
    public String addManager(Model model,Manager manager) {
    	Result result = new Result();
    	if(managerDao.selectByAccount(manager.getAccount()) != null) {
    		result.setHref("toAddManger");
        	result.setMsg("账号已存在");
        	model.addAttribute("result",result);
        	return "common/result";
    	}
    	String salt = RandomStrUtil.getRandomStr(8);
    	String password = PasswordUtil.getMD5Password(manager.getPassword(), salt);
    	manager.setPassword(password);
    	String rights = String.join(";", manager.getRightArry());
    	manager.setRights(rights);
    	manager.setSalt(salt);
    	managerDao.save(manager);
    	
    	
    	
    	result.setHref("managerList");
    	result.setMsg("创建成功");
    	model.addAttribute("result",result);
    	return "common/result";
    }
    
    
    
    @RequestMapping(value="/delManager",method=RequestMethod.GET)
    public String delManager(@RequestParam("managerId") String managerId) {
    	managerDao.delById(managerId);
    	return "forward:managerList";
    }
}
