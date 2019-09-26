cmake_minimum_required(VERSION 3.14)
project(BankingStack C)

set(CMAKE_C_STANDARD 99)

add_executable(BankingStack Main.c Utils/MenuUtils.c Utils/MenuUtils.h Model/Client.h Model/Account.h)