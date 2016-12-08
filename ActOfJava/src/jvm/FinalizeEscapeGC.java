package jvm;

public class FinalizeEscapeGC {
	
	public static FinalizeEscapeGC SAVE_HOOK=null;
	
	public void isAlive(){
		System.out.println("yes,i am still alive:)");
	}

	
	/**
	 * finalize方法系统只会调用一次,
	 * 
	 */
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("finalize method executed");
		SAVE_HOOK=this;
	}

	public static void main(String[] args) throws InterruptedException {
		SAVE_HOOK=new FinalizeEscapeGC();
		SAVE_HOOK=null;
		System.gc();
		Thread.sleep(500);
		if(SAVE_HOOK!=null){
			SAVE_HOOK.isAlive();
		}else{
			System.out.println("no,i am dead:(");
		}
		
		SAVE_HOOK=null;
		System.gc();
		Thread.sleep(500);
		if(SAVE_HOOK!=null){
			SAVE_HOOK.isAlive();
		}else{
			System.out.println("no,i am dead:(");
		}
		
		
	}
}
