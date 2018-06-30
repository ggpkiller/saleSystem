package xyz.nxlexiaoyao.sales;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import xyz.nxlexiaoyao.sales.bean.Manager;
import xyz.nxlexiaoyao.sales.dao.ManagerDao;
import xyz.nxlexiaoyao.sales.util.PasswordUtil;
import xyz.nxlexiaoyao.sales.util.RandomStrUtil;

public class TestAddManager extends TestParent {
	@Autowired
	private ManagerDao managerDao;
	
	@Test
	public void testAddManager() {
		Manager manager = new Manager();
		manager.setAccount("ggp");
		manager.setName("郭大叔");
		manager.setRights("568b7a97-6d2f-44b5-bcaf-69a4b746a1ce;"
				+ "f8b3b18d-73e1-4226-85cd-7968f379ff04;"
				+ "21d1fef9-2908-4918-945e-acfa1aec3796;"
				+ "873bfafe-cb4d-4f20-9320-a2b73a06c229;"
				+ "16bde779-7d77-4fdf-a40b-675359428395;"
				+ "9cbb0a73-7eba-432c-900d-c5633c8ff668");
		
		String salt = RandomStrUtil.getRandomStr(8);
		
    	String password = PasswordUtil.getMD5Password("123", salt);
    	manager.setPassword(password);
    	manager.setSalt(salt);
    	managerDao.save(manager);
	}
}
