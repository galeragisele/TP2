/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import persona.Alumno;

/**
 *
 * @author gisele.galera
 */
public class AlumnoModel extends AbstractTableModel {
    
    List<Alumno> alumnos = new ArrayList<>();
    
    private static final String[] encabezados = {
        "DNI", "NOMBRE", "APELLIDO", "FECHANAC"
    };

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
        refrescarModelo();
    }

    @Override
    public int getRowCount() {
        if (alumnos !=null){
            return alumnos.size();
        }
        return 0;
    }
        

    @Override
    public int getColumnCount() {
        return encabezados.length; // cantidad de columnas
        
        
    }

    @Override
    public String getColumnName(int col) {
        return encabezados[col];
    }

    @Override // devuelve el valor de una fila y columna
    public Object getValueAt(int row, int col) {
        Alumno alu = alumnos.get(row); // devuelve un alumno
        
        switch (col) {
            case 0: // DNI
                return alu.getDni();
            
            case 1: // nombre
                return alu.getNombre();
                
            case 2: // apellido
                return alu.getApellido();
            
            case 3: // fecha nacimiento
                return alu.getFechaNac();
        }
        return null;
    }
    
    public void refrescarModelo() {
        fireTableDataChanged();
    }
}
