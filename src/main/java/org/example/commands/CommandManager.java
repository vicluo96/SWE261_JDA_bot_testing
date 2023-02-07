package org.example.commands;

import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import java.util.ArrayList;
import java.util.List;

public class CommandManager extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        //super.onSlashCommandInteraction(event);
        String command = event.getName();
        if(command.equals("bark")){// command /bark
            String userTag = event.getUser().getAsTag();
            event.reply(userTag + ", woof woof!").queue();
        }
    }

    @Override
    public void onGuildReady(GuildReadyEvent event) {
        //super.onGuildReady(event);
        List<CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("bark", "Shiba is greeting you by barking"));
        event.getGuild().updateCommands().addCommands(commandData).queue();
    }

    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        //super.onGuildJoin(event);
        List<CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("bark", "Shiba is greeting you by barking"));
        event.getGuild().updateCommands().addCommands(commandData).queue();

    }
}
