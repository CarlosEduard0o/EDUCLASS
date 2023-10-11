from Configuration.main import pauseScreen
from Game.main import mainGame
from Ranking.main import ranking
from globalFunctions import click
from Exit.main import exit

def initGame(mouse, screen, config, clock):
    x_init = 654
    x_final = 600
    y_init = 314
    y_final = 150
    if (click(mouse, x_init, x_final, y_init, y_final)):
     mainGame(screen, config, clock)


def configuration(mouse, screen, clock, config):
    x_init = 654
    x_final = 600
    y_init = 496
    y_final = 150
    if (click(mouse, x_init, x_final, y_init, y_final)):
      pauseScreen(screen, clock, config)

def rankingButton(mouse, screen, clock):
    x_init = 654
    x_final = 600
    y_init = 678
    y_final = 150
    if (click(mouse, x_init, x_final, y_init, y_final)):
      ranking(screen, clock)

def exitGame(mouse, screen, clock):
  x_init = 810
  width = 294
  y_init = 860
  heigth = 150
  if (click(mouse, x_init, width, y_init, heigth)):
    exit(screen, clock)
