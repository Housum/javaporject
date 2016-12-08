package com.hotusm.test;

public class OverrideTest {

	public enum Type{

		ADD{

			@Override
			public void add() {
				
			}
		};
		
		public abstract void add();
	}
	
}
