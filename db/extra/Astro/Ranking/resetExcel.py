from os import system

system('cls')
# import xlsxwriter module
import xlsxwriter
import pandas as pd

# Workbook() takes one, non-optional, argument
# which is the filename that we want to create.
workbook = xlsxwriter.Workbook('Ranking/ranking.xlsx')

# The workbook object is then used to add new
# worksheet via the add_worksheet() method.
worksheet = workbook.add_worksheet()

# Use the worksheet object to write
# data via the write() method.
worksheet.write('A1', 'JOGADOR')
worksheet.write('A2', '---')
worksheet.write('A3', '---')
worksheet.write('A4', '---')

worksheet.write('B1', 'PONTUACAO')
worksheet.write('B2', 000)
worksheet.write('B3', 000)
worksheet.write('B4', 000)

# Finally, close the Excel file
# via the close() method.
workbook.close()

dataframe1 = pd.read_excel('Ranking/ranking.xlsx')
##               linha coluna
print(dataframe1.iat[0,0])
