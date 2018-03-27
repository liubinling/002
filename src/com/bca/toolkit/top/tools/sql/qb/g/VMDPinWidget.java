/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bca.toolkit.top.tools.sql.qb.g;

import com.bca.toolkit.top.tools.sql.qb.QbPinBean;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.List;
import org.netbeans.api.visual.anchor.Anchor;
import org.netbeans.api.visual.layout.LayoutFactory;
import org.netbeans.api.visual.model.ObjectState;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;

/**
 * This class represents a pin widget in the VMD visualization style.
 * The pin widget consists of a name and a glyph set.
 *
 * @author David Kaspar
 */
public class VMDPinWidget extends Widget {

    final com.bca.tools.log.Log log = com.bca.tools.log.LogFactory.getLog();
    private VMDColorScheme scheme;
//    private LabelWidget nameWidget;
    private VMDGlyphSetWidget glyphsWidget;
    private VMDNodeAnchor anchor;
    private QbPinBean pinBean;

//    /**
//     * Creates a pin widget.
//     * @param scene the scene
//     */
//    public VMDPinWidget(Scene scene) {
//        this(scene, VMDFactory.getOriginalScheme());
//    }
    /**
     * Creates a pin widget with a specific color scheme.
     * @param scene the scene
     * @param scheme the color scheme
     */
    public VMDPinWidget(Scene scene, QbPinBean pin, VMDColorScheme scheme) {
        super(scene);
        assert scheme != null;
        this.scheme = scheme;
        this.pinBean = pin;

        setLayout(LayoutFactory.createHorizontalFlowLayout(LayoutFactory.SerialAlignment.CENTER, 8));
//        addChild(nameWidget = new LabelWidget(scene));
        addChild(glyphsWidget = new VMDGlyphSetWidget(scene));

        scheme.installUI(this);
        notifyStateChanged(ObjectState.createNormal(), ObjectState.createNormal());
    }

    /**
     * Called to notify about the change of the widget state.
     * @param previousState the previous state
     * @param state the new state
     */
    @Override
    protected void notifyStateChanged(ObjectState previousState, ObjectState state) {
        scheme.updateUI(this, previousState, state);
    }

//    /**
//     * Returns a pin name widget.
//     * @return the pin name widget
//     */
//    public Widget getPinNameWidget() {
//        return nameWidget;
//    }
//
//    /**
//     * Sets a pin name.
//     * @param name the pin name
//     */
//    public void setPinName(String name) {
//        nameWidget.setLabel(name);
//    }
//    /**
//     * Returns a pin name.
//     * @return the pin name
//     */
//    public String getPinName() {
//        return nameWidget.getLabel();
//    }
    /**
     * Sets pin glyphs.
     * @param glyphs the list of images
     */
    public void setGlyphs(List<Image> glyphs) {
        glyphsWidget.setGlyphs(glyphs);
    }

    /**
     * Sets all pin properties at once.
     * @param name the pin name
     * @param glyphs the pin glyphs
     */
    public void setProperties(String name, List<Image> glyphs) {
//        setPinName(name);
        glyphsWidget.setGlyphs(glyphs);
    }

    /**
     * Creates a horizontally oriented anchor similar to VMDNodeWidget.createAnchorPin
     * @return the anchor
     */
    public Anchor createAnchor() {
        if (anchor == null) {
            anchor = new VMDNodeAnchor(this, false);
        }
        return anchor;
    }

    /**
     * @return the pinBean
     */
    public QbPinBean getPinBean() {
        return pinBean;
    }

    @Override
    protected void paintBorder() {
        Rectangle rect = super.getBounds();

        super.getBorder().paint(getGraphics(), rect);
//        if (this.pinBean.getSide() == 'r') {
//            log.debug("pin:%s,%s", pinBean, rect);
//        }
//        super.paintBorder();
    }

    @Override
    protected void paintWidget() {
        super.paintWidget();
    }
}
