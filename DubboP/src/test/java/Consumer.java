

import java.io.IOException;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hotusm.dubbo.provider.DemoService;
import com.yonyou.pc.gn.modules.notifications.service.itf.ShowNotifications;


public class Consumer {

	public static void main(String[] args) {
		 ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(  
	                new String[] { "spring-context-consumer.xml" });  
	        context.start();  
	  
	        ShowNotifications demo = (ShowNotifications) context.getBean("showNotifications"); //  
	}
}
