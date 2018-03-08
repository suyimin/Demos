cmake_minimum_required(VERSION 3.4.1)

# now build app's shared lib
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11 -Wall")

add_library(hello SHARED
            hellojni.c)

# Include libraries needed for hello lib
target_link_libraries(hello
                      android
                      log)
