package javamon.frontend.dungeon;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import javamon.backend.Javamon;
import javamon.backend.entity.BattleArena;
import javamon.backend.entity.Monster;
import javamon.backend.entity.items.Item;
import javamon.backend.entity.places.Dungeon;
import javamon.frontend.HomeGUI;
import javamon.frontend.Panel;
import javamon.frontend.components.Button;
import javamon.frontend.components.Column;
import javamon.frontend.components.Label;
import javamon.frontend.components.Row;
import javamon.frontend.components.SizedBox;
import javamon.frontend.styles.Colors;

public class BattleArenaPanel extends Panel {
    public BattleArenaPanel(HomeGUI homeGUI, BattleArena battleArena) {
        super(homeGUI);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Monster playerMonster = battleArena.getPlayerMonster();
        Monster wildMonster = battleArena.getWildMonster();

        Label battleLabel = new Label("Battle Arena", "jua", 32f, 8, Colors.WELCOME,
                Color.WHITE);

        Label playerMonsterHpLabel = new Label(
                String.format("%s HP: %.0f/%.0f", playerMonster.getName(), playerMonster.getCurrHp(),
                        playerMonster.getMaxHp()),
                "jua", 24f, 8, Colors.WELCOME, Color.WHITE);
        Label wildMonsterHpLabel = new Label(
                String.format("%s HP: %.0f/%.0f", wildMonster.getName(), wildMonster.getCurrHp(),
                        wildMonster.getMaxHp()),
                "jua", 24f, 8, Colors.WELCOME, Color.WHITE);

        Row monstersHpRow = new Row();
        monstersHpRow.add(Box.createHorizontalGlue());
        monstersHpRow.add(playerMonsterHpLabel);
        monstersHpRow.add(SizedBox.width(256));
        monstersHpRow.add(wildMonsterHpLabel);
        monstersHpRow.add(Box.createHorizontalGlue());

        ImageIcon playerMonsterIcon = new ImageIcon(
                String.format("assets/images/pokemon/%s-Transparant.png", playerMonster.getName()));
        ImageIcon wildMonsterIcon = new ImageIcon(
                String.format("assets/images/pokemon/%s-Transparant.png", wildMonster.getName()));
        int newWidth = 256;
        int newHeight = 256;
        Image scaledPlayerMonsterImage = playerMonsterIcon.getImage().getScaledInstance(newWidth, newHeight,
                Image.SCALE_SMOOTH);
        Image scaledWildMonsterImage = wildMonsterIcon.getImage().getScaledInstance(newWidth, newHeight,
                Image.SCALE_SMOOTH);
        ImageIcon resizedPlayerMonsterIcon = new ImageIcon(scaledPlayerMonsterImage);
        ImageIcon resizedWildMonsterIcon = new ImageIcon(scaledWildMonsterImage);

        JLabel playerMonsterLabel = new JLabel(resizedPlayerMonsterIcon);
        JLabel wildMonsterLabel = new JLabel(resizedWildMonsterIcon);

        Row monstersImageRow = new Row();
        monstersImageRow.add(Box.createHorizontalGlue());
        monstersImageRow.add(playerMonsterLabel);
        monstersImageRow.add(SizedBox.width(256));
        monstersImageRow.add(wildMonsterLabel);
        monstersImageRow.add(Box.createHorizontalGlue());

        Item[] items = Javamon.getPlayerItems();
        String[] itemsName = new String[items.length];
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null)
                itemsName[i] = items[i].getName();
        }

        JComboBox<String> itemsComboBox = new JComboBox<>(itemsName);
        itemsComboBox.setPreferredSize(new Dimension(200, 30));
        itemsComboBox.setMinimumSize(new Dimension(200, 30));
        itemsComboBox.setMaximumSize(new Dimension(200, 30));
        itemsComboBox.setSize(new Dimension(200, 30));

        Label turnLabel = new Label("It's your turn", "jua", 24f, 8, Colors.WELCOME, Color.WHITE);
        turnLabel.setForeground(Color.WHITE);

        Button attackButton = new Button("Attack", "Inter-Bold", 16f, Colors.WELCOME, Colors.TEXT,
                attack(battleArena, turnLabel, playerMonsterHpLabel, wildMonsterHpLabel));
        attackButton.setPreferredSize(new Dimension(200, 30));

        Button elementalAttackButton = new Button("Elemental Attack", "Inter-Bold", 16f, Colors.WELCOME, Colors.TEXT,
                elementalAttack(battleArena, turnLabel, playerMonsterHpLabel, wildMonsterHpLabel));
        elementalAttackButton.setPreferredSize(new Dimension(200, 30));

        Button specialAttackButton = new Button("Special Attack", "Inter-Bold", 16f, Colors.WELCOME, Colors.TEXT,
                specialAttack(battleArena, turnLabel, playerMonsterHpLabel, wildMonsterHpLabel));
        specialAttackButton.setPreferredSize(new Dimension(200, 30));

        Button runButton = new Button("Run", "Inter-Bold", 16f, Colors.WELCOME, Colors.TEXT,
                run(battleArena, turnLabel, playerMonsterHpLabel));
        runButton.setPreferredSize(new Dimension(200, 30));

        Button useItemButton = new Button("Use Item", "Inter-Bold", 16f, Colors.WELCOME, Colors.TEXT,
                useItem(battleArena, itemsComboBox, turnLabel, playerMonsterHpLabel));
        useItemButton.setPreferredSize(new Dimension(200, 30));

        Column playerMonsterColumn = new Column();
        Row firstRow = new Row();
        firstRow.add(attackButton);
        firstRow.add(Box.createHorizontalGlue());
        firstRow.add(elementalAttackButton);
        firstRow.add(Box.createHorizontalGlue());
        firstRow.add(itemsComboBox);
        Row secondRow = new Row();
        secondRow.add(specialAttackButton);
        secondRow.add(Box.createHorizontalGlue());
        secondRow.add(runButton);
        secondRow.add(Box.createHorizontalGlue());
        secondRow.add(useItemButton);

        playerMonsterColumn.add(firstRow);
        playerMonsterColumn.add(SizedBox.height(16));
        playerMonsterColumn.add(secondRow);
        playerMonsterColumn.add(SizedBox.height(64));

        add(battleLabel);
        add(Box.createVerticalGlue());
        add(monstersHpRow);
        add(monstersImageRow);
        add(SizedBox.height(8));
        add(turnLabel);
        add(Box.createVerticalGlue());
        add(playerMonsterColumn);
    }

    private ActionListener useItem(BattleArena battleArena, JComboBox<String> itemsComboBox, Label label,
            Label playerHpLabel) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String itemName = (String) itemsComboBox.getSelectedItem();
                Item[] items = Javamon.getPlayerItems();
                Item item = null;
                for (int i = 0; i < items.length; i++) {
                    if (items[i] != null && items[i].getName().equals(itemName)) {
                        item = items[i];
                        break;
                    }
                }
                itemsComboBox.removeItem(itemName);

                label.setText("You use item " + item.getName());
                playerHpLabel.setText(String.format("%s HP: %.0f/%.0f",
                        battleArena.getPlayerMonster().getName(),
                        battleArena.getPlayerMonster().getCurrHp(),
                        battleArena.getPlayerMonster().getMaxHp()));
                battleArena.useItem(item);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);

                            String action = battleArena.wildMonsterTurn();

                            Monster playerMonster = battleArena.getPlayerMonster();
                            Monster wildMonster = battleArena.getWildMonster();

                            if (playerMonster.getCurrHp() <= 0) {
                                playerMonster.setCurrHp(0);

                                ResultPanel resultPanel = new ResultPanel(homeGUI,
                                        String.format("You are defeated by %s... skill issue", wildMonster.getName()));
                                homeGUI.addPanel("result", resultPanel);
                                homeGUI.setPanel("result");
                                return;
                            }

                            String playerHp = String.format("%s HP: %.0f/%.0f",
                                    playerMonster.getName(),
                                    playerMonster.getCurrHp(),
                                    playerMonster.getMaxHp());
                            playerHpLabel.setText(playerHp);

                            label.setText(String.format("Wild monster attacks with %s", action));
                            label.setForeground(Color.RED);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        };
    }

    private ActionListener run(BattleArena battleArena, Label label, Label playerHpLabel) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean success = battleArena.escape();
                if (!success) {
                    label.setText("You fail to escape from battle");
                    label.setForeground(Color.WHITE);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(3000);

                                String action = battleArena.wildMonsterTurn();

                                Monster playerMonster = battleArena.getPlayerMonster();
                                Monster wildMonster = battleArena.getWildMonster();

                                if (playerMonster.getCurrHp() <= 0) {
                                    playerMonster.setCurrHp(0);

                                    ResultPanel resultPanel = new ResultPanel(homeGUI,
                                            String.format("You are defeated by %s... skill issue",
                                                    wildMonster.getName()));
                                    homeGUI.addPanel("result", resultPanel);
                                    homeGUI.setPanel("result");
                                    return;
                                }

                                String playerHp = String.format("%s HP: %.0f/%.0f",
                                        playerMonster.getName(),
                                        playerMonster.getCurrHp(),
                                        playerMonster.getMaxHp());
                                playerHpLabel.setText(playerHp);

                                label.setText(String.format("Wild monster attacks with %s", action));
                                label.setForeground(Color.RED);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    return;
                }

                ResultPanel resultPanel = new ResultPanel(homeGUI,
                        Javamon.getPLAYER().getName() + " escapes from battle... what a coward lol");
                homeGUI.addPanel("result", resultPanel);
                homeGUI.setPanel("result");
            }
        };

    }

    private ActionListener specialAttack(BattleArena battleArena, Label label, Label playerHpLabel, Label wildHpLabel) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean success = battleArena.specialAttack(battleArena.getPlayerMonster(),
                        battleArena.getWildMonster());

                if (!success) {
                    label.setText("You miss the special attack.");
                    label.setForeground(Color.WHITE);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(3000);

                                String action = battleArena.wildMonsterTurn();

                                Monster playerMonster = battleArena.getPlayerMonster();
                                Monster wildMonster = battleArena.getWildMonster();

                                if (playerMonster.getCurrHp() <= 0) {
                                    playerMonster.setCurrHp(0);
                                    ResultPanel resultPanel = new ResultPanel(homeGUI,
                                            String.format("You are defeated by %s... skill issue",
                                                    wildMonster.getName()));
                                    homeGUI.addPanel("result", resultPanel);
                                    homeGUI.setPanel("result");
                                    return;
                                }

                                String playerHp = String.format("%s HP: %.0f/%.0f",
                                        playerMonster.getName(),
                                        playerMonster.getCurrHp(),
                                        playerMonster.getMaxHp());
                                playerHpLabel.setText(playerHp);

                                label.setText(String.format("Wild monster attacks with %s", action));
                                label.setForeground(Color.RED);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    return;
                }

                label.setText("You attack with special attack.");
                label.setForeground(Color.WHITE);

                Monster wildMonster = battleArena.getWildMonster();
                String wildHp = String.format("%s HP: %.0f/%.0f", wildMonster.getName(),
                        wildMonster.getCurrHp(), wildMonster.getMaxHp());
                if (wildMonster.getCurrHp() <= 0) {
                    wildMonster.setCurrHp(0);

                    // Add gold to player
                    Monster playerMonster = battleArena.getPlayerMonster();
                    double gold = playerMonster.getGold();
                    double playerGold = Javamon.getPLAYER().getGold();
                    Javamon.getPLAYER().setGold(playerGold + gold);

                    // Add exp to monster
                    double exp = playerMonster.getExp();
                    double monsterExp = wildMonster.getExp();
                    playerMonster.setExp(exp + monsterExp);

                    Javamon.getPLAYER().addMonster(wildMonster);

                    ResultPanel resultPanel = new ResultPanel(homeGUI,
                            String.format("You defeated %s... lucky", wildMonster.getName()));
                    homeGUI.addPanel("result", resultPanel);
                    homeGUI.setPanel("result");
                    return;
                }
                wildHpLabel.setText(wildHp);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);

                            String action = battleArena.wildMonsterTurn();

                            Monster playerMonster = battleArena.getPlayerMonster();

                            if (playerMonster.getCurrHp() <= 0) {
                                playerMonster.setCurrHp(0);
                                ResultPanel resultPanel = new ResultPanel(homeGUI,
                                        String.format("You are defeated by %s... skill issue", wildMonster.getName()));
                                homeGUI.addPanel("result", resultPanel);
                                homeGUI.setPanel("result");
                                return;
                            }

                            String playerHp = String.format("%s HP: %.0f/%.0f",
                                    playerMonster.getName(),
                                    playerMonster.getCurrHp(),
                                    playerMonster.getMaxHp());
                            playerHpLabel.setText(playerHp);

                            label.setText(String.format("Wild monster attacks with %s", action));
                            label.setForeground(Color.RED);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }

        };
    }

    private ActionListener elementalAttack(BattleArena battleArena, Label label, Label playerHpLabel,
            Label wildHpLabel) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                battleArena.elementalAttack(battleArena.getPlayerMonster(), battleArena.getWildMonster());

                label.setText("You attack with elemental attack.");
                label.setForeground(Color.WHITE);

                Monster wildMonster = battleArena.getWildMonster();
                String wildHp = String.format("%s HP: %.0f/%.0f", wildMonster.getName(),
                        wildMonster.getCurrHp(), wildMonster.getMaxHp());
                if (wildMonster.getCurrHp() <= 0) {
                    wildMonster.setCurrHp(0);

                    // Add gold to player
                    Monster playerMonster = battleArena.getPlayerMonster();
                    double gold = playerMonster.getGold();
                    double playerGold = Javamon.getPLAYER().getGold();
                    Javamon.getPLAYER().setGold(playerGold + gold);

                    // Add exp to monster
                    double exp = playerMonster.getExp();
                    double monsterExp = wildMonster.getExp();
                    playerMonster.setExp(exp + monsterExp);

                    Javamon.getPLAYER().addMonster(wildMonster);

                    ResultPanel resultPanel = new ResultPanel(homeGUI,
                            String.format("You defeated %s... lucky", wildMonster.getName()));
                    homeGUI.addPanel("result", resultPanel);
                    homeGUI.setPanel("result");
                    return;
                }
                wildHpLabel.setText(wildHp);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);

                            String action = battleArena.wildMonsterTurn();

                            Monster playerMonster = battleArena.getPlayerMonster();

                            if (playerMonster.getCurrHp() <= 0) {
                                playerMonster.setCurrHp(0);
                                ResultPanel resultPanel = new ResultPanel(homeGUI,
                                        String.format("You are defeated by %s... skill issue", wildMonster.getName()));
                                homeGUI.addPanel("result", resultPanel);
                                homeGUI.setPanel("result");
                                return;
                            }

                            String playerHp = String.format("%s HP: %.0f/%.0f",
                                    playerMonster.getName(),
                                    playerMonster.getCurrHp(),
                                    playerMonster.getMaxHp());
                            playerHpLabel.setText(playerHp);

                            label.setText(String.format("Wild monster attacks with %s", action));
                            label.setForeground(Color.RED);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        };
    }

    private ActionListener attack(BattleArena battleArena, Label label, Label playerHpLabel, Label wildHpLabel) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                battleArena.basicAttack(battleArena.getPlayerMonster(), battleArena.getWildMonster());

                label.setText("You attack with basic attack.");
                label.setForeground(Color.WHITE);

                Monster wildMonster = battleArena.getWildMonster();
                String wildHp = String.format("%s HP: %.0f/%.0f", wildMonster.getName(),
                        wildMonster.getCurrHp(), wildMonster.getMaxHp());
                if (wildMonster.getCurrHp() <= 0) {
                    wildMonster.setCurrHp(0);
                    // Add gold to player
                    Monster playerMonster = battleArena.getPlayerMonster();
                    double gold = playerMonster.getGold();
                    double playerGold = Javamon.getPLAYER().getGold();
                    Javamon.getPLAYER().setGold(playerGold + gold);

                    // Add exp to monster
                    double exp = playerMonster.getExp();
                    double monsterExp = wildMonster.getExp();
                    playerMonster.setExp(exp + monsterExp);

                    Javamon.getPLAYER().addMonster(wildMonster);

                    ResultPanel resultPanel = new ResultPanel(homeGUI,
                            String.format("You defeated %s... lucky", wildMonster.getName()));
                    homeGUI.addPanel("result", resultPanel);
                    homeGUI.setPanel("result");
                    return;
                }
                wildHpLabel.setText(wildHp);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);

                            String action = battleArena.wildMonsterTurn();

                            Monster playerMonster = battleArena.getPlayerMonster();

                            if (playerMonster.getCurrHp() <= 0) {
                                playerMonster.setCurrHp(0);
                                ResultPanel resultPanel = new ResultPanel(homeGUI,
                                        String.format("You are defeated by %s... skill issue", wildMonster.getName()));
                                homeGUI.addPanel("result", resultPanel);
                                homeGUI.setPanel("result");
                                return;
                            }

                            String playerHp = String.format("%s HP: %.0f/%.0f",
                                    playerMonster.getName(),
                                    playerMonster.getCurrHp(),
                                    playerMonster.getMaxHp());
                            playerHpLabel.setText(playerHp);

                            label.setText(String.format("Wild monster attacks with %s", action));
                            label.setForeground(Color.RED);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        };
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        ImageIcon bgImage = new ImageIcon(
                String.format("assets/images/background/%s.jpg", ((Dungeon) Javamon.getPOSITION()).getName()));
        g.drawImage(bgImage.getImage(), 0, 0, getWidth(), getHeight(), null);
    }
}
