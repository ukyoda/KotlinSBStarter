package com.ukyoda.kotlinStart

import database.UserDynamicSqlSupport
import database.UserMapper
import database.count
import database.selectByPrimaryKey
import org.apache.ibatis.io.Resources
import org.apache.ibatis.session.SqlSessionFactory
import org.apache.ibatis.session.SqlSessionFactoryBuilder
import org.mybatis.dynamic.sql.SqlBuilder

fun createSessionFactory(): SqlSessionFactory {
    val resource = "mybatis-config.xml";
    val inputStream = Resources.getResourceAsStream(resource);
    return SqlSessionFactoryBuilder().build(inputStream);
}

fun main () {
    createSessionFactory().openSession().use {session ->
        val mapper = session.getMapper(UserMapper::class.java);
        val user = mapper.selectByPrimaryKey(100);
        println(user);
    }
    createSessionFactory().openSession().use {session ->
        val mapper = session.getMapper(UserMapper::class.java);
        val count = mapper.count {
            where(UserDynamicSqlSupport.User.age, SqlBuilder.isGreaterThanOrEqualTo(25))
        };
        println(count);
    }
}
