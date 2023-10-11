import pygame
from Game.LoadingGame.buttons import buttonHome, buttonInit, buttonNext
from globalFunctions import drawMouse, setBackgroundImage, setFps


def setBackgroundLoading(item):
  localImage = ''
  if(item == -1):
    localImage = 'Game\LoadingGame\Images\HistoryNotTuto.png'
  if (item == 0):
    localImage = 'Game\LoadingGame\Images\History.png'
  if (item == 1):
    localImage = 'Game\LoadingGame\Images\Tutorial_1.png'
  if (item == 2):
    localImage = 'Game\LoadingGame\Images\Tutorial_2.png'
  backgroundImage = setBackgroundImage(localImage)
  return backgroundImage

def tuorial(screen, clock):
  mouse = pygame.mouse.get_pos()
  i = 1
  while i < 3:
    button = False
    home = False
    backgroundImage = setBackgroundLoading(i)
    while (not button and not home):
      setFps(clock)
      for event in pygame.event.get():
          if event.type == pygame.QUIT:
              pygame.quit()
          elif event.type == pygame.MOUSEMOTION:
              mouse = event.pos
          elif event.type == pygame.MOUSEBUTTONDOWN:
            mouse = event.pos
            if (i == 1):
              home = buttonHome(mouse)
              button = buttonNext(mouse)
              if(home):
                return home
              if(button):
                i = i + 1
            else:
              home = buttonHome(mouse)
              button = buttonNext(mouse)
              if(home):
                i = i - 1
              if(button):
                i = i + 1
      screen.blit(backgroundImage, (0,0))
      drawMouse(screen, mouse)
      pygame.display.update()


def history(screen, clock, config):
  button = False
  mouse = pygame.mouse.get_pos()
  if(not config[1]):
    backgroundImage = setBackgroundLoading(-1)
  else:
    backgroundImage = setBackgroundLoading(0) 
  while (not button):
    setFps(clock)
    screen.blit(backgroundImage, (0,0))
    for event in pygame.event.get():
      if event.type == pygame.QUIT:
        pygame.quit()
      elif event.type == pygame.MOUSEMOTION:
        mouse = event.pos
      elif event.type == pygame.MOUSEBUTTONDOWN:
          mouse = event.pos
          if(not config[0]):
            button = buttonNext(mouse)
          else:
            button = buttonInit(mouse)
          if(button):
             return False
          button = buttonHome(mouse)
          if(button):
            return True
    drawMouse(screen, mouse)
    pygame.display.update()


def loading(screen, clock, config):
  home = False
  home = history(screen, clock, config)
  if(not home and config[1]):
    home = tuorial(screen, clock)
  return home


