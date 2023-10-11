import pandas as pd
import xlsxwriter
from os import system

from globalFunctions import text
system('cls')
# import xlsxwriter module


def writeExcelFile(name, value):
    workbook = xlsxwriter.Workbook('Ranking/ranking.xlsx')
    dataframe1 = pd.read_excel('Ranking/ranking.xlsx')
    a2 = dataframe1.iat[0, 0]
    a3 = dataframe1.iat[1, 0]
    a4 = dataframe1.iat[2, 0]
    b2 = dataframe1.iat[0, 1]
    b3 = dataframe1.iat[1, 1]
    b4 = dataframe1.iat[2, 1]
    worksheet = workbook.add_worksheet()
    if (value > b2):
        worksheet.write('A1', 'JOGADOR')
        worksheet.write('A2', name)
        worksheet.write('A3', a2)
        worksheet.write('A4', a3)
        worksheet.write('B1', 'PONTUACAO')
        worksheet.write('B2', value)
        worksheet.write('B3', b2)
        worksheet.write('B4', b3)

    elif (value > b3):
        worksheet.write('A1', 'JOGADOR')
        worksheet.write('A2', a2)
        worksheet.write('A3', name)
        worksheet.write('A4', a3)
        worksheet.write('B1', 'PONTUACAO')
        worksheet.write('B2', b2)
        worksheet.write('B3', value)
        worksheet.write('B4', b3)
    elif (value >= b4):
        worksheet.write('A1', 'JOGADOR')
        worksheet.write('A2', a2)
        worksheet.write('A3', a3)
        worksheet.write('A4', name)
        worksheet.write('B1', 'PONTUACAO')
        worksheet.write('B2', b2)
        worksheet.write('B3', b3)
        worksheet.write('B4', value)
    else:
        worksheet.write('A1', 'JOGADOR')
        worksheet.write('A2', a2)
        worksheet.write('A3', a3)
        worksheet.write('A4', a4)
        worksheet.write('B1', 'PONTUACAO')
        worksheet.write('B2', b2)
        worksheet.write('B3', b3)
        worksheet.write('B4', b4)
    workbook.close()


def insetText(screen, font):
    dataframe1 = pd.read_excel('Ranking/ranking.xlsx')
    a2 = dataframe1.iat[0, 0]
    a3 = dataframe1.iat[1, 0]
    a4 = dataframe1.iat[2, 0]
    b2 = dataframe1.iat[0, 1]
    b3 = dataframe1.iat[1, 1]
    b4 = dataframe1.iat[2, 1]
    text(screen, font, str(a2), (871, 554))
    text(screen, font, str(a3), (871, 714))
    text(screen, font, str(a4), (871, 863))
    text(screen, font, str(b2), (1304, 554))
    text(screen, font, str(b3), (1304, 714))
    text(screen, font, str(b4), (1304, 863))
