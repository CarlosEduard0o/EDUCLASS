import pygame

from globalFunctions import text
import random


def verifyHandleTime(anterior, atual):
    igual = False
    if (anterior != atual):
        igual = True
    return igual

def showTime(screen, time):
    font = pygame.font.SysFont("arial", 60)
    pontuacaoPosition = [922, 0]
    text(screen, font, str(time), pontuacaoPosition)


def calcLifes(screen, lifes, hightImage, lowImage):
    if (lifes == 3):
      screen.blit(hightImage, (246, 0))
      screen.blit(hightImage, (306, 0))
      screen.blit(hightImage, (366, 0))
    if (lifes == 2):
        screen.blit(hightImage, (246, 0))
        screen.blit(hightImage, (306, 0))
        screen.blit(lowImage, (366, 0))
    if (lifes == 1):
        screen.blit(hightImage, (246, 0))
        screen.blit(lowImage, (306, 0))
        screen.blit(lowImage, (366, 0))

def showPoints(screen, points):
    font = pygame.font.SysFont("arial", 60)
    pontuacaoPosition = [1686, 0]
    text(screen, font, str(points), pontuacaoPosition)

def setExpression(random, difficult):
    equation = random
    resposta = ""
    if (difficult == 0 or difficult == 1):
      if (equation == 0):
          resposta = "+"
      elif (equation == 1):
          resposta = "+"
    else:
      if (equation == 0):
          resposta = "+"
      elif (equation == 1):
          resposta = "-"
    return resposta

def equationExpression(x, expressao):
    resposta = -404
    if (expressao == "+"):
        resposta = x[0] + x[1]
    elif (expressao == "-"):
        resposta = x[0] - x[1]
    return resposta

def setQuestion(x, res, difficult, expressao):
    rangeMinQuestion = 0
    rangeMaxQuestion = 10
    if (difficult == 0):
        rangeMinQuestion = 0
        rangeMaxQuestion = 10
    elif (difficult == 1):
        rangeMinQuestion = 0
        rangeMaxQuestion = 100
    elif (difficult == 2):
        rangeMinQuestion = 0
        rangeMaxQuestion = 100
    resposta = 0
    x[0] = random.randint(rangeMinQuestion, rangeMaxQuestion)
    x[1] = random.randint(rangeMinQuestion, rangeMaxQuestion)
    while (x[1] >= x[0]):
        x[0] = random.randint(rangeMinQuestion, rangeMaxQuestion)
        x[1] = random.randint(rangeMinQuestion, rangeMaxQuestion)
    correta = random.randint(0, 3)
    resposta = equationExpression(x, expressao)
    i = 0
    while (i <= 3):
        if (i == correta):
            res[i] = resposta
        else:
            igual = True
            while (igual):
                resAtual = random.randint(resposta - 5, resposta + 5)
                while (resAtual < 0):
                   resAtual = random.randint(resposta - 5, resposta + 5)
                if (resAtual == resposta):
                    igual = True
                else:
                    igual = False
                    res[i] = resAtual
        i = i + 1
    return correta

def showQuestion(x, res, screen, expressao):
    font = pygame.font.SysFont("arial", 60)
    position = [1472, 453]
    question = str(x[0]) + expressao + str(x[1])
    text(screen, font, question, position)

    p1 = [1587, 571]
    p2 = [1587, 716]
    p3 = [1587, 847]
    p4 = [1587, 969]

    text(screen, font, str(res[0]), p1)
    text(screen, font, str(res[1]), p2)
    text(screen, font, str(res[2]), p3)
    text(screen, font, str(res[3]), p4)
