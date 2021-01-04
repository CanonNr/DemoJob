package cn.lksun.demojob.core.entity;

import lombok.Data;
import java.util.Map;

@Data
public class Node {

    public String nid;

    public String appName;

    public String url;

    public Map<String, Handle> handleMap;

    public Node(String appName,String url,Map<String, Handle> handleMap){
        this.appName = appName;
        this.url = url;
        this.handleMap = handleMap;
    }

    public Node(){

    }

}
