package xyz.nxlexiaoyao.sales.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import xyz.nxlexiaoyao.sales.bean.Result;
import xyz.nxlexiaoyao.sales.bean.SaleRule;
import xyz.nxlexiaoyao.sales.dao.RebateDao;
import xyz.nxlexiaoyao.sales.dao.SaleRuleDao;

@Controller
@RequestMapping("/sale")
public class SaleRuleController {
	@Autowired
	private SaleRuleDao saleRuleDao;
	@Autowired
	private RebateDao directionalDao;

	@RequestMapping(value = "/toRules", method = RequestMethod.GET)
	public String toRules(Model model) {
		List<SaleRule> saleRules = saleRuleDao.selectAll();
		saleRules.forEach(r -> {
			int outNumber = r.getOutNumber();
			int outTotal = 0;
			for (int i = 0; i < outNumber + 1; i++) {
				outTotal += Math.pow(3, i);
			}
			r.setOutNumber(outTotal);
		});
		model.addAttribute("saleRules", saleRules);
		return "rule/rules";
	}

	@RequestMapping(value = "/toAddRule", method = RequestMethod.GET)
	public String toAddRule() {
		return "rule/addRule";
	}

	@RequestMapping(value = "/addRule", method = RequestMethod.POST)
	public String addRule(SaleRule saleRule, Model model) {
		Result result = new Result();
		result.setHref("sale/toRules");
		saleRuleDao.save(saleRule);
		result.setMsg("设置成功");
		model.addAttribute("result", result);
		return "common/result";
	}
	
	
	/**
	 * 编辑页面
	 * @param ruleId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toEdit", method = RequestMethod.GET)
	public String toEidtRule(@RequestParam("ruleId") String ruleId, Model model) {
		
		
		
		SaleRule rule = saleRuleDao.selectById(ruleId);
		model.addAttribute("rule", rule);
		
		return "rule/edit";
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String eidtRule(SaleRule rule, Model model) {
		Result result = new Result();
		result.setHref("sale/toRules");
		result.setMsg("设置成功");
		result.setMsg("设置成功");
		model.addAttribute("result", result);
		
		saleRuleDao.updateAll(rule);
		
		return "common/result";
	}
	
	/**
	 * 详情页面
	 * @param ruleId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toDetail", method = RequestMethod.GET)
	public String toDetail(@RequestParam("ruleId") String ruleId, Model model) {
		SaleRule rule = saleRuleDao.selectById(ruleId);
		int outNumber = rule.getOutNumber();
		int outTotal = 0;
		for (int i = 0; i < outNumber + 1; i++) {
			outTotal += Math.pow(3, i);
		}
		rule.setOutNumber(outTotal);
		model.addAttribute("rule", rule);
		return "rule/detail";
	}
	
	/**
	 * 删除网络
	 * 
	 * @return
	 */
	@RequestMapping(value = "/del", method = RequestMethod.GET)
	public String del(@RequestParam("ruleId") String ruleId) {
		saleRuleDao.del(ruleId);
		// directionalDao.del(ruleId);
		return "forward:toRules";
	}
}
