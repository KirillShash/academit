package ru.academit.shashkov.minesweeper.model;

import lombok.Getter;
import ru.academit.shashkov.minesweeper.common.*;
import ru.academit.shashkov.minesweeper.storage.HighScoresStorage;

import java.util.*;
import java.util.function.Predicate;

public class GameManager implements Model {
    private static final int MILLIS_IN_SECOND = 1000;

    private final HighScoresManager highScoresManager;
    private MinesweeperView minesweeperView;
    private Minefield minefield;
    private Random randomCoordinatesGenerator;

    private int openedCellsToWinNumber;
    private int openedCellsNumber;
    private long timeSpentInSeconds;

    private DifficultyMode difficultyMode;

    @Getter
    private GameState gameState;
    @Getter
    private boolean isFirstStep;

    public GameManager(HighScoresStorage highScoresStorage) {
        highScoresManager = new HighScoresManager(highScoresStorage);
    }

    @Override
    public void setMinesweeperView(MinesweeperView minesweeperView) {
        this.minesweeperView = minesweeperView;
    }


    @Override
    public void initMinesweeperView() {
        minesweeperView.init(minefield.getRowsNumber(), minefield.getColumnsNumber());
        minefield.setRemainingBombsNumber(minefield.getMinesNumber());
    }

    @Override
    public void prepareGame() {
        this.difficultyMode = StandardDifficultyModes.getDefault().getDifficultyMode();
        randomCoordinatesGenerator = new Random();
        prepareGameData(difficultyMode.getRowsNumber(), difficultyMode.getColumnsNumber(), difficultyMode.getMinesNumber());
    }

    private void prepareGameData(int rowsNumber, int columnsNumber, int minesNumber) {
        openedCellsToWinNumber = (rowsNumber * columnsNumber) - minesNumber;
        openedCellsNumber = 0;

        minefield = new Minefield(rowsNumber, columnsNumber, minesNumber, minesweeperView);

        isFirstStep = true;

        initPlayingField();
    }

    private void initPlayingField() {
        minefield.init();
    }

    @Override
    public void setFlag(int x, int y) {
        minefield.setFlag(minefield.getCell(x, y));
    }

    @Override
    public void openCell(int x, int y) {
        if (isFirstStep()) {
            startGame(x, y);
        }
        Cell currentCell = minefield.getCell(x, y);

        if (getGameState() == GameState.IS_ON && currentCell.getCellState() == CellState.CLOSED) {
            if (currentCell.getCellContent() == CellContent.MINE) {
                minesweeperView.showCellContent(currentCell.getX(), currentCell.getY(), currentCell.getCellContent());
                stopGameAfterDefeat(currentCell);
            } else {
                openCellAndNeighbours(currentCell);

                if (isGameWon()) {
                    stopGameAfterVictory();
                }
            }
        }
    }

    @Override
    public void startGame(int x, int y) {
        isFirstStep = false;
        Cell firstStepCell = minefield.getCell(x, y);
        locateMines(firstStepCell);
        gameState = GameState.IS_ON;
        minesweeperView.startGame();
    }

    private void locateMines(Cell firstStepCell) {
        int counter = minefield.getMinesNumber();
        List<Cell> cellsListCopy = minefield.getCellsListCopy();
        cellsListCopy.remove(firstStepCell);
        while (counter > 0) {
            int index = randomCoordinatesGenerator.nextInt(cellsListCopy.size());
            Cell currentCell = cellsListCopy.remove(index);

            currentCell.setCellContent(CellContent.MINE);
            minefield.addMine(currentCell);
            counter--;
        }
    }

    private boolean isGameWon() {
        return openedCellsNumber == openedCellsToWinNumber;
    }

    private void openCellAndNeighbours(Cell clickedSafeCell) {
        Set<Cell> visitedCells = new HashSet<>();
        Deque<Cell> deque = new LinkedList<>();
        deque.addLast(clickedSafeCell);

        while (!deque.isEmpty()) {
            Cell currentCell = deque.removeLast();

            if (visitedCells.add(currentCell) &&
                    currentCell.getCellState() == CellState.CLOSED &&
                    currentCell.getCellContent() != CellContent.MINE) {
                currentCell.setCellState(CellState.OPENED);
                minesweeperView.showCellContent(currentCell.getX(), currentCell.getY(), currentCell.getCellContent());
                ++openedCellsNumber;
                tryAddNeighboursToDeque(deque, currentCell);
            }
        }
    }

    private void tryAddNeighboursToDeque(Deque<Cell> deque, Cell currentCell) {
        if (currentCell.getCellContent() == CellContent.EMPTY) {
            List<Cell> neighbours = minefield.getCellNeighbours(currentCell);
            neighbours.stream()
                    .filter(Predicate.not(deque::contains))
                    .forEach(deque::addLast);
        }
    }

    private void stopGameAfterVictory() {
        gameState = GameState.VICTORY;
        minefield.markAllMinesAfterVictory(minefield.getMinedCells());
        stopTimerAndBlockCells();

        if (!difficultyMode.isCustomMode() && highScoresManager.isHighScore(timeSpentInSeconds, difficultyMode)) {
            highScoresManager.setCurrentBestTime(timeSpentInSeconds);
            minesweeperView.notifyPlayerAboutRecord();
        }
    }

    private void stopTimerAndBlockCells() {
        minesweeperView.finishGame(isGameWon());
    }

    private void stopGameAfterDefeat(Cell detonatedCell) {
        gameState = GameState.WASTED;
        minefield.processGameStop(detonatedCell);
        stopTimerAndBlockCells();
    }

    @Override
    public void openNotFlaggedNeighbours(int x, int y) {
        if (getGameState() == GameState.IS_ON) {
            Cell currentCell = minefield.getCell(x, y);
            if (currentCell.getCellState() != CellState.OPENED || currentCell.getCellContent() == CellContent.EMPTY) {
                return;
            }

            List<Cell> neighbours = minefield.getCellNeighbours(currentCell);
            int flaggedNeighboursNumber = (int) neighbours.stream()
                    .filter(neighbour -> CellState.FLAGGED == neighbour.getCellState())
                    .count();

            int minesAroundCurrentCellNumber = currentCell.getMinesAroundNumber();
            if (minesAroundCurrentCellNumber == flaggedNeighboursNumber) {
                neighbours.forEach(currentNeighbour -> openCell(currentNeighbour.getX(), currentNeighbour.getY()));
            }
        }
    }

    private void prepareGameAfterRestart(int rowsNumber, int columnsNumber, int minesNumber) {
        prepareGameData(rowsNumber, columnsNumber, minesNumber);
        minefield.setRemainingBombsNumber(minesNumber);
    }

    @Override
    public void restartGame() {
        prepareGameAfterRestart(minefield.getRowsNumber(), minefield.getColumnsNumber(), minefield.getMinesNumber());
        minesweeperView.restartGame();
    }

    @Override
    public void restartGameWithCustomSettings(int rowsNumber, int columnsNumber, int minesNumber) {
        int validateRowsNumber = CustomSettingsUtils.fitRowsNumber(rowsNumber);
        int validateColumnsNumber = CustomSettingsUtils.fitColumnsNumber(columnsNumber);
        int validateMinesNumber = CustomSettingsUtils.fitMinesNumber(validateRowsNumber, validateColumnsNumber,
                minesNumber);

        difficultyMode = new DifficultyMode(validateRowsNumber, validateColumnsNumber, validateMinesNumber,
                true, DifficultyMode.CUSTOM_MODE_NAME);

        prepareGameAfterRestart(validateRowsNumber, validateColumnsNumber, validateMinesNumber);

        minesweeperView.hideCustomSettingsInput();
        minesweeperView.restartGameWithNewDifficulty(validateRowsNumber, validateColumnsNumber);
    }

    @Override
    public void restartGameWithNewDifficulty(StandardDifficultyModes newDifficultyMode) {
        difficultyMode = newDifficultyMode.getDifficultyMode();
        prepareGameAfterRestart(difficultyMode.getRowsNumber(), difficultyMode.getColumnsNumber(),
                difficultyMode.getMinesNumber());
        minesweeperView.restartGameWithNewDifficulty(difficultyMode.getRowsNumber(), difficultyMode.getColumnsNumber());
    }

    @Override
    public Map<String, Score> getHighScores() {
        return highScoresManager.getHighScores();
    }

    @Override
    public void writeNewHighScore(String name) {
        highScoresManager.createNewHighScore(name, difficultyMode);
        minesweeperView.hideHighScoreNotification();
    }

    @Override
    public void resetHighScores() {
        highScoresManager.resetHighScores();
        minesweeperView.showHighScores(highScoresManager.getHighScores());
    }

    @Override
    public void setTime(long timeSpent) {
        this.timeSpentInSeconds = timeSpent / MILLIS_IN_SECOND;
        minesweeperView.modifyTimer(timeSpent);
    }

    @Override
    public DifficultyMode getCurrentDifficultyMode() {
        return difficultyMode;
    }

    @Override
    public void exitGame() {
        highScoresManager.saveBeforeExit();
        minesweeperView.exitGame();
    }
}
