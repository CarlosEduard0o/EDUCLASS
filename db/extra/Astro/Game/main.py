import pygame
from Game.Game.game import game
from Game.LoadingGame.loading import loading
from Game.LoseGame.main import loseGame


def mainGame(screen, config, clock):
  home = loading(screen, clock, config)
  if(not home):
    pygame.mouse.set_visible(False)
    pontuacao = game(screen, config, clock)
    pygame.mouse.set_visible(True)
    loseGame(screen, clock,pontuacao)
