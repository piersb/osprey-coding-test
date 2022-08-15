# Assumptions

* The grid is numbered from 1 to 10 rather than 0 to 9
* The grid is notionally mapped with North to Up
* The grid numbering begins in the North-west corner
* The game is single-player only
* Only capitalised directions for each of the major compass points are acceptable input for a move
* We want to retain the history of the player's moves in the database for the duration of the current game
* POST to /api/reset to start a new game
* Attempting to move off the edge of the board is an invalid move in the game, but valid input, and therefore gets a 200 rather than 400 response
* Sample GET and POST requests for acceptance testing are available in generated-requests.http
* The game doesn't begin (ie the Avatar is not randomised and the db may contain old or no data) until /api/reset is called


