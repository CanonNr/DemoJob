package cn.lksun.demojob.core.entity;

import lombok.Data;
import java.util.Map;

@Data
public class Node {

    public String appName;

    public String url;

    public Map<String, Handle> handleMap;

}
