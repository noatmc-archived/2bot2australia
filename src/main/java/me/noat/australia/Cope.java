package me.noat.australia;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class Cope {
    public static void main(String[] args) throws LoginException, InterruptedException {
         JDABuilder.createDefault("token")
                 .addEventListeners(new Listener())
                .setActivity(Activity.watching("2b2t Australia API"))
                .build().awaitReady();
    }

}
