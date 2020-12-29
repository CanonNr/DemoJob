package cn.lksun.demojob.admin.entity;

import lombok.Data;
import java.util.LinkedList;

@Data
public class NodeGroup {
    public LinkedList<Node> nodes = new LinkedList<Node>();
}
