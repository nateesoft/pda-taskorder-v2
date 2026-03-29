package com.ics.pdatakeorder.model;

public class MenuDisplayItem {

    private MenuSetup menu;
    private String bgColor;
    private boolean backButton;
    private String backPrefix;

    public MenuDisplayItem() {
    }

    public MenuSetup getMenu() {
        return menu;
    }

    public void setMenu(MenuSetup menu) {
        this.menu = menu;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public boolean isBackButton() {
        return backButton;
    }

    public void setBackButton(boolean backButton) {
        this.backButton = backButton;
    }

    public String getBackPrefix() {
        return backPrefix;
    }

    public void setBackPrefix(String backPrefix) {
        this.backPrefix = backPrefix;
    }
}
