package org.example.commands;

import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.interactions.CommandDataImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CommandDataTest {
    @Test
    public void testSlashCommand(){
        CommandData command = new CommandDataImpl("bark", "Shiba is greeting you by barking");
        DataObject data = command.toData();
        Assertions.assertEquals("bark", data.getString("name"));
        Assertions.assertEquals("Shiba is greeting you by barking", data.getString("description"));
    }

    @Test
    public void testSlashCommandWith1RequiredOption(){
        OptionData option1 = new OptionData(OptionType.STRING, "message","The message you want the bot say", true);
        CommandData command = new CommandDataImpl("say", "Make a bot say a message")
                .addOptions(option1);
        DataObject data = command.toData();
        Assertions.assertEquals("say", data.getString("name"));
        Assertions.assertEquals("Make a bot say a message", data.getString("description"));

        DataArray options = data.getArray("options");
        DataObject option = options.getObject(0);
        Assertions.assertTrue(option.getBoolean("required"));
        Assertions.assertEquals("message", option.getString("name"));
        Assertions.assertEquals("The message you want the bot say", option.getString("description"));

    }

    @Test
    public void testSlashCommandWith1RequiredOption1OptionalOption(){
        CommandData command = new CommandDataImpl("chat", "Make a bot chat in a channel")
                .addOptions(new OptionData(OptionType.STRING, "content","The content you want a bot say",  true))
                .addOptions(new OptionData(OptionType.CHANNEL, "channel", "The channel you want to sent this content in", false));
        DataObject data = command.toData();
        Assertions.assertEquals("chat", data.getString("name"));
        Assertions.assertEquals("Make a bot chat in a channel", data.getString("description"));

        DataArray options = data.getArray("options");
        DataObject option = options.getObject(0);
        Assertions.assertTrue(option.getBoolean("required"));
        Assertions.assertEquals("content", option.getString("name"));
        Assertions.assertEquals("The content you want a bot say", option.getString("description"));

        option = options.getObject(1);
        Assertions.assertFalse(option.getBoolean("required"));
        Assertions.assertEquals("channel", option.getString("name"));
        Assertions.assertEquals("The channel you want to sent this content in", option.getString("description"));

    }

}
