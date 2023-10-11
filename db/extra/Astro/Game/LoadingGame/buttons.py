from globalFunctions import click

def buttonNext(mouse):
    x_init = 1693
    x_final = 150
    y_init = 910
    y_final = 150
    return click(mouse, x_init, x_final, y_init, y_final)

def buttonInit(mouse):
    x_init = 1525
    x_final = 318
    y_init = 910
    y_final = 150
    return click(mouse, x_init, x_final, y_init, y_final)


def buttonHome(mouse):
    x_init = 73
    x_final = 150
    y_init = 910
    y_final = 150
    return click(mouse, x_init, x_final, y_init, y_final)
