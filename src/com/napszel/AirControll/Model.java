package com.napszel.AirControll;

import android.content.Context;
import android.graphics.Matrix;
import android.util.Pair;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Model {
    private static final Logger LOG = Logger.getLogger(Model.class.getName());

    class Elem {
        int imageResource;
        int rotation; // 0, 1, 2 or 3

        Elem(int imageResource) {
            this.imageResource = imageResource;
            this.rotation = 0;
        }

        void rotate() {
            this.rotation = (this.rotation + 1) % 4;
        }
    }

    static final int WIDTH = 7;
    static final int HEIGHT = 8;

    static private int deindex(int y, int x) {
        return y * WIDTH + x;
    }

    static private Pair<Integer, Integer> index(int i) {
        return Pair.create(i / WIDTH, i % WIDTH);
    }

    public int getCount() {
        return WIDTH * HEIGHT;
    }

    private Elem[][] elems;
    private List<BaseAdapter> toNotifyOnChange = new ArrayList<>();

    public Model(int [][] source) {
        elems = new Elem[HEIGHT][WIDTH];

//        int[][] source =
//                {
//                        {1, 2},
//                        {3, 4},
//                        {5, 6}
//                };

        for (int y = 0; y < HEIGHT; y++)
            for (int x = 0; x < WIDTH; x++)
                elems[y][x] = new Elem(source[y][x]);
    }

    public void rotate(int idx) {
        this.elems[index(idx).first][index(idx).second].rotate();

        notifyAdapters();
    }

    public ImageView getImageView(Context context, int idx) {
        Elem e = this.elems[index(idx).first][index(idx).second];
        ImageView iv = new ImageView(context);
        iv.setScaleType(ImageView.ScaleType.MATRIX);
        Matrix m = new Matrix();
        m.setRotate(e.rotation * 90, 61, 61);
        iv.setImageMatrix(m);
        iv.setImageResource(e.imageResource);

        return iv;
    }

    public Elem getElem(int idx) {
        return this.elems[index(idx).first][index(idx).second];
    }

    public void registerAdapter(BaseAdapter adapter) {
        toNotifyOnChange.add(adapter);
    }

    private void notifyAdapters() {
        for (int i = 0; i < toNotifyOnChange.size(); i++) {
            toNotifyOnChange.get(i).notifyDataSetChanged();
        }
    }
}
