package xyz.nxlexiaoyao.sales;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import xyz.nxlexiaoyao.sales.bean.Member;
import xyz.nxlexiaoyao.sales.bean.SaleForm;
import xyz.nxlexiaoyao.sales.dao.MemberDao;
import xyz.nxlexiaoyao.sales.dao.SaleFormDao;
import xyz.nxlexiaoyao.sales.util.ChineseNameGeneric;
import xyz.nxlexiaoyao.sales.util.PasswordUtil;
import xyz.nxlexiaoyao.sales.util.RandomStrUtil;

public class MakeData extends TestParent{
	@Autowired
	private SaleFormDao saleFormDao;
	@Autowired
	private MemberDao memberDao;
	
	private final AtomicInteger COUNTER = new AtomicInteger();
	
	private final int MAX = 400;
	
	@Test
	public void testMakeForm() {
		
		int len = 3;
		
		for(int i=0;i<len;i++) {
			String name = ChineseNameGeneric.generalCnName(1).get(0);
			String mobile = RandomStrUtil.getRandomNumberStr(11);
			String bankAccount = RandomStrUtil.getRandomNumberStr(16);
			String bankName = "中国银行";
			String idNumber = RandomStrUtil.getRandomNumberStr(18);
			String address = "世纪大道";
			String ruleId = "b93ee2b0-928b-47b8-9002-93315bbc5d80";
			String refereeId = "6b4080fa-f4aa-41bf-9eaa-7abbd5145f6c";
			
			
			SaleForm saleForm = new SaleForm();
			String formId = UUID.randomUUID().toString();
			saleForm.setId(formId);
			saleForm.setName(name);
			saleForm.setMobile(mobile);
			saleForm.setBankAccount(bankAccount);
			saleForm.setBankName(bankName);
			saleForm.setIdNumber(idNumber);
			saleForm.setAddress(address);
			saleForm.setRuleId(ruleId);
			
			saleForm.setFormType("sys");
			if(COUNTER.get() >= MAX) {
				return;
			}
			makeSomeData(saleForm,refereeId);
			
		}
	
	}
	
	
	private void makeSomeData(SaleForm saleForm,String refereeId) {
		Member member = new Member();
        String memberId = UUID.randomUUID().toString();
        member.setId(memberId);
        String password = "123456";
        String salt = RandomStrUtil.getRandomStr(8);
        password = PasswordUtil.getMD5Password(password,salt);
        member.setSalt(salt);
        member.setPassword(password);
        member.setMobile(saleForm.getMobile());
        member.setAccount(saleForm.getMobile());
        member.setIdNumber(saleForm.getIdNumber());
        member.setRefereeId(saleForm.getMemberId());
        member.setBankName(saleForm.getBankName());
        member.setBankAccount(saleForm.getBankAccount());
        member.setAddress(saleForm.getAddress());
        member.setName(saleForm.getName());
        memberDao.save(member);
        saleForm.setMemberId(memberId);
		
		saleForm.setRefereeId(refereeId);
		
		
		saleFormDao.save(saleForm);
		saleFormDao.verifyForm(saleForm);
		COUNTER.incrementAndGet();
		if(COUNTER.get() >= MAX) {
			return;
		}
		for(int i=0;i<3;i++) {
			String name = ChineseNameGeneric.generalCnName(1).get(0);
			String mobile = RandomStrUtil.getRandomNumberStr(11);
			String bankAccount = RandomStrUtil.getRandomNumberStr(16);
			String bankName = "中国银行";
			String idNumber = RandomStrUtil.getRandomNumberStr(18);
			String address = "世纪大道";
			String ruleId = "b93ee2b0-928b-47b8-9002-93315bbc5d80";
			String newRefereeId = saleForm.getId();
			
			
			SaleForm newSaleForm = new SaleForm();
			String formId = UUID.randomUUID().toString();
			newSaleForm.setId(formId);
			newSaleForm.setName(name);
			newSaleForm.setMobile(mobile);
			newSaleForm.setBankAccount(bankAccount);
			newSaleForm.setBankName(bankName);
			newSaleForm.setIdNumber(idNumber);
			newSaleForm.setAddress(address);
			newSaleForm.setRuleId(ruleId);
			
			newSaleForm.setFormType("sys");
			makeSomeData(newSaleForm,newRefereeId);
		}
	}
}
