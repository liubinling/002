/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bca.toolkit.top.tools.sql.qb.g;

import com.bca.toolkit.top.tools.sql.qb.g.VMDColorScheme;
import java.awt.Color;
import org.netbeans.api.visual.border.Border;


/**
 * Used as a factory class for objects defined in VMD visualization style.
 *
 * @author David Kaspar
 */
public final class VMDFactory {

    private static VMDColorScheme SCHEME_ORIGINAL = new VMDOriginalColorScheme ();
    private static VMDColorScheme SCHEME_NB60 = new VMDNetBeans60ColorScheme ();

    private VMDFactory () {
    }

    /**
     * Creates the original vmd color scheme. Used by default.
     * @return the color scheme
     * @since 2.5
     */
    public static VMDColorScheme getOriginalScheme () {
        return SCHEME_ORIGINAL;
    }

    /**
     * Creates the NetBeans 6.0 vmd color scheme.
     * @return the color scheme
     * @since 2.5
     */
    public static VMDColorScheme getNetBeans60Scheme () {
        return SCHEME_NB60;
    }

    /**
     * Creates a border used by VMD node.
     * @return the VMD node border
     */
    public static Border createVMDNodeBorder () {
        return VMDOriginalColorScheme.BORDER_NODE;
    }

    /**
     * Creates a border used by VMD node with a specific colors.
     * @return the VMD node border
     * @param borderColor the border color
     * @param borderThickness the border thickness
     * @param color1 1. color of gradient background
     * @param color2 2. color of gradient background
     * @param color3 3. color of gradient background
     * @param color4 4. color of gradient background
     * @param color5 5. color of gradient background
     * @since 2.5
     */
    public static Border createVMDNodeBorder (Color borderColor, int borderThickness, Color color1, Color color2, Color color3, Color color4, Color color5) {

        return new VMDNodeBorder (borderColor, borderThickness, color1, color2, color3, color4, color5);
    }

}
