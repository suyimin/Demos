package com.xdroid.blogcodes.refresh;

public class TestBean extends SelectedBean {
    private String name;

    public TestBean(String name) {
        this.name = name;
    }

    public TestBean(String name, boolean isSelected) {
        this.name = name;
        setSelected(isSelected);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
