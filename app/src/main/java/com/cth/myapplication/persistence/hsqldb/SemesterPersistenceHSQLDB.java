package com.cth.myapplication.persistence.hsqldb;
import com.cth.myapplication.objects.Semester;
import com.cth.myapplication.persistence.SemesterPersistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SemesterPersistenceHSQLDB implements SemesterPersistence {

    private final String dbPath;

    public SemesterPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }//CoursePersistenceHSQLDB Database

    private Connection connection() throws SQLException {
        System.out.printf("dtpath is %s\n", dbPath);
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }//connection

    private Semester fromResultSet(final ResultSet rs) throws SQLException{
        final String semesterName = rs.getString("semesterName");
        return new Semester(semesterName);
    }//fromResultSet

    //Method that return the all the semester in the database
    @Override
    public List<Semester> getSemesterSequential() {
        final List<Semester> semesters = new ArrayList<>();
        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM semesters");
            while (rs.next()) {
                final Semester semester = fromResultSet(rs);
                semesters.add(semester);
            }//while
            rs.close();
            st.close();
            return semesters;
        }//try
        catch (final SQLException e) {
            throw new PersistenceException(e);
        }//catch
    }//getCourseSequential

    //Check that semestername (primary key) value already exist and if not add the semester
    @Override
    public Semester insertSemester(Semester semester) {
        try (final Connection c = connection()) {
            final PreparedStatement pstmt = c.prepareStatement("SELECT * FROM semesters WHERE semestername = ?");
            pstmt.setString(1,semester.getSemesterName());
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                return fromResultSet(rs);
            }else{
                final PreparedStatement st = c.prepareStatement("INSERT INTO semesters VALUES(?)");
                st.setString(1, semester.getSemesterName());
                st.executeUpdate();
                return semester;
            }
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }//catch
    }//insertSemester

    @Override
    public void deleteSemester(Semester semester) {
        try (final Connection c = connection()) {
            final PreparedStatement sc = c.prepareStatement("DELETE FROM semesters WHERE semestername = ?");
            sc.setString(1, semester.getSemesterName());
            sc.executeUpdate();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }//catch
    }//deleteSemester
}//SemesterPersistenceHSQLDB
