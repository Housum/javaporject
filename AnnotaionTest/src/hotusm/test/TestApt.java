package hotusm.test;

import java.io.IOException;




public class TestApt {
	
	public static void main(String[] args) throws IOException {
		String cmd="apt -factory"
				+ "com.hotusm.utils.InterfaceExtractorProcessorFactory"
				+ "Multiplier.java -s";
		Runtime.getRuntime().exec(cmd);
	}
}
