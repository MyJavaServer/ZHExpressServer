package com.zhihui.zhexpress.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CordinateExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CordinateExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andXlistIsNull() {
            addCriterion("xlist is null");
            return (Criteria) this;
        }

        public Criteria andXlistIsNotNull() {
            addCriterion("xlist is not null");
            return (Criteria) this;
        }

        public Criteria andXlistEqualTo(String value) {
            addCriterion("xlist =", value, "xlist");
            return (Criteria) this;
        }

        public Criteria andXlistNotEqualTo(String value) {
            addCriterion("xlist <>", value, "xlist");
            return (Criteria) this;
        }

        public Criteria andXlistGreaterThan(String value) {
            addCriterion("xlist >", value, "xlist");
            return (Criteria) this;
        }

        public Criteria andXlistGreaterThanOrEqualTo(String value) {
            addCriterion("xlist >=", value, "xlist");
            return (Criteria) this;
        }

        public Criteria andXlistLessThan(String value) {
            addCriterion("xlist <", value, "xlist");
            return (Criteria) this;
        }

        public Criteria andXlistLessThanOrEqualTo(String value) {
            addCriterion("xlist <=", value, "xlist");
            return (Criteria) this;
        }

        public Criteria andXlistLike(String value) {
            addCriterion("xlist like", value, "xlist");
            return (Criteria) this;
        }

        public Criteria andXlistNotLike(String value) {
            addCriterion("xlist not like", value, "xlist");
            return (Criteria) this;
        }

        public Criteria andXlistIn(List<String> values) {
            addCriterion("xlist in", values, "xlist");
            return (Criteria) this;
        }

        public Criteria andXlistNotIn(List<String> values) {
            addCriterion("xlist not in", values, "xlist");
            return (Criteria) this;
        }

        public Criteria andXlistBetween(String value1, String value2) {
            addCriterion("xlist between", value1, value2, "xlist");
            return (Criteria) this;
        }

        public Criteria andXlistNotBetween(String value1, String value2) {
            addCriterion("xlist not between", value1, value2, "xlist");
            return (Criteria) this;
        }

        public Criteria andYlistIsNull() {
            addCriterion("ylist is null");
            return (Criteria) this;
        }

        public Criteria andYlistIsNotNull() {
            addCriterion("ylist is not null");
            return (Criteria) this;
        }

        public Criteria andYlistEqualTo(String value) {
            addCriterion("ylist =", value, "ylist");
            return (Criteria) this;
        }

        public Criteria andYlistNotEqualTo(String value) {
            addCriterion("ylist <>", value, "ylist");
            return (Criteria) this;
        }

        public Criteria andYlistGreaterThan(String value) {
            addCriterion("ylist >", value, "ylist");
            return (Criteria) this;
        }

        public Criteria andYlistGreaterThanOrEqualTo(String value) {
            addCriterion("ylist >=", value, "ylist");
            return (Criteria) this;
        }

        public Criteria andYlistLessThan(String value) {
            addCriterion("ylist <", value, "ylist");
            return (Criteria) this;
        }

        public Criteria andYlistLessThanOrEqualTo(String value) {
            addCriterion("ylist <=", value, "ylist");
            return (Criteria) this;
        }

        public Criteria andYlistLike(String value) {
            addCriterion("ylist like", value, "ylist");
            return (Criteria) this;
        }

        public Criteria andYlistNotLike(String value) {
            addCriterion("ylist not like", value, "ylist");
            return (Criteria) this;
        }

        public Criteria andYlistIn(List<String> values) {
            addCriterion("ylist in", values, "ylist");
            return (Criteria) this;
        }

        public Criteria andYlistNotIn(List<String> values) {
            addCriterion("ylist not in", values, "ylist");
            return (Criteria) this;
        }

        public Criteria andYlistBetween(String value1, String value2) {
            addCriterion("ylist between", value1, value2, "ylist");
            return (Criteria) this;
        }

        public Criteria andYlistNotBetween(String value1, String value2) {
            addCriterion("ylist not between", value1, value2, "ylist");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNull() {
            addCriterion("updatetime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("updatetime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(Date value) {
            addCriterion("updatetime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Date value) {
            addCriterion("updatetime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Date value) {
            addCriterion("updatetime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updatetime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Date value) {
            addCriterion("updatetime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("updatetime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Date> values) {
            addCriterion("updatetime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Date> values) {
            addCriterion("updatetime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Date value1, Date value2) {
            addCriterion("updatetime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("updatetime not between", value1, value2, "updatetime");
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