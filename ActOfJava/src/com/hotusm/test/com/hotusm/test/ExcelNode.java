package com.hotusm.test.com.hotusm.test;

import java.util.ArrayList;
import java.util.List;

import com.hotusm.crawl.excel.ExportExcel;
import com.hotusm.crawl.excel.annotation.Row;
import com.hotusm.crawl.excel.annotation.Sheet;

@Sheet("����Excel")
public class ExcelNode {

	@Row(index=1,columnName="����")
	private String name;
	@Row(index=1,columnName="����")
	private int index;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
}
