from random import random
import pygame
import datetime
from Game.Game.planets import calcShowPlanets, clickInPlanets, getPlanetClickmouse
from Game.Game.utils import calcLifes, setExpression, setQuestion, showPoints, showQuestion, showTime, verifyHandleTime
from Game.PauseGame.main import pauseGame
from globalFunctions import setBackgroundImage, setFps, xValue, yValue
import random


def game(screen, config, clock):
  cursor = [0, 0]
  mouseAnt = [0, 0]
  mouse = pygame.mouse.get_pos()

  planetsCoordinates = [[-500, -500],[-500, -500],[-500, -500],[-500, -500]]

  pontuacao = 0
  correta = 0
  response = 0

  x = [0, 0]
  res = [0, 0, 0, 0]
  difficult = config[2]

  lose = False

  timer = datetime.datetime.now()
  timeAnt = timer.second
  timeAtual = timer.second
  difficultTime = 0

  time = 50

  backgroundImage = setBackgroundGame()
  p0 = drawPlanets(0)
  p1 = drawPlanets(1)
  p2 = drawPlanets(2)
  p3 = drawPlanets(3)
  calcShowPlanets(planetsCoordinates)
  lifeLow = drawLifeLow()
  lifeHigh = drawLifeHigh()

  lifes = 3

  expressao = setExpression(random.randint(0, 1), difficult)
  correta = setQuestion(x, res, difficult, expressao)

  returnMenu = False

  while (not lose and not returnMenu):
    if(lifes <= 0 or time < 0):
      break
    mouse = pygame.mouse.get_pos()
    timer = datetime.datetime.now()
    timeAtual = timer.second
    timeEqual = verifyHandleTime(timeAnt, timeAtual)
    if (timeEqual):
      time = time - 1
    timeAnt = timeAtual

    setFps(clock)
    screen.blit(backgroundImage, (0,0))

    for event in pygame.event.get():
      if event.type == pygame.QUIT:
        pygame.quit()
      elif event.type == pygame.MOUSEMOTION:
        mouse = event.pos
      elif event.type == pygame.MOUSEBUTTONDOWN:
        if (clickInPlanets(planetsCoordinates, cursor)):
          response = getPlanetClickmouse(planetsCoordinates, cursor)
          pontuacao = pontuacao + verifyResponse(correta, response, difficult)

          difficultTime = setDificult(pontuacao)
          time = setTimedifficult(difficultTime)
          if (verifyResponse(correta, response, difficult) == 0):
            lifes = lifes-1

          expressao = setExpression(random.randint(0, 1), difficult)
          correta = setQuestion(x, res, difficult, expressao)
          calcShowPlanets(planetsCoordinates)
      elif event.type == pygame.KEYDOWN:
        if event.key == pygame.K_ESCAPE:
          returnMenu  = pauseGame(screen, clock)
          if(returnMenu):
            break

    calcLifes(screen, lifes, lifeHigh, lifeLow)
    showTime(screen, time)
    showPoints(screen, pontuacao)
    showQuestion(x, res, screen, expressao)
    insertPlanetsInScreen(screen, p0,p1,p2,p3,planetsCoordinates)
    cursor = drawPoint(screen, mouse, mouseAnt, cursor)
    mouseAnt = mouse

    pygame.display.update()
  return pontuacao

def insertPlanetsInScreen(screen, p0,p1,p2,p3,planetsCoordinates):
  screen.blit(p0, planetsCoordinates[0])
  screen.blit(p1, planetsCoordinates[1])
  screen.blit(p2, planetsCoordinates[2])
  screen.blit(p3, planetsCoordinates[3])

def setBackgroundGame():
  localImage = 'Game/Game/Images/background.png'
  backgroundImage = setBackgroundImage(localImage)
  return backgroundImage

def drawPlanets(i):
  localImage = 'Game/Game/Images/p'+str(i)+'.png'
  image = pygame.image.load(localImage).convert_alpha()
  image =  pygame.transform.scale(image,(xValue(100), yValue(100)))
  return image

def drawLifeLow():
  imagePathLow = 'Game/Game/Images/Low.png'
  imagePathLow = pygame.image.load(imagePathLow).convert_alpha()
  imagePathLow =  pygame.transform.scale(imagePathLow,(xValue(60), yValue(60)))
  return imagePathLow

def drawLifeHigh():
  imagePathHigh = 'Game/Game/Images/High.png'
  imagePathHigh = pygame.image.load(imagePathHigh).convert_alpha()
  imagePathHigh =  pygame.transform.scale(imagePathHigh,(xValue(60), yValue(60)))
  return imagePathHigh

def drawPoint(screen, mouse, mouseAnt, cursor):
  info_screen = pygame.display.Info()
  imx = info_screen.current_w
  imy = info_screen.current_h
  screen_i = [imx, imy], [1920, 1080]
  dif = [mouse[0] - mouseAnt[0], mouse[1] - mouseAnt[1]]
  tamanho = 1408
  width = (screen_i[0][0] * tamanho) / screen_i[1][0]
  if (cursor[0] + dif[0] >= (width)):
      cursor = [width, cursor[1] + dif[1]]
  elif (cursor[0] + dif[0] <= 0):
      cursor = [0, cursor[1] + dif[1]]
  else:
      cursor = [cursor[0] + dif[0], cursor[1] + dif[1]]
  color = (255, 0, 0)
  pygame.draw.circle(screen, color, cursor, 10, 10)
  return cursor



def verifyResponse(correta, response, dificult):
    if (correta == response):
      if(dificult == 0):
          return 1
      elif (dificult == 1):
          return 2
      elif (dificult == 2):
          return 3
    else:
        return 0

def setTimedifficult(difficult):
    time = 50
    if (difficult == 0):
        time = 50
    if (difficult == 1):
        time = 40
    if (difficult == 2):
        time = 30
    if (difficult == 3):
        time = 20
    if (difficult == 4):
        time = 10
    return time

def setDificult(pontuacao):
    dificuldade = 0
    if (pontuacao < 5):
        dificuldade = 0
    if (pontuacao >= 5):
        dificuldade = 1
    if (pontuacao >= 10):
        dificuldade = 2
    if (pontuacao >= 15):
        dificuldade = 3
    if (pontuacao > 20):
        dificuldade = 4
    return dificuldade
