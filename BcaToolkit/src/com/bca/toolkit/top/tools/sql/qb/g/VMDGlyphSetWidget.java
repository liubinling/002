/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bca.toolkit.top.tools.sql.qb.g;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import org.netbeans.api.visual.layout.LayoutFactory;
import org.netbeans.api.visual.widget.ImageWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;

/**
 * This widget represents a list of glyphs rendered horizontally one after another. A glyph is a small image - usually 16x16px.
 *
 * @author David Kaspar
 */
public class VMDGlyphSetWidget extends Widget {

    /**
     * Creates a glyph set widget.
     * @param scene the scene
     */
    public VMDGlyphSetWidget (Scene scene) {
        super (scene);
        setLayout (LayoutFactory.createHorizontalFlowLayout ());
    }

    /**
     * Sets glyphs as a list of images.
     * @param glyphs the list of images used as glyphs
     */
    public void setGlyphs (List<Image> glyphs) {
        List<Widget> children = new ArrayList<Widget> (getChildren ());
        for (Widget widget : children)
            removeChild (widget);
        if (glyphs != null)
            for (Image glyph : glyphs) {
                ImageWidget imageWidget = new ImageWidget (getScene ());
                imageWidget.setImage (glyph);
                addChild (imageWidget);
            }
    }

}
