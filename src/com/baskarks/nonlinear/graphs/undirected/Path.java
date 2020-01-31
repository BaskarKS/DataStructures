package com.baskarks.nonlinear.graphs.undirected;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private List<String> nodes = new ArrayList<>();

    @Override
    public String toString() {
        return nodes.toString();
    }

    public void add(String node) {
        nodes.add(node);
    }
}
