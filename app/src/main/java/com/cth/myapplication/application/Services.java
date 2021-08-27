package com.cth.myapplication.application;

import com.cth.myapplication.persistence.CoursePersistence;
import com.cth.myapplication.persistence.SemesterPersistence;
import com.cth.myapplication.persistence.Stub.CoursePersistenceStub;
import com.cth.myapplication.persistence.Stub.SemesterPersistenceStub;
import com.cth.myapplication.persistence.hsqldb.CoursePersistenceHSQLDB;
import com.cth.myapplication.persistence.hsqldb.SemesterPersistenceHSQLDB;

public class Services {
    private static CoursePersistence coursePersistence = null;
    private static SemesterPersistence semesterPersistence = null;

    public static synchronized CoursePersistence getCoursePersistence() {
        if (coursePersistence == null) {
            //coursePersistence = new CoursePersistenceStub();
            coursePersistence = new CoursePersistenceHSQLDB(Main.getDBPathName());
        }
        return coursePersistence;
    }

    public static synchronized SemesterPersistence getSemesterPersistence() {
        if (semesterPersistence == null) {
            //semesterPersistence = new SemesterPersistenceStub();
            semesterPersistence = new SemesterPersistenceHSQLDB(Main.getDBPathName());
        }
        return semesterPersistence;
    }
}
