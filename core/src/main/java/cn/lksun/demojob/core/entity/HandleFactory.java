package cn.lksun.demojob.core.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class HandleFactory<Handle> {
    public Map<String,Handle> map = new HashMap<String,Handle>();
}
