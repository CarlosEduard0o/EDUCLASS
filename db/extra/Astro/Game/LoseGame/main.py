import pygame
from Game.LoseGame.buttons import okButton
from Ranking.excel import writeExcelFile
from globalFunctions import drawMouse, setBackgroundImage, setFps, text


def setBackgroundLose():
  localImage = 'Game\LoseGame\Images\Background.png'
  backgroundImage = setBackgroundImage(localImage)
  return backgroundImage

def loseGame(screen, clock, pontuacao):
  font = pygame.font.SysFont("arial", 100)
  userText = ''

  backgroundImage = setBackgroundLose()
  ok = False
  while (not ok):
    pygame.mouse.set_visible(False)
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
          ok = okButton(mouse)
      elif event.type == pygame.KEYDOWN:
          if event.key == pygame.K_BACKSPACE:
            userText = userText[:-1]
          else:
            if(len(userText) < 3):
              userText += event.unicode
              userText= userText.upper()
    drawMouse(screen, mouse)
    text(screen, font, userText, (700,870))
    text(screen, font, str(pontuacao), (840,550))
    pygame.display.update()
  writeExcelFile(userText, pontuacao)

