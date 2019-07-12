package com.cqut.indoor.model;

public class SSIDIntensity {
    private String SSID;
    private String intensity;

    public String getSSID() {
        return SSID;
    }

    public void setSSID(String SSID) {
        this.SSID = SSID;
    }

    public String getIntensity() {
        return intensity;
    }

    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }

    public SSIDIntensity(String SSID, String intensity) {
        this.SSID = SSID;
        this.intensity = intensity;
    }

    public SSIDIntensity() {
    }
}
