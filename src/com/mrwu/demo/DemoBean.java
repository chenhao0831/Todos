package com.mrwu.demo;

public class DemoBean {

	/**
	 * йг╥Я©ирти╬ЁЩ
	 */
	private String title;
	
	private boolean canRemove = true;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isCanRemove() {
		return canRemove;
	}

	public void setCanRemove(boolean canRemove) {
		this.canRemove = canRemove;
	}

	public DemoBean(String title, boolean canRemove) {
		this.title = title;
		this.canRemove = canRemove;
	}

	public DemoBean() {
	}

}
