# Competitive Tool for Super Smash Bros. Melee.

## A personal project by Jeffrey Chow

What does the application do?

This is an application tool for the competitive E-sports game Super Smash Bros. Melee. The
ultimate aims of this application are to:

1. Track tournament results of my favourite competitive Super Smash Bros. Melee competitors.
2. Rank competitors by recent performance in the past year.
3. Create tournament brackets (Single Elimination, Double Elimination, Group-Stage, etc.).
4. Provide player data, along with other statistics (Head-to-Head, Character Matchups, etc.).

Melee players, rank them by recent performance in the past year, provide player data, and
generate brackets based on their rankings. The tool's intention is to *expand the player
database to all players*, which will allow for easy tournament seeding and bracket maker for
tournament organizers. The statistics tracking component of the application serves as a
one-stop shop for that type of data that will be used by players, analysts, tournament
organizers, and community members in the Melee community.

The issue of tournament seeding, and end-of-year rankings for competitive players in Super
Smash Bros. Melee has been a yearly topic of debate. As a fan of the sport, I wanted to 
contribute to this grass-roots community and hopefully help tournament organizers seed and 
run tournaments more smoothly. Additionally, the player statistics can be used by community 
members to evaluate player performance and use the data to formulate their own player rankings.

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

## Phase 4: Task 2
This is a representative EventLog output for the GUI version of the application.
- Fri Apr 01 12:23:32 PDT 2022 Player: mang0 loaded from file. 
- Fri Apr 01 12:23:32 PDT 2022 Player: Zain loaded from file.
- Fri Apr 01 12:23:32 PDT 2022 Player: Plup loaded from file.
- Fri Apr 01 12:23:32 PDT 2022 Player: iBDW loaded from file.
- Fri Apr 01 12:23:32 PDT 2022 Player: Wizzrobe loaded from file.
- Fri Apr 01 12:23:32 PDT 2022 Player: SFAT loaded from file.
- Fri Apr 01 12:23:32 PDT 2022 Player: Hungrybox loaded from file.
- Fri Apr 01 12:23:32 PDT 2022 Player: Leffen loaded from file.
- Fri Apr 01 12:23:32 PDT 2022 Player: Jeffrey loaded from file.
- Fri Apr 01 12:23:32 PDT 2022 Player: Jeffrey Two loaded from file.
- Fri Apr 01 12:23:44 PDT 2022 Player: Jeffrey Three added.
- Fri Apr 01 12:23:50 PDT 2022 Search for Jeffrey occurred.

## Phase 4: Task 3
Even before constructing the UML diagram, I had already sensed that the program's structure 
was poorly designed. After making the UML diagram, it has become clear that much refactoring
can be done to improve the program structure and design of this program. For example, many of 
the interclass relationships are not necessarily in the fields of the classes headers. The
first change I would make is to ensure that every interclass relationship is represented in
the fields of their respective classes. Secondly, I would heavily refactor the MeleeAppUI class.
Each component of the GUI would have its own class, not just the AddPlayerUI and the
PlayerDatabaseUI parts. That would give more consistency to navigating through the code.
Additionally, I would refactor all of the ActionListeners or WindowListeners into a different
packages, similar to the exceptions class. These two main changes would be a good place to start.
Something that I realized during the end of phase 2 and the beginning of phase 3, was that the
core structure between Player, Match, and Tournament felt inflexible. The classes in the model
package did not adapt well to changes, nor did these classes feel flexible when working with
the UI. I think that ultimately gave me the feeling that the program structure was poorly designed,
which resulted in a lot of spaghetti code that became more and more apparent as the project
progressed. I'm not completely sure what the solution is at this point, but the Player and Match
classes are basic enough to stay as they are, as they represent a clear representation of what
they should represent. However, I would look into the tournament class and how it interacts
with the Match and Player class. More specifically, I would consider various different data
structures for storing these objects that are more suited to what I am looking for.