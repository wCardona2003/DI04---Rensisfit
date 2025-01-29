/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.rensis.rensisfit;

import com.rensis.models.Exercici;
import com.rensis.models.Workout;
import java.util.ArrayList;


/**
 *
 * @author Wilde
 */
public class RensisFit {
    

    public static void main(String[] args) {
        //Initialization of our frame
        MainScreen mainScreen = new MainScreen();

        //Setting the location of the frame
        mainScreen.setLocationRelativeTo(null);

        //Setting the visibility of our frame
        mainScreen.setVisible(true);

        //Disabling resizable window
        mainScreen.setResizable(false);
        
    }
    
    public static String[] exerciciArrayListToString(ArrayList<Exercici> exercisesArrayList){
        String[] formattedList = new String[exercisesArrayList.size()+1];
        formattedList[0] = "--Select--";
        int i = 1;
        for(Exercici e : exercisesArrayList){
            formattedList[i] = e.getNomExercici();
            i++;
        }
        return formattedList;
    }
    
    public static String[] workoutsArrayListToString(ArrayList<Workout> workoutsArrayList) {
        // Lista temporal para construir el resultado sin duplicados
        ArrayList<String> tempList = new ArrayList<>();
        tempList.add("--Select--");  // Agregar la opción inicial

        // Recorrer la lista de workouts y agregar solo comentarios únicos
        for (Workout w : workoutsArrayList) {
            String comment = w.getComments();
            if (!tempList.contains(comment)) {  // Verificar si el comentario ya está en tempList
                tempList.add(comment);  // Agregar si no es un duplicado
            }
        }

        // Convertir la lista temporal a un arreglo y retornarlo
        String[] formattedList = tempList.toArray(new String[0]);
        return formattedList;
    }
}
