package serial.entity;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
/**
 *������Ҫ�Լ�ʵ��ϸ�ڵ�   ����ʵ�� externalizable
 *
 */
public class Order implements Externalizable{

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
	}

}
