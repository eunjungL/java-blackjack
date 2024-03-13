package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.dto.Profits;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Players {

    private static final String NAME_DUPLICATED_EXCEPTION = "플레이어의 이름은 중복될 수 없습니다.";

    private final List<Player> players;

    private Players(final List<Player> players) {
        this.players = players;
    }

    public static Players of(final List<String> names, final List<String> battings, final Dealer dealer) {
        validate(names);

        List<Player> players = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            players.add(new Player(names.get(i), dealer, battings.get(i)));
        }

        return new Players(players);
    }

    private static void validate(final List<String> names) {
        if (isDuplicated(names)) {
            throw new IllegalArgumentException(NAME_DUPLICATED_EXCEPTION);
        }
    }

    private static boolean isDuplicated(final List<String> names) {
        return names.size() != Set.copyOf(names).size();
    }

    public Profits createResult(final Dealer dealer) {
        Profits profits = new Profits();

        for (Player player : players) {
            Judge.judge(player, dealer, profits);
        }

        return profits;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

//    public Map<Player, Integer> calculateProfits(final PlayerResult playerResult) {
//        Map<Player, Integer> profitResult = new HashMap<>();
//
//        for (Player player : players) {
//            GameResult gameResult = playerResult.findByName(player.getName());
//            profitResult.put(player, player.calculateProfit(gameResult));
//        }
//
//        return profitResult;
//    }

//    public int sumProfits(final PlayerResult playerResult) {
//        return calculateProfits(playerResult).values().stream()
//                .mapToInt(i -> i)
//                .sum();
//    }
}
