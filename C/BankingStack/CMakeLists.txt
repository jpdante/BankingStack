cmake_minimum_required(VERSION 3.14)
project(BankingStack C)

set(CMAKE_C_STANDARD 99)

add_executable(BankingStack main.c utils/menu.c utils/menu.h model/client.h model/account.h model/account.c main.h model/client.c utils/convert.c utils/convert.h manager/account_manager.c manager/account_manager.h manager/client_manager.c manager/client_manager.h)