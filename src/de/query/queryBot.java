package de.query;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

import listener.CommandListener;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

public class queryBot {

    public static queryBot INSTANCE;

    public ShardManager shardMan;
    private CommandManager cmdMan;

    public static void main(String[] args) {


        try {

            Runnable r = () -> {

                Scanner sc = new Scanner(System.in);
                while (true) {

                    if (sc.hasNext()) {
                        String s = sc.next();
                        if (s.equalsIgnoreCase("quit")) {
                            INSTANCE.shutdown();
                        }
                    }
                }

            };

            Thread t = new Thread(r);


            t.start();

            new queryBot();
        } catch (LoginException | IllegalArgumentException e) {
            e.printStackTrace();
        }

    }

    public queryBot() throws LoginException, IllegalArgumentException {
        INSTANCE = this;

        DefaultShardManagerBuilder builder = new DefaultShardManagerBuilder();
        builder.setToken("TOKEN");

        builder.setActivity(Activity.playing("stands still and is a Bot."));
        builder.setStatus(OnlineStatus.ONLINE);

        this.cmdMan = new CommandManager();

        builder.addEventListeners(new CommandListener());

        shardMan = builder.build();
        System.out.println("Bot online.");


    }

    public void shutdown() {
        shardMan.setStatus(OnlineStatus.OFFLINE);
        System.out.println("Bot offline.");
        System.exit(0);
    }

    public CommandManager getCmdMan() {
        return cmdMan;
    }

}
