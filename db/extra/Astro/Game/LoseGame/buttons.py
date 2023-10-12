from globalFunctions import click

def okButton(mouse):
    x_init = 1061
    x_final = 150
    y_init = 844
    y_final = 150
    if (click(mouse, x_init, x_final, y_init, y_final)):
     return True
