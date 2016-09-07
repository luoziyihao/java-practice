package com.luozi.json.gson.http.parser;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.luozi.json.entity.User;
import com.luozi.json.gson.model.Model1_Map;
import com.luozi.json.gson.model.Model2_List;
import com.luozi.json.gson.model.Model3_User;
import org.junit.Before;
import org.junit.Test;

/**
 * 只需要将传入 的 class 替换成 type
 * 传入 tyep 可以支持泛型
 * Created by luoziyihao on 9/7/16.
 */
public class JsonParserFactoryTest {


    private String Model1_Map_str;

    private String Model2_List_str;

    private String Model2_List_str_2;

    private String Model3_User_str;

    private Gson gson = new Gson();

    @Before
    public void createJsonStr(){
        Model1_Map model1_map = new Model1_Map();
        model1_map.setCode(0);
        model1_map.setMsg("tst items with Map");
        model1_map.setItems(ImmutableMap.of("k1", "v1", "k2", "v2", "k3", "v3"));
        this.Model1_Map_str = gson.toJson(model1_map);

        Model2_List model2_list = new Model2_List();
        model2_list.setCode(0);
        model2_list.setMsg("tst items with List");
        model2_list.setItems(ImmutableList.of("v1", "v2", "v3"));
        this.Model2_List_str = gson.toJson(model2_list);

        Model3_User model3_user = new Model3_User();
        model3_user.setCode(0);
        model3_user.setMsg("tst items with User Object");
        model3_user.setItems(new User(16, "daisy"));
        this.Model3_User_str = gson.toJson(model3_user);

        Model2_List model2_list_2 = new Model2_List();
        model2_list_2.setCode(0);
        model2_list_2.setMsg("tst items with List of User Object");
        model2_list_2.setItems(ImmutableList.of(new User(17, "daisy"), new User(19, "miki"), new User(26, "tina")));
        this.Model2_List_str_2 = gson.toJson(model2_list_2);
    }

    @Test
    public void testParseStringJson() throws Exception {
        Model1_Map model1_map = JsonParserFactory.parseStringJson(new TypeToken<Model1_Map>(){}.getType(),  Model1_Map_str);
        System.out.println(model1_map);

        Model2_List model2_list= JsonParserFactory.parseStringJson(new TypeToken<Model2_List>(){}.getType(),  Model2_List_str);
        System.out.println(model2_list);

        Model3_User model3_user= JsonParserFactory.parseStringJson(new TypeToken<Model3_User>(){}.getType(),  Model3_User_str);
        System.out.println(model3_user);

        Model2_List model2_list_2 = JsonParserFactory.parseStringJson(new TypeToken<Model2_List>(){}.getType(),  Model2_List_str_2);
        System.out.println(model2_list_2);
    }

    @Test
    public void testParseBaseModel() throws Exception {
        IResultDataParser<Model1_Map> model1_mapIResultDataParser = JsonParserFactory.parseBaseModel(new TypeToken<Model1_Map>(){}.getType());
        Model1_Map model1_map = model1_mapIResultDataParser.parse(Model1_Map_str);
        System.out.println(model1_map);

        IResultDataParser<Model2_List> model2_listIResultDataParser = JsonParserFactory.parseBaseModel(new TypeToken<Model2_List>(){}.getType());
        Model2_List model2_list = model2_listIResultDataParser.parse(Model2_List_str);
        System.out.println(model2_list);

        IResultDataParser<Model3_User> model3_userIResultDataParser = JsonParserFactory.parseBaseModel(new TypeToken<Model3_User>(){}.getType());
        Model3_User model3_user = model3_userIResultDataParser.parse(Model3_User_str);
        System.out.println(model3_user);

        IResultDataParser<Model2_List> model2_listIResultDataParser_2 = JsonParserFactory.parseBaseModel(new TypeToken<Model2_List>(){}.getType());
        Model2_List model2_list_2 = model2_listIResultDataParser_2.parse(Model2_List_str_2);
        System.out.println(model2_list_2);
    }
}