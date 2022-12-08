package uk.co.eandrews.advent2022.day.day08;

import lombok.experimental.ExtensionMethod;
import one.util.streamex.StreamEx;
import org.springframework.stereotype.Component;
import uk.co.eandrews.advent2022.Day2022;
import uk.co.eandrews.util.PuzzleSolution;
import uk.co.eandrews.util.StreamExUtil;
import uk.co.eandrews.util.io.input.parser.InputParser;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component("Day8-2022")
@ExtensionMethod({StreamEx.class, StreamExUtil.class})
public class Day8 extends Day2022<Integer[][], Long> {

    public Day8(final InputParser<Integer[][]> inputParser) {
        super(8, inputParser);
    }

    @Override
    public Collection<PuzzleSolution<Integer[][], Long>> getSolutions() {
        return List.of(
            partOneSolution(),
            partTwoSolution()
        );
    }

    public PuzzleSolution<Integer[][], Long> partOneSolution() {
        return input -> {

            Set<String> visible = new HashSet<>();

            int maxHeight=-1;

            for (int i=0; i<input.length; i++) {
                for (int j = 0; j<input[i].length; j++){
                    int tree = input[i][j];
                    if (tree>maxHeight) {
                        maxHeight = tree;
                        visible.add("("+i+","+j+")");
                    }
                }
                maxHeight=-1;
            }

            for (int i=0; i<input.length; i++) {
                for (int j = input[i].length-1; j>=0; j--){
                    int tree = input[i][j];
                    if (tree>maxHeight) {
                        maxHeight = tree;
                        visible.add("("+i+","+j+")");
                    }
                }
                maxHeight=-1;
            }


            for (int j = 0; j<input[0].length; j++){
                for (int i=0; i<input.length; i++) {
                    int tree = input[i][j];
                    if (tree>maxHeight) {
                        maxHeight = tree;
                        visible.add("("+i+","+j+")");
                    }
                }
                maxHeight=-1;
            }

            for (int j = 0; j<input[0].length; j++){
                for (int i = input.length-1; i>=0; i--){
                    int tree = input[i][j];
                    if (tree>maxHeight) {
                        maxHeight = tree;
                        visible.add("("+i+","+j+")");
                    }
                }
                maxHeight=-1;
            }

            return Long.valueOf(visible.size());
        };
    }

    public PuzzleSolution<Integer[][], Long> partTwoSolution() {
        return input -> {
            int maxScenicScore=0;

            for (int i = 0; i<input.length; i++) {
                for (int j = 0; j<input[i].length; j++) {
                    int scenicScore = getScenicScore(i,j,input);
                    if (scenicScore>maxScenicScore) {
                        maxScenicScore = scenicScore;
                    }
                }
            }
            return Long.valueOf(maxScenicScore);
        };
    }



    private int getScenicScore(final int x, final int y, Integer[][] trees) {

        int treeHeight=trees[x][y];
        int tmpX = x+1;

        int scenicScore=1;

        int view = 0;

        while (tmpX<trees.length) {

            int nextTreeHeight = trees[tmpX][y];
            view++;

            if (nextTreeHeight>=treeHeight) {
                break;
            }

            tmpX++;
        }

        scenicScore=scenicScore*view;

        tmpX = x-1;
        view = 0;

        while (tmpX>=0) {

            int nextTreeHeight = trees[tmpX][y];
            view++;

            if (nextTreeHeight>=treeHeight) {
                break;
            }

            tmpX--;
        }

        scenicScore=scenicScore*view;

        int tmpY = y+1;
        view = 0;

        while (tmpY<trees[0].length) {

            int nextTreeHeight = trees[x][tmpY];
            view++;

            if (nextTreeHeight>=treeHeight) {
                break;
            }

            tmpY++;
        }

        scenicScore=scenicScore*view;

        tmpY = y-1;
        view = 0;

        while (tmpY>=0) {
            int nextTreeHeight = trees[x][tmpY];
            view++;

            if (nextTreeHeight>=treeHeight) {
                break;
            }

            tmpY--;
        }

        scenicScore=scenicScore*view;

        return scenicScore;
    }


}
