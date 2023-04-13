package main.java.view;

import main.java.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.zip.DeflaterInputStream;

import static main.java.model.GameCategory.*;

public class searchBar extends JPanel {
    private JLabel searchLabel;
    private JCheckBox abstractStrategyCheckBox;
    private JCheckBox ancientCheckBox;
    private JCheckBox bluffingCheckBox1;
    private JCheckBox comicBookStripCheckBox;
    private JCheckBox economicCheckBox;
    private JCheckBox environmentalCheckBox1;
    private JCheckBox fantasyCheckBox;
    private JCheckBox horrorCheckBox;
    private JTextField textField1;
    private JCheckBox adventureCheckBox;
    private JCheckBox animalsCheckBox;
    private JCheckBox cardGameCheckBox;

    private JCheckBox cityBuildingCheckBox;
    private JCheckBox civilizationCheckBox;
    private JCheckBox deductionCheckBox;
    private JCheckBox educationalCheckBox;
    private JCheckBox expansionForBaseGameCheckBox;
    private JCheckBox farmingCheckBox;
    private JCheckBox humorCheckBox;
    private JCheckBox mazeCheckBox;
    private JCheckBox ageOfReasonCheckBox1;
    private JCheckBox arabianCheckBox;
    private JCheckBox childrenSGameCheckBox1;
    private JCheckBox collectibleComponentsCheckBox1;
    private JCheckBox diceCheckBox;
    private JCheckBox electronicCheckBox;
    private JCheckBox explorationCheckBox;
    private JCheckBox fightingCheckBox;
    private JCheckBox industryManufacturingCheckBox;
    private JCheckBox medicalCheckBox;
    private JCheckBox mafiaCheckBox;
    private JCheckBox medievalCheckBox;
    private JCheckBox moviesTVRadioThemeCheckBox;
    private JCheckBox napoleonicCheckBox;
    private JCheckBox novelBasedCheckBox;
    private JCheckBox politicalCheckBox;
    private JCheckBox printAndPlayCheckBox;
    private JCheckBox renaissanceCheckBox;
    private JCheckBox spiesSecretAgentsCheckBox1;
    private JCheckBox transportationCheckBox;
    private JCheckBox triviaCheckBox1;
    private JCheckBox memoryCheckBox;
    private JCheckBox videoGameThemeCheckBox;
    private JCheckBox travelCheckBox;
    private JCheckBox territoryBuildingCheckBox1;
    private JCheckBox scienceFictionCheckBox;
    private JCheckBox puzzleCheckBox;
    private JCheckBox postNapoleonicCheckBox;
    private JCheckBox partyGameCheckBox;
    private JCheckBox nauticalCheckBox;
    private JCheckBox murderMysteryCheckBox1;
    private JCheckBox miniaturesCheckBox;
    private JCheckBox mythologyCheckBox;
    private JCheckBox negotiationCheckBox;
    private JCheckBox pikeAndShotCheckBox;
    private JCheckBox spaceExplorationCheckBox;
    private JCheckBox racingCheckBox;
    private JCheckBox prehistoricCheckBox;
    private JCheckBox wordGameCheckBox;
    private JCheckBox sportsCheckBox1;
    private final JCheckBox worldWarIICheckBox1;
    private JCheckBox wargameCheckBox;
    private JLabel filterLabel;

    public searchBar() {
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(300,800));

        filterLabel = new JLabel("Filter Games by Category: ");

        abstractStrategyCheckBox = new JCheckBox("Abstract Strategy");
        abstractStrategyCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(ABSTRACT_STRATEGY.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(ABSTRACT_STRATEGY.toString());
                }
            }
        });

        adventureCheckBox = new JCheckBox("Adventure");
        adventureCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(ADVENTURE.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(ADVENTURE.toString());
                }
            }
        });

        ageOfReasonCheckBox1 = new JCheckBox("Age of Reason");
        ageOfReasonCheckBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(AGE_OF_REASON.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(AGE_OF_REASON.toString());
                }
            }
        });

        ancientCheckBox = new JCheckBox("Ancient");
        ancientCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(ANCIENT.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(ANCIENT.toString());
                }
            }
        });

        animalsCheckBox = new JCheckBox("Animals");
        animalsCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(ANIMALS.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(ANIMALS.toString());
                }
            }
        });

        arabianCheckBox = new JCheckBox("Arabian");
        arabianCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(ARABIAN.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(ARABIAN.toString());
                }
            }
        });


        bluffingCheckBox1 = new JCheckBox("Bluffing");
        bluffingCheckBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(BLUFFING.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(BLUFFING.toString());
                }
            }
        });

        cardGameCheckBox = new JCheckBox("Card Game");
        cardGameCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(CARD_GAME.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(CARD_GAME.toString());
                }
            }
        });

        childrenSGameCheckBox1 = new JCheckBox("Children's Game");
        childrenSGameCheckBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(CHILDRENS_GAME.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(CHILDRENS_GAME.toString());
                }
            }
        });

        cityBuildingCheckBox = new JCheckBox("City Building");
        cityBuildingCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(CITY_BUILDING.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(CITY_BUILDING.toString());
                }
            }
        });

        civilizationCheckBox = new JCheckBox("Civilization");
        civilizationCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(CIVILIZATION.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(CIVILIZATION.toString());
                }
            }
        });

        collectibleComponentsCheckBox1 = new JCheckBox("Collectible Components");
        collectibleComponentsCheckBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(COLLECTIBLE_COMPONENTS.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(COLLECTIBLE_COMPONENTS.toString());
                }
            }
        });

        comicBookStripCheckBox = new JCheckBox("Comic Book Strip");
        comicBookStripCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(COMIC_BOOK_STRIP.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(COMIC_BOOK_STRIP.toString());
                }
            }
        });

        deductionCheckBox = new JCheckBox("Deduction");
        deductionCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(DEDUCTION.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(DEDUCTION.toString());
                }
            }
        });

        diceCheckBox = new JCheckBox("Dice");
        diceCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(DICE.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(DICE.toString());
                }
            }
        });

        economicCheckBox = new JCheckBox("Economic");
        economicCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(ECONOMIC.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(ECONOMIC.toString());
                }
            }
        });

        educationalCheckBox = new JCheckBox("Educational");
        educationalCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(EDUCATIONAL.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(EDUCATIONAL.toString());
                }
            }
        });

        electronicCheckBox = new JCheckBox("Electronic");
        electronicCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(ELECTRONIC.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(ELECTRONIC.toString());
                }
            }
        });

        environmentalCheckBox1 = new JCheckBox("Environmental");
        environmentalCheckBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(ENVIRONMENTAL.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(ENVIRONMENTAL.toString());
                }
            }
        });

        expansionForBaseGameCheckBox = new JCheckBox("Expansion for Base Game");
        expansionForBaseGameCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(EXPANSION_FOR_BASE_GAME.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(EXPANSION_FOR_BASE_GAME.toString());
                }
            }
        });

        explorationCheckBox = new JCheckBox("Exploration");
        explorationCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(EXPLORATION.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(EXPLORATION.toString());
                }
            }
        });

        fantasyCheckBox = new JCheckBox("Fantasy");
        fantasyCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(FANTASY.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(FANTASY.toString());
                }
            }
        });

        farmingCheckBox = new JCheckBox("Farming");
        farmingCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(FARMING.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(FARMING.toString());
                }
            }
        });

        fightingCheckBox = new JCheckBox("Fighting");
        fightingCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(FIGHTING.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(FIGHTING.toString());
                }
            }
        });

        horrorCheckBox = new JCheckBox("Horror");
        horrorCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(HORROR.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(HORROR.toString());
                }
            }
        });

        humorCheckBox = new JCheckBox("Humor");
        humorCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(HUMOR.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(HUMOR.toString());
                }
            }
        });

        industryManufacturingCheckBox = new JCheckBox("Industry/Manufacturing");
        industryManufacturingCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(INDUSTRY_MANUFACTURING.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(INDUSTRY_MANUFACTURING.toString());
                }
            }
        });

        mafiaCheckBox = new JCheckBox("Mafia");
        mafiaCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(MAFIA.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(MAFIA.toString());
                }
            }
        });

        mazeCheckBox = new JCheckBox("Maze");
        mazeCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(MAZE.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(MAZE.toString());
                }
            }
        });

        medicalCheckBox = new JCheckBox("Medical");

        medicalCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(MEDICAL.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(MEDICAL.toString());
                }
            }
        });



        medievalCheckBox = new JCheckBox("Medieval");
        medievalCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(MEDIEVAL.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(MEDIEVAL.toString());
                }
            }
        });

        memoryCheckBox = new JCheckBox("Memory");
        memoryCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(MEMORY.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(MEMORY.toString());
                }
            }
        });

        miniaturesCheckBox = new JCheckBox("Miniatures");
        miniaturesCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(MINIATURES.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(MINIATURES.toString());
                }
            }
        });

        moviesTVRadioThemeCheckBox = new JCheckBox("Movies/TV/Radio Theme");
        moviesTVRadioThemeCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(MOVIES_TV_RADIO_THEME.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(MOVIES_TV_RADIO_THEME.toString());
                }
            }
        });

        murderMysteryCheckBox1 = new JCheckBox("Murder/Mystery");
        murderMysteryCheckBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(MURDER_MYSTERY.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(MURDER_MYSTERY.toString());
                }
            }
        });

        mythologyCheckBox = new JCheckBox("Mythology");
        mythologyCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(MYTHOLOGY.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(MYTHOLOGY.toString());
                }
            }
        });

        napoleonicCheckBox = new JCheckBox("Napoleonic");
        napoleonicCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(NAPOLEONIC.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(NAPOLEONIC.toString());
                }
            }
        });

        nauticalCheckBox = new JCheckBox("Nautical");
        nauticalCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(NAUTICAL.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(NAUTICAL.toString());
                }
            }
        });

        negotiationCheckBox = new JCheckBox("Negotiation");
        negotiationCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(NEGOTIATION.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(NEGOTIATION.toString());
                }
            }
        });

        novelBasedCheckBox = new JCheckBox("Novel Based");
        novelBasedCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(NOVEL_BASED.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(NOVEL_BASED.toString());
                }
            }
        });

        partyGameCheckBox = new JCheckBox("Party Game");
        partyGameCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(PARTY_GAME.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(PARTY_GAME.toString());
                }
            }
        });

        pikeAndShotCheckBox = new JCheckBox("Pike and Shot");
        pikeAndShotCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(PIKE_AND_SHOT.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(PIKE_AND_SHOT.toString());
                }
            }
        });

        politicalCheckBox = new JCheckBox("Political");
        politicalCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(POLITICAL.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(POLITICAL.toString());
                }
            }
        });

        postNapoleonicCheckBox = new JCheckBox("Post-Napoleonic");
        postNapoleonicCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(POST_NAPOLEONIC.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(POST_NAPOLEONIC.toString());
                }
            }
        });

        prehistoricCheckBox = new JCheckBox("Prehistoric");
        prehistoricCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(PREHISTORIC.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(PREHISTORIC.toString());
                }
            }
        });

        printAndPlayCheckBox = new JCheckBox("Print and Play");
        printAndPlayCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(PRINT_AND_PLAY.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(PRINT_AND_PLAY.toString());
                }
            }
        });

        puzzleCheckBox = new JCheckBox("Puzzle");
        puzzleCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(PUZZLE.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(PUZZLE.toString());
                }
            }
        });

        racingCheckBox = new JCheckBox("Racing");
        racingCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(RACING.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(RACING.toString());
                }
            }
        });

        renaissanceCheckBox = new JCheckBox("Renaissance");
        renaissanceCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(RENAISSANCE.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(RENAISSANCE.toString());
                }
            }
        });

        scienceFictionCheckBox = new JCheckBox("Science Fiction");
        scienceFictionCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(SCIENCE_FICTION.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(SCIENCE_FICTION.toString());
                }
            }
        });

        spaceExplorationCheckBox = new JCheckBox("Space Exploration");
        spaceExplorationCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(SPACE_EXPLORATION.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(SPACE_EXPLORATION.toString());
                }
            }
        });

        spiesSecretAgentsCheckBox1 = new JCheckBox("Spies/Secret Agents");
        spiesSecretAgentsCheckBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(SPIES_SECRET_AGENTS.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(SPIES_SECRET_AGENTS.toString());
                }
            }
        });

        sportsCheckBox1 = new JCheckBox("Sports");
        sportsCheckBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(SPORTS.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(SPORTS.toString());
                }
            }
        });

        territoryBuildingCheckBox1 = new JCheckBox("Territory Building");
        territoryBuildingCheckBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(TERRITORY_BUILDING.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(TERRITORY_BUILDING.toString());
                }
            }
        });

        transportationCheckBox = new JCheckBox("Transportation");
        transportationCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(TRANSPORTATION.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(TRANSPORTATION.toString());
                }
            }
        });

        travelCheckBox = new JCheckBox("Travel");
        travelCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(TRAVEL.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(TRAVEL.toString());
                }
            }
        });

        triviaCheckBox1 = new JCheckBox("Trivia");
        triviaCheckBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(TRIVIA.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(TRIVIA.toString());
                }
            }
        });

        videoGameThemeCheckBox = new JCheckBox("Video Game Theme");
        videoGameThemeCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(VIDEO_GAME_THEME.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(VIDEO_GAME_THEME.toString());
                }
            }
        });

        wargameCheckBox = new JCheckBox("Wargame");
        wargameCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(WARGAME.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(WARGAME.toString());
                }
            }
        });

        wordGameCheckBox = new JCheckBox("Word Game");
        wordGameCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(WORD_GAME.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(WORD_GAME.toString());
                }
            }
        });

        worldWarIICheckBox1 = new JCheckBox("World War II");
        worldWarIICheckBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                if (abstractButton.getModel().isSelected()) {
                    Controller.getInstance().addCategoryFilter(WORLD_WAR_II.toString());
                }
                if (!abstractButton.getModel().isSelected()) {
                    Controller.getInstance().removeCategoryFilter(WORLD_WAR_II.toString());
                }
            }
        });





        add(filterLabel);
        add(abstractStrategyCheckBox);
        add(adventureCheckBox);
        add(ageOfReasonCheckBox1);
        add(animalsCheckBox);
        add(arabianCheckBox);
        add(bluffingCheckBox1);
        add(cardGameCheckBox);
        add(childrenSGameCheckBox1);
        add(cityBuildingCheckBox);
        add(civilizationCheckBox);
        add(collectibleComponentsCheckBox1);
        add(comicBookStripCheckBox);
        add(deductionCheckBox);
        add(diceCheckBox);
        add(economicCheckBox);
        add(educationalCheckBox);
        add(electronicCheckBox);
        add(environmentalCheckBox1);
        add(expansionForBaseGameCheckBox);
        add(explorationCheckBox);
        add(fantasyCheckBox);
        add(farmingCheckBox);
        add(fightingCheckBox);
        add(horrorCheckBox);
        add(humorCheckBox);
        add(industryManufacturingCheckBox);
        add(mafiaCheckBox);
        add(mazeCheckBox);
        add(medicalCheckBox);
        add(medievalCheckBox);
        add(memoryCheckBox);
        add(miniaturesCheckBox);
        add(moviesTVRadioThemeCheckBox);
        add(murderMysteryCheckBox1);
        add(mythologyCheckBox);
        add(napoleonicCheckBox);
        add(nauticalCheckBox);
        add(negotiationCheckBox);
        add(novelBasedCheckBox);
        add(partyGameCheckBox);
        add(pikeAndShotCheckBox);
        add(politicalCheckBox);
        add(postNapoleonicCheckBox);
        add(prehistoricCheckBox);
        add(printAndPlayCheckBox);
        add(puzzleCheckBox);
        add(racingCheckBox);
        add(renaissanceCheckBox);
        add(scienceFictionCheckBox);
        add(spaceExplorationCheckBox);
        add(spiesSecretAgentsCheckBox1);
        add(sportsCheckBox1);
        add(territoryBuildingCheckBox1);
        add(transportationCheckBox);
        add(travelCheckBox);
        add(triviaCheckBox1);
        add(videoGameThemeCheckBox);
        add(wargameCheckBox);
        add(wordGameCheckBox);
        add(worldWarIICheckBox1);

    }
}

