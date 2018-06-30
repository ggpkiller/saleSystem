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
		List<SaleRule> rules = saleRuleDao.selectAll();
		rules.forEach(rule->{
			String ruleId = rule.getId();
			List<SaleForm> forms = saleFormDao.selectOnlineAll(ruleId);
			forms.forEach(form->{
				List<SaleForm> relationsDown = saleFormDao.relationsDown(form);
				if(relationsDown != null && relationsDown.size() >0) {
					form.setTotalRefereCount(relationsDown.size());
					saleFormDao.increaseRefereeTotalSelf(form);
				}
			});
		});
	}
}
