package ssamot.utilities;

import java.io.Serializable;
import java.util.Arrays;

public class ShortArrayKey implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1360074539478046893L;
	private short[] key;

	public ShortArrayKey(short[] key) {
		super();
		this.key = key;
	}

	public ShortArrayKey() {
		
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(key);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShortArrayKey other = (ShortArrayKey) obj;
		if (!Arrays.equals(key, other.key))
			return false;
		return true;
	}

	public short[] getKey() {
		return key;
	}

}
