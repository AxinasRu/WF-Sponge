package wfplugin.wfplugin.commands.custommessage;

import wfplugin.wfplugin.commands.Command;

public class CustomMessageCommand extends Command {
    @Override
    public String[] names() {
        return new String[]{"custommessage"};
    }

    @Override
    public String permission() {
        return "wf.admin.custommessage";
    }

    @Override
    public Command[] children() {
        return new Command[]{
                new CustomJoinMessageCommand(),
                new CustomLeaveMessageCommand()
        };
    }
}
