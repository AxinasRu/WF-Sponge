package wfplugin.wfplugin.storage;

import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.action.TextActions;
import org.spongepowered.api.text.format.TextColor;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.serializer.TextSerializers;
import org.spongepowered.api.text.title.Title;
import wfplugin.wfplugin.storage.country.Country;
import wfplugin.wfplugin.storage.country.CountryGroup;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Strings {
    public String insertDescription = "\u041F\u043E\u043F\u043E\u043B\u043D\u0438\u0442\u044C \u0441\u0447\u0451\u0442";
    public String insertSuccessStart = "\u0412\u0430\u0448 \u0441\u0447\u0451\u0442 \u0431\u044B\u043B \u043F\u043E\u043F\u043E\u043B\u043D\u0435\u043D \u043D\u0430 ";
    public String insertSuccessStop = " \u0441 \u0432\u0430\u0448\u0435\u0433\u043E \u0438\u043D\u0432\u0435\u043D\u0442\u0430\u0440\u044F";
    public String withdrawSuccessStart = "\u0421 \u0432\u0430\u0448\u0435\u0433\u043E \u0441\u0447\u0451\u0442\u0430 \u0431\u044B\u043B\u043E \u0441\u043F\u0438\u0441\u0430\u043D\u043D\u043E ";
    public String moneySuccess = "\u0412\u0430\u0448 \u0431\u0430\u043B\u0430\u043D\u0441 - ";
    public String moneySuccessSee = "\u0411\u0430\u043B\u0430\u043D\u0441 \u0438\u0433\u0440\u043E\u043A\u0430 ";
    public String payError = "\u0412\u044B \u043D\u0435 \u043C\u043E\u0436\u0435\u0442\u0435 \u043E\u0442\u043F\u0440\u0430\u0432\u0438\u0442\u044C ";
    public String giveMoneySuccess = "\u0412\u0430\u0448 \u0431\u0430\u043B\u0430\u043D\u0441 \u043F\u043E\u043F\u043E\u043B\u043D\u0435\u043D \u043D\u0430 ";
    public String giveMoneySuccessSrcStart = "\u0411\u0430\u043B\u0430\u043D\u0441 ";
    public String giveMoneySuccessSrcStop = " \u043F\u043E\u043F\u043E\u043B\u043D\u0435\u043D \u043D\u0430 ";
    public String setBalanceSuccess = "\u0412\u0430\u0448 \u0431\u0430\u043B\u0430\u043D\u0441 \u0443\u0441\u0442\u0430\u043D\u043E\u0432\u043B\u0435\u043D \u043D\u0430 ";
    public String setBalanceSuccessSrcStart = "\u0411\u0430\u043B\u0430\u043D\u0441 ";
    public String setBalanceSuccessSrcStop = " \u0443\u0441\u0442\u0430\u043D\u043E\u0432\u043B\u0435\u043D \u043D\u0430 ";
    public String playerNameStart = "-=[";
    public String playerNameStop = "]=-";
    public String startPosition = "\u041F\u0435\u0440\u0432\u0430\u044F \u043F\u043E\u0437\u0438\u0446\u0438\u044F \u0443\u0441\u0442\u0430\u043D\u043E\u0432\u043B\u0435\u043D\u0430 \u0432 \u0442\u043E\u0447\u043A\u0443 ";
    public String stopPosition = "\u0412\u0442\u043E\u0440\u0430\u044F \u043F\u043E\u0437\u0438\u0446\u0438\u044F \u0443\u0441\u0442\u0430\u043D\u043E\u0432\u043B\u0435\u043D\u0430 \u0432 \u0442\u043E\u0447\u043A\u0443 ";
    public String notEnoughRights = "\u041D\u0435\u0434\u043E\u0441\u0442\u0430\u0442\u043E\u0447\u043D\u043E \u043F\u0440\u0430\u0432 \u0434\u043B\u044F \u0434\u0435\u0439\u0441\u0442\u0432\u0438\u044F";
    public String countryCreateNotEnoughMoneyStart = "\u0414\u043B\u044F \u043E\u0441\u0443\u0449\u0435\u0441\u0442\u0432\u043B\u0435\u043D\u0438\u044F \u044D\u0442\u043E\u0439 \u043E\u043F\u0435\u0440\u0430\u0446\u0438\u0438, \u0442\u0440\u0435\u0431\u0443\u0435\u0442\u0441\u044F ";
    public String countryCreateNotEnoughMoneyStop = " \u043D\u0430 \u0441\u0447\u0435\u0442\u0443";
    public String createCountryAlreadyExistStart = "\u0421\u0442\u0440\u0430\u043D\u0430 \u0441 \u043D\u0430\u0437\u0432\u0430\u043D\u0438\u0435\u043C ";
    public String createCountryAlreadyExistStop = " \u0443\u0436\u0435 \u0441\u0443\u0449\u0435\u0441\u0442\u0432\u0443\u0435\u0442";
    public String citizenLeft = " \u043F\u043E\u043A\u0438\u043D\u0443\u043B ";
    public String citizenJoin = " \u0442\u0435\u043F\u0435\u0440\u044C \u044F\u0432\u043B\u044F\u0435\u0442\u0441\u044F \u0436\u0438\u0442\u0435\u043B\u0435\u043C ";
    public String ministerAdd = " \u043D\u0430\u0437\u043D\u0430\u0447\u0435\u043D \u043C\u0438\u043D\u0438\u0441\u0442\u0440\u043E\u043C \u0432 ";
    public String ministerRemove = " \u0441\u043D\u044F\u0442 \u0441 \u043F\u043E\u0441\u0442\u0430 \u043C\u0438\u043D\u0438\u0441\u0442\u0440\u0430 \u0432 ";
    public String banCitizen = " \u043F\u043E\u043B\u0443\u0447\u0438\u043B \u0431\u0430\u043D \u0432 ";
    public String unbanCitizen = " \u043F\u043E\u043B\u0443\u0447\u0438\u043B \u0440\u0430\u0437\u0431\u0430\u043D \u0432 ";
    public String restrictCitizen = " \u043F\u043E\u043B\u0443\u0447\u0438\u043B \u043E\u0433\u0440\u0430\u043D\u0438\u0447\u0435\u043D\u0438\u0435 \u0432 \u0434\u0435\u0439\u0441\u0442\u0432\u0438\u044F\u0445 \u0432 ";
    public String unRestrictCitizenStart = "\u0421 ";
    public String unRestrictCitizenStop = " \u0441\u043D\u044F\u0442\u044B \u0432\u0441\u0435 \u043E\u0433\u0440\u0430\u043D\u0438\u0447\u0435\u043D\u0438\u044F \u0432";
    public String createCountrySuccess = "\u0412\u044B \u0443\u0441\u043F\u0435\u0448\u043D\u043E \u0441\u043E\u0437\u0434\u0430\u043B\u0438 \u0441\u0442\u0440\u0430\u043D\u0443 ";
    public String countryRequestSent = "\u0412\u044B \u0443\u0441\u043F\u0435\u0448\u043D\u043E \u043E\u0442\u043F\u0440\u0430\u0432\u0438\u043B\u0438 \u0437\u0430\u043F\u0440\u043E\u0441 \u043D\u0430 \u0433\u0440\u0430\u0436\u0434\u0430\u043D\u0441\u0442\u0432\u043E \u0432 ";
    public String countryRequestReceive = "\u0412\u044B \u043F\u043E\u043B\u0443\u0447\u0438\u043B\u0438 \u0437\u0430\u043F\u0440\u043E\u0441 \u043D\u0430 \u0433\u0440\u0430\u0436\u0434\u0430\u043D\u0441\u0442\u0432\u043E \u043E\u0442 ";
    public String countryReceiveAccept = "\u041F\u0440\u0438\u043D\u044F\u0442\u044C";
    public String countryReceiveReject = "\u041E\u0442\u043A\u043B\u043E\u043D\u0438\u0442\u044C";
    public String countryCitizenRejectMinisterStart = "\u0417\u0430\u043F\u0440\u043E\u0441 \u043D\u0430 \u0433\u0440\u0430\u0436\u0434\u0430\u043D\u0441\u0442\u0432\u043E \u043E\u0442 ";
    public String countryCitizenRejectMinisterStop = " \u0431\u044B\u043B \u043E\u0442\u043A\u043B\u043E\u043D\u0451\u043D ";
    public String countryCitizenRejectStart = "\u0412\u0430\u0448 \u0437\u0430\u043F\u0440\u043E\u0441 \u043D\u0430 \u0432\u0441\u0442\u0443\u043F\u043B\u0435\u043D\u0438\u0435 \u0432 ";
    public String countryCitizenRejectStop = " \u0431\u044B\u043B \u043E\u0442\u043A\u043B\u043E\u043D\u0451\u043D.";
    public String countryInviteSent = "\u0412\u044B \u0443\u0441\u043F\u0435\u0448\u043D\u043E \u043E\u0442\u043F\u0440\u0430\u0432\u0438\u043B\u0438 \u0437\u0430\u043F\u0440\u043E\u0441 \u043D\u0430 \u0433\u0440\u0430\u0436\u0434\u0430\u043D\u0441\u0442\u0432\u043E \u0438\u0433\u0440\u043E\u043A\u0443 ";
    public String countryInviteReceive = "\u0412\u044B \u043F\u043E\u043B\u0443\u0447\u0438\u043B\u0438 \u0437\u0430\u043F\u0440\u043E\u0441 \u043D\u0430 \u0433\u0440\u0430\u0436\u0434\u0430\u043D\u0441\u0442\u0432\u043E \u043E\u0442 ";
    public String countryReject = "\u0412\u044B \u0443\u0441\u043F\u0435\u0448\u043D\u043E \u043E\u0442\u043A\u043B\u043E\u043D\u0438\u043B\u0438 \u0437\u0430\u043F\u0440\u043E\u0441 \u043D\u0430 \u0433\u0440\u0430\u0436\u0434\u0430\u043D\u0441\u0442\u0432\u043E \u0432 ";
    public String countryRejectMinisterStart = " \u043E\u0442\u043A\u043B\u043E\u043D\u0438\u043B \u0437\u0430\u044F\u0432\u043A\u0443 \u043D\u0430 \u0433\u0440\u0430\u0436\u0434\u0430\u043D\u0441\u0442\u0432\u043E.";
    public String onlyLeader = "\u0422\u043E\u043B\u044C\u043A\u043E \u043B\u0438\u0434\u0435\u0440 \u0438\u043C\u0435\u0435\u0442 \u043F\u0440\u0430\u0432\u0430 \u0434\u043B\u044F \u044D\u0442\u043E\u0433\u043E \u0434\u0435\u0439\u0441\u0442\u0432\u0438\u044F";
    public String onlyMinisters = "\u0422\u043E\u043B\u044C\u043A\u043E \u043C\u0438\u043D\u0438\u0441\u0442\u0440\u044B \u0438\u043C\u0435\u044E\u0442 \u043F\u0440\u0430\u0432\u0430 \u0434\u043B\u044F \u044D\u0442\u043E\u0433\u043E \u0434\u0435\u0439\u0441\u0442\u0432\u0438\u044F";
    public String countryRemovedStart = "\u0421\u0442\u0440\u0430\u043D\u0430 ";
    public String countryRemovedStop = " \u0431\u044B\u043B\u0430 \u0440\u0430\u0441\u0444\u043E\u0440\u043C\u0438\u0440\u043E\u0432\u0430\u043D\u0430";
    public String countryNotSelected = "\u041D\u0430\u0446\u0438\u044F \u043D\u0435 \u0432\u044B\u0431\u0440\u0430\u043D\u0430";
    public String listMinisters = "\u041C\u0438\u043D\u0438\u0441\u0442\u0440\u044B ";
    public String notEnoughMoney = "\u041D\u0435\u0434\u043E\u0441\u0442\u0430\u0442\u043E\u0447\u043D\u043E \u0441\u0440\u0435\u0434\u0441\u0442\u0432";
    public String tempNotWork = "\u0424\u0443\u043D\u043A\u0446\u0438\u044F \u0432\u0440\u0435\u043C\u0435\u043D\u043D\u043E \u043D\u0435 \u0440\u0430\u0431\u043E\u0442\u0430\u0435\u0442";
    public String nonMinister = " \u043D\u0435 \u044F\u0432\u043B\u044F\u0435\u0442\u0441\u044F \u043C\u0438\u043D\u0438\u0441\u0442\u0440\u043E\u043C!";
    public String newLeader = " \u0441\u043B\u043E\u0436\u0438\u043B \u0441\u0432\u043E\u0438 \u043F\u043E\u043B\u043D\u043E\u043C\u043E\u0447\u0438\u044F. \u041D\u043E\u0432\u044B\u0439 \u043F\u043E\u0441\u0442 \u043B\u0438\u0434\u0435\u0440\u0430 \u0437\u0430\u043D\u044F\u043B ";
    public String banknote = "\u0411\u0430\u043D\u043A\u043D\u043E\u0442\u0430 ";
    public String paperMoney = "100$";
    public String coin = "\u041C\u043E\u043D\u0435\u0442\u0430 ";
    public String coinMoney = "1$";
    public String moneyLore = "\u0412\u044B \u043C\u043E\u0436\u0435\u0442\u0435 \u043F\u043E\u043B\u043E\u0436\u0438\u0442\u044C \u0438\u0445 \u0432 \u0431\u0430\u043D\u043A \u0438\u0441\u043F\u043E\u043B\u044C\u0437\u0443\u044F ";
    public String moneyLoreCommand = "/bank insert";
    public String countryName = "\u041D\u0430\u0437\u0432\u0430\u043D\u0438\u0435: ";
    public String countryLeader = "\u041B\u0438\u0434\u0435\u0440: ";
    public String countrySize = "\u0421\u0443\u043C\u043C\u0430\u0440\u043D\u044B\u0439 \u0440\u0430\u0437\u043C\u0435\u0440 \u0442\u0435\u0440\u0440\u0438\u0442\u043E\u0440\u0438\u0439: ";
    public String countryCitizens = "\u0416\u0438\u0442\u0435\u043B\u0438: ";
    public String countryMinisters = "\u041C\u0438\u043D\u0438\u0441\u0442\u0440\u044B: ";
    public String countryRestricted = "\u041E\u0433\u0440\u0430\u043D\u0438\u0447\u0435\u043D\u043D\u044B\u0435: ";
    public String countryBalance = "\u0411\u0430\u043B\u0430\u043D\u0441: ";
    public String countryFreeBlocks = "\u0414\u043E\u0441\u0442\u0443\u043F\u043D\u044B\u0435 \u0431\u043B\u043E\u043A\u0438: ";
    public String countryRegion = "\u0420\u0435\u0433\u0438\u043E\u043D ";
    public String notEnoughBlocksStart = "\u0414\u043B\u044F \u0441\u043E\u0432\u0435\u0440\u0448\u0435\u043D\u0438\u044F \u043E\u043F\u0435\u0440\u0430\u0446\u0438\u0438 \u0432\u0430\u043C \u0442\u0440\u0435\u0431\u0443\u0435\u0442\u0441\u044F \u0435\u0449\u0451 ";
    public String notEnoughBlockStop = " \u0431\u043B\u043E\u043A\u043E\u0432 \u043D\u0430 \u0441\u0447\u0451\u0442\u0435 \u043D\u0430\u0446\u0438\u0438. \u041E\u043D\u0438 \u0432\u044B\u0434\u0430\u044E\u0442\u0441\u044F \u0437\u0430 \u043A\u0430\u0436\u0434\u043E\u0433\u043E \u043D\u043E\u0432\u043E\u0433\u043E \u0433\u0440\u0430\u0436\u0434\u0430\u043D\u0438\u043D\u0430. \u0422\u0430\u043A \u0436\u0435 \u0432\u044B \u043C\u043E\u0436\u0435\u0442\u0435 \u043A\u0443\u043F\u0438\u0442\u044C \u0438\u0445 \u043F\u043E \u043A\u043E\u043C\u043C\u0430\u043D\u0434\u0435";
    public String notEnoughCommandsStop = "/c buyblocks";
    public String successClaimed1 = "\u0412\u044B \u0443\u0441\u043F\u0435\u0448\u043D\u043E \u0441\u0434\u0435\u043B\u0430\u043B\u0438 \u043F\u0440\u0438\u0432\u0430\u0442 \u0441 \u0440\u0435\u0433\u0438\u043E\u043D\u043E\u043C ";
    public String successClaimed2 = " \u0431\u043B\u043E\u043A\u043E\u0432";
    public String successBuyBlocksStart = "\u0412\u044B \u0443\u0441\u043F\u0435\u0448\u043D\u043E \u0434\u043E\u043A\u0443\u043F\u0438\u043B\u0438 ";
    public String successBuyBlocksStop = " \u0431\u043B\u043E\u043A\u043E\u0432 \u0437\u0430 ";
    public String haveIntersections = "\u0420\u0435\u0433\u0438\u043E\u043D \u0438\u043C\u0435\u0435\u0442 \u043F\u0435\u0440\u0435\u0441\u0435\u0447\u0435\u043D\u0438\u044F \u0441 \u043F\u0440\u0438\u0432\u0430\u0442\u0430\u043C\u0438 \u0434\u0440\u0443\u0433\u0438\u0445 \u043D\u0430\u0446\u0438\u0439";
    public String nothingChanged = "\u041D\u0438\u0447\u0435\u0433\u043E \u043D\u0435 \u0438\u0437\u043C\u0435\u043D\u0438\u043B\u043E\u0441\u044C.";
    public String successUnClaimed1 = "\u041F\u0440\u0438\u0432\u0430\u0442 \u0431\u044B\u043B \u0443\u0434\u0430\u043B\u0451\u043D \u0441 ";
    public String successUnClaimed2 = " \u0431\u043B\u043E\u043A\u043E\u0432";
    public String selectionEmpty = "\u0420\u0435\u0433\u0438\u043E\u043D \u043D\u0435 \u0432\u044B\u0431\u0440\u0430\u043D";
    public HashMap<String, String> joinMessages = new HashMap<>();
    public HashMap<String, String> leaveMessages = new HashMap<>();

    public Title parseActionBar(Text text) {
        return Title
                .builder()
                .actionBar(text)
                .build();
    }

    public Title parseActionBar(TextColor color, Text text) {
        return Title
                .builder()
                .actionBar(Text.of(color, text))
//                .actionBar(Text.builder().color(color).append(text).build())
                .build();
    }

    public Text insertSuccess(int i) {
        return Text.of(
                Text.builder().color(TextColors.GREEN).append(Text.of(insertSuccessStart)),
                Text.builder().color(TextColors.GOLD).append(Text.of(i + "$")),
                Text.builder().color(TextColors.GREEN).append(Text.of(insertSuccessStop))
        );
    }

    public Text withdrawSuccess(int i) {
        return Text.of(
                Text.builder().color(TextColors.GREEN).append(Text.of(withdrawSuccessStart)),
                Text.builder().color(TextColors.GOLD).append(Text.of(i + "$"))
        );
    }

    public Text moneySuccess(int i) {
        return Text.of(
                Text.builder().color(TextColors.GREEN).append(Text.of(moneySuccess)),
                Text.builder().color(TextColors.GOLD).append(Text.of(i + "$"))
        );
    }

    public Text moneySuccessSee(String player, int i) {
        return Text.of(
                Text.builder().color(TextColors.GREEN).append(Text.of(moneySuccessSee)),
                Text.builder().color(TextColors.GOLD).append(Text.of(player)),
                Text.builder().color(TextColors.GREEN).append(Text.of(" - ")),
                Text.builder().color(TextColors.GOLD).append(Text.of(i + "$"))
        );
    }

    public Text payError(int i) {
        return Text.of(
                Text.builder().color(TextColors.RED).append(Text.of(payError)),
                Text.builder().color(TextColors.GOLD).append(Text.of(i + "$"))
        );
    }

    public Text paySuccess(CommandSource src, Player consumer, int i) {
        return Text.of(
                Text.builder().color(TextColors.WHITE)
                        .append(Text.of("["))
                        .append(Text.of(src.getName()))
                        .append(Text.of(" -> "))
                        .append(Text.of(consumer.getName()))
                        .append(Text.of("] ")).build(),

                Text.builder().color(TextColors.GOLD).append(Text.of(i + "$"))
        );
    }

    public Text giveMoneySuccess(int i) {
        return Text.of(
                Text.builder().color(TextColors.GREEN).append(Text.of(giveMoneySuccess)),
                Text.builder().color(TextColors.GOLD).append(Text.of(i + "$"))
        );
    }

    public Text giveMoneySuccessSrc(String src, int i) {
        return Text.of(
                Text.builder().color(TextColors.GREEN).append(Text.of(giveMoneySuccessSrcStart)),
                Text.builder().color(TextColors.GOLD).append(Text.of(src)),
                Text.builder().color(TextColors.GREEN).append(Text.of(giveMoneySuccessSrcStop)),
                Text.builder().color(TextColors.GOLD).append(Text.of(i + "$"))
        );
    }

    public Text setBalanceSuccess(int i) {
        return Text.of(
                Text.builder().color(TextColors.GREEN).append(Text.of(setBalanceSuccess)),
                Text.builder().color(TextColors.GOLD).append(Text.of(i + "$"))
        );
    }

    public Text setBalanceSuccessSrc(String src, int i) {
        return Text.of(
                Text.builder().color(TextColors.GREEN).append(Text.of(setBalanceSuccessSrcStart)),
                Text.builder().color(TextColors.GOLD).append(Text.of(src)),
                Text.builder().color(TextColors.GREEN).append(Text.of(setBalanceSuccessSrcStop)),
                Text.builder().color(TextColors.GOLD).append(Text.of(i + "$"))
        );
    }

    public Text playerName(String name) {
        return Text.of(
                Text.builder().color(TextColors.WHITE).append(Text.of(playerNameStart)),
                Text.builder().color(TextColors.GOLD).append(Text.of(name)),
                Text.builder().color(TextColors.WHITE).append(Text.of(playerNameStop))
        );
    }

    public Text startPosition(Region2d region2d, Position2d position2d) {
        Text.Builder builder = Text.builder();
        builder
                .append(Text.builder().color(TextColors.WHITE).append(Text.of(startPosition)).build())
                .append(Text.builder().color(TextColors.GOLD).append(Text.of(position2d.x + " " + position2d.z)).build());

        int size = region2d.size();
        if (size != 0)
            builder
                    .append(Text.builder().color(TextColors.WHITE).append(Text.of(" - ")).build())
                    .append(Text.builder().color(TextColors.GOLD).append(Text.of(region2d.size())).build());

        return builder.build();
    }

    public Text stopPosition(Region2d region2d, Position2d position2d) {
        Text.Builder builder = Text.builder();
        builder
                .append(Text.builder().color(TextColors.WHITE).append(Text.of(stopPosition)).build())
                .append(Text.builder().color(TextColors.GOLD).append(Text.of(position2d.x + " " + position2d.z)).build());

        int size = region2d.size();
        if (size != 0)
            builder
                    .append(Text.builder().color(TextColors.WHITE).append(Text.of(" - ")).build())
                    .append(Text.builder().color(TextColors.GOLD).append(Text.of(region2d.size())).build());

        return builder.build();
    }

    public Title notEnoughRightsTitle() {
        return parseActionBar(notEnoughRights());
    }

    public Text notEnoughRights() {
        return Text
                .builder()
                .color(TextColors.RED)
                .append(Text.of(notEnoughRights))
                .build();
    }

    public Text createCountrySuccess(String name) {
        return Text.of(
                Text.builder().color(TextColors.GREEN).append(Text.of(createCountrySuccess)).build(),
                Text.builder().color(TextColors.GOLD).append(Text.of(name)).build()
        );
    }

    public Text countryCreateNotEnoughMoney() {
        return Text.of(
                Text.builder().color(TextColors.RED).append(Text.of(countryCreateNotEnoughMoneyStart)).build(),
                Text.builder().color(TextColors.GOLD).append(Text.of("4000$")).build(),
                Text.builder().color(TextColors.RED).append(Text.of(countryCreateNotEnoughMoneyStop)).build()
        );
    }

    public Text createCountryAlreadyExist(String name) {
        return Text.of(
                Text.builder().color(TextColors.RED).append(Text.of(createCountryAlreadyExistStart)).build(),
                Text.builder().color(TextColors.GOLD).append(Text.of(name)).build(),
                Text.builder().color(TextColors.RED).append(Text.of(createCountryAlreadyExistStop)).build()
        );
    }

    public Text citizenLeft(String player, String name) {
        return Text.of(
                Text.builder().color(TextColors.GOLD).append(Text.of(player)).build(),
                Text.builder().color(TextColors.WHITE).append(Text.of(citizenLeft)).build(),
                Text.builder().color(TextColors.GOLD).append(Text.of(name)).build()
        );
    }

    public Text citizenJoin(String player, String name) {
        return Text.of(
                Text.builder().color(TextColors.GOLD).append(Text.of(player)).build(),
                Text.builder().color(TextColors.WHITE).append(Text.of(citizenJoin)).build(),
                Text.builder().color(TextColors.GOLD).append(Text.of(name)).build()
        );
    }

    public Text addMinister(String player, String name) {
        return Text.of(
                Text.builder().color(TextColors.GOLD).append(Text.of(player)).build(),
                Text.builder().color(TextColors.WHITE).append(Text.of(ministerAdd)).build(),
                Text.builder().color(TextColors.GOLD).append(Text.of(name)).build()
        );
    }

    public Text removeMinister(String player, String name) {
        return Text.of(
                Text.builder().color(TextColors.GOLD).append(Text.of(player)).build(),
                Text.builder().color(TextColors.WHITE).append(Text.of(ministerRemove)).build(),
                Text.builder().color(TextColors.GOLD).append(Text.of(name)).build()
        );
    }

    public Text banCitizen(String player, String name) {
        return Text.of(
                Text.builder().color(TextColors.GOLD).append(Text.of(player)).build(),
                Text.builder().color(TextColors.WHITE).append(Text.of(banCitizen)).build(),
                Text.builder().color(TextColors.GOLD).append(Text.of(name)).build()
        );
    }

    public Text unbanCitizen(String player, String name) {
        return Text.of(
                Text.builder().color(TextColors.GOLD).append(Text.of(player)).build(),
                Text.builder().color(TextColors.WHITE).append(Text.of(unbanCitizen)).build(),
                Text.builder().color(TextColors.GOLD).append(Text.of(name)).build()
        );
    }

    public Text restrictCitizen(String player, String name) {
        return Text.of(
                Text.builder().color(TextColors.GOLD).append(Text.of(player)).build(),
                Text.builder().color(TextColors.WHITE).append(Text.of(restrictCitizen)).build(),
                Text.builder().color(TextColors.GOLD).append(Text.of(name)).build()
        );
    }

    public Text unRestrictCitizen(String player, String name) {
        return Text.of(
                Text.builder().color(TextColors.WHITE).append(Text.of(unRestrictCitizenStart)).build(),
                Text.builder().color(TextColors.GOLD).append(Text.of(player)).build(),
                Text.builder().color(TextColors.WHITE).append(Text.of(unRestrictCitizenStop)).build(),
                Text.builder().color(TextColors.GOLD).append(Text.of(name)).build()
        );
    }

    public Text countryRequestSent(String countryName) {
        return Text.of(
                Text.builder().color(TextColors.GREEN).append(Text.of(countryRequestSent)).build(),
                Text.builder().color(TextColors.GOLD).append(Text.of(countryName)).build()
        );
    }

    public Text countryRequestReceive(String name) {
        return Text.of(
                Text.builder().color(TextColors.WHITE).append(Text.of(countryRequestReceive)).build(),
                Text.builder().color(TextColors.GOLD).append(Text.of(name)).build(),
                Text.NEW_LINE,
                Text.builder().color(TextColors.GREEN).append(Text.of(countryReceiveAccept)).onClick(TextActions.runCommand("/country citizens invite " + name)).build(),
                Text.builder().color(TextColors.WHITE).append(Text.of(" / ")).build(),
                Text.builder().color(TextColors.RED).append(Text.of(countryReceiveReject)).onClick(TextActions.runCommand("/country citizens reject " + name)).build()
        );
    }

    public Text countryCitizenRejectMinister(Player player, String name) {
        return Text.of(
                Text.builder().color(TextColors.WHITE).append(Text.of(countryCitizenRejectMinisterStart)).build(),
                Text.builder().color(TextColors.GOLD).append(Text.of(player)).build(),
                Text.builder().color(TextColors.WHITE).append(Text.of(countryCitizenRejectMinisterStop)).build(),
                Text.builder().color(TextColors.GOLD).append(Text.of(name)).build()
        );
    }

    public Text countryCitizenReject(String name) {
        return Text.of(
                Text.builder().color(TextColors.RED).append(Text.of(countryCitizenRejectStart)).build(),
                Text.builder().color(TextColors.GOLD).append(Text.of(name)).build(),
                Text.builder().color(TextColors.RED).append(Text.of(countryCitizenRejectStop)).build()
        );
    }

    public Text countryInviteSent(String player) {
        return Text.of(
                Text.builder().color(TextColors.GREEN).append(Text.of(countryInviteSent)).build(),
                Text.builder().color(TextColors.GOLD).append(Text.of(player)).build()
        );
    }

    public Text countryInviteReceive(String name) {
        return Text.of(
                Text.builder().color(TextColors.WHITE).append(Text.of(countryInviteReceive)).build(),
                Text.builder().color(TextColors.GOLD).append(Text.of(name)).build(),
                Text.NEW_LINE,
                Text.builder().color(TextColors.GREEN).append(Text.of(countryReceiveAccept)).onClick(TextActions.runCommand("/country join " + name)).build(),
                Text.builder().color(TextColors.WHITE).append(Text.of(" / ")).build(),
                Text.builder().color(TextColors.RED).append(Text.of(countryReceiveReject)).onClick(TextActions.runCommand("/country reject " + name)).build()
        );
    }

    public Text countryReject(String country) {
        return Text.of(
                Text.builder().color(TextColors.GREEN).append(Text.of(countryReject)).build(),
                Text.builder().color(TextColors.GOLD).append(Text.of(country)).build()
        );
    }

    public Text countryRejectMinister(String player) {
        return Text.of(
                Text.builder().color(TextColors.GOLD).append(Text.of(player)).build(),
                Text.builder().color(TextColors.RED).append(Text.of(countryRejectMinisterStart)).build()
        );
    }

    public Text onlyLeader() {
        return Text.builder().color(TextColors.RED).append(Text.of(onlyLeader)).build();
    }

    public Text onlyMinisters() {
        return Text.builder().color(TextColors.RED).append(Text.of(onlyMinisters)).build();
    }

    public Text countryRemoved(String name) {
        return Text.of(
                Text.builder().color(TextColors.RED).append(Text.of(countryRemovedStart)).build(),
                Text.builder().color(TextColors.GOLD).append(Text.of(name)).build(),
                Text.builder().color(TextColors.RED).append(Text.of(countryRemovedStop)).build()
        );
    }

    public Text countryNotSelected() {
        return Text.builder().color(TextColors.RED).append(Text.of(countryNotSelected)).build();
    }

    public Text listMinisters(String name, List<String> ministers) {
        Text startText = Text.builder().color(TextColors.WHITE).append(Text.of(listMinisters)).build();
        Text nameText = Text.builder().color(TextColors.GOLD).append(Text.of(name)).build();
        Text endText = Text.builder().color(TextColors.WHITE).append(Text.of(": ")).build();
        Text ministerTexts = Text.joinWith(
                Text.builder().color(TextColors.WHITE).append(Text.of(", ")).build(),
                ministers.stream().map(
                        s -> Text.builder().color(TextColors.GOLD).append(Text.of(s)).build()
                ).collect(Collectors.toList())
        );

        return Text.of(startText, nameText, endText, ministerTexts);
    }

    public Text notEnoughMoney() {
        return Text.of(TextColors.RED, notEnoughMoney);
    }

    public Text tempNotWork() {
        return Text.of(TextColors.RED + tempNotWork);
    }

    public Text broadcast(String name, String message) {
        return Text.of(
                TextColors.GOLD, "[", name, "] ",
                TextColors.GREEN, TextSerializers.FORMATTING_CODE.deserialize(message)
        );
    }

    public Text alreadyCitizenJoin() {
        return Text.of("You are citizen of another nation");
    }

    public Text alreadyCitizenInvite() {
        return Text.of("He's are citizen of another nation");
    }

    public Text nonMinister(String player) {
        return Text.of(
                Text.builder().color(TextColors.GOLD).append(Text.of(player)).build(),
                Text.builder().color(TextColors.RED).append(Text.of(nonMinister)).build()
        );
    }

    public Text newLeader(String leader, String player) {
        return Text.of(
                TextColors.GOLD, leader,
                TextColors.WHITE, newLeader,
                TextColors.GOLD, player
        );
    }

    public Text paperMoney() {
        return Text.of(
                TextColors.WHITE, banknote,
                TextColors.GOLD, paperMoney
        );
    }

    public Text coinMoney() {
        return Text.of(
                TextColors.WHITE, coin,
                TextColors.GOLD, coinMoney
        );
    }

    public List<Text> moneyLore() {
        return Collections.singletonList(Text.of(
                TextColors.WHITE, moneyLore,
                TextColors.GOLD, moneyLoreCommand
        ));
    }

    public Text countryInfo(Country country, String executor) {
        if (country == null)
            return countryNotSelected();
        Text countryName = Text.of(TextColors.WHITE, this.countryName, TextColors.GOLD, country.name);
        Text leader = Text.of(TextColors.WHITE, this.countryLeader, TextColors.GOLD, country.leader);
        Text size = Text.of(TextColors.WHITE, this.countrySize, TextColors.GOLD, country.regions.size());

        ArrayList<Text> result = new ArrayList<>(Arrays.asList(countryName, leader, size));

        if (country.isCitizen(executor)) {
            for (CountryGroup group : country.groups.groups) {
                result.add(Text.of(
                        TextColors.WHITE, "Группа ",
                        group.name,
                        TextColors.WHITE, ": ",
                        Text.joinWith(
                                Text.of(TextColors.WHITE, ", "),
                                country.groups.playersByGroup(group.name).stream().map(s -> Text.of(TextColors.GOLD, s)).collect(Collectors.toList())
                        )
                ));
            }
        }

        if (country.isMinister(executor)) {
            Text balance = Text.of(TextColors.WHITE, this.countryBalance, TextColors.GOLD, country.balance);
            result.add(balance);
            Text freeBlocks = Text.of(TextColors.WHITE, this.countryFreeBlocks, TextColors.GOLD, country.freeBlocks);
            result.add(freeBlocks);
        }

        return Text.joinWith(Text.NEW_LINE, result);
    }

    public Text textOfRegion(Region2d regions, TextColor textColor, TextColor mainColor) {
        Position2d start = regions.getStart();
        Position2d stop = regions.getStop();
        return Text.of(
                textColor, "(",
                mainColor, start.x, " ", start.z,
                textColor, " -> ",
                mainColor, stop.x, " ", stop.z,
                textColor, ") (",
                mainColor, regions.size(),
                textColor, ")"
        );
    }

    public Text notEnoughBlocks(int i) {
        return Text.of(
                TextColors.RED, notEnoughBlocksStart,
                TextColors.GOLD, i,
                TextColors.RED, notEnoughBlockStop,
                TextColors.GOLD, notEnoughCommandsStop
        );
    }

    public Text haveIntersections() {
        return Text.of(TextColors.RED, haveIntersections);
    }

    public Text successClaimed(int i) {
        return Text.of(
                TextColors.GREEN, successClaimed1,
                TextColors.GOLD, i,
                TextColors.GREEN, successClaimed2
        );
    }

    public Text successBuyBlocks(int amount, int cost) {
        return Text.of(
                TextColors.GREEN, successBuyBlocksStart,
                TextColors.GOLD, amount,
                TextColors.GREEN, successBuyBlocksStop,
                TextColors.GOLD, cost, "$"
        );
    }

    public Text nothingChanged() {
        return Text.of(TextColors.RED, nothingChanged);
    }

    public Text successUnclaimed(int removed) {
        return Text.of(
                TextColors.GREEN, successUnClaimed1,
                TextColors.GOLD, removed,
                TextColors.GREEN, successUnClaimed2
        );
    }

    public Text selectionEmpty() {
        return Text.of(TextColors.RED, selectionEmpty);
    }

    public Text clickToMsg(String playerName) {
        return Text.of("\u041D\u0430\u0436\u043C\u0438\u0442\u0435 \u0434\u043B\u044F ", TextColors.GOLD, "/msg ", playerName);
    }

    public Text declaringWar(String sender, String consumer, long end) {
        Date date = new Date(end);
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return Text.of(
                TextColors.RED, "\u041D\u0430\u0446\u0438\u044F ",
                TextColors.GOLD, sender,
                TextColors.RED, " \u043D\u0430\u0447\u0438\u043D\u0430\u0435\u0442 \u043F\u043E\u0434\u0433\u043E\u0442\u043E\u0432\u043A\u0443 \u043A \u0432\u043E\u0439\u043D\u0435 \u0441 ",
                TextColors.GOLD, consumer,
                TextColors.RED, "\u0421\u0440\u043E\u043A \u043F\u043E\u0434\u0433\u043E\u0442\u043E\u0432\u043A\u0438 \u043E\u043A\u0430\u043D\u0447\u0438\u0432\u0430\u0435\u0442\u0441\u044F \u0432 ",
                TextColors.GOLD, instance.get(Calendar.HOUR_OF_DAY) + ":" + instance.get(Calendar.MINUTE)
        );
    }

    public Text declaredWar(String senderName, String consumerName) {
        return Text.of(
                TextColors.RED, "\u041E\u0431\u044A\u044F\u0432\u043B\u0435\u043D\u0438\u0435 \u0432\u043E\u0439\u043D\u044B ",
                TextColors.GOLD, senderName,
                TextColors.RED, " \u043A \u043D\u0430\u0446\u0438\u0438 ",
                TextColors.GOLD, consumerName,
                TextColors.RED, " \u043F\u043E\u0434\u0442\u0432\u0435\u0440\u0436\u0434\u0435\u043D\u043E."
        );
    }

    public Text alreadyDeclared() {
        return Text.of(TextColors.RED, "\u0417\u0430\u044F\u0432\u043B\u0435\u043D\u0438\u0435 \u043E \u0432\u043E\u0439\u043D\u0435 \u043A \u044D\u0442\u043E\u0439 \u043D\u0430\u0446\u0438\u0438 \u0443\u0436\u0435 \u043F\u043E\u0434\u0430\u043D\u043E");
    }

    public Text capitulated(Country who, Country country) {
        return Text.of(
                TextColors.RED, "\u041D\u0430\u0446\u0438\u044F ",
                TextColors.GOLD, who,
                TextColors.RED, " \u043A\u0430\u043F\u0438\u0442\u0443\u043B\u0438\u0440\u043E\u0432\u0430\u043B\u0430. \u0422\u0435\u043F\u0435\u0440\u044C \u0432\u0441\u0435 \u0432\u043B\u0430\u0434\u0435\u043D\u0438\u044F \u0438 \u0436\u0438\u0442\u0435\u043B\u0438 \u043F\u0440\u0438\u043D\u0430\u0434\u043B\u0435\u0436\u0430\u0442 ",
                TextColors.GOLD, country
        );
    }

    public Text localPrefix() {
        return Text.builder().append(
                        Text.of(
                                TextColors.WHITE, "[",
                                TextColors.GREEN, "L",
                                TextColors.WHITE, "] "
                        )
                ).onHover(TextActions.showText(Text.of("\u041B\u043E\u043A\u0430\u043B\u044C\u043D\u044B\u0439 \u0447\u0430\u0442")))
                .build();
    }

    public Text globalPrefix() {
        return Text.builder().append(
                        Text.of(
                                TextColors.WHITE, "[",
                                TextColors.AQUA, "G",
                                TextColors.WHITE, "] "
                        )
                ).onHover(TextActions.showText(Text.of("\u0413\u043B\u043E\u0431\u0430\u043B\u044C\u043D\u044B\u0439 \u0447\u0430\u0442")))
                .build();
    }

    public Text countryPrefix() {
        return Text.builder().append(
                        Text.of(
                                TextColors.WHITE, "[",
                                TextColors.YELLOW, "C",
                                TextColors.WHITE, "] "
                        )
                ).onHover(TextActions.showText(Text.of("\u0427\u0430\u0442 \u043D\u0430\u0446\u0438\u0438")))
                .build();
    }

    public Text spy() {
        return Text.builder().append(
                        Text.of(
                                TextColors.WHITE, "[",
                                TextColors.YELLOW, "SPY L",
                                TextColors.WHITE, "] "
                        )
                ).onHover(TextActions.showText(Text.of("\u041C\u043E\u0434\u0435\u0440")))
                .build();
    }

    public Text mutePlayer(CommandSource admin, Player player) {
        return Text.of(
                TextColors.GOLD, player.getName(),
                TextColors.RED, " \u0431\u044B\u043B \u0437\u0430\u0433\u043B\u0443\u0448\u0435\u043D ",
                TextColors.GOLD, admin
        );
    }

    public Text tempMutePlayer(CommandSource admin, Player player, LocalDateTime date) {
        return Text.of(
                mutePlayer(admin, player),
                TextColors.RED, " \u0434\u043E ",
                TextColors.GOLD, date.format(DateTimeFormatter.ofPattern("yy.MM.dd"))
        );
    }

    public Text muted() {
        return Text.of(TextColors.RED, "\u0412\u044B \u043D\u0435 \u043C\u043E\u0436\u0435\u0442\u0435 \u043E\u0442\u043F\u0440\u0430\u0432\u043B\u044F\u0442\u044C \u0441\u043E\u043E\u0431\u0449\u0435\u043D\u0438\u044F");
    }

    public Text plotNotFound(String name) {
        return Text.of(
                TextColors.RED, "\u041F\u043B\u043E\u0442 ",
                TextColors.GOLD, name,
                TextColors.RED, " \u043D\u0435 \u043D\u0430\u0439\u0434\u0451\u043D"
        );
    }

    public Text plotDefaultNotAllowed() {
        return Text.of(TextColors.RED, "\u041F\u043B\u043E\u0442 \u043F\u043E \u0443\u043C\u043E\u043B\u0447\u0430\u043D\u0438\u044E \u043D\u0435 \u0434\u043E\u0441\u0442\u0443\u043F\u0435\u043D \u0434\u043B\u044F \u044D\u0442\u043E\u0433\u043E \u0434\u0435\u0439\u0441\u0442\u0432\u0438\u044F");
    }

    public Text plotDelete(String name) {
        return Text.of(
                TextColors.RED, "\u041F\u043B\u043E\u0442 ",
                TextColors.GOLD, name,
                TextColors.RED, " \u0443\u0434\u0430\u043B\u0451\u043D"
        );
    }

    public Text plotGroup(String name) {
        return Text.of(
                TextColors.WHITE, "\u0413\u0440\u0443\u043F\u043F\u0430 \u0434\u043E\u0441\u0442\u0443\u043F\u0430 \u043A \u043F\u043B\u043E\u0442\u0443 - ",
                TextColors.GOLD, name
        );
    }

    public Text plotGroupSet(String group) {
        return Text.of(
                TextColors.GREEN, "\u041D\u043E\u0432\u0430\u044F \u0433\u0440\u0443\u043F\u043F\u0430 \u0434\u043E\u0441\u0442\u0443\u043F\u0430 - ",
                TextColors.GOLD, group,
                TextColors.GREEN, " \u0443\u0441\u043F\u0435\u0448\u043D\u043E \u0443\u0441\u0442\u0430\u043D\u043E\u0432\u043B\u0435\u043D\u0430"
        );
    }

    public Text regionNotSelected() {
        return Text.of(
                TextColors.RED, "\u0420\u0435\u0433\u0438\u043E\u043D \u043D\u0435 \u0432\u044B\u0431\u0440\u0430\u043D"
        );
    }

    public Text plotSuccessMove() {
        return Text.of(
                TextColors.GREEN, "\u041F\u043B\u043E\u0442 \u0443\u0441\u043F\u0435\u0448\u043D\u043E \u043F\u0435\u0440\u0435\u0434\u0432\u0438\u043D\u0443\u0442"
        );
    }

    public Text plotAlreadyExist() {
        return Text.of(
                TextColors.RED, "\u041F\u043B\u043E\u0442 \u0441 \u0442\u0430\u043A\u0438\u043C ID \u0443\u0436\u0435 \u0441\u0443\u0449\u0435\u0441\u0442\u0432\u0443\u0435\u0442"
        );
    }

    public Text plotAddPlayer(String name) {
        return Text.of(
                TextColors.GREEN, "\u0418\u0433\u0440\u043E\u043A ",
                TextColors.GOLD, name,
                TextColors.GREEN, " \u0434\u043E\u0431\u0430\u0432\u043B\u0435\u043D \u0432 \u0441\u043F\u0438\u0441\u043E\u043A \u0440\u0430\u0437\u0440\u0435\u0448\u0451\u043D\u043D\u044B\u0445"
        );
    }

    public Text plotPlayersList(List<String> players) {
        return Text.of(
                TextColors.WHITE, "\u0421\u043F\u0438\u0441\u043E\u043A \u0438\u0433\u0440\u043E\u043A\u043E\u0432 \u043F\u043B\u043E\u0442\u0430: ",
                Text.joinWith(
                        Text.of(TextColors.WHITE, ", "),
                        players.stream().map(s -> Text.of(TextColors.GOLD, s)).collect(Collectors.toList())
                )
        );
    }

    public Text plotPlayersRemove(String name) {
        return Text.of(
                TextColors.GREEN, "\u0427\u043B\u0435\u043D \u043F\u043B\u043E\u0442\u0430 ",
                TextColors.GOLD, name,
                TextColors.GREEN, " \u0443\u0434\u0430\u043B\u0451\u043D \u0441 \u043F\u0440\u0430\u0432 \u0434\u043E\u0441\u0442\u0443\u043F\u0430"
        );
    }

    public Text plotNew(String name) {
        return Text.of(
                TextColors.GREEN, "\u041F\u043B\u043E\u0442 ",
                TextColors.GOLD, name,
                TextColors.GREEN, " \u0443\u0441\u043F\u0435\u0448\u043D\u043E \u0441\u043E\u0437\u0434\u0430\u043D"
        );
    }

    public Text plotNewName(String plotId, String plotName) {
        return Text.of(
                TextColors.GREEN, "\u041D\u043E\u0432\u043E\u0435 \u0438\u043C\u044F \u0434\u043B\u044F \u043F\u043B\u043E\u0442\u0430 ",
                TextColors.GOLD, plotId,
                TextColors.GREEN, " \u0443\u0441\u0442\u0430\u043D\u043E\u0432\u043B\u0435\u043D\u043E \u043D\u0430 ",
                TextColors.GOLD, plotName
        );
    }
}
