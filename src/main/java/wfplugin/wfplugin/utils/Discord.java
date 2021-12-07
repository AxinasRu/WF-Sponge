package wfplugin.wfplugin.utils;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;
import net.dv8tion.jda.api.requests.restaction.GuildAction;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.dv8tion.jda.api.utils.concurrent.Task;
import org.jetbrains.annotations.NotNull;
import wfplugin.wfplugin.WFPlugin;

import javax.security.auth.login.LoginException;
import java.util.List;

public class Discord implements EventListener {
    public static final String TOKEN = "NzkzMzczMTU2NDE3MDc3MjU5.X-rUVA.VR9FRWlXzqCckszDnrePpJjIw40";
    private static final Discord discord = new Discord();
    private static Guild guild = null;
    private static JDA jda;

    public static void init() {
        try {
            JDABuilder builder = JDABuilder.createDefault(TOKEN);
            builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
            builder.enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS);
            builder.setBulkDeleteSplittingEnabled(false);
            jda = builder.build();
            jda.awaitReady();
            GuildAction j = jda.createGuild("j");
            guild = jda.getGuilds().get(0);
            jda.addEventListener(discord);
        } catch (LoginException | InterruptedException e) {
            WFPlugin.error(e + "");
        }
    }

    public static void renameUser(String nick, String newName) {
        getMember(nick).onSuccess(members -> {
            AuditableRestAction<Void> voidAuditableRestAction = members.get(0).modifyNickname(newName);
            voidAuditableRestAction.complete();
        });
    }

    private static Task<List<Member>> getMember(String nick) {
        return guild.findMembers(member -> member.getUser().getAsTag().equals(nick));
    }

    public static void sendMessage(String nick, String message) {
        getMember(nick).onSuccess(members -> {
            User user = members.get(0).getUser();
            if (!user.isBot())
                user.openPrivateChannel().complete().sendMessage(message).complete();
        });
    }

    public static void shutdown() {
        jda.shutdownNow();
    }

    public static String integrate(String nick, String tag) {
        WFPlugin.discordPlayers.users.put(tag, nick);
        String token = new RandomString(7).nextString();
        WFPlugin.discordPlayers.tokens.put(token, nick);
        sendMessage(tag, "\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u0442\u043E\u043A\u0435\u043D");
        return token;
    }

    @Override
    public void onEvent(@NotNull GenericEvent event) {
        if (event instanceof MessageReceivedEvent) {
            if (((MessageReceivedEvent) event).isFromType(ChannelType.PRIVATE)) {
                String message = ((MessageReceivedEvent) event).getMessage().getContentRaw();

                String name = ((MessageReceivedEvent) event).getAuthor().getAsTag();
                String minecraftPlayer = WFPlugin.discordPlayers.tokens.get(message);

                if (minecraftPlayer != null) {
                    renameUser(name, minecraftPlayer);
                    sendMessage(name, "Success");
                } else
                    sendMessage(name, "Invalid token");
            }
        }
    }
}
