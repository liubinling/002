/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Sun designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Sun in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Contributor(s):
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2007 Sun
 * Microsystems, Inc. All Rights Reserved.
 *
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 */
package com.bca.toolkit.top.tools.sql.qb.g;

import org.netbeans.api.visual.border.Border;
import org.netbeans.api.visual.border.BorderFactory;
import org.netbeans.api.visual.model.ObjectState;
import org.netbeans.api.visual.widget.Widget;
import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.anchor.AnchorShape;
import org.netbeans.api.visual.anchor.PointShape;
import org.netbeans.api.visual.anchor.PointShapeFactory;
import org.openide.util.Utilities;

import java.awt.*;

/**
 * This class specifies look and feel of vmd widgets. There are predefined schemes in VFactory class.
 *
 * @author David Kaspar
 */
public class VOriginalColorScheme extends VColorScheme {

    static final Color COLOR_NORMAL = new Color (0xBACDF0);
    private static final Color COLOR_HOVERED = Color.BLACK;
    private static final Color COLOR_SELECTED = new Color (0x748CC0);
    static final Color COLOR_HIGHLIGHTED = new Color (0x316AC5);

//    private static final Color COLOR0 = new Color (169, 197, 235);
    static final Color COLOR1 = new Color (221, 235, 246);
    static final Color COLOR2 = new Color (255, 255, 255);
    static final Color COLOR3 = new Color (214, 235, 255);
    static final Color COLOR4 = new Color (241, 249, 253);
    static final Color COLOR5 = new Color (255, 255, 255);

    public static final Border BORDER_NODE = VFactory.createVNodeBorder (COLOR_NORMAL, 1, COLOR1, COLOR2, COLOR3, COLOR4, COLOR5);

    static final Color BORDER_CATEGORY_BACKGROUND = new Color (0xCDDDF8);
    static final Border BORDER_MINIMIZE = BorderFactory.createRoundedBorder (2, 2, null, COLOR_NORMAL);
    static final Border BORDER_PIN = BorderFactory.createOpaqueBorder (2, 8, 2, 8);
    private static final Border BORDER_PIN_HOVERED = BorderFactory.createLineBorder (2, 8, 2, 8, Color.BLACK);

    static final PointShape POINT_SHAPE_IMAGE = PointShapeFactory.createImagePointShape (Utilities.loadImage ("org/netbeans/modules/visual/resources/vmd-pin.png")); // NOI18N

    public VOriginalColorScheme () {
    }

    public void installUI (VNodeWidget widget) {
        widget.setBorder (VFactory.createVNodeBorder ());
        widget.setOpaque (false);

        Widget header = widget.getHeader ();
        header.setBorder (BORDER_PIN);
        header.setBackground (COLOR_SELECTED);
        header.setOpaque (false);

        Widget minimize = widget.getMinimizeButton ();
        minimize.setBorder (BORDER_MINIMIZE);

        Widget pinsSeparator = widget.getPinsSeparator ();
        pinsSeparator.setForeground (BORDER_CATEGORY_BACKGROUND);
    }

    public void updateUI (VNodeWidget widget, ObjectState previousState, ObjectState state) {
        if (! previousState.isSelected ()  &&  state.isSelected ())
            widget.bringToFront ();
        else if (! previousState.isHovered ()  &&  state.isHovered ())
            widget.bringToFront ();

        Widget header = widget.getHeader ();
        header.setOpaque (state.isSelected ());
        header.setBorder (state.isFocused () || state.isHovered () ? BORDER_PIN_HOVERED : BORDER_PIN);
    }

    public boolean isNodeMinimizeButtonOnRight (VNodeWidget widget) {
        return false;
    }

    public Image getMinimizeWidgetImage (VNodeWidget widget) {
        return widget.isMinimized ()
                ? Utilities.loadImage ("org/netbeans/modules/visual/resources/vmd-expand.png") // NOI18N
                : Utilities.loadImage ("org/netbeans/modules/visual/resources/vmd-collapse.png"); // NOI18N
    }

    public Widget createPinCategoryWidget (VNodeWidget widget, String categoryDisplayName) {
        return createPinCategoryWidgetCore (widget, categoryDisplayName, true);
    }

    public void installUI (VConnectionWidget widget) {
        widget.setSourceAnchorShape (AnchorShape.NONE);
        widget.setTargetAnchorShape (AnchorShape.TRIANGLE_FILLED);
        widget.setPaintControlPoints (true);
    }

    public void updateUI (VConnectionWidget widget, ObjectState previousState, ObjectState state) {
        if (state.isHovered ())
            widget.setForeground (COLOR_HOVERED);
        else if (state.isSelected ())
            widget.setForeground (COLOR_SELECTED);
        else if (state.isHighlighted ())
            widget.setForeground (COLOR_HIGHLIGHTED);
        else if (state.isFocused ())
            widget.setForeground (COLOR_HOVERED);
        else
            widget.setForeground (COLOR_NORMAL);

        if (state.isSelected ()) {
            widget.setControlPointShape (PointShape.SQUARE_FILLED_SMALL);
            widget.setEndPointShape (PointShape.SQUARE_FILLED_BIG);
        } else {
            widget.setControlPointShape (PointShape.NONE);
            widget.setEndPointShape (POINT_SHAPE_IMAGE);
        }
    }

    public void installUI (VPinWidget widget) {
        widget.setBorder (BORDER_PIN);
        widget.setBackground (COLOR_SELECTED);
        widget.setOpaque (false);
    }

    public void updateUI (VPinWidget widget, ObjectState previousState, ObjectState state) {
        widget.setOpaque (state.isSelected ());
        widget.setBorder (state.isFocused () || state.isHovered () ? BORDER_PIN_HOVERED : BORDER_PIN);
//        LookFeel lookFeel = getScene ().getLookFeel ();
//        setBorder (BorderFactory.createCompositeBorder (BorderFactory.createEmptyBorder (8, 2), lookFeel.getMiniBorder (state)));
//        setForeground (lookFeel.getForeground (state));
    }

    public int getNodeAnchorGap (VNodeAnchor anchor) {
        return 8;
    }

    static Widget createPinCategoryWidgetCore (VNodeWidget widget, String categoryDisplayName, boolean changeFont) {
        Scene scene = widget.getScene ();
        LabelWidget label = new LabelWidget (scene, categoryDisplayName);
        label.setOpaque (true);
        label.setBackground (BORDER_CATEGORY_BACKGROUND);
        label.setForeground (Color.GRAY);
        if (changeFont) {
            Font fontPinCategory = scene.getDefaultFont ().deriveFont (10.0f);
            label.setFont (fontPinCategory);
        }
        label.setAlignment (LabelWidget.Alignment.CENTER);
        label.setCheckClipping (true);
        return label;
    }

}
