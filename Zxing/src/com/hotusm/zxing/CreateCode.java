package com.hotusm.zxing;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;


public class CreateCode {

	public static void main(String[] args) {
		ByteArrayOutputStream bos=QRCode.from("http://hotusm.oss-cn-beijing.aliyuncs.com/%E9%A1%B9%E7%9B%AE%E5%BC%80%E5%8F%91%E4%B8%AD%E5%BF%83-beat4.apk").to(ImageType.JPG).stream();
		try {
			FileOutputStream fos=new FileOutputStream("D://android.jpg");
			fos.write(bos.toByteArray());
			bos.flush();
			bos.close();
			System.out.println("成功二维码成功");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
