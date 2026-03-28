package com.ics.pdatakeorder.model;

/**
 * Presentation wrapper for a single menu button on the main ordering page.
 * All display-logic decisions (bgColor, back-button state) are computed
 * in MainPageHelper so that main.jsp requires no Java scriptlets.
 */
public class MenuDisplayItem {

    private MenuSetup menu;
    private String bgColor;
    /** True when this is the last cell and represents the "back to main menu" button. */
    private boolean backButton;
    /** The prefix to navigate back to (e.g. "A"). Only meaningful when backButton == true. */
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
