package entities.command;

public abstract class AbstractCommand implements Command {

	@Override
	public String getSyntax() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(getName());
		for (String arg : getArgumentLabels()) {
			buffer.append(" {");
			buffer.append(arg);
			buffer.append("}");
		}
		return buffer.toString();
	}

}
