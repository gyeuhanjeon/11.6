package com.ISOUR.ISOUR.common;

import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;


public class Common {
    // 오라클 설정 정보 (JDBC 연결)
    final static String ORACLE_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    final static String ORACLE_ID = "ISOUR";
    final static String ORACLE_PW = "ULTRA";
    final static String ORACLE_DRV = "oracle.jdbc.driver.OracleDriver";

    public static Connection getConnection() {
        Connection conn = null;

        try {
            Class.forName(ORACLE_DRV); // 드라이버 로딩
            conn = DriverManager.getConnection(ORACLE_URL, ORACLE_ID, ORACLE_PW);
            System.out.println("Connection 연결 성공");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }

    public static void close(Connection conn) {
        try {
            if(conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Connection 해제 성공");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void close(Statement stmt) {
        try {
            if(stmt != null && !stmt.isClosed()) {
                stmt.close();
                System.out.println("Statement 해제 성공");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void close(ResultSet rset) {
        try {
            if(rset != null && !rset.isClosed()) {
                rset.close();
                System.out.println("Result set 해제 성공");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 서블릿으로 부터 입력 받은 내용을 문자열로 변환
    public static StringBuffer reqStringBuff(HttpServletRequest req) {
        StringBuffer sb = new StringBuffer();
        String line = null;

        try {
            BufferedReader reader = req.getReader(); // 개행문자를 만나기 전까지 읽음
            while((line = reader.readLine()) != null) sb.append(line);
        } catch (Exception e) {}

        return sb;
    }

    // JSON Object 만들기
    public static JSONObject getJsonObj(StringBuffer sb) {
        JSONParser parser = new JSONParser();
        JSONObject jsonObj = null;

        try {
            jsonObj = (JSONObject)parser.parse(sb.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return jsonObj;
    }
}