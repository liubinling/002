/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bca.toolkit.top.tools.sql.qb.g;

import com.bca.toolkit.top.tools.sql.qb.g.VMDColorScheme;
import org.netbeans.api.visual.model.ObjectState;
import org.netbeans.api.visual.router.Router;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.Scene;



/**
 * This class represents a connection widget in the VMD visualization style. Can be combined with any other widget.
 *
 * @author David Kaspar
 */
public class VMDConnectionWidget extends ConnectionWidget {

    private VMDColorScheme scheme;

    /**
     * Creates a connection widget with a specific router.
     * @param scene the scene
     * @param router the router
     */
    public VMDConnectionWidget (Scene scene, Router router) {
        this (scene, VMDFactory.getOriginalScheme ());
        if (router != null)
            setRouter (router);
    }

    /**
     * Creates a connection widget with a specific color scheme.
     * @param scene the scene
     * @param scheme the color scheme
     */
    public VMDConnectionWidget (Scene scene, VMDColorScheme scheme) {
        super (scene);
        assert scheme != null;
        this.scheme = scheme;
        scheme.installUI (this);
        setState (ObjectState.createNormal ());
    }

    /**
     * Implements the widget-state specific look of the widget.
     * @param previousState the previous state
     * @param state the new state
     */
    @Override
    public void notifyStateChanged (ObjectState previousState, ObjectState state) {
        scheme.updateUI (this, previousState, state);
    }

}
