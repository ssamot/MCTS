package ssamot.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public abstract class KryoPersistable {

	public void persistKryo(Kryo kryo, String fileName) {

		try {
			ZipOutputStream zipStream = null;

			String tmpFileName;

			tmpFileName = fileName + ".tmp";

			zipStream = new ZipOutputStream(new FileOutputStream(tmpFileName));
			zipStream.setLevel(Deflater.BEST_COMPRESSION);

			ZipEntry entry = new ZipEntry(fileName + ".ser");
			zipStream.putNextEntry(entry);
			Output output = new Output(zipStream);
			kryo.writeObject(output, this);
			output.close();

			// File (or directory) with old name
			File tmpFile = new File(tmpFileName);

			// File (or directory) with new name
			File finalFile = new File(fileName);
			if (finalFile.exists()) {
				finalFile.delete();
			}
			// Rename file (or directory)
			boolean success = tmpFile.renameTo(finalFile);
			if (!success) {
				throw new RuntimeException("Unable to Persiste object!");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static <T> T loadKryo(Kryo kryo, String fileName, Class<T> type) {

		T object = null;
		Input input = null;
		try {

			File file = new File(fileName);
			if(!file.exists()) {
				return null;
			}
			
			ZipInputStream zipStream = new ZipInputStream(new FileInputStream(
					file));
			zipStream.getNextEntry();

			input = new Input(zipStream);
			object = kryo.readObject(input, type);
			if (object == null) {
				throw new RuntimeException("Unable to load object");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} 
		
		input.close();
		return object;

	}

}
