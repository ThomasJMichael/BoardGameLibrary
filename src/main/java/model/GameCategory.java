package main.java.model;

public enum GameCategory {
    WARGAME("Wargame"),
    MINIATURES("Miniatures"),
    DEDUCTION("Deduction"),
    MEMORY("Memory"),
    PREHISTORIC("Prehistoric"),
    POST_NAPOLEONIC("Post-Napoleonic"),
    BLUFFING("Bluffing"),
    RACING("Racing"),
    SPORTS("Sports"),
    TERRITORY_BUILDING("Territory Building"),
    MAFIA("Mafia"),
    NEGOTIATION("Negotiation"),
    ENVIRONMENTAL("Environmental"),
    NOVEL_BASED("Novel-based"),
    PIKE_AND_SHOT("Pike and Shot"),
    COLLECTIBLE_COMPONENTS("Collectible Components"),
    MURDER_MYSTERY("Murder/Mystery"),
    AGE_OF_REASON("Age of Reason"),
    CHILDRENS_GAME("Children's Game"),
    SPIES_SECRET_AGENTS("Spies/Secret Agents"),
    FANTASY("Fantasy"),
    MEDICAL("Medical"),
    POLITICAL("Political"),
    WORD_GAME("Word Game"),
    COMIC_BOOK_STRIP("Comic Book / Strip"),
    FARMING("Farming"),
    WORLD_WAR_II("World War II"),
    FIGHTING("Fighting"),
    TRANSPORTATION("Transportation"),
    CARD_GAME("Card Game"),
    SCIENCE_FICTION("Science Fiction"),
    ABSTRACT_STRATEGY("Abstract Strategy"),
    CIVILIZATION("Civilization"),
    SPACE_EXPLORATION("Space Exploration"),
    ANCIENT("Ancient"),
    INDUSTRY_MANUFACTURING("Industry / Manufacturing"),
    PRINT_AND_PLAY("Print and Play"),
    VIDEO_GAME_THEME("Video Game Theme"),
    MOVIES_TV_RADIO_THEME("Movies / TV / Radio theme"),
    ADVENTURE("Adventure"),
    HORROR("Horror"),
    NAUTICAL("Nautical"),
    ARABIAN("Arabian"),
    DICE("Dice"),
    EXPANSION_FOR_BASE_GAME("Expansion for Base-game"),
    CITY_BUILDING("City Building"),
    EXPLORATION("Exploration"),
    EDUCATIONAL("Educational"),
    ELECTRONIC("Electronic"),
    TRIVIA("Trivia"),
    HUMOR("Humor"),
    NAPOLEONIC("Napoleonic"),
    RENAISSANCE("Renaissance"),
    TRAVEL("Travel"),
    MEDIEVAL("Medieval"),
    ECONOMIC("Economic"),
    ANIMALS("Animals"),
    PUZZLE("Puzzle"),
    PARTY_GAME("Party Game"),
    MYTHOLOGY("Mythology"),
    MAZE("Maze");

    private final String categoryName;

    GameCategory(String value) {
        this.categoryName = value.toUpperCase();
    }

    @Override
    public String toString() {
        return categoryName;
    }
}
