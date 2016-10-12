package com.luozi.json.gson.http.parser;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.luozi.json.entity.User;
import com.luozi.json.gson.model.Model;
import com.luozi.json.gson.model.Model1_Map;
import com.luozi.json.gson.model.Model2_List;
import com.luozi.json.gson.model.Model3_User;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * JsonParserFactoryTest 测试
 */
public class JsonParserFactoryTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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


    /**
     * 默认支持基本类型 包括List, Map, 值对象
     * @throws Exception
     */
    @Test
    public void testParseStringJson() throws Exception {
        Model1_Map model1_map = JsonParserFactory.parseStringJson(Model1_Map.class,  Model1_Map_str);
        infoOrigin(model1_map);

        Model2_List model2_list= JsonParserFactory.parseStringJson(Model2_List.class,  Model2_List_str);
        infoOrigin(model2_list);

        Model3_User model3_user= JsonParserFactory.parseStringJson(Model3_User.class,  Model3_User_str);
        infoOrigin(model3_user);

        Model2_List model2_list_2 = JsonParserFactory.parseStringJson(Model2_List.class,  Model2_List_str_2);
        infoOrigin(model2_list_2);

        septation();
    }

    /**
     * 默认支持基本类型 包括List, Map, 值对象
     * @throws Exception
     */
    @Test
    public void testParseBaseModel() throws Exception {
        IResultDataParser<Model1_Map> model1_mapIResultDataParser = JsonParserFactory.parseBaseModel(Model1_Map.class);
        Model1_Map model1_map = model1_mapIResultDataParser.parse(Model1_Map_str);
        infoOrigin(model1_map);

        IResultDataParser<Model2_List> model2_listIResultDataParser = JsonParserFactory.parseBaseModel(Model2_List.class);
        Model2_List model2_list = model2_listIResultDataParser.parse(Model2_List_str);
        infoOrigin(model2_list);

        IResultDataParser<Model3_User> model3_userIResultDataParser = JsonParserFactory.parseBaseModel(Model3_User.class);
        Model3_User model3_user = model3_userIResultDataParser.parse(Model3_User_str);
        infoOrigin(model3_user);

        IResultDataParser<Model2_List> model2_listIResultDataParser_2 = JsonParserFactory.parseBaseModel(Model2_List.class);
        Model2_List model2_list_2 = model2_listIResultDataParser_2.parse(Model2_List_str_2);
        infoOrigin(model2_list_2);

        septation();
    }

    /**
     * 默认支持基本类型 包括List, Map, 值对象
     * 支持泛型
     * @throws Exception
     */
    @Test
    public void testParseStringJson2() throws Exception {
        Model model1_map = JsonParserFactory.parseStringJson(new TypeToken<Model<Map>>(){}.getType(),  Model1_Map_str);
        infoNew(model1_map);

        Model model2_list= JsonParserFactory.parseStringJson(new TypeToken<Model<List>>(){}.getType(),  Model2_List_str);
        infoNew(model2_list);

        Model model3_user= JsonParserFactory.parseStringJson(new TypeToken<Model<User>>(){}.getType(),  Model3_User_str);
        infoNew(model3_user);

        Model model2_list_2 = JsonParserFactory.parseStringJson(new TypeToken<Model<List<User>>>(){}.getType(),  Model2_List_str_2);
        infoNew(model2_list_2);

        septation();
    }

    /**
     * 默认支持基本类型 包括List, Map, 值对象
     * 支持泛型
     * @throws Exception
     */
    @Test
    public void testParseBaseModel2() throws Exception {
        IResultDataParser<Model1_Map> model1_mapIResultDataParser = JsonParserFactory.parseBaseModel(new TypeToken<Model1_Map>(){}.getType());
        Model1_Map model1_map = model1_mapIResultDataParser.parse(Model1_Map_str);
        infoNew(model1_map);

        IResultDataParser<Model2_List> model2_listIResultDataParser = JsonParserFactory.parseBaseModel(new TypeToken<Model2_List>(){}.getType());
        Model2_List model2_list = model2_listIResultDataParser.parse(Model2_List_str);
        infoNew(model2_list);

        IResultDataParser<Model3_User> model3_userIResultDataParser = JsonParserFactory.parseBaseModel(new TypeToken<Model3_User>(){}.getType());
        Model3_User model3_user = model3_userIResultDataParser.parse(Model3_User_str);
        infoNew(model3_user);

        IResultDataParser<Model2_List> model2_listIResultDataParser_2 = JsonParserFactory.parseBaseModel(new TypeToken<Model2_List>(){}.getType());
        Model2_List model2_list_2 = model2_listIResultDataParser_2.parse(Model2_List_str_2);
        infoNew(model2_list_2);

        septation();
    }

    private void infoOrigin(Object o) {
        logger.info("use origin json parser {}", o);
    }

    private void infoNew(Object o) {
        logger.info("use new json parser {}", o);
    }

    private void septation(){
        logger.info("###############################\n");
    }
}