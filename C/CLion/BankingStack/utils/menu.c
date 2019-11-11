#include "menu.h"
#include <string.h>
#include <stdio.h>

const char HeadLeftChar   = 201;
const char HeadRightChar  = 187;
const char HorizontalChar = 205;
const char VerticalChar   = 186;
const char FootLeftChar   = 200;
const char FootRightChar  = 188;

void menuPrintWindowLeft(char *header, char **content, int size, char *footer) {
    int max = menuGetMaxSize(header, content, size, footer);
    menuPrintHeader(header, max);
    for (int i = 0; i < size; i++) {
        menuPrintItemLeft(content[i], max);
    }
    menuPrintFooter(footer, max);
}

void menuPrintWindowCenter(char *header, char **content, int size, char *footer) {
    int max = menuGetMaxSize(header, content, size, footer);
    menuPrintHeader(header, max);
    for (int i = 0; i < size; i++) {
        menuPrintItemCenter(content[i], max);
    }
    menuPrintFooter(footer, max);
}

void menuPrintWindowRight(char *header, char **content, int size, char *footer) {
    int max = menuGetMaxSize(header, content, size, footer);
    menuPrintHeader(header, max);
    for (int i = 0; i < size; i++) {
        menuPrintItemRight(content[i], max);
    }
    menuPrintFooter(footer, max);
}

int menuGetMaxSize(char *header, char **content, int size, char *footer) {
    int max = 0;
    if(header && strlen(header) > max) max = strlen(header);
    if(footer && strlen(footer) > max) max = strlen(footer);
    for (int i = 0; i < size; i++) {
        if(strlen(content[i]) > max) {
            max = strlen(content[i]);
        }
    }
    return max + 2;
}

void menuPrintHeader(char *header, int windowSize) {
    if(header && strlen(header) > 0) {
        printf("%c", HeadLeftChar);
        int size = (windowSize - strlen(header)) / 2;
        for (int i = 0; i < size; i++) {
            printf("%c", HorizontalChar);
        }
        printf(" %s ", header);
        for (int i = 0; i < windowSize - (size + strlen(header)); i++) {
            printf("%c", HorizontalChar);
        }
        printf("%c\n", HeadRightChar);
    } else {
        printf("%c", HeadLeftChar);
        for (int i = 0; i < windowSize + 2; i++) {
            printf("%c", HorizontalChar);
        }
        printf("%c\n", HeadRightChar);
    }
}

void menuPrintItemCenter(char *content, int windowSize) {
    printf("%c", VerticalChar);
    int size = (windowSize - strlen(content)) / 2;
    for (int i = 0; i < size; i++) {
        printf(" ");
    }
    printf(" %s ", content);
    for (int i = 0; i < windowSize - (size + strlen(content)); i++) {
        printf(" ");
    }
    printf("%c\n", VerticalChar);
}

void menuPrintItemRight(char *content, int windowSize) {
    printf("%c", VerticalChar);
    for (int i = 0; i < (windowSize - strlen(content)) + 1; i++) {
        printf(" ");
    }
    printf("%s ", content);
    printf("%c\n", VerticalChar);
}

void menuPrintItemLeft(char *content, int windowSize) {
    printf("%c", VerticalChar);
    printf(" %s", content);
    for (int i = 0; i < windowSize - strlen(content) + 1; i++) {
        printf(" ");
    }
    printf("%c\n", VerticalChar);
}


void menuPrintFooter(char *footer, int windowSize) {
    if(footer && strlen(footer) > 0) {
        printf("%c", FootLeftChar);
        int size = (windowSize - strlen(footer)) / 2;
        for (int i = 0; i < size; i++) {
            printf("%c", HorizontalChar);
        }
        printf(" %s ", footer);
        for (int i = 0; i < windowSize - (size + strlen(footer)); i++) {
            printf("%c", HorizontalChar);
        }
        printf("%c\n", FootRightChar);
    } else {
        printf("%c", FootLeftChar);
        for (int i = 0; i < windowSize + 2; i++) {
            printf("%c", HorizontalChar);
        }
        printf("%c\n", FootRightChar);
    }
}