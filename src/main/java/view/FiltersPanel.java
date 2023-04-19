package main.java.view;

import main.java.controller.Controller;
import main.java.model.GameCategory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static main.java.model.GameCategory.*;

/**
 * FiltersPanel displays the possible game categories for the user to filter their
 * search by, which are found in the GameCategory model. Uses the FilterActionListener class
 * to monitor adding/removing filters related to checking and unchecking category boxes.
 */

public class FiltersPanel extends JPanel {
    private HomePageFrame frame;
    private final JCheckBox abstractStrategyCheckBox;
    private final JCheckBox ancientCheckBox;
    private final JCheckBox bluffingCheckBox1;
    private final JCheckBox comicBookStripCheckBox;
    private final JCheckBox economicCheckBox;
    private final JCheckBox environmentalCheckBox1;
    private final JCheckBox fantasyCheckBox;
    private final JCheckBox horrorCheckBox;
    private final JCheckBox adventureCheckBox;
    private final JCheckBox animalsCheckBox;
    private final JCheckBox cardGameCheckBox;
    private final JCheckBox cityBuildingCheckBox;
    private final JCheckBox civilizationCheckBox;
    private final JCheckBox deductionCheckBox;
    private final JCheckBox educationalCheckBox;
    private final JCheckBox expansionForBaseGameCheckBox;
    private final JCheckBox farmingCheckBox;
    private final JCheckBox humorCheckBox;
    private final JCheckBox mazeCheckBox;
    private final JCheckBox ageOfReasonCheckBox1;
    private final JCheckBox arabianCheckBox;
    private final JCheckBox childrenSGameCheckBox1;
    private final JCheckBox collectibleComponentsCheckBox1;
    private final JCheckBox diceCheckBox;
    private final JCheckBox electronicCheckBox;
    private final JCheckBox explorationCheckBox;
    private final JCheckBox fightingCheckBox;
    private final JCheckBox industryManufacturingCheckBox;
    private final JCheckBox medicalCheckBox;
    private final JCheckBox mafiaCheckBox;
    private final JCheckBox medievalCheckBox;
    private final JCheckBox moviesTVRadioThemeCheckBox;
    private final JCheckBox napoleonicCheckBox;
    private final JCheckBox novelBasedCheckBox;
    private final JCheckBox politicalCheckBox;
    private final JCheckBox printAndPlayCheckBox;
    private final JCheckBox renaissanceCheckBox;
    private final JCheckBox spiesSecretAgentsCheckBox1;
    private final JCheckBox transportationCheckBox;
    private final JCheckBox triviaCheckBox1;
    private final JCheckBox memoryCheckBox;
    private final JCheckBox videoGameThemeCheckBox;
    private final JCheckBox travelCheckBox;
    private final JCheckBox territoryBuildingCheckBox1;
    private final JCheckBox scienceFictionCheckBox;
    private final JCheckBox puzzleCheckBox;
    private final JCheckBox postNapoleonicCheckBox;
    private final JCheckBox partyGameCheckBox;
    private final JCheckBox nauticalCheckBox;
    private final JCheckBox murderMysteryCheckBox1;
    private final JCheckBox miniaturesCheckBox;
    private final JCheckBox mythologyCheckBox;
    private final JCheckBox negotiationCheckBox;
    private final JCheckBox pikeAndShotCheckBox;
    private final JCheckBox spaceExplorationCheckBox;
    private final JCheckBox racingCheckBox;
    private final JCheckBox prehistoricCheckBox;
    private final JCheckBox wordGameCheckBox;
    private final JCheckBox sportsCheckBox1;
    private final JCheckBox worldWarIICheckBox1;
    private final JCheckBox wargameCheckBox;
    private final JLabel filterLabel;

    /**
     * Parameterized Constructor: creates the FiltersPanel with checkboxes for every
     * possible GameCategory to filter by, also adds the appropriate action listener
     * to each checkbox by creating a FilterActionListener object
     * @param homeFrame the frame the panel sits on
     */
    public FiltersPanel(HomePageFrame homeFrame) {
        frame = homeFrame;
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(300,800));

        filterLabel = new JLabel("Add a filter to your search:");

        abstractStrategyCheckBox = new JCheckBox("Abstract Strategy");
        ActionListener actionlistener = new FilterActionListener(MAZE, abstractStrategyCheckBox);
        abstractStrategyCheckBox.addActionListener(actionlistener);

        adventureCheckBox = new JCheckBox("Adventure");
        actionlistener = new FilterActionListener(ADVENTURE, adventureCheckBox);
        adventureCheckBox.addActionListener(actionlistener);

        ageOfReasonCheckBox1 = new JCheckBox("Age of Reason");
        actionlistener = new FilterActionListener(AGE_OF_REASON, ageOfReasonCheckBox1);
        ageOfReasonCheckBox1.addActionListener(actionlistener);

        ancientCheckBox = new JCheckBox("Ancient");
        actionlistener = new FilterActionListener(ANCIENT, ancientCheckBox);
        ancientCheckBox.addActionListener(actionlistener);

        animalsCheckBox = new JCheckBox("Animals");
        actionlistener = new FilterActionListener(ANIMALS, animalsCheckBox);
        animalsCheckBox.addActionListener(actionlistener);

        arabianCheckBox = new JCheckBox("Arabian");
        actionlistener = new FilterActionListener(ARABIAN, arabianCheckBox);
        arabianCheckBox.addActionListener(actionlistener);

        bluffingCheckBox1 = new JCheckBox("Bluffing");
        actionlistener = new FilterActionListener(BLUFFING, bluffingCheckBox1);
        bluffingCheckBox1.addActionListener(actionlistener);

        cardGameCheckBox = new JCheckBox("Card Game");
        actionlistener = new FilterActionListener(CARD_GAME, cardGameCheckBox);
        cardGameCheckBox.addActionListener(actionlistener);

        childrenSGameCheckBox1 = new JCheckBox("Children's Game");
        actionlistener = new FilterActionListener(CHILDRENS_GAME, childrenSGameCheckBox1);
        childrenSGameCheckBox1.addActionListener(actionlistener);

        cityBuildingCheckBox = new JCheckBox("City Building");
        actionlistener = new FilterActionListener(CITY_BUILDING, cityBuildingCheckBox);
        cityBuildingCheckBox.addActionListener(actionlistener);

        civilizationCheckBox = new JCheckBox("Civilization");
        actionlistener = new FilterActionListener(CIVILIZATION, civilizationCheckBox);
        civilizationCheckBox.addActionListener(actionlistener);

        collectibleComponentsCheckBox1 = new JCheckBox("Collectible Components");
        actionlistener = new FilterActionListener(COLLECTIBLE_COMPONENTS, collectibleComponentsCheckBox1);
        collectibleComponentsCheckBox1.addActionListener(actionlistener);

        comicBookStripCheckBox = new JCheckBox("Comic Book Strip");
        actionlistener = new FilterActionListener(COMIC_BOOK_STRIP, comicBookStripCheckBox);
        comicBookStripCheckBox.addActionListener(actionlistener);

        deductionCheckBox = new JCheckBox("Deduction");
        actionlistener = new FilterActionListener(DEDUCTION, deductionCheckBox);
        deductionCheckBox.addActionListener(actionlistener);

        diceCheckBox = new JCheckBox("Dice");
        actionlistener = new FilterActionListener(DICE, diceCheckBox);
        diceCheckBox.addActionListener(actionlistener);

        economicCheckBox = new JCheckBox("Economic");
        actionlistener = new FilterActionListener(ECONOMIC, economicCheckBox);
        economicCheckBox.addActionListener(actionlistener);

        educationalCheckBox = new JCheckBox("Educational");
        actionlistener = new FilterActionListener(EDUCATIONAL, educationalCheckBox);
        educationalCheckBox.addActionListener(actionlistener);

        electronicCheckBox = new JCheckBox("Electronic");
        actionlistener = new FilterActionListener(ELECTRONIC, electronicCheckBox);
        electronicCheckBox.addActionListener(actionlistener);

        environmentalCheckBox1 = new JCheckBox("Environmental");
        actionlistener = new FilterActionListener(ENVIRONMENTAL, environmentalCheckBox1);
        environmentalCheckBox1.addActionListener(actionlistener);

        expansionForBaseGameCheckBox = new JCheckBox("Expansion for Base Game");
        actionlistener = new FilterActionListener(EXPANSION_FOR_BASE_GAME, expansionForBaseGameCheckBox);
        expansionForBaseGameCheckBox.addActionListener(actionlistener);

        explorationCheckBox = new JCheckBox("Exploration");
        actionlistener = new FilterActionListener(EXPLORATION, explorationCheckBox);
        explorationCheckBox.addActionListener(actionlistener);

        fantasyCheckBox = new JCheckBox("Fantasy");
        actionlistener = new FilterActionListener(FANTASY, fantasyCheckBox);
        fantasyCheckBox.addActionListener(actionlistener);

        farmingCheckBox = new JCheckBox("Farming");
        actionlistener = new FilterActionListener(FARMING, farmingCheckBox);
        fantasyCheckBox.addActionListener(actionlistener);

        fightingCheckBox = new JCheckBox("Fighting");
        actionlistener = new FilterActionListener(FIGHTING, fightingCheckBox);
        fightingCheckBox.addActionListener(actionlistener);

        horrorCheckBox = new JCheckBox("Horror");
        actionlistener = new FilterActionListener(HORROR, horrorCheckBox);
        horrorCheckBox.addActionListener(actionlistener);

        humorCheckBox = new JCheckBox("Humor");
        actionlistener = new FilterActionListener(HUMOR, humorCheckBox);
        humorCheckBox.addActionListener(actionlistener);

        industryManufacturingCheckBox = new JCheckBox("Industry/Manufacturing");
        actionlistener = new FilterActionListener(INDUSTRY_MANUFACTURING, industryManufacturingCheckBox);
        industryManufacturingCheckBox.addActionListener(actionlistener);

        mafiaCheckBox = new JCheckBox("Mafia");
        actionlistener = new FilterActionListener(MAFIA, mafiaCheckBox);
        mafiaCheckBox.addActionListener(actionlistener);

        mazeCheckBox = new JCheckBox("Maze");
        actionlistener = new FilterActionListener(MAZE, mazeCheckBox);
        mazeCheckBox.addActionListener(actionlistener);

        medicalCheckBox = new JCheckBox("Medical");
        actionlistener = new FilterActionListener(MEDICAL, medicalCheckBox);
        medicalCheckBox.addActionListener(actionlistener);

        medievalCheckBox = new JCheckBox("Medieval");
        actionlistener = new FilterActionListener(MEDIEVAL, medievalCheckBox);
        medievalCheckBox.addActionListener(actionlistener);

        memoryCheckBox = new JCheckBox("Memory");
        actionlistener = new FilterActionListener(MEMORY, memoryCheckBox);
        memoryCheckBox.addActionListener(actionlistener);

        miniaturesCheckBox = new JCheckBox("Miniatures");
        actionlistener = new FilterActionListener(MINIATURES, miniaturesCheckBox);
        miniaturesCheckBox.addActionListener(actionlistener);

        moviesTVRadioThemeCheckBox = new JCheckBox("Movies/TV/Radio Theme");
        actionlistener = new FilterActionListener(MOVIES_TV_RADIO_THEME, moviesTVRadioThemeCheckBox);
        moviesTVRadioThemeCheckBox.addActionListener(actionlistener);

        murderMysteryCheckBox1 = new JCheckBox("Murder/Mystery");
        actionlistener = new FilterActionListener(MURDER_MYSTERY, murderMysteryCheckBox1);
        murderMysteryCheckBox1.addActionListener(actionlistener);

        mythologyCheckBox = new JCheckBox("Mythology");
        actionlistener = new FilterActionListener(MYTHOLOGY, mythologyCheckBox);
        mythologyCheckBox.addActionListener(actionlistener);

        napoleonicCheckBox = new JCheckBox("Napoleonic");
        actionlistener = new FilterActionListener(NAPOLEONIC, napoleonicCheckBox);
        napoleonicCheckBox.addActionListener(actionlistener);

        nauticalCheckBox = new JCheckBox("Nautical");
        actionlistener = new FilterActionListener(NAUTICAL, nauticalCheckBox);
        nauticalCheckBox.addActionListener(actionlistener);

        negotiationCheckBox = new JCheckBox("Negotiation");
        actionlistener = new FilterActionListener(NEGOTIATION, negotiationCheckBox);
        negotiationCheckBox.addActionListener(actionlistener);

        novelBasedCheckBox = new JCheckBox("Novel Based");
        actionlistener = new FilterActionListener(NOVEL_BASED, novelBasedCheckBox);
        novelBasedCheckBox.addActionListener(actionlistener);

        partyGameCheckBox = new JCheckBox("Party Game");
        actionlistener = new FilterActionListener(PARTY_GAME, partyGameCheckBox);
        partyGameCheckBox.addActionListener(actionlistener);

        pikeAndShotCheckBox = new JCheckBox("Pike and Shot");
        actionlistener = new FilterActionListener(PIKE_AND_SHOT, pikeAndShotCheckBox);
        pikeAndShotCheckBox.addActionListener(actionlistener);

        politicalCheckBox = new JCheckBox("Political");
        actionlistener = new FilterActionListener(POLITICAL, politicalCheckBox);
        politicalCheckBox.addActionListener(actionlistener);

        postNapoleonicCheckBox = new JCheckBox("Post-Napoleonic");
        actionlistener = new FilterActionListener(POST_NAPOLEONIC, postNapoleonicCheckBox);
        postNapoleonicCheckBox.addActionListener(actionlistener);

        prehistoricCheckBox = new JCheckBox("Prehistoric");
        actionlistener = new FilterActionListener(PREHISTORIC, prehistoricCheckBox);
        prehistoricCheckBox.addActionListener(actionlistener);

        printAndPlayCheckBox = new JCheckBox("Print and Play");
        actionlistener = new FilterActionListener(PRINT_AND_PLAY, printAndPlayCheckBox);
        printAndPlayCheckBox.addActionListener(actionlistener);

        puzzleCheckBox = new JCheckBox("Puzzle");
        actionlistener = new FilterActionListener(PUZZLE, puzzleCheckBox);
        puzzleCheckBox.addActionListener(actionlistener);

        racingCheckBox = new JCheckBox("Racing");
        actionlistener = new FilterActionListener(RACING, racingCheckBox);
        racingCheckBox.addActionListener(actionlistener);

        renaissanceCheckBox = new JCheckBox("Renaissance");
        actionlistener = new FilterActionListener(RENAISSANCE, renaissanceCheckBox);
        renaissanceCheckBox.addActionListener(actionlistener);

        scienceFictionCheckBox = new JCheckBox("Science Fiction");
        actionlistener = new FilterActionListener(SCIENCE_FICTION, scienceFictionCheckBox);
        scienceFictionCheckBox.addActionListener(actionlistener);

        spaceExplorationCheckBox = new JCheckBox("Space Exploration");
        actionlistener = new FilterActionListener(SPACE_EXPLORATION, spaceExplorationCheckBox);
        spaceExplorationCheckBox.addActionListener(actionlistener);

        spiesSecretAgentsCheckBox1 = new JCheckBox("Spies/Secret Agents");
        actionlistener = new FilterActionListener(SPIES_SECRET_AGENTS, spiesSecretAgentsCheckBox1);
        spiesSecretAgentsCheckBox1.addActionListener(actionlistener);

        sportsCheckBox1 = new JCheckBox("Sports");
        actionlistener = new FilterActionListener(SPORTS, sportsCheckBox1);
        sportsCheckBox1.addActionListener(actionlistener);

        territoryBuildingCheckBox1 = new JCheckBox("Territory Building");
        actionlistener = new FilterActionListener(TERRITORY_BUILDING, territoryBuildingCheckBox1);
        territoryBuildingCheckBox1.addActionListener(actionlistener);

        transportationCheckBox = new JCheckBox("Transportation");
        actionlistener = new FilterActionListener(TRANSPORTATION, transportationCheckBox);
        transportationCheckBox.addActionListener(actionlistener);

        travelCheckBox = new JCheckBox("Travel");
        actionlistener = new FilterActionListener(TRAVEL, travelCheckBox);
        travelCheckBox.addActionListener(actionlistener);

        triviaCheckBox1 = new JCheckBox("Trivia");
        actionlistener = new FilterActionListener(TRIVIA, triviaCheckBox1);
        triviaCheckBox1.addActionListener(actionlistener);

        videoGameThemeCheckBox = new JCheckBox("Video Game Theme");
        actionlistener = new FilterActionListener(VIDEO_GAME_THEME, videoGameThemeCheckBox);
        videoGameThemeCheckBox.addActionListener(actionlistener);

        wargameCheckBox = new JCheckBox("Wargame");
        actionlistener = new FilterActionListener(WARGAME, wargameCheckBox);
        wargameCheckBox.addActionListener(actionlistener);

        wordGameCheckBox = new JCheckBox("Word Game");
        actionlistener = new FilterActionListener(WORD_GAME, wordGameCheckBox);
        wordGameCheckBox.addActionListener(actionlistener);

        worldWarIICheckBox1 = new JCheckBox("World War II");
        actionlistener = new FilterActionListener(WORLD_WAR_II, worldWarIICheckBox1);
        worldWarIICheckBox1.addActionListener(actionlistener);

        // add the filter message
        add(filterLabel);

        // add all the JCheckBoxes
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

/**
 * FilterActionListener implements ActionListener to create a custom function for actionPerformed().
 * If a filter is selected, the given category is added to the filter search list and when the
 * filter is unselected, the given category is removed from the filter search list
 */

class FilterActionListener implements ActionListener {
    private GameCategory category;
    private JCheckBox filterCheckbox;

    /**
     * Constructs the FilterActionListener with the given objects
     * @param category the game category
     * @param checkbox the checkbox associated with the game category
     */
    public FilterActionListener(GameCategory category, JCheckBox checkbox) {
        this.category = category;
        filterCheckbox = checkbox;
    }

    /**
     * Adds or removes the GameCategory from the filtered search via checking or
     * unchecking the filterCheckbox
     * @param e the event to be processed (clicked or unclicked)
     */
    public void actionPerformed(ActionEvent e) {
        if (filterCheckbox.isSelected()) {
            Controller.getInstance().addCategoryFilter(category.name());
            System.out.println(category.name() + " selected.");
        }
        else {
            Controller.getInstance().removeCategoryFilter(category.name());
            System.out.println(category.name() + " unselected.");
        }
    }

}

