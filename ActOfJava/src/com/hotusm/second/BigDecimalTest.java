package com.hotusm.second;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.hotusm.thread.detail.start.Log;
/**
 * 因为一般的基本类型精读都不高  我们可以使用BigDecimal
 * 来进行进行这些高精度的操作
 * 
 *
 */
public class BigDecimalTest {
	
	public static void main(String[] args) {
		/**
		 * 构造函数的参数可以是int float String等类型  
		 * 
		 */
		BigDecimal bd=new BigDecimal(10);
		BigDecimal bd1=new BigDecimal(7);
		/**
		 * 第二个参数表示的是精度(默认是0) 
		 * 但是超出精度的话  那么就是最大精度
		 * 下面的结果是1.4285715  但是我们可以带上精度
		 * 下面的结果是:1.43
		 * 第三个参数表示的是舍去后面的数字的策略
		 * UP:绝对值进一法						1.2->2    -1.2->-2
		 * DOWM:绝对值退一法                        			1.2->1	  -1.2->-1
		 * CEILING:向正数靠拢  					1.2->2    -1.1->-1
		 * FLOOR:往负数靠拢       					1.2->1	  -1.1->-2
		 * HALF_UP:遇到5的时候进一      				1.3->1 	   1.5->2  -1.5->-2 
		 * HALF_DOWM:遇到5后退一				 	
		 * 
		 */
		BigDecimal divide = bd.divide(bd1,2,RoundingMode.HALF_UP);
		/**
		 * 可以用过BigDecimal.intValue()或者是BigDecimal.floatValue()
		 * 得到不同的结果
		 */
		Log.info(divide.floatValue()+"");
	}
	
}
