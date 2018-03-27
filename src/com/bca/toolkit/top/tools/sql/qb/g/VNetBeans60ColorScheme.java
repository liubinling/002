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

import java.awt.Color;
import java.awt.Image;
import org.netbeans.api.visual.border.Border;
import org.netbeans.api.visual.border.BorderFactory;
import org.netbeans.api.visual.widget.Widget;
import org.netbeans.api.visual.model.ObjectState;
import org.netbeans.api.visual.anchor.AnchorShape;
import org.netbeans.api.visual.anchor.PointShape;
import org.netbeans.api.visual.anchor.PointShapeFactory;
import org.openide.util.Utilities;

/**
 * @author David Kaspar
 */
public class VNetBeans60ColorScheme extends VColorScheme {

    public static final Color COLOR60_SELECT = new Color (0xFF8500);
    public static final Color COLOR60_HOVER = new Color (0x5B67B0);
    public static final Color COLOR60_HOVER_BACKGROUND = new Color (0xB0C3E1);

    private static final Border BORDER60 = VFactory.createVNodeBorder (VOriginalColorScheme.COLOR_NORMAL, 2, VOriginalColorScheme.COLOR1, VOriginalColorScheme.COLOR2, VOriginalColorScheme.COLOR3, VOriginalColorScheme.COLOR4, VOriginalColorScheme.COLOR5);
    private static final Border BORDER60_SELECT = VFactory.createVNodeBorder (COLOR60_SELECT, 2, VOriginalColorScheme.COLOR1, VOriginalColorScheme.COLOR2, VOriginalColorScheme.COLOR3, VOriginalColorScheme.COLOR4, VOriginalColorScheme.COLOR5);
    private static final Border BORDER60_HOVER = VFactory.createVNodeBorder (COLOR60_HOVER, 2, VOriginalColorScheme.COLOR1, VOriginalColorScheme.COLOR2, VOriginalColorScheme.COLOR3, VOriginalColorScheme.COLOR4, VOriginalColorScheme.COLOR5);

    private static final Border BORDER60_PIN_SELECT = BorderFactory.createCompositeBorder (BorderFactory.createLineBorder (0, 1, 0, 1, COLOR60_SELECT), BorderFactory.createLineBorder (2, 7, 2, 7, COLOR60_SELECT));
//        private static final Border BORDER60_PIN_HOVER = BorderFactory.createLineBorder (2, 8, 2, 8, COLOR60_HOVER);

    private static final PointShape POINT_SHAPE60_IMAGE = PointShapeFactory.createImagePointShape (Utilities.loadImage ("org/netbeans/modules/visual/resources/vmd-pin-60.png")); // NOI18N

    public void installUI (VNodeWidget widget) {
        widget.setBorder (BORDER60);

        Widget header = widget.getHeader ();
        header.setBackground (COLOR60_HOVER_BACKGROUND);
        header.setBorder (VOriginalColorScheme.BORDER_PIN);

        Widget pinsSeparator = widget.getPinsSeparator ();
        pinsSeparator.setForeground (VOriginalColorScheme.BORDER_CATEGORY_BACKGROUND);
    }

    public void updateUI (VNodeWidget widget, ObjectState previousState, ObjectState state) {
        if (! previousState.isSelected ()  &&  state.isSelected ())
            widget.bringToFront ();

        boolean hover = state.isHovered () || state.isFocused ();
        widget.getHeader ().setOpaque (hover);

        if (state.isSelected ())
            widget.setBorder (BORDER60_SELECT);
        else if (state.isHovered ())
            widget.setBorder (BORDER60_HOVER);
        else if (state.isFocused ())
            widget.setBorder (BORDER60_HOVER);
        else
            widget.setBorder (BORDER60);
    }

    public void installUI (VConnectionWidget widget) {
        widget.setSourceAnchorShape (AnchorShape.NONE);
        widget.setTargetAnchorShape (AnchorShape.TRIANGLE_FILLED);
        widget.setPaintControlPoints (true);
    }

    public void updateUI (VConnectionWidget widget, ObjectState previousState, ObjectState state) {
        if (state.isSelected ())
            widget.setForeground (COLOR60_SELECT);
        else if (state.isHighlighted ())
            widget.setForeground (VOriginalColorScheme.COLOR_HIGHLIGHTED);
        else if (state.isHovered ()  ||  state.isFocused ())
            widget.setForeground (COLOR60_HOVER);
        else
            widget.setForeground (VOriginalColorScheme.COLOR_NORMAL);

        if (state.isSelected ()  ||  state.isHovered ()) {
            widget.setControlPointShape (PointShape.SQUARE_FILLED_SMALL);
            widget.setEndPointShape (PointShape.SQUARE_FILLED_BIG);
            widget.setControlPointCutDistance (0);
        } else {
            widget.setControlPointShape (PointShape.NONE);
            widget.setEndPointShape (POINT_SHAPE60_IMAGE);
            widget.setControlPointCutDistance (5);
        }
    }

    public void installUI (VPinWidget widget) {
        widget.setBorder (VOriginalColorScheme.BORDER_PIN);
        widget.setBackground (COLOR60_HOVER_BACKGROUND);
    }

    public void updateUI (VPinWidget widget, ObjectState previousState, ObjectState state) {
        widget.setOpaque (state.isHovered ()  ||  state.isFocused ());
        if (state.isSelected ())
            widget.setBorder (BORDER60_PIN_SELECT);
        else
            widget.setBorder (VOriginalColorScheme.BORDER_PIN);
    }

    public int getNodeAnchorGap (VNodeAnchor anchor) {
        return 4;
    }

    public boolean isNodeMinimizeButtonOnRight (VNodeWidget widget) {
        return true;
    }

    public Image getMinimizeWidgetImage (VNodeWidget widget) {
        return widget.isMinimized ()
                ? Utilities.loadImage ("org/netbeans/modules/visual/resources/vmd-expand-60.png") // NOI18N
                : Utilities.loadImage ("org/netbeans/modules/visual/resources/vmd-collapse-60.png"); // NOI18N
    }

    public Widget createPinCategoryWidget (VNodeWidget widget, String categoryDisplayName) {
        return VOriginalColorScheme.createPinCategoryWidgetCore (widget, categoryDisplayName, false);
    }

}
