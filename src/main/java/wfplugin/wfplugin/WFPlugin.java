package wfplugin.wfplugin;

import com.flowpowered.math.vector.Vector3d;
import com.google.inject.Inject;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.dynmap.DynmapCommonAPI;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.block.InteractBlockEvent;
import org.spongepowered.api.event.cause.EventContext;
import org.spongepowered.api.event.cause.EventContextKeys;
import org.spongepowered.api.event.entity.InteractEntityEvent;
import org.spongepowered.api.event.entity.MoveEntityEvent;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.event.filter.cause.Root;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.event.game.state.GameStoppingServerEvent;
import org.spongepowered.api.event.message.MessageChannelEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.scoreboard.Team;
import org.spongepowered.api.scoreboard.Visibilities;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.action.TextActions;
import org.spongepowered.api.text.format.TextColor;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.serializer.TextSerializers;
import org.spongepowered.api.text.title.Title;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;
import wfplugin.wfplugin.commands.CommandManager;
import wfplugin.wfplugin.commands.DiscordCommand;
import wfplugin.wfplugin.commands.WorldTeleport;
import wfplugin.wfplugin.commands.bank.BankCommand;
import wfplugin.wfplugin.commands.chat.ChatCommand;
import wfplugin.wfplugin.commands.chat.MuteCommand;
import wfplugin.wfplugin.commands.chat.UnMuteCommand;
import wfplugin.wfplugin.commands.country.CountryCommand;
import wfplugin.wfplugin.commands.custommessage.CustomMessageCommand;
import wfplugin.wfplugin.storage.*;
import wfplugin.wfplugin.storage.country.Countries;
import wfplugin.wfplugin.storage.country.Country;
import wfplugin.wfplugin.storage.country.CountryGroup;
import wfplugin.wfplugin.storage.country.Plot;
import wfplugin.wfplugin.storage.log.Log;
import wfplugin.wfplugin.storage.log.LogElement;
import wfplugin.wfplugin.utils.Discord;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Stream;

import static wfplugin.wfplugin.storage.ConfigManager.flush;
import static wfplugin.wfplugin.storage.ConfigManager.loadOrCreate;

@Plugin(
        id = "wfplugin",
        name = "WF",
        url = "https://redise.fun",
        description = "RedISE WF Plugin",
        authors = {
                "slavapmk"
        }
)
public class WFPlugin {
    public static WFPlugin plugin;
    public static Bank bank;
    public static DiscordPlayers discordPlayers;
    public static Strings strings;
    public static Countries countries;
    public static OldPos oldPos;
    public static Log log;
    public static HashMap<String, Region2d> selectedRegions = new HashMap<>();
    public static HashMap<String, String> invites = new HashMap<>();
    public static HashMap<String, String> requests = new HashMap<>();
    public static HashMap<String, String> lastLocations = new HashMap<>();
    public static HashMap<String, ChatMode> chatMode = new HashMap<>();
    public static HashMap<String, Mute> mutes = new HashMap<>();
    public static TaskManager tasks = new TaskManager();
    private final Team team = Team
            .builder()
            .name("wf")
            .nameTagVisibility(Visibilities.NEVER)
            .build();
    @Inject
    private Logger logger;
    private DynmapCommonAPI dynmapAPI;

    public static void flushConfigs() {
        try {
            flush("config/wf/strings.json", strings);
            flush("config/wf/bank.json", bank);
            flush("config/wf/countries.json", countries);
            flush("config/wf/discord.json", discordPlayers);
            flush("config/wf/log.json", log);
            flush("config/wf/tasks.json", tasks);
            flush("config/wf/oldPos.json", oldPos);
        } catch (IOException e) {
            error("Error while loading configs");
            error(e + "");
        }
    }

    static Stream<Player> getPlayersStream(
            Collection<Player> onlinePlayers,
            Player player, ChatMode chatMode, Country country, Text countryPrefix, Text textMessage, Text prefix, Text suffix
    ) {
        switch (chatMode) {
            case LOCAL:
                return onlinePlayers.stream().filter(player1 -> {
                    boolean b = get2dScale(player.getPosition(), player1.getPosition()) <= 200;
                    if (!b && player1.hasPermission("wf.admin.chat.local-spy"))
                        player1.sendMessage(Text.of(strings.spy(), prefix, countryPrefix, player.getName(), suffix, " > ", textMessage));
                    return b;
                });
            case GLOBAL:
                return onlinePlayers.stream();
            case COUNTRY:
                return onlinePlayers.stream().filter(player1 -> country.citizens.contains(player1.getName()));
            default:
                throw new IllegalArgumentException("Invalid value: " + chatMode);
        }
    }

    public static double get2dScale(Vector3d pos1, Vector3d pos2) {
        return Math.sqrt(((int) Math.max(pos1.getX(), pos2.getX()) - (int) Math.min(pos1.getX(), pos2.getX())) * ((int) Math.max(pos1.getX(), pos2.getX()) - (int) Math.min(pos1.getX(), pos2.getX())) + ((int) Math.max(pos1.getZ(), pos2.getZ()) - (int) Math.min(pos1.getZ(), pos2.getZ())) * ((int) Math.max(pos1.getZ(), pos2.getZ()) - (int) Math.min(pos1.getZ(), pos2.getZ())));
    }

    public static void log(String text) {
        plugin.logger.info(text);
    }

    public static void log(LogElement logElement) {
        log(logElement + "");
        log.add(logElement);
    }

    public static void error(String text) {
        plugin.logger.error(text);
    }

    @Listener
    public void onServerStart(GameStartingServerEvent event) {
        plugin = this;
        loadConfigs();

        Sponge.getPluginManager().getPlugin("wfplugin").ifPresent(pluginContainer -> CommandManager.registerCommands(
                pluginContainer,
                new BankCommand(),
                new CountryCommand(),
                new DiscordCommand(),
                new ChatCommand(),
                new MuteCommand(),
                new UnMuteCommand(),
                new WorldTeleport(),
                new CustomMessageCommand()
        ));

        Sponge.getServer().getServerScoreboard().ifPresent(scoreboard -> {
            if (!scoreboard.getTeam(team.getName()).isPresent())
                scoreboard.registerTeam(team);
        });

//        PluginManager pm = Sponge.getPluginManager();
//        Optional<PluginContainer> dynmap = pm.getPlugin("dynmap");
//        if (dynmap.isPresent()) {
//            Optional<?> instance = dynmap.get().getInstance();
//            instance.ifPresent(o -> dynmapAPI = (DynmapCommonAPI) o);
//            MarkerSet countryMarkers = dynmapAPI.getMarkerAPI().getMarkerSet("countryMarkers");
//            for (Country country : countries.countries) {
//                if (country.id == -1)
//                    country.id = countries.newId();
//                for (Region2d region : country.regions.regions) {
//                    String id = "country_" + country.id + "_" + region.id;
//                    AreaMarker areaMarker = countryMarkers.findAreaMarker(id);
//                    if (areaMarker == null)
//                        countryMarkers.createAreaMarker(id, country.name, false, "world", new double[]{region.getCorrected().getStart().x, region.getCorrected().getStop().x}, new double[]{region.getCorrected().getStart().z, region.getCorrected().getStop().z}, false);
//                }
//            }
//        }
        Discord.init();
    }

    @Listener
    public void onServerStop(GameStoppingServerEvent event) {
        flushConfigs();
        Discord.shutdown();
    }

    private void loadConfigs() {
        try {
            strings = loadOrCreate("config/wf/strings.json", new Strings());
            countries = loadOrCreate("config/wf/countries.json", new Countries());
            bank = loadOrCreate("config/wf/bank.json", new Bank());
            discordPlayers = loadOrCreate("config/wf/discord.json", new DiscordPlayers());
            log = loadOrCreate("config/wf/log.json", new Log());
            tasks = loadOrCreate("config/wf/tasks.json", new TaskManager());
            oldPos = loadOrCreate("config/wf/oldPos.json", new OldPos());
            log(strings + "");
        } catch (IOException e) {
            logger.error("Error while loading configs");
            logger.error(e + "");
        }
        tasks.activate();
    }

    @Listener
    public void playerJoinEvent(ClientConnectionEvent.Join e) {
        Player p = e.getTargetEntity();
        bank.players.putIfAbsent(p.getName(), 450);


        Sponge.getServer().getServerScoreboard().flatMap(scoreboard -> scoreboard.getTeam(team.getName())).ifPresent(team1 -> team1.addMember(p.getTeamRepresentation()));
        log(p.getTeamRepresentation().toString());

        Country country = countries.get(p.getName());
        if (country != null) {
            for (WFTask task : tasks.tasks)
                if (task.consumer.equals(country.name) || task.sender.equals(country.name))
                    p.sendMessage(strings.declaringWar(task.sender, task.consumer, task.stopTime));
            for (String war : country.wars)
                p.sendMessage(strings.declaredWar(war, country.name));
        }

        String message = strings.joinMessages.get(p.getName());
        if (message == null)
            e.setMessageCancelled(true);
        else
            e.setMessage(getDeserialize(message.replace("%player", p.getName())));
    }

    @Listener
    public void playerLeaveEvent(ClientConnectionEvent.Disconnect e) {
        Player p = e.getTargetEntity();
        invites.remove(p.getName());
        requests.remove(p.getName());

        String message = strings.leaveMessages.get(p.getName());
        if (message == null)
            e.setMessageCancelled(true);
        else
            e.setMessage(getDeserialize(message.replace("%player", p.getName())));
    }

    @NotNull
    private Text getDeserialize(String message) {
        if (message == null)
            return Text.EMPTY;
        return TextSerializers.FORMATTING_CODE.deserialize(message);
    }

    @Listener
    public void playerLeftClickBlock(InteractBlockEvent.Primary e) {
        onClick(true, e);
    }

    @Listener
    public void playerRightClickBlock(InteractBlockEvent.Secondary e) {
        onClick(false, e);
    }

    @Listener(order = Order.POST)
    public void playerMoveEvent(MoveEntityEvent event, @First Player player) {
        Location<World> worldLocation = event.getToTransform().getLocation();

        Optional<Country> byLocation = countries.getByLocation(worldLocation);
        String s = lastLocations.get(player.getName());
        if (byLocation.isPresent()) {
            String name = byLocation.get().name;
            Optional<Plot> plot = byLocation.get().plotByLocation(worldLocation);
            if (plot.isPresent())
                name = name + " - " + plot.get().name;

            TextColor color = TextColors.RED;
            if (byLocation.get().allowInteract(player, worldLocation))
                color = TextColors.GREEN;

            if (!name.equals(s)) {
                lastLocations.put(player.getName(), name);
                player.sendTitle(strings.parseActionBar(color, Text.of(name)));
                String finalName = name;
                byLocation.ifPresent(country -> log(player.getName() + " entered " + finalName));
            }
        } else
            lastLocations.remove(player.getName());
    }

    @Listener
    public void playerRightClick(InteractEntityEvent.Secondary e) {
        e.getCause().getContext().require(EventContextKeys.OWNER).getPlayer().ifPresent(
                player -> {
                    Entity targetEntity = e.getTargetEntity();
                    if (targetEntity instanceof Player)
                        player.sendTitle(Title.builder().actionBar(strings.playerName(((Player) targetEntity).getName())).build());
                }
        );
    }

    public void onClick(boolean lmb, InteractBlockEvent e) {
        EventContext context = e.getCause().getContext();
        context.require(EventContextKeys.OWNER).getPlayer().ifPresent(player -> {
            String name = player.getName();

            e.getTargetBlock().getLocation().ifPresent(worldLocation -> {
                if (!countries.allowInteract(player, worldLocation)) {
                    e.setCancelled(true);
                    player.sendTitle(strings.notEnoughRightsTitle());
                    return;
                }

                Optional<ItemStackSnapshot> type = context.get(EventContextKeys.USED_ITEM);
                if (!type.isPresent() || type.get().getType() != ItemTypes.WOODEN_HOE)
                    return;
                Region2d region2d = selectedRegions.getOrDefault(name, new Region2d());
                Position2d position2d = new Position2d(worldLocation.getBlockX(), worldLocation.getBlockZ());
                if (lmb) {
                    region2d.setStart(position2d);
                    player.sendMessage(strings.startPosition(region2d, position2d));
                }
                if (!lmb) {
                    region2d.setStop(position2d);
                    player.sendMessage(strings.stopPosition(region2d, position2d));
                }
                selectedRegions.put(name, region2d);
                e.setCancelled(true);
            });
        });
    }

    @Listener
    public void onPlayerChat(MessageChannelEvent.Chat e, @Root Player messageSender) {
        Mute mute = mutes.get(messageSender.getName());
        if (mute != null)
            if (mute.endTime == -1 || System.currentTimeMillis() < mute.endTime) {
                messageSender.sendMessage(strings.muted());
                e.setMessageCancelled(true);
            } else
                mutes.remove(messageSender.getName());
        if (e.isMessageCancelled()) return;
        e.setCancelled(true);

        String plain = e.getRawMessage().toPlain();
        String playerName = messageSender.getName();
        ChatMode chatMode = WFPlugin.chatMode.get(playerName);
        if (chatMode == null)
            if (plain.startsWith("!")) {
                plain = plain.substring(1);
                chatMode = ChatMode.GLOBAL;
            } else
                chatMode = ChatMode.LOCAL;

        Country country = countries.getByCitizen(playerName);
        if (country == null && chatMode == ChatMode.COUNTRY) {
            messageSender.sendMessage(Text.of(TextColors.RED, "\u0412\u044B \u043D\u0435 \u0441\u043E\u0441\u0442\u043E\u0438\u0442\u0435 \u043D\u0438 \u0432 \u043E\u0434\u043D\u043E\u0439 \u043D\u0430\u0446\u0438\u0438"));
            return;
        }
        Text countryPrefix = Text.EMPTY;
        if (country != null) {
            Text countryName;
            String status;
            if (country.isLeader(playerName)) {
                countryName = Text.of(TextColors.GOLD, country.name);
                status = "\u041B\u0438\u0434\u0435\u0440";
            } else {
                CountryGroup byPlayer = country.groups.getByPlayer(playerName);
                status = byPlayer.name;
                countryName = getDeserialize(byPlayer.color + country.name);
            }
            countryPrefix = Text.of(
                    TextActions.showText(Text.of(status, " ", country.name)),
                    TextColors.WHITE, "[",
                    countryName,
                    TextColors.WHITE, "] "
            );
        }

        User user = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(messageSender);
        Text textMessage;
        if (messageSender.hasPermission("wf.admin.chat.colored"))
            textMessage = getDeserialize(plain);
        else
            textMessage = Text.of(plain);
        Text prefix = getDeserialize(user.getCachedData().getMetaData().getPrefix());
        Text suffix = getDeserialize(user.getCachedData().getMetaData().getSuffix());
        Text chatModePrefix = chatMode.prefix;

        Text.Builder senderNameText = Text.builder()
                .onHover(TextActions.showText(Text.of(TextColors.WHITE, "\u041D\u0430\u0436\u043C\u0438\u0442\u0435 \u0434\u043B\u044F ", TextColors.GOLD, "/msg " + playerName)))
                .onClick(TextActions.suggestCommand("/msg " + playerName + " "))
                .append(Text.of(playerName));

        Text resultMessage = Text.of(chatModePrefix, prefix, countryPrefix, senderNameText, suffix, " > ", textMessage);
        getPlayersStream(
                Sponge.getServer().getOnlinePlayers(),
                messageSender,
                chatMode,
                country,
                countryPrefix,
                textMessage,
                prefix,
                suffix
        )
                .forEach(player -> player.sendMessage(resultMessage));

        logger.info(resultMessage.toPlain());
    }
}
