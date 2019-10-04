#include "convert.h"
#include <stdio.h>
#include <stdlib.h>

/*
 * SEMPRE LEMBRAR DE LIBERAR A MEMORIA APOS O USO!
 * free(<ponteiro>);
 */

char* intToString(int i) {
    char *str = malloc(sizeof (char) * 12);
    sprintf(str, "%d", i);
    return str;
}

char* intToYesNo(int i) {
    if(i) return "Yes";
    return "No";
}

char* floatToString(double d) {
    char *str = malloc(sizeof (char) * 50);
    sprintf(str, "%f", d);
    return str;
}

char* tDataToString(TDate date) {
    char *str = malloc(sizeof (char) * 10);
    sprintf(str, "%d/%d/%d", date.day, date.month, date.year);
    return str;
}