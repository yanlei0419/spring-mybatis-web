package org.seeker.common.excel.entity;

import org.apache.poi.ss.usermodel.CellStyle;

public class ExcelMergeCell {

    private String val;//列值
    private int row=1;//合并行数
    private int cell=1;//合并列数

    private short vertical_align= CellStyle.VERTICAL_CENTER;//垂直
    private short text_align= CellStyle.ALIGN_CENTER;//水平

    public ExcelMergeCell() {
    }

    public ExcelMergeCell(String val) {
        this.val = val;
    }

    public ExcelMergeCell(String val, int cell) {
        this.val = val;
        this.cell = cell;
    }

    public ExcelMergeCell(String val, int row, int cell) {
        this.val = val;
        this.row = row;
        this.cell = cell;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCell() {
        return cell;
    }

    public void setCell(int cell) {
        this.cell = cell;
    }

    public short getVertical_align() {
        return vertical_align;
    }

    public void setVertical_align(short vertical_align) {
        this.vertical_align = vertical_align;
    }

    public short getText_align() {
        return text_align;
    }

    public void setText_align(short text_align) {
        this.text_align = text_align;
    }
}
