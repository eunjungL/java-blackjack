package blackjack;

import blackjack.domain.Deck;
import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.strategy.RandomShuffleStrategy;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.RankView;
import blackjack.view.SuitView;
import java.util.List;

public class BlackjackGame {

    private static final String DEALER_NAME = "딜러";

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackGame() {
        this.inputView = InputView.getInstance();
        this.outputView = OutputView.getInstance();
    }

    public void start() {
        Deck deck = new Deck(new RandomShuffleStrategy());

        List<String> names = inputView.readPlayersName();
        Players players = Players.of(names, deck);
        Dealer dealer = new Dealer(DEALER_NAME, deck);

        printCardDistribute(names, players, dealer);

        players.getPlayers()
                .forEach(player -> readMoreCardChoice(player, deck));

        printAddDealerCard(dealer, deck);
        printResultCardsStatus(dealer, players);

        outputView.printFinalResult(names, players.createResult(dealer));
    }

    private void printCardDistribute(final List<String> names, final Players players, final Dealer dealer) {
        outputView.printCardDistribute(dealer.getName(), names);
        printHandCardsStatus(dealer, players);
    }

    private String makeCardOutput(final Card card) {
        return RankView.toSymbol(card.getRank()) + SuitView.toSuitView(card.getSuit());
    }

    private List<String> makeCardOutput(final List<Card> cards) {
        return cards.stream()
                .map(this::makeCardOutput)
                .toList();
    }

    private void printHandCardsStatus(final Dealer dealer, final Players players) {
        outputView.printHandCards(dealer.getName(), makeCardOutput(dealer.showFirstCard()));

        for (Player player : players.getPlayers()) {
            outputView.printHandCards(player.getName(), makeCardOutput(player.getHandCards()));
        }
        outputView.printNewLine();
    }

    private void readMoreCardChoice(final Player player, final Deck deck) {
        String choice = inputView.readMoreCardChoice(player.getName());

        if (!outputView.isMoreChoice(choice)) {
            outputView.printHandCards(player.getName(), makeCardOutput(player.getHandCards()));
            return;
        }

        do {
            player.draw(deck);
            outputView.printHandCards(player.getName(), makeCardOutput(player.getHandCards()));
        } while ((player.canReceiveCard()) && outputView.isMoreChoice(inputView.readMoreCardChoice(player.getName())));
    }

    private void printAddDealerCard(final Dealer dealer, final Deck deck) {
        if (dealer.canReceiveCard()) {
            dealer.draw(deck);
            outputView.printAddDealerCard(dealer.getName());
            return;
        }
        outputView.printNewLine();
    }

    private void printResultCardsStatus(final Dealer dealer, final Players players) {
        outputView.printCardResultStatus(dealer.getName(), makeCardOutput(dealer.getHandCards()), dealer.getScore());

        for (Player player : players.getPlayers()) {
            outputView.printCardResultStatus(
                    player.getName(),
                    makeCardOutput(player.getHandCards()),
                    player.getScore()
            );
        }
    }

}
