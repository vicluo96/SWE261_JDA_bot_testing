package org.example.commands;

import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;

/*reference: JDA 5: Discord Bot Tutorial by TechnoVision
https://www.youtube.com/watch?v=oeXlOuP9Rsg&list=PLDhiRTZ_vnoWcdVDvT896SjLmqMfPvrXK&index=1
 */

public class CommandManager extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        //super.onSlashCommandInteraction(event);
        String command = event.getName();
        if(command.equals("bark")){// command /bark
            String userTag = event.getUser().getAsTag();
            event.reply(userTag + ", woof woof!").queue();
        } else if (command.equals("say")) {
            OptionMapping messageOption = event.getOption("message");
            String message = messageOption.getAsString();
            event.getChannel().sendMessage(message).queue();
            event.reply("Your message is sent").setEphemeral(true).queue();
        } else if (command.equals("chat")) {
            OptionMapping contentOption = event.getOption("content");
            String content = contentOption.getAsString();

            MessageChannel channel;
            //Channel channel;
            OptionMapping channelOption = event.getOption("channel");
            if (channelOption != null){
                channel = channelOption.getAsChannel().asTextChannel();
            }else{
                channel = event.getChannel();
            }
            channel.sendMessage(content).queue();
            event.reply("Your chat content is sent").setEphemeral(true).queue();

        }else if (command.equals("mood")){
            OptionMapping option = event.getOption("type");
            String type = option.getAsString();
            String replyMessage = "";

            switch (type.toLowerCase()){
                case "angry" -> {
                    replyMessage = "You really pissed me off!";
                }
                case "haha" -> {
                    replyMessage = "Haha you are hilarious";
                }
                case "wow" -> {
                    replyMessage = "Wow, you surprised me!";
                }
            }
            event.reply(replyMessage).queue();
        }
    }

    @Override
    public void onGuildReady(GuildReadyEvent event) {
        //super.onGuildReady(event);
        List<CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("bark", "Shiba is greeting you by barking"));

        OptionData option1 = new OptionData(OptionType.STRING, "message","The message you want the bot say", true);
        commandData.add(Commands.slash("say","Make a bot say a message").addOptions(option1));

        commandData.add(Commands.slash("chat", "Make a bot chat in a chanel")
                .addOptions(new OptionData(OptionType.STRING,"content", "The content you want a bot say", true),
                        new OptionData(OptionType.CHANNEL, "channel","The channel you want to send this content in",false)
                                .setChannelTypes(ChannelType.TEXT)));

        commandData.add(Commands.slash("mood", "Express your current mood through text ")
                .addOptions(new OptionData(OptionType.STRING, "type", "The type of your mood", true)
                        .addChoice("Angry","angry")
                        .addChoice("Haha","haha")
                        .addChoice("Wow", "wow")));
        event.getGuild().updateCommands().addCommands(commandData).queue();
    }

    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        //super.onGuildJoin(event);
        List<CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("bark", "Shiba is greeting you by barking"));

        OptionData option1 = new OptionData(OptionType.STRING, "message","The message you want the bot say", true);
        commandData.add(Commands.slash("say","Make a bot say a message").addOptions(option1));

        commandData.add(Commands.slash("chat", "Make a bot chat in a chanel")
                .addOptions(new OptionData(OptionType.STRING,"content", "The content you want a bot say", true),
                        new OptionData(OptionType.CHANNEL, "channel","The channel you want to send this content in",false)
                                .setChannelTypes(ChannelType.TEXT)));

        commandData.add(Commands.slash("mood", "Express your current mood through text ")
                .addOptions(new OptionData(OptionType.STRING, "type", "The type of your mood", true)
                        .addChoice("Angry","angry")
                        .addChoice("Haha","haha")
                        .addChoice("Wow", "wow")));

        event.getGuild().updateCommands().addCommands(commandData).queue();

    }
}
