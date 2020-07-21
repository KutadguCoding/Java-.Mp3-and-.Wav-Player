/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mplayer;

/**
 *
 * @author m_ugu
 */

import java.util.List;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DnDL implements DropTargetListener {
    
    JLabel fileLabel = new JLabel();
    JTable jtbl = new JTable();
    DefaultTableModel tmodel;
    
    public DnDL (JTable jt, JLabel jl){
        
        jtbl = jt;
        fileLabel = jl;
        tmodel = (DefaultTableModel) jtbl.getModel();
        
    }
    
    @Override
    public void dragEnter(DropTargetDragEvent dtde) {}

    @Override
    public void dragOver(DropTargetDragEvent dtde) {}

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {}

    @Override
    public void dragExit(DropTargetEvent dte) {}

    
    @Override
    public void drop(DropTargetDropEvent ev) {
        
        ev.acceptDrop(DnDConstants.ACTION_COPY);
        Transferable t = ev.getTransferable();
        DataFlavor [] df = t.getTransferDataFlavors();
        
        for (DataFlavor f : df){
            try{
                
                if(f.isFlavorJavaFileListType()){
                List <File> files;   
                files = (List <File>) t.getTransferData(f);
                    files.stream().map((File file) -> {
                        return file;
                    }).forEachOrdered((File file) -> {
                        tmodel.addRow (new String[] {file.getName(), file.getAbsolutePath()});
                    });
                }
            }
            catch(UnsupportedFlavorException | IOException ex){
                JOptionPane.showMessageDialog (null, ex);
            }
        }
    }
}
    
    
    
        
    

