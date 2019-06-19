package com.demo;

import java.sql.*;

/**
 * @Authot : lwg
 * @Date : Created in 15:18 2019/6/19
 */
public class Hello {

    public static void main(String[] args) throws Exception {
        //1.注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2.获取数据库连接
        String url = "jdbc:mysql://localhost:3306/demo1";
        String user = "root";
        String password = "123456";
        Connection conn = DriverManager.getConnection(url, user, password);
        //3.获取执行者对象
        Statement stat = conn.createStatement();
        //调用更新数据的方法
        //update(stat);
        //调用删除数据的方法
        //delete(stat);
        //调用增加数据的方法
        //insert(stat);
        //调用查询数据的方法
        select(stat);
        //6.释放资源
        stat.close();
        conn.close();
    }

    /*
     * 使用JDBC技术,查询数据库中表的数据
     */
    private static void select(Statement stat) throws Exception {
        //拼接sql语句
        String sql = "SELECT * FROM student";
        /*
         * 4.执行sql语句
         * 使用Statement中的方法
         * ResultSet executeQuery(String sql) 执行给定的 SQL 语句，该语句返回单个 ResultSet 对象。
         * 返回值ResultSet标准接口的实现类对象,实现类对象由mysql驱动提供,可以使用ResultSet接口接收
         */
        ResultSet rs = stat.executeQuery(sql);
        System.out.println(rs);//com.mysql.jdbc.JDBC4ResultSet@1acb189
        /*
         * 5.处理结果
         * ResultSet中有一个方法
         * boolean next() 将光标从当前位置向前移一行。
         * 如果新的当前行有效，则返回 true；如果不存在下一行，则返回 false
         * 如果有结果集返回true,若果没有结果集返回false
         * 相当于迭代器中的hasNext方法
         */
        while(rs.next()){
            /*
             * next返回true,有结果集
             * 取出结果集
             * 使用ResultSet中的方法getXXX(参数);
             * 参数:
             *     int columnIndex:列所在的索引,从1开始
             *     String columnLabel:列名
             * 注意:
             *     如果使用getInt,getDouble指定数据类型的方法,返回值就是对应的数据类型
             *     如果使用getObject方法返回值是object类型(只是打印可用)
             * 如果使用getString方法返回值是String类型
             */
            /*int i1 = rs.getInt(1);
            String s2 = rs.getString(2);
            System.out.println(i1+"\t"+s2);*/

            //System.out.println(rs.getObject(1)+"\t"+rs.getObject(2));
            System.out.println(rs.getObject("id")+"\t"+rs.getObject("name") + "\t" +rs.getObject("age"));
            //5.释放资源
           // rs.close();
        }
    }

    /*
     * 使用JDBC技术,对数据库中的表数据进行增加
     */
    private static void insert(Statement stat) throws SQLException {
        //拼接sql语句
        String sql = "INSERT INTO category(cname) VALUES('玩具')";
        //4.执行sql语句
        int row = stat.executeUpdate(sql);
        //5.处理结果
        if(row>0){
            System.out.println("增加数据成功!");
        }else{
            System.out.println("增加数据失败!");
        }

    }

    /*
     * 使用JDBC技术,对数据库中的表数据进行删除
     */
    private static void delete(Statement stat) throws Exception {
        //拼接sql语句
        String sql = "DELETE FROM category WHERE cid=5";
        //4.执行sql语句
        int row = stat.executeUpdate(sql);
        //5.处理结果
        if(row>0){
            System.out.println("删除数据成功!");
        }else{
            System.out.println("删除数据失败!");
        }
    }

    /*
     * 使用JDBC技术,对数据库中的表数据进行更新
     */
    private static void update(Statement stat) throws Exception {
        //拼接sql语句
        String sql = "UPDATE category SET cname='鞋帽' WHERE cid=6";
        //4.执行sql语句
        int row = stat.executeUpdate(sql);
        //5.处理结果
        if(row>0){
            System.out.println("更新数据成功!");
        }else{
            System.out.println("更新数据失败!");
        }
    }
}
