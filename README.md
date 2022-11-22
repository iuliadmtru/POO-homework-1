# Homework 1 - GwentStone

> **_DISCLAIMER:_**  This homework is a disgrace to the OOP paradigm. I
> apologize in advance for what you are about to see in the source code.
> I have only one thing to say: https://www.youtube.com/watch?v=KkGVmN68ByU.

## Components

- `Game`
  - 
    - Contains general information about a game, such as **players**, **game
    board**, **player turn** and **turn and round counters**.

    - Apart from getters and setters, a `Game` instance can **end a player's
    turn** (`nextTurn` method) or the **current round** (`nextRound` method).

- `Player`
  - 
  - Contains information specific to a player, such as the **index** (in our
    case, 1 or 2), the available **mana**, the chosen **deck** of cards and the
    **cards in hand**.

  - A player can do general actions such as **shuffle his deck**, **take a card
    from the deck** or **place a card on the board**. He can do
    end-of-turn-specific actions such as **unfreeze his cards** or **reset his
    cards' state** such that they can attack, or do the turn-specific action of
    **using an environment card**. Lastly, he can also perform certain **checks**,
    to see if he has placed `Tank` cards on the board or if a certain row belongs
    to him.

- `Board`
  - 
  - Contains only a **matrix of cards** representing the cards that the players
  placed on the board.

- `Card`
  - 
  - Apart from the relevant information specific to a card (mana, attack
  damage, description etc.) a card also contains the information of whether it
  is **frozen** or not.

  - A card can be **placed on a player's board** (this is an abstract method
  implemented by the three main types of cards detailed below).

### `cards` package

There are three main abstract cards (that extend, of course, `Card`):

- `Environment`
- `Hero`
- `Minion`

`Hero` and `Environment` cards can **use their ability** and `Minion`s can
**attack**. Each of these three has subclasses corresponding to each possible
card that implement `super`'s abstract methods (e.g. `Environment` has
subclasses `Firestorm`, `HeartHound` and `Winterfell` that implement
`useAbilityOnRow`). For `Minion`s: `Sentinel` and `Berserker` are grouped into
the class `RegularMinion` and `Goliath` and `Walden` into the class `Tank`. The
special-ability minions inherit an abstract class with the abstract method
`useAbilityOn`.

### `fileio` package

This package contains input classes given in the skeleton. I have not changed
them.

### `main` package

This package contains the `Test` and `Main` classes included in the skeleton. I
have not changed the `Test` class and the `main` method inside `Main`.

#### `action` method

Welcome to the dark side of the `README` file. This is where the ~~disaster~~
main implementation starts. The general layout of the `action` method is the
following:

- Read input from JSON
- Create JSON output node
- Initialize general information
- Loop over all games
  - Set starting configuration for a game
  - Loop over all actions
    - Set action configuration
    - Execute instructions for a given action chosen with `switch` statement
    - Append the action output to the output node
- Write to output file

Doesn't sound so bad? You'll see. ~~Spoiler alert: #`instanceof` = 17~~
