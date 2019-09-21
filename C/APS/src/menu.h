#ifndef MENU_H
#define MENU_H

#include <string.h>

#define TAM 40

void criarMenuSuperior();
void criarCabecalho(char str[]);
void criarMenuLinhaHorizontal();
void criarItem(char str[]);
void criarMenuRodape();


void criarMenuSuperior()
{
    printf("%c", 201);
    for (int i = 0; i < TAM; i++)
    {
        printf("%c", 205);
    }
    printf("%c\n", 187);
}

void criarCabecalho(char str[])
{
    printf("%c", 186);
    for (int i = strlen(str); i < TAM/2+2; i++)
    {
        printf("%c", 32);
    }
    printf("%s", str);
    for (int i = strlen(str); i < TAM/2+2; i++)
    {
        printf("%c", 32);
    }
    printf("%c\n", 186);
}

void criarMenuLinhaHorizontal()
{
    printf("%c", 204);
    for(int i = 0; i < TAM; i++)
    {
        printf("%c", 205);
    }
    printf("%c\n", 185);
}

void criarItem(char str[])
{
    printf("%c", 186);
    printf("%s", str);
    for (int i = strlen(str); i < TAM; i++)
    {
        printf("%c", 32);
    }
    printf("%c\n", 186);
}

void criarMenuRodape()
{
    printf("%c", 200);
    for (int i = 0; i < TAM; i++)
    {
        printf("%c", 205);
    }
    printf("%c\n", 188);
}

#endif