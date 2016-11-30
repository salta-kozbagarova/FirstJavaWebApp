package com.mysoft;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by www on 28.11.2016.
 */
public class DAO {

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root",null);
    }

    /**
     *
     * static data for posts
     * used before connecting database to the project
     */
    /*static List<Post> posts;
    static {
        posts=new ArrayList<Post>();
        posts.add(new Post(1,"Hello"));
        posts.add(new Post(2,"world"));
        posts.add(new Post(3,"people"));
    }*/

    public static List<Post> getPosts() throws SQLException, ClassNotFoundException {
        try(Connection c = getConnection();
        PreparedStatement ps = c.prepareStatement("SELECT id, txt FROM posts");
        ResultSet resultSet = ps.executeQuery();) {

            ArrayList<Post> posts = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String txt = resultSet.getString(2);
                posts.add(new Post(id, txt));
            }
            return posts;
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println(getPosts());
    }

    /*public static void deletePost(int id){
        Post d=null;
        for (Post p:posts){
            if(p.id==id){
                d=p;
            }
        }
        if(d!=null){
            posts.remove(d);
        }
    }*/

    public static void deletePost(int id) throws SQLException, ClassNotFoundException {
        try(Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement("DELETE FROM posts WHERE id=?");
            ) {
            ps.setInt(1,id);
            ps.executeUpdate();
        }
    }

    /*public static void addPost(String txt){
        posts.add(new Post(posts.size(),txt));
    }*/

    public static void addPost(String txt) throws SQLException, ClassNotFoundException {
        try(Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement("INSERT INTO posts(txt) VALUES (?)");
            ) {
            ps.setString(1,txt);
            ps.executeUpdate();
        }
    }
}
