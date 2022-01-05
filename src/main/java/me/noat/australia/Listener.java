package me.noat.australia;

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
            if (event.getMessage().getContentRaw().equalsIgnoreCase("2bau.info")) {
                EmbedBuilder embedBuilder = new EmbedBuilder();
                embedBuilder.setTitle("Server Info");
                try {
                    embedBuilder.setColor(new Color(143, 255, 167));
                    embedBuilder.addField(":flag_au: - Name", JsonUtil.parse("https://api.2b2t.com.au/v1/server", "name"), false);
                    embedBuilder.addField(":star: - MOTD", JsonUtil.parse("https://api.2b2t.com.au/v1/server", "motd"), false);
                    embedBuilder.addField(":desktop: - Version", JsonUtil.parse("https://api.2b2t.com.au/v1/server", "version"), false);
                    embedBuilder.addField(":clock1: - Uptime", JsonUtil.parse("https://api.2b2t.com.au/v1/server", "uptime"), false);
                    embedBuilder.addField(":computer: - TPS", JsonUtil.parseArray("https://api.2b2t.com.au/v1/server","performance", "tps"), false);
                    event.getChannel().sendMessageEmbeds(embedBuilder.build()).queue();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (event.getMessage().getContentRaw().contains("2bau.player")) {
                if (args.size() < 1) {
                    event.getChannel().sendMessage("atleast add player dummy").queue();
                } else {
                    String uuid = JsonUtil.getUUIDFromName(args.get(1));
                    try {
                        EmbedBuilder embedBuilder = new EmbedBuilder();
                        embedBuilder.setAuthor(args.get(1), "https://namemc.com/profile/" + args.get(1), "https://cravatar.eu/head/" + uuid + "/128");
                        embedBuilder.setColor(new Color(148,169,255));
                        embedBuilder.addField("Join Date", JsonUtil.parse("https://api.2b2t.com.au/v1/players/" + uuid, "joinDate"), false);
                        embedBuilder.addField("Play Time", JsonUtil.parse("https://api.2b2t.com.au/v1/players/" + uuid, "playTime"), false);
                        event.getChannel().sendMessageEmbeds(embedBuilder.build()).queue();
                    } catch (Exception exc) {
                        // System.out.println("https://cravatar.eu/head/" + uuid + "/128.png");
                        exc.printStackTrace();
                        event.getChannel().sendMessage("invalid username or player isn't online :(").queue();
                    }
                }
            }
        }
}
