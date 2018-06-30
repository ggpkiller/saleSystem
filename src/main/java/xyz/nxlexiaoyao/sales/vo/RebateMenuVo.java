package xyz.nxlexiaoyao.sales.vo;

public class RebateMenuVo {
    private String ruleId;
    private String ruleName;
    private Integer rebateCount;
    private Integer satisfiedCount = 0;


    public Integer getSatisfiedCount() {
        return satisfiedCount;
    }

    public void setSatisfiedCount(Integer satisfiedCount) {
        this.satisfiedCount = satisfiedCount;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public Integer getRebateCount() {
        return rebateCount;
    }

    public void setRebateCount(Integer rebateCount) {
        this.rebateCount = rebateCount;
    }
}
