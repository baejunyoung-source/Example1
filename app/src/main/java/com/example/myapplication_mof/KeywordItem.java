package com.example.myapplication_mof;

public class KeywordItem {
    private String keywordName;
    private boolean isAlarmOn;

    public KeywordItem(String keywordName, boolean isAlarmOn) {
        this.keywordName = keywordName;
        this.isAlarmOn = isAlarmOn;
    }

    public String getKeywordName() { return keywordName; }
    public boolean isAlarmOn() { return isAlarmOn; }

    public void setAlarmOn(boolean alarmOn) { isAlarmOn = alarmOn; }
}
