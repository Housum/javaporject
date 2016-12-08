package com.hotusm.filestream;

import java.io.FileOutputStream;
import java.nio.channels.FileLock;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * 
 *对文件进行加锁
 */
public class LockFileTest {
	
	@Test
	public void  lockfile(){
		try {
			FileOutputStream os=new FileOutputStream("D://nio.txt");
			FileLock fl = os.getChannel().tryLock();
			if(fl!=null){
				TimeUnit.MILLISECONDS.sleep(100);
				//将文件的锁释放
				fl.release();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
