package xyz.nxlexiaoyao.sales.controller.system;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import xyz.nxlexiaoyao.sales.bean.Income;
import xyz.nxlexiaoyao.sales.bean.Member;
import xyz.nxlexiaoyao.sales.bean.Result;
import xyz.nxlexiaoyao.sales.bean.SaleForm;
import xyz.nxlexiaoyao.sales.bean.SaleRule;
import xyz.nxlexiaoyao.sales.dao.IncomeDao;
import xyz.nxlexiaoyao.sales.dao.MemberDao;
import xyz.nxlexiaoyao.sales.dao.RebateDao;
import xyz.nxlexiaoyao.sales.dao.SaleFormDao;
import xyz.nxlexiaoyao.sales.dao.SaleRuleDao;
import xyz.nxlexiaoyao.sales.service.SaleFormService;
import xyz.nxlexiaoyao.sales.util.MathUtil;
import xyz.nxlexiaoyao.sales.util.PasswordUtil;
import xyz.nxlexiaoyao.sales.util.RandomStrUtil;
import xyz.nxlexiaoyao.sales.vo.LineMenuVo;

@Controller
@RequestMapping("/form")
public class SaleFormController {
	private final int pageShow = 15;
	@Autowired
	private SaleFormDao saleFormDao;
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private SaleRuleDao saleRuleDao;
	@Autowired
	private RebateDao rebateDao;
	@Autowired
	private SaleFormService saleFormService;
	@Autowired
	private IncomeDao incomeDao;

	@RequestMapping(value = "/verifyList", method = RequestMethod.GET)
	public String toVerifyList(@RequestParam(value = "pageIndex", required = false) Integer pageIndex, Model model) {
		if (pageIndex == null) {
			pageIndex = 1;
		}
		SaleForm saleForm = new SaleForm();
		saleForm.setVerifyStatus("N");
		List<SaleForm> list = saleFormDao.select(saleForm);
		Integer counter = list.size();
		int pageMax = counter / pageShow;
		pageMax++;
		int offset = (pageIndex - 1) * pageShow;
		int last = counter - offset;
		if (last > 0) {
			int limit = 0;
			if (last >= pageShow) {
				limit = pageShow;
			} else {
				limit = last;
			}
			saleForm.setLimit(limit);
			saleForm.setOffset(offset);
			list = saleFormDao.select(saleForm);
			model.addAttribute("pageMax", pageMax);
			model.addAttribute("pageIndex", pageIndex);
			model.addAttribute("counter", counter.intValue());
			model.addAttribute("pageShow", pageShow);
			model.addAttribute("list", list);
		}
		return "form/verifyList";
	}

	/**
	 * 报单详情页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/singleForm", method = RequestMethod.GET)
	public String singleForm(@RequestParam("formId") String formId, Model model) {
		SaleForm saleForm = new SaleForm();
		saleForm.setVerifyStatus("N");
		saleForm.setId(formId);
		SaleForm newForm = saleFormDao.select(saleForm).get(0);
		String mobile = newForm.getMobile();
		Member same = memberDao.selectByAccount(mobile);
		if (same != null) {
			newForm.setIsMember("Y");
		} else {
			newForm.setIsMember("N");
		}
		model.addAttribute("form", newForm);
		return "form/singleForm";
	}

	/**
	 * 报单：审核通过 新建账号（新用户） 更新标记改为Y 更新memberId 更新insertNo和currentNo 更新updateTime为当时时间
	 * 
	 * @param saleForm
	 * @return
	 */
	@RequestMapping(value = "/verifyForm", method = RequestMethod.POST)
	public String verifyForm(SaleForm saleForm, Model model, @RequestParam("menuId") String menuId) {
		Result result = new Result();
		result.setMsg("审核通过");
		result.setHref("form/verifyList?menuId=" + menuId);

		SaleForm newForm = saleFormDao.select(saleForm).get(0);
		String mobile = newForm.getMobile();
		Member same = memberDao.selectByAccount(mobile);
		if (same == null) {
			Member member = new Member();
			String memberId = UUID.randomUUID().toString();
			member.setId(memberId);
			String password = "123456";
			String salt = RandomStrUtil.getRandomStr(8);
			password = PasswordUtil.getMD5Password(password, salt);
			member.setSalt(salt);
			member.setPassword(password);
			member.setMobile(mobile);
			member.setAccount(mobile);
			member.setIdNumber(newForm.getIdNumber());
			// member.setRefereeId(newForm.getMemberId());
			member.setBankName(newForm.getBankName());
			member.setBankAccount(newForm.getBankAccount());
			member.setAddress(newForm.getAddress());
			member.setName(newForm.getName());
			memberDao.save(member);
			saleForm.setMemberId(memberId);
		} else {
			saleForm.setMemberId(newForm.getMemberId());
		}
		saleFormDao.verifyForm(saleForm);
		//找到他的上级，上上级等，向上递归
		List<SaleForm> relations = saleFormDao.relationsUp(saleForm);
		//关系人每人递增1个总推荐人
		saleFormDao.increaseRefereeTotal(relations);
		//找到他的下级、下下级、向下递归
		List<SaleForm> relationDown = saleFormDao.relationsDown(saleForm);
		int totalRefereeCount = 0;
		if(relationDown != null) {
			totalRefereeCount = relationDown.size();
		}
		saleForm.setTotalRefereCount(totalRefereeCount);
		//更新自己的总推荐人
		saleFormDao.increaseRefereeTotalSelf(saleForm);
		
		model.addAttribute("result", result);
		return "common/result";
	}

	/**
	 * 批量通过 报单：审核通过 新建账号（新用户） 更新标记改为Y 更新memberId 更新insertNo和currentNo
	 * 更新updateTime为当时时间
	 * 
	 * @param formIds
	 * @return
	 */
	@RequestMapping(value = "/verifyForms", method = RequestMethod.POST)
	public String verifyForms(String[] formIds, Model model, @RequestParam("menuId") String menuId) {
		Result result = new Result();
		result.setMsg("审核通过");
		result.setHref("form/verifyList?menuId=" + menuId);
		List<String> ids = Arrays.asList(formIds);
		List<SaleForm> forms = saleFormDao.selectByIds(ids);
		forms.forEach(form -> {
			String mobile = form.getMobile();
			Member same = memberDao.selectByAccount(mobile);
			if (same == null) {
				Member member = new Member();
				String memberId = UUID.randomUUID().toString();
				member.setId(memberId);
				String password = "123456";
				String salt = RandomStrUtil.getRandomStr(8);
				password = PasswordUtil.getMD5Password(password, salt);
				member.setSalt(salt);
				member.setPassword(password);
				member.setMobile(mobile);
				member.setAccount(mobile);
				member.setIdNumber(form.getIdNumber());
				member.setRefereeId(form.getMemberId());
				member.setBankName(form.getBankName());
				member.setBankAccount(form.getBankAccount());
				member.setAddress(form.getAddress());
				member.setName(form.getName());
				memberDao.save(member);
				form.setMemberId(memberId);
			} else {
				form.setMemberId(same.getId());
			}
			saleFormDao.verifyForm(form);
		});
		model.addAttribute("result", result);
		return "common/result";
	}

	/**
	 * 视图需要：规则列表，包含【规则名称，报单数，报单总金额】
	 * 
	 * @return
	 */
	@RequestMapping(value = "/lineMenu", method = RequestMethod.GET)
	public String toLineMenu(Model model) {
		List<LineMenuVo> lineMenuVos = saleFormDao.selectLineMenu();
		model.addAttribute("lineMenus", lineMenuVos);
		return "line/menu";
	}

	/**
	 * 视图要求：出局顺序，排名,姓名，上级推荐人姓名，报单者姓名，直接推荐人数,下级总人数，定拨，出局,公排时间
	 * 
	 * @param model
	 * @param ruleId
	 * @return
	 */
	@RequestMapping(value = "/lineList", method = RequestMethod.GET)
	public String lineList(@RequestParam(value = "pageIndex", required = false) Integer pageIndex, Model model,
			@RequestParam("ruleId") String ruleId, @RequestParam(value = "formId", required = false) String formId,
			@RequestParam(value = "type", required = false) String type) {
		List<SaleForm> tree = saleFormDao.selectAllTree(ruleId);
		SaleRule rule = saleRuleDao.selectById(ruleId);
		// 树状总单数
		int treeCount = tree.size();
		int leaderFirstRank = rule.getLeaderFirstRank().intValue();
		int leaderSecondRank = rule.getLeaderSecondRank().intValue();
		int leaderThirdRank = rule.getLeaderThirdRank().intValue();

		int leaderReferee = rule.getLeaderReferee().intValue();
		int leaderUnderNumber = rule.getLeaderUnderNumber().intValue();

		/*int commonFirstRank = rule.getCommonFirstRank().intValue();
		int commonFirstReferee = rule.getCommonFirstReferee().intValue();

		int commonSecondRank = rule.getCommonSecondRank().intValue();
		int commonSecondReferee = rule.getCommonSecondReferee().intValue();

		int commonThirdRank = rule.getCommonThirdRank().intValue();
		int commonThirdReferee = rule.getCommonThirdReferee().intValue();*/

		int noshareFirstRank = rule.getNoshareFirstRank().intValue();
		int noshareSecondRank = rule.getNoshareSecondRank().intValue();
		int noshareThirdRank = rule.getNoshareThirdRank().intValue();

		SaleForm condition = new SaleForm();
		condition.setRuleId(ruleId);
		condition.setVerifyStatus("Y");
		condition.setIsOut("N");

		if (pageIndex == null) {
			pageIndex = 1;
		}
		List<SaleForm> allFormTree = saleFormDao.selectLineList(condition);
		if (formId != null && !"".equals(formId)) {
			condition.setId(formId);
		}
		List<SaleForm> allForms = saleFormDao.selectLineList(condition);

		Integer counter = allForms.size();
		int pageMax = counter / pageShow;
		pageMax++;
		int offset = (pageIndex - 1) * pageShow;
		int last = counter - offset;
		if (last > 0) {
			int limit = 0;
			if (last >= pageShow) {
				limit = pageShow;
			} else {
				limit = last;
			}
			condition.setLimit(limit);
			condition.setOffset(offset);
			List<SaleForm> saleForms = saleFormDao.selectLineList(condition);
			if (saleForms != null) {
				saleForms.forEach(saleForm -> {
					String fid = saleForm.getId();
					// 间接推荐人数
					int totalReferrCount = 0;
					if (saleForm.getTotalRefereCount() != null) {
						totalReferrCount = saleForm.getTotalRefereCount().intValue();
					}
					int insertNo = saleForm.getInsertNo();
					Set<Integer> myTreeInsertNo = getMyTreeInsertNo(insertNo, treeCount);
					AtomicInteger rank = new AtomicInteger(1);// 每有一个下级，则增加1,就是他自己的排名
					if (!myTreeInsertNo.isEmpty()) {
						Iterator<Integer> ite = myTreeInsertNo.iterator();
						while (ite.hasNext()) {
							int childInsertNo = ite.next();
							Iterator<SaleForm> saleFormIte = allFormTree.iterator();
							while (saleFormIte.hasNext()) {
								SaleForm thisForm = saleFormIte.next();
								if (!fid.equals(thisForm.getId())
										&& childInsertNo == thisForm.getInsertNo().intValue()) {
									rank.incrementAndGet();
								}
							}
						}
					}
					saleForm.setRank(rank.get());

					if (type == null || !type.equals("out")) {
						// 判断定拨条件

						int refereeCount = saleForm.getRefereeCount();// 直推人数

						boolean isLeader = false;
						String findDingboCondition = "N";

						// 领导奖励判断
						if (refereeCount >= leaderReferee && totalReferrCount >= leaderUnderNumber) {
							isLeader = true;
						}
						if (isLeader) {
							if (saleForm.getLeaderFirst().equals("N")) {
								if (rank.intValue() >= leaderFirstRank) {
									findDingboCondition = "Y";
									saleForm.setLeaderFirst("Y");
									saleForm.setLeaderSecond("N");
									saleForm.setLeaderThird("N");
									saleForm.setCommonFirst("N");
									saleForm.setCommonSecond("N");
									saleForm.setCommonThird("N");
									saleForm.setNoshareFirst("N");
									saleForm.setNoshareSecond("N");
									saleForm.setNoshareThird("N");
								}
							}

							if (saleForm.getLeaderSecond().equals("N") && !"Y".equals(findDingboCondition)) {
								if (rank.intValue() >= leaderSecondRank) {
									findDingboCondition = "Y";
									saleForm.setLeaderSecond("Y");
									saleForm.setLeaderFirst("N");
									saleForm.setLeaderThird("N");
									saleForm.setCommonFirst("N");
									saleForm.setCommonSecond("N");
									saleForm.setCommonThird("N");
									saleForm.setNoshareFirst("N");
									saleForm.setNoshareSecond("N");
									saleForm.setNoshareThird("N");
								}
							}

							if (saleForm.getLeaderThird().equals("N") && !"Y".equals(findDingboCondition)) {
								if (rank.intValue() >= leaderThirdRank) {
									findDingboCondition = "Y";
									saleForm.setLeaderThird("Y");
									saleForm.setLeaderFirst("N");
									saleForm.setLeaderSecond("N");
									saleForm.setCommonFirst("N");
									saleForm.setCommonSecond("N");
									saleForm.setCommonThird("N");
									saleForm.setNoshareFirst("N");
									saleForm.setNoshareSecond("N");
									saleForm.setNoshareThird("N");
								}
							}
						}

						// 普通分享奖励判断

						/*if (saleForm.getCommonFirst().equals("N") && !"Y".equals(findDingboCondition)
								&& saleForm.getNoshareFirst().equals("N")) {
							if (rank.intValue() >= commonFirstRank && refereeCount >= commonFirstReferee) {
								findDingboCondition = "Y";
								saleForm.setCommonFirst("Y");
								saleForm.setLeaderFirst("N");
								saleForm.setLeaderSecond("N");
								saleForm.setLeaderThird("N");
								saleForm.setCommonSecond("N");
								saleForm.setCommonThird("N");
								saleForm.setNoshareFirst("N");
								saleForm.setNoshareSecond("N");
								saleForm.setNoshareThird("N");
							}
						}

						if (saleForm.getCommonSecond().equals("N") && !"Y".equals(findDingboCondition)
								&& saleForm.getNoshareSecond().equals("N")) {
							if (rank.intValue() >= commonSecondRank && refereeCount >= commonSecondReferee) {
								findDingboCondition = "Y";
								saleForm.setCommonSecond("Y");
								saleForm.setLeaderFirst("N");
								saleForm.setLeaderSecond("N");
								saleForm.setLeaderThird("N");
								saleForm.setCommonFirst("N");
								saleForm.setCommonThird("N");
								saleForm.setNoshareFirst("N");
								saleForm.setNoshareSecond("N");
								saleForm.setNoshareThird("N");
							}
						}

						if (saleForm.getCommonThird().equals("N") && !"Y".equals(findDingboCondition)
								&& saleForm.getNoshareThird().equals("N")) {
							if (rank.intValue() >= commonThirdRank && refereeCount >= commonThirdReferee) {
								findDingboCondition = "Y";
								saleForm.setCommonThird("Y");
								saleForm.setLeaderFirst("N");
								saleForm.setLeaderSecond("N");
								saleForm.setLeaderThird("N");
								saleForm.setCommonFirst("N");
								saleForm.setCommonSecond("N");
								saleForm.setNoshareFirst("N");
								saleForm.setNoshareSecond("N");
								saleForm.setNoshareThird("N");
							}
						}*/

						// 判断无分享
//						if (refereeCount == 0) {
							if (saleForm.getNoshareFirst().equals("N") && saleForm.getCommonFirst().equals("N")
									&& rank.intValue() >= noshareFirstRank && !"Y".equals(findDingboCondition)) {
								findDingboCondition = "Y";
								saleForm.setNoshareFirst("Y");
								saleForm.setLeaderFirst("N");
								saleForm.setLeaderSecond("N");
								saleForm.setLeaderThird("N");
								saleForm.setCommonFirst("N");
								saleForm.setCommonSecond("N");
								saleForm.setCommonThird("N");
								saleForm.setNoshareSecond("N");
								saleForm.setNoshareThird("N");
							}
							if (saleForm.getNoshareSecond().equals("N") && saleForm.getCommonSecond().equals("N")
									&& rank.intValue() >= noshareSecondRank && !"Y".equals(findDingboCondition)) {
								findDingboCondition = "Y";
								saleForm.setNoshareSecond("Y");
								saleForm.setLeaderFirst("N");
								saleForm.setLeaderSecond("N");
								saleForm.setLeaderThird("N");
								saleForm.setCommonFirst("N");
								saleForm.setCommonSecond("N");
								saleForm.setCommonThird("N");
								saleForm.setNoshareFirst("N");
								saleForm.setNoshareThird("N");
							}
							if (saleForm.getNoshareThird().equals("N") && saleForm.getCommonThird().equals("N")
									&& rank.intValue() >= noshareThirdRank && !"Y".equals(findDingboCondition)) {
								findDingboCondition = "Y";
								saleForm.setNoshareThird("Y");
								saleForm.setLeaderFirst("N");
								saleForm.setLeaderSecond("N");
								saleForm.setLeaderThird("N");
								saleForm.setCommonFirst("N");
								saleForm.setCommonSecond("N");
								saleForm.setCommonThird("N");
								saleForm.setNoshareFirst("N");
								saleForm.setNoshareSecond("N");
							}
//						}
						saleForm.setFindDingboCondition(findDingboCondition);
					}

					if (saleForm.getCurrentNo() == 1) {
						// 判断排位第一的人是否可以出局

						// 规则定义的满足层数
						int maxLayer = rule.getOutNumber();
						// 最下层的单数
						int bottomCount = (int) Math.pow(3, maxLayer);
						// 原始排名
						// 公排要求的人数
						int subordinate = MathUtil.multiplyAndAdd(3, 0, maxLayer);
						model.addAttribute("subordinate", subordinate);
						int satisfyOut = bottomCount * insertNo + subordinate;

						if (satisfyOut <= treeCount) {
							saleForm.setIsOut("Y");
						}
					}
				});
			}

			model.addAttribute("pageMax", pageMax);
			model.addAttribute("pageIndex", pageIndex);
			model.addAttribute("counter", counter.intValue());
			model.addAttribute("pageShow", pageShow);
			model.addAttribute("saleForms", saleForms);
		}

		model.addAttribute("rule", saleRuleDao.selectById(ruleId));
		if (type != null && type.equals("out")) {
			model.addAttribute("rules", saleRuleDao.selectAll());
			return "line/out";
		} else if (formId != null && !"".equals(formId)) {
			return "line/dingbo";
		} else {
			return "line/list";
		}
	}

	@RequestMapping(value = "/regDingbo", method = RequestMethod.POST)
	public String regDingbo(SaleForm saleForm, @RequestParam("menuId") String menuId, Model model) {
		Result result = new Result();
		result.setHref("form/lineList?ruleId=" + saleForm.getRuleId() + "&menuId=" + menuId);
		result.setMsg("登记成功");

		saleFormDao.regDingbo(saleForm);

		model.addAttribute("result", result);

		return "common/result";
	}

	@RequestMapping(value = "/regOut", method = RequestMethod.POST)
	public String regOut(SaleForm saleForm, @RequestParam("menuId") String menuId, Model model) {
		Result result = new Result();

		result.setMsg("出局成功");
		String formId = saleForm.getId();
		saleForm = saleFormDao.selectById(formId);
		SaleForm condition = new SaleForm();
		condition.setVerifyStatus("Y");
		condition.setIsOut("N");
		condition.setId(formId);

		List<SaleRule> rules = saleRuleDao.selectAll();

		SaleForm form = saleFormDao.selectLineList(condition).get(0);

		saleFormDao.out(saleForm);

		String ruleId = form.getRuleId();
		result.setHref("form/lineList?ruleId=" + ruleId + "&menuId=" + menuId);
		SaleRule rule = saleRuleDao.selectById(ruleId);

		String incomeContent = "";
		int totalAmount = 0;
		AtomicInteger deductAmount = new AtomicInteger(0);
		int commonRabate = rule.getRebate();
		int leaderRabate = rule.getShareRebate();

		int refereeCount = form.getRefereeCount();
		int totalRefereeCount = form.getTotalRefereCount();

		boolean isLeader = false;
		if (refereeCount >= rule.getShareReferee() && totalRefereeCount >= rule.getShareUnderNumber()) {
			isLeader = true;
		}

		if (isLeader) {
			incomeContent = rule.getRuleName() + " 领导出局奖励";
			totalAmount = rule.getShareRebate().intValue();
			if (form.getLeaderFirst().equals("Y")) {
				deductAmount.addAndGet(rule.getLeaderFirstRebate().intValue());
			}
			if (form.getLeaderSecond().equals("Y")) {
				deductAmount.addAndGet(rule.getLeaderSecondRebate().intValue());
			}
			if (form.getLeaderThird().equals("Y")) {
				deductAmount.addAndGet(rule.getLeaderThirdRebate().intValue());
			}
		} else {
			incomeContent = rule.getRuleName() + " 普通分享出局奖励";

			totalAmount = rule.getRebate().intValue();
			/*if (form.getCommonFirst().equals("Y")) {
				deductAmount.addAndGet(rule.getCommonFirstRebate().intValue());
			}
			if (form.getCommonSecond().equals("Y")) {
				deductAmount.addAndGet(rule.getCommonSecondRebate().intValue());
			}
			if (form.getCommonThird().equals("Y")) {
				deductAmount.addAndGet(rule.getCommonThirdRebate().intValue());
			}*/

			if (form.getNoshareFirst().equals("Y")) {
				deductAmount.addAndGet(rule.getNoshareFirstRebate().intValue());
			}
			if (form.getNoshareSecond().equals("Y")) {
				deductAmount.addAndGet(rule.getNoshareSecondRebate().intValue());
			}

			if (form.getNoshareThird().equals("Y")) {
				deductAmount.addAndGet(rule.getNoshareThirdRebate().intValue());
			}
		}
		int integral = (int) (totalAmount * 0.2);
		deductAmount.addAndGet(integral);
		deductAmount.addAndGet((int) (totalAmount * 0.1));

		String ruleIds = saleForm.getRuleId();
		if (ruleIds != null && !ruleIds.equals("")) {
			if (ruleIds.endsWith(";")) {
				ruleIds = ruleIds.substring(0, ruleIds.length() - 1);
			}
			List<String> ruleIdArry = Arrays.asList(ruleIds.split(";"));
			ruleIdArry.forEach(ri -> {
				rules.forEach(r -> {
					if (r.getId().equals(ri)) {
						deductAmount.addAndGet(r.getFormPrice().intValue());
						String newFormId = UUID.randomUUID().toString();
						form.setId(newFormId);
						form.setFormType("sys");
						saleFormDao.save(form);
						saleFormDao.verifyForm(form);
					}
				});
			});
		}

		String memberId = form.getMemberId();
		Member member = memberDao.selectById(memberId);
		member.setIntegral(integral);
		memberDao.updateFull(member);
		int income = totalAmount - deductAmount.get();
		Income incomeBean = new Income();
		incomeBean.setIncomeAmount(income);
		incomeBean.setMemberId(memberId);
		incomeBean.setIncomeContent(incomeContent);
		incomeDao.save(incomeBean);

		model.addAttribute("result", result);

		return "common/result";
	}

	@RequestMapping(value = "/delSaleForm", method = RequestMethod.GET)
	public String delSaleForm(@RequestParam("menuId") String menuId, @RequestParam("formId") String formId,
			Model model) {
		Result result = new Result();
		SaleForm saleForm = saleFormDao.selectById(formId);
		String ruleId = saleForm.getRuleId();

		saleFormDao.delById(formId);
		if(saleForm.getVerifyStatus().equals("Y")) {
			saleFormDao.updateInsertNoByNo(saleForm);
			//找到上级、上上级的人
			List<SaleForm> relationsUP = saleFormDao.relationsUp(saleForm);
			saleFormDao.decreaseRefereeTotal(relationsUP);
			result.setHref("form/lineList?menuId=" + menuId + "&ruleId=" + ruleId);
		}else {
			result.setHref("form/verifyList?menuId=" + menuId);
		}
		
		
		result.setMsg("已删除");

		model.addAttribute("result", result);
		return "common/result";
	}

	private static Set<Integer> getMyTreeInsertNo(int insertNo, int treeCount) {
		Set<Integer> myTreeInsertNo = new HashSet<Integer>();
		insertNo *= 3;
		for (int i = 0; i < 3; i++) {
			insertNo++;
			if (insertNo >= treeCount) {
				break;
			}
			Integer thisVal = new Integer(insertNo);
			myTreeInsertNo.add(thisVal);
			Set<Integer> children = getMyTreeInsertNo(thisVal.intValue(), treeCount);
			if (!children.isEmpty()) {
				myTreeInsertNo.addAll(children);
			}
		}
		return myTreeInsertNo;
	}

}
