package 第68场双周赛;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode.cn/problems/find-all-possible-recipes-from-given-supplies/description/
 * 
 * 从给定原材料中找到所有可以做出的菜（中等）
 * 你有 n 道不同菜的信息。给你一个字符串数组 recipes 和一个二维字符串数组 ingredients 。
 * 第 i 道菜的名字为 recipes[i] ，如果你有它 所有 的原材料 ingredients[i] ，那么你可以 做出 这道菜。
 * 一道菜的原材料可能是 另一道 菜，也就是说 ingredients[i] 可能包含 recipes 中另一个字符串。
 * 同时给你一个字符串数组 supplies ，它包含你初始时拥有的所有原材料，每一种原材料你都有无限多。
 * 请你返回你可以做出的所有菜。你可以以 任意顺序 返回它们。
 * 
 * 注意两道菜在它们的原材料中可能互相包含。
 * 
 * 示例：
 * 输入：recipes = ["bread"], ingredients = [["yeast","flour"]], supplies = ["yeast","flour","corn"]
 * 输出：["bread"]
 * 
 * 输入：recipes = ["bread","sandwich"], ingredients = [["yeast","flour"],["bread","meat"]], supplies = ["yeast","flour","meat"]
 * 输出：["bread","sandwich"]
 * 
 * 输入：recipes = ["bread","sandwich","burger"], ingredients = [["yeast","flour"],["bread","meat"],["sandwich","meat","bread"]], supplies = ["yeast","flour","meat"]
 * 输出：["bread","sandwich","burger"]
 * 
 * 输入：recipes = ["bread"], ingredients = [["yeast","flour"]], supplies = ["yeast"]
 * 输出：[]
 * 
 * n == recipes.length == ingredients.length
 * 1 <= n <= 100
 * 1 <= ingredients[i].length, supplies.length <= 100
 * 1 <= recipes[i].length, ingredients[i][j].length, supplies[k].length <= 10
 * recipes[i], ingredients[i][j] 和 supplies[k] 只包含小写英文字母。
 * 所有 recipes 和 supplies 中的值互不相同。
 * ingredients[i] 中的字符串互不相同。
 */

/**
 * 一句话题解：
 * 实际上是拓扑排序的过程;
 * 构建图，如果一道菜需要某种原材料，那么原材料到菜有一条有向边;
 * 再构建入度的HashMap,supplies中的原材料入度都为0;
 * BFS遍历拓扑图，入度为0的入队列，如果是菜，就加入到做出的菜集合中。
 */

 class Solution {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Set<String> recipeSet = new HashSet<>(Arrays.asList(recipes));
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> indegrees = new HashMap<>();
        for(int i=0; i<ingredients.size(); i++) {
            for(String ingredient : ingredients.get(i)) {
                if(!graph.containsKey(ingredient)) {
                    graph.put(ingredient, new ArrayList<>());
                }
                List<String> recipeList = graph.get(ingredient);
                recipeList.add(recipes[i]);
            }
            indegrees.put(recipes[i], ingredients.get(i).size());
        }

        Queue<String> q = new LinkedList<>();
        for(String supply : supplies) {
            q.offer(supply);
        }
        List<String> ans = new ArrayList<>();
        while(!q.isEmpty()) {
            String ingredient = q.poll();
            if(graph.containsKey(ingredient)) {
                for(String recipe : graph.get(ingredient)) {
                    int indegree = indegrees.get(recipe);
                    if(indegree == 1) {
                        indegrees.remove(recipe);
                        q.offer(recipe);
                        if(recipeSet.contains(recipe)) {
                            ans.add(recipe);
                        }
                    }
                    else {
                        indegrees.put(recipe, indegree - 1);
                    }
                }
            }
        }
        return ans;
    }
}