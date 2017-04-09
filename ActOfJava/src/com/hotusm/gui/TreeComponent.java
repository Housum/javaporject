package com.hotusm.gui;

import java.util.Collection;

public interface TreeComponent{

	public String getName();
	public void click();
	public void addTreeComponent(TreeComponent component);
	public void remove(TreeComponent component);
	public TreeComponent getChild(int i);
	public Collection<TreeComponent> getAllChild();
}
