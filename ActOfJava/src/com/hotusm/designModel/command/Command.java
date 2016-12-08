package com.hotusm.designModel.command;
/**
 * 
 * @author Hotusm  <br/>
 * @date 2016年10月11日   <br/>
 * @description 命令接口
 */
public interface Command {

	public void execute();
	
	public void undo();
	
}
