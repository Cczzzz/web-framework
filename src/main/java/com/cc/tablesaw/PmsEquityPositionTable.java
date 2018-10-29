package com.cc.tablesaw;

import com.cc.demo.model.PmsEquityPosition;
import tech.tablesaw.api.Table;

import java.util.List;

/**
 * Created by chenchang on 2018/10/17.
 */
public class PmsEquityPositionTable extends BaseTableSaw<PmsEquityPosition>{


    public PmsEquityPositionTable(Table table, List<EntityColumn> entityColumnList) {
        super(table, entityColumnList);
    }
}
