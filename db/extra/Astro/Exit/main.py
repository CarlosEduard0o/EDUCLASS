import pygame
from Exit.buttons import continueGame, exitGame
from globalFunctions import drawMouse, setBackgroundImage, setFps

def setBackgroundExit():
  localImage = 'Exit\Images\DesejaSair.png'
  backgroundImage = setBackgroundImage(localImage)
  return backgroundImage

def exit(screen, clock):
	continueButton = False
	backgroundImage = setBackgroundExit()
	while (not continueButton):
		mouse = pygame.mouse.get_pos()
		setFps(clock)
		screen.blit(backgroundImage, (0,0))
		for event in pygame.event.get():
			if event.type == pygame.MOUSEMOTION:
				mouse = event.pos
			elif event.type == pygame.MOUSEBUTTONDOWN:
				mouse = event.pos
				exitGame(mouse)
				continueButton = continueGame(mouse)
		drawMouse(screen, mouse)
		pygame.display.update()