/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luffy.wzqr.wzqrserver.beans.bean;

import jxl.LabelCell;
import jxl.format.CellFormat;
import jxl.write.Label;

/**
 *
 * @author luffy
 */
public class NoNullLabel extends Label {

    public NoNullLabel(int c, int r, String cont) {
        super(c, r, cont == null ? "" : cont);
    }

    public NoNullLabel(int c, int r, String cont, CellFormat st) {
        super(c, r, cont, st);
    }

    public NoNullLabel(int col, int row, Label l) {
        super(col, row, l);
    }

    public NoNullLabel(LabelCell lc) {
        super(lc);
    }

}
