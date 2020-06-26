package in.nit.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class CompressedFile {

public static byte[]	compress(byte[] data){
	//System.out.println("Before compress  size "+data.length);
		Deflater def=new Deflater();
		def.setInput(data);
		def.finish();
		ByteArrayOutputStream stream=new ByteArrayOutputStream();
		byte[] buffer=new byte[1024];
	   while(!def.finished()) {
		   int count = def.deflate(buffer);
		   stream.write(buffer, 0, count);
	   }
	   try {
		stream.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
	  // System.out.println("after compress size "+stream.toByteArray().length);
	   return stream.toByteArray();
	}

public static byte[]	deCompress(byte[] data){
//	System.out.println("Before decompress  size "+data.length);
		Inflater inf=new Inflater();
		if(data!=null)
		inf.setInput(data);

		ByteArrayOutputStream stream=new ByteArrayOutputStream();
		byte[] buffer=new byte[1024];
		try {
			while(!inf.finished()) {
			int count;
			count = inf.inflate(buffer);
			stream.write(buffer, 0, count);
		}
			stream.close();
		}catch (DataFormatException | IOException e) {
			e.printStackTrace();
		}
	   
	
	//   System.out.println("after decompress size "+stream.toByteArray().length);
	   return stream.toByteArray();
	}
}
