package com.cqut.indoor.model;

import java.math.BigDecimal;

public class RPIDdistance implements Comparable<RPIDdistance>{
    private String RPID;
    private BigDecimal distance;

    public RPIDdistance(String RPID, BigDecimal distance) {
        this.RPID = RPID;
        this.distance = distance;
    }

    public String getRPID() {
        return RPID;
    }

    public void setRPID(String RPID) {
        this.RPID = RPID;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }


    @Override
    public int compareTo(RPIDdistance o) {
       return this.distance.compareTo(o.distance);
    }
}
