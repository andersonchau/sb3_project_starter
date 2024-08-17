package ac.proj.util;

import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JasperReportBase {

    public List<Pair<String, String>> paramList;
    public List<List<Pair<String, String>>> rowList;

    public JasperReportBase() {
        paramList = new ArrayList<Pair<String, String>>();
        rowList = new ArrayList<List<Pair<String, String>>>();
    }

    public void addParam(String key, String value) {
       // paramList.add( new ImmutablePair(key, value));
    }


    //public void addRow{
    //
    //}


}
