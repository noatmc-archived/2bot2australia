import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class Listener extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        List<String> args = Arrays.asList(event.getMessage().getContentRaw().split(" "));
        if (event.getMessage().getContentRaw().equalsIgnoreCase("+help")) {
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle("Help Page");
            embedBuilder.setColor(new Color(26, 196, 9, 255));
            embedBuilder.addField(":desktop: - Info", "Tells u info about 2b au.", false);
            embedBuilder.addField(":computer: - Player", "Tells u info about a player playing 2b au.", false);
            event.getChannel().sendMessageEmbeds(embedBuilder.build()).queue();
        }
        else if (event.getMessage().getContentRaw().equalsIgnoreCase("+info")) {
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle("Server Info");
            try {
                embedBuilder.setColor(new Color(197, 8, 20));
                embedBuilder.addField(":flag_au: - Name", JsonUtil.parse("https://api.2b2t.com.au/v1/server", "name"), false);
                embedBuilder.addField(":star: - MOTD", JsonUtil.parse("https://api.2b2t.com.au/v1/server", "motd"), false);
                embedBuilder.addField(":desktop: - Version", JsonUtil.parse("https://api.2b2t.com.au/v1/server", "version"), false);
                embedBuilder.addField(":clock1: - Uptime", JsonUtil.parse("https://api.2b2t.com.au/v1/server", "uptime"), false);
                embedBuilder.addField(":computer: - TPS", JsonUtil.parseArray("https://api.2b2t.com.au/v1/server", "performance", "tps"), false);
                String memory = JsonUtil.parseArray("https://api.2b2t.com.au/v1/server", "performance", "totalMemory");
                embedBuilder.addField(":computer: - Memory", memory.substring(0, memory.length() - 6) + " MB", false);
                embedBuilder.addField(":computer: - CPU(s)", JsonUtil.parseArray("https://api.2b2t.com.au/v1/server", "performance", "cpus"), false);
                event.getChannel().sendMessageEmbeds(embedBuilder.build()).queue();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (event.getMessage().getContentRaw().contains("+player")) {
            if (args.size() > 1) {
                String uuid = JsonUtil.getUUIDFromName(args.get(1));
                try {
                    EmbedBuilder embedBuilder = new EmbedBuilder();
                    embedBuilder.setAuthor(args.get(1), "https://namemc.com/profile/" + args.get(1), "https://cravatar.eu/head/" + uuid + "/128");
                    embedBuilder.setColor(new Color(148, 169, 255));
                    embedBuilder.addField("Join Date", JsonUtil.parse("https://api.2b2t.com.au/v1/players/" + uuid, "joinDate"), false);
                    embedBuilder.addField("Play Time", JsonUtil.parse("https://api.2b2t.com.au/v1/players/" + uuid, "playTime"), false);
                    embedBuilder.addField("Death(s)", JsonUtil.parseArray("https://api.2b2t.com.au/v1/players/" + uuid, "statistics", "deaths"), false);
                    event.getChannel().sendMessageEmbeds(embedBuilder.build()).queue();
                } catch (Exception e) {
                    e.printStackTrace();
                    event.getMessage().addReaction("❌").queue();
                }
            }
            else {
                event.getMessage().addReaction("❌").queue();
            }
        }
    }
}