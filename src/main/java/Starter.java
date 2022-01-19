import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

/**
 * @author perry.
 * @since 1/19/2022.
 */
public class Starter {
    public static void main(String[] args) {
        try {
            JDABuilder.createDefault("Insert Discord bot token.")
                .addEventListeners(new Listener())
                .setActivity(Activity.playing("+ Prefix"))
                .build().awaitReady();
        } catch (InterruptedException e) {
            System.out.println("ERROR! Thread Interrupted.");
            e.printStackTrace();
        } catch (LoginException e) {
            System.out.println("ERROR! Login Failure.");
            e.printStackTrace();
        }
    }
}
