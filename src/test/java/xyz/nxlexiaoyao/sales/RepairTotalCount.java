package xyz.nxlexiaoyao.sales;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import xyz.nxlexiaoyao.sales.bean.SaleForm;
import xyz.nxlexiaoyao.sales.bean.SaleRule;
import xyz.nxlexiaoyao.sales.dao.SaleFormDao;
import xyz.nxlexiaoyao.sales.dao.SaleRuleDao;

public class RepairTotalCount extends TestParent {
	@Autowired
	private SaleFormDao saleFormDao;
	
	@Autowired
	private SaleRuleDao saleRuleDao;
	
	@Test
	public void testRun() {
//		List<SaleRule> rules = saleRuleDao.selectAll();
//		rules.forEach(rule->{
//			String ruleId = rule.getId();
//			List<SaleForm> forms = saleFormDao.selectOnlineAll(ruleId);
//			forms.forEach(form->{
//				List<SaleForm> relationsDown = saleFormDao.relationsDown(form);
//				if(relationsDown != null && relationsDown.size() >0) {
//					form.setTotalRefereCount(relationsDown.size());
//					saleFormDao.increaseRefereeTotalSelf(form);
//				}
//			});
//		});
		
		
		String ruleId = "6e28582b-569c-47bb-8f4d-450070c15734";
		List<SaleForm> forms = saleFormDao.selectOnlineAll(ruleId);
		forms.forEach(form->{
			List<SaleForm> relationsDown = saleFormDao.relationsDown(form);
			if(relationsDown != null && relationsDown.size() >0) {
				form.setTotalRefereCount(relationsDown.size());
				saleFormDao.increaseRefereeTotalSelf(form);
			}
		});
	}
	
	
	@Test
	public void testError() {
		System.out.println("陈辉");
		suvies("陈辉","--");
	}
	
	public void suvies(String refereeId,String separator) {
		List<SaleForm> children = saleFormDao.selectByRefereeIdPlus(refereeId, "6e28582b-569c-47bb-8f4d-450070c15734");
		children.forEach(child->{
			System.out.println(separator+child.getName());
			suvies(child.getName(), separator+"--");
		});
	}
}
