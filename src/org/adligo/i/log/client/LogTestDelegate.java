package org.adligo.i.log.client;

public class LogTestDelegate implements I_LogDelegate {

	private int level = -10;
	private Object message;
	private Throwable t;

	public void log(int type, Object p_message, Throwable p_t) {
		level = type;
		message = message;
		t = p_t;
	}

	protected int getLevel() {
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
		LogTestDelegate other = (LogTestDelegate) obj;
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
}
