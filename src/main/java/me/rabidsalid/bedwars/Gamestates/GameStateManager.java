package me.rabidsalid.bedwars.Gamestates;

public class GameStateManager {
    private Gamestate currentGameState;
    public GameStateManager() {
        currentGameState = Gamestate.LOBBY;
    }

    public Gamestate getGameState() {
        return currentGameState;
    }

    public void setGameState(Gamestate state) {
        currentGameState = state;
    }
}
