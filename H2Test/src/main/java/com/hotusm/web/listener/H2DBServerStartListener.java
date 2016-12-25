package com.hotusm.web.listener;

import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.h2.tools.Server;

public class H2DBServerStartListener implements ServletContextListener{

	 //H2���ݿ����������ʵ��
    private Server server;
    /* 
     * WebӦ�ó�ʼ��ʱ����H2���ݿ�
     */
    public void contextInitialized(ServletContextEvent sce) {
        try {  
            System.out.println("��������h2���ݿ�...");
            //ʹ��org.h2.tools.Server����ഴ��һ��H2���ݿ�ķ���������������û��ָ���κβ�������ôH2���ݿ�����ʱĬ��ռ�õĶ˿ھ���8082
            server = Server.createTcpServer().start(); 
            System.out.println("h2���ݿ������ɹ�...");
        } catch (SQLException e) {  
            System.out.println("����h2���ݿ����" + e.toString());  
            e.printStackTrace();  
            throw new RuntimeException(e);  
        }  
    }

    /* 
     * WebӦ������ʱֹͣH2���ݿ�
     */
    public void contextDestroyed(ServletContextEvent sce) {
        if (this.server != null) {
            // ֹͣH2���ݿ�
            this.server.stop();
            this.server = null;
        }
    }
    

}
