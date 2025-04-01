 package com.rensis.data;

import com.rensis.models.Exercici;
import com.rensis.models.Usuari;
import com.rensis.models.Workout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author Mike
 */
public class DataAccess {

    private static Connection getConnection() {
        Connection connection = null;
        Properties properties = new Properties();
        try {
            //properties.load(DataAccess.class.getClassLoader().getResourceAsStream("properties/application.properties"));
            //connection = DriverManager.getConnection(properties.getProperty("connectionUrl"));
            
            //Keeping simulapdb name of the database for an easier revision
            String connectionUrl = "jdbc:sqlserver://simulapsqlserver.database.windows.net:1433;database=simulapdb25;user=simulapdbadmin@simulapsqlserver;password=Pwd1234.;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

            connection = DriverManager.getConnection(connectionUrl);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static Usuari getUser(String email) {
        Usuari user = null;
        String sql = "SELECT * FROM Usuaris WHERE Email = ?";
        try (Connection connection = getConnection(); PreparedStatement selectStatement = connection.prepareStatement(sql);) {
            selectStatement.setString(1, email);
            ResultSet resultSet = selectStatement.executeQuery();
            user = new Usuari();
            while (resultSet.next()) {
                
                user.setId(resultSet.getInt("Id"));
                user.setNom(resultSet.getString("Nom"));
                user.setEmail(resultSet.getString("Email"));
                user.setPasswordHash(resultSet.getString("PasswordHash"));
                user.setInstructor(resultSet.getBoolean("Instructor"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return user;
    }

    public static ArrayList<Usuari> getAllUsers() {
        ArrayList<Usuari> usuaris = new ArrayList<>();
        String sql = "SELECT * FROM Usuaris WHERE Instructor=0";
        try (Connection connection = getConnection(); PreparedStatement selectStatement = connection.prepareStatement(sql);) {

            ResultSet resultSet = selectStatement.executeQuery();

            while (resultSet.next()) {
                Usuari user = new Usuari();
                user.setId(resultSet.getInt("Id"));
                user.setNom(resultSet.getString("Nom"));
                user.setEmail(resultSet.getString("Email"));
                user.setPasswordHash(resultSet.getString("PasswordHash"));
                user.setInstructor(resultSet.getBoolean("Instructor"));
                usuaris.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuaris;
    }

    public static ArrayList<Usuari> getAllUsersByInstructor(int idInstructor) {
        ArrayList<Usuari> usuaris = new ArrayList<>();
        String sql = "SELECT * FROM Usuaris WHERE AssignedInstructor=?";
        try (Connection connection = getConnection(); PreparedStatement selectStatement = connection.prepareStatement(sql);) {
            selectStatement.setInt(1, idInstructor);
            ResultSet resultSet = selectStatement.executeQuery();

            while (resultSet.next()) {
                Usuari user = new Usuari();
                user.setId(resultSet.getInt("Id"));
                user.setNom(resultSet.getString("Nom"));
                user.setEmail(resultSet.getString("Email"));
                user.setPasswordHash(resultSet.getString("PasswordHash"));
                user.setInstructor(resultSet.getBoolean("Instructor"));
                usuaris.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuaris;
    }
    
        public static ArrayList<Workout> getAllWorkouts() {
        ArrayList<Workout> workouts = new ArrayList<>();
        String sql = "SELECT * FROM Workouts";
        try (Connection connection = getConnection(); PreparedStatement selectStatement = connection.prepareStatement(sql);) {

            ResultSet resultSet = selectStatement.executeQuery();

            while (resultSet.next()) {
                Workout workout = new Workout();
                workout.setId(resultSet.getInt("Id"));
                workout.setComments(resultSet.getString("Comments"));
                workout.setForDate(resultSet.getString("ForDate"));
                workout.setIdUsuari(resultSet.getInt("UserId"));
                workouts.add(workout);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return workouts;
    }

    public static ArrayList<Workout> getWorkoutsPerUser(Usuari user) {
        ArrayList<Workout> workouts = new ArrayList<>();
        String sql = "SELECT Workouts.Id, Workouts.ForDate, Workouts.UserId, Workouts.Comments"
                + " FROM Workouts"
                + " WHERE Workouts.UserId=?"
                + " ORDER BY Workouts.ForDate";
        try (Connection connection = getConnection(); PreparedStatement selectStatement = connection.prepareStatement(sql);) {
            selectStatement.setInt(1, user.getId());
            ResultSet resultSet = selectStatement.executeQuery();

            while (resultSet.next()) {
                Workout workout = new Workout();
                workout.setId(resultSet.getInt("Id"));
                workout.setForDate(resultSet.getString("ForDate"));
                workout.setIdUsuari(resultSet.getInt("UserId"));
                workout.setComments(resultSet.getString("Comments"));

                workouts.add(workout);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return workouts;

    }

    public static ArrayList<Exercici> getExercicisPerWorkout(Workout workout) {
        ArrayList<Exercici> exercicis = new ArrayList<>();
        String sql = "SELECT ExercicisWorkouts.IdExercici,"
                + " Exercicis.NomExercici, Exercicis.Descripcio, Exercicis.DemoFoto"
                + " FROM ExercicisWorkouts INNER JOIN Exercicis ON ExercicisWorkouts.IdExercici=Exercicis.Id"
                + " WHERE ExercicisWorkouts.IdWorkout=?";
        try (Connection connection = getConnection(); PreparedStatement selectStatement = connection.prepareStatement(sql);) {
            selectStatement.setInt(1, workout.getId());
            ResultSet resultSet = selectStatement.executeQuery();

            while (resultSet.next()) {
                Exercici exercici = new Exercici();
                exercici.setId(resultSet.getInt("IdExercici"));
                exercici.setNomExercici(resultSet.getString("NomExercici"));
                exercici.setDescripcio(resultSet.getString("Descripcio"));
                exercici.setDemoFoto(resultSet.getString("DemoFoto"));

                exercicis.add(exercici);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exercicis;
    }

    public static ArrayList<Exercici> getAllExercicis() {
        ArrayList<Exercici> exercicis = new ArrayList<>();
        String sql = "SELECT Id, Exercicis.NomExercici, Exercicis.Descripcio, Exercicis.DemoFoto"
                + " FROM Exercicis";
        try (Connection connection = getConnection(); PreparedStatement selectStatement = connection.prepareStatement(sql);) {

            ResultSet resultSet = selectStatement.executeQuery();

            while (resultSet.next()) {
                Exercici exercici = new Exercici();
                exercici.setId(resultSet.getInt("Id"));
                exercici.setNomExercici(resultSet.getString("NomExercici"));
                exercici.setDescripcio(resultSet.getString("Descripcio"));
                exercici.setDemoFoto(resultSet.getString("DemoFoto"));

                exercicis.add(exercici);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exercicis;
    }

    public static int registerUser(Usuari u) {
        String sql = "INSERT INTO dbo.Usuaris (Nom, Email, PasswordHash, Instructor)"
                + " VALUES (?,?,?,?)"
                + " SELECT CAST(SCOPE_IDENTITY() as int)";
        try (Connection conn = getConnection(); PreparedStatement insertStatement = conn.prepareStatement(sql)) {
            insertStatement.setString(1, u.getNom());
            insertStatement.setString(2, u.getEmail());
            insertStatement.setString(3, u.getPasswordHash());
            insertStatement.setBoolean(4, u.isInstructor());

            int newUserId = insertStatement.executeUpdate();
            return newUserId;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void insertWorkout(Workout w, ArrayList<Exercici> exercicis) {
        // The following should be done in a SQL transaction
        int newWorkoutId = insertToWorkoutTable(w);
        insertExercisesPerWorkout(newWorkoutId, exercicis);
    }

    private static int insertToWorkoutTable(Workout w) {
        String sql = "INSERT INTO dbo.Workouts (ForDate, UserId, Comments)"
                + " VALUES (?,?,?)";
        try (Connection conn = getConnection();
                PreparedStatement insertStatement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                ) {
            insertStatement.setString(1, w.getForDate());
            insertStatement.setInt(2, w.getIdUsuari());
            insertStatement.setString(3, w.getComments());

            int affectedRows = insertStatement.executeUpdate();
            
            if (affectedRows > 0) {
                // Retrieve the generated keys (identity value)
                ResultSet resultSet = insertStatement.getGeneratedKeys();

                // Check if a key was generated
                if (resultSet.next()) {
                    // Get the last inserted identity value
                    int lastInsertedId = resultSet.getInt(1);
                    return lastInsertedId;
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static int insertExercisesPerWorkout(int wId, ArrayList<Exercici> exercicis) {
        for(Exercici e: exercicis) {
            int rowsAffected = insertExerciciPerWorkout(wId, e);
            if (rowsAffected != 1) {
                return 0;
            }
        }
        return exercicis.size();
    }

    private static int insertExerciciPerWorkout(int wId, Exercici e) {
        String sql = "INSERT INTO dbo.ExercicisWorkouts (IdWorkout, IdExercici)"
                + " VALUES (?,?)";
        try (Connection conn = getConnection(); PreparedStatement insertStatement = conn.prepareStatement(sql)) {
            insertStatement.setInt(1, wId);
            insertStatement.setInt(2, e.getId());
            int rowsAffected = insertStatement.executeUpdate();
            return rowsAffected;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    
    // Method to delete workout (chatGPT helped)
    public static void deleteWorkout(int workoutId) {
        // Comenzamos una transacción para asegurar la integridad de los datos
        Connection conn = null;
        PreparedStatement deleteExercisesStmt = null;
        PreparedStatement deleteWorkoutStmt = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);  // Disable auto-commit to do a transaction

            // First delete exercises associated to our workout
            String deleteExercisesSql = "DELETE FROM dbo.ExercicisWorkouts WHERE IdWorkout = ?";
            deleteExercisesStmt = conn.prepareStatement(deleteExercisesSql);
            deleteExercisesStmt.setInt(1, workoutId);
            deleteExercisesStmt.executeUpdate();

            // Then delete the workout
            String deleteWorkoutSql = "DELETE FROM dbo.Workouts WHERE Id = ?";
            deleteWorkoutStmt = conn.prepareStatement(deleteWorkoutSql);
            deleteWorkoutStmt.setInt(1, workoutId);
            int rowsAffected = deleteWorkoutStmt.executeUpdate();

            // If deleted correctly confirm transaction
            if (rowsAffected > 0) {
                conn.commit();
            } else {
                conn.rollback();  // If something failed undo transaction
            }
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();  // In error case do a rollback
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            try {
                if (deleteExercisesStmt != null) deleteExercisesStmt.close();
                if (deleteWorkoutStmt != null) deleteWorkoutStmt.close();
                if (conn != null) conn.setAutoCommit(true); 
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void updateWorkout(Workout w, ArrayList<Exercici> newExercises) {

        // Verificar si la lista de ejercicios está vacía
        if (newExercises == null || newExercises.isEmpty()) {
            throw new IllegalArgumentException("La lista de ejercicios no puede estar vacía.");
        }

        Connection conn = null;
        PreparedStatement updateWorkoutStmt = null;
        PreparedStatement deleteOldExercisesStmt = null;
        PreparedStatement insertNewExercisesStmt = null;

        try {
            // Get database connection
            conn = getConnection();
            conn.setAutoCommit(false); // Disable autocommit to check the transaction manually

            // 1. Update workout comments
            String updateWorkoutSql = "UPDATE dbo.Workouts SET Comments = ? WHERE Id = ?";
            updateWorkoutStmt = conn.prepareStatement(updateWorkoutSql);
            updateWorkoutStmt.setString(1, w.getComments());
            updateWorkoutStmt.setInt(2, w.getId());
            int workoutUpdateResult = updateWorkoutStmt.executeUpdate();

            // Check the update
            if (workoutUpdateResult != 1) {
                conn.rollback();
                throw new SQLException("Error al actualizar los comentarios del workout.");
            }

            // 2. Check if there are associated exercises
            String checkAssociatedExercisesSql = "SELECT COUNT(*) FROM dbo.ExercicisWorkouts WHERE IdWorkout = ?";
            PreparedStatement checkAssociatedExercisesStmt = conn.prepareStatement(checkAssociatedExercisesSql);
            checkAssociatedExercisesStmt.setInt(1, w.getId());
            ResultSet rs = checkAssociatedExercisesStmt.executeQuery();

            // Check if there are associated exercises
            if (rs.next()) {
                int count = rs.getInt(1); // Get the number of associated exercises
                if (count > 0) {
                    // If there are exercises, delete them
                    String deleteOldExercisesSql = "DELETE FROM dbo.ExercicisWorkouts WHERE IdWorkout = ?";
                    deleteOldExercisesStmt = conn.prepareStatement(deleteOldExercisesSql);
                    deleteOldExercisesStmt.setInt(1, w.getId());
                    int exercisesDeleted = deleteOldExercisesStmt.executeUpdate();

                    // Check if the deletion was successful
                    if (exercisesDeleted < 1) {
                        conn.rollback();
                        throw new SQLException("Error deleting old exercises.");
                    }
                }
                // If there are no exercises, do nothing
            } else {
                conn.rollback();
                throw new SQLException("Error checking associated exercises.");
            }

            // 3. Insert new exercises to the database
            String insertNewExercisesSql = "INSERT INTO dbo.ExercicisWorkouts (IdWorkout, IdExercici) VALUES (?, ?)";
            insertNewExercisesStmt = conn.prepareStatement(insertNewExercisesSql);

            for (Exercici e : newExercises) {
                insertNewExercisesStmt.setInt(1, w.getId());
                insertNewExercisesStmt.setInt(2, e.getId());
                insertNewExercisesStmt.addBatch(); // Añadir cada insert a un batch
            }

            int[] results = insertNewExercisesStmt.executeBatch();

            // Check all exercises 
            int exercisesInserted = 0;
            for (int result : results) {
                if (result == PreparedStatement.SUCCESS_NO_INFO || result > 0) {
                    exercisesInserted++;
                }
            }

            if (exercisesInserted != newExercises.size()) {
                conn.rollback();
                throw new SQLException("Error al insertar los nuevos ejercicios.");
            }

            // Commit if all good
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback(); // In error case undo the transaction
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        } finally {
            try {
                if (updateWorkoutStmt != null) updateWorkoutStmt.close();
                if (deleteOldExercisesStmt != null) deleteOldExercisesStmt.close();
                if (insertNewExercisesStmt != null) insertNewExercisesStmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
