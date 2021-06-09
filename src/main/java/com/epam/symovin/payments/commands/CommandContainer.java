package com.epam.symovin.payments.commands;

import com.epam.symovin.payments.commands.admin.*;
import com.epam.symovin.payments.commands.common.*;
import com.epam.symovin.payments.commands.user.*;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {
    private static final Map<String, Command> commands = new TreeMap<>();

    static {
        commands.put("adminMenu", new AdminMenuCommand());
        commands.put("blockCard", new BlockCardCommand());
        commands.put("blockUser", new BlockUserCommand());
        commands.put("showCards", new ShowCardsCommand());
        commands.put("showRequests", new ShowRequestsCommand());
        commands.put("unblockCard", new UnblockCardCommand());
        commands.put("unblockRequest", new UnblockRequestCommand());
        commands.put("unblockUser", new UnblockUserCommand());

        commands.put("login", new LoginCommand());
        commands.put("login_page", new LoginPageCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("reg", new RegistrationCommand());
        commands.put("reg_page", new RegistrationPageCommand());
        commands.put("switchLanguage", new SwitchLanguageCommand());

        commands.put("main", new MainMenuCommand());
        commands.put("addCard", new AddCardCommand());
        commands.put("getCard", new CardDetailCommand());
        commands.put("addPaymentPage", new AddPaymentPageCommand());
        commands.put("addPayment", new AddPayment());
        commands.put("submitPayment", new SubmitPaymentCommand());
        commands.put("submitPaymentPage", new SubmitPageCommand());
        commands.put("userBlockCard", new UserBlockCardCommand());
        commands.put("sendRequest", new SendRequestCommand());
    }

    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            return commands.get("noCommand");
        }

        return commands.get(commandName);
    }
}
