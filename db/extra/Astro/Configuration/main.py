import pygame
from globalFunctions import click, drawMouse, setBackgroundImage, setFps, xValue, yValue


def setBackgroundConfiguration():
  localImage = 'Configuration\Images\Background.png'
  backgroundImage = setBackgroundImage(localImage)
  return backgroundImage


def pauseScreen(screen, clock, config):
  select = drawSelect()
  backgroundImage = setBackgroundConfiguration()
  stopWhile = True
  while (stopWhile):
    mouse = pygame.mouse.get_pos()
    setFps(clock)
    screen.blit(backgroundImage, (0,0))
    for event in pygame.event.get():
      if event.type == pygame.QUIT:
        pygame.quit()
      if event.type == pygame.KEYDOWN:
         if event.key == pygame.K_ESCAPE:
             stopWhile = False
      elif event.type == pygame.MOUSEMOTION:
        mouse = event.pos
      elif event.type == pygame.MOUSEBUTTONDOWN:
        mouse = event.pos
        tutorialButton(mouse, config)
        musicButton(mouse, config)
        difficultButton(mouse, config)
        stopWhile = returnButton(mouse)
    ShowSelect(screen, config, select)
    drawMouse(screen, mouse)
    pygame.display.update()


def drawSelect():
  localImage = 'Configuration/Images/Select.png'
  image = pygame.image.load(localImage).convert_alpha()
  image =  pygame.transform.scale(image,(xValue(100), yValue(100)))
  return image

def ShowSelect(screen, config, select):
  musicCoordinates= [-500, -500]
  tutorialCoordinates = [-500, -500]
  dificuldadeCoordinate = [-500, -500]

  if(config[0] == True):
    musicCoordinates = [594, 356]
    screen.blit(select, musicCoordinates)

  if (config[1] == True):
    tutorialCoordinates = [594, 540]
    screen.blit(select, tutorialCoordinates)

  if (config[2] == 0):
    dificuldadeCoordinate = [758, 701]
    screen.blit(select, dificuldadeCoordinate)
  elif (config[2] == 1):
    dificuldadeCoordinate = [1081, 701]
    screen.blit(select, dificuldadeCoordinate)
  elif (config[2] == 2):
    dificuldadeCoordinate = [1403, 701]
    screen.blit(select, dificuldadeCoordinate)

def returnButton(mouse):
    x_init = 78
    x_final = 150
    y_init = 75
    y_final = 150
    return not click(mouse, x_init, x_final, y_init, y_final)

def tutorialButton(mouse, configuration):
    x_init = 594
    x_final = 100
    y_init = 540
    y_final = 100
    if (click(mouse, x_init, x_final, y_init, y_final)):
        configuration[1] = not configuration[1]

def musicButton(mouse, configuration):
    x_init = 594
    x_final = 100
    y_init = 356
    y_final = 100
    if (click(mouse, x_init, x_final, y_init, y_final)):
        configuration[0] = not configuration[0]

def difficultButton(mouse, configuration):
    x_init = 758
    x_final = 100
    y_init = 701
    y_final = 100
    if (click(mouse, x_init, x_final, y_init, y_final)):
        configuration[2] = 0
    x_init = 1081
    if (click(mouse, x_init, x_final, y_init, y_final)):
        configuration[2] = 1
    x_init = 1403
    if (click(mouse, x_init, x_final, y_init, y_final)):
        configuration[2] = 2
