package entities.command;

public class ArgumentsWithLabel {

	public String[] create(String label, String[] args) {
		String[] arguments = new String[args.length + 1];
		arguments[0] = label;
		for (int i = 0; i < args.length; i++) {
			arguments[i + 1] = args[i];
		}
		return arguments;
	}
	
}
