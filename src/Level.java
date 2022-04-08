public class Level {
    private final int NUMBER_OF_CORRIDOR = 3;
    private final int NUMBER_OF_ROOM_IN_A_CORRIDOR = 4;
    private int randomNum = 0;
    private static int levelNum = 0;
    private Room[][] simpleLevel = new Room[NUMBER_OF_CORRIDOR][NUMBER_OF_ROOM_IN_A_CORRIDOR];

    public Level() {
        generateLevelAndRoomsWithDoors();
        levelNum++;
    }

    public void generateLevelAndRoomsWithDoors() {
        for (int c = 0; c < NUMBER_OF_CORRIDOR; c++) {
            for (int d = 0; d < NUMBER_OF_ROOM_IN_A_CORRIDOR; d++) {
                simpleLevel[c][d] = new Room();
                simpleLevel[c][d].setSpecificRoomNum(Room.getRoomNum());
                int numToMonster = (c + 1) * (d + 1) * (levelNum + 1);
                int numToMonsterHP = (c + 1) * (d + 1) * (levelNum + 1);
                simpleLevel[c][d].addMonster(numToMonster, numToMonsterHP);
                simpleLevel[c][d].addTownsPeople((int) Math.round(numToMonster / (double) 3));
            }
        }
        for (int i = 0; i < NUMBER_OF_CORRIDOR; i++) {
            randomNum = (int) (Math.random() * 4);
            if (i < NUMBER_OF_CORRIDOR - 1) {
                simpleLevel[i][randomNum].getDoor()[2] = simpleLevel[i + 1][randomNum];
                simpleLevel[i + 1][randomNum].getDoor()[0] = simpleLevel[i][randomNum];
            }
            for (int j = 0; j < NUMBER_OF_ROOM_IN_A_CORRIDOR; j++) {
                if (j == 0) {
                    simpleLevel[i][j].getDoor()[1] = simpleLevel[i][j + 1];
                } else if (j == 3) {
                    simpleLevel[i][j].getDoor()[3] = simpleLevel[i][j - 1];
                } else {
                    simpleLevel[i][j].getDoor()[1] = simpleLevel[i][j + 1];
                    simpleLevel[i][j].getDoor()[3] = simpleLevel[i][j - 1];
                }
            }
        }
    }

    public Room[][] getSimpleLevel() {
        return simpleLevel;
    }

    public static int getLevelNum() {
        return levelNum;

    }

    public boolean aliveMonster() {
        for (int c = 0; c < NUMBER_OF_CORRIDOR; c++) {
            for (int d = 0; d < NUMBER_OF_ROOM_IN_A_CORRIDOR; d++) {
                if (simpleLevel[c][d].getMonsterList().size() != 0)
                    return true;
            }
        }
        return false;
    }

    public void killAllMonster() {
        for (int c = 0; c < NUMBER_OF_CORRIDOR; c++) {
            for (int d = 0; d < NUMBER_OF_ROOM_IN_A_CORRIDOR; d++) {
                simpleLevel[c][d].getMonsterList().clear();
            }
        }

    }
}
