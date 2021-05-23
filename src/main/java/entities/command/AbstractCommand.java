package entities.command;

public abstract class AbstractCommand implements Command {

	private String name;
	
	public AbstractCommand(String name) {
		this.name = name;
	}
	
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
	
	@Override
	public String getName() {
		return name;
	}

}
