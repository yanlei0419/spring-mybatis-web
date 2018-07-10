package org.seeker.common.excel.entity;

import java.util.List;

public class ExcelMergeRow {

    private List<ExcelMergeCell> list;

    public List<ExcelMergeCell> getList() {
        return list;
    }

    public void setList(List<ExcelMergeCell> list) {
        this.list = list;
    }

    public void setData(ExcelMergeCell cell) {
        if(this.list==null){
            this.list.add(cell);

        }
    }
}
