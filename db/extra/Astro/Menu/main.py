from Menu.buttons import configuration, exitGame, initGame, rankingButton
from globalFunctions import drawMouse, setBackgroundImage, setFps
import pygame
from Exit.main import exit

def setBackgroundMenu():
  localImage = 'Menu/Images/Background.png'
  backgroundImage = setBackgroundImage(localImage)
  return backgroundImage

########################################################

def menu(screen, clock, config):
  backgroundImage = setBackgroundMenu()
  while (True):
    pygame.mouse.set_visible(False)
    mouse = pygame.mouse.get_pos()
    setFps(clock)
    screen.blit(backgroundImage, (0,0))
    for event in pygame.event.get():
      if event.type == pygame.QUIT:
        exit(screen, clock)
      elif event.type == pygame.MOUSEMOTION:
        mouse = event.pos
      elif event.type == pygame.MOUSEBUTTONDOWN:
          mouse = event.pos
          initGame(mouse, screen, config, clock)
          configuration(mouse, screen, clock, config)
          rankingButton(mouse, screen, clock)
          exitGame(mouse, screen, clock)
    drawMouse(screen, mouse)
    pygame.display.update()





