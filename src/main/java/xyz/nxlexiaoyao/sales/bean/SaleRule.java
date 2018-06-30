package xyz.nxlexiaoyao.sales.bean;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class SaleRule {
    private String id;
    private String ruleName;
    @JSONField(serialize = false)
    private Integer outNumber;
    @JSONField(serialize = false)
    private Integer rebate;
    private Integer formPrice;
    @JSONField(serialize = false)
    private Date insertTime;

    private Integer shareReferee;
    private Integer shareRebate;
    private Integer shareUnderNumber;
    
    /*private Integer commonFirstReferee;
    private Integer commonFirstRank;
    private Integer commonFirstRebate;
    
    private Integer commonSecondReferee;
    private Integer commonSecondRank;
    private Integer commonSecondRebate;
    
    private Integer commonThirdReferee;
    private Integer commonThirdRank;
    private Integer commonThirdRebate;*/
    
    private Integer leaderReferee;
    private Integer leaderUnderNumber;
    
    private Integer leaderFirstRank;
    private Integer leaderFirstRebate;
    
    private Integer leaderSecondRank;
    private Integer leaderSecondRebate;
    
    private Integer leaderThirdRank;
    private Integer leaderThirdRebate;
    
    private Integer noshareFirstRank;
    private Integer noshareFirstRebate;
    
    private Integer noshareSecondRank;
    private Integer noshareSecondRebate;
    
    
    private Integer noshareThirdRank;
    private Integer noshareThirdRebate;
    
    
    
    
    /**
	 * @return the noshareFirstRank
	 */
	public Integer getNoshareFirstRank() {
		return noshareFirstRank;
	}

	/**
	 * @param noshareFirstRank the noshareFirstRank to set
	 */
	public void setNoshareFirstRank(Integer noshareFirstRank) {
		this.noshareFirstRank = noshareFirstRank;
	}

	/**
	 * @return the noshareFirstRebate
	 */
	public Integer getNoshareFirstRebate() {
		return noshareFirstRebate;
	}

	/**
	 * @param noshareFirstRebate the noshareFirstRebate to set
	 */
	public void setNoshareFirstRebate(Integer noshareFirstRebate) {
		this.noshareFirstRebate = noshareFirstRebate;
	}

	/**
	 * @return the noshareSecondRank
	 */
	public Integer getNoshareSecondRank() {
		return noshareSecondRank;
	}

	/**
	 * @param noshareSecondRank the noshareSecondRank to set
	 */
	public void setNoshareSecondRank(Integer noshareSecondRank) {
		this.noshareSecondRank = noshareSecondRank;
	}

	/**
	 * @return the noshareSecondRebate
	 */
	public Integer getNoshareSecondRebate() {
		return noshareSecondRebate;
	}

	/**
	 * @param noshareSecondRebate the noshareSecondRebate to set
	 */
	public void setNoshareSecondRebate(Integer noshareSecondRebate) {
		this.noshareSecondRebate = noshareSecondRebate;
	}

	/**
	 * @return the noshareThirdRank
	 */
	public Integer getNoshareThirdRank() {
		return noshareThirdRank;
	}

	/**
	 * @param noshareThirdRank the noshareThirdRank to set
	 */
	public void setNoshareThirdRank(Integer noshareThirdRank) {
		this.noshareThirdRank = noshareThirdRank;
	}

	/**
	 * @return the noshareThirdRebate
	 */
	public Integer getNoshareThirdRebate() {
		return noshareThirdRebate;
	}

	/**
	 * @param noshareThirdRebate the noshareThirdRebate to set
	 */
	public void setNoshareThirdRebate(Integer noshareThirdRebate) {
		this.noshareThirdRebate = noshareThirdRebate;
	}

	/**
	 * @return the shareReferee
	 */
	public Integer getShareReferee() {
		return shareReferee;
	}

	/**
	 * @param shareReferee the shareReferee to set
	 */
	public void setShareReferee(Integer shareReferee) {
		this.shareReferee = shareReferee;
	}

	/**
	 * @return the shareRebate
	 */
	public Integer getShareRebate() {
		return shareRebate;
	}

	/**
	 * @param shareRebate the shareRebate to set
	 */
	public void setShareRebate(Integer shareRebate) {
		this.shareRebate = shareRebate;
	}

	/**
	 * @return the shareUnderNumber
	 */
	public Integer getShareUnderNumber() {
		return shareUnderNumber;
	}

	/**
	 * @param shareUnderNumber the shareUnderNumber to set
	 */
	public void setShareUnderNumber(Integer shareUnderNumber) {
		this.shareUnderNumber = shareUnderNumber;
	}


	/**
	 * @return the leaderReferee
	 */
	public Integer getLeaderReferee() {
		return leaderReferee;
	}

	/**
	 * @param leaderReferee the leaderReferee to set
	 */
	public void setLeaderReferee(Integer leaderReferee) {
		this.leaderReferee = leaderReferee;
	}

	/**
	 * @return the leaderUnderNumber
	 */
	public Integer getLeaderUnderNumber() {
		return leaderUnderNumber;
	}

	/**
	 * @param leaderUnderNumber the leaderUnderNumber to set
	 */
	public void setLeaderUnderNumber(Integer leaderUnderNumber) {
		this.leaderUnderNumber = leaderUnderNumber;
	}

	/**
	 * @return the leaderFirstRank
	 */
	public Integer getLeaderFirstRank() {
		return leaderFirstRank;
	}

	/**
	 * @param leaderFirstRank the leaderFirstRank to set
	 */
	public void setLeaderFirstRank(Integer leaderFirstRank) {
		this.leaderFirstRank = leaderFirstRank;
	}

	/**
	 * @return the leaderFirstRebate
	 */
	public Integer getLeaderFirstRebate() {
		return leaderFirstRebate;
	}

	/**
	 * @param leaderFirstRebate the leaderFirstRebate to set
	 */
	public void setLeaderFirstRebate(Integer leaderFirstRebate) {
		this.leaderFirstRebate = leaderFirstRebate;
	}

	/**
	 * @return the leaderSecondRank
	 */
	public Integer getLeaderSecondRank() {
		return leaderSecondRank;
	}

	/**
	 * @param leaderSecondRank the leaderSecondRank to set
	 */
	public void setLeaderSecondRank(Integer leaderSecondRank) {
		this.leaderSecondRank = leaderSecondRank;
	}

	/**
	 * @return the leaderSecondRebate
	 */
	public Integer getLeaderSecondRebate() {
		return leaderSecondRebate;
	}

	/**
	 * @param leaderSecondRebate the leaderSecondRebate to set
	 */
	public void setLeaderSecondRebate(Integer leaderSecondRebate) {
		this.leaderSecondRebate = leaderSecondRebate;
	}

	/**
	 * @return the leaderThirdRank
	 */
	public Integer getLeaderThirdRank() {
		return leaderThirdRank;
	}

	/**
	 * @param leaderThirdRank the leaderThirdRank to set
	 */
	public void setLeaderThirdRank(Integer leaderThirdRank) {
		this.leaderThirdRank = leaderThirdRank;
	}

	/**
	 * @return the leaderThirdRebate
	 */
	public Integer getLeaderThirdRebate() {
		return leaderThirdRebate;
	}

	/**
	 * @param leaderThirdRebate the leaderThirdRebate to set
	 */
	public void setLeaderThirdRebate(Integer leaderThirdRebate) {
		this.leaderThirdRebate = leaderThirdRebate;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public Integer getOutNumber() {
        return outNumber;
    }

    public void setOutNumber(Integer outNumber) {
        this.outNumber = outNumber;
    }

    public Integer getRebate() {
        return rebate;
    }

    public void setRebate(Integer rebate) {
        this.rebate = rebate;
    }

    public Integer getFormPrice() {
        return formPrice;
    }

    public void setFormPrice(Integer formPrice) {
        this.formPrice = formPrice;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
}
