package com.cth.myapplication.persistence.hsqldb;

import com.cth.myapplication.objects.Course;
import com.cth.myapplication.objects.Semester;
import com.cth.myapplication.objects.Term;
import com.cth.myapplication.persistence.CoursePersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CoursePersistenceHSQLDB implements CoursePersistence {
    private final String dbPath;

    public CoursePersistenceHSQLDB(final String dbPath){ this.dbPath = dbPath; }//CoursePersistenceHSQLDB Database

    private Connection connection() throws SQLException {
        System.out.printf("dtpath is %s\n",dbPath);
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", ""); }//connection

    private Course fromResultSet(final ResultSet rs) throws SQLException{
        final String courseCode = rs.getString("courseCode");
        final String courseName = rs.getString("courseName");
        final String department = rs.getString("department");
        final boolean lab = rs.getBoolean("lab");
        final int term = rs.getInt("Term");
        final int credit = rs.getInt("credit");
        final String semesterName = rs.getString("semesterName");
        return new Course(courseCode,courseName,department,lab,Term.getTerm(term),credit,new Semester(semesterName));
    }//fromResultSet

    @Override
    public List<Course> getCourseSequential() {
        final List<Course> courses = new ArrayList<>();
        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM courses");
            while (rs.next()) {
                final Course course = fromResultSet(rs);
                courses.add(course);
            }//while
            rs.close();
            st.close();
            return courses;
        }//try
        catch (final SQLException e) {
            throw new PersistenceException(e);
        }//catch
    }//getCourseSequential

    @Override
    public List<Course> getTermCourse(Term term) {
        final List<Course> courses = new ArrayList<>();
        int TermValue = term.getValue();
        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM courses");
            while (rs.next()) {
                final Course course = fromResultSet(rs);
                if(course.checkTerm(term)){
                    courses.add(course);
                }//if
            }//while
            rs.close();
            st.close();
            return courses;
        }//try
        catch (final SQLException e) {
            throw new PersistenceException(e);
        }//catch
    }//getTermCourse

    @Override
    public List<Course> getSemesterNTermCourse(Term term,Semester semester){
        final List<Course> courses = new ArrayList<>();
        int TermValue = term.getValue();
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM courses WHERE semestername = ?");
            st.setString(1, semester.getSemesterName());
            final ResultSet rs = st.executeQuery();
            while (rs.next()) {
                final Course course = fromResultSet(rs);
                if(course.checkTerm(term)){
                    courses.add(course);
                }//if
            }//while
            rs.close();
            st.close();
            return courses;
        }//try
        catch (final SQLException e) {
            throw new PersistenceException(e);
        }//catch
    }//getTermCourse

    @Override
    public List<Course> getDepartmentCourse(String department) {
        final List<Course> courses = new ArrayList<>();
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM courses WHERE department= ?");
            st.setString(1, department);
            final ResultSet rs = st.executeQuery();
            while (rs.next()) {
                final Course course = fromResultSet(rs);
                courses.add(course);
            }//while
            rs.close();
            st.close();
            return courses;
        }//try
        catch (final SQLException e) {
            throw new PersistenceException(e);
        }//catch
    }//getDepartmentCourse

    @Override
    public List<Course> getSemesterCourse(Semester semester) {
        final List<Course> courses = new ArrayList<>();
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM courses WHERE semestername = ?");
            st.setString(1, semester.getSemesterName());
            final ResultSet rs = st.executeQuery();
            while (rs.next()) {
                final Course course = fromResultSet(rs);
                courses.add(course);
            }//while
            rs.close();
            st.close();
            return courses;
        }//try
        catch (final SQLException e) {
            throw new PersistenceException(e);
        }//catch
    }//getSemesterCourse

    @Override
    public Course updateCourse(Course currentCourse,Semester semester) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("UPDATE courses SET SEMESTERNAME = ? WHERE COURSECODE = ?");
            st.setString(1, semester.getSemesterName());
            st.setString(2, currentCourse.getCourseCode());
            st.executeUpdate();
            return currentCourse;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }//catch
    }//updateCourse

}//CoursePersistenceHSQLDB
