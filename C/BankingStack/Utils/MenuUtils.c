#include "MenuUtils.h"
#include <string.h>
#include <stdio.h>

const char HeadLeftChar   = 201;
const char HeadRightChar  = 187;
const char HorizontalChar = 205;
const char VerticalChar   = 186;
const char FootLeftChar   = 200;
const char FootRightChar  = 188;

void menuPrintWindow(char *title, char **content, int size) {
    int max = menuGetMaxSize(content, size);
    menuPrintHeader(title, max);
    for (int i = 0; i < size; i++) printf("%s\n", content[i]);
}

int menuGetMaxSize(char **content, int size) {
    int max = 0;
    for (int i = 0; i < size; i++) {
        if(strlen(content[i]) > max) {
            max = strlen(content[i]);
        }
    }
    return max;
}

void menuPrintHeader(char *title, int windowSize) {
    printf("%c", HeadLeftChar);
    for (int i = 0; i < windowSize + 2; i++) {
        printf("%c", HorizontalChar);
    }
    printf("%c\n", HeadRightChar);
    printf("%c ", VerticalChar);
    for (int i = 0; i < (windowSize - strlen(title)) / 2; i++) {
        printf(" ");
    }
    printf("%s", title);
    for (int i = 0; i < (windowSize - strlen(title)) / 2; i++) {
        printf(" ");
    }
    printf(" %c\n", VerticalChar);
    printf("%c", FootLeftChar);
    for (int i = 0; i < windowSize + 2; i++) {
        printf("%c", HorizontalChar);
    }
    printf("%c\n", FootRightChar);
}
/*
void menuCreateHorizontalLine() {
    printf("%c", 204);
    for (int i = 0; i < TAM; i++) {
        printf("%c", 205);
    }
    printf("%c\n", 185);
}

void menuCreateItem(char str[]) {
    printf("%c", 186);
    printf("%s", str);
    for (int i = strlen(str); i < TAM; i++) {
        printf("%c", 32);
    }
    printf("%c\n", 186);
}

void menuCreateFoot() {
    printf("%c", 200);
    for (int i = 0; i < TAM; i++) {
        printf("%c", 205);
    }
    printf("%c\n", 188);
}*/