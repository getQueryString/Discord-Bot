package listener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import de.query.queryBot;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {

        String message = e.getMessage().getContentRaw();

        if (e.isFromType(ChannelType.TEXT)) {
            TextChannel channel = e.getTextChannel();


            if (message.startsWith("!")) {

                String[] args = message.split(" ");

                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("!hi")) {

                        channel.sendMessage("Moin " + e.getMember().getAsMention() + "!").queue();
                    }
                    if (args[0].equalsIgnoreCase("!time")) {

                        SimpleDateFormat timeDay = new SimpleDateFormat("dd.MM.YYYY");
                        SimpleDateFormat timeTime = new SimpleDateFormat("HH:mm:ss");

                        channel.sendMessage("Es ist der " + timeDay.format(new Date()) + " und es ist " + timeTime.format(new Date()) + " Uhr").queue();
                    }
                    if (args[0].equalsIgnoreCase("!test1")) {
                        channel.sendMessage("test0").queue();
                    }
                }
            }
            if (message.startsWith("!")) {

                String[] args = message.substring(1).split(" ");

                if (args.length > 1) {
                    if (!queryBot.INSTANCE.getCmdMan().perform(args[0], e.getMember(), channel, e.getMessage())) {
                        channel.sendMessage("'Unbekannter Befehl'").complete().delete().queueAfter(2, TimeUnit.SECONDS);
                        ;
                    }

                }
            }
        }

    }

}
