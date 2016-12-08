package com.hotusm.filestream;

import java.io.FileOutputStream;
import java.nio.channels.FileLock;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * 
 *���ļ����м���
 */
public class LockFileTest {
	
	@Test
	public void  lockfile(){
		try {
			FileOutputStream os=new FileOutputStream("D://nio.txt");
			FileLock fl = os.getChannel().tryLock();
			if(fl!=null){
				TimeUnit.MILLISECONDS.sleep(100);
				//���ļ������ͷ�
				fl.release();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
