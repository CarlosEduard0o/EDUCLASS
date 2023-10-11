from globalFunctions import click

def continueToGame(mouse):
    x_init = 709
    x_final = 500
    y_init = 450
    y_final = 200
    if (click(mouse, x_init, x_final, y_init, y_final)):
      return True

def returnToMenu(mouse):
    x_init = 709
    x_final = 500
    y_init = 735
    y_final = 200
    if (click(mouse, x_init, x_final, y_init, y_final)):
      return True
