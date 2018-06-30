package xyz.nxlexiaoyao.sales.bean;

import java.util.Date;

public class Income {
	private String incomeId;
	private String incomeContent;
	private String memberId;
	private Integer incomeAmount;
	private Date insertTime;
	
	
	
	
	/**
	 * @return the insertTime
	 */
	public Date getInsertTime() {
		return insertTime;
	}
	/**
	 * @param insertTime the insertTime to set
	 */
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	/**
	 * @return the incomeId
	 */
	public String getIncomeId() {
		return incomeId;
	}
	/**
	 * @param incomeId the incomeId to set
	 */
	public void setIncomeId(String incomeId) {
		this.incomeId = incomeId;
	}
	/**
	 * @return the incomeContent
	 */
	public String getIncomeContent() {
		return incomeContent;
	}
	/**
	 * @param incomeContent the incomeContent to set
	 */
	public void setIncomeContent(String incomeContent) {
		this.incomeContent = incomeContent;
	}
	/**
	 * @return the memberId
	 */
	public String getMemberId() {
		return memberId;
	}
	/**
	 * @param memberId the memberId to set
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	/**
	 * @return the incomeAmount
	 */
	public Integer getIncomeAmount() {
		return incomeAmount;
	}
	/**
	 * @param incomeAmount the incomeAmount to set
	 */
	public void setIncomeAmount(Integer incomeAmount) {
		this.incomeAmount = incomeAmount;
	}
	
	
	
}
