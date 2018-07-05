package xyz.nxlexiaoyao.sales.schedule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import xyz.nxlexiaoyao.sales.bean.SaleForm;
import xyz.nxlexiaoyao.sales.bean.SaleRule;
import xyz.nxlexiaoyao.sales.common.MobileSession;
import xyz.nxlexiaoyao.sales.dao.SaleFormDao;
import xyz.nxlexiaoyao.sales.dao.SaleRuleDao;

@Component
public class UpdateTotalReferee {
	@Autowired
	private SaleFormDao saleFormDao;
	
	@Autowired
	private SaleRuleDao saleRuleDao;
	
	
	
	@Scheduled(cron = "0 0 0 * * ?")
	public void clearSession() {
		MobileSession.check();
	}
	
	@Scheduled(cron = "* */2 * * * ?")
	public void updateTotalReferee() {
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
