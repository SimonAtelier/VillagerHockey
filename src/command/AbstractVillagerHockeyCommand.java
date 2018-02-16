package command;

public abstract class AbstractVillagerHockeyCommand implements Command {

	@Override
	public String getSyntax() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("/vh ");
		buffer.append(getName());
		for (String arg : getArgumentLabels()) {
			buffer.append(" {");
			buffer.append(arg);
			buffer.append("}");
		}
		return buffer.toString();
	}

}
