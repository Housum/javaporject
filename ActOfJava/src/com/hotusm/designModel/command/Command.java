package com.hotusm.designModel.command;
/**
 * 
 * @author Hotusm  <br/>
 * @date 2016��10��11��   <br/>
 * @description ����ӿ�
 */
public interface Command {

	public void execute();
	
	public void undo();
	
}
