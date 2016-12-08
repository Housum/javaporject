package serial.entity;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
/**
 *对于需要自己实现细节的   可以实现 externalizable
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
