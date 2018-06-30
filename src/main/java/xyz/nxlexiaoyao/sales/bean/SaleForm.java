package xyz.nxlexiaoyao.sales.bean;

import java.util.Date;

public class SaleForm {

    private String id;
    private String ruleId;
    private String memberId;
    private String refereeId;
    private Date insertTime;
    private String verifyStatus;
    private Integer insertNo;
    private Integer currentNo;
    private String isOut;
    private Integer limit;
    private Integer offset;

    private String name;
    private String mobile;
    private String bankName;
    private String idNumber;
    private String address;
    private String bankAccount;
    private String account;
    private String formType;
    private Date updateTime;


    private String isMember;
    private String ruleName;
    private String memberName;
    private Integer refereeCount;
    private Integer totalRefereCount;
    private Integer underCount;
    
    private String upperName;
    
    
    private String leaderFirst;
    private String leaderSecond;
    private String leaderThird;
    private String commonFirst;
    private String commonSecond;
    private String commonThird;
    private String noshareFirst;
    private String noshareSecond;
    private String noshareThird;
    
    private Integer rank;
    
    private String findDingboCondition;
    
    
    
    
    
    
    /**
	 * @return the rank
	 */
	public Integer getRank() {
		return rank;
	}

	/**
	 * @param rank the rank to set
	 */
	public void setRank(Integer rank) {
		this.rank = rank;
	}

	/**
	 * @return the findDingboCondition
	 */
	public String getFindDingboCondition() {
		return findDingboCondition;
	}

	/**
	 * @param findDingboCondition the findDingboCondition to set
	 */
	public void setFindDingboCondition(String findDingboCondition) {
		this.findDingboCondition = findDingboCondition;
	}

	/**
	 * @return the leaderFirst
	 */
	public String getLeaderFirst() {
		return leaderFirst;
	}

	/**
	 * @param leaderFirst the leaderFirst to set
	 */
	public void setLeaderFirst(String leaderFirst) {
		this.leaderFirst = leaderFirst;
	}

	/**
	 * @return the leaderSecond
	 */
	public String getLeaderSecond() {
		return leaderSecond;
	}

	/**
	 * @param leaderSecond the leaderSecond to set
	 */
	public void setLeaderSecond(String leaderSecond) {
		this.leaderSecond = leaderSecond;
	}

	/**
	 * @return the leaderThird
	 */
	public String getLeaderThird() {
		return leaderThird;
	}

	/**
	 * @param leaderThird the leaderThird to set
	 */
	public void setLeaderThird(String leaderThird) {
		this.leaderThird = leaderThird;
	}

	/**
	 * @return the commonFirst
	 */
	public String getCommonFirst() {
		return commonFirst;
	}

	/**
	 * @param commonFirst the commonFirst to set
	 */
	public void setCommonFirst(String commonFirst) {
		this.commonFirst = commonFirst;
	}

	/**
	 * @return the commonSecond
	 */
	public String getCommonSecond() {
		return commonSecond;
	}

	/**
	 * @param commonSecond the commonSecond to set
	 */
	public void setCommonSecond(String commonSecond) {
		this.commonSecond = commonSecond;
	}

	/**
	 * @return the commonThird
	 */
	public String getCommonThird() {
		return commonThird;
	}

	/**
	 * @param commonThird the commonThird to set
	 */
	public void setCommonThird(String commonThird) {
		this.commonThird = commonThird;
	}

	/**
	 * @return the noshareFirst
	 */
	public String getNoshareFirst() {
		return noshareFirst;
	}

	/**
	 * @param noshareFirst the noshareFirst to set
	 */
	public void setNoshareFirst(String noshareFirst) {
		this.noshareFirst = noshareFirst;
	}

	/**
	 * @return the noshareSecond
	 */
	public String getNoshareSecond() {
		return noshareSecond;
	}

	/**
	 * @param noshareSecond the noshareSecond to set
	 */
	public void setNoshareSecond(String noshareSecond) {
		this.noshareSecond = noshareSecond;
	}

	/**
	 * @return the noshareThird
	 */
	public String getNoshareThird() {
		return noshareThird;
	}

	/**
	 * @param noshareThird the noshareThird to set
	 */
	public void setNoshareThird(String noshareThird) {
		this.noshareThird = noshareThird;
	}

	/**
	 * @return the upperName
	 */
	public String getUpperName() {
		return upperName;
	}

	/**
	 * @param upperName the upperName to set
	 */
	public void setUpperName(String upperName) {
		this.upperName = upperName;
	}

	/**
	 * @return the totalRefereCount
	 */
	public Integer getTotalRefereCount() {
		return totalRefereCount;
	}

	/**
	 * @param totalRefereCount the totalRefereCount to set
	 */
	public void setTotalRefereCount(Integer totalRefereCount) {
		this.totalRefereCount = totalRefereCount;
	}

	/**
	 * @return the underCount
	 */
	public Integer getUnderCount() {
		return underCount;
	}

	/**
	 * @param underCount the underCount to set
	 */
	public void setUnderCount(Integer underCount) {
		this.underCount = underCount;
	}

	public Integer getRefereeCount() {
        return refereeCount;
    }

    public void setRefereeCount(Integer refereeCount) {
        this.refereeCount = refereeCount;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    public String getRuleName() {
        return ruleName;
    }


    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getIsMember() {
        return isMember;
    }

    public void setIsMember(String isMember) {
        this.isMember = isMember;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }


    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getRefereeId() {
        return refereeId;
    }

    public void setRefereeId(String refereeId) {
        this.refereeId = refereeId;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public String getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(String verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getInsertNo() {
        return insertNo;
    }

    public void setInsertNo(Integer insertNo) {
        this.insertNo = insertNo;
    }

    public Integer getCurrentNo() {
        return currentNo;
    }

    public void setCurrentNo(Integer currentNo) {
        this.currentNo = currentNo;
    }

    public String getIsOut() {
        return isOut;
    }

    public void setIsOut(String isOut) {
        this.isOut = isOut;
    }
}
