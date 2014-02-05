package org.adligo.i.log.tests;

import org.adligo.i.log.client.I_LogDelegate;

public class LogMockDelegate implements I_LogDelegate {

	private short level = (short) -10;
	private Object message;
	private Throwable t;

	public void log(short type, Object p_message, Throwable p_t) {
		level = type;
		message = p_message;
		t = p_t;
	}

	public short getLevel() {
		return level;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + level;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((t == null) ? 0 : t.hashCode());
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
		LogMockDelegate other = (LogMockDelegate) obj;
		if (level != other.level)
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (t == null) {
			if (other.t != null)
				return false;
		} else if (!t.equals(other.t))
			return false;
		return true;
	}

	protected Object getMessage() {
		return message;
	}

	protected Throwable getT() {
		return t;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("LogTestDelegate [level=");
		sb.append(level);
		sb.append(",message=");
		sb.append(message);
		sb.append(",throwabe=");
		sb.append(t);
		sb.append("]");
		return sb.toString();
	}

	@Override
	public void debug(Object message, Throwable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void debug(Object message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void error(Object message, Throwable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void error(Object message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fatal(Object message, Throwable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fatal(Object message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void info(Object message, Throwable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void info(Object message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isDebugEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isErrorEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFatalEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInfoEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTraceEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isWarnEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setEnabled(boolean p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void trace(Object message, Throwable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void trace(Object message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void warn(Object message, Throwable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void warn(Object message) {
		// TODO Auto-generated method stub
		
	}
}
