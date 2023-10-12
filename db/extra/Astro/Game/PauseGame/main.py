import pygame
from Game.PauseGame.buttons import continueToGame, returnToMenu
from globalFunctions import drawMouse, setBackgroundImage, setFps


def setBackgroundPause():
  localImage = 'Game/PauseGame/Images/Pause.png'
  backgroundImage = setBackgroundImage(localImage)
  return backgroundImage


def pauseGame(screen, clock):
  backgroundImage = setBackgroundPause()
  continueGame = False
  returnMenu = False
  while (True):
    mouse = pygame.mouse.get_pos()
    setFps(clock)
    screen.blit(backgroundImage, (0,0))
    for event in pygame.event.get():
      if event.type == pygame.QUIT:
        pygame.quit()
      elif event.type == pygame.MOUSEMOTION:
        mouse = event.pos
      elif event.type == pygame.MOUSEBUTTONDOWN:
          mouse = event.pos
          continueGame = continueToGame(mouse)
          returnMenu = returnToMenu(mouse)
          if(continueGame or returnMenu):
            return returnMenu
    drawMouse(screen, mouse)
    pygame.display.update()
