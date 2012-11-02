/*
 *
 * *** BEGIN LICENSE
 *  Copyright (C) 2012 Spyridon Samothrakis spyridon.samothrakis@gmail.com
 *  This program is free software: you can redistribute it and/or modify it
 *  under the terms of the GNU General Public License version 3, as published
 *  by the Free Software Foundation.
 *
 *  This program is distributed in the hope that it will be useful, but
 *  WITHOUT ANY WARRANTY; without even the implied warranties of
 *  MERCHANTABILITY, SATISFACTORY QUALITY, or FITNESS FOR A PARTICULAR
 *  PURPOSE.  See the GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program.  If not, see <http://www.gnu.org/licenses/>.
 * *** END LICENSE
 *
 */

package ssamot.utilities;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.Queue;

public class SerialClone {
    public static <T> T clone(T x) {
	try {
	    return cloneX(x);
	} catch (IOException e) {
	    throw new IllegalArgumentException(e);
	} catch (ClassNotFoundException e) {
	    throw new IllegalArgumentException(e);
	}
    }

    private static <T> T cloneX(T x) throws IOException, ClassNotFoundException {
	ByteArrayOutputStream bout = new ByteArrayOutputStream();
	CloneOutput cout = new CloneOutput(bout);
	cout.writeObject(x);
	byte[] bytes = bout.toByteArray();
	
	ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
	CloneInput cin = new CloneInput(bin, cout);

	@SuppressWarnings("unchecked")  // thanks to Bas de Bakker for the tip!
	T clone = (T) cin.readObject();
	return clone;
    }

    private static class CloneOutput extends ObjectOutputStream {
	Queue<Class<?>> classQueue = new LinkedList<Class<?>>();

	CloneOutput(OutputStream out) throws IOException {
	    super(out);
	}

	@Override
	protected void annotateClass(Class<?> c) {
	    classQueue.add(c);
	}

	@Override
	protected void annotateProxyClass(Class<?> c) {
	    classQueue.add(c);
	}
    }

    private static class CloneInput extends ObjectInputStream {
	private final CloneOutput output;

	CloneInput(InputStream in, CloneOutput output) throws IOException {
	    super(in);
	    this.output = output;
	}

    	@Override
	protected Class<?> resolveClass(ObjectStreamClass osc)
	throws IOException, ClassNotFoundException {
	    Class<?> c = output.classQueue.poll();
	    String expected = osc.getName();
	    String found = (c == null) ? null : c.getName();
	    if (!expected.equals(found)) {
		throw new InvalidClassException("Classes desynchronized: " +
			"found " + found + " when expecting " + expected);
	    }
	    return c;
	}

    	@Override
    	protected Class<?> resolveProxyClass(String[] interfaceNames)
	throws IOException, ClassNotFoundException {
    	    return output.classQueue.poll();
    	}
    }
}