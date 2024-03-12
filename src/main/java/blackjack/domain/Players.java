package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.dto.BlackjackResult;
import blackjack.dto.DealerResult;
import blackjack.dto.PlayerResult;
import java.util.List;
import java.util.Set;

public class Players {

    private static final String NAME_DUPLICATED_EXCEPTION = "플레이어의 이름은 중복될 수 없습니다.";

    private final List<Player> players;

    private Players(final List<Player> players) {
        this.players = players;
    }

    public static Players of(final List<String> names, final Dealer dealer) {
        validate(names);

        List<Player> players = names.stream()
                .map(name -> new Player(name, dealer))
                .toList();

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

    public BlackjackResult createResult(final Dealer dealer) {
        ResultStatus resultStatus = ResultStatus.init();
        PlayerResult playerResult = new PlayerResult();

        for (Player player : players) {
            DealerResult result = Judge.judge(resultStatus, player, dealer, playerResult);
            resultStatus.updateResultStatus(result);
        }

        return new BlackjackResult(DealerResult.of(resultStatus), playerResult);
    }

    public List<Player> getPlayers() {
        return players;
    }
}
