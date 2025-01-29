/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.rensis.rensisfit;

import com.rensis.data.DataAccess;
import com.rensis.models.Exercici;
import com.rensis.models.Usuari;
import com.rensis.models.Workout;
import com.rensis.styles.*;
import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author Wilde
 */
public class WorkoutEditForm extends javax.swing.JDialog {

    private MainScreen mainScreen;
    private ArrayList<Exercici> allExercises = null;
    private Usuari user;
    private JXDatePicker picker;
    private ArrayList<Exercici> selectedExercises = new ArrayList<>();
    // Idea get from https://stackoverflow.com/questions/11736878/display-calendar-to-pick-a-date-in-java and https://docs.oracle.com/javase/10/docs/api/java/text/SimpleDateFormat.html
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSSS");
    
    private Workout selectedWorkout = null;
    private String action = "";

    /**
     * Constructor for creating a new workout.
     */
    public WorkoutEditForm(java.awt.Frame parent, boolean modal, Usuari user) {
        super(parent, modal);
        this.allExercises = DataAccess.getAllExercicis();
        this.user = user;
        this.mainScreen = (MainScreen) parent;

        setUndecorated(true);
        initComponents();
        
        setLocationRelativeTo(mainScreen);
        
        // Initialize combo box with exercises
        initComboBox();

        // Initialize date picker
        initDatePicker();
        
        //Styling
        editPanel.setBorder(UIStyles.DEFAULT_BORDER_2PX);
        uploadPhotoPanel.setBorder(UIStyles.DEFAULT_BORDER_2PX);
        saveButton.setBorder(UIStyles.DEFAULT_BORDER_2PX_BUTTON);
        exitButton.setBorder(UIStyles.DEFAULT_BORDER_2PX_BUTTON);
        removePhotoButton.setBorder(UIStyles.DEFAULT_BORDER_2PX_BUTTON);
        addExerciseButton.setBorder(UIStyles.DEFAULT_BORDER_2PX_BUTTON);
        removeExerciseButton.setBorder(UIStyles.DEFAULT_BORDER_2PX_BUTTON);
        deleteButton.setBorder(UIStyles.DEFAULT_BORDER_2PX_BUTTON);
        picker.setBorder(UIStyles.DEFAULT_BORDER_2PX);
        workoutName.setBorder(UIStyles.DEFAULT_BORDER_2PX);
        exerciseListScrollPane.setBorder(UIStyles.DEFAULT_BORDER_2PX);
        exerciseSelector.setBorder(UIStyles.DEFAULT_BORDER_2PX);
        addExsitingWorkout.setBorder(UIStyles.DEFAULT_BORDER_2PX);
        
        // Hide delete button by default for new workout
        deleteButton.setVisible(false);
    }
    
    /**
    * Constructor for editing an existing workout.
    */
    public WorkoutEditForm(java.awt.Frame parent, boolean modal, Usuari user, Workout workout) {
        super(parent, modal);
        this.allExercises = DataAccess.getAllExercicis();
        this.user = user;
        this.mainScreen = (MainScreen) parent;
        this.selectedWorkout = workout;

        // Load selected exercises for editing
        selectedExercises = DataAccess.getExercicisPerWorkout(workout);
        action = "edit";

        setUndecorated(true);
        initComponents();
        setLocationRelativeTo(mainScreen);

        // Initialize combo box with exercises
        initComboBox();

        // Initialize date picker and set workout date
        initDatePicker();
        
        addExsitingWorkout.setVisible(false);
        
        workoutName.setText(workout.getComments());
        
        try {
            picker.setDate(dateFormatter.parse(workout.getForDate()));
        } catch (ParseException e) {
            picker.setDate(Calendar.getInstance().getTime());
        }
        
        //Styling
        editPanel.setBorder(UIStyles.DEFAULT_BORDER_2PX);
        uploadPhotoPanel.setBorder(UIStyles.DEFAULT_BORDER_2PX);
        saveButton.setBorder(UIStyles.DEFAULT_BORDER_2PX);
        exitButton.setBorder(UIStyles.DEFAULT_BORDER_2PX);
        removePhotoButton.setBorder(UIStyles.DEFAULT_BORDER_2PX);
        addExerciseButton.setBorder(UIStyles.DEFAULT_BORDER_2PX);
        removeExerciseButton.setBorder(UIStyles.DEFAULT_BORDER_2PX);
        deleteButton.setBorder(UIStyles.DEFAULT_BORDER_2PX);
        picker.setBorder(UIStyles.DEFAULT_BORDER_2PX);
        workoutName.setBorder(UIStyles.DEFAULT_BORDER_2PX);
        exerciseListScrollPane.setBorder(UIStyles.DEFAULT_BORDER_2PX);
        exerciseSelector.setBorder(UIStyles.DEFAULT_BORDER_2PX);
        loadSelectedExercises();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        editPanel = new javax.swing.JPanel();
        saveButton = new RoundedButton("");
        exitButton = new RoundedButton("");
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        uploadPhotoPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        removePhotoButton = new RoundedButton("");
        exerciseSelector = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        scrollPaneDateFor = new javax.swing.JScrollPane();
        dateForPanel = new javax.swing.JPanel();
        addExerciseButton = new RoundedButton("");
        jLabel6 = new javax.swing.JLabel();
        workoutName = new RoundedTextField(1);
        exerciseListScrollPane = new javax.swing.JScrollPane();
        exerciseListPanel = new javax.swing.JPanel();
        removeExerciseButton = new RoundedButton("");
        deleteButton = new RoundedButton("");
        addExsitingWorkout = new RoundedButton("");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        editPanel.setBackground(new java.awt.Color(230, 230, 230));
        editPanel.setFont(new java.awt.Font("Poppins Medium", 0, 24)); // NOI18N
        editPanel.setMaximumSize(new java.awt.Dimension(898, 328));
        editPanel.setMinimumSize(new java.awt.Dimension(898, 328));
        editPanel.setPreferredSize(new java.awt.Dimension(898, 328));

        saveButton.setBackground(new java.awt.Color(254, 194, 209));
        saveButton.setFont(UIStyles.getFont(18));
        saveButton.setForeground(new java.awt.Color(0, 0, 0));
        saveButton.setText("Save Workout");
        saveButton.setBorder(null);
        saveButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        saveButton.setFocusable(false);
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        exitButton.setBackground(new java.awt.Color(254, 194, 209));
        exitButton.setFont(UIStyles.getFont(18));
        exitButton.setForeground(new java.awt.Color(0, 0, 0));
        exitButton.setText("Exit");
        exitButton.setBorder(null);
        exitButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exitButton.setFocusable(false);
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(UIStyles.getFont(24));
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Name");

        jLabel2.setFont(UIStyles.getFont(36));
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Adding workout");

        uploadPhotoPanel.setBackground(new java.awt.Color(130, 130, 130));
        uploadPhotoPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        uploadPhotoPanel.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        uploadPhotoPanel.setMinimumSize(new java.awt.Dimension(80, 16));

        jLabel4.setFont(UIStyles.getFont(18));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Click to upload");

        javax.swing.GroupLayout uploadPhotoPanelLayout = new javax.swing.GroupLayout(uploadPhotoPanel);
        uploadPhotoPanel.setLayout(uploadPhotoPanelLayout);
        uploadPhotoPanelLayout.setHorizontalGroup(
            uploadPhotoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
            .addGroup(uploadPhotoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(uploadPhotoPanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel4)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        uploadPhotoPanelLayout.setVerticalGroup(
            uploadPhotoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
            .addGroup(uploadPhotoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(uploadPhotoPanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel4)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jLabel3.setFont(UIStyles.getFont(24));
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Photo");

        removePhotoButton.setBackground(new java.awt.Color(254, 194, 209));
        removePhotoButton.setFont(UIStyles.getFont(18));
        removePhotoButton.setForeground(new java.awt.Color(0, 0, 0));
        removePhotoButton.setText("Remove");
        removePhotoButton.setBorder(null);
        removePhotoButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        removePhotoButton.setFocusable(false);

        exerciseSelector.setFont(UIStyles.getFont(18));

        jLabel5.setFont(UIStyles.getFont(24));
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Exercises List");

        scrollPaneDateFor.setBorder(null);
        scrollPaneDateFor.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneDateFor.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        dateForPanel.setBackground(new java.awt.Color(230, 230, 230));
        dateForPanel.setLayout(new java.awt.GridLayout(DataAccess.getAllExercicis().size(), 1));
        scrollPaneDateFor.setViewportView(dateForPanel);

        addExerciseButton.setBackground(new java.awt.Color(254, 194, 209));
        addExerciseButton.setFont(UIStyles.getFont(18));
        addExerciseButton.setForeground(new java.awt.Color(0, 0, 0));
        addExerciseButton.setText("Add");
        addExerciseButton.setBorder(null);
        addExerciseButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addExerciseButton.setFocusable(false);
        addExerciseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addExerciseButtonActionPerformed(evt);
            }
        });

        jLabel6.setFont(UIStyles.getFont(24));
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("For date");

        workoutName.setFont(UIStyles.getFont(18));
        workoutName.setBorder(null);

        exerciseListScrollPane.setBorder(null);

        exerciseListPanel.setLayout(new java.awt.GridLayout(allExercises.size(), 1));
        exerciseListScrollPane.setViewportView(exerciseListPanel);

        removeExerciseButton.setBackground(new java.awt.Color(254, 194, 209));
        removeExerciseButton.setFont(UIStyles.getFont(18));
        removeExerciseButton.setForeground(new java.awt.Color(0, 0, 0));
        removeExerciseButton.setText("Remove");
        removeExerciseButton.setBorder(null);
        removeExerciseButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        removeExerciseButton.setFocusable(false);
        removeExerciseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeExerciseButtonActionPerformed(evt);
            }
        });

        deleteButton.setBackground(new java.awt.Color(255, 102, 102));
        deleteButton.setFont(UIStyles.getFont(18));
        deleteButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteButton.setText("Delete Workout");
        deleteButton.setBorder(null);
        deleteButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteButton.setFocusable(false);
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        addExsitingWorkout.setBackground(new java.awt.Color(254, 194, 209));
        addExsitingWorkout.setFont(UIStyles.getFont(18));
        addExsitingWorkout.setForeground(new java.awt.Color(0, 0, 0));
        addExsitingWorkout.setText("Add existing workout");
        addExsitingWorkout.setBorder(null);
        addExsitingWorkout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addExsitingWorkout.setFocusable(false);
        addExsitingWorkout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addExsitingWorkoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout editPanelLayout = new javax.swing.GroupLayout(editPanel);
        editPanel.setLayout(editPanelLayout);
        editPanelLayout.setHorizontalGroup(
            editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editPanelLayout.createSequentialGroup()
                        .addGroup(editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(editPanelLayout.createSequentialGroup()
                                .addComponent(removePhotoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(editPanelLayout.createSequentialGroup()
                                .addComponent(uploadPhotoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(editPanelLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel6)
                                            .addComponent(scrollPaneDateFor, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editPanelLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(workoutName, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5)))
                        .addGap(18, 18, 18))
                    .addGroup(editPanelLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(136, 136, 136)))
                .addGroup(editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editPanelLayout.createSequentialGroup()
                        .addComponent(addExerciseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeExerciseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(exerciseListScrollPane)
                    .addComponent(exerciseSelector, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addExsitingWorkout, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        editPanelLayout.setVerticalGroup(
            editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3))
                    .addComponent(addExsitingWorkout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editPanelLayout.createSequentialGroup()
                        .addGroup(editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(exerciseSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(removeExerciseButton, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                            .addComponent(addExerciseButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exerciseListScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(74, Short.MAX_VALUE))
                    .addGroup(editPanelLayout.createSequentialGroup()
                        .addGroup(editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(editPanelLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(workoutName, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollPaneDateFor, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11))
                            .addComponent(uploadPhotoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(removePhotoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Helper method to initialize the combo box with exercise options
    private void initComboBox() {
        DefaultComboBoxModel model = new DefaultComboBoxModel<>(RensisFit.exerciciArrayListToString(allExercises));
        exerciseSelector.setModel(model);
    }
    
    // Helper method to initialize the date picker
    private void initDatePicker() {
        picker = new JXDatePicker();
        picker.setDate(Calendar.getInstance().getTime());
        picker.setFormats(dateFormatter);
        dateForPanel.add(picker);
    }
    
    // Method to load the exercises already selected for editing
    private void loadSelectedExercises() {
        for (Exercici e : selectedExercises) {
            addExercisePanel(e);
        }
    }
    
    // Method to add an exercise panel to the UI
    private void addExercisePanel(Exercici e) {
        JPanel selectedPanel = new JPanel();
        selectedPanel.setSize(new Dimension(100, 20));
        JLabel selectedPanelLabel = new JLabel(e.getNomExercici(), SwingConstants.CENTER);
        selectedPanelLabel.setFont(UIStyles.getFont(18));
        selectedPanel.add(selectedPanelLabel);
        exerciseListPanel.add(selectedPanel);
        exerciseListPanel.revalidate();
        exerciseListPanel.repaint();
    }
    // Methon when the Save button is clicked for adding or editing a workout
    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // TODO add your handling code here:
        if (action.equals("edit")) {
            // Update existing workout
            selectedWorkout.setComments(workoutName.getText());
            selectedWorkout.setForDate(dateFormatter.format(picker.getDate()));

            // Save changes to the database
            DataAccess.updateWorkout(selectedWorkout, selectedExercises);
        } else {
            // Create new workout
            Workout w = new Workout();
            w.setComments(workoutName.getText());
            w.setIdUsuari(user.getId());
            w.setForDate(dateFormatter.format(picker.getDate()));

            // Insert new workout into the database
            DataAccess.insertWorkout(w, selectedExercises);
        }

        // Close the form after saving
        setVisible(false);
    }//GEN-LAST:event_saveButtonActionPerformed
    
    // Method when the Cancel button is clicked
    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        setVisible(false);
    }//GEN-LAST:event_exitButtonActionPerformed
    
    // Method when the Add Exercise button is clicked
    private void addExerciseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addExerciseButtonActionPerformed
        String selectedExerciseName = (String) exerciseSelector.getSelectedItem();
        for (Exercici e : allExercises) {
            if (e.getNomExercici().equals(selectedExerciseName) && !selectedExercises.contains(e)) {
                selectedExercises.add(e);
                addExercisePanel(e);
            }
        }
    }//GEN-LAST:event_addExerciseButtonActionPerformed

    // Method to remove an exercise from exercises lists
    private void removeExerciseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeExerciseButtonActionPerformed
        String selectedExerciseName = (String) exerciseSelector.getSelectedItem();
        Exercici exerciseToRemove = null;
        JPanel panelToRemove = null;

        for (Exercici e : selectedExercises) {
            // Check if combo box selected option is equal to any option in selected list
            if (e.getNomExercici().equals(selectedExerciseName)) {
                exerciseToRemove = e;
                for (Component c : exerciseListPanel.getComponents()) {
                    // If instance of JPanel
                    if (c instanceof JPanel) {
                        JPanel panel = (JPanel) c;
                        // If instance of JLabel
                        if (panel.getComponent(0) instanceof JLabel) {
                            JLabel label = (JLabel) panel.getComponent(0);
                            if (label.getText().equals(selectedExerciseName)) {
                                // Setting up the panel to remove
                                panelToRemove = panel;
                                break;
                            }
                        }
                    }
                }
                break;
            }
        }

        // If not null remove and repaint panel
        if (panelToRemove != null && exerciseToRemove != null) {
            exerciseListPanel.remove(panelToRemove);
            selectedExercises.remove(exerciseToRemove);
            exerciseListPanel.revalidate();
            exerciseListPanel.repaint();
        }
    }//GEN-LAST:event_removeExerciseButtonActionPerformed
    
    // Method when Delete button clicked to delete a workout
    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // Delete the selected workout from the database
        if (selectedWorkout != null) {
            DataAccess.deleteWorkout(selectedWorkout.getId());
        }
        setVisible(false);
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void addExsitingWorkoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addExsitingWorkoutActionPerformed
        ExistingWorkoutForm form = new ExistingWorkoutForm(this.mainScreen,true,user.getId());
        form.setVisible(true);
    setVisible(false);
    }//GEN-LAST:event_addExsitingWorkoutActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addExerciseButton;
    private javax.swing.JButton addExsitingWorkout;
    private javax.swing.JPanel dateForPanel;
    private javax.swing.JButton deleteButton;
    private javax.swing.JPanel editPanel;
    private javax.swing.JPanel exerciseListPanel;
    private javax.swing.JScrollPane exerciseListScrollPane;
    private javax.swing.JComboBox<String> exerciseSelector;
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JButton removeExerciseButton;
    private javax.swing.JButton removePhotoButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JScrollPane scrollPaneDateFor;
    private javax.swing.JPanel uploadPhotoPanel;
    private javax.swing.JTextField workoutName;
    // End of variables declaration//GEN-END:variables
}
