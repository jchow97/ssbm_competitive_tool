# Competitive Tool for Super Smash Bros. Melee.

## An academic project by Jeffrey Chow

What does the application do?

This is an application tool for the competitive E-sports game Super Smash Bros. Melee. The aims 
of this application are to:

1. Track tournament results Super Smash Bros. Melee competitors.
2. Rank competitors by tournament results.
3. Create tournament brackets (Single Elimination, Double Elimination, Group-Stage, etc.).
   1. Currently, only 8-player single elimination is available.

Melee players, rank them by recent performance in the past year, provide player data, and
generate brackets based on their rankings. The tool serves as a proof of concept for future 
expansion to *include all players in the database*, which will allow for easy tournament 
seeding and bracket maker for tournament organizers. The statistics tracking component of 
the application serves as a one-stop shop for that type of data that will be used by players,
analysts, tournament organizers, and community members in the Melee community.

The issue of tournament seeding, and end-of-year rankings for competitive players in Super
Smash Bros. Melee has been a yearly topic of debate. As a fan of the sport, I wanted to 
contribute to this grass-roots community and hopefully help tournament organizers seed and 
run tournaments more smoothly. Additionally, the player statistics can be used by community 
members to evaluate player performance and use the data to formulate their own player rankings.

### How to Use:
Currently, this application supports a basic GUI viewer of the player database using JavaSwing
or a command-line program which can do the same, but also simulate an 8-player tournament. The two
different UI's can be accessed by going into 'ui/main' and changing the function to include
'new MeleeAppUI()' for the JavaSwing GUI, or 'new MeleeApp();' for the command-line application.
}

## User stories:
- As a user, I want to be able to create an 8-player tournament bracket.
- As a user, I want to be able to progress through the tournament bracket.
- As a user, I want to be able to add a new player to the player database.
- As a user, I want to be able to view the player database, with basic statistics.
- As a user, I want to be able to search for a player in the database, and retrieve statistics.
- As a user, I want to be able to see tournament results reflected in the player database (not 100% working as intended yet).
- As a user, I want to be able to save my player database and related statistics to file.
- As a user, I want to be able to load my player database and related statistics from file.
- As a user, I want to be able to save an ongoing tournament to file.
- As a user, I want to be able to load and resume an ongoing tournament from file.