package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

public class TestBot {
    private final Dotenv config;
    private final ShardManager shardManager;

    /**
     * Load environmental variables and builds the bot shard manager.
     * @throws LoginException when bot token is invalid
     */
    public TestBot() throws LoginException {
        config = Dotenv.configure().load();
        String token = config.get("TOKEN");
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.playing("Grand Theft Auto V"));
        shardManager = builder.build();

    }

    public Dotenv getConfig(){
        return config;
    }

    /**
     * Retrieve the bot shard manager.
     * @return the shard manager instance for the bot.
     */

    public ShardManager getShardManager(){
        return shardManager;
    }
    public static void main(String[] args){
        try {
            TestBot bot = new TestBot();
        }catch (LoginException e){
            System.out.println("Error: Provided bot token is invalid");
            e.printStackTrace();
        }
    }
}
