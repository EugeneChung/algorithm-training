package codility.lesson99;

import helpers.TestHelper;

import java.util.Stack;

public class TreeHeight {
    static class Tree {
        public int x;
        public Tree l;
        public Tree r;

        public Tree(int i, Tree o, Tree o1) {
            this.x = i;
            this.l = o;
            this.r = o1;
        }
    }

    public static void main(String[] args) {
        Object solution = new Solution().solution(
            new Tree(1, new Tree(1, null, new Tree(1, null, new Tree(1, null, new Tree(1, null, null)))), new Tree(1, new Tree(1, null, new Tree(1, null, null)), null))
        );
        TestHelper.printSolution(solution);
    }

    static class Solution {
        class TreeWithHeight {
            int height;
            Tree node;

            TreeWithHeight(int height, Tree node) {
                this.height = height;
                this.node = node;
            }
        }

        public int solution(Tree T) {
            int height = -1;
            Stack<TreeWithHeight> stack = new Stack<>();
            stack.push(new TreeWithHeight(0, T));

            while (!stack.isEmpty()) {
                TreeWithHeight tree = stack.pop();
                height = Math.max(height, tree.height);
                if (tree.node.l != null) {
                    stack.push(new TreeWithHeight(tree.height + 1, tree.node.l));
                }
                if (tree.node.r != null) {
                    stack.push(new TreeWithHeight(tree.height + 1, tree.node.r));
                }
            }

            return height;
        }
    }
}