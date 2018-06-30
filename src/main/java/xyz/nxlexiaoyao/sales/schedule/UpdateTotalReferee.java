package xyz.nxlexiaoyao.sales.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import xyz.nxlexiaoyao.sales.common.MobileSession;

@Component
public class UpdateTotalReferee {

	@Scheduled(cron = "0 0 0 * * ?")
	public void clearSession() {
		MobileSession.check();
	}
}
