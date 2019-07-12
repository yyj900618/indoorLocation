package com.cqut.indoor.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TDmeasureExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TDmeasureExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andRpidIsNull() {
            addCriterion("RPID is null");
            return (Criteria) this;
        }

        public Criteria andRpidIsNotNull() {
            addCriterion("RPID is not null");
            return (Criteria) this;
        }

        public Criteria andRpidEqualTo(String value) {
            addCriterion("RPID =", value, "rpid");
            return (Criteria) this;
        }

        public Criteria andRpidNotEqualTo(String value) {
            addCriterion("RPID <>", value, "rpid");
            return (Criteria) this;
        }

        public Criteria andRpidGreaterThan(String value) {
            addCriterion("RPID >", value, "rpid");
            return (Criteria) this;
        }

        public Criteria andRpidGreaterThanOrEqualTo(String value) {
            addCriterion("RPID >=", value, "rpid");
            return (Criteria) this;
        }

        public Criteria andRpidLessThan(String value) {
            addCriterion("RPID <", value, "rpid");
            return (Criteria) this;
        }

        public Criteria andRpidLessThanOrEqualTo(String value) {
            addCriterion("RPID <=", value, "rpid");
            return (Criteria) this;
        }

        public Criteria andRpidLike(String value) {
            addCriterion("RPID like", value, "rpid");
            return (Criteria) this;
        }

        public Criteria andRpidNotLike(String value) {
            addCriterion("RPID not like", value, "rpid");
            return (Criteria) this;
        }

        public Criteria andRpidIn(List<String> values) {
            addCriterion("RPID in", values, "rpid");
            return (Criteria) this;
        }

        public Criteria andRpidNotIn(List<String> values) {
            addCriterion("RPID not in", values, "rpid");
            return (Criteria) this;
        }

        public Criteria andRpidBetween(String value1, String value2) {
            addCriterion("RPID between", value1, value2, "rpid");
            return (Criteria) this;
        }

        public Criteria andRpidNotBetween(String value1, String value2) {
            addCriterion("RPID not between", value1, value2, "rpid");
            return (Criteria) this;
        }

        public Criteria andApidIsNull() {
            addCriterion("APID is null");
            return (Criteria) this;
        }

        public Criteria andApidIsNotNull() {
            addCriterion("APID is not null");
            return (Criteria) this;
        }

        public Criteria andApidEqualTo(String value) {
            addCriterion("APID =", value, "apid");
            return (Criteria) this;
        }

        public Criteria andApidNotEqualTo(String value) {
            addCriterion("APID <>", value, "apid");
            return (Criteria) this;
        }

        public Criteria andApidGreaterThan(String value) {
            addCriterion("APID >", value, "apid");
            return (Criteria) this;
        }

        public Criteria andApidGreaterThanOrEqualTo(String value) {
            addCriterion("APID >=", value, "apid");
            return (Criteria) this;
        }

        public Criteria andApidLessThan(String value) {
            addCriterion("APID <", value, "apid");
            return (Criteria) this;
        }

        public Criteria andApidLessThanOrEqualTo(String value) {
            addCriterion("APID <=", value, "apid");
            return (Criteria) this;
        }

        public Criteria andApidLike(String value) {
            addCriterion("APID like", value, "apid");
            return (Criteria) this;
        }

        public Criteria andApidNotLike(String value) {
            addCriterion("APID not like", value, "apid");
            return (Criteria) this;
        }

        public Criteria andApidIn(List<String> values) {
            addCriterion("APID in", values, "apid");
            return (Criteria) this;
        }

        public Criteria andApidNotIn(List<String> values) {
            addCriterion("APID not in", values, "apid");
            return (Criteria) this;
        }

        public Criteria andApidBetween(String value1, String value2) {
            addCriterion("APID between", value1, value2, "apid");
            return (Criteria) this;
        }

        public Criteria andApidNotBetween(String value1, String value2) {
            addCriterion("APID not between", value1, value2, "apid");
            return (Criteria) this;
        }

        public Criteria andRrsiIsNull() {
            addCriterion("RRSI is null");
            return (Criteria) this;
        }

        public Criteria andRrsiIsNotNull() {
            addCriterion("RRSI is not null");
            return (Criteria) this;
        }

        public Criteria andRrsiEqualTo(BigDecimal value) {
            addCriterion("RRSI =", value, "rrsi");
            return (Criteria) this;
        }

        public Criteria andRrsiNotEqualTo(BigDecimal value) {
            addCriterion("RRSI <>", value, "rrsi");
            return (Criteria) this;
        }

        public Criteria andRrsiGreaterThan(BigDecimal value) {
            addCriterion("RRSI >", value, "rrsi");
            return (Criteria) this;
        }

        public Criteria andRrsiGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("RRSI >=", value, "rrsi");
            return (Criteria) this;
        }

        public Criteria andRrsiLessThan(BigDecimal value) {
            addCriterion("RRSI <", value, "rrsi");
            return (Criteria) this;
        }

        public Criteria andRrsiLessThanOrEqualTo(BigDecimal value) {
            addCriterion("RRSI <=", value, "rrsi");
            return (Criteria) this;
        }

        public Criteria andRrsiIn(List<BigDecimal> values) {
            addCriterion("RRSI in", values, "rrsi");
            return (Criteria) this;
        }

        public Criteria andRrsiNotIn(List<BigDecimal> values) {
            addCriterion("RRSI not in", values, "rrsi");
            return (Criteria) this;
        }

        public Criteria andRrsiBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("RRSI between", value1, value2, "rrsi");
            return (Criteria) this;
        }

        public Criteria andRrsiNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("RRSI not between", value1, value2, "rrsi");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}