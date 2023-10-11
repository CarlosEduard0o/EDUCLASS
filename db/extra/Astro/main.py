import pygame
from Menu.main import menu


#Initialize pygame
pygame.init()

#Initialize variables

config = [True, True, 0]

####################################################################

#screen configuration
infoScreen = pygame.display.Info()
imx = infoScreen.current_w
imy = infoScreen.current_h
size = (imx, imy)
screen = pygame.display.set_mode((size))
# Mouse
pygame.mouse.set_visible(False)
mouse = pygame.mouse.get_pos()

# loop game
clock = pygame.time.Clock()
menu(screen, clock, config)
