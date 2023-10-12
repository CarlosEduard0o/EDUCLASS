import pygame

def setBackgroundImage(localImage):
	'localImage is the path for the background Image'
	infoScreen = pygame.display.Info()
	imx = infoScreen.current_w
	imy = infoScreen.current_h
	screenInfo = [imx, imy], [1920, 1080]
	backgroundImage = pygame.image.load(localImage).convert_alpha()
	backgroundImage = pygame.transform.scale(backgroundImage,(screenInfo[0][0], screenInfo[0][1]))
	return backgroundImage

def click(mouse, xInit, widht, yInit, height):
    xFinal = xInit + widht
    yFinal = yInit + height
    return (xValue(xInit)) <= mouse[0] <= (xValue(xFinal)) and (
        yValue(yInit)) <= mouse[1] <= (yValue(yFinal))

def text(screen, font, text, position):
    text = font.render(text, True, (250, 250, 250))
    screen.blit(text, (xValue(position[0]), yValue(position[1])))

def xValue(value):
    infoScreen = pygame.display.Info()
    return (infoScreen.current_w * value) / 1920


def yValue(value):
    infoScreen = pygame.display.Info()
    return (infoScreen.current_h * value) / 1080

def setFps(clock):
  clock.tick()

def drawMouse(screen, mouse):
    image_path = "Images\Cursor.png"
    selectImage = pygame.image.load(image_path).convert_alpha()
    selectImage = pygame.transform.scale(selectImage,
                                         (xValue(100), yValue(100)))
    mouse = [int(xValue(mouse[0])-xValue(20)), int(yValue(mouse[1])-yValue(20))]
    screen.blit(selectImage, mouse)
