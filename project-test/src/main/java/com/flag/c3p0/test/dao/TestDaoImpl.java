package com.flag.c3p0.test.dao;

import com.flag.c3p0.test.db.DBConnecter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Authuor Administrator
 * @Create 2016-11-03-15:17
 */
public class TestDaoImpl implements ITestDao {

    @Override
    public String query() {
        String result = null;
        try {
            Connection conn = DBConnecter.getConnection();
            String sql = "SELECT * FROM ys_charging_db.post_report_records";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            if (rs != null){
                result = "OK";
            }
            DBConnecter.close(rs, pstmt, conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
