/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jme3.macaq.bridge;

import com.jme3.gde.core.sceneexplorer.SceneExplorerTopComponent;
import com.jme3.gde.scenecomposer.SceneComposerTopComponent;
import java.util.ArrayList;
import java.util.List;
import org.openide.nodes.Node;

/**
 *
 * @author Rickard <neph1 @ github>
 */
public class SceneComposerBridge {
    
    private SceneComposerTopComponent sceneComposer;
    private SceneExplorerTopComponent sceneExplorer;
    
    public SceneComposerBridge(){
        init();
    }
    
    public final void init(){
        this.sceneExplorer = SceneExplorerTopComponent.findInstance();
    }
    
    public String getSelectedSpatialName(){
        String name = null;
        if(sceneExplorer == null){
            init();
        }
        if(sceneExplorer != null && sceneExplorer.getActivatedNodes().length > 0){
            name = sceneExplorer.getActivatedNodes()[0].getName();
//            name = sceneExplorer.getExplorerManager().getSelectedNodes()[0].getName();
            System.out.println("Name " + name);
        }
        
        return name;
    }
    
    public String[] getAllSpatialNames(){
        String[] nameArray = new String[0];
        if(sceneExplorer == null){
            sceneExplorer = SceneExplorerTopComponent.findInstance();
        }
        if(sceneExplorer != null){
            Node root = sceneExplorer.getExplorerManager().getRootContext();
            if(root != null){
                List<String> names = getNodeNames(new ArrayList<>(), root);
    //            name = sceneExplorer.getExplorerManager().getSelectedNodes()[0].getName();
                nameArray = names.toArray(nameArray);
            }
        }
        return nameArray;
    }
    
    private List<String> getNodeNames(List<String> names, Node node){
        if(!node.getName().equals("Mesh")){
            names.add(node.getName());
        }
        if(!node.isLeaf()){
            for(Node n: node.getChildren().getNodes()){
                getNodeNames(names, n);
            }
        }
        return names;
    }
    
    public boolean isActive(){
        return sceneExplorer != null;
    }
}
