from globalFunctions import xValue, yValue
import random

def calcShowPlanets(allCoordinates):
    screen = [int(xValue(1408)), int(yValue(1080))]
    all = [[-500, -500], [-500, -500], [-500, -500], [-500, -500]]
    i = 0
    while (i <= 3):
        VerifyLocation(all, screen, i)
        i = i + 1
    allCoordinates[0] = all[0]
    allCoordinates[1] = all[1]
    allCoordinates[2] = all[2]
    allCoordinates[3] = all[3]

def VerifyLocation(all, screen, i):
    igual = True
    while (igual):
        if (i == 0):
            atual = randomPlanets(screen)
            all[i] = atual
            igual = False

        if (i == 1):
            atual = randomPlanets(screen)
            all[i] = atual
            spaceTerra = getSpacePlanet(all[0])
            igual = compareLocalPlanets(atual, spaceTerra)
        if (i == 2):
            atual = randomPlanets(screen)
            all[i] = atual
            spaceTerra = getSpacePlanet(all[0])
            spaceLua = getSpacePlanet(all[1])
            igual = compareLocalPlanets(atual, spaceTerra)
            if (igual == False):
                igual = compareLocalPlanets(atual, spaceLua)
        if (i == 3):
            atual = randomPlanets(screen)
            all[i] = atual
            spaceTerra = getSpacePlanet(all[0])
            spaceLua = getSpacePlanet(all[1])
            spaceSat = getSpacePlanet(all[2])
            igual = compareLocalPlanets(atual, spaceTerra)
            if (igual == False):
                igual = compareLocalPlanets(atual, spaceLua)
                if (igual == False):
                    igual = compareLocalPlanets(atual, spaceSat)


def randomPlanets(screen):
    return [
        random.randint(0, screen[0] - int(xValue(100))),
        random.randint(60, screen[1] - int(yValue(100)))
    ]

def compareLocalPlanets(random, space):
    atual = getSpacePlanet(random)

    atualXRandomEqual = atual[0][1] >= space[0][0] or atual[0][0] <= space[0][1]
    atualyRandomEqual = atual[1][0] <= space[1][1] and atual[1][1] >= space[1][
        0]
    igual = atualXRandomEqual and atualyRandomEqual
    return igual


def getSpacePlanet(planet):
    value = [[int(xValue(planet[0])),
              int(xValue(planet[0] + (xValue(100))))],
             [int(yValue(planet[1])),
              int(yValue(planet[1] + (yValue(100))))]]
    return value



def clickInPlanets(allCoordinates, mouse):
    i = 0
    clique = False
    while (i <= 3):
        atual = getSpacePlanet(allCoordinates[i])
        atualXRandomEqual = mouse[0] >= atual[0][0] and mouse[0] <= atual[0][1]
        atualyRandomEqual = mouse[1] >= atual[1][0] and mouse[1] <= atual[1][1]
        if (not clique):
            clique = atualXRandomEqual and atualyRandomEqual
        i = i + 1
    return clique



def getPlanetClickmouse(allCoordinates, mouse):
    i = 0
    clique = False
    while (i <= 3):
        atual = getSpacePlanet(allCoordinates[i])
        atualXRandomEqual = mouse[0] >= atual[0][0] and mouse[0] <= atual[0][1]
        atualyRandomEqual = mouse[1] >= atual[1][0] and mouse[1] <= atual[1][1]
        if (not clique):
            clique = atualXRandomEqual and atualyRandomEqual
            if (atualXRandomEqual and atualyRandomEqual):
                return i
        i = i + 1
