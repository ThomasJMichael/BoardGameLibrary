package main.java.view;

import main.java.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.zip.DeflaterInputStream;

import static main.java.model.GameCategory.*;

public class searchBar extends JPanel {
    private JButton submitButton;

    private homePageFrame frame;

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

    public searchBar(homePageFrame homeFrame) {
        frame = homeFrame;
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(300,800));

        filterLabel = new JLabel("Add a filter to your search:");

        abstractStrategyCheckBox = new JCheckBox("Abstract Strategy");
        ActionListener actionlistener = new filterActionListener(ABSTRACT_STRATEGY, abstractStrategyCheckBox);
        abstractStrategyCheckBox.addActionListener(actionlistener);

        adventureCheckBox = new JCheckBox("Adventure");
        actionlistener = new filterActionListener(ADVENTURE, adventureCheckBox);
        abstractStrategyCheckBox.addActionListener(actionlistener);

        ageOfReasonCheckBox1 = new JCheckBox("Age of Reason");
        actionlistener = new filterActionListener(AGE_OF_REASON, ageOfReasonCheckBox1);
        ageOfReasonCheckBox1.addActionListener(actionlistener);

        ancientCheckBox = new JCheckBox("Ancient");
        actionlistener = new filterActionListener(ANCIENT, ancientCheckBox);
        ancientCheckBox.addActionListener(actionlistener);

        animalsCheckBox = new JCheckBox("Animals");
        actionlistener = new filterActionListener(ANIMALS, animalsCheckBox);
        animalsCheckBox.addActionListener(actionlistener);

        arabianCheckBox = new JCheckBox("Arabian");
        actionlistener = new filterActionListener(ARABIAN, arabianCheckBox);
        arabianCheckBox.addActionListener(actionlistener);


        bluffingCheckBox1 = new JCheckBox("Bluffing");
        actionlistener = new filterActionListener(BLUFFING, bluffingCheckBox1);
        bluffingCheckBox1.addActionListener(actionlistener);

        cardGameCheckBox = new JCheckBox("Card Game");
        actionlistener = new filterActionListener(CARD_GAME, cardGameCheckBox);
        cardGameCheckBox.addActionListener(actionlistener);

        childrenSGameCheckBox1 = new JCheckBox("Children's Game");
        actionlistener = new filterActionListener(CHILDRENS_GAME, childrenSGameCheckBox1);
        childrenSGameCheckBox1.addActionListener(actionlistener);

        cityBuildingCheckBox = new JCheckBox("City Building");
        actionlistener = new filterActionListener(CITY_BUILDING, cityBuildingCheckBox);
        cityBuildingCheckBox.addActionListener(actionlistener);

        civilizationCheckBox = new JCheckBox("Civilization");
        actionlistener = new filterActionListener(CIVILIZATION, civilizationCheckBox);
        civilizationCheckBox.addActionListener(actionlistener);

        collectibleComponentsCheckBox1 = new JCheckBox("Collectible Components");
        actionlistener = new filterActionListener(COLLECTIBLE_COMPONENTS, collectibleComponentsCheckBox1);
        collectibleComponentsCheckBox1.addActionListener(actionlistener);

        comicBookStripCheckBox = new JCheckBox("Comic Book Strip");
        actionlistener = new filterActionListener(COMIC_BOOK_STRIP, comicBookStripCheckBox);
        comicBookStripCheckBox.addActionListener(actionlistener);

        deductionCheckBox = new JCheckBox("Deduction");
        actionlistener = new filterActionListener(DEDUCTION, deductionCheckBox);
        deductionCheckBox.addActionListener(actionlistener);

        diceCheckBox = new JCheckBox("Dice");
        actionlistener = new filterActionListener(DICE, diceCheckBox);
        diceCheckBox.addActionListener(actionlistener);

        economicCheckBox = new JCheckBox("Economic");
        actionlistener = new filterActionListener(ECONOMIC, economicCheckBox);
        economicCheckBox.addActionListener(actionlistener);

        educationalCheckBox = new JCheckBox("Educational");
        actionlistener = new filterActionListener(EDUCATIONAL, educationalCheckBox);
        educationalCheckBox.addActionListener(actionlistener);

        electronicCheckBox = new JCheckBox("Electronic");
        actionlistener = new filterActionListener(ELECTRONIC, electronicCheckBox);
        electronicCheckBox.addActionListener(actionlistener);

        environmentalCheckBox1 = new JCheckBox("Environmental");
        actionlistener = new filterActionListener(ENVIRONMENTAL, environmentalCheckBox1);
        environmentalCheckBox1.addActionListener(actionlistener);

        expansionForBaseGameCheckBox = new JCheckBox("Expansion for Base Game");
        actionlistener = new filterActionListener(EXPANSION_FOR_BASE_GAME, expansionForBaseGameCheckBox);
        expansionForBaseGameCheckBox.addActionListener(actionlistener);

        explorationCheckBox = new JCheckBox("Exploration");
        actionlistener = new filterActionListener(EXPLORATION, explorationCheckBox);
        explorationCheckBox.addActionListener(actionlistener);

        fantasyCheckBox = new JCheckBox("Fantasy");
        actionlistener = new filterActionListener(FANTASY, fantasyCheckBox);
        fantasyCheckBox.addActionListener(actionlistener);

        farmingCheckBox = new JCheckBox("Farming");
        actionlistener = new filterActionListener(FARMING, farmingCheckBox);
        fantasyCheckBox.addActionListener(actionlistener);

        fightingCheckBox = new JCheckBox("Fighting");
        actionlistener = new filterActionListener(FIGHTING, fightingCheckBox);
        fightingCheckBox.addActionListener(actionlistener);

        horrorCheckBox = new JCheckBox("Horror");
        actionlistener = new filterActionListener(HORROR, horrorCheckBox);
        horrorCheckBox.addActionListener(actionlistener);

        humorCheckBox = new JCheckBox("Humor");
        actionlistener = new filterActionListener(HUMOR, humorCheckBox);
        humorCheckBox.addActionListener(actionlistener);

        industryManufacturingCheckBox = new JCheckBox("Industry/Manufacturing");
        actionlistener = new filterActionListener(INDUSTRY_MANUFACTURING, industryManufacturingCheckBox);
        industryManufacturingCheckBox.addActionListener(actionlistener);

        mafiaCheckBox = new JCheckBox("Mafia");
        actionlistener = new filterActionListener(MAFIA, mafiaCheckBox);
        mafiaCheckBox.addActionListener(actionlistener);

        mazeCheckBox = new JCheckBox("Maze");
        actionlistener = new filterActionListener(MAZE, mazeCheckBox);
        mazeCheckBox.addActionListener(actionlistener);

        medicalCheckBox = new JCheckBox("Medical");
        actionlistener = new filterActionListener(MEDICAL, medicalCheckBox);
        medicalCheckBox.addActionListener(actionlistener);

        medievalCheckBox = new JCheckBox("Medieval");
        actionlistener = new filterActionListener(MEDIEVAL, medievalCheckBox);
        medievalCheckBox.addActionListener(actionlistener);

        memoryCheckBox = new JCheckBox("Memory");
        actionlistener = new filterActionListener(MEMORY, memoryCheckBox);
        memoryCheckBox.addActionListener(actionlistener);

        miniaturesCheckBox = new JCheckBox("Miniatures");
        actionlistener = new filterActionListener(MINIATURES, miniaturesCheckBox);
        miniaturesCheckBox.addActionListener(actionlistener);

        moviesTVRadioThemeCheckBox = new JCheckBox("Movies/TV/Radio Theme");
        actionlistener = new filterActionListener(MOVIES_TV_RADIO_THEME, moviesTVRadioThemeCheckBox);
        moviesTVRadioThemeCheckBox.addActionListener(actionlistener);

        murderMysteryCheckBox1 = new JCheckBox("Murder/Mystery");
        actionlistener = new filterActionListener(MURDER_MYSTERY, murderMysteryCheckBox1);
        murderMysteryCheckBox1.addActionListener(actionlistener);

        mythologyCheckBox = new JCheckBox("Mythology");
        actionlistener = new filterActionListener(MYTHOLOGY, mythologyCheckBox);
        mythologyCheckBox.addActionListener(actionlistener);

        napoleonicCheckBox = new JCheckBox("Napoleonic");
        actionlistener = new filterActionListener(NAPOLEONIC, napoleonicCheckBox);
        napoleonicCheckBox.addActionListener(actionlistener);

        nauticalCheckBox = new JCheckBox("Nautical");
        actionlistener = new filterActionListener(NAUTICAL, nauticalCheckBox);
        nauticalCheckBox.addActionListener(actionlistener);

        negotiationCheckBox = new JCheckBox("Negotiation");
        actionlistener = new filterActionListener(NEGOTIATION, negotiationCheckBox);
        negotiationCheckBox.addActionListener(actionlistener);

        novelBasedCheckBox = new JCheckBox("Novel Based");
        actionlistener = new filterActionListener(NOVEL_BASED, novelBasedCheckBox);
        novelBasedCheckBox.addActionListener(actionlistener);

        partyGameCheckBox = new JCheckBox("Party Game");
        actionlistener = new filterActionListener(PARTY_GAME, partyGameCheckBox);
        partyGameCheckBox.addActionListener(actionlistener);

        pikeAndShotCheckBox = new JCheckBox("Pike and Shot");
        actionlistener = new filterActionListener(PIKE_AND_SHOT, pikeAndShotCheckBox);
        pikeAndShotCheckBox.addActionListener(actionlistener);

        politicalCheckBox = new JCheckBox("Political");
        actionlistener = new filterActionListener(POLITICAL, politicalCheckBox);
        politicalCheckBox.addActionListener(actionlistener);

        postNapoleonicCheckBox = new JCheckBox("Post-Napoleonic");
        actionlistener = new filterActionListener(POST_NAPOLEONIC, postNapoleonicCheckBox);
        postNapoleonicCheckBox.addActionListener(actionlistener);

        prehistoricCheckBox = new JCheckBox("Prehistoric");
        actionlistener = new filterActionListener(PREHISTORIC, prehistoricCheckBox);
        prehistoricCheckBox.addActionListener(actionlistener);

        printAndPlayCheckBox = new JCheckBox("Print and Play");
        actionlistener = new filterActionListener(PRINT_AND_PLAY, printAndPlayCheckBox);
        printAndPlayCheckBox.addActionListener(actionlistener);

        puzzleCheckBox = new JCheckBox("Puzzle");
        actionlistener = new filterActionListener(PUZZLE, puzzleCheckBox);
        puzzleCheckBox.addActionListener(actionlistener);

        racingCheckBox = new JCheckBox("Racing");
        actionlistener = new filterActionListener(RACING, racingCheckBox);
        racingCheckBox.addActionListener(actionlistener);

        renaissanceCheckBox = new JCheckBox("Renaissance");
        actionlistener = new filterActionListener(RENAISSANCE, renaissanceCheckBox);
        renaissanceCheckBox.addActionListener(actionlistener);

        scienceFictionCheckBox = new JCheckBox("Science Fiction");
        actionlistener = new filterActionListener(SCIENCE_FICTION, scienceFictionCheckBox);
        scienceFictionCheckBox.addActionListener(actionlistener);

        spaceExplorationCheckBox = new JCheckBox("Space Exploration");
        actionlistener = new filterActionListener(SPACE_EXPLORATION, spaceExplorationCheckBox);
        spaceExplorationCheckBox.addActionListener(actionlistener);

        spiesSecretAgentsCheckBox1 = new JCheckBox("Spies/Secret Agents");
        actionlistener = new filterActionListener(SPIES_SECRET_AGENTS, spiesSecretAgentsCheckBox1);
        spiesSecretAgentsCheckBox1.addActionListener(actionlistener);

        sportsCheckBox1 = new JCheckBox("Sports");
        actionlistener = new filterActionListener(SPORTS, sportsCheckBox1);
        sportsCheckBox1.addActionListener(actionlistener);

        territoryBuildingCheckBox1 = new JCheckBox("Territory Building");
        actionlistener = new filterActionListener(TERRITORY_BUILDING, territoryBuildingCheckBox1);
        territoryBuildingCheckBox1.addActionListener(actionlistener);

        transportationCheckBox = new JCheckBox("Transportation");
        actionlistener = new filterActionListener(TRANSPORTATION, transportationCheckBox);
        transportationCheckBox.addActionListener(actionlistener);

        travelCheckBox = new JCheckBox("Travel");
        actionlistener = new filterActionListener(TRAVEL, travelCheckBox);
        travelCheckBox.addActionListener(actionlistener);

        triviaCheckBox1 = new JCheckBox("Trivia");
        actionlistener = new filterActionListener(TRIVIA, triviaCheckBox1);
        triviaCheckBox1.addActionListener(actionlistener);

        videoGameThemeCheckBox = new JCheckBox("Video Game Theme");
        actionlistener = new filterActionListener(VIDEO_GAME_THEME, videoGameThemeCheckBox);
        videoGameThemeCheckBox.addActionListener(actionlistener);

        wargameCheckBox = new JCheckBox("Wargame");
        actionlistener = new filterActionListener(WARGAME, wargameCheckBox);
        wargameCheckBox.addActionListener(actionlistener);

        wordGameCheckBox = new JCheckBox("Word Game");
        actionlistener = new filterActionListener(WORD_GAME, wordGameCheckBox);
        wordGameCheckBox.addActionListener(actionlistener);

        worldWarIICheckBox1 = new JCheckBox("World War II");
        actionlistener = new filterActionListener(WORLD_WAR_II, worldWarIICheckBox1);
        worldWarIICheckBox1.addActionListener(actionlistener);

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

