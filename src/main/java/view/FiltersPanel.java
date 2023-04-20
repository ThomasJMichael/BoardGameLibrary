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
    /**
     * Object of HomePageFrame
     */
    private final JLabel filterLabel;

    private final JPanel filterByRatingPanel;
    private final JComboBox ratingFilterMinimum;
    private final JButton addRatingFilter;
    private final JButton removeRatingFilter;

    private final JPanel filterByMinAgePanel;
    private final JButton addMinimumAge;
    private final JButton removeMinimumAge;



    /**
     * Parameterized Constructor: creates the FiltersPanel with checkboxes for every
     * possible GameCategory to filter by, also adds the appropriate action listener
     * to each checkbox by creating a FilterActionListener object
     * @param homeFrame the frame the panel sits on
     */
    public FiltersPanel(HomePageFrame homeFrame) {
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(300,1200));

        filterLabel = new JLabel("Add a filter to your search:");

        JCheckBox abstractStrategyCheckBox = new JCheckBox("Abstract Strategy");
        ActionListener actionlistener = new FilterActionListener(MAZE, abstractStrategyCheckBox);
        abstractStrategyCheckBox.addActionListener(actionlistener);

        JCheckBox adventureCheckBox = new JCheckBox("Adventure");
        actionlistener = new FilterActionListener(ADVENTURE, adventureCheckBox);
        adventureCheckBox.addActionListener(actionlistener);

        JCheckBox ageOfReasonCheckBox1 = new JCheckBox("Age of Reason");
        actionlistener = new FilterActionListener(AGE_OF_REASON, ageOfReasonCheckBox1);
        ageOfReasonCheckBox1.addActionListener(actionlistener);

        JCheckBox ancientCheckBox = new JCheckBox("Ancient");
        actionlistener = new FilterActionListener(ANCIENT, ancientCheckBox);
        ancientCheckBox.addActionListener(actionlistener);

        JCheckBox animalsCheckBox = new JCheckBox("Animals");
        actionlistener = new FilterActionListener(ANIMALS, animalsCheckBox);
        animalsCheckBox.addActionListener(actionlistener);

        JCheckBox arabianCheckBox = new JCheckBox("Arabian");
        actionlistener = new FilterActionListener(ARABIAN, arabianCheckBox);
        arabianCheckBox.addActionListener(actionlistener);

        JCheckBox bluffingCheckBox1 = new JCheckBox("Bluffing");
        actionlistener = new FilterActionListener(BLUFFING, bluffingCheckBox1);
        bluffingCheckBox1.addActionListener(actionlistener);

        JCheckBox cardGameCheckBox = new JCheckBox("Card Game");
        actionlistener = new FilterActionListener(CARD_GAME, cardGameCheckBox);
        cardGameCheckBox.addActionListener(actionlistener);

        JCheckBox childrenSGameCheckBox1 = new JCheckBox("Children's Game");
        actionlistener = new FilterActionListener(CHILDRENS_GAME, childrenSGameCheckBox1);
        childrenSGameCheckBox1.addActionListener(actionlistener);

        JCheckBox cityBuildingCheckBox = new JCheckBox("City Building");
        actionlistener = new FilterActionListener(CITY_BUILDING, cityBuildingCheckBox);
        cityBuildingCheckBox.addActionListener(actionlistener);

        JCheckBox civilizationCheckBox = new JCheckBox("Civilization");
        actionlistener = new FilterActionListener(CIVILIZATION, civilizationCheckBox);
        civilizationCheckBox.addActionListener(actionlistener);

        JCheckBox collectibleComponentsCheckBox1 = new JCheckBox("Collectible Components");
        actionlistener = new FilterActionListener(COLLECTIBLE_COMPONENTS, collectibleComponentsCheckBox1);
        collectibleComponentsCheckBox1.addActionListener(actionlistener);

        JCheckBox comicBookStripCheckBox = new JCheckBox("Comic Book Strip");
        actionlistener = new FilterActionListener(COMIC_BOOK_STRIP, comicBookStripCheckBox);
        comicBookStripCheckBox.addActionListener(actionlistener);

        JCheckBox deductionCheckBox = new JCheckBox("Deduction");
        actionlistener = new FilterActionListener(DEDUCTION, deductionCheckBox);
        deductionCheckBox.addActionListener(actionlistener);

        JCheckBox diceCheckBox = new JCheckBox("Dice");
        actionlistener = new FilterActionListener(DICE, diceCheckBox);
        diceCheckBox.addActionListener(actionlistener);

        JCheckBox economicCheckBox = new JCheckBox("Economic");
        actionlistener = new FilterActionListener(ECONOMIC, economicCheckBox);
        economicCheckBox.addActionListener(actionlistener);

        JCheckBox educationalCheckBox = new JCheckBox("Educational");
        actionlistener = new FilterActionListener(EDUCATIONAL, educationalCheckBox);
        educationalCheckBox.addActionListener(actionlistener);

        JCheckBox electronicCheckBox = new JCheckBox("Electronic");
        actionlistener = new FilterActionListener(ELECTRONIC, electronicCheckBox);
        electronicCheckBox.addActionListener(actionlistener);

        JCheckBox environmentalCheckBox1 = new JCheckBox("Environmental");
        actionlistener = new FilterActionListener(ENVIRONMENTAL, environmentalCheckBox1);
        environmentalCheckBox1.addActionListener(actionlistener);

        JCheckBox expansionForBaseGameCheckBox = new JCheckBox("Expansion for Base Game");
        actionlistener = new FilterActionListener(EXPANSION_FOR_BASE_GAME, expansionForBaseGameCheckBox);
        expansionForBaseGameCheckBox.addActionListener(actionlistener);

        JCheckBox explorationCheckBox = new JCheckBox("Exploration");
        actionlistener = new FilterActionListener(EXPLORATION, explorationCheckBox);
        explorationCheckBox.addActionListener(actionlistener);

        JCheckBox fantasyCheckBox = new JCheckBox("Fantasy");
        actionlistener = new FilterActionListener(FANTASY, fantasyCheckBox);
        fantasyCheckBox.addActionListener(actionlistener);

        JCheckBox farmingCheckBox = new JCheckBox("Farming");
        actionlistener = new FilterActionListener(FARMING, farmingCheckBox);
        fantasyCheckBox.addActionListener(actionlistener);

        JCheckBox fightingCheckBox = new JCheckBox("Fighting");
        actionlistener = new FilterActionListener(FIGHTING, fightingCheckBox);
        fightingCheckBox.addActionListener(actionlistener);

        JCheckBox horrorCheckBox = new JCheckBox("Horror");
        actionlistener = new FilterActionListener(HORROR, horrorCheckBox);
        horrorCheckBox.addActionListener(actionlistener);

        JCheckBox humorCheckBox = new JCheckBox("Humor");
        actionlistener = new FilterActionListener(HUMOR, humorCheckBox);
        humorCheckBox.addActionListener(actionlistener);

        JCheckBox industryManufacturingCheckBox = new JCheckBox("Industry/Manufacturing");
        actionlistener = new FilterActionListener(INDUSTRY_MANUFACTURING, industryManufacturingCheckBox);
        industryManufacturingCheckBox.addActionListener(actionlistener);

        JCheckBox mafiaCheckBox = new JCheckBox("Mafia");
        actionlistener = new FilterActionListener(MAFIA, mafiaCheckBox);
        mafiaCheckBox.addActionListener(actionlistener);

        JCheckBox mazeCheckBox = new JCheckBox("Maze");
        actionlistener = new FilterActionListener(MAZE, mazeCheckBox);
        mazeCheckBox.addActionListener(actionlistener);

        JCheckBox medicalCheckBox = new JCheckBox("Medical");
        actionlistener = new FilterActionListener(MEDICAL, medicalCheckBox);
        medicalCheckBox.addActionListener(actionlistener);

        JCheckBox medievalCheckBox = new JCheckBox("Medieval");
        actionlistener = new FilterActionListener(MEDIEVAL, medievalCheckBox);
        medievalCheckBox.addActionListener(actionlistener);

        JCheckBox memoryCheckBox = new JCheckBox("Memory");
        actionlistener = new FilterActionListener(MEMORY, memoryCheckBox);
        memoryCheckBox.addActionListener(actionlistener);

        JCheckBox miniaturesCheckBox = new JCheckBox("Miniatures");
        actionlistener = new FilterActionListener(MINIATURES, miniaturesCheckBox);
        miniaturesCheckBox.addActionListener(actionlistener);

        JCheckBox moviesTVRadioThemeCheckBox = new JCheckBox("Movies/TV/Radio Theme");
        actionlistener = new FilterActionListener(MOVIES_TV_RADIO_THEME, moviesTVRadioThemeCheckBox);
        moviesTVRadioThemeCheckBox.addActionListener(actionlistener);

        JCheckBox murderMysteryCheckBox1 = new JCheckBox("Murder/Mystery");
        actionlistener = new FilterActionListener(MURDER_MYSTERY, murderMysteryCheckBox1);
        murderMysteryCheckBox1.addActionListener(actionlistener);

        JCheckBox mythologyCheckBox = new JCheckBox("Mythology");
        actionlistener = new FilterActionListener(MYTHOLOGY, mythologyCheckBox);
        mythologyCheckBox.addActionListener(actionlistener);

        JCheckBox napoleonicCheckBox = new JCheckBox("Napoleonic");
        actionlistener = new FilterActionListener(NAPOLEONIC, napoleonicCheckBox);
        napoleonicCheckBox.addActionListener(actionlistener);

        JCheckBox nauticalCheckBox = new JCheckBox("Nautical");
        actionlistener = new FilterActionListener(NAUTICAL, nauticalCheckBox);
        nauticalCheckBox.addActionListener(actionlistener);

        JCheckBox negotiationCheckBox = new JCheckBox("Negotiation");
        actionlistener = new FilterActionListener(NEGOTIATION, negotiationCheckBox);
        negotiationCheckBox.addActionListener(actionlistener);

        JCheckBox novelBasedCheckBox = new JCheckBox("Novel Based");
        actionlistener = new FilterActionListener(NOVEL_BASED, novelBasedCheckBox);
        novelBasedCheckBox.addActionListener(actionlistener);

        JCheckBox partyGameCheckBox = new JCheckBox("Party Game");
        actionlistener = new FilterActionListener(PARTY_GAME, partyGameCheckBox);
        partyGameCheckBox.addActionListener(actionlistener);

        JCheckBox pikeAndShotCheckBox = new JCheckBox("Pike and Shot");
        actionlistener = new FilterActionListener(PIKE_AND_SHOT, pikeAndShotCheckBox);
        pikeAndShotCheckBox.addActionListener(actionlistener);

        JCheckBox politicalCheckBox = new JCheckBox("Political");
        actionlistener = new FilterActionListener(POLITICAL, politicalCheckBox);
        politicalCheckBox.addActionListener(actionlistener);

        JCheckBox postNapoleonicCheckBox = new JCheckBox("Post-Napoleonic");
        actionlistener = new FilterActionListener(POST_NAPOLEONIC, postNapoleonicCheckBox);
        postNapoleonicCheckBox.addActionListener(actionlistener);

        JCheckBox prehistoricCheckBox = new JCheckBox("Prehistoric");
        actionlistener = new FilterActionListener(PREHISTORIC, prehistoricCheckBox);
        prehistoricCheckBox.addActionListener(actionlistener);

        JCheckBox printAndPlayCheckBox = new JCheckBox("Print and Play");
        actionlistener = new FilterActionListener(PRINT_AND_PLAY, printAndPlayCheckBox);
        printAndPlayCheckBox.addActionListener(actionlistener);

        JCheckBox puzzleCheckBox = new JCheckBox("Puzzle");
        actionlistener = new FilterActionListener(PUZZLE, puzzleCheckBox);
        puzzleCheckBox.addActionListener(actionlistener);

        JCheckBox racingCheckBox = new JCheckBox("Racing");
        actionlistener = new FilterActionListener(RACING, racingCheckBox);
        racingCheckBox.addActionListener(actionlistener);

        JCheckBox renaissanceCheckBox = new JCheckBox("Renaissance");
        actionlistener = new FilterActionListener(RENAISSANCE, renaissanceCheckBox);
        renaissanceCheckBox.addActionListener(actionlistener);

        JCheckBox scienceFictionCheckBox = new JCheckBox("Science Fiction");
        actionlistener = new FilterActionListener(SCIENCE_FICTION, scienceFictionCheckBox);
        scienceFictionCheckBox.addActionListener(actionlistener);

        JCheckBox spaceExplorationCheckBox = new JCheckBox("Space Exploration");
        actionlistener = new FilterActionListener(SPACE_EXPLORATION, spaceExplorationCheckBox);
        spaceExplorationCheckBox.addActionListener(actionlistener);

        JCheckBox spiesSecretAgentsCheckBox1 = new JCheckBox("Spies/Secret Agents");
        actionlistener = new FilterActionListener(SPIES_SECRET_AGENTS, spiesSecretAgentsCheckBox1);
        spiesSecretAgentsCheckBox1.addActionListener(actionlistener);

        JCheckBox sportsCheckBox1 = new JCheckBox("Sports");
        actionlistener = new FilterActionListener(SPORTS, sportsCheckBox1);
        sportsCheckBox1.addActionListener(actionlistener);

        JCheckBox territoryBuildingCheckBox1 = new JCheckBox("Territory Building");
        actionlistener = new FilterActionListener(TERRITORY_BUILDING, territoryBuildingCheckBox1);
        territoryBuildingCheckBox1.addActionListener(actionlistener);

        JCheckBox transportationCheckBox = new JCheckBox("Transportation");
        actionlistener = new FilterActionListener(TRANSPORTATION, transportationCheckBox);
        transportationCheckBox.addActionListener(actionlistener);

        JCheckBox travelCheckBox = new JCheckBox("Travel");
        actionlistener = new FilterActionListener(TRAVEL, travelCheckBox);
        travelCheckBox.addActionListener(actionlistener);

        JCheckBox triviaCheckBox1 = new JCheckBox("Trivia");
        actionlistener = new FilterActionListener(TRIVIA, triviaCheckBox1);
        triviaCheckBox1.addActionListener(actionlistener);

        JCheckBox videoGameThemeCheckBox = new JCheckBox("Video Game Theme");
        actionlistener = new FilterActionListener(VIDEO_GAME_THEME, videoGameThemeCheckBox);
        videoGameThemeCheckBox.addActionListener(actionlistener);

        JCheckBox wargameCheckBox = new JCheckBox("Wargame");
        actionlistener = new FilterActionListener(WARGAME, wargameCheckBox);
        wargameCheckBox.addActionListener(actionlistener);

        JCheckBox wordGameCheckBox = new JCheckBox("Word Game");
        actionlistener = new FilterActionListener(WORD_GAME, wordGameCheckBox);
        wordGameCheckBox.addActionListener(actionlistener);

        JCheckBox worldWarIICheckBox1 = new JCheckBox("World War II");
        actionlistener = new FilterActionListener(WORLD_WAR_II, worldWarIICheckBox1);
        worldWarIICheckBox1.addActionListener(actionlistener);

        JLabel ratingFilterLabel = new JLabel("Minimum Rating: ");
        Integer[] ratingNums = {1,2,3,4,5};
        ratingFilterMinimum = new JComboBox<>(ratingNums);

        addRatingFilter = new JButton("Add");
        addRatingFilter.addActionListener(new ActionListener() {
            /**
             * Adds a rating filter to the search given a minimum game rating
             * @param e the event to be processed (click)
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer ratingMin = ratingFilterMinimum.getSelectedIndex() + 1;
                Controller.getInstance().addRatingFilter(ratingMin);
            }
        });

        removeRatingFilter = new JButton("Remove Filter");
        /**
         * Removes the rating filter that is applied
         */
        removeRatingFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.getInstance().removeRatingFilter();
                JOptionPane.showMessageDialog(null, "Rating filter removed.");
            }
        });

        filterByRatingPanel = new JPanel(new FlowLayout());
        filterByRatingPanel.add(ratingFilterLabel);
        filterByRatingPanel.add(ratingFilterMinimum);
        filterByRatingPanel.add(addRatingFilter);
        filterByRatingPanel.add(removeRatingFilter);
        filterByRatingPanel.setPreferredSize(new Dimension(200, 75));

        JLabel minAgeFilterLabel = new JLabel("Minimum Age: ");
        JTextField minimumAgeField = new JTextField();
        minimumAgeField.setPreferredSize(new Dimension(50,20));

        addMinimumAge = new JButton("Add");
        addMinimumAge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int minAge;
                boolean valid = true;
                if (minimumAgeField.getText() == null)
                    valid = false;
                try {
                    minAge = Integer.parseInt(minimumAgeField.getText());
                } catch (NumberFormatException nfe) {
                    valid = false;
                    JOptionPane.showMessageDialog(null, "Minimum age must be an integer.");
                }

                if (valid) {
                    minAge = Integer.parseInt(minimumAgeField.getText());
                    Controller.getInstance().addMinAgeFilter(minAge);
                }
            }
        });

        removeMinimumAge = new JButton("Remove Filter");
        removeMinimumAge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // want to remove all age filters
            }
        });

        filterByMinAgePanel = new JPanel(new FlowLayout());
        filterByMinAgePanel.setPreferredSize(new Dimension(200,200));
        filterByMinAgePanel.add(minAgeFilterLabel);
        filterByMinAgePanel.add(minimumAgeField);
        filterByMinAgePanel.add(addMinimumAge);
        filterByMinAgePanel.add(removeMinimumAge);

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

        // add the rating filter
        add(filterByRatingPanel);

        add(filterByMinAgePanel);

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
            Controller.getInstance().addCategoryFilter(category.toString());
        }
        else {
            Controller.getInstance().removeCategoryFilter(category.toString());
        }
    }

}

