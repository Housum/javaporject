package com.hotusm.thread.detail.book.executejob.webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

import com.hotusm.thread.detail.start.Log;

/**
 * 具体生命周期的web服务器(模拟)
 * @author Hotusm
 *
 */
public class LifecyleWebServer {

	/**
	 * ExecutorService 继承了Executor  但是扩展了很多的功能  包括
	 * 生命周期的控制
	 */
	private final ExecutorService handlers=Executors.newFixedThreadPool(100);
	

	public void start() throws IOException {
		ServerSocket ss=new ServerSocket(80);
		while(!handlers.isShutdown()){
			try {
				final Socket conn=ss.accept();
				handlers.submit(new Runnable() {
					
					@Override
					public void run() {
						handleRequest(conn);
					}
				});
			} catch (RejectedExecutionException e) {
				if(!handlers.isShutdown()){
					Log.error("task submission reject"+e);
				}
			}

		}
	}
	
	public void stop(){handlers.shutdown();}
	void handleRequest(Socket conn){}
}
