import pygame
from Ranking.excel import insetText

from globalFunctions import click, drawMouse, setBackgroundImage, setFps

def ranking(screen, clock):
    backgroundImage = setBackgroundRanking()
    font = pygame.font.SysFont("arial", 70)
    mouse = pygame.mouse.get_pos()
    button = False
    while not button:
      mouse = pygame.mouse.get_pos()
      setFps(clock)
      screen.blit(backgroundImage, (0,0))
      for event in pygame.event.get():
          if event.type == pygame.QUIT:
              pygame.quit()
          if event.type == pygame.MOUSEBUTTONDOWN:
              mouse = event.pos
              button = returnRanking(mouse)
          if event.type == pygame.MOUSEMOTION:
              mouse = event.pos
      insetText(screen, font)
      drawMouse(screen, mouse)
      pygame.display.update()


def returnRanking(mouse):
    x_init = 78
    x_final = 150
    y_init = 75
    y_final = 150
    if (click(mouse, x_init, x_final, y_init, y_final)):
        return True

def setBackgroundRanking():
  localImage = 'Ranking/Images/Background.png'
  backgroundImage = setBackgroundImage(localImage)
  return backgroundImage

