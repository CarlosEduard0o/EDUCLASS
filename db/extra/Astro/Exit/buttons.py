import pygame
from globalFunctions import click

def exitGame(mouse):
    x_init = 501
    x_final = 250
    y_init = 771
    y_final = 200
    if (click(mouse, x_init, x_final, y_init, y_final)):
      pygame.quit()

def continueGame(mouse):
    x_init = 1169
    x_final = 250
    y_init = 771
    y_final = 200
    return click(mouse, x_init, x_final, y_init, y_final)

