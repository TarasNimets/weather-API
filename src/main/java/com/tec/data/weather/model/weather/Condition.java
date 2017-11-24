package com.tec.data.weather.model.weather;

public final class Condition {

    private String text;
    private String icon;

    private Condition() {
    }

    public String getText() {
        return text;
    }

    public String getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return "Condition [text=" + text + ", icon=" + icon + "]";
    }

}
