package me.thamma; /**
 * Created by Dominic on 1/20/2016.
 */
import static me.thamma.Cube.*;

public enum Turn {
    RIGHT(2, new int[]{ULB, UB, UBR, UR, URF, UF, UFL, UL}, new int[]{0, 0, 0, 0, 0, 0, 0, 0});

    private int offset;
    private int[] target, rotation;

    Turn(int offset, int[] target, int[] rotation) {
        this.offset = offset;
        this.target = target;
        this.rotation = rotation;
    }

    public int getOffset() {
        return this.offset;
    }
    public int[] getTarget() {
        return this.target;
    }

    public int[] getRotation(){
        return this.rotation;
    }


}
