#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import mysql.connector

def main():
    conn=mysql.connector.connect(host='127.0.0.1',user='root', password='yuliang',port=3306)
    cursor=conn.cursor()
    print(cursor)
    cursor.execute("create database if NOT EXISTS bbs")
    cursor.execute('use bbs')
    cursor.execute('create table if not exists user('
                   '_id int PRIMARY KEY auto_increment,'
                   '_name VARCHAR(10),'
                   '_password VARCHAR(16))')
    print('create user table')

    cursor.execute('create table if not exists topic('
                   '_id int PRIMARY KEY auto_increment,'
                   '_title text,'
                   '_content text,'
                   '_poster text,'
                   '_time long)')
    print('create post table')

    cursor.execute('create table if not exists comment('
                   '_id int PRIMARY KEY auto_increment,'
                   '_content text,'
                   '_commenter text,'
                   '_topicid int,'
                   '_time long)')
    print('create comment table')


    cursor.close()
    conn.close()

if __name__ == '__main__':
    main()
