package pahss_core;

import java.io.IOException;
import java.io.Writer;

import javax.swing.JTextArea;

public class NotificationWriter extends Writer {
	private JTextArea note;
	private StringBuffer buffer = new StringBuffer();
	
	public NotificationWriter(JTextArea note) {
		// TODO Auto-generated constructor stub
		super();
		this.note = note;
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		flush();
		note = null;
	}

	@Override
	public void flush() throws IOException {
		// TODO Auto-generated method stub
		note.insert(buffer.toString(), 0);
		buffer.delete(0, buffer.length());
	}

	@Override
	public void write(char[] arg0, int arg1, int arg2) throws IOException {
		// TODO Auto-generated method stub
//		for(int i = arg1; i < (i+arg2); i++){
//			buffer.append(arg0[i]);
//		}
	}
	
	@Override
	public synchronized void write(String str) throws IOException {
		// TODO Auto-generated method stub
		super.write(str);
		buffer.append(str);
		buffer.append('\n');
		flush();
	}


}
