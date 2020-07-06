package by.htp.ishop.controller.command;

import java.util.HashMap;

public class CommandProvider {
    private final HashMap<CommandName, Command> commandMap = new HashMap<>();

    public CommandProvider() {
    	commandMap.put(CommandName.LOCALE, new LocaleCommand());
        commandMap.put(CommandName.LOG_IN, new LogInCommand());
        commandMap.put(CommandName.LOG_OUT, new LogOutCommand());
        commandMap.put(CommandName.GO_TO_MAIN, new GoToMainCommand());
        commandMap.put(CommandName.GO_TO_LOGIN, new GoToLogInCommand());
        commandMap.put(CommandName.GO_TO_REGISTRATION, new GoToRegistrationCommand());
        commandMap.put(CommandName.GO_TO_EDIT_CART, new GoToEditCartCommand());
        commandMap.put(CommandName.REGISTRATION, new RegistrationCommand());
        commandMap.put(CommandName.DISPLAY_ITEMS, new DisplayItemsCommand());
        commandMap.put(CommandName.ITEMS_BY_CATEGORY, new ItemsByCategoryCommand());
        commandMap.put(CommandName.ADD_TO_CART, new AddToCartCommand());
        commandMap.put(CommandName.OPEN_CART, new DisplayCartCommand());
        commandMap.put(CommandName.MAKE_ORDER, new MakeOrderCommand());
        commandMap.put(CommandName.DELETE_CART_ITEM, new DeleteCartItemCommand());
    }

    public Command getCommand(String name) {
    	CommandName commandName = null;
    	Command command = null;
    	commandName = CommandName.valueOf(name.toUpperCase());
    	command = commandMap.get(commandName);
		return command;
    }

}
