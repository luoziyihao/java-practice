package com.luozi.json.gson.http.parser;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.luozi.json.domain.Model1_Map;
import com.luozi.json.domain.Model2_List;
import org.junit.Before;
import org.junit.Test;

/**
 * 只需要将传入类型改成 传入 type 即可
 * 传入 tyep 可以支持泛型
 * Created by luoziyihao on 9/7/16.
 */
public class JsonParserFactoryTest {


    private String Model1_Map_str;

    private String Model2_List_str;

    @Before
    public void createJsonStr(){
        Model1_Map model1_map = new Model1_Map();
        model1_map.setCode(0);
        model1_map.setMsg("tst");
        model1_map.setItems(ImmutableMap.of("k1", "v1", "k2", "v2", "k3", "v3"));
        this.Model1_Map_str = new Gson().toJson(model1_map);

        Model2_List model2_list = new Model2_List();
        model2_list.setCode(0);
        model2_list.setMsg("tst");
        model2_list.setItems(ImmutableList.of("v1", "v2", "v3"));
        this.Model2_List_str = new Gson().toJson(model2_list);
    }

    @Test
    public void testParseStringJson() throws Exception {
        Model1_Map model1_map = JsonParserFactory.parseStringJson(new TypeToken<Model1_Map>(){}.getType(),  Model1_Map_str);
        System.out.println(model1_map);

        Model2_List model2_list= JsonParserFactory.parseStringJson(new TypeToken<Model2_List>(){}.getType(),  Model2_List_str);
        System.out.println(model2_list);

    }

}